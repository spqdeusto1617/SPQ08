mvn clean compile
mvn datanucleus:schema-delete
mvn datanucleus:schema-create
mvn exec:java -Pmockito
mvn test
