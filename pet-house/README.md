#How to compile and run the main class

http://localhost:8080
http://localhost:8080/cats

Move the home.html file to templates folder under src/main/templates

check all process:
ps -fea | grep java

kill the process:
kill -9 22317


mvn clean

mvn package

mvn spring-boot:run

mvn package assembly:single  


Test save Cat

```
curl -H 'Content-Type: application/json' -d '{ "id":"6", "name":"Bilibii", "age": "1", "breed":"Calico", "description":"Available" }' -X POST http://localhost:8080/saveCat
```




