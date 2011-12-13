package com.google.code.proto.memless;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import protobuf_memless_unittest.OneStringImpl;
import protobuf_memless_unittest.TestAllTypesImpl;
import protobuf_memless_unittest.UnittestProto.OneString;
import protobuf_memless_unittest.UnittestProto.OneStringSerializer;
import protobuf_memless_unittest.UnittestProto.TestAllTypes;
import protobuf_memless_unittest.UnittestProto.TestAllTypesSerializer;

public class SerializationTest {

	@Test
	public void testOneStringSer() {
		OneString message = new OneStringImpl();
		message.setData("1");
		byte[] data = OneStringSerializer.serialize(message);
		System.out.println("here");
	}
	
	@Test
	public void testSerializationDeserialization() {
		
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
		message.setOptional_bytes(new byte[]{(byte)1, (byte)2});
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
		valuesRepeated_string.add("123");
		message.setRepeated_string(valuesRepeated_string);
		message.setRepeated_bytes(new byte[]{(byte)1, (byte)2});
		List<protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedMessage> valuesRepeated_nested_message = new ArrayList<protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedMessage>();
		message.setRepeated_nested_message(valuesRepeated_nested_message);
		List<protobuf_memless_unittest.UnittestProto.ForeignMessage> valuesRepeated_foreign_message = new ArrayList<protobuf_memless_unittest.UnittestProto.ForeignMessage>();
		message.setRepeated_foreign_message(valuesRepeated_foreign_message);
		List<protobuf_memless_import.ImportMessage> valuesRepeated_import_message = new ArrayList<protobuf_memless_import.ImportMessage>();
		message.setRepeated_import_message(valuesRepeated_import_message);
		List<protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedEnum> valuesRepeated_nested_enum = new ArrayList<protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedEnum>();
		message.setRepeated_nested_enum(valuesRepeated_nested_enum);
		List<protobuf_memless_unittest.UnittestProto.ForeignEnum> valuesRepeated_foreign_enum = new ArrayList<protobuf_memless_unittest.UnittestProto.ForeignEnum>();
		message.setRepeated_foreign_enum(valuesRepeated_foreign_enum);
		List<protobuf_memless_import.ImportEnum> valuesRepeated_import_enum = new ArrayList<protobuf_memless_import.ImportEnum>();
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
		message.setDefault_bytes(new byte[]{(byte)1, (byte)2});
		message.setDefault_string_piece("123");
		message.setDefault_cord("123");
		
		byte[] data = TestAllTypesSerializer.serialize(message);
	}
	
	
}
