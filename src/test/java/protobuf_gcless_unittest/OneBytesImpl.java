package protobuf_gcless_unittest;

public class OneBytesImpl implements protobuf_gcless_unittest.UnittestProto.OneBytes {
private byte[] Data;
private boolean hasData;
public boolean hasData() {
return hasData;
}
public byte[] getData() {
return Data;
}
public void setData(byte[] Data) {
this.Data = Data;
this.hasData = true;
}
}
