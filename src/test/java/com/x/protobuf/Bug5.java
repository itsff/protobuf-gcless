// Generated by the protocol buffer gcless compiler.  DO NOT EDIT!
package com.x.protobuf;

import java.io.IOException;
public final class Bug5 {
private Bug5() {}
public static class A {
private java.util.List<String> entry;
private boolean hasEntry;
public boolean hasEntry() {
return hasEntry;
}
public java.util.List<String> getEntry() {
return entry;
}
public void setEntry(java.util.List<String> Entry) {
this.entry = Entry;
this.hasEntry = true;
}
public String getEntry(int index) {
return this.entry.get(index);
}
public int getEntryCount() {
return this.entry.size();
}
public void setEntry(int index, String value) {
this.entry.set(index, value);
}
public void addEntry(String value) {
if( this.entry == null ) {
this.entry = new java.util.ArrayList<String>();
}
this.entry.add(value);
}
public void addAllEntry(java.lang.Iterable<? extends String> values) {
if( this.entry == null ) {
this.entry = new java.util.ArrayList<String>();
}
if (values instanceof java.util.Collection) {
@SuppressWarnings("unsafe") final
java.util.Collection<? extends String> collection = (java.util.Collection<? extends String>) values;
this.entry.addAll(collection);
} else {
for (final String value : values) {
this.entry.add(value);
}
}
this.hasEntry = true;
}
public void clearEntry() {
this.hasEntry = false;
this.entry = null;
}
private java.util.List<Integer> entry2;
private boolean hasEntry2;
public boolean hasEntry2() {
return hasEntry2;
}
public java.util.List<Integer> getEntry2() {
return entry2;
}
public void setEntry2(java.util.List<Integer> Entry2) {
this.entry2 = Entry2;
this.hasEntry2 = true;
}
public Integer getEntry2(int index) {
return this.entry2.get(index);
}
public int getEntry2Count() {
return this.entry2.size();
}
public void setEntry2(int index, Integer value) {
this.entry2.set(index, value);
}
public void addEntry2(Integer value) {
if( this.entry2 == null ) {
this.entry2 = new java.util.ArrayList<Integer>();
}
this.entry2.add(value);
}
public void addAllEntry2(java.lang.Iterable<? extends Integer> values) {
if( this.entry2 == null ) {
this.entry2 = new java.util.ArrayList<Integer>();
}
if (values instanceof java.util.Collection) {
@SuppressWarnings("unsafe") final
java.util.Collection<? extends Integer> collection = (java.util.Collection<? extends Integer>) values;
this.entry2.addAll(collection);
} else {
for (final Integer value : values) {
this.entry2.add(value);
}
}
this.hasEntry2 = true;
}
public void clearEntry2() {
this.hasEntry2 = false;
this.entry2 = null;
}
}
public static class ASerializer {
public static byte[] serialize(com.x.protobuf.Bug5.A message) {
try {
int totalSize = 0;
byte[] entryBuffer = null;
if (message.hasEntry()) {
java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
for( int i=0;i<message.getEntry().size();i++) {
ProtobufOutputStream.writeString(32904, message.getEntry().get(i), baos);
}
entryBuffer = baos.toByteArray();
totalSize += entryBuffer.length;
}
if (message.hasEntry2()) {
for(int i=0;i<message.getEntry2().size();i++) {
totalSize += ProtobufOutputStream.computeInt32Size(32902, message.getEntry2().get(i));
}
}
final byte[] result = new byte[totalSize];
int position = 0;
if (message.hasEntry()) {
position = ProtobufOutputStream.writeRawBytes(entryBuffer, result, position);
}
if (message.hasEntry2()) {
position = ProtobufOutputStream.writeRepeatedInt32(32902, message.getEntry2(), result, position);
}
ProtobufOutputStream.checkNoSpaceLeft(result, position);
return result;
} catch (Exception e) {
throw new RuntimeException(e);
}
}
public static void serialize(com.x.protobuf.Bug5.A message, java.io.OutputStream os) {
try {
if (message.hasEntry()) {
for( int i=0;i<message.getEntry().size();i++) {
ProtobufOutputStream.writeString(32904, message.getEntry().get(i), os);
}
}
if (message.hasEntry2()) {
for( int i=0;i<message.getEntry2().size();i++) {
ProtobufOutputStream.writeInt32(32902, message.getEntry2().get(i), os);
}
}
} catch (IOException e) {
throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
}
}
public static com.x.protobuf.Bug5.A parseFrom(byte[] data) throws java.io.IOException {
CurrentCursor cursor = new CurrentCursor();
return parseFrom(data, cursor);
}
public static com.x.protobuf.Bug5.A parseFrom(byte[] data, int offset, int length) throws java.io.IOException {
CurrentCursor cursor = new CurrentCursor();
cursor.addToPosition(offset);
cursor.setProcessUpToPosition(offset + length);
return parseFrom(data, cursor);
}
public static com.x.protobuf.Bug5.A parseFrom(byte[] data, CurrentCursor cursor) throws java.io.IOException {
com.x.protobuf.Bug5.A message = new com.x.protobuf.Bug5.A();
while(true) {
if (ProtobufInputStream.isAtEnd(data, cursor)) {
return message;
}
int varint = ProtobufInputStream.readRawVarint32(data, cursor);
int tag = ProtobufInputStream.getTagFieldNumber(varint);
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(varint, data, cursor);
 break;
case 32904: 
if( message.getEntry() == null || message.getEntry().isEmpty()) {
message.setEntry(new java.util.ArrayList<String>());
}
message.getEntry().add(ProtobufInputStream.readString(data,cursor));
break;
case 32902: 
if( message.getEntry2() == null || message.getEntry2().isEmpty()) {
message.setEntry2(new java.util.ArrayList<Integer>());
}
message.getEntry2().add(ProtobufInputStream.readInt32(data,cursor));
break;
}
}
}
public static com.x.protobuf.Bug5.A parseFrom(java.io.InputStream is) throws java.io.IOException {
CurrentCursor cursor = new CurrentCursor();
return parseFrom(is, cursor);
}
public static com.x.protobuf.Bug5.A parseFrom(java.io.InputStream is, int offset, int length) throws java.io.IOException {
CurrentCursor cursor = new CurrentCursor();
cursor.addToPosition(offset);
cursor.setProcessUpToPosition(offset + length);
return parseFrom(is, cursor);
}
public static com.x.protobuf.Bug5.A parseFrom(java.io.InputStream is, CurrentCursor cursor) throws java.io.IOException {
com.x.protobuf.Bug5.A message = new com.x.protobuf.Bug5.A();
while(true) {
if( cursor.getCurrentPosition() == cursor.getProcessUpToPosition() ) {
return message;
}
int varint = ProtobufInputStream.readRawVarint32(is, cursor);
int tag = ProtobufInputStream.getTagFieldNumber(varint);
if (ProtobufInputStream.isAtEnd(cursor)) {
return message;
}
switch(tag) {
case 0: 
return message;
 default: 
 ProtobufInputStream.skipUnknown(varint, is, cursor);
 break;case 32904: 
if( message.getEntry() == null || message.getEntry().isEmpty()) {
message.setEntry(new java.util.ArrayList<String>());
}
message.getEntry().add(ProtobufInputStream.readString(is,cursor));
break;
case 32902: 
if( message.getEntry2() == null || message.getEntry2().isEmpty()) {
message.setEntry2(new java.util.ArrayList<Integer>());
}
message.getEntry2().add(ProtobufInputStream.readInt32(is,cursor));
break;
}
}
}
}
}

