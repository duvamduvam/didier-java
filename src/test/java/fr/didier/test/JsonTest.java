package fr.didier.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import fr.didier.json.Audio;
import fr.didier.json.JsonObjectManager;

public class JsonTest {

	private final String testPath = "src/test/resources";
	File file = new File(testPath);
	String jsonAudioPath = file.getAbsolutePath()+"/audios.json";
	JsonObjectManager objectGenerator = new JsonObjectManager();

	private static final Logger LOGGER = Logger.getLogger(JsonTest.class);

	@Test
	public void getAudio() throws IOException {

		List<Audio> audio = objectGenerator.getAudioList(jsonAudioPath);
		Assert.assertEquals(audio.get(0).getName(), "bonjour");

	}

}
