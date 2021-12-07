#/bin/bash

cd /home/pi/rosita/rosita-java
git pull
git add -A
git commit -m "$*"
git push