# FNB-PTAF (FNB Playwright Test Automation Framework)

## Project Overview

**FNB-PTAF** is a test automation framework that leverages Playwright for browser automation and Cucumber for Behavior-Driven Development (BDD). The project is designed to provide a robust and scalable solution for automated testing of web applications.

## Project Structure

### 1. **`pom.xml`**

The `pom.xml` file is used by Maven to manage project dependencies and build configurations.

**Key Sections:**
- **`<properties>`**: Defines project properties like source encoding and Java version.
- **`<dependencies>`**: Lists libraries and frameworks used in the project.
- **`<build>`**: Configures the build process and plugins.

### 2. **`com.fnb_ptaf.Hooks`**

The `Hooks` class manages the setup and teardown of the browser session for Cucumber tests.

**Key Methods:**
- **`setUp()`**: Initializes the browser and page objects before each test scenario.
- **`tearDown(Scenario scenario)`**: Takes a screenshot if a scenario fails and closes the browser.
- **`getPage()`**: Returns the Playwright `Page` object for use in test steps.

### 3. **`com.fnb_ptaf.BrowserFactory`**

The `BrowserFactory` class is responsible for creating browser instances based on the specified browser type.

**Key Methods:**
- **`createBrowser(BrowserTypeEnum browserTypeEnum)`**: Creates and returns a browser instance based on the given `BrowserTypeEnum`.

### 4. **`com.fnb_ptaf.CommonMethods`**

The `CommonMethods` class provides common actions that can be performed on web elements.

**Key Methods:**
- **`getElement(String element, String key)`**: Retrieves the CSS selector for a specified element from a YAML configuration file.
- **`enterValue(String element, String key, String value)`**: Enters a value into a specified web element.
- **`clickElement(String element, String key)`**: Clicks on a specified web element.
- **`clickByText(String element, String key)`**: Clicks on an element specified by its text content.
- **`getTitle()`**: Retrieves the title of the current page.

### 5. **`com.fnb_ptaf.ConfigurationProperties`**

The `ConfigurationProperties` class provides methods to retrieve configuration properties.

**Key Methods:**
- **`getBaseUrl(String URL)`**: Retrieves the base URL for the application from the YAML configuration.
- **`getBrowser()`**: Retrieves the browser type specified in the configuration.

### 6. **`com.fnb_ptaf.YamlReader`**

The `YamlReader` class reads and parses YAML configuration files.

**Key Methods:**
- **`mergeData(Map<String, Object> base, Map<String, Object> newData)`**: Merges new data into the existing configuration data.
- **`get(String key)`**: Retrieves a value from the configuration data based on a key.

### 7. **`com.fnb_ptaf.runners.Landing_page_Runner`**

The `TestRunner` class configures and runs Cucumber tests with JUnit.

**Key Annotations:**
- **`@RunWith(Cucumber.class)`**: Specifies that the tests should be run with the Cucumber runner.
- **`@CucumberOptions`**: Configures Cucumber options, such as plugin settings and feature file locations.

### 8. **`com.fnb_ptaf.stepdefinitions.CommonSteps`**

The `LoginSteps` class contains step definitions for login-related test scenarios.

**Key Methods:**
- **`weNavigateToURL(String URL)`**: Navigates to a specified URL.
- **`weEnterKeyOnElement(String element, String key, String value)`**: Enters a value into a specified element.
- **`weClickOnElement(String element, String key)`**: Clicks on a specified element.
- **`weClickOnText(String element, String key)`**: Clicks on an element specified by its text content.

## Diagram

Here's a simplified diagram of the project structure:

    FNB-PTAF
    │
    ├── src
    │ ├── main
    │ │ └── java
    │ │ └── com
    │ │ └── doccenter
    │ │ ├── hooks
    │ │ │ └── Hooks.java
    │ │ ├── pages
    │ │ │ └── CommonMethods.java
    │ │ └── utils
    │ │ ├── BrowserFactory.java
    │ │ ├── ConfigurationProperties.java
    │ │ └── YamlReader.java
    │ └── test
    │ └── java
    │ └── com
    │ └── doccenter
    │ ├── runners
    │ │ └── TestRunner.java
    │ └── stepdefinitions
    │ └── LoginSteps.java
    └── pom.xml


## How to Pull the Code

1. **Clone the Repository**: Use Git to clone the repository to your local machine. Run the following command:
   ```bash
   [git clone https://github.com/your-repository-url/FNB-PTAF.git](https://github.com/azimovm202010/FNB-PTAF.git)
   
## How to Install
    1.    npm init -y
    2.    npx playwright cache clear
    3.    npm install playwright 

## How to Run
    1.    Navigate to Test Runner class src-->test-->java-->com-->doccenter-->runners-->TestRunner.java
    2.    Run TestRunner.Java Class

## How to Use

1. **Set Up**: Ensure you have Maven and JDK 11 installed on your machine.
2. **Build the Project**: Run `mvn clean install` to build the project and resolve dependencies.
3. **Run Tests**: Execute tests using `mvn test` to run Cucumber tests with the configured runner.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
