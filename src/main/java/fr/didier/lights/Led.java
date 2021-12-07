package fr.didier.lights;

public class Led {

	public Led(byte red, byte green, byte blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	byte red, green, blue;	
	
	public byte getRed() {
		return red;
	}

	public void setRed(byte red) {
		this.red = red;
	}

	public byte getGreen() {
		return green;
	}

	public void setGreen(byte green) {
		this.green = green;
	}

	public byte getBlue() {
		return blue;
	}

	public void setBlue(byte blue) {
		this.blue = blue;
	}

	
	
}
