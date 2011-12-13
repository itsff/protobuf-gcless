package protobuf_memless_unittest;

public class TestMutualRecursionAImpl implements protobuf_memless_unittest.UnittestProto.TestMutualRecursionA {
private protobuf_memless_unittest.UnittestProto.TestMutualRecursionB Bb;
private boolean hasBb;
public boolean hasBb() {
return hasBb;
}
public protobuf_memless_unittest.UnittestProto.TestMutualRecursionB getBb() {
return Bb;
}
public void setBb(protobuf_memless_unittest.UnittestProto.TestMutualRecursionB Bb) {
this.Bb = Bb;
this.hasBb = true;
}
}
