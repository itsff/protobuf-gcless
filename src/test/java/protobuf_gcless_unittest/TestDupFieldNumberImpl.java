package protobuf_gcless_unittest;

public class TestDupFieldNumberImpl implements protobuf_gcless_unittest.UnittestProto.TestDupFieldNumber {
private int A;
private boolean hasA;
public boolean hasA() {
return hasA;
}
public int getA() {
return A;
}
public void setA(int A) {
this.A = A;
this.hasA = true;
}
public protobuf_gcless_unittest.UnittestProto.TestDupFieldNumber create() {
return new TestDupFieldNumberImpl();
}
}
