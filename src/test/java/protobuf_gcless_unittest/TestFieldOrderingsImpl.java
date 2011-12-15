package protobuf_gcless_unittest;

public class TestFieldOrderingsImpl implements protobuf_gcless_unittest.UnittestProto.TestFieldOrderings {
private String My_string;
private boolean hasMy_string;
public boolean hasMy_string() {
return hasMy_string;
}
public String getMy_string() {
return My_string;
}
public void setMy_string(String My_string) {
this.My_string = My_string;
this.hasMy_string = true;
}
private long My_int;
private boolean hasMy_int;
public boolean hasMy_int() {
return hasMy_int;
}
public long getMy_int() {
return My_int;
}
public void setMy_int(long My_int) {
this.My_int = My_int;
this.hasMy_int = true;
}
private float My_float;
private boolean hasMy_float;
public boolean hasMy_float() {
return hasMy_float;
}
public float getMy_float() {
return My_float;
}
public void setMy_float(float My_float) {
this.My_float = My_float;
this.hasMy_float = true;
}
public protobuf_gcless_unittest.UnittestProto.TestFieldOrderings create() {
return new TestFieldOrderingsImpl();
}
}
