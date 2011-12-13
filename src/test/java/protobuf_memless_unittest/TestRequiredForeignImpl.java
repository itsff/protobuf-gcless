package protobuf_memless_unittest;

public class TestRequiredForeignImpl implements protobuf_memless_unittest.UnittestProto.TestRequiredForeign {
private protobuf_memless_unittest.UnittestProto.TestRequired Optional_message;
private boolean hasOptional_message;
public boolean hasOptional_message() {
return hasOptional_message;
}
public protobuf_memless_unittest.UnittestProto.TestRequired getOptional_message() {
return Optional_message;
}
public void setOptional_message(protobuf_memless_unittest.UnittestProto.TestRequired Optional_message) {
this.Optional_message = Optional_message;
this.hasOptional_message = true;
}
private java.util.List<protobuf_memless_unittest.UnittestProto.TestRequired> Repeated_message;
private boolean hasRepeated_message;
public boolean hasRepeated_message() {
return hasRepeated_message;
}
public java.util.List<protobuf_memless_unittest.UnittestProto.TestRequired> getRepeated_message() {
return Repeated_message;
}
public void setRepeated_message(java.util.List<protobuf_memless_unittest.UnittestProto.TestRequired> Repeated_message) {
this.Repeated_message = Repeated_message;
this.hasRepeated_message = true;
}
private int Dummy;
private boolean hasDummy;
public boolean hasDummy() {
return hasDummy;
}
public int getDummy() {
return Dummy;
}
public void setDummy(int Dummy) {
this.Dummy = Dummy;
this.hasDummy = true;
}
}
