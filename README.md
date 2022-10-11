Build:
mvn clean compile assembly:single

Test:
java -jar OracleBlob-1.0-jar-with-dependencies.jar <username> <pass> 42 ../bin/upload_test