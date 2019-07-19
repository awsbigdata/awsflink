# awsflink
how to connect kinesis from flink in emr in java

Building Flink-kinesis dependency 

1. Cloned the git repo for flink-1.6. 
"git clone -b release-1.6 https://github.com/apache/flink.git"

2. Go to connector directory.

"cd flink/flink-connectors/flink-connector-kinesis/"

3. Build the jar
mvn clean package -Dmaven.test.skip=true -Dhadoop.version=2.8.4 -Daws.sdk.version=1.11.319 -Daws.kinesis-kcl.version=1.9.0 -Daws.kinesis-kpl.version=0.12.9 -Dcheckstyle.skip=true

4. Installed flink

"mvn clean install -Pinclude-kinesis -DskipTests -Dfast"
 
 OR

4. if it is differnet macine then copy the jar to local and install the jar using below command 

mvn install:install-file -Dfile=./flink-connector-kinesis_2.11-1.6-SNAPSHOT.jar -DgroupId=org.apache.flink -DartifactId=flink-connector-kinesis_2.11 -Dversion=1.6.0 -Dpackaging=jar
 
 Build Fat jar
 
 1. Clone the source 
 
 git clone https://github.com/awsbigdata/awsflink.git
 
 2. gradle clean build fatJar
 
 3.Copy the farJar to master (local -->s3-->emr master or scp)
 
 4.Run below command to consume kinesis 
 
flink run -m yarn-cluster -yn 2 -ys 2 -c com.awssupport.flink.kinesis.ConsumeFromKinesis awsflinkFat.jar --region [regionName]  -streamname [KinesisStreamName]
 
 
