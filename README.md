RESTFUL - API Tests (Cucumber - Rest Assured)
========================================
This Maven project is an automation suite designed to test a RESTful API. It includes test scenarios that cover CRUD (Create, Read, Update, Delete) operations.

<b>Prerequisites</b>

Before you can run this automation suite, ensure that you have the following prerequisites installed on your system:

    Java 1.8+: You need Java Development Kit (JDK) version 1.8 or higher installed on your system. You can download and install it from the official Oracle website or use an alternative Java distribution like OpenJDK.

    Maven: Maven is required to build and manage dependencies for this project. You can download Maven from the official website (https://maven.apache.org/download.cgi) and follow the installation instructions.

    Java IDE: You can use an Integrated Development Environment (IDE) like Eclipse or IntelliJ IDEA to work with this project. Ensure you have one of these IDEs installed, or you can use your preferred Java IDE.

<b>Note:</b> While the suite is designed to work on both Windows and Mac platforms, it has been primarily tested on a Mac. It is recommended to execute the test suite on a Mac if possible.


Running the Automation Suite

To execute the automation suite, follow these steps:

1. Clone the Project: Clone this repository to your local machine using Git or download it as a ZIP file and extract it.

2. Open a Terminal/CMD Window: Navigate to the project directory using your preferred terminal or command prompt.

3. Run the Tests:

Using Maven, run the following command to execute the tests:

	mvn clean test

If you pefer, you can also import the project into your IDE (Eclipse or IntelliJ) and run the test scenarios directly from the IDE.

<b>Test Reporting</b>

This project integrates the Serenity framework to provide detailed test result reports. After running the tests, you can find the Serenity report in the following location:

       /target/site/serenity/index.html

Open this HTML file in your web browser to view the test results, including detailed information about test cases, scenarios, and any issues encountered during the test run.

Please feel free to reach out if you have any questions or encounter any issues while setting up or running the automation suite. Happy testing!
