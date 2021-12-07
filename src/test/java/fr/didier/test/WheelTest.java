package fr.didier.test;

import org.junit.Test;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;

import fr.didier.move.Wheel;

public class WheelTest {
	
	Context pi4j = Pi4J.newAutoContext();

	@Test 
	public void testForward() {
		Wheel wheel = new Wheel(pi4j);
		//wheel.process(null);
	}
	
	@Test 
	public void testStop() {
		Wheel wheel = new Wheel(pi4j);
		wheel.stop();
	}
	
}
