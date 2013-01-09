package com.google.code.proto.gcless;

import java.util.Properties;

final class GeneratorConfiguration {

	private final boolean interfaceBased;
	private final boolean generateStaticFields;
	private final boolean generateListHelpers;
	private final boolean generateChaining;
	private final String messageExtendsClass;
	private final boolean generateToString;
    private final boolean generateGsonAnnotations;
    private final boolean generateSerializer;

	GeneratorConfiguration(Properties props) {
		String interfaceBased = props.getProperty("interface.based");
        this.interfaceBased = interfaceBased != null && interfaceBased.equals("true");

		if (!this.interfaceBased) {
			String genateStaticFields = props.getProperty("generate.static.fields");
            generateStaticFields = genateStaticFields != null && genateStaticFields.equals("true");
		} else {
			generateStaticFields = false;
		}

		String generateRepeatedHelpers = props.getProperty("generate.list.helpers");
        this.generateListHelpers = generateRepeatedHelpers != null && generateRepeatedHelpers.equals("true");

        String generateChaining = props.getProperty("generate.chaining");
        this.generateChaining = generateChaining != null && generateChaining.equals("true");

        this.messageExtendsClass = props.getProperty("message.extends.class");


        String generateToStringStr = props.getProperty("generate.tostring");
        this.generateToString = generateToStringStr != null && generateToStringStr.equals("true");

        String generateGson = props.getProperty("generate.gson");
        this.generateGsonAnnotations = generateGson != null && generateGson.equals("true");

        String generateSerializer = props.getProperty("generate.serializer");
        this.generateSerializer = generateSerializer != null && generateSerializer.equals("true");
	}

	public boolean isGenerateToString() {
		return generateToString;
	}

	public String getMessageExtendsClass() {
		return messageExtendsClass;
	}

	public boolean isGenerateChaining() {
		return generateChaining;
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

    public boolean isGenerateGsonAnnotations() {
        return generateGsonAnnotations;
    }

    public boolean isGenerateSerializer() {
        return generateSerializer;
    }
}
