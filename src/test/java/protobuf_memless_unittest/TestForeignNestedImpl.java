package protobuf_memless_unittest;

public class TestForeignNestedImpl implements protobuf_memless_unittest.UnittestProto.TestForeignNested {
private protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedMessage Foreign_nested;
private boolean hasForeign_nested;
public boolean hasForeign_nested() {
return hasForeign_nested;
}
public protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedMessage getForeign_nested() {
return Foreign_nested;
}
public void setForeign_nested(protobuf_memless_unittest.UnittestProto.TestAllTypes.NestedMessage Foreign_nested) {
this.Foreign_nested = Foreign_nested;
this.hasForeign_nested = true;
}
}
