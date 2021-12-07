package fr.didier.move;

import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;

import fr.didier.utils.PropertiesUtil;

public class Head extends AbstratGpio {
	private static final int PIN_PWM_HEAD = 12;
	private static final int INCREMENT_STEP = 1;
	int cmdHead;
	int currentPos = 95;
	Context pi4j;
	Pwm pwmHead = pi4j.create(buildPwmConfig(pi4j, PIN_PWM_HEAD,"head"));
	long lastModified;
	long period = 200;

	public Head(Context pi4j) {
		this.pi4j = pi4j;
		pwmHead.on(currentPos, FREQUENCY);
	}

	public void update(String cmd) {
		// TODO check servo range
		cmdHead = mapRange(FIRST_CHAR_NB, 126, 0, 99, cmd.charAt(2));
		lastModified = System.currentTimeMillis();
	}

	public void process() {
		if (PropertiesUtil.isTimeOut(lastModified, period)) {
			incrementPWM(pwmHead, currentPos, cmdHead, INCREMENT_STEP);
		}
	}

}
