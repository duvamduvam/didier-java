package fr.didier.com;

import org.apache.log4j.Logger;

import com.pi4j.context.Context;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.StopBits;
import com.pi4j.util.Console;

import fr.didier.sound.Sound;

public class Rxtx {
	private static final Logger LOGGER = Logger.getLogger(Rxtx.class);
	private static final String SERIAL_ADDRESS = "/dev/ttyS0";
	Context pi4j;

	public Rxtx(Context pi4j) {
		this.pi4j = pi4j;
	}

	// Serial serial = pi4j.create(Serial.newConfigBuilder(pi4j));

	public void read() {

		Console console = new Console();
		// var pi4j = Pi4J.newAutoContext();

		Serial serial = pi4j.create(Serial.newConfigBuilder(pi4j).use_9600_N81().dataBits_8().parity(Parity.NONE)
				.stopBits(StopBits._1).flowControl(FlowControl.NONE).id("my-serial").device(SERIAL_ADDRESS)
				.provider("pigpio-serial").build());
		serial.open();

		// Wait till the serial port is open
		console.print("Waiting till serial port is open");
		while (!serial.isOpen()) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}

		// Start a thread to handle the incoming data from the serial port
		SerialReader serialReader = new SerialReader(console, serial);
		Thread serialReaderThread = new Thread(serialReader, "SerialReader");
		serialReaderThread.setDaemon(true);
		serialReaderThread.start();

		while (serial.isOpen()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				LOGGER.error(e);
			}
		}

		serialReader.stopReading();
	}

	/*
	 * void connect( String portName ) throws Exception { CommPortIdentifier
	 * portIdentifier = CommPortIdentifier .getPortIdentifier( portName ); if(
	 * portIdentifier.isCurrentlyOwned() ) { System.out.println(
	 * "Error: Port is currently in use" ); } else { int timeout = 2000; CommPort
	 * commPort = portIdentifier.open( this.getClass().getName(), timeout );
	 * 
	 * if( commPort instanceof SerialPort ) { SerialPort serialPort = ( SerialPort
	 * )commPort; serialPort.setSerialPortParams( 57600, SerialPort.DATABITS_8,
	 * SerialPort.STOPBITS_1, SerialPort.PARITY_NONE );
	 * 
	 * InputStream in = serialPort.getInputStream(); OutputStream out =
	 * serialPort.getOutputStream();
	 * 
	 * ( new Thread( new SerialReader( in ) ) ).start(); ( new Thread( new
	 * SerialWriter( out ) ) ).start();
	 * 
	 * } else { System.out.println(
	 * "Error: Only serial ports are handled by this example." ); } } }
	 * 
	 * public static class SerialReader implements Runnable {
	 * 
	 * InputStream in;
	 * 
	 * public SerialReader( InputStream in ) { this.in = in; }
	 * 
	 * public void run() { byte[] buffer = new byte[ 1024 ]; int len = -1; try {
	 * while( ( len = this.in.read( buffer ) ) > -1 ) { System.out.print( new
	 * String( buffer, 0, len ) ); } } catch( IOException e ) { e.printStackTrace();
	 * } } }
	 * 
	 * public static class SerialWriter implements Runnable {
	 * 
	 * OutputStream out;
	 * 
	 * public SerialWriter( OutputStream out ) { this.out = out; }
	 * 
	 * public void run() { try { int c = 0; while( ( c = System.in.read() ) > -1 ) {
	 * this.out.write( c ); } } catch( IOException e ) { e.printStackTrace(); } } }
	 * 
	 * public static void main( String[] args ) { try { ( new TwoWaySerialComm()
	 * ).connect( "/dev/ttyUSB0" ); } catch( Exception e ) { e.printStackTrace(); }
	 * }
	 */
}