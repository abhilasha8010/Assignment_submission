## NoKodr Automation Testing

This repository contains automated test scripts for validating the functionality of the NoKodr platform's Signup, Login, and Forgot Password features. The tests are written in Java using Selenium WebDriver and TestNG.

## Features

1. Signup Page Validation

Mandatory Fields Validation: Ensures all required fields (e.g., email, password) are filled.

Input Format Validation: Checks for proper email and password formats.

Valid and Invalid Inputs:

Valid: Correct email and password.

Invalid: Blank fields, invalid email formats, mismatched passwords, etc.

Submission: Validates success and error messages.

2. Login Page Validation

Mandatory Fields Validation: Ensures username and password are provided.

Valid and Invalid Inputs:

Valid: Correct username and password.

Invalid: Blank fields, incorrect credentials, and special characters.

Submission: Verifies redirection to the dashboard on success and error messages for invalid inputs.

3. Forgot Password Validation

Mandatory Email Field: Ensures the email field is not blank.

Input Format Validation: Checks for proper email formats.

Valid and Invalid Inputs:

Valid: Registered email.

Invalid: Non-registered email, invalid formats, blank fields.

Submission: Validates success and error messages (e.g., "Reset link sent to your email").

## Project Structure

NoKodr
├── src/main/java
│   └── NoKodr/Nokodr
│       └── SignupPage.java
├── src/test/java
│   └── NoKodr/Nokodr
│       ├── SignupTest.java
│       ├── LoginTest.java
│       └── ForgotPasswordTest.java
├── pom.xml
└── README.md

## Prerequisites

Java Development Kit (JDK) 8 or higher

Maven

Selenium WebDriver

TestNG

Google Chrome and ChromeDriver

Installation

## Clone the repository:

git clone https://github.com/yourusername/NoKodr-Automation.git
cd NoKodr-Automation

## Install dependencies:

mvn clean install

How to Run Tests

Update the chromedriver path in the test classes.

Run the TestNG suite:

mvn test

## Locators Used

Signup Page:

Email Field: //input[@type='email' and @name='username']

Password Field: //input[@type='password' and @name='password']

Login Button: //div[@id='staticElement' and text()='Log In']

Error Messages:

General Error: //body//abx-modal (Forgot Password error)

Email Format Error: //div[@role='status' and contains(@class, 'slds-theme_error')]

Example Test Cases

## Signup Validation

Test Name: validateMandatoryFields

Verifies error message when fields are left blank.

Test Name: validateValidSignup

Verifies success message on providing valid inputs.

## Login Validation

Test Name: validateInvalidCredentials

Verifies error message on incorrect username/password.

Test Name: validateValidLogin

Verifies dashboard redirection on valid credentials.

## Forgot Password Validation

Test Name: validateBlankEmail

Verifies error message on submitting an empty email field.

Test Name: validateValidEmail

Verifies success message on submitting a registered email.

Contributing

Fork the repository.

Create a new branch for your feature/bug fix.

Submit a pull request with a detailed description of your changes.

## License :-

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For issues or contributions, contact:

Name: Abhilasha

Email: abhilashap699@gmail.com
