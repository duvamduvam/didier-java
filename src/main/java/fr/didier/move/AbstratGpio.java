package fr.didier.move;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;

public class AbstratGpio {

	protected static final int FIRST_CHAR_NB = 33;
	protected static final int FREQUENCY = 50;

	protected static PwmConfig buildPwmConfig(Context pi4j, int address, String name) {
		return Pwm.newConfigBuilder(pi4j).id("BCM" + address).name(name).address(address).pwmType(PwmType.HARDWARE)
				.provider("pigpio-pwm").initial(0).shutdown(0).build();
	}

	protected int mapRange(int a1, int a2, int b1, int b2, int s) {
		return b1 + ((s - a1) * (b2 - b1)) / (a2 - a1);
	}

	protected void setDigitalState(boolean direction, DigitalOutput digitalOutput) {
		if (direction) {
			digitalOutput.state(DigitalState.HIGH);
		} else {
			digitalOutput.state(DigitalState.LOW);
		}
	}

	protected int incrementPWM(Pwm pwm, int currentPwm, int nextPwm, int incrementStep) {
		if (currentPwm != nextPwm) {
			if (nextPwm > currentPwm) {
				currentPwm = currentPwm + incrementStep;
			} else {
				currentPwm = currentPwm - incrementStep;
			}
			pwm.on(currentPwm, FREQUENCY);
		}
		return currentPwm;
	}


}
