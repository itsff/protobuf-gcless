package com.google.code.proto.gcless;

import java.util.Properties;

final class GeneratorConfiguration {

	private final boolean interfaceBased;
	private final boolean generateStaticFields;
	private final boolean generateListHelpers;

	GeneratorConfiguration(Properties props) {
		String interfaceBased = props.getProperty("interface.based");
		if (interfaceBased != null && interfaceBased.equals("true")) {
			this.interfaceBased = true;
		} else {
			this.interfaceBased = false;
		}
		if (!this.interfaceBased) {
			String genateStaticFields = props.getProperty("generate.static.fields");
			if (genateStaticFields != null && genateStaticFields.equals("true")) {
				generateStaticFields = true;
			} else {
				generateStaticFields = false;
			}
		} else {
			generateStaticFields = false;
		}
		String generateRepeatedHelpers = props.getProperty("generate.list.helpers");
		if (generateRepeatedHelpers != null && generateRepeatedHelpers.equals("true")) {
			this.generateListHelpers = true;
		} else {
			this.generateListHelpers = false;
		}
	}

	public boolean isInterfaceBased() {
		return interfaceBased;
	}
	
	public boolean isGenerateListHelpers() {
		return generateListHelpers;
	}
	
	public boolean isGenerateStaticFields() {
		return generateStaticFields;
	}
}
