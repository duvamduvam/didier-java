package fr.didier.move;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmType;

import fr.didier.utils.PropertiesUtil;

public class Wheel extends AbstratGpio {

	private static final int PIN_PWM_LEFT = 17;
	private static final int PIN_DIR_LEFT = 27;
	private static final int PIN_PWM_RIGHT = 22;
	private static final int PIN_DIR_RIGHT = 23;

	private static final int INCREMENT_STEP = 2;
	private static final int MAX_PWM_WHEEL = 50;

	int cmdLeft, cmdRight, currentLeft, currentRight = 0;
	long lastModified;
	long period = 200;

	Context pi4j;

	Pwm pwmLeft = pi4j.create(buildPwmConfig(pi4j, PIN_PWM_LEFT, PwmType.SOFTWARE, "wheel left"));
	Pwm pwmRight = pi4j.create(buildPwmConfig(pi4j, PIN_PWM_RIGHT, PwmType.SOFTWARE, "wheel right"));

	DigitalOutput dirLeft = pi4j.dout().create(PIN_DIR_LEFT);
	DigitalOutput dirRight = pi4j.dout().create(PIN_DIR_RIGHT);

	public Wheel(Context pi4j) {
		this.pi4j = pi4j;
	}

	public void update(String cmd) {

		cmdLeft = mapRange(FIRST_CHAR_NB, 126, -MAX_PWM_WHEEL, MAX_PWM_WHEEL, cmd.charAt(3));
		setDigitalState(cmdLeft > 0, dirLeft);
		cmdRight = mapRange(FIRST_CHAR_NB, 126, -MAX_PWM_WHEEL, MAX_PWM_WHEEL, cmd.charAt(4));
		setDigitalState(cmdRight > 0, dirRight);
		lastModified = System.currentTimeMillis();

	}

	public void process() {
		if (PropertiesUtil.isTimeOut(lastModified, period)) {
			incrementPWM(pwmLeft, currentLeft, cmdLeft, INCREMENT_STEP);
			incrementPWM(pwmRight, currentRight, cmdRight, INCREMENT_STEP);
		} else
			stop();

	}

	public void stop() {
		pwmLeft.off();
		pwmRight.off();

	}

}
