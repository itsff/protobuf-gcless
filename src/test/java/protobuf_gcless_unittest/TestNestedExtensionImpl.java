package protobuf_gcless_unittest;

public class TestNestedExtensionImpl implements protobuf_gcless_unittest.UnittestProto.TestNestedExtension {
private String Test;
private boolean hasTest;
public boolean hasTest() {
return hasTest;
}
public String getTest() {
return Test;
}
public void setTest(String Test) {
this.Test = Test;
this.hasTest = true;
}
public protobuf_gcless_unittest.UnittestProto.TestNestedExtension create() {
return new TestNestedExtensionImpl();
}
}
