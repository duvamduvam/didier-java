package fr.didier.json;

import java.util.List;

import org.apache.log4j.Logger;

public class Audio extends AbstractJsonObject {

	private static final Logger LOGGER = Logger.getLogger(Audio.class);

	private List<String> keys;
	private String name;
	private String extension;

	private static String AUDIO_FOLDER = "audios";

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPath() {
		return getMediaFolder() + "/" + name + "." + extension;
	}

}
