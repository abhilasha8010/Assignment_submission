package noKodr1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
public class SignupValidation {
    public static void main(String[] args) {
        // Set up WebDriver and open browser
        System.setProperty("webdriver.chrome.driver", "/Users/Owner/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Navigate to the signup page
            driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");

            // Locators for input fields and buttons
            By emailField = By.cssSelector("input[name='username'][type='email']");
            By passwordField = By.cssSelector("input[name='password'][type='password']");
            By loginButton = By.cssSelector("div[title='Log In']");
            By errorToast = By.cssSelector("div.slds-notify_toast.slds-theme_error");
            By successMessage = By.xpath("//h1[contains(text(), 'Account created successfully!')]");

            // Test case 1: Valid inputs
            System.out.println("Testing valid inputs...");
            driver.findElement(emailField).sendKeys("abhilashap699@gmail.com");
            driver.findElement(passwordField).sendKeys("Abhi@123");
            driver.findElement(loginButton).click();

            // Wait for the success message
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            System.out.println("Success message displayed: Account created successfully!");

            // Test case 2: Invalid email format
            System.out.println("Testing invalid email format...");
            driver.findElement(emailField).clear();
            driver.findElement(passwordField).clear();
            driver.findElement(emailField).sendKeys("abc@gmail.com");
            driver.findElement(passwordField).sendKeys("Abhi@123");
            driver.findElement(loginButton).click();

            // Wait for the error message
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorToast));
            System.out.println("Error message displayed for invalid email.");

            // Test case 3: Invalid password
            System.out.println("Testing invalid password...");
            driver.findElement(emailField).clear();
            driver.findElement(passwordField).clear();
            driver.findElement(emailField).sendKeys("abhilashap699@gmail.com");
            driver.findElement(passwordField).sendKeys("abc123");
            driver.findElement(loginButton).click();

            // Wait for the error message
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorToast));
            System.out.println("Error message displayed for invalid password.");

            // Test case 4: Missing mandatory fields
            System.out.println("Testing missing mandatory fields...");
            driver.findElement(emailField).clear();
            driver.findElement(passwordField).clear();
            driver.findElement(loginButton).click();

            // Wait for the error message
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorToast));
            System.out.println("Error message displayed for missing mandatory fields.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
