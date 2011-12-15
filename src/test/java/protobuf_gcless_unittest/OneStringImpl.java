package protobuf_gcless_unittest;

public class OneStringImpl implements protobuf_gcless_unittest.UnittestProto.OneString {
private String Data;
private boolean hasData;
public boolean hasData() {
return hasData;
}
public String getData() {
return Data;
}
public void setData(String Data) {
this.Data = Data;
this.hasData = true;
}
public protobuf_gcless_unittest.UnittestProto.OneString create() {
return new OneStringImpl();
}
}
