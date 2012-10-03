package com.google.code.proto.gcless;

public class GenerateUnittest {
	public static void main(String[] args) throws Exception {
		MemlessGenerator.main(new String[] { "src/test/java", "src/test/resources/unittest.proto" });
	}
}
