package com.google.code.proto.memless;

import java.util.ArrayList;
import java.util.List;

final class ProtobufEnum {

	private String name;
	private String fullyClarifiedName;

	private List<EnumValue> values = new ArrayList<EnumValue>();

	public String getFullyClarifiedName() {
		return fullyClarifiedName;
	}

	public void setFullyClarifiedName(String fullyClarifiedName) {
		this.fullyClarifiedName = fullyClarifiedName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addValue(EnumValue value) {
		values.add(value);
	}

	public List<EnumValue> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return name;
	}
}
