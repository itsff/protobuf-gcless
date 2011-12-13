package protobuf_memless_unittest;

public class TestDynamicExtensionsImpl implements protobuf_memless_unittest.UnittestProto.TestDynamicExtensions {
private int Scalar_extension;
private boolean hasScalar_extension;
public boolean hasScalar_extension() {
return hasScalar_extension;
}
public int getScalar_extension() {
return Scalar_extension;
}
public void setScalar_extension(int Scalar_extension) {
this.Scalar_extension = Scalar_extension;
this.hasScalar_extension = true;
}
private protobuf_memless_unittest.UnittestProto.ForeignEnum Enum_extension;
private boolean hasEnum_extension;
public boolean hasEnum_extension() {
return hasEnum_extension;
}
public protobuf_memless_unittest.UnittestProto.ForeignEnum getEnum_extension() {
return Enum_extension;
}
public void setEnum_extension(protobuf_memless_unittest.UnittestProto.ForeignEnum Enum_extension) {
this.Enum_extension = Enum_extension;
this.hasEnum_extension = true;
}
private protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicEnumType Dynamic_enum_extension;
private boolean hasDynamic_enum_extension;
public boolean hasDynamic_enum_extension() {
return hasDynamic_enum_extension;
}
public protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicEnumType getDynamic_enum_extension() {
return Dynamic_enum_extension;
}
public void setDynamic_enum_extension(protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicEnumType Dynamic_enum_extension) {
this.Dynamic_enum_extension = Dynamic_enum_extension;
this.hasDynamic_enum_extension = true;
}
private protobuf_memless_unittest.UnittestProto.ForeignMessage Message_extension;
private boolean hasMessage_extension;
public boolean hasMessage_extension() {
return hasMessage_extension;
}
public protobuf_memless_unittest.UnittestProto.ForeignMessage getMessage_extension() {
return Message_extension;
}
public void setMessage_extension(protobuf_memless_unittest.UnittestProto.ForeignMessage Message_extension) {
this.Message_extension = Message_extension;
this.hasMessage_extension = true;
}
private protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicMessageType Dynamic_message_extension;
private boolean hasDynamic_message_extension;
public boolean hasDynamic_message_extension() {
return hasDynamic_message_extension;
}
public protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicMessageType getDynamic_message_extension() {
return Dynamic_message_extension;
}
public void setDynamic_message_extension(protobuf_memless_unittest.UnittestProto.TestDynamicExtensions.DynamicMessageType Dynamic_message_extension) {
this.Dynamic_message_extension = Dynamic_message_extension;
this.hasDynamic_message_extension = true;
}
private java.util.List<String> Repeated_extension;
private boolean hasRepeated_extension;
public boolean hasRepeated_extension() {
return hasRepeated_extension;
}
public java.util.List<String> getRepeated_extension() {
return Repeated_extension;
}
public void setRepeated_extension(java.util.List<String> Repeated_extension) {
this.Repeated_extension = Repeated_extension;
this.hasRepeated_extension = true;
}
private java.util.List<Integer> Packed_extension;
private boolean hasPacked_extension;
public boolean hasPacked_extension() {
return hasPacked_extension;
}
public java.util.List<Integer> getPacked_extension() {
return Packed_extension;
}
public void setPacked_extension(java.util.List<Integer> Packed_extension) {
this.Packed_extension = Packed_extension;
this.hasPacked_extension = true;
}
}
