#!/usr/bin/env python3
import serial
import os.path

ports= ['ttyACM0', 'ttyACM1', 'ttyACM2',  'ttyACM3'] 

portPrefix = '/dev/'
if __name__ == '__main__':
    port = ''
    for p in ports:
        try:
            with open(portPrefix+p) as f:
                print('arduino port '+p)
                port = p
                break

        except IOError:
            print("File not accessible")
        
        #if os.path.isfile(portPrefix+p):
         #   print('arduino port'+p)
           # port = p

    if (port == ''):
        print('can t find arduino port')
    else:
        ser = serial.Serial(portPrefix+port, 115200, timeout=1)
        ser.flush()
        print('start listening arduino port')
        
        # Append-adds at last 


        
        while True:
            if ser.in_waiting > 0:
                line = ser.readline().decode('utf-8').rstrip()
                print(line)
                file1 = open("arduinoOut", "a")  # append mode 
                file1.write(line+"\n") 
                file1.close() 
