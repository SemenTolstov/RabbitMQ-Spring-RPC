#HOWTO run in Windows environment
1. Change connection string to database if file application.properties (dataService)
2. cd dto ./mvnw install cd..
3. cd dataService ./mvnw package cd..
4. cd userService ./mvnw package cd..
5. java -jar dataService/target/dataService-0.0.1-SNAPSHOT.jar
6. Open new cmd window
7. java -jar userService/target/userService--0.0.1-SNAPSHOT.jar
8. Use to check: Swagger: http://localhost:8080/swagger-ui/index.html
   Postman: http://localhost:8080/user