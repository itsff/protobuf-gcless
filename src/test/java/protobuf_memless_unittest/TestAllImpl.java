package protobuf_memless_unittest;

import java.util.List;

import protobuf_memless_import.ImportEnum;
import protobuf_memless_import.ImportMessage;
import protobuf_memless_unittest.UnittestProto.ForeignEnum;
import protobuf_memless_unittest.UnittestProto.ForeignMessage;
import protobuf_memless_unittest.UnittestProto.TestAllTypes;

public class TestAllImpl implements TestAllTypes {

	private int optionalInt32;
	private boolean hasOptionnalInt32;
	
	public boolean hasOptional_int32() {
		return hasOptionnalInt32;
	}

	public int getOptional_int32() {
		return optionalInt32;
	}

	public void setOptional_int32(int optional_int32) {
		this.optionalInt32 = optional_int32;
		this.hasOptionnalInt32 = true;
	}
	
	private long optionalInt64;
	private boolean hasOptionalInt64;

	public boolean hasOptional_int64() {
		return hasOptionalInt64;
	}

	public long getOptional_int64() {
		return optionalInt64;
	}

	public void setOptional_int64(long optional_int64) {
		this.optionalInt64 = optional_int64;
		this.hasOptionalInt64 = true;
	}
	
	private int optionalUInt32;
	private boolean hasOptionalUInt32;

	public boolean hasOptional_uint32() {
		return hasOptionalUInt32;
	}

	public int getOptional_uint32() {
		return optionalUInt32;
	}

	public void setOptional_uint32(int optional_uint32) {
		this.optionalUInt32 = optional_uint32;
		this.hasOptionalUInt32 = true;
	}
	
	private long optionalUInt64;
	private boolean hasOptionalUInt64;

	public boolean hasOptional_uint64() {
		return hasOptionalUInt64;
	}

	public long getOptional_uint64() {
		return optionalUInt64;
	}

	public void setOptional_uint64(long optional_uint64) {
		this.optionalUInt64 = optional_uint64;
		this.hasOptionalUInt64 = true;
	}
	
	private int optionalSInt32;
	private boolean hasOptionalSInt32;

	public boolean hasOptional_sint32() {
		return hasOptionalSInt32;
	}

	public int getOptional_sint32() {
		return optionalSInt32;
	}

	public void setOptional_sint32(int optional_sint32) {
		this.optionalSInt32 = optional_sint32;
		this.hasOptionalSInt32 = true;
	}
	
	private long optionalSInt64;
	private boolean hasOptionalSInt64;

	public boolean hasOptional_sint64() {
		return hasOptionalSInt64;
	}

	public long getOptional_sint64() {
		return optionalSInt64;
	}

	public void setOptional_sint64(long optional_sint64) {
		this.optionalSInt64 = optional_sint64;
		this.hasOptionalSInt64 = true;
	}
	
	private int optionalFixed32;
	private boolean hasOptionalFixed32;

	public boolean hasOptional_fixed32() {
		return hasOptionalFixed32;
	}

	public int getOptional_fixed32() {
		return optionalFixed32;
	}

	public void setOptional_fixed32(int optional_fixed32) {
		this.optionalFixed32 = optional_fixed32;
		this.hasOptionalFixed32 = true;
	}
	
	private long optionalFixed64;
	private boolean hasOptionalFixed64;

	public boolean hasOptional_fixed64() {
		return hasOptionalFixed64;
	}

	public long getOptional_fixed64() {
		return optionalFixed64;
	}

	public void setOptional_fixed64(long optional_fixed64) {
		this.optionalFixed64 = optional_fixed64;
		this.hasOptionalFixed64 = true;
	}

	public boolean hasOptional_sfixed32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getOptional_sfixed32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setOptional_sfixed32(int optional_sfixed32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_sfixed64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getOptional_sfixed64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setOptional_sfixed64(long optional_sfixed64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_float() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getOptional_float() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setOptional_float(float optional_float) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_double() {
		// TODO Auto-generated method stub
		return false;
	}

	public double getOptional_double() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setOptional_double(double optional_double) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_bool() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getOptional_bool() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setOptional_bool(boolean optional_bool) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_string() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getOptional_string() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_string(String optional_string) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_bytes() {
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] getOptional_bytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_bytes(byte[] optional_bytes) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_nested_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public NestedMessage getOptional_nested_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_nested_message(NestedMessage optional_nested_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_foreign_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public ForeignMessage getOptional_foreign_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_foreign_message(ForeignMessage optional_foreign_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_import_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImportMessage getOptional_import_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_import_message(ImportMessage optional_import_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_nested_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public NestedEnum getOptional_nested_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_nested_enum(NestedEnum optional_nested_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_foreign_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public ForeignEnum getOptional_foreign_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_foreign_enum(ForeignEnum optional_foreign_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_import_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImportEnum getOptional_import_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_import_enum(ImportEnum optional_import_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_string_piece() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getOptional_string_piece() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_string_piece(String optional_string_piece) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasOptional_cord() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getOptional_cord() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOptional_cord(String optional_cord) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_int32() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> getRepeated_int32() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_int32(List<Integer> repeated_int32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_int64() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Long> getRepeated_int64() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_int64(List<Long> repeated_int64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_uint32() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> getRepeated_uint32() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_uint32(List<Integer> repeated_uint32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_uint64() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Long> getRepeated_uint64() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_uint64(List<Long> repeated_uint64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_sint32() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> getRepeated_sint32() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_sint32(List<Integer> repeated_sint32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_sint64() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Long> getRepeated_sint64() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_sint64(List<Long> repeated_sint64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_fixed32() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> getRepeated_fixed32() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_fixed32(List<Integer> repeated_fixed32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_fixed64() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Long> getRepeated_fixed64() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_fixed64(List<Long> repeated_fixed64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_sfixed32() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Integer> getRepeated_sfixed32() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_sfixed32(List<Integer> repeated_sfixed32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_sfixed64() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Long> getRepeated_sfixed64() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_sfixed64(List<Long> repeated_sfixed64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_float() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Float> getRepeated_float() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_float(List<Float> repeated_float) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_double() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Double> getRepeated_double() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_double(List<Double> repeated_double) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_bool() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Boolean> getRepeated_bool() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_bool(List<Boolean> repeated_bool) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_string() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getRepeated_string() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_string(List<String> repeated_string) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_bytes() {
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] getRepeated_bytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_bytes(byte[] repeated_bytes) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_nested_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<NestedMessage> getRepeated_nested_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_nested_message(List<NestedMessage> repeated_nested_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_foreign_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ForeignMessage> getRepeated_foreign_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_foreign_message(List<ForeignMessage> repeated_foreign_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_import_message() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ImportMessage> getRepeated_import_message() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_import_message(List<ImportMessage> repeated_import_message) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_nested_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<NestedEnum> getRepeated_nested_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_nested_enum(List<NestedEnum> repeated_nested_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_foreign_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ForeignEnum> getRepeated_foreign_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_foreign_enum(List<ForeignEnum> repeated_foreign_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_import_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ImportEnum> getRepeated_import_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_import_enum(List<ImportEnum> repeated_import_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_string_piece() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getRepeated_string_piece() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_string_piece(List<String> repeated_string_piece) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasRepeated_cord() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getRepeated_cord() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRepeated_cord(List<String> repeated_cord) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_int32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDefault_int32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_int32(int default_int32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_int64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getDefault_int64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_int64(long default_int64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_uint32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDefault_uint32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_uint32(int default_uint32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_uint64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getDefault_uint64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_uint64(long default_uint64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_sint32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDefault_sint32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_sint32(int default_sint32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_sint64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getDefault_sint64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_sint64(long default_sint64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_fixed32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDefault_fixed32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_fixed32(int default_fixed32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_fixed64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getDefault_fixed64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_fixed64(long default_fixed64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_sfixed32() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getDefault_sfixed32() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_sfixed32(int default_sfixed32) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_sfixed64() {
		// TODO Auto-generated method stub
		return false;
	}

	public long getDefault_sfixed64() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_sfixed64(long default_sfixed64) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_float() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getDefault_float() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_float(float default_float) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_double() {
		// TODO Auto-generated method stub
		return false;
	}

	public double getDefault_double() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setDefault_double(double default_double) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_bool() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getDefault_bool() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDefault_bool(boolean default_bool) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_string() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDefault_string() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_string(String default_string) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_bytes() {
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] getDefault_bytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_bytes(byte[] default_bytes) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_nested_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public NestedEnum getDefault_nested_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_nested_enum(NestedEnum default_nested_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_foreign_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public ForeignEnum getDefault_foreign_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_foreign_enum(ForeignEnum default_foreign_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_import_enum() {
		// TODO Auto-generated method stub
		return false;
	}

	public ImportEnum getDefault_import_enum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_import_enum(ImportEnum default_import_enum) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_string_piece() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDefault_string_piece() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_string_piece(String default_string_piece) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasDefault_cord() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDefault_cord() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDefault_cord(String default_cord) {
		// TODO Auto-generated method stub
		
	}

}
