
install rxtx localy :
mvn install:install-file -Dfile=lib/RXTXcomm.jar -DgroupId=org.rxtx -DartifactId=rxtx -Dversion=2.2pre1 -Dpackaging=jar


run with
java -jar --module-path /home/david/softs/libraries/javafx-sdk-11.0.2/lib --add-modules=javafx.controls --add-modules=javafx.media target/rosita-jar-with-dependencies.jar 


in the java folder