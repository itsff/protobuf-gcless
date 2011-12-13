package protobuf_memless_unittest;

public class TestNestedExtensionImpl implements protobuf_memless_unittest.UnittestProto.TestNestedExtension {
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
}
