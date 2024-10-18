# UI Automation Testing with Selenium
## Introduction
This project automates UI test cases for the Rahul Shetty Academy Automation Practice website using Selenium and JUnit.

## Features
Automated UI testing for multiple scenarios, including radio buttons, dropdowns, alerts, and more.
Assertions to verify expected outcomes without using print statements.

## Prerequisites
Ensure you have the following installed:
* Java Development Kit (JDK)
* Apache Maven
* ChromeDriver
* An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse

## Installation and Setup
* Clone the Repository
  ``` git clone git@github.com:AnushkaRawatZ/UITestingSelenium.git ```
* Navigate to the Repository
  ``` cd <path> ```
* Install Dependencies
  ``` mvn clean install ```

## Running Tests
Execute the tests with:
``` mvn test ```

## Project Structure
```
UITestingSelenium/
├── .idea/
│   ├── .gitignore
│   ├── encodings.xml
│   ├── misc.xml
│   ├── uiDesigner.xml
│   └── vcs.xml
├── drivers/
│   └── chromedriver
├── src/
│   └── test/
│       └── java/
│           └── features/
│               └── UITests.java
├── .gitignore
├── README.md
└── pom.xml
```

## Writing Tests
Tests in this project are written using JUnit and Selenium. Each test case is implemented as a method within the UITests.java file, with each method focusing on a specific UI interaction.

Each test typically includes the following components:
* **Setup and Teardown**: Methods annotated with @BeforeEach and @AfterEach to initialize and clean up the WebDriver instance.
* **Assertions**: Using JUnit assertions to verify expected outcomes.
### Example Test Case:
``` java
    @Test
    public void testCheckbox() throws InterruptedException {
        WebElement checkbox1 = driver.findElement(By.id("checkBoxOption1"));
        checkbox1.click();
        assertTrue(checkbox1.isSelected(), "Checkbox should be checked");
        Thread.sleep(2000);
    }
```
## Execution Result
Reports are generated in the console output when tests are executed. You can see a summary of passed and failed tests directly in your terminal.

