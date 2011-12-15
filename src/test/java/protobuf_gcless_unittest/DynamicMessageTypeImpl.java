package protobuf_gcless_unittest;

public class DynamicMessageTypeImpl implements protobuf_gcless_unittest.UnittestProto.TestDynamicExtensions.DynamicMessageType {
private int Dynamic_field;
private boolean hasDynamic_field;
public boolean hasDynamic_field() {
return hasDynamic_field;
}
public int getDynamic_field() {
return Dynamic_field;
}
public void setDynamic_field(int Dynamic_field) {
this.Dynamic_field = Dynamic_field;
this.hasDynamic_field = true;
}
}
