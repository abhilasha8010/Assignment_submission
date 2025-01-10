package NoKodr.Nokodr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://app-staging.nokodr.com/");

        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void validateMandatoryFields() {
        loginPage.clickLogin();
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual error message: " + actualError);
        Assert.assertTrue(actualError.contains("Fields are mandatory!"), "Error message for mandatory fields is missing!");
    }

    @Test(priority = 2)
    public void validateInvalidPasswordFormat() {
        loginPage.setUsername("abhilashap699@gmail.com");
        loginPage.setPassword("short");
        loginPage.clickLogin();
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual error message: " + actualError);
        Assert.assertTrue(actualError.contains("Password must meet length/format requirements"), "Error for invalid password format is missing!");
    }

    @Test(priority = 3)
    public void validateInvalidCredentials() {
        loginPage.setUsername("invalid_email@gmail.com");
        loginPage.setPassword("InvalidPass123!");
        loginPage.clickLogin();
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual error message: " + actualError);
        Assert.assertTrue(actualError.contains("Invalid username or password"), "Error message for invalid credentials is missing!");
    }

    @Test(priority = 4)
    public void validateSpecialCharacters() {
        loginPage.setUsername("!!!@@@###");
        loginPage.setPassword("*****");
        loginPage.clickLogin();
        String actualError = loginPage.getErrorMessage();
        System.out.println("Actual error message: " + actualError);
        Assert.assertTrue(actualError.contains("Invalid username or password"), "Error for special characters is missing!");
    }

    @Test(priority = 5)
    public void validateValidCredentials() {
        loginPage.setUsername("abhilashap699@gmail.com");
        loginPage.setPassword("Abhi@123");
        loginPage.clickLogin();
        String actualSuccess = loginPage.getSuccessMessage();
        System.out.println("Actual success message: " + actualSuccess);
        Assert.assertTrue(actualSuccess.contains("Dashboard"), "Success message or redirection to the dashboard is missing!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
