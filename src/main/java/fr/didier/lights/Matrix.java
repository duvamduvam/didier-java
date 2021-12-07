package fr.didier.lights;

import java.awt.Color;
import java.util.List;

import com.pi4j.io.pwm.Pwm;

public class Matrix extends AbstractLight{

	public void updateMatrix(Pwm pwm, List<Color> colors) {
		int i = 0;
		for(Color color: colors){
			updateLed(i, color);
			i++;
		}
	}
	
}
