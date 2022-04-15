
*****************************
### Solution
*****************************
* This sample solution test framework automates the testing of REST API with endpoint: https://api.m3o.com/v1/user/ (can be extended for any different endpoint)
* Test cases cover functionality of creating, deleting and reading user
* As a test runner the Junit 5 framework is used with Java 8 SDK. It helps easily run and maintain Java tests.
* As an API testing tool RestAssured is in place. It is out of box REST client which provides a huge set of API testing capabilities.

In order to run tests Java 8 JRE and Maven build tool need to be installed.
Clone project and run command: mvn clean test
This will install all dependencies and run junit tests.
In order to generate Allure report run command: mvn allure:serve. 
Report will be generated into temp folder. Web server with results will start. 

Some improvements can be done:
 - Store token and other metadata in properties file inside resources folder
 - More generic test results check methods to avoid code duplication
 - BDD framework like Cucumber can be used to have features described in domain languages
 - Docker image can be build to avoid installing dependencies on host machine