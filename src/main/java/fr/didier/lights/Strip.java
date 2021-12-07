package fr.didier.lights;

import java.util.LinkedList;
import java.util.List;

public class Strip {

	List<Led> leds = new LinkedList<Led>();

	public int getSize() {
		return leds.size();
	}
	
	public Strip() {
		super();
	}
	
	public Strip(List<Led> leds) {
		super();
		this.leds = leds;
	}
	
	public List<Led> getInternalList() {
		return leds;
	}

    public void pushAll(List<Led> elements) {
        for (Led element : elements) {
            this.push(element);
        }
    }
	
	public void setLeds(List<Led> leds) {
		this.leds = leds;
	}
	
    public void push(Led led) {
    	leds.add(led);
    }

    public Led pop() {
        return leds.remove(0);
    }
	
	
}
