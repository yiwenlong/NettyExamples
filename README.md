# How to run these netty examples.
## 1. Build & package executable jars.
```shell
mvn clean package -Dmaven.javadoc.skip=true
```

## 2. Run the executable jars

### 2.1 LogEvent

You should run the following command in a terminal:

```shell
java -jar ./LogEvent/Monitor/target/LogEvent.Monitor-1.0-SNAPSHOT-jar-with-dependencies.jar 7788
```

Run the following command in another terminal:

```shell
# You should replace <logfile path> with you real log file.
java -jar ./LogEvent/Monitor/target/LogEvent.Monitor-1.0-SNAPSHOT-jar-with-dependencies.jar 7788 <logfile path>
```

