package com.google.code.proto.memless;

import java.util.ArrayList;
import java.util.List;

final class ProtobufMessage {

	private String name;
	private String fullyClarifiedName;

	private final List<ProtobufMessage> nestedMessages = new ArrayList<ProtobufMessage>();
	private final List<ProtobufEnum> enums = new ArrayList<ProtobufEnum>();
	private final List<ProtobufField> fields = new ArrayList<ProtobufField>();

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

	public List<ProtobufMessage> getNestedMessages() {
		return nestedMessages;
	}

	public List<ProtobufEnum> getEnums() {
		return enums;
	}

	public void addNestedMessage(ProtobufMessage message) {
		nestedMessages.add(message);
	}

	public void addEnum(ProtobufEnum curEnum) {
		enums.add(curEnum);
	}

	public void addField(ProtobufField field) {
		fields.add(field);
	}

	public List<ProtobufField> getFields() {
		return fields;
	}

	@Override
	public String toString() {
		return name;
	}
}
