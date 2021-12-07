package fr.didier.lights;

import java.util.Random;

import fr.didier.utils.PropertiesUtil;
import java.awt.Color;

/*#define NB_BARS 13

#define FACE_FRONT  0
#define FACE_LEFT   1
#define FACE_RIGHT  2
#define FACE_UP     3
#define FACE_BACK   4

#define NB_SNAKES_MAX   20*/

public class StripManager extends AbstractLight{

	// front
	private static int LINE_FRONT_BOTTOM_LEFT_START = 0; //
	private static int LINE_FRONT_BOTTOM_LEFT_END = 11;
	private static int LINE_FRONT_UP_LEFT_START = 12; // 1
	private static int LINE_FRONT_UP_LEFT_END = 36;
	private static int LINE_FRONT_UP_RIGHT_START = 37; // 2
	private static int LINE_FRONT_UP_RIGHT_END = 61;
	private static int LINE_FRONT_BOTTOM_RIGHT_START = 62; // 3
	private static int LINE_FRONT_BOTTOM_RIGHT_END = 73;
	private static int LINE_FRONT_MIDDLE_DOWN_START = 74; // 4
	private static int LINE_FRONT_MIDDLE_DOWN_END = 78;
	private static int LINE_FRONT_MIDDLE_UP_START = 79; // 5
	private static int LINE_FRONT_MIDDLE_UP_END = 87;

	// Left
	private static int LEFT_DOWN_START = 88; // 6
	private static int LEFT_DOWN_END = 113;

	// Back
	private static int BACK_START = 114; // 7
	private static int BACK_END = 143;

	// Left
	private static int LEFT_UP_START = 144; // 8
	private static int LEFT_UP_END = 169;

	// Up
	private static int UP_LEFT_START = 170; // 9
	private static int UP_LEFT_END = 190;
	private static int UP_RIGHT_START = 191; // 10
	private static int UP_RIGHT_END = 211;

	// Right
	private static int RIGHT_UP_START = 212; // 11
	private static int RIGHT_UP_END = 237;
	private static int RIGHT_DOWN_START = 238; // 12
	private static int RIGHT_DOWN_END = 264;

	long lastModified;
	int ledNumbers = 265;

	Random rand = new Random();

	public void update(String cmd) {
	}

	public void process() {
	}


	private void fade(long delay, Color color) {
		if (PropertiesUtil.isTimeOut(lastModified, delay)) {
			for (int i = 0; i < ledNumbers; i++) {
				updateLed(i, color);
			}
			lastModified = System.currentTimeMillis();
		}
	}

	private void random(long delay) {
		if (PropertiesUtil.isTimeOut(lastModified, delay)) {
			// int led = rand.nextInt(ledNumbers);
			updateLed(rand.nextInt(ledNumbers), new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			lastModified = System.currentTimeMillis();
		}
	}

	private void testBand(long delay) {
		
	}
}
