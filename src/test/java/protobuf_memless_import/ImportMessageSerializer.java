package protobuf_memless_import;

import java.io.IOException;
import com.google.code.proto.memless.ProtobufOutputStream;

public final class ImportMessageSerializer {
	public static byte[] serialize(protobuf_memless_import.ImportMessage message) {
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
			throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
		}
	}

	private static void assertInitialized(protobuf_memless_import.ImportMessage message) {
	}
}
