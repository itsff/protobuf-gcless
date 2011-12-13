package com.google.code.proto.memless;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemlessGenerator {

	private static final String HEADER = "// Generated by the protocol buffer memless compiler.  DO NOT EDIT!\n";

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("invalid usage. Expected: <output-path> <proto-files>");
			return;
		}

		File output = new File(args[0]);
		if (!output.exists()) {
			System.out.println("<output-path> doesnt exist");
			return;
		}
		if (!output.isDirectory()) {
			System.out.println("<output-path> is not a directory");
			return;
		}

		for (int i = 1; i < args.length; i++) {
			try {
				process(output, args[i]);
			} catch (Exception e) {
				System.out.println("unable to process: " + args[i]);
				e.printStackTrace();
			}
		}
	}

	private static void process(File output, String filename) throws Exception {
		MemlessParser parser = new MemlessParser();
		parser.process(filename);
		String packageName = parser.getPackageName();
		if (packageName != null) {
			output = createPackage(output, packageName);
		}

		BufferedWriter w = null;
		if (parser.getOuterClassName() != null) {
			w = new BufferedWriter(new FileWriter(new File(output, parser.getOuterClassName() + ".java")));
			w.append(HEADER);
			appendPackage(w, parser.getPackageName());
			appendImport(w, "java.io.IOException");
			appendImport(w, "com.google.code.proto.memless.ProtobufOutputStream");
			w.append("public final class ");
			w.append(parser.getOuterClassName());
			w.append(" {\nprivate ");
			w.append(parser.getOuterClassName());
			w.append("() {}\n");
		}

		for (ProtobufEnum curEnum : parser.getEnums()) {
			String curEnumData = generateEnum(curEnum);
			if (parser.getOuterClassName() != null) {
				w.append(curEnumData);
			} else {
				BufferedWriter enumWriter = new BufferedWriter(new FileWriter(new File(output, curEnum.getName() + ".java")));
				appendPackage(enumWriter, parser.getPackageName());
				enumWriter.append(curEnumData);
				enumWriter.flush();
				enumWriter.close();
			}
		}

		for (ProtobufMessage curMessage : parser.getMessages()) {
			String curMessageData = generateMessage(curMessage, parser.getOuterClassName());
			String serializerData = generateSerializer(curMessage, parser.getOuterClassName());
			if (parser.getOuterClassName() != null) {
				w.append(curMessageData);
				w.append(serializerData);
			} else {
				BufferedWriter messageWriter = new BufferedWriter(new FileWriter(new File(output, curMessage.getName() + ".java")));
				appendPackage(messageWriter, parser.getPackageName());
				messageWriter.append(curMessageData);
				messageWriter.flush();
				messageWriter.close();
				messageWriter = new BufferedWriter(new FileWriter(new File(output, curMessage.getName() + "Serializer" + ".java")));
				appendPackage(messageWriter, parser.getPackageName());
				if (!curMessage.getFields().isEmpty()) {
					appendImport(messageWriter, "java.io.IOException");
					appendImport(messageWriter, "com.google.code.proto.memless.ProtobufOutputStream");
				}
				messageWriter.append(serializerData);
				messageWriter.flush();
				messageWriter.close();
			}
		}

		if (parser.getOuterClassName() != null) {
			w.append("}\n\n");
			w.flush();
			w.close();
		}

		String generateDefaultImpl = System.getProperty("generate.default");
		if (generateDefaultImpl != null && generateDefaultImpl.equals("true")) {
			for (ProtobufMessage curMessage : parser.getMessages()) {
				generateDefaultMessageImpl(curMessage, output, parser.getPackageName());
			}
		}

	}

	private static String generateSerializer(ProtobufMessage curMessage, String outerClassName) {
		StringBuilder result = new StringBuilder();
		if (outerClassName == null) {
			result.append("public final class ");
		} else {
			result.append("public static class ");
		}
		String fullMessageType = curMessage.getFullyClarifiedName();
		result.append(curMessage.getName());
		result.append("Serializer {\npublic static byte[] serialize(");
		result.append(fullMessageType);
		result.append(" message) {\n");
		if (curMessage.getFields().isEmpty()) {
			result.append("return new byte[0]; \n}\n");
		} else {
			result.append("try {\nassertInitialized(message);\nint totalSize = 0;\n");
			for (ProtobufField curField : curMessage.getFields()) {
				if (curField.isComplexType() && !curField.isEnumType()) {
					result.append("byte[] " + curField.getName() + "Buffer = null;\n");
					result.append("if (message.has" + curField.getBeanName() + "()) {\n");
					if (curField.getNature().equals("repeated")) {
						result.append("java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();\n");
						result.append("totalSize += ProtobufOutputStream.computeTagSize(" + curField.getTag() + ");\n");
						result.append("totalSize += ProtobufOutputStream.computeRawVarint32Size(message.get" + curField.getBeanName() + "().size());\n");
						result.append("for( int i=0;i<message.get" + curField.getBeanName() + "().size();i++) {\n");
						result.append("baos.write(" + curField.getFullyClarifiedJavaType() + "Serializer.serialize(message.get" + curField.getBeanName() + "().get(i)));\n");
						result.append("}\n");
						result.append(curField.getName() + "Buffer = baos.toByteArray();\n");
					} else {
						result.append(curField.getName() + "Buffer = " + curField.getFullyClarifiedJavaType() + "Serializer.serialize(message.get" + curField.getBeanName() + "());\n");
					}
					result.append("totalSize += " + curField.getName() + "Buffer.length;\n");
					//TODO remove next line
					result.append("System.out.println(totalSize);\n");
					result.append("}\n");
					continue;
				}
				if (curField.getType().equals("string")) {
					result.append("byte[] " + curField.getName() + "Buffer = null;\n");
					if (curField.getNature().equals("repeated")) {
						result.append("if (message.has" + curField.getBeanName() + "()) {\n");
						result.append("java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();\n");
						result.append("for( int i=0;i<message.get" + curField.getBeanName() + "().size();i++) {\n");
						result.append("ProtobufOutputStream.writeString(" + curField.getTag() + ", message.get" + curField.getBeanName() + "().get(i), baos);\n");
						result.append("}\n");
						result.append(curField.getName() + "Buffer = baos.toByteArray();\n");
						result.append("totalSize += ProtobufOutputStream.computeTagSize(" + curField.getTag() + ");\n");
						result.append("totalSize += ProtobufOutputStream.computeRawVarint32Size(" + curField.getName() + "Buffer.length);\n");
						result.append("totalSize += " + curField.getName() + "Buffer.length;\n");
						//TODO remove next line
						result.append("System.out.println(totalSize);\n");
						result.append("}\n");
					} else {
						result.append("if (message.has" + curField.getBeanName() + "()) {\n");
						result.append(curField.getName() + "Buffer = message.get" + curField.getBeanName() + "().getBytes(\"UTF-8\");\n");
						result.append("totalSize += " + curField.getName() + "Buffer.length;\n");
						result.append("totalSize += ProtobufOutputStream.computeTagSize(" + curField.getTag() + ");\n");
						result.append("totalSize += ProtobufOutputStream.computeRawVarint32Size(" + curField.getName() + "Buffer.length);\n");
						//TODO remove next line
						result.append("System.out.println(totalSize);\n");
						result.append("}\n");
					}
					continue;
				}

				if (curField.getNature().equals("repeated")) {
					result.append("int " + curField.getName() + "Size = 0;\n");
					result.append("if (message.has" + curField.getBeanName() + "()) {\n");
					if (curField.getType().equals("bytes")) {
						result.append(curField.getName() + "Size = message.get" + curField.getBeanName() + "().length;\n");
					} else {
						result.append("for(int i=0;i<message.get" + curField.getBeanName() + "().size();i++) {\n");
						if (curField.isEnumType()) {
							result.append(curField.getName() + "Size += ProtobufOutputStream.computeEnumSizeNoTag(message.get" + curField.getBeanName() + "().get(i).getValue());\n");
						} else {
							result.append(curField.getName() + "Size += ProtobufOutputStream.compute" + curField.getStreamBeanType() + "SizeNoTag(message.get" + curField.getBeanName() + "().get(i));\n");
						}
						result.append("}\n");
						result.append("totalSize += ProtobufOutputStream.computeTagSize(" + curField.getTag() + ");\n");
						result.append("totalSize += ProtobufOutputStream.computeRawVarint32Size(message.get" + curField.getBeanName() + "().size());\n");
					}
					result.append("totalSize += " + curField.getName() + "Size;\n}\n");
					//TODO remove next line
					result.append("System.out.println(totalSize);\n");
				} else {
					result.append("if (message.has" + curField.getBeanName() + "()) {\n");
					result.append("totalSize += ");
					if (curField.getType().equals("bytes")) {
						result.append("message.get" + curField.getBeanName() + "().length;\n");
						result.append("totalSize += ProtobufOutputStream.computeTagSize(" + curField.getTag() + ");\n");
						result.append("totalSize += ProtobufOutputStream.computeRawVarint32Size(message.get" + curField.getBeanName() + "().length);\n");
					} else if (curField.isEnumType()) {
						result.append("ProtobufOutputStream.computeEnumSize(" + curField.getTag() + ", message.get" + curField.getBeanName() + "().getValue());\n");
					} else {
						result.append("ProtobufOutputStream.compute" + curField.getStreamBeanType() + "Size(" + curField.getTag() + ", message.get" + curField.getBeanName() + "());\n");
					}
					//TODO remove next line
					result.append("System.out.println(totalSize);\n");
					result.append("}\n");
				}
			}
			result.append("final byte[] result = new byte[totalSize];\nint position = 0;\n");
			for (ProtobufField curField : curMessage.getFields()) {
				result.append("if (message.has" + curField.getBeanName() + "()) {\n");
				if (curField.getType().equals("string")) {
					result.append("position = ProtobufOutputStream.writeString(" + curField.getTag() + "," + curField.getName() + "Buffer, result, position);\n");
					//TODO remove next line
					result.append("System.out.println(position);\n");
				} else if (curField.getNature().equals("repeated")) {
					if( curField.isComplexType() ) {
						result.append("//TODO\n");
					} else {
						result.append("position = ProtobufOutputStream.writeRepeated" + curField.getStreamBeanType() + "(" + curField.getTag() + ", message.get" + curField.getBeanName() + "(), result, position);\n");
					}
//					if (curField.getType().equals("bytes")) {
//						result.append("if (message.get" + curField.getBeanName() + "().length != 0) {\n");
//					} else {
//						result.append("if (message.get" + curField.getBeanName() + "().size() > 0) {\n");
//					}
//					result.append("position = ProtobufOutputStream.writeRawVarint32(" + curField.getTag() + ", result, position);\n");
//					result.append("position = ProtobufOutputStream.writeRawVarint32(");
//					if( curField.isEnumType() ) {
//						result.append("message.get" + curField.getBeanName() + "().size(), result, position);\n");
//					} else if (curField.isComplexType()) {
//						result.append(curField.getName());
//						result.append("Buffer.length, result, position);\n");
//					} else {
//						result.append(curField.getName());
//						result.append("Size, result, position);\n");
//					}
//					if (curField.getType().equals("bytes")) {
//						result.append("position = ProtobufOutputStream.writeRawBytes(message.get" + curField.getBeanName() + "(), result, position);\n");
//						//TODO remove next line
//						result.append("System.out.println(position);\n");
//					} else {
//						if (curField.isComplexType()) {
//							if (curField.isEnumType()) {
//								result.append("for( int i=0;i<message.get" + curField.getBeanName() + "().size();i++) {\n");
//								result.append("position = ProtobufOutputStream.writeEnumNoTag(message.get" + curField.getBeanName() + "().get(i).getValue(), result, position);\n");
//								result.append("}\n");
//								//TODO remove next line
//								result.append("System.out.println(position);\n");
//							} else {
//								result.append("position = ProtobufOutputStream.writeRawBytes(" + curField.getName() + "Buffer, result, position);\n");
//								//TODO remove next line
//								result.append("System.out.println(position);\n");
//							}
//						} else {
//							result.append("for( int i=0;i<message.get" + curField.getBeanName() + "().size();i++) {\n");
//							result.append("position = ProtobufOutputStream.write" + curField.getStreamBeanType() + "NoTag(message.get" + curField.getBeanName() + "().get(i), result, position);\n");
//							result.append("}\n");
//							//TODO remove next line
//							result.append("System.out.println(position);\n");
//						}
//					}
//					result.append("}\n}\n");
				} else {
					writeSingleField(curField, result);
				}
				result.append("}\n");
			}
			result.append("ProtobufOutputStream.checkNoSpaceLeft(result, position);\nreturn result;\n} catch (IOException e) {\n	throw new RuntimeException(\"Serializing to a byte array threw an IOException (should never happen).\", e);}\n}\n");
			result.append("private static void assertInitialized(");
			result.append(fullMessageType);
			result.append(" message) {\n");
			for (ProtobufField curField : curMessage.getFields()) {
				if (curField.getNature().equals("required")) {
					result.append("if( !message.has" + curField.getBeanName() + "()) {\n");
					result.append("throw new IllegalArgumentException(\"Required field not initialized: ");
					result.append(curField.getName());
					result.append("\");\n}\n");
				}
			}
			result.append("}\n");
		}
		result.append("}\n");
		return result.toString();
	}

	private static void writeSingleField(ProtobufField curField, StringBuilder result) {
		result.append("position = ProtobufOutputStream.write");
		if (!curField.isComplexType()) {
			result.append(curField.getStreamBeanType());
			result.append("(");
			result.append(curField.getTag());
			result.append(", message.get");
			result.append(curField.getBeanName());
			result.append("(), result, position);\n");
		} else {
			if (curField.isEnumType()) {
				result.append("Enum(" + curField.getTag() + ", message.get" + curField.getBeanName() + "().getValue(), result, position);\n");
			} else {
				result.append("Bytes(" + curField.getTag() + ", " + curField.getName() + "Buffer, result, position);\n");
			}
		}
		//TODO remove next line
		result.append("System.out.println(position);\n");
	}

	private static void generateDefaultMessageImpl(ProtobufMessage curMessage, File output, String packageName) throws Exception {
		StringBuilder result = new StringBuilder();
		result.append("public class " + curMessage.getName() + "Impl implements " + curMessage.getFullyClarifiedName() + " {\n");
		for (ProtobufField curField : curMessage.getFields()) {
			String javaType = constructType(curField, curMessage);
			result.append("private " + javaType + " " + curField.getBeanName() + ";\n");
			result.append("private boolean has" + curField.getBeanName() + ";\n");
			result.append("public boolean has" + curField.getBeanName() + "() {\n");
			result.append("return has" + curField.getBeanName() + ";\n");
			result.append("}\n");
			result.append("public " + javaType + " get" + curField.getBeanName() + "() {\n");
			result.append("return " + curField.getBeanName() + ";\n");
			result.append("}\n");
			result.append("public void set" + curField.getBeanName() + "(" + javaType + " " + curField.getBeanName() + ") {\n");
			result.append("this." + curField.getBeanName() + " = " + curField.getBeanName() + ";\n");
			result.append("this.has" + curField.getBeanName() + " = true;\n");
			result.append("}\n");
		}
		result.append("}\n");

		BufferedWriter messageWriter = new BufferedWriter(new FileWriter(new File(output, curMessage.getName() + "Impl.java")));
		appendPackage(messageWriter, packageName);
		messageWriter.append(result.toString());
		messageWriter.flush();
		messageWriter.close();

		if (curMessage.getNestedMessages() != null) {
			for (ProtobufMessage message : curMessage.getNestedMessages()) {
				generateDefaultMessageImpl(message, output, packageName);
			}
		}

	}

	private static String generateMessage(ProtobufMessage curMessage, String outerClassName) {
		StringBuilder result = new StringBuilder();
		result.append("public interface ");
		result.append(curMessage.getName());
		result.append(" {\n");
		for (ProtobufField curField : curMessage.getFields()) {
			if (curField.isDeprecated()) {
				result.append("@Deprecated\n");
			}
			result.append("boolean has");
			result.append(curField.getBeanName());
			result.append("();\n");
			String javaType = constructType(curField, curMessage);
			if (curField.isDeprecated()) {
				result.append("@Deprecated\n");
			}
			result.append(javaType);
			result.append(" get");
			result.append(curField.getBeanName());
			result.append("();\n");
			if (curField.isDeprecated()) {
				result.append("@Deprecated\n");
			}
			result.append("void set");
			result.append(curField.getBeanName());
			result.append("(");
			result.append(javaType);
			result.append(" ");
			result.append(curField.getName());
			result.append(");\n");
		}
		for (ProtobufMessage innerMessage : curMessage.getNestedMessages()) {
			result.append(generateMessage(innerMessage, outerClassName));
			String serializerData = generateSerializer(innerMessage, outerClassName);
			result.append(serializerData);
		}
		for (ProtobufEnum curEnum : curMessage.getEnums()) {
			result.append(generateEnum(curEnum));
		}
		result.append("}\n");
		return result.toString();
	}

	private static String generateEnum(ProtobufEnum pEnum) {
		StringBuilder result = new StringBuilder();
		result.append("public enum ");
		result.append(pEnum.getName());
		result.append(" {\n");
		Map<Long, EnumValue> added = new HashMap<Long, EnumValue>();
		List<EnumValue> duplicate = new ArrayList<EnumValue>();
		for (EnumValue curValue : pEnum.getValues()) {
			if (added.containsKey(curValue.getId())) {
				duplicate.add(curValue);
				continue;
			}
			result.append(curValue.getName());
			result.append("(");
			result.append(curValue.getId());
			result.append("),\n");
			added.put(curValue.getId(), curValue);
		}

		for (EnumValue curDuplicate : duplicate) {
			result.append(";\npublic static final ");
			result.append(pEnum.getName());
			result.append(" ");
			result.append(curDuplicate.getName());
			result.append(" = ");
			result.append(added.get(curDuplicate.getId()).getName());
		}

		result.append(";\npublic static ");
		result.append(pEnum.getName());
		result.append(" valueOf(int value) {\nswitch (value) {\n");
		for (EnumValue curValue : pEnum.getValues()) {
			if (duplicate.contains(curValue)) {
				continue;
			}
			result.append("case ");
			result.append(curValue.getId());
			result.append(": return ");
			result.append(curValue.getName());
			result.append(";\n");
		}
		result.append("default: return null;\n}\n}\nprivate ");
		result.append(pEnum.getName());
		result.append("(int value) {\nthis.value = value;\n}\nprivate int value;\npublic int getValue() {\nreturn value;\n}\n}\n\n");
		return result.toString();
	}

	private static void appendPackage(BufferedWriter w, String packageName) throws Exception {
		if (packageName != null) {
			w.append("package ");
			w.append(packageName);
			w.append(";\n\n");
		}
	}

	private static void appendImport(BufferedWriter w, String imp) throws Exception {
		w.append("import ");
		w.append(imp);
		w.append(";\n");
	}

	private static String constructType(ProtobufField curField, ProtobufMessage curMessage) {
		StringBuilder result = new StringBuilder();
		String javaType = curField.getFullyClarifiedJavaType();
		if (javaType == null) {
		}
		if (curField.getNature().equals("repeated") && !curField.getType().equals("bytes")) {
			result.append("java.util.List<");
		}
		result.append(javaType);
		if (curField.getNature().equals("repeated") && !curField.getType().equals("bytes")) {
			result.append(">");
		}
		return result.toString();
	}

	private static File createPackage(File parent, String packageName) {
		String[] paths = packageName.split("\\.");
		File curDirectory = parent;
		for (String curPath : paths) {
			File curDirPath = new File(curDirectory, curPath);
			if (!curDirPath.exists() || !curDirPath.isDirectory()) {
				curDirPath.mkdir();
			}
			curDirectory = curDirPath;
		}
		return curDirectory;
	}

}
