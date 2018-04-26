## Requirements
1. Java JDK 8
2. Maven 3

## Libraries
1. CucumberJVM
2. Hamcrest
3. JUnit 4
4. Restassured
5. JsonPath
6. Cucumber Reports

## Test Executions
### Run Test
Using specific browser
Specify parameter on CLI -DbrowserName with browser name to run test.
Examples:

mvn test -DbrowserName=firefox
mvn test -DbrowserName=chrome

Using specific tag or scenario test

Examples:
mvn test -DbrowserName=chrome -Dcucumber.options="-t @userLogin"


