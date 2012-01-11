package com.google.code.proto.gcless;

import java.io.IOException;
import java.io.InputStream;

final public class ProtobufInputStream {

	static final int WIRETYPE_VARINT = 0;
	static final int WIRETYPE_FIXED64 = 1;
	static final int WIRETYPE_LENGTH_DELIMITED = 2;
	static final int WIRETYPE_FIXED32 = 5;

	static final int TAG_TYPE_BITS = 3;
	static final int TAG_TYPE_MASK = (1 << TAG_TYPE_BITS) - 1;

	public static boolean skipUnknown(final int tag, byte[] data, CurrentCursor cursor) throws IOException {
		switch (getTagWireType(tag)) {
		case WIRETYPE_VARINT:
			readInt64(data, cursor);
			return true;
		case WIRETYPE_FIXED64:
			readFixed64(data, cursor);
			return true;
		case WIRETYPE_LENGTH_DELIMITED:
			readBytes(data, cursor);
			return true;
		case WIRETYPE_FIXED32:
			readFixed32(data, cursor);
			return true;
		default:
			throw new IOException("invalid wire type");
		}
	}

	public static int readEnum(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint32(data, cursor);
	}

	public static byte[] readBytes(byte[] data, CurrentCursor cursor) throws IOException {
		final int size = readRawVarint32(data, cursor);
		return readRawBytes(size, data, cursor);
	}

	private static int getTagWireType(final int tag) {
		return tag & TAG_TYPE_MASK;
	}

	public static int readTag(byte[] data, CurrentCursor currentPosition) throws IOException {
		if (isAtEnd(data, currentPosition.getCurrentPosition())) {
			return 0;
		}

		int lastTag = getTagFieldNumber(readRawVarint32(data, currentPosition));
		if (lastTag == 0) {
			// If we actually read zero (or any tag number corresponding to field
			// number zero), that's not a valid tag.
			throw new IOException("invalid data");
		}
		return lastTag;
	}
	
	public static int readTag(InputStream is, CurrentCursor cursor) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int readInt32(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint32(data, cursor);
	}

	public static long readInt64(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint64(data, cursor);
	}

	public static long readRawVarint64(byte[] data, CurrentCursor cursor) throws IOException {
		int shift = 0;
		long result = 0;
		while (shift < 64) {
			final byte b = readRawByte(data, cursor);
			result |= (long) (b & 0x7F) << shift;
			if ((b & 0x80) == 0) {
				return result;
			}
			shift += 7;
		}
		throw new IOException("malformed varint64");
	}

	public static int readUint32(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint32(data, cursor);
	}

	public static int readRawVarint32(byte[] data, CurrentCursor currentPosition) throws IOException {
		byte tmp = readRawByte(data, currentPosition);
		if (tmp >= 0) {
			return tmp;
		}
		int result = tmp & 0x7f;
		if ((tmp = readRawByte(data, currentPosition)) >= 0) {
			result |= tmp << 7;
		} else {
			result |= (tmp & 0x7f) << 7;
			if ((tmp = readRawByte(data, currentPosition)) >= 0) {
				result |= tmp << 14;
			} else {
				result |= (tmp & 0x7f) << 14;
				if ((tmp = readRawByte(data, currentPosition)) >= 0) {
					result |= tmp << 21;
				} else {
					result |= (tmp & 0x7f) << 21;
					result |= (tmp = readRawByte(data, currentPosition)) << 28;
					if (tmp < 0) {
						// Discard upper 32 bits.
						for (int i = 0; i < 5; i++) {
							if (readRawByte(data, currentPosition) >= 0) {
								return result;
							}
						}
						throw new IOException("malformed varint32");
					}
				}
			}
		}
		return result;
	}
	
	public static int readRawVarint32(InputStream is) throws IOException {
		byte tmp = readRawByte(is);
		if (tmp >= 0) {
			return tmp;
		}
		int result = tmp & 0x7f;
		if ((tmp = readRawByte(is)) >= 0) {
			result |= tmp << 7;
		} else {
			result |= (tmp & 0x7f) << 7;
			if ((tmp = readRawByte(is)) >= 0) {
				result |= tmp << 14;
			} else {
				result |= (tmp & 0x7f) << 14;
				if ((tmp = readRawByte(is)) >= 0) {
					result |= tmp << 21;
				} else {
					result |= (tmp & 0x7f) << 21;
					result |= (tmp = readRawByte(is)) << 28;
					if (tmp < 0) {
						// Discard upper 32 bits.
						for (int i = 0; i < 5; i++) {
							if (readRawByte(is) >= 0) {
								return result;
							}
						}
						throw new IOException("malformed varint32");
					}
				}
			}
		}
		return result;
	}

	public static byte readRawByte(byte[] data, CurrentCursor currentPosition) throws IOException {
		byte result = data[currentPosition.getCurrentPosition()];
		currentPosition.addToPosition(1);
		return result;
	}
	
	public static byte readRawByte(InputStream is) throws IOException {
		return (byte)is.read();
	}

	public static boolean isAtEnd(byte[] data, int currentPosition) throws IOException {
		return currentPosition == data.length;
	}

	private static int getTagFieldNumber(final int tag) {
		return tag >>> TAG_TYPE_BITS;
	}

	private static int decodeZigZag32(final int n) {
		return (n >>> 1) ^ -(n & 1);
	}

	private static long decodeZigZag64(final long n) {
		return (n >>> 1) ^ -(n & 1);
	}

	private ProtobufInputStream() {
		//do nothing
	}

	public static long readUint64(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint64(data, cursor);
	}

	public static int readSint32(byte[] data, CurrentCursor cursor) throws IOException {
		return decodeZigZag32(readRawVarint32(data, cursor));
	}

	public static long readSint64(byte[] data, CurrentCursor cursor) throws IOException {
		return decodeZigZag64(readRawVarint64(data, cursor));
	}

	public static int readFixed32(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawLittleEndian32(data, cursor);
	}

	public static int readRawLittleEndian32(byte[] data, CurrentCursor cursor) throws IOException {
		final byte b1 = readRawByte(data, cursor);
		final byte b2 = readRawByte(data, cursor);
		final byte b3 = readRawByte(data, cursor);
		final byte b4 = readRawByte(data, cursor);
		return (((int) b1 & 0xff)) | (((int) b2 & 0xff) << 8) | (((int) b3 & 0xff) << 16) | (((int) b4 & 0xff) << 24);
	}

	public static long readFixed64(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawLittleEndian64(data, cursor);
	}

	public static long readRawLittleEndian64(byte[] data, CurrentCursor cursor) throws IOException {
		final byte b1 = readRawByte(data, cursor);
		final byte b2 = readRawByte(data, cursor);
		final byte b3 = readRawByte(data, cursor);
		final byte b4 = readRawByte(data, cursor);
		final byte b5 = readRawByte(data, cursor);
		final byte b6 = readRawByte(data, cursor);
		final byte b7 = readRawByte(data, cursor);
		final byte b8 = readRawByte(data, cursor);
		return (((long) b1 & 0xff)) | (((long) b2 & 0xff) << 8) | (((long) b3 & 0xff) << 16) | (((long) b4 & 0xff) << 24) | (((long) b5 & 0xff) << 32) | (((long) b6 & 0xff) << 40) | (((long) b7 & 0xff) << 48) | (((long) b8 & 0xff) << 56);
	}

	public static int readSfixed32(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawLittleEndian32(data, cursor);
	}

	public static long readSfixed64(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawLittleEndian64(data, cursor);
	}

	public static float readFloat(byte[] data, CurrentCursor cursor) throws IOException {
		return Float.intBitsToFloat(readRawLittleEndian32(data, cursor));
	}

	public static double readDouble(byte[] data, CurrentCursor cursor) throws IOException {
		return Double.longBitsToDouble(readRawLittleEndian64(data, cursor));
	}

	public static boolean readBool(byte[] data, CurrentCursor cursor) throws IOException {
		return readRawVarint32(data, cursor) != 0;
	}

	public static String readString(byte[] data, CurrentCursor cursor) throws IOException {
		final int size = readRawVarint32(data, cursor);
		if (size <= (data.length - cursor.getCurrentPosition()) && size > 0) {
			// Fast path:  We already have the bytes in a contiguous buffer, so
			//   just copy directly from it.
			final String result = new String(data, cursor.getCurrentPosition(), size, "UTF-8");
			cursor.addToPosition(size);
			return result;
		} else {
			// Slow path:  Build a byte array first then copy it.
			return new String(readRawBytes(size, data, cursor), "UTF-8");
		}
	}

	public static byte[] readRawBytes(final int size, byte[] data, CurrentCursor cursor) throws IOException {
		if (size < 0) {
			throw new IOException("Invalid buffer size");
		}

		// We have all the bytes we need already.
		final byte[] bytes = new byte[size];
		System.arraycopy(data, cursor.getCurrentPosition(), bytes, 0, size);
		cursor.addToPosition(size);
		return bytes;
	}

}
