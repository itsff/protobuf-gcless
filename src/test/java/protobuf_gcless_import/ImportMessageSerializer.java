package protobuf_gcless_import;

import java.io.IOException;
public final class ImportMessageSerializer {
public static byte[] serialize(protobuf_gcless_import.ImportMessage message) {
try {
assertInitialized(message);
int totalSize = 0;
if (message.hasD()) {
totalSize += ProtobufOutputStream.computeInt32Size(1, message.getD());
}
final byte[] result = new byte[totalSize];
int position = 0;
if (message.hasD()) {
position = ProtobufOutputStream.writeInt32(1, message.getD(), result, position);
}
ProtobufOutputStream.checkNoSpaceLeft(result, position);
return result;
} catch (IOException e) {
	throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);}
}
public static void serialize(protobuf_gcless_import.ImportMessage message, java.io.OutputStream os) {
try {
assertInitialized(message);
if (message.hasD()) {
ProtobufOutputStream.writeInt32(1, message.getD(), os);
}
} catch (IOException e) {
	throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);}
}
private static void assertInitialized(protobuf_gcless_import.ImportMessage message) {
}
}
