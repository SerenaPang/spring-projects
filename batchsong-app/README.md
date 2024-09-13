
# Spring JDBC Batch

This project shows how to use Spring JDBC Batch.

It compares batch vs non-batch inserts.

#How to create a maven project

```
mvn archetype:generate -DgroupId=com.batchsong.app -DartifactId=batchsong-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

```

## How to build and run Spring Boot

```
 # Command to build the project.
 $ mvn package

 # Command to start spring boot.
 $ mvn spring-boot:run
```

Or alternatively, you can run

```
 # Generates jar file with dependencies.
 $ mvn package assembly:single

 # Executes the main class.
 $java -cp target/batchsong-app-1.0-SNAPSHOT.jar com.batchsong.app.App
 
 java -cp target/batchsong-app-1.0-SNAPSHOT-jar-with-dependencies.jar com.batchsong.app.SpringJdbcBatchPerformanceApplication

```





