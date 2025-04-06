JabberPoint is a basic slide presentation program written in Java, originally developed for educational purposes. This project refactors the original JabberPoint system by applying modern software design principles and includes unit testing and continuous integration support.

Unit Testing & Code Coverage
This project uses JUnit 5 for unit testing and JaCoCo for generating test coverage reports.

**Setup Instructions**
- Install Apache Maven

- Download it from the official site: https://maven.apache.org/download.cgi

- Extract it and add the bin/ folder to your system's PATH environment variable.

- Clone the Repository

**Run Tests & Generate Coverage Report**

- Run the following command in the project root: mvn clean verify

**How to Run the Tests with Coverage**
To run all tests and check code coverage:

- Right-click the test directory in IntelliJ.

- Select More Run/Debug.

- Choose Run Tests in 'test' with Coverage.

IntelliJ will display detailed line coverage in the editor and coverage panel.

**The project achieves at least 75% line coverage as required.**