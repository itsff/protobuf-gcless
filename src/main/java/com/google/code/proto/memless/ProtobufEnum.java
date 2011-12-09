package com.google.code.proto.memless;

import java.util.ArrayList;
import java.util.List;

final class ProtobufEnum {

	private String name;
	private List<EnumValue> values = new ArrayList<EnumValue>();

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
