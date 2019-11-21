#luua angular klassid kausta  ../server/src/main/resources/static (kaust on määratud angular.json failis )
# web kataloogist
ng build

#luua fat jar
# rakenduse põhikaustast
mvn clean install

#Küivitamiseks põhikaustast
java -jar server/target/server-1.0-spring-boot.jar

# Avada browseris
http://localhost:8080/