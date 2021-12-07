#/bin/bash

cd /home/pi/rosita
pkill -f serialCom.py
python3 /home/pi/rosita/rosita-java/scripts/serialCom.py > /home/pi/rosita/arduinoOut &

cd /home/pi/rosita/rosita-java
java -jar ./target/rosita-jar-with-dependencies.jar

$SHELL
