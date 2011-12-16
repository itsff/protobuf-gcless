package protobuf_gcless_unittest;

public class TestMutualRecursionAImpl implements protobuf_gcless_unittest.UnittestProto.TestMutualRecursionA {
private protobuf_gcless_unittest.UnittestProto.TestMutualRecursionB Bb;
private boolean hasBb;
public boolean hasBb() {
return hasBb;
}
public protobuf_gcless_unittest.UnittestProto.TestMutualRecursionB getBb() {
return Bb;
}
public void setBb(protobuf_gcless_unittest.UnittestProto.TestMutualRecursionB Bb) {
this.Bb = Bb;
this.hasBb = true;
}
}
