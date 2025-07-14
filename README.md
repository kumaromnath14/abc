# Selenium Page Object Model Framework

This is a test automation framework built using Selenium WebDriver with Java, following the Page Object Model design pattern.

## Framework Features
- Page Object Model design pattern
- TestNG for test execution and assertions
- Maven for dependency management and build automation
- WebDriverManager for automated driver management
- Support for parallel test execution
- Cross-browser testing support (Chrome and Firefox)

## Project Structure
```
selenium-pom-framework/
├── src/
│   ├── main/java/
│   │   └── com/test/framework/
│   │       ├── pages/
│   │       │   ├── BasePage.java
│   │       │   └── LoginPage.java
│   │       └── utils/
│   │           └── DriverManager.java
│   └── test/java/
│       └── com/test/framework/
│           ├── BaseTest.java
│           └── LoginTest.java
├── pom.xml
└── testng.xml
```

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome or Firefox browser installed

## Running Tests
To run the tests, use the following Maven command:
```bash
mvn clean test
```

## Configuration
- Browser selection is configured in the `testng.xml` file
- Default timeout settings can be modified in `BasePage.java`
- Test parallel execution settings are in `testng.xml`
