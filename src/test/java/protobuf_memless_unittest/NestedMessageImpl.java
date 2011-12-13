package protobuf_memless_unittest;

public class NestedMessageImpl implements protobuf_memless_unittest.UnittestProto.TestNestedMessageHasBits.NestedMessage {
private java.util.List<Integer> Nestedmessage_repeated_int32;
private boolean hasNestedmessage_repeated_int32;
public boolean hasNestedmessage_repeated_int32() {
return hasNestedmessage_repeated_int32;
}
public java.util.List<Integer> getNestedmessage_repeated_int32() {
return Nestedmessage_repeated_int32;
}
public void setNestedmessage_repeated_int32(java.util.List<Integer> Nestedmessage_repeated_int32) {
this.Nestedmessage_repeated_int32 = Nestedmessage_repeated_int32;
this.hasNestedmessage_repeated_int32 = true;
}
private java.util.List<protobuf_memless_unittest.UnittestProto.ForeignMessage> Nestedmessage_repeated_foreignmessage;
private boolean hasNestedmessage_repeated_foreignmessage;
public boolean hasNestedmessage_repeated_foreignmessage() {
return hasNestedmessage_repeated_foreignmessage;
}
public java.util.List<protobuf_memless_unittest.UnittestProto.ForeignMessage> getNestedmessage_repeated_foreignmessage() {
return Nestedmessage_repeated_foreignmessage;
}
public void setNestedmessage_repeated_foreignmessage(java.util.List<protobuf_memless_unittest.UnittestProto.ForeignMessage> Nestedmessage_repeated_foreignmessage) {
this.Nestedmessage_repeated_foreignmessage = Nestedmessage_repeated_foreignmessage;
this.hasNestedmessage_repeated_foreignmessage = true;
}
}
