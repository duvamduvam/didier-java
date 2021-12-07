package fr.didier.sound;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.log4j.Logger;

import fr.didier.json.Audio;
import fr.didier.json.JsonObjectManager;
import fr.didier.utils.PropertiesUtil;

public class Sound extends Thread {
	private static final Logger LOGGER = Logger.getLogger(Sound.class);	
	
	
	Clip clip;

	// current status of clip
	String status;

	JsonObjectManager jsonObjectManager = new JsonObjectManager();
	AudioInputStream audioInputStream;

	public void play(String key) {

		Audio audio = jsonObjectManager.getAudioByKey(key);
		if(audio==null) {
			LOGGER.error("audio not found by key "+key);
			return;
		}
		try {
			LOGGER.info("play audio : " + audio.getPath());
			
			audioInputStream = AudioSystem.getAudioInputStream(new File(audio.getPath()).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);	
			clip.start();
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			LOGGER.error(e);
		}
	}

	public void playSequence(List<Integer> song) {
		// TODO Auto-generated method stub

	}

	public void stop(int song) {
		// TODO Auto-generated method stub

	}

	public void pause(int song) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
