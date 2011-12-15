package protobuf_gcless_unittest;

public class ForeignMessageImpl implements protobuf_gcless_unittest.UnittestProto.ForeignMessage {
private int C;
private boolean hasC;
public boolean hasC() {
return hasC;
}
public int getC() {
return C;
}
public void setC(int C) {
this.C = C;
this.hasC = true;
}
}
