# Rest Assured API Test Automation

This project implements API test automation using **Rest Assured**, **Cucumber**, **JUnit**, and **Maven**. It was created to validate RESTful endpoints in a readable, reusable, and behavior-driven (BDD) approach.

## 🧪 Technologies Used

- Java 17+ or 23
- Maven
- Rest Assured
- Cucumber
- JUnit
- IntelliJ IDEA (recommended IDE)

## ✅ What Is Being Tested?

CRUD operations (Create, Read, Update, Delete) against a sample post API, covering endpoints like:

- `GET /posts`
- `GET /posts/{id}`
- `POST /posts`
- `PUT /posts/{id}`
- `DELETE /posts/{id}`

Each endpoint is tested for:

- Expected status code
- Response fields
- Content validation based on the input data

## 📁 Project Structure

```
src
├── main
│   └── java
├── test
│   ├── java
│   │   └── stepsdefinitions  → Cucumber step definitions
│   ├── resources
│   │   └── features          → Cucumber `.feature` files
│
├── pom.xml                  → Maven dependencies and configuration
└── README.md
```

## ▶️ How to Run the Tests

### Prerequisites:

- Java and Maven installed
- IntelliJ (optional but recommended)

### Run via terminal:

```bash
mvn clean verify
```

### Run tests with specific tag:

```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

> **Note**: Ensure the Cucumber plugin is set up to generate reports in `target/cucumber-report`.

## 📊 Reports

After execution, an HTML report is available at:

```
target/cucumber-report/index.html
```

## 🧾 Sample Feature

```gherkin
Feature: Update an existing post

  Scenario: Successfully update a post
    Given I have the following post data:
      | title | updated title |
      | body  | new content   |
    When I send a PUT request to "/posts/1" with the given data
    Then the response status code should be 200
    And the response should contain the updated post data
```

## 👨‍💻 Author

Paulo Corbacho
