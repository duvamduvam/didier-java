package fr.didier;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.library.pigpio.PiGpio;

import fr.didier.com.Rxtx;
import fr.didier.lights.StripManager;
import fr.didier.move.Head;
import fr.didier.move.Wheel;
import fr.didier.python.PythonGateway;
import fr.didier.sound.Sound;

public class Didier {

    Logger logger = LoggerFactory.getLogger(Didier.class);

    final PiGpio piGpio = PiGpio.newNativeInstance();
    
    Context pi4j = Pi4J.newAutoContext();
	/*Context pi4j = Pi4J.newContextBuilder()
            .noAutoDetect()
            .add(new CrowPiPlatform())
            .add(
                PiGpioDigitalInputProvider.newInstance(piGpio),
                PiGpioDigitalOutputProvider.newInstance(piGpio),
                PiGpioPwmProvider.newInstance(piGpio),
                PiGpioI2CProvider.newInstance(piGpio),
                PiGpioSerialProvider.newInstance(piGpio),
                PiGpioSpiProvider.newInstance(piGpio)
            )
            .build();*/
    
    
    /*Context pi4j = Pi4J.newContextBuilder()
    		   .add(new MockPlatform())
    		   .add(MockAnalogInputProvider.newInstance(),
    		      MockAnalogOutputProvider.newInstance(),
    		      MockSpiProvider.newInstance(),
    		      MockPwmProvider.newInstance(),
    		      MockSerialProvider.newInstance(),
    		      MockI2CProvider.newInstance(),
    		      MockDigitalInputProvider.newInstance(),
    		      MockDigitalOutputProvider.newInstance())*/
    		   //.add(new MyCustomADCProvider(/* implements AnalogInputProvider, id="my-adc-prov" */))
    		   //.add(new MyCustomSPIProvider(/* implements SpiProvider, id="my-spi-prov" */))
    		   //.build();
    
	Head head = new Head(pi4j);
	Wheel wheel = new Wheel(pi4j);
	Sound sound = new Sound();
	Rxtx rxtx = new Rxtx(pi4j);

	StripManager strip = new StripManager();

	PythonGateway pythonGateway = new PythonGateway();

	protected void start() {
		sound.start();
		run();
	}

	private void run() {
		while (true) {
			rxtx.read();

			String cmd = "";

			if (cmd != null) {
				wheel.update(cmd);
				head.update(cmd);
				strip.update(cmd);
			}

			wheel.process();
			head.process();
			strip.process();
			
			pythonGateway.addStrip();
			
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Didier().start();
			}
		});
	}

}
