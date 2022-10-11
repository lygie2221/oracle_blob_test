Build:
mvn clean compile assembly:single

Test:
java -jar OracleBlob-1.0-jar-with-dependencies.jar \
jdbc:oracle:thin:@//localhost:1521/XEPDB1 \
<username> <pass> PHL_BLOB pruefid 42 PHL_BLOB ../bin/upload_test