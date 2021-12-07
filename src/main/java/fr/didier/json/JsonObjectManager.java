package fr.didier.json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectManager {

	private static final Logger LOGGER = Logger.getLogger(JsonObjectManager.class);
	protected static ObjectMapper mapper = new ObjectMapper();

	private final String testPath = "src/main/resources";
	File file = new File(testPath);
	String jsonAudioPath = file.getAbsolutePath() + "/audios.json";

	private List<Audio> audios;

	public JsonObjectManager() {
		getAudioList();
	}

	private String getJsonString(String path) {
		try {
			return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error(e);
			return null;
		}
	}

	public List<Audio> getAudioList() {
		return getAudioList(jsonAudioPath);
	}

	public List<Audio> getAudioList(String path) {
		if (audios != null) {
			return audios;
		}
		try {
			String jsonContent = getJsonString(path);
			audios = mapper.readValue(jsonContent, new TypeReference<List<Audio>>() {
			});
			return audios;
		} catch (JsonProcessingException e) {
			LOGGER.error(e);
			return null;
		}
	}

	public Audio getAudioByKey(String key) {
		for (Audio audio : audios) {
			if (audio.getKeys().contains(key)) {
				return audio;
			}
		}
		LOGGER.error("no audio for key : " + key);
		return null;
	}

}
