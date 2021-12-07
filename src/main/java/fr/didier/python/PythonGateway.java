package fr.didier.python;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.didier.lights.Led;
import fr.didier.lights.Strip;
import py4j.GatewayServer;

public class PythonGateway {

    List<PythonListener> listeners = new ArrayList<PythonListener>();
    Random rand = new Random(); 

    private Strip strip;
    
    public PythonGateway() {
    	
        GatewayServer server = new GatewayServer(this);
        server.start(true);
    	
    	/*strip = new Strip();
    	byte b = 100;
    	strip.push(new Led(b,b,b));
    	b = 111;
    	strip.push(new Led(b,b,b));*/
    }
    
    public void addStrip() {
    	Integer integer = rand.nextInt(100);
    	byte b = integer.byteValue();
    	strip.push(new Led(b,b,b));
    	integer = rand.nextInt(100);
    	b = integer.byteValue();
    	strip.push(new Led(b,b,b));
    }

    public void registerListener(PythonListener listener) {
        listeners.add(listener);
    }

    public void notifyAllListeners() {
        for (PythonListener listener: listeners) {
            Object returnValue = listener.notify(this);
            System.out.println(returnValue);
        }
    }

    public Strip getStrip() {
        return strip;
    }
    
    @Override
    public String toString() {
        return "<ListenerApplication> instance";
    }

    public static void main(String[] args) {
        PythonGateway application = new PythonGateway();
        
        GatewayServer server = new GatewayServer(application);
        server.start(true);
        
        while(true) {
        	try {        		
        		application.notifyAllListeners();
        		application.addStrip();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }
}