# Holiday Service

Holiday Service developed with Spring Boot.

Service is providing next shared holiday for provided date and two country codes.
Holidays are searched only in provided year.

## External Holiday Api

Project is using external holiday api: [https://calendarific.com/](https://calendarific.com/)

## Configuration

External api key should be replaced in location

>
	src/main/resources/application.properties

> 
	calendarific.apiKey=xxx

## How to run

### Using Executable Jar

To create an executable jar run:

>
	mvn clean package
	
To run that application, use the java -jar command, as follows:

>
	java -jar target/holiday-service-0.0.1-SNAPSHOT.jar
	
## Example
	
[http://localhost:8080/holiday?countryCode1=us&countryCode2=pl&date=2021-04-24](http://localhost:8080/holiday?countryCode1=us&countryCode2=pl&date=2021-04-24)

{
"date": "2021-05-01",
"name1": "Orthodox Holy Saturday",
"name2": "Labor Day / May Day"
}
