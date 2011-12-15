package protobuf_gcless_unittest;

public class TestMutualRecursionBImpl implements protobuf_gcless_unittest.UnittestProto.TestMutualRecursionB {
private protobuf_gcless_unittest.UnittestProto.TestMutualRecursionA A;
private boolean hasA;
public boolean hasA() {
return hasA;
}
public protobuf_gcless_unittest.UnittestProto.TestMutualRecursionA getA() {
return A;
}
public void setA(protobuf_gcless_unittest.UnittestProto.TestMutualRecursionA A) {
this.A = A;
this.hasA = true;
}
private int Optional_int32;
private boolean hasOptional_int32;
public boolean hasOptional_int32() {
return hasOptional_int32;
}
public int getOptional_int32() {
return Optional_int32;
}
public void setOptional_int32(int Optional_int32) {
this.Optional_int32 = Optional_int32;
this.hasOptional_int32 = true;
}
}
