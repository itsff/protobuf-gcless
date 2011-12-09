package com.google.code.proto.memless;

class ProtobufField {

	private String name;
	private String type;
	private String nature;
	private long tag;
	private String defaults;
	private boolean isDeprecated;
	private boolean isPacked;

	//TODO comments, deprecated &etc
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getTag() {
		return tag;
	}

	public boolean isDeprecated() {
		return isDeprecated;
	}

	public void setDeprecated(boolean isDeprecated) {
		this.isDeprecated = isDeprecated;
	}

	public boolean isPacked() {
		return isPacked;
	}

	public void setPacked(boolean isPacked) {
		this.isPacked = isPacked;
	}

	public void setTag(long tag) {
		this.tag = tag;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public String getDefaults() {
		return defaults;
	}
	
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}

}
