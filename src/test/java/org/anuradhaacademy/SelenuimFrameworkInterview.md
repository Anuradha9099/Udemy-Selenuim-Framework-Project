question 1: what is the design pattern used?
Answer:

1. The primary design pattern used in this Selenium automation framework is the **Page Object Model (POM)**, in
   combination with **Page Factory**. The POM pattern helps create an object repository where web elements and page
   behaviors are centrally managed, making test scripts more readable, maintainable, and reusable. Specifically, the
   framework uses `PageFactory`—as seen in the `landingPage` class—to initialize and manage web elements with
   annotations (like `@FindBy`). This approach not only simplifies element initialization but also ensures all
   page-specific locators and interactions are encapsulated within their respective classes.

================================================================================================
Question2: how the utilities handle by your framework?
Answer:

Utilities in the framework are managed by creating dedicated utility classes or components that encapsulate reusable
actions, configurations, and helper methods. Additionally, the framework leverages inheritance to share common
functionality: essential utilities such as custom waits, browser setup, or file handling (like reading Excel/JSON)
are placed in base classes like `AbstractComponent` or `BaseTest`, which are then extended by specific page or test
classes. This use of inheritance helps eliminate code duplication and ensures that common utilities are consistently
available across the framework.

================================================================================================
Question3 : When did you use OOP concept in your framework?
Answer:

The framework extensively applies Object-Oriented Programming (OOP) concepts throughout its architecture:

- **Encapsulation:** Encapsulation is the practice of bundling data (fields) and the methods that operate on that data
  into a single unit, usually a class, and restricting direct access to some of the object's components. In the context
  of this framework, each page class (like `landingPage` or `ProductCatalogPage`) contains private web element locators
  and actions, which are only accessible via public methods. This hides the internal implementation and provides a
  controlled way to interact with the page.

- **Inheritance:** Inheritance allows a class to acquire the properties and behaviors (methods) of another class. The
  framework makes use of inheritance by creating base classes such as `AbstractComponent` or `BaseTest`, which contain
  shared functionality (for example, common waits, browser setup, or test hooks). Other page or utility classes extend
  these base classes, reusing and building upon their features while avoiding code duplication.

- **Abstraction:** Abstraction is the concept of hiding complex implementation details and showing only the necessary
  features of an object. In this framework, abstraction is achieved by exposing high-level methods like
  `loginApplication(email, password)` to perform actions in tests, rather than requiring the test code to interact
  directly with low-level web elements. This way, the complexity is hidden behind simple, well-named methods.

- **Polymorphism:** Polymorphism means having many forms—it allows objects to be treated as instances of their parent
  class rather than their actual class, and methods to behave differently depending on the object they are operating on.
  In this framework, polymorphism is used when base classes or interfaces define common methods (such as wait
  functions), and derived classes can override or specialize those methods for their specific behavior. It is also
  evident in the use of interfaces (like Selenium’s WebDriver), where different browser drivers can be used
  interchangeably by referring to them through the same interface type.

Using these OOP principles, the framework achieves high modularity, easier maintenance, and improved scalability in test
automation.

===================================================================================================

Question 4: How do you drive the data from external files in your framework?
Answer:

Data-driven testing in the framework is achieved by integrating TestNG’s `@DataProvider` feature with custom utility
methods that extract test data from external files like JSON or Excel. Here’s how it works in depth, including the
dependencies used:

- **Dependency**: The framework uses the Jackson library (`com.fasterxml.jackson.core` and
  `com.fasterxml.jackson.databind.ObjectMapper`) for parsing JSON files, along with the TestNG testing framework for
  test parameterization.

- **Process Flow**:
    1. In the `StandaloneTest` class, the `@DataProvider` method (`getData`) is defined. This method is responsible for
       reading all test data that will be supplied to the test methods.
    2. The `getData` method calls a custom utility function, for example, `getJsonDataToMap(String filePath)`. This
       utility leverages Jackson’s `ObjectMapper` to read the specified JSON file (e.g., `Data.json`) and convert its
       contents into a list of Java `HashMap<String, String>`.
        - **Sample code logic**:
          ```java
          List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
              // Reads the JSON file and parses it as a List of HashMaps
              ObjectMapper mapper = new ObjectMapper();
              List<HashMap<String, String>> data = mapper.readValue(
                  new File(filePath), new TypeReference<List<HashMap<String, String>>>() {});
              return data;
          }
          ```
    3. The data provider returns this list as a two-dimensional Object array (
       `new Object[][]{{data.get(0)}, {data.get(1)}, ...}`), supplying one `HashMap` per test run.
    4. When the test method is executed, TestNG injects the corresponding test data into it, allowing the test to use
       real data values directly from the external file.
    5. This separation lets you easily switch data sources (for example, implement a similar method for Excel using
       Apache POI), or change the data file, without touching the test logic itself.

- **Benefits**:
    - Keeps test code clean and maintainable.
    - Makes it simple to expand or modify test input by editing only the external data files.
    - Supports robust parameterized and data-driven tests, enabling multiple scenarios to be tested efficiently.

**Summary:** The core mechanism relies on TestNG’s data provider system and Jackson’s `ObjectMapper` for JSON parsing,
making the framework highly flexible for externalized, parameterized test execution.

==================================================================================================
Question 5: In your framework do you used interfaces?
Answer:
Yes, interfaces are used in the framework in several important ways.

- **WebDriver Interface:** Selenium WebDriver itself is an interface (`org.openqa.selenium.WebDriver`). Throughout the
  framework, test and page classes refer to and interact with web browsers through this WebDriver interface, which
  allows for flexible switching between different browser drivers (like ChromeDriver, FirefoxDriver, etc.) without
  changing the core test logic. This provides loose coupling and supports browser-agnostic automation.

- **TestNG ITestListener Interface:** The framework also implements the TestNG `ITestListener` interface to manage test
  execution events. By implementing this interface (in a custom listener class), the framework gains control over hooks
  such as `onTestStart`, `onTestSuccess`, `onTestFailure`, etc. This is especially useful for tasks like automatically
  taking WebDriver screenshots on failure, logging, or generating test reports.

**Scope in the Framework:**

- Driving browser actions via the WebDriver interface allows for high flexibility and scalability.
- Implementing interfaces like `ITestListener` enables the addition of framework-wide behaviors, such as reporting,
  logging, or managing WebDriver lifecycles during various test phases.

**Example:**

```java
// Using WebDriver interface
WebDriver driver = new ChromeDriver();

// Implementing ITestListener
public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        // Take screenshot with WebDriver instance
    }
    // Other event methods...
}
```

In summary, interfaces such as WebDriver and ITestListener are fundamental to the framework’s scalability, flexibility, and maintainability.
=============================================================================================

Question6: Does your framework support parall runs?
Answer:
Yes, the framework does support parallel execution of tests.

**Detailed Explanation:**

- **TestNG Parallel Execution:**  
  The framework leverages TestNG’s built-in capabilities to run tests in parallel. By configuring the `testng.xml` file
  or adding appropriate annotations and parameters in the test code, you can specify classes, methods, or tests to
  execute concurrently.
    - Example in `testng.xml`:

      ```xml
      <suite name="ParallelSuite" parallel="tests" thread-count="3">
          <test name="Test1">
              <classes>
                  <class name="org.anuradhaacademy.tests.TestClass1"/>
              </classes>
          </test>
          <test name="Test2">
              <classes>
                  <class name="org.anuradhaacademy.tests.TestClass2"/>
              </classes>
          </test>
          <test name="Test3">
              <classes>
                  <class name="org.anuradhaacademy.tests.TestClass3"/>
              </classes>
          </test>
      </suite>
      ```
      Or using annotation-based configuration:
      ```java
      @Test(threadPoolSize = 3, invocationCount = 3, timeOut = 10000)
      public void testMethod() {
          // test steps
      }
      ```

- **WebDriver Thread Safety:**  
  To ensure proper isolation of browser instances between parallel tests, each test creates and manages its own
  WebDriver instance (often handled by using the ThreadLocal pattern or by initializing WebDriver inside the test setup
  methods). This prevents data leakage and race conditions between tests.

- **Benefits:**
    - Significantly reduces total execution time by utilizing available CPU cores.
    - Helps validate the stability and reentrancy of the framework and tests in cross-browser or high-volume scenarios.

==================================================================================================
Question 7: Do you have static keyword in your framework? If so, what is the usage?

Answer:
Yes, the `static` keyword is used in the framework. Below is a detailed explanation of how and why it is used:

**What is the `static` keyword?**  
The `static` keyword in Java indicates that a member variable or method belongs to the class itself, rather than to any
specific instance. This means `static` members are shared across all instances of the class. They can be accessed
without creating an instance of the class.

**Usage in the Framework:**

1. **Constants and Utility Methods:**
    - Frequently, you will find `static` final variables used to define constants, such as file paths (e.g.,
      configuration file locations), URLs, or reusable test data, ensuring these values remain unchanged and are
      globally accessible.
    - Utility methods (such as logging, date formatting, or common assertions) are often declared as `static`, so they
      can be called directly from the class without creating new objects, facilitating code reuse.

    ```java
    public class Constants {
        public static final String BASE_URL = "https://rahulshettyacademy.com/client";
        public static final int TIMEOUT = 30;
    }

    public class Utils {
        public static void takeScreenshot(WebDriver driver, String fileName) {
            // ... implementation ...
        }
    }
    // Usage example:
    driver.get(Constants.BASE_URL);
    Utils.takeScreenshot(driver, "fail.png");
    ```

2. **Singletons and Shared Test Data:**
    - In some designs, `static` methods/fields can enforce singleton patterns (single shared instance of a helper,
      manager, or configuration class throughout the test run).
    - If there is test data or resources that must be shared or cached, static variables can provide a straightforward
      mechanism.

3. **Thread Safety Caution:**
    - While `static` variables can be helpful, they should be used cautiously in test frameworks, especially when
      running tests in parallel. Static mutable state can introduce shared state issues and race conditions.
    - In this framework, if any static variables are mutable, the code ensures they are either thread-local or properly
      synchronized to avoid conflicts in parallel execution.

**Example Scenarios from the Framework:**

- A `static` block may initialize resources (like reading properties/configurations at class loading).
- Static utility functions are used for actions like reading/writing files, capturing screenshots, waiting for elements,
  or environment setup and teardown.

**Summary:**
The `static` keyword is used for global constants, utility methods, or sometimes to maintain shared resources, helping
to reduce memory footprint and improve code organization. However, careful usage is ensured to maintain thread safety
and test independence, especially as the framework supports parallel test execution.
===================================================================================================

Question 8: How do you send or inject global parameters into your tests at runtime?

Answer:
In our framework, global parameters are supplied to tests at runtime primarily through test configuration files,
command-line arguments, system/environment variables, and parameterization mechanisms provided by the test runner (such
as TestNG or JUnit). Here’s an in-depth explanation of the main strategies:

**1. Using Properties or Configuration Files**

- We maintain configuration files (typically `.properties` or `.yaml`) that define global parameters such as environment
  URLs, browser types, credentials, timeouts, etc.
- At runtime, the test framework reads these files and loads the parameters, making them accessible throughout the test
  via a dedicated configuration utility or singleton pattern.

  ```java
  // Loading from properties file
  Properties configProps = new Properties();
  FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
  configProps.load(fis);
  String baseUrl = configProps.getProperty("base.url");
  ```

**2. Using Command-Line Arguments or System Properties**

- When initiating a test run (for example via Maven or Gradle), we can pass parameters using `-D` arguments.
- These parameters are retrieved in the test code using `System.getProperty()`.

  ```sh
  mvn test -Dbrowser=chrome -Denv=qa
  ```
  ```java
  String browser = System.getProperty("browser", "chrome");
  String environment = System.getProperty("env", "dev");
  ```

**3. Through TestNG/JUnit Parameterization**

- Test parameters can be defined in the TestNG XML (`<parameter name="browser" value="firefox"/>`) or JUnit
  configuration and accessed in test classes using annotations such as `@Parameters`.
- This approach is convenient for running the same tests with different data sets/environments.

  ```xml
  <!-- TestNG example -->
  <suite name="Suite">
    <parameter name="browser" value="firefox"/>
    <test name="SampleTest">
      <!-- ... -->
    </test>
  </suite>
  ```
  ```java
  @Parameters({"browser"})
  @BeforeTest
  public void setup(String browser) {
      // Use browser parameter for driver initialization
  }
  ```

**4. With Environment Variables**

- Environment variables are commonly used for sensitive data like credentials or external service tokens.
- Accessed in code via `System.getenv("ENV_VAR_NAME")`.

  ```java
  String apiKey = System.getenv("API_KEY");
  ```

**5. Centralized Parameter Management**

- All global parameters, regardless of how they are injected, are centralized in configuration helper classes or as
  static/global variables in configuration manager classes.
- This ensures they are accessible throughout the test session, supporting both flexibility and maintainability.

**Summary:**
Global parameters are sent or injected into tests at runtime using configuration files, system properties, test runner
parameterization, or environment variables. This design enables flexible configuration of tests (e.g., changing
browsers, switching environments) without modifying the test code, thereby promoting reusability and supporting robust
CI/CD pipelines.
================================================================================================

Question 9: what is the mechanisum use to run only selected set of test inside the framework?

Answer:
To run only a selected set of tests inside the framework, several mechanisms can be used. Here’s a detailed explanation:

**1. TestNG Groups**

- In TestNG, you can assign tests to groups using the `@Test(groups = {"group_name"})` annotation.
- When running tests, you can include or exclude certain groups in your TestNG XML suite file or via command line
  options. This enables running only the tests belonging to specific groups.

  ```java
  @Test(groups = {"sanity"})
  public void testSanityFeature() { ... }

  @Test(groups = {"regression"})
  public void testRegressionFeature() { ... }
  ```
  ```xml
  <suite name="Suite">
    <test name="SanityTests">
      <groups>
        <run>
          <include name="sanity"/>
        </run>
      </groups>
      <!-- test classes here -->
    </test>
  </suite>
  ```

**2. Including/Excluding Tests in TestNG/JUnit XML or Configuration**

- You can explicitly specify which test classes, methods, or packages to include or exclude in your test suite XML
  file (TestNG) or configuration (JUnit).
- This lets you tailor your test run to only the selected test cases.

**3. Using Maven Surefire Plugin Parameters**

- You can configure the Maven Surefire plugin in your `pom.xml` to run specific tests by providing pattern matches or
  test class names:

  ```sh
  mvn test -Dtest=TestClassName
  mvn test -Dtest=TestClassName#testMethodName
  mvn test -Dtest=TestClass1,TestClass2
  ```

**4. Tagging Tests or Using Filters**

- In JUnit 5, you can use the `@Tag` annotation to mark tests and use `-Dgroups` or Surefire plugin configuration to run
  only tests with specific tags.

**5. IntelliJ IDEA/Eclipse and Build Tool Runners**

- Modern IDEs allow you to right-click on a specific test method or class and run/debug only that selection.
- You can also create custom run/debug configurations to execute particular tests.

**Summary:**  
The mechanism used to run only a selected set of tests usually involves grouping/tagging test methods, configuring your
test runner or build tool to include/exclude particular tests, or by selectively specifying test names/patterns from the
command line or in configuration files. This flexibility is essential for running smoke, regression, or specific
scenario tests efficiently in large frameworks.

=================================================================================================
Qustion 10: How do you handle falky tests in the your framework?

Answer:
Flaky tests are tests that exhibit intermittent failures—sometimes passing and sometimes failing—without any changes to
the code. Handling flaky tests is crucial to maintain trust in your test suite and CI/CD pipelines. Here are several
techniques and best practices employed to address flaky tests in test automation frameworks:

1. **Test Stability and Retry Mechanisms**
    - Implement a retry mechanism using TestNG’s `IRetryAnalyzer` or JUnit’s `@RepeatedTest` or third-party retry rules.
      For example, TestNG allows you to specify that a failed test should be rerun a certain number of times before
      marking it as truly failed. This can be done by implementing a custom `RetryAnalyzer` and using it with your
      tests:
      ```java
      public class RetryAnalyzer implements IRetryAnalyzer {
          private int count = 0;
          private static final int maxTry = 2;
          public boolean retry(ITestResult iTestResult) {
              if (count < maxTry) {
                  count++;
                  return true;
              }
              return false;
          }
      }
      ```
      And then annotate your test:
      ```java
      @Test(retryAnalyzer = RetryAnalyzer.class)
      public void testFeature() { ... }
      ```

2. **Robust Wait Strategies**
    - Use explicit waits (like WebDriverWait) instead of fixed thread sleeps to synchronize the test actions with the
      application under test. This reduces flakiness caused by timing issues and ensures that elements are
      present/interactable before acting on them.

3. **Isolation and Clean-Up**
    - Ensure that each test is independent and does not rely on or affect the state of other tests. Use proper setup and
      teardown methods (`@BeforeMethod`, `@AfterMethod` for TestNG) to reset the environment between tests.

4. **Better Element Locators and Identification**
    - Avoid using dynamic or brittle selectors (like XPath with indices). Opt for more stable locators such as IDs or
      data attributes whenever possible.

5. **CI/CD Integration and Reporting**
    - Continuously monitor and report on flaky test occurrences in CI pipelines. Mark and quarantine consistently flaky
      tests so they do not block the pipeline, and proactively prioritize fixing the root cause.

6. **Root Cause Analysis**
    - Frequently analyze logs, screenshots, and error messages to identify the real causes of flakiness — whether in
      test code, application stability, network, or environment configuration.

7. **Test Data Management**
    - Use unique or properly managed test data for each run to avoid conflicts and dependencies that may cause test
      flakiness.

8. **Parallel and Distributed Testing Considerations**
    - When running tests in parallel, ensure thread safety and instance isolation to avoid shared state or resource
      conflicts.
      =============================================================================================

Question 12: what are the challenges you faced during implementing framework?
Answer:
During the implementation of the Selenium test automation framework, I encountered several challenges:

1. **Cross-browser Compatibility:**  
   Ensuring that tests run reliably across multiple browsers (such as Chrome, Firefox, and Edge) was difficult due to differences in browser drivers, rendering engines, and timing issues. Resolving them required configuring WebDriver manager utilities and customizing wait strategies.

2. **Test Flakiness and Synchronization Issues:**  
   Many tests were initially unstable due to poorly synchronized steps or dynamic content. Replacing thread sleeps with explicit waits (like WebDriverWait) and using better locator strategies significantly improved reliability.

3. **Dynamic Element Locators:**  
   Web applications often use dynamic element IDs or classes that change frequently. To address this, I implemented robust strategies such as using stable attributes, CSS selectors, and even custom data attributes, making selectors more maintainable.

4. **Test Data Management:**  
   Managing test data to avoid collisions and maintain independence between test cases was tricky, especially for create/read/update/delete operations. I automated data setup and cleanup procedures using hooks (e.g., @BeforeMethod, @AfterMethod in TestNG) and used randomization or unique identifiers in generated test data.

5. **Parallel and Distributed Test Execution:**  
   Running tests in parallel on a Selenium Grid or cloud (like BrowserStack or Sauce Labs) introduced thread-safety concerns, particularly with shared resources. I designed the framework to create isolated WebDriver instances per test and ensured no global/shared mutable state affected results.

6. **Reporting and CI/CD Integration:**  
   Integrating detailed reporting (ExtentReports, Allure, etc.) and getting the framework stable within a CI/CD environment required handling environment variabilities, like differing networks or headless browsers in CI pipelines.

7. **Maintaining Reusable and Scalable Code:**  
   As the number of tests grew, maintaining reusable code (via Page Object Model and utility classes) and keeping the framework scalable required continuous refactoring and enforcing good coding standards.

8. **Environment and Configuration Management:**  
   Accommodating different environments (QA, staging, production) was a challenge. I used configuration files (like `Globaldata.properties`) and utility classes to ensure environment-specific URLs, credentials, and parameters could be set externally and safely.

9. **Handling Pop-ups, Alerts, and Multi-Window Scenarios:**  
   Dealing with scenarios involving browser pop-ups, alerts, and switching between multiple windows/tabs required implementing additional WebDriver utilities and custom handlers.

10. **Learning Curve and Team Collaboration:**  
    Bringing new team members up to speed on framework conventions and tools (e.g., Maven, TestNG, Selenium best practices) also became essential, so proper documentation and code reviews played a vital role.

Overall, these challenges helped shape a robust, maintainable, and scalable automation framework aligned with both project needs and industry best practices.