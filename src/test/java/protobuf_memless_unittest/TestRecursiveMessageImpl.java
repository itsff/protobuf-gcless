package protobuf_memless_unittest;

public class TestRecursiveMessageImpl implements protobuf_memless_unittest.UnittestProto.TestRecursiveMessage {
private protobuf_memless_unittest.UnittestProto.TestRecursiveMessage A;
private boolean hasA;
public boolean hasA() {
return hasA;
}
public protobuf_memless_unittest.UnittestProto.TestRecursiveMessage getA() {
return A;
}
public void setA(protobuf_memless_unittest.UnittestProto.TestRecursiveMessage A) {
this.A = A;
this.hasA = true;
}
private int I;
private boolean hasI;
public boolean hasI() {
return hasI;
}
public int getI() {
return I;
}
public void setI(int I) {
this.I = I;
this.hasI = true;
}
}
