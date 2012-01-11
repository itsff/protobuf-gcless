package protobuf_gcless_import;

import java.io.IOException;

import com.google.code.proto.gcless.CurrentCursor;
import com.google.code.proto.gcless.MessageFactory;
import com.google.code.proto.gcless.ProtobufInputStream;

public final class ImportMessageSerializer {
	public static byte[] serialize(protobuf_gcless_import.ImportMessage message) {
		try {
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
			throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
		}
	}

	public static void serialize(protobuf_gcless_import.ImportMessage message, java.io.OutputStream os) {
		try {
			if (message.hasD()) {
				ProtobufOutputStream.writeInt32(1, message.getD(), os);
			}
		} catch (IOException e) {
			throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
		}
	}

	public static protobuf_gcless_import.ImportMessage parseFrom(MessageFactory factory, byte[] data) throws java.io.IOException {
		protobuf_gcless_import.ImportMessage message = (protobuf_gcless_import.ImportMessage) factory.create("protobuf_gcless_import.ImportMessage");
		if (message == null || !(message instanceof protobuf_gcless_import.ImportMessage)) {
			throw new IOException("Factory create invalid message for type: protobuf_gcless_import.ImportMessage");
		}
		CurrentCursor cursor = new CurrentCursor();
		while (true) {
			int tag = ProtobufInputStream.readTag(data, cursor);
			switch (tag) {
			case 0:
				return message;
			default:
				ProtobufInputStream.skipUnknown(tag, data, cursor);
				break;
			case 1:
				message.setD(ProtobufInputStream.readInt32(data, cursor));
				break;
			}
		}
	}

	public static protobuf_gcless_import.ImportMessage parseFrom(MessageFactory factory, byte[] data, int offset, int length) throws java.io.IOException {
		protobuf_gcless_import.ImportMessage message = (protobuf_gcless_import.ImportMessage) factory.create("protobuf_gcless_import.ImportMessage");
		if (message == null || !(message instanceof protobuf_gcless_import.ImportMessage)) {
			throw new IOException("Factory create invalid message for type: protobuf_gcless_import.ImportMessage");
		}
		CurrentCursor cursor = new CurrentCursor();
		cursor.addToPosition(offset);
		cursor.setProcessUpToPosition(offset + length);
		while (true) {
			int tag = ProtobufInputStream.readTag(data, cursor);
			switch (tag) {
			case 0:
				return message;
			default:
				ProtobufInputStream.skipUnknown(tag, data, cursor);
				break;
			case 1:
				message.setD(ProtobufInputStream.readInt32(data, cursor));
				break;
			}
		}
	}
}
