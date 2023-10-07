# Java Coding Test

### Required Software

* At least Java 17 for compile
* At least Maven 3.6 for build
* At least NodeJs v20.8.0
* At least npm 10.1.0
* H2 in-memory database


### Project Structure
* java-coding-test-api is a spring boot based for backend API application
* java-coding-test-web is a ReactJS based frontend application

### Build + Run Instructions in locally

#### java-coding-test-api
* open a terminal and go to `java-coding-test/java-coding-test-api` folder
* run `mvn clean install`
* run `mvn spring-boot:run`
* open `http://localhost:8080/swagger-ui/index.html` in the browser

#### java-coding-test-frontend
* open a terminal and go to `java-coding-test/java-coding-test-web` folder
* run `npm ci`
* run `npm run build`
* run `npm run start`
* open `http://localhost:3000` in the browser

### Testing
* Unit tests are written in JUnit for `java-coding-test-api` project
    * open a terminal and go to `java-coding-test/java-coding-test-api` folder
    * run `mvn test` to execute all test cases