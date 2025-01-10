package NoKodr.Nokodr;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import NoKodr.Nokodr.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class SignupTest {

    WebDriver driver;
    SignupPage signupPage;

    @BeforeClass
    public void setup() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "/Users/Owner/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
    	//WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.slds-notify_toast.slds-theme_error")));

        driver.get("https://app-staging.nokodr.com/");

        // Initialize the page object
        signupPage = new SignupPage(driver);
    }

    @Test(priority = 1)
    public void validateMandatoryFields() {
        signupPage.clickLogin();
        String actualMessage = signupPage.getErrorMessage();
        System.out.println("Actual error: " + actualMessage);
        Assert.assertTrue(actualMessage.contains("Fields are mandatory!"), "Error for mandatory fields is missing!");
    }

    @Test(priority = 2)
    public void validateInvalidEmailFormat() {
        signupPage.setEmail("invalid_email");
        signupPage.setPassword("ValidPass123!");
        signupPage.clickLogin();
        String actualMessage = signupPage.getErrorMessage();
        System.out.println("Actual error: " + actualMessage);
        Assert.assertTrue(actualMessage.contains("Invalid email format!"), "Error for invalid email is missing!");
    }

    @Test(priority = 3)
    public void validatePasswordMismatch() {
        signupPage.setEmail("abhilashap699@gmail.com");
        signupPage.setPassword("Password123!");
        signupPage.clickLogin();
        String actualMessage = signupPage.getErrorMessage();
        System.out.println("Actual error: " + actualMessage);
        Assert.assertTrue(actualMessage.contains("Passwords do not match!"), "Error for password mismatch is missing!");
    }

    @Test(priority = 4)
    public void validateValidSignup() {
        signupPage.setEmail("abhilashap699@gmail.com");
        signupPage.setPassword("Abhi@123");
        signupPage.clickLogin();
        String actualMessage = signupPage.getSuccessMessage();
        System.out.println("Actual success message: " + actualMessage);
        Assert.assertTrue(actualMessage.contains("Account created successfully!"), "Success message is missing!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}



