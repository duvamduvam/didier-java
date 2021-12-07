package fr.didier.test;

import org.junit.Test;

import fr.didier.sound.Sound;

public class AudioTest{

	@Test
	public void play2() {
		Sound sound = new Sound();
		sound.play("C1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
	}


}
