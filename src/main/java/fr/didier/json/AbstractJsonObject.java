package fr.didier.json;

import fr.didier.utils.PropertiesUtil;

public class AbstractJsonObject {

	PropertiesUtil properties = new PropertiesUtil();
	String mediaFolder = properties.getMediaFolder();
	
	protected String getMediaFolder() {
		return mediaFolder;
	}
	
}
