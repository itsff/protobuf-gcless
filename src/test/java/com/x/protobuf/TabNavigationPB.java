// Generated by the protocol buffer gcless compiler.  DO NOT EDIT!
package com.x.protobuf;

import java.io.IOException;
public final class TabNavigationPB {
private TabNavigationPB() {}
public static class TabNavigation {
private String State;
private boolean hasState;
public boolean hasState() {
return hasState;
}
public String getState() {
return State;
}
public void setState(String State) {
this.State = State;
this.hasState = true;
}
private com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition PageTransition;
private boolean hasPageTransition;
public boolean hasPageTransition() {
return hasPageTransition;
}
public com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition getPageTransition() {
return PageTransition;
}
public void setPageTransition(com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition PageTransition) {
this.PageTransition = PageTransition;
this.hasPageTransition = true;
}
}
public static class TabNavigationSerializer {
public static byte[] serialize(com.x.protobuf.TabNavigationPB.TabNavigation message) {
try {
int totalSize = 0;
byte[] stateBuffer = null;
if (message.hasState()) {
stateBuffer = message.getState().getBytes("UTF-8");
totalSize += stateBuffer.length;
totalSize += ProtobufOutputStream.computeTagSize(5);
totalSize += ProtobufOutputStream.computeRawVarint32Size(stateBuffer.length);
}
if (message.hasPageTransition()) {
totalSize += ProtobufOutputStream.computeEnumSize(6, message.getPageTransition().getValue());
}
final byte[] result = new byte[totalSize];
int position = 0;
if (message.hasState()) {
position = ProtobufOutputStream.writeString(5,stateBuffer, result, position);
}
if (message.hasPageTransition()) {
position = ProtobufOutputStream.writeEnum(6, message.getPageTransition().getValue(), result, position);
}
ProtobufOutputStream.checkNoSpaceLeft(result, position);
return result;
} catch (IOException e) {
throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
}
}
public static void serialize(com.x.protobuf.TabNavigationPB.TabNavigation message, java.io.OutputStream os) {
try {
if (message.hasState()) {
ProtobufOutputStream.writeString(5, message.getState(), os);
}
if (message.hasPageTransition()) {
ProtobufOutputStream.writeEnum(6, message.getPageTransition().getValue(), os);
}
} catch (IOException e) {
throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
}
}
public static com.x.protobuf.TabNavigationPB.TabNavigation parseFrom(byte[] data) throws java.io.IOException {
com.x.protobuf.TabNavigationPB.TabNavigation message = new com.x.protobuf.TabNavigationPB.TabNavigation();
CurrentCursor cursor = new CurrentCursor();
while(true) {
int tag = ProtobufInputStream.readTag(data,cursor);
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(tag, data, cursor);
 break;
case 5: 
message.setState(ProtobufInputStream.readString(data,cursor));
break;
case 6: 
message.setPageTransition(com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition.valueOf(ProtobufInputStream.readEnum(data,cursor)));
break;
}
}
}
public static com.x.protobuf.TabNavigationPB.TabNavigation parseFrom(byte[] data, int offset, int length) throws java.io.IOException {
com.x.protobuf.TabNavigationPB.TabNavigation message = new com.x.protobuf.TabNavigationPB.TabNavigation();
CurrentCursor cursor = new CurrentCursor();
cursor.addToPosition(offset);
cursor.setProcessUpToPosition(offset + length);
while(true) {
int tag = ProtobufInputStream.readTag(data,cursor);
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(tag, data, cursor);
 break;
case 5: 
message.setState(ProtobufInputStream.readString(data,cursor));
break;
case 6: 
message.setPageTransition(com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition.valueOf(ProtobufInputStream.readEnum(data,cursor)));
break;
}
}
}
public static com.x.protobuf.TabNavigationPB.TabNavigation parseFrom(java.io.InputStream is) throws java.io.IOException {
com.x.protobuf.TabNavigationPB.TabNavigation message = new com.x.protobuf.TabNavigationPB.TabNavigation();
CurrentCursor cursor = new CurrentCursor();
while(true) {
int tag = ProtobufInputStream.readTag(is,cursor);
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(tag, is, cursor);
 break;case 5: 
message.setState(ProtobufInputStream.readString(is,cursor));
break;
case 6: 
message.setPageTransition(com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition.valueOf(ProtobufInputStream.readEnum(is,cursor)));
break;
}
}
}
public static com.x.protobuf.TabNavigationPB.TabNavigation parseFrom(java.io.InputStream is, int offset, int length) throws java.io.IOException {
com.x.protobuf.TabNavigationPB.TabNavigation message = new com.x.protobuf.TabNavigationPB.TabNavigation();
CurrentCursor cursor = new CurrentCursor();
cursor.addToPosition(offset);
cursor.setProcessUpToPosition(offset + length);
while(true) {
int tag = ProtobufInputStream.readTag(is,cursor);
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(tag, is, cursor);
 break;case 5: 
message.setState(ProtobufInputStream.readString(is,cursor));
break;
case 6: 
message.setPageTransition(com.x.protobuf.SyncEnumsPB.SyncEnums.PageTransition.valueOf(ProtobufInputStream.readEnum(is,cursor)));
break;
}
}
}
}
}

