package com.google.code.proto.memless;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ProtobufOutputStream {

	static final int WIRETYPE_VARINT = 0;
	static final int WIRETYPE_FIXED64 = 1;
	static final int WIRETYPE_LENGTH_DELIMITED = 2;
	static final int WIRETYPE_START_GROUP = 3;
	static final int WIRETYPE_END_GROUP = 4;
	static final int WIRETYPE_FIXED32 = 5;

	static final int TAG_TYPE_BITS = 3;
	static final int TAG_TYPE_MASK = (1 << TAG_TYPE_BITS) - 1;

	static final int LITTLE_ENDIAN_64_SIZE = 8;
	public static final int LITTLE_ENDIAN_32_SIZE = 4;

	public static int writeDouble(final int fieldNumber, final double value, byte[] output, int currentPosition) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED64, output, currentPosition);
		return writeDoubleNoTag(value, output, result);
	}

	public static int writeTag(final int fieldNumber, final int wireType, byte[] buffer, int position) throws IOException {
		return writeRawVarint32(makeTag(fieldNumber, wireType), buffer, position);
	}

	public static int writeFloat(final int fieldNumber, final float value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED32, buffer, position);
		return writeFloatNoTag(value, buffer, result);
	}

	public static int writeUint64(final int fieldNumber, final long value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeUint64NoTag(value, buffer, result);
	}

	public static int writeInt64(final int fieldNumber, final long value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeInt64NoTag(value, buffer, result);
	}

	public static int writeInt32(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeInt32NoTag(value, buffer, result);
	}

	public static int writeFixed64(final int fieldNumber, final long value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED64, buffer, position);
		return writeFixed64NoTag(value, buffer, result);
	}

	public static int writeFixed32(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED32, buffer, position);
		return writeFixed32NoTag(value, buffer, result);
	}

	public static int writeBool(final int fieldNumber, final boolean value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeBoolNoTag(value, buffer, result);
	}

	public static int writeString(final int fieldNumber, final String value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_LENGTH_DELIMITED, buffer, position);
		return writeStringNoTag(value, buffer, result);
	}

	public static int writeBytes(final int fieldNumber, final byte[] value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_LENGTH_DELIMITED, buffer, position);
		result = writeRawVarint32(value.length, buffer, result);
		result = writeRawBytes(value, buffer, result);
		return result;
	}

	public static int writeUint32(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeUint32NoTag(value, buffer, result);
	}

	public static int writeSfixed32(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED32, buffer, position);
		return writeSfixed32NoTag(value, buffer, result);
	}

	public static int writeSfixed64(final int fieldNumber, final long value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_FIXED64, buffer, position);
		return writeSfixed64NoTag(value, buffer, result);
	}

	public static int writeSint32(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeSint32NoTag(value, buffer, result);
	}

	public static int writeSint64(final int fieldNumber, final long value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeSint64NoTag(value, buffer, result);
	}

	public static int writeDoubleNoTag(final double value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian64(Double.doubleToRawLongBits(value), buffer, position);
	}

	public static int writeFloatNoTag(final float value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian32(Float.floatToRawIntBits(value), buffer, position);
	}

	public static int writeUint64NoTag(final long value, byte[] buffer, int position) throws IOException {
		return writeRawVarint64(value, buffer, position);
	}

	public static int writeInt64NoTag(final long value, byte[] buffer, int position) throws IOException {
		return writeRawVarint64(value, buffer, position);
	}

	public static int writeInt32NoTag(final int value, byte[] buffer, int position) throws IOException {
		if (value >= 0) {
			return writeRawVarint32(value, buffer, position);
		} else {
			// Must sign-extend.
			return writeRawVarint64(value, buffer, position);
		}
	}

	public static int writeFixed64NoTag(final long value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian64(value, buffer, position);
	}

	public static int writeFixed32NoTag(final int value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian32(value, buffer, position);
	}

	public static int writeBoolNoTag(final boolean value, byte[] buffer, int position) throws IOException {
		return writeRawByte(value ? 1 : 0, buffer, position);
	}

	public static int writeStringNoTag(final String value, byte[] buffer, int position) throws IOException {
		// Unfortunately there does not appear to be any way to tell Java to encode
		// UTF-8 directly into our buffer, so we have to let it create its own byte
		// array and then copy.
		final byte[] bytes = value.getBytes("UTF-8");
		int result = writeRawVarint32(bytes.length, buffer, position);
		return writeRawBytes(bytes, buffer, result);
	}

	public static int writeRawBytes(final byte[] value, byte[] buffer, int position) throws IOException {
		return writeRawBytes(value, 0, value.length, buffer, position);
	}

	public static int writeRawBytes(final byte[] value, int offset, int length, byte[] buffer, int position) throws IOException {
		if (buffer.length - position >= length) {
// We have room in the current buffer.
			System.arraycopy(value, offset, buffer, position, length);
			position += length;
		} else {
			//TODO something with outputstreams
// Write extends past current buffer.  Fill the rest of this buffer and
// flush.
//			final int bytesWritten = buffer.length - position;
//			System.arraycopy(value, offset, buffer, position, bytesWritten);
//			offset += bytesWritten;
//			length -= bytesWritten;
//			position = buffer.length;
//			refreshBuffer();
//
//// Now deal with the rest.
//// Since we have an output stream, this is our buffer
//// and buffer offset == 0
//			if (length <= limit) {
//// Fits in new buffer.
//				System.arraycopy(value, offset, buffer, 0, length);
//				position = length;
//			} else {
//// Write is very big.  Let's do it all at once.
//				output.write(value, offset, length);
//			}
		}
		return position + length;
	}

	public static int writeUint32NoTag(final int value, byte[] buffer, int position) throws IOException {
		return writeRawVarint32(value, buffer, position);
	}

	public static int writeSfixed32NoTag(final int value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian32(value, buffer, position);
	}

	public static int writeSfixed64NoTag(final long value, byte[] buffer, int position) throws IOException {
		return writeRawLittleEndian64(value, buffer, position);
	}

	public static int writeSint32NoTag(final int value, byte[] buffer, int position) throws IOException {
		return writeRawVarint32(encodeZigZag32(value), buffer, position);
	}

	public static int writeSint64NoTag(final long value, byte[] buffer, int position) throws IOException {
		return writeRawVarint64(encodeZigZag64(value), buffer, position);
	}

	public static int writeRawVarint32(int value, byte[] buffer, int position) throws IOException {
		int result = position;
		while (true) {
			if ((value & ~0x7F) == 0) {
				result = writeRawByte(value, buffer, result);
				return result;
			} else {
				result = writeRawByte((value & 0x7F) | 0x80, buffer, result);
				value >>>= 7;
			}
		}
	}

	public static int writeRawVarint64(long value, byte[] buffer, int position) throws IOException {
		int result = position;
		while (true) {
			if ((value & ~0x7FL) == 0) {
				result = writeRawByte((int) value, buffer, result);
				return result;
			} else {
				result = writeRawByte(((int) value & 0x7F) | 0x80, buffer, result);
				value >>>= 7;
			}
		}
	}

	public static int writeEnum(final int fieldNumber, final int value, byte[] buffer, int position) throws IOException {
		int result = writeTag(fieldNumber, WIRETYPE_VARINT, buffer, position);
		return writeEnumNoTag(value, buffer, result);
	}

	public static int writeEnumNoTag(final int value, byte[] buffer, int position) throws IOException {
		return writeRawVarint32(value, buffer, position);
	}

	public static int writeRawLittleEndian32(final int value, byte[] buffer, int position) throws IOException {
		int result = writeRawByte((value) & 0xFF, buffer, position);
		result = writeRawByte((value >> 8) & 0xFF, buffer, result);
		result = writeRawByte((value >> 16) & 0xFF, buffer, result);
		result = writeRawByte((value >> 24) & 0xFF, buffer, result);
		return result;
	}

	public static int encodeZigZag32(final int n) {
		// Note:  the right-shift must be arithmetic
		return (n << 1) ^ (n >> 31);
	}

	private static long encodeZigZag64(final long n) {
		// Note:  the right-shift must be arithmetic
		return (n << 1) ^ (n >> 63);
	}

	public static int makeTag(final int fieldNumber, final int wireType) {
		return (fieldNumber << TAG_TYPE_BITS) | wireType;
	}

	public static int writeRawLittleEndian64(final long value, byte[] buffer, int position) throws IOException {
		int result = writeRawByte((int) (value) & 0xFF, buffer, position);
		result = writeRawByte((int) (value >> 8) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 16) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 24) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 32) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 40) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 48) & 0xFF, buffer, result);
		result = writeRawByte((int) (value >> 56) & 0xFF, buffer, result);
		return result;
	}

	public static int writeRawByte(final byte value, byte[] buffer, int position) throws IOException {
		if (position == buffer.length) {
			throw new ArrayIndexOutOfBoundsException("can't write more than buffer expects");
		}

		buffer[position] = value;
		return position + 1;
	}

	public static int writeRawByte(final int value, byte[] buffer, int position) throws IOException {
		return writeRawByte((byte) value, buffer, position);
	}

	public static int spaceLeft(byte[] output, int position) {
		return output.length - position;
	}

	public static void checkNoSpaceLeft(byte[] output, int position) {
		if (spaceLeft(output, position) != 0) {
			throw new IllegalStateException("Did not write as much data as expected.");
		}
	}

	public static int computeDoubleSize(final int fieldNumber, final double value) {
		return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
	}

	public static int computeFloatSize(final int fieldNumber, final float value) {
		return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
	}

	public static int computeTagSize(final int fieldNumber) {
		return computeRawVarint32Size(makeTag(fieldNumber, 0));
	}

	public static int computeRawVarint32Size(final int value) {
		if ((value & (0xffffffff << 7)) == 0)
			return 1;
		if ((value & (0xffffffff << 14)) == 0)
			return 2;
		if ((value & (0xffffffff << 21)) == 0)
			return 3;
		if ((value & (0xffffffff << 28)) == 0)
			return 4;
		return 5;
	}

	public static int computeUint64Size(final int fieldNumber, final long value) {
		return computeTagSize(fieldNumber) + computeUint64SizeNoTag(value);
	}

	public static int computeInt64Size(final int fieldNumber, final long value) {
		return computeTagSize(fieldNumber) + computeInt64SizeNoTag(value);
	}

	public static int computeInt32Size(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
	}

	public static int computeFixed64Size(final int fieldNumber, final long value) {
		return computeTagSize(fieldNumber) + computeFixed64SizeNoTag(value);
	}

	public static int computeFixed32Size(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeFixed32SizeNoTag(value);
	}

	public static int computeBoolSize(final int fieldNumber, final boolean value) {
		return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
	}

	public static int computeStringSize(final int fieldNumber, final String value) {
		return computeTagSize(fieldNumber) + computeStringSizeNoTag(value);
	}

	public static int computeUint32Size(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeUint32SizeNoTag(value);
	}

	public static int computeEnumSize(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
	}

	public static int computeSfixed32Size(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeSfixed32SizeNoTag(value);
	}

	public static int computeSfixed64Size(final int fieldNumber, final long value) {
		return computeTagSize(fieldNumber) + computeSfixed64SizeNoTag(value);
	}

	public static int computeSint32Size(final int fieldNumber, final int value) {
		return computeTagSize(fieldNumber) + computeSint32SizeNoTag(value);
	}

	public static int computeSint64Size(final int fieldNumber, final long value) {
		return computeTagSize(fieldNumber) + computeSint64SizeNoTag(value);
	}

	public static int computeDoubleSizeNoTag(final double value) {
		return LITTLE_ENDIAN_64_SIZE;
	}

	public static int computeFloatSizeNoTag(final float value) {
		return LITTLE_ENDIAN_32_SIZE;
	}

	public static int computeUint64SizeNoTag(final long value) {
		return computeRawVarint64Size(value);
	}

	public static int computeInt64SizeNoTag(final long value) {
		return computeRawVarint64Size(value);
	}

	public static int computeRawVarint64Size(final long value) {
		if ((value & (0xffffffffffffffffL << 7)) == 0)
			return 1;
		if ((value & (0xffffffffffffffffL << 14)) == 0)
			return 2;
		if ((value & (0xffffffffffffffffL << 21)) == 0)
			return 3;
		if ((value & (0xffffffffffffffffL << 28)) == 0)
			return 4;
		if ((value & (0xffffffffffffffffL << 35)) == 0)
			return 5;
		if ((value & (0xffffffffffffffffL << 42)) == 0)
			return 6;
		if ((value & (0xffffffffffffffffL << 49)) == 0)
			return 7;
		if ((value & (0xffffffffffffffffL << 56)) == 0)
			return 8;
		if ((value & (0xffffffffffffffffL << 63)) == 0)
			return 9;
		return 10;
	}

	public static int computeInt32SizeNoTag(final int value) {
		if (value >= 0) {
			return computeRawVarint32Size(value);
		} else {
			// Must sign-extend.
			return 10;
		}
	}

	public static int computeFixed64SizeNoTag(final long value) {
		return LITTLE_ENDIAN_64_SIZE;
	}

	public static int computeFixed32SizeNoTag(final int value) {
		return LITTLE_ENDIAN_32_SIZE;
	}

	public static int computeBoolSizeNoTag(final boolean value) {
		return 1;
	}

	public static int computeStringSizeNoTag(final String value) {
		try {
			final byte[] bytes = value.getBytes("UTF-8");
			return computeRawVarint32Size(bytes.length) + bytes.length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 not supported.", e);
		}
	}

	public static int computeUint32SizeNoTag(final int value) {
		return computeRawVarint32Size(value);
	}

	public static int computeEnumSizeNoTag(final int value) {
		return computeRawVarint32Size(value);
	}

	public static int computeSfixed32SizeNoTag(final int value) {
		return LITTLE_ENDIAN_32_SIZE;
	}

	public static int computeSfixed64SizeNoTag(final long value) {
		return LITTLE_ENDIAN_64_SIZE;
	}

	public static int computeSint32SizeNoTag(final int value) {
		return computeRawVarint32Size(encodeZigZag32(value));
	}

	public static int computeSint64SizeNoTag(final long value) {
		return computeRawVarint64Size(encodeZigZag64(value));
	}
}
