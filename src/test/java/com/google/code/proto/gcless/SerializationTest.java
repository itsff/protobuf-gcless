package com.google.code.proto.gcless;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import protobuf_gcless_unittest.ForeignMessageImpl;
import protobuf_gcless_unittest.TestAllTypesImpl;
import protobuf_gcless_unittest.UnittestProto.ForeignMessage;
import protobuf_gcless_unittest.UnittestProto.TestAllTypes;
import protobuf_gcless_unittest.UnittestProto.TestAllTypesSerializer;

public class SerializationTest {
	
	@Test
	public void testDefaultSerializationOptimizedStreamedDeserialization() throws Exception {
		TestAllTypes message = createSampleMessage();
		byte[] data = TestAllTypesSerializer.serialize(message);
		
		protobuf_unittest.UnittestProto.TestAllTypes result = protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(data);
		
		byte[] defaultSerializationData = result.toByteArray();
		
		TestAllTypes optimizedResult = TestAllTypesSerializer.parseFrom(new MessageFactoryImpl(), new ByteArrayInputStream(defaultSerializationData));
		assertDeepEquals(result, optimizedResult);
		assertEquals(2, optimizedResult.getRepeated_foreign_message().size());
		assertEquals(1, optimizedResult.getRepeated_foreign_message().get(0).getC());
		assertEquals(2, optimizedResult.getRepeated_foreign_message().get(1).getC());
		assertEquals(message.getDefault_string(), optimizedResult.getDefault_string());
	}
	
	@Test 
	public void testDefaultSerializationOptimizedDeserialization() throws Exception {
		TestAllTypes message = createSampleMessage();
		byte[] data = TestAllTypesSerializer.serialize(message);
		
		protobuf_unittest.UnittestProto.TestAllTypes result = protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(data);
		
		byte[] defaultSerializationData = result.toByteArray();
		
		TestAllTypes optimizedResult = TestAllTypesSerializer.parseFrom(new MessageFactoryImpl(), defaultSerializationData);
		assertDeepEquals(result, optimizedResult);
		assertEquals(2, optimizedResult.getRepeated_foreign_message().size());
		assertEquals(1, optimizedResult.getRepeated_foreign_message().get(0).getC());
		assertEquals(2, optimizedResult.getRepeated_foreign_message().get(1).getC());
	}
	
	@Test
	public void testOptimizedSerializationDeserialization() throws Exception {
		TestAllTypes message = createSampleMessage();

		byte[] data = TestAllTypesSerializer.serialize(message);

		TestAllTypes result = TestAllTypesSerializer.parseFrom(new MessageFactoryImpl(), data);
		assertNotNull(result);
		assertDeepEquals(message, result);
		//TODO move to deep equals
		assertEquals(2, result.getRepeated_foreign_message().size());
		assertEquals(1, result.getRepeated_foreign_message().get(0).getC());
		assertEquals(2, result.getRepeated_foreign_message().get(1).getC());
	}
	
	@Test
	public void testOptimizedSerializationToStreamedDeserialization() throws Exception {
		TestAllTypes message = createSampleMessage();

		byte[] data = TestAllTypesSerializer.serialize(message);
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		TestAllTypes result = TestAllTypesSerializer.parseFrom(new MessageFactoryImpl(), bais);
		assertNotNull(result);
		assertDeepEquals(message, result);
		//TODO move to deep equals
		assertEquals(2, result.getRepeated_foreign_message().size());
		assertEquals(1, result.getRepeated_foreign_message().get(0).getC());
		assertEquals(2, result.getRepeated_foreign_message().get(1).getC());
	}
	
	@Test
	public void testOptimizedSerializationDefaultDeserialization() throws Exception {

		TestAllTypes message = createSampleMessage();

		byte[] data = TestAllTypesSerializer.serialize(message);

		protobuf_unittest.UnittestProto.TestAllTypes result = protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(data);
		assertNotNull(result);
		assertDeepEquals(result, message);
		assertTrue(result.getUnknownFields().asMap().isEmpty());
	}

	@Test
	public void testOptimizedSerializeToStreamDefaultDeserialization() throws Exception {

		TestAllTypes message = createSampleMessage();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TestAllTypesSerializer.serialize(message, baos);
		
		protobuf_unittest.UnittestProto.TestAllTypes result = protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(baos.toByteArray());
		assertNotNull(result);
		assertDeepEquals(result, message);
		//TODO move to deep equals		
		assertEquals(2, result.getRepeatedForeignMessageCount());
		assertEquals(1, result.getRepeatedForeignMessage(0).getC());
		assertEquals(2, result.getRepeatedForeignMessage(1).getC());		
		assertTrue(result.getUnknownFields().asMap().isEmpty());
	}

	public static void main(String[] args) throws Exception {
		
		MessageFactoryImpl factory = new MessageFactoryImpl();
		
		long times = 1000000;

		long start;
		
		TestAllTypes message = createSampleMessage();
		byte[] data = TestAllTypesSerializer.serialize(message);

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			TestAllTypesSerializer.serialize(message);
		}
		System.out.println(" * Optimized version(serialize): " + (System.currentTimeMillis() - start));
		protobuf_unittest.UnittestProto.TestAllTypes result = protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(data);

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			result.toByteArray();
		}
		System.out.println(" * Default version(serialize): " + (System.currentTimeMillis() - start));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length + 10); //little trick to remove stream io issues and measure pure serialization power.
		
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			TestAllTypesSerializer.serialize(message, baos);
			baos.reset();
		}
		System.out.println(" * Optimized streamed version(serialize): " + (System.currentTimeMillis() - start));
		

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			result.writeTo(baos);
			baos.reset();
		}
		System.out.println(" * Default streamed version(serialize): " + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			TestAllTypesSerializer.parseFrom(factory, data);
		}
		System.out.println(" * Optimized version(de-serialize): " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(data);
		}
		System.out.println(" * Default version(de-serialize): " + (System.currentTimeMillis() - start));
		
		ByteArrayInputStream bais = new ByteArrayInputStream(data);

		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			TestAllTypesSerializer.parseFrom(factory, bais);
			bais.reset();
		}
		System.out.println(" * Optimized streamed version(de-serialize): " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			protobuf_unittest.UnittestProto.TestAllTypes.parseFrom(bais);
			bais.reset();
		}
		System.out.println(" * Default streamed version(de-serialize): " + (System.currentTimeMillis() - start));
	}
	
	private static void assertDeepEquals(protobuf_unittest.UnittestProto.TestAllTypes defaultImpl, TestAllTypes optimized) {
		assertEquals(defaultImpl.getOptionalInt32(), optimized.getOptional_int32());
		assertEquals(defaultImpl.getOptionalInt64(), optimized.getOptional_int64());
		assertEquals(defaultImpl.getOptionalUint32(), optimized.getOptional_uint32());
		assertEquals(defaultImpl.getOptionalUint64(), optimized.getOptional_uint64());
		assertEquals(defaultImpl.getOptionalSint32(), optimized.getOptional_sint32());
		assertEquals(defaultImpl.getOptionalSint64(), optimized.getOptional_sint64());
		assertEquals(defaultImpl.getOptionalFixed32(), optimized.getOptional_fixed32());
		assertEquals(defaultImpl.getOptionalFixed64(), optimized.getOptional_fixed64());
		assertEquals(defaultImpl.getOptionalSfixed32(), optimized.getOptional_sfixed32());
		assertEquals(defaultImpl.getOptionalSfixed64(), optimized.getOptional_sfixed64());
		assertEquals(defaultImpl.getOptionalFloat(), optimized.getOptional_float());
		assertEquals(defaultImpl.getOptionalDouble(), optimized.getOptional_double());
		assertEquals(defaultImpl.getOptionalBool(), optimized.getOptional_bool());
		assertEquals(defaultImpl.getOptionalString(), optimized.getOptional_string());
		assertTrue(Arrays.equals(defaultImpl.getOptionalBytes().toByteArray(), optimized.getOptional_bytes()));
		assertEquals(defaultImpl.getOptionalStringPiece(), optimized.getOptional_string_piece());
		assertEquals(defaultImpl.getOptionalCord(), optimized.getOptional_cord());
		assertTrue(Arrays.equals(defaultImpl.getRepeatedInt32List().toArray(), optimized.getRepeated_int32().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedInt64List().toArray(), optimized.getRepeated_int64().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedUint32List().toArray(), optimized.getRepeated_uint32().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedUint64List().toArray(), optimized.getRepeated_uint64().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedSint32List().toArray(), optimized.getRepeated_sint32().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedSint64List().toArray(), optimized.getRepeated_sint64().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedFixed32List().toArray(), optimized.getRepeated_fixed32().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedFixed64List().toArray(), optimized.getRepeated_fixed64().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedSfixed32List().toArray(), optimized.getRepeated_sfixed32().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedSfixed64List().toArray(), optimized.getRepeated_sfixed64().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedFloatList().toArray(), optimized.getRepeated_float().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedDoubleList().toArray(), optimized.getRepeated_double().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedBoolList().toArray(), optimized.getRepeated_bool().toArray()));
		assertTrue(Arrays.equals(defaultImpl.getRepeatedStringList().toArray(), optimized.getRepeated_string().toArray()));
	}
	
	private static void assertDeepEquals(TestAllTypes original, TestAllTypes result) {
		assertEquals(original.getOptional_int32(), result.getOptional_int32());
		assertEquals(original.getOptional_int64(), result.getOptional_int64());
		assertEquals(original.getOptional_uint32(), result.getOptional_uint32());
		assertEquals(original.getOptional_uint64(), result.getOptional_uint64());
		assertEquals(original.getOptional_sint32(), result.getOptional_sint32());
		assertEquals(original.getOptional_sint64(), result.getOptional_sint64());
		assertEquals(original.getOptional_fixed32(), result.getOptional_fixed32());
		assertEquals(original.getOptional_fixed64(), result.getOptional_fixed64());
		assertEquals(original.getOptional_sfixed32(), result.getOptional_sfixed32());
		assertEquals(original.getOptional_sfixed64(), result.getOptional_sfixed64());
		assertEquals(original.getOptional_float(), result.getOptional_float());
		assertEquals(original.getOptional_double(), result.getOptional_double());
		assertEquals(original.getOptional_bool(), result.getOptional_bool());
		assertEquals(original.getOptional_string(), result.getOptional_string());
		assertTrue(Arrays.equals(original.getOptional_bytes(), result.getOptional_bytes()));
		assertEquals(original.getOptional_string_piece(), result.getOptional_string_piece());
		assertEquals(original.getOptional_cord(), result.getOptional_cord());
		assertTrue(Arrays.equals(original.getRepeated_int32().toArray(), result.getRepeated_int32().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_int64().toArray(), result.getRepeated_int64().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_uint32().toArray(), result.getRepeated_uint32().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_uint64().toArray(), result.getRepeated_uint64().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_sint32().toArray(), result.getRepeated_sint32().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_sint64().toArray(), result.getRepeated_sint64().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_fixed32().toArray(), result.getRepeated_fixed32().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_fixed64().toArray(), result.getRepeated_fixed64().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_sfixed32().toArray(), result.getRepeated_sfixed32().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_sfixed64().toArray(), result.getRepeated_sfixed64().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_float().toArray(), result.getRepeated_float().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_double().toArray(), result.getRepeated_double().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_bool().toArray(), result.getRepeated_bool().toArray()));
		assertTrue(Arrays.equals(original.getRepeated_string().toArray(), result.getRepeated_string().toArray()));
	}

	private static TestAllTypes createSampleMessage() {
		TestAllTypes message = new TestAllTypesImpl();
		message.setOptional_int32(1);
		message.setOptional_int64(1l);
		message.setOptional_uint32(1);
		message.setOptional_uint64(1l);
		message.setOptional_sint32(1);
		message.setOptional_sint64(1l);
		message.setOptional_fixed32(1);
		message.setOptional_fixed64(1l);
		message.setOptional_sfixed32(1);
		message.setOptional_sfixed64(1l);
		message.setOptional_float(1.0f);
		message.setOptional_double(1.1);
		message.setOptional_bool(true);
		message.setOptional_string("123");
		message.setOptional_bytes(new byte[] {(byte) 1, (byte) 2});
		message.setOptional_string_piece("123");
		message.setOptional_cord("123");
		List<Integer> valuesRepeated_int32 = new ArrayList<Integer>();
		valuesRepeated_int32.add(1);
		valuesRepeated_int32.add(1);
		message.setRepeated_int32(valuesRepeated_int32);
		List<Long> valuesRepeated_int64 = new ArrayList<Long>();
		valuesRepeated_int64.add(1l);
		valuesRepeated_int64.add(1l);
		message.setRepeated_int64(valuesRepeated_int64);
		List<Integer> valuesRepeated_uint32 = new ArrayList<Integer>();
		valuesRepeated_uint32.add(1);
		valuesRepeated_uint32.add(1);
		message.setRepeated_uint32(valuesRepeated_uint32);
		List<Long> valuesRepeated_uint64 = new ArrayList<Long>();
		valuesRepeated_uint64.add(1l);
		valuesRepeated_uint64.add(1l);
		message.setRepeated_uint64(valuesRepeated_uint64);
		List<Integer> valuesRepeated_sint32 = new ArrayList<Integer>();
		valuesRepeated_sint32.add(1);
		valuesRepeated_sint32.add(1);
		message.setRepeated_sint32(valuesRepeated_sint32);
		List<Long> valuesRepeated_sint64 = new ArrayList<Long>();
		valuesRepeated_sint64.add(1l);
		valuesRepeated_sint64.add(1l);
		message.setRepeated_sint64(valuesRepeated_sint64);
		List<Integer> valuesRepeated_fixed32 = new ArrayList<Integer>();
		valuesRepeated_fixed32.add(1);
		valuesRepeated_fixed32.add(1);
		message.setRepeated_fixed32(valuesRepeated_fixed32);
		List<Long> valuesRepeated_fixed64 = new ArrayList<Long>();
		valuesRepeated_fixed64.add(1l);
		valuesRepeated_fixed64.add(1l);
		message.setRepeated_fixed64(valuesRepeated_fixed64);
		List<Integer> valuesRepeated_sfixed32 = new ArrayList<Integer>();
		valuesRepeated_sfixed32.add(1);
		valuesRepeated_sfixed32.add(1);
		message.setRepeated_sfixed32(valuesRepeated_sfixed32);
		List<Long> valuesRepeated_sfixed64 = new ArrayList<Long>();
		valuesRepeated_sfixed64.add(1l);
		valuesRepeated_sfixed64.add(1l);
		message.setRepeated_sfixed64(valuesRepeated_sfixed64);
		List<Float> valuesRepeated_float = new ArrayList<Float>();
		valuesRepeated_float.add(1.0f);
		valuesRepeated_float.add(1.0f);
		message.setRepeated_float(valuesRepeated_float);
		List<Double> valuesRepeated_double = new ArrayList<Double>();
		valuesRepeated_double.add(1.1);
		valuesRepeated_double.add(1.1);
		message.setRepeated_double(valuesRepeated_double);
		List<Boolean> valuesRepeated_bool = new ArrayList<Boolean>();
		valuesRepeated_bool.add(true);
		valuesRepeated_bool.add(true);
		message.setRepeated_bool(valuesRepeated_bool);
		List<String> valuesRepeated_string = new ArrayList<String>();
		valuesRepeated_string.add("123");
		valuesRepeated_string.add("456");
		message.setRepeated_string(valuesRepeated_string);
		message.setRepeated_bytes(new byte[] {(byte) 1, (byte) 2});
		List<protobuf_gcless_unittest.UnittestProto.TestAllTypes.NestedMessage> valuesRepeated_nested_message = new ArrayList<protobuf_gcless_unittest.UnittestProto.TestAllTypes.NestedMessage>();
		message.setRepeated_nested_message(valuesRepeated_nested_message);
		List<protobuf_gcless_unittest.UnittestProto.ForeignMessage> valuesRepeated_foreign_message = new ArrayList<protobuf_gcless_unittest.UnittestProto.ForeignMessage>();
		ForeignMessage foreignMessage1 = new ForeignMessageImpl();
		foreignMessage1.setC(1);
		ForeignMessage foreignMessage2 = new ForeignMessageImpl();
		foreignMessage2.setC(2);
		valuesRepeated_foreign_message.add(foreignMessage1);
		valuesRepeated_foreign_message.add(foreignMessage2);
		message.setRepeated_foreign_message(valuesRepeated_foreign_message);
		List<protobuf_gcless_import.ImportMessage> valuesRepeated_import_message = new ArrayList<protobuf_gcless_import.ImportMessage>();
		message.setRepeated_import_message(valuesRepeated_import_message);
		List<protobuf_gcless_unittest.UnittestProto.TestAllTypes.NestedEnum> valuesRepeated_nested_enum = new ArrayList<protobuf_gcless_unittest.UnittestProto.TestAllTypes.NestedEnum>();
		message.setRepeated_nested_enum(valuesRepeated_nested_enum);
		List<protobuf_gcless_unittest.UnittestProto.ForeignEnum> valuesRepeated_foreign_enum = new ArrayList<protobuf_gcless_unittest.UnittestProto.ForeignEnum>();
		message.setRepeated_foreign_enum(valuesRepeated_foreign_enum);
		List<protobuf_gcless_import.ImportEnum> valuesRepeated_import_enum = new ArrayList<protobuf_gcless_import.ImportEnum>();
		message.setRepeated_import_enum(valuesRepeated_import_enum);
		List<String> valuesRepeated_string_piece = new ArrayList<String>();
		valuesRepeated_string_piece.add("123");
		valuesRepeated_string_piece.add("123");
		message.setRepeated_string_piece(valuesRepeated_string_piece);
		List<String> valuesRepeated_cord = new ArrayList<String>();
		valuesRepeated_cord.add("123");
		valuesRepeated_cord.add("123");
		message.setRepeated_cord(valuesRepeated_cord);
		message.setDefault_int32(1);
		message.setDefault_int64(1l);
		message.setDefault_uint32(1);
		message.setDefault_uint64(1l);
		message.setDefault_sint32(1);
		message.setDefault_sint64(1l);
		message.setDefault_fixed32(1);
		message.setDefault_fixed64(1l);
		message.setDefault_sfixed32(1);
		message.setDefault_sfixed64(1l);
		message.setDefault_float(1.0f);
		message.setDefault_double(1.1);
		message.setDefault_bool(true);
		message.setDefault_string("123");
		message.setDefault_bytes(new byte[] {(byte) 1, (byte) 2});
		message.setDefault_string_piece("123");
		message.setDefault_cord("123");
		return message;
	}

}

