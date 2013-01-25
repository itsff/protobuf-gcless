package com.google.code.proto.gcless;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

final class GeneratorConfiguration {

	private final boolean interfaceBased;
	private final boolean generateStaticFields;
	private final boolean generateListHelpers;
	private final boolean generateChaining;
	private final String messageExtendsClass;
    private final String enumImplementsInterface;
    private final String gsonHelperPackage;
    private final String gsonEnumAdapter;
	private final boolean generateToString;
    private final boolean generateSerializer;
    private final String packageSuffix;
    private Map<String, String> optionMapping;

	GeneratorConfiguration(Properties props) throws Exception
    {
		optionMapping = new HashMap<String, String>();
		
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
        this.enumImplementsInterface = props.getProperty("enum.implements.interface");
        this.gsonHelperPackage = props.getProperty("gson.helper.package");
        this.gsonEnumAdapter = props.getProperty("gson.enum.adapter");
        this.packageSuffix = props.getProperty("generate.package.suffix");

        String generateToStringStr = props.getProperty("generate.tostring");
        this.generateToString = generateToStringStr != null && generateToStringStr.equals("true");

        String generateSerializer = props.getProperty("generate.serializer");
        this.generateSerializer = generateSerializer != null && generateSerializer.equals("true");
        
        for (String propertyName : props.stringPropertyNames())
        {
        	if (propertyName.startsWith("option.map."))
        	{
        		String optionName = propertyName.substring("option.map.".length());
        		String optionValue = props.getProperty(propertyName);
        		optionMapping.put(optionName, optionValue);
        	}
        }

        if (isNonEmpty(this.gsonHelperPackage))
        {
            // Ensure user specified enum base interface
            if (!isNonEmpty(this.enumImplementsInterface))
            {
                throw new Exception("You must specify enum.implements.interface with gson helper");
            }

            if (!isNonEmpty(this.gsonEnumAdapter))
            {
                throw new Exception("You must specify gson.enum.adapter with gson helper");
            }
        }
	}

    static boolean isNonEmpty(String str)
    {
        return str != null && !str.isEmpty();
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

    public boolean isGenerateSerializer() {
        return generateSerializer;
    }
    
    public String getOptionMapping(String key) {
    	return optionMapping.get(key);
    }

    public String getEnumImplementsInterface()
    {
        return enumImplementsInterface;
    }

    public String getGsonHelperPackage()
    {
        return gsonHelperPackage;
    }

    public String getGsonEnumAdapter()
    {
        return gsonEnumAdapter;
    }
    
    public String getPackageSuffix()
    {
    	return packageSuffix;
    }
}
