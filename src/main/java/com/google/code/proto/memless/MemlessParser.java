package com.google.code.proto.memless;

import java.util.ArrayList;
import java.util.List;

class MemlessParser {

	private String packageName;
	private String outerClassName;

	private List<ProtobufMessage> messages = new ArrayList<ProtobufMessage>();
	private List<ProtobufEnum> enums = new ArrayList<ProtobufEnum>();

	private int curIndex = 0;
	private String[] tokens;

	void process(String file) throws Exception {
		file = file.replaceAll(";", " ; ");
		file = file.replaceAll("\\[", " [ ");
		file = file.replaceAll("\\]", " ] ");
		file = file.replaceAll(",", " , ");
		file = file.replaceAll("=", " = ");
		file = file.replaceAll("\\{", " { ");
		file = file.replaceAll("\\}", " } ");
		file = file.replaceAll(" {2,}", " ");
		tokens = file.split("[ \n\r]");

		String curToken = null;
		while ((curToken = getNextIgnoreNewLine()) != null) {
			if (curToken.equals(Tokens.PACKAGE)) {
				packageName = getNextIgnoreNewLine();
				if (packageName == null || !Tokens.isIdentifier(packageName)) {
					throw new Exception("Invalid package definition. Expected package name");
				}
				consume(";");
				continue;
			}
			if (curToken.equals(Tokens.MESSAGE)) {
				String messageName = getNextNotEmpty();
				if (messageName == null || !Tokens.isIdentifier(messageName)) {
					throw new Exception("Invalid message name. Invalid symbols found");
				}
				ProtobufMessage curMessage = new ProtobufMessage();
				curMessage.setName(messageName);
				curMessage.setFullyClarifiedName(messageName);
				processInnerMessage(curMessage);
				messages.add(curMessage);
				continue;
			}
			if (curToken.equals(Tokens.ENUM_TOKEN)) {
				String enumName = getNextNotEmpty();
				if (enumName == null || !Tokens.isIdentifier(enumName)) {
					throw new Exception("Invalid enum name. Invalid symbols found");
				}
				ProtobufEnum curEnum = new ProtobufEnum();
				curEnum.setName(enumName);
				curEnum.setFullyClarifiedName(enumName);
				processInnerEnum(curEnum);
				enums.add(curEnum);
				continue;
			}
			if (curToken.equals(Tokens.OPTION)) {
				String optionType = getNextIgnoreNewLine();
				if (optionType != null && optionType.equals(Tokens.JAVA_OUTER_CLASSNAME)) {
					consume("=");
					outerClassName = getNextIgnoreNewLine();
					outerClassName = outerClassName.replaceAll("\"", "");
					continue;
				}
				System.out.println("Ignoring: " + optionType);
				continue;
			}
			System.out.println("Ignoring: " + curToken);
		}

		String strToAppend = null;
		if (packageName != null) {
			strToAppend = packageName;
			if (outerClassName != null) {
				strToAppend += "." + outerClassName;
			}
		}

		if (strToAppend != null) {
			for (ProtobufMessage curMessage : messages) {
				appendPackageName(curMessage, strToAppend);
			}
			for (ProtobufEnum curEnum : enums) {
				curEnum.setFullyClarifiedName(strToAppend + "." + curEnum.getFullyClarifiedName());
			}
		}

		enrichFieldsInMessage(messages, strToAppend);
	}

	private void appendPackageName(ProtobufMessage message, String name) {
		message.setFullyClarifiedName(name + "." + message.getFullyClarifiedName());
		System.out.println(message.getFullyClarifiedName());
		for (ProtobufMessage curMessage : message.getNestedMessages()) {
			appendPackageName(curMessage, getParent(message.getFullyClarifiedName()));
		}

		for (ProtobufEnum curEnum : message.getEnums()) {
			curEnum.setFullyClarifiedName(name + "." + curEnum.getFullyClarifiedName());
		}
	}

	private void processInnerMessage(ProtobufMessage parentMessage) throws Exception {
		String curToken = null;
		int intendtion = 0;
		while ((curToken = getNextIgnoreNewLine()) != null) {
			if (curToken.equals(Tokens.BRACE_START)) {
				intendtion++;
				continue;
			}
			if (curToken.equals(Tokens.BRACE_END)) {
				intendtion--;
				if (intendtion == 0) {
					return;
				}
				continue;
			}
			if (curToken.equals(Tokens.MESSAGE)) {
				String messageName = getNextNotEmpty();
				if (messageName == null || !Tokens.isIdentifier(messageName)) {
					throw new Exception("Invalid message name. Invalid symbols found");
				}
				ProtobufMessage curMessage = new ProtobufMessage();
				curMessage.setName(messageName);
				curMessage.setFullyClarifiedName(parentMessage.getFullyClarifiedName() + "." + messageName);
				processInnerMessage(curMessage);
				parentMessage.addNestedMessage(curMessage);
				continue;
			}
			if (curToken.equals(Tokens.OPTIONAL_FIELD)) {
				String type = getNextNotEmpty();
				if (type == null || !Tokens.isValidFieldType(type)) {
					throw new Exception("Invalid field type found: " + type);
				}
				if (type.equals("group")) {
					System.out.println("groups are deprecated and not supported.");
					consumeTillMessage(Tokens.BRACE_END);
					continue;
				}
				ProtobufField curField = new ProtobufField();
				curField.setType(type);
				curField.setNature("optional");
				String name = getNextNotEmpty();
				if (name == null || !Tokens.isIdentifier(name)) {
					throw new Exception("Invalid field name: " + name);
				}
				curField.setName(name);
				consume("=");
				long tag = consumeLong();
				if (!Tokens.isValidTag(tag)) {
					throw new Exception("Invalid tag detected: " + tag);
				}
				curField.setTag(tag);
				parentMessage.addField(curField);
				String brace = lookAhead(1);
				if (brace.equals(Tokens.SQUARE_BRACE_START)) {
					processInnerSquareBraces(curField);
				}
				consume(";");
				continue;
			}
			if (curToken.equals(Tokens.REPEATED_FIELD)) {
				String type = getNextNotEmpty();
				if (type == null || !Tokens.isValidFieldType(type)) {
					throw new Exception("Invalid field type found: " + type);
				}
				if (type.equals("group")) {
					System.out.println("groups are deprecated and not supported.");
					consumeTillMessage(Tokens.BRACE_END);
					continue;
				}
				ProtobufField curField = new ProtobufField();
				curField.setType(type);
				curField.setNature("repeated");
				String name = getNextNotEmpty();
				if (name == null || !Tokens.isIdentifier(name)) {
					throw new Exception("Invalid field name: " + name);
				}
				curField.setName(name);
				consume("=");
				long tag = consumeLong();
				if (!Tokens.isValidTag(tag)) {
					throw new Exception("Invalid tag detected: " + tag);
				}
				curField.setTag(tag);
				parentMessage.addField(curField);
				String brace = lookAhead(1);
				if (brace.equals(Tokens.SQUARE_BRACE_START)) {
					processInnerSquareBraces(curField);
				}
				consume(";");
				continue;
			}
			if (curToken.equals(Tokens.REQUIRED_FIELD)) {
				String type = getNextNotEmpty();
				if (type == null || !Tokens.isValidFieldType(type)) {
					throw new Exception("Invalid field type found: " + type);
				}
				if (type.equals("group")) {
					System.out.println("groups are deprecated and not supported.");
					consumeTillMessage(Tokens.BRACE_END);
					continue;
				}
				ProtobufField curField = new ProtobufField();
				curField.setType(type);
				curField.setNature("required");
				String name = getNextNotEmpty();
				if (name == null || !Tokens.isIdentifier(name)) {
					throw new Exception("Invalid field name: " + name);
				}
				curField.setName(name);
				consume("=");
				long tag = consumeLong();
				if (!Tokens.isValidTag(tag)) {
					throw new Exception("Invalid tag detected: " + tag);
				}
				curField.setTag(tag);
				parentMessage.addField(curField);
				String brace = lookAhead(1);
				if (brace.equals(Tokens.SQUARE_BRACE_START)) {
					processInnerSquareBraces(curField);
				}
				consume(";");
				continue;
			}
			if (curToken.equals(Tokens.ENUM_TOKEN)) {
				String enumName = getNextNotEmpty();
				if (enumName == null || !Tokens.isIdentifier(enumName)) {
					throw new Exception("Invalid enum name. Invalid symbols found");
				}
				ProtobufEnum curEnum = new ProtobufEnum();
				curEnum.setName(enumName);
				curEnum.setFullyClarifiedName(parentMessage.getFullyClarifiedName() + "." + enumName);
				processInnerEnum(curEnum);
				parentMessage.addEnum(curEnum);
				continue;
			}
			System.out.println("Ignoring: " + curToken);
		}
		throw new Exception("Incomplete message: " + parentMessage);
	}

	private void processInnerEnum(ProtobufEnum pEnum) throws Exception {
		String curToken = null;
		int intendtion = 0;
		while ((curToken = getNextIgnoreNewLine()) != null) {
			if (curToken.equals(Tokens.BRACE_START)) {
				intendtion++;
				continue;
			}
			if (curToken.equals(Tokens.BRACE_END)) {
				intendtion--;
				if (intendtion == 0) {
					return;
				}
				continue;
			}
			if (!Tokens.isIdentifier(curToken)) {
				throw new Exception("Invalid enum name: " + curToken);
			}
			EnumValue curValue = new EnumValue();
			curValue.setName(curToken);
			consume("=");
			long tag = consumeLong();
			if (!Tokens.isValidTag(tag)) {
				throw new Exception("Invalid tag detected: " + tag);
			}
			curValue.setId(tag);
			pEnum.addValue(curValue);
			consume(";");
		}
		throw new Exception("incomplete enum: " + pEnum);
	}

	private void processInnerSquareBraces(ProtobufField fields) throws Exception {
		String curToken = null;
		int intendtion = 0;
		while ((curToken = getNextIgnoreNewLine()) != null) {
			if (curToken.equals(Tokens.SQUARE_BRACE_START)) {
				intendtion++;
				continue;
			}
			if (curToken.equals(Tokens.SQUARE_BRACE_END)) {
				intendtion--;
				if (intendtion == 0) {
					return;
				}
				continue;
			}
			if (curToken.equals(",")) {
				continue;
			}
			if (curToken.equals(Tokens.DEPRECATED)) {
				consume("=");
				String value = getNextIgnoreNewLine();
				if (value != null && value.equals("true")) {
					fields.setDeprecated(true);
				}
				continue;
			}
			if (curToken.equals(Tokens.PACKED)) {
				consume("=");
				String value = getNextIgnoreNewLine();
				if (value != null && value.equals("true")) {
					fields.setPacked(true);
				}
				continue;
			}
			if (curToken.equals(Tokens.DEFAULT)) {
				consume("=");
				String value = getNextIgnoreNewLine();
				fields.setDefaults(value);
				continue;
			}
			System.out.println("Ignoring: " + curToken);
		}
		throw new Exception("Incomplete square braces");
	}

	String getPackageName() {
		return packageName;
	}

	List<ProtobufMessage> getMessages() {
		return messages;
	}

	List<ProtobufEnum> getEnums() {
		return enums;
	}

	String getOuterClassName() {
		return outerClassName;
	}

	private void consume(String expected) throws Exception {
		String token = getNextNotEmpty();
		if (token == null || !token.equals(expected)) {
			throw new Exception("Invalid token found: " + token + " Expected: " + expected);
		}
	}

	private long consumeLong() throws Exception {
		String token = getNextNotEmpty();
		if (token == null) {
			throw new Exception("Token not found");
		}
		try {
			return Long.valueOf(token);
		} catch (Exception e) {
			throw new Exception("Expected integer. Received: " + token, e);
		}
	}

	private String getNextIgnoreNewLine() {
		String curToken = null;
		do {
			curToken = getNext();
		} while (curToken != null && (curToken.equals("\n") || curToken.equals("\r") || curToken.equals("\n\r") || curToken.length() == 0));
		return curToken;
	}

	private String consumeTillMessage(String str) {
		String curToken = null;
		do {
			curToken = getNextIgnoreNewLine();
		} while (curToken != null && !curToken.equals(str));
		return curToken;
	}

	private String getNext() {
		if (curIndex == tokens.length) {
			return null;
		}
		String result = tokens[curIndex];
		curIndex += 1;
		return result;
	}

	private String lookAhead(int number) {
		if (curIndex == tokens.length) {
			return null;
		}
		String result = tokens[number + curIndex - 1];
		return result;
	}

	private String getNextNotEmpty() {
		String curToken = null;
		do {
			curToken = getNext();
		} while (curToken != null && curToken.length() == 0);
		return curToken;
	}

	private void enrichFieldsInMessage(List<ProtobufMessage> messages, String externalPackage) {
		for (ProtobufMessage curMessage : messages) {
			if (curMessage.getFields() != null) {
				for (ProtobufField curField : curMessage.getFields()) {
					enrichField(curField, curMessage.getFullyClarifiedName(), externalPackage);
				}
			}
			if (curMessage.getNestedMessages() != null) {
				enrichFieldsInMessage(curMessage.getNestedMessages(), externalPackage);
			}
		}
	}

	private void enrichField(ProtobufField curField, String prefix, String externalPackage) {
		curField.setBeanName(convertNameToJavabean(curField.getName()));
		curField.setStreamBeanType(convertNameToJavabean(curField.getType()));

		String javaType = getJavaType(curField);
		if (javaType != null) {
			curField.setFullyClarifiedJavaType(javaType);
			return;
		}

		curField.setComplexType(true);

		String type = null;
		if (prefix != null) {
			type = prefix + "." + curField.getType();
		} else {
			type = curField.getType();
		}

		String complexFieldType = getFullyClarifiedNameBySimpleName(messages, type);
		if (complexFieldType != null) {
			curField.setFullyClarifiedJavaType(complexFieldType);
			if (isEnum(messages, type)) {
				curField.setEnumType(true);
			}
			return;
		}
		complexFieldType = getFullyClarifiedNameBySimpleName(messages, curField.getType());
		if (complexFieldType != null) {
			curField.setFullyClarifiedJavaType(complexFieldType);
			if (isEnum(messages, curField.getType())) {
				curField.setEnumType(true);
			}
			return;
		}
		if (externalPackage != null) {
			complexFieldType = getFullyClarifiedNameBySimpleName(messages, externalPackage + "." + curField.getType());
			if (complexFieldType != null) {
				curField.setFullyClarifiedJavaType(complexFieldType);
				if (isEnum(messages, externalPackage + "." + curField.getType())) {
					curField.setEnumType(true);
				}
				return;
			}
		}

		complexFieldType = getFullyClarifiedNameBySimpleNameFromEnum(enums, type);
		if (complexFieldType != null) {
			curField.setFullyClarifiedJavaType(complexFieldType);
			curField.setEnumType(true);
			return;
		}
		complexFieldType = getFullyClarifiedNameBySimpleNameFromEnum(enums, curField.getType());
		if (complexFieldType != null) {
			curField.setFullyClarifiedJavaType(complexFieldType);
			curField.setEnumType(true);
			return;
		}
		if (externalPackage != null) {
			complexFieldType = getFullyClarifiedNameBySimpleNameFromEnum(enums, externalPackage + "." + curField.getType());
			if (complexFieldType != null) {
				curField.setFullyClarifiedJavaType(complexFieldType);
				curField.setEnumType(true);
				return;
			}
		}

		curField.setFullyClarifiedJavaType(curField.getType());
		System.out.println("unknown field type: " + type);
	}

	private static boolean isEnum(List<ProtobufMessage> messages, String type) {
		for (ProtobufMessage curMessage : messages) {
			if (curMessage.getNestedMessages() != null) {
				boolean result = isEnum(curMessage.getNestedMessages(), type);
				if (result) {
					return result;
				}
			}
			if (curMessage.getEnums() != null) {
				for (ProtobufEnum curEnum : curMessage.getEnums()) {
					if (curEnum.getName().equals(type) || curEnum.getFullyClarifiedName().equals(type)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static String getParent(String fullyClarifiedName) {
		int index = fullyClarifiedName.lastIndexOf(".");
		if (index == -1) {
			return null;
		}
		return fullyClarifiedName.substring(0, index);
	}

	private static String getFullyClarifiedNameBySimpleName(List<ProtobufMessage> messages, String name) {
		for (ProtobufMessage curMessage : messages) {
			if (curMessage.getName().equals(name) || curMessage.getFullyClarifiedName().equals(name)) {
				return curMessage.getFullyClarifiedName();
			}
			if (curMessage.getNestedMessages() != null) {
				String result = getFullyClarifiedNameBySimpleName(curMessage.getNestedMessages(), name);
				if (result != null) {
					return result;
				}
			}
			if (curMessage.getEnums() != null) {
				String result = getFullyClarifiedNameBySimpleNameFromEnum(curMessage.getEnums(), name);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	private static String getFullyClarifiedNameBySimpleNameFromEnum(List<ProtobufEnum> messages, String name) {
		for (ProtobufEnum curEnum : messages) {
			if (curEnum.getName().equals(name) || curEnum.getFullyClarifiedName().equals(name)) {
				return curEnum.getFullyClarifiedName();
			}
		}
		return null;
	}

	private static String convertNameToJavabean(String str) {
		StringBuilder result = new StringBuilder();
		result.append(Character.toUpperCase(str.charAt(0)));
		if (str.length() > 1) {
			result.append(str.substring(1));
		}
		return result.toString();
	}

	private static String getJavaType(ProtobufField curField) {
		if (curField.getType().equals("int32") || curField.getType().equals("uint32") || curField.getType().equals("sint32") || curField.getType().equals("fixed32") || curField.getType().equals("sfixed32")) {
			if (curField.getNature().equals("repeated")) {
				return "Integer";
			} else {
				return "int";
			}
		}
		if (curField.getType().equals("int64") || curField.getType().equals("uint64") || curField.getType().equals("sint64") || curField.getType().equals("fixed64") || curField.getType().equals("sfixed64")) {
			if (curField.getNature().equals("repeated")) {
				return "Long";
			} else {
				return "long";
			}
		}
		if (curField.getType().equals("double")) {
			if (curField.getNature().equals("repeated")) {
				return "Double";
			} else {
				return "double";
			}
		}
		if (curField.getType().equals("bool")) {
			if (curField.getNature().equals("repeated")) {
				return "Boolean";
			} else {
				return "boolean";
			}
		}
		if (curField.getType().equals("string")) {
			return "String";
		}
		if (curField.getType().equals("bytes")) {
			return "byte[]";
		}
		if (curField.getType().equals("float")) {
			if (curField.getNature().equals("repeated")) {
				return "Float";
			} else {
				return "float";
			}
		}
		return null;
	}

}
