package NoKodr.Nokodr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ForgotPasswordTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://app-staging.nokodr.com/");
    }

    @Test(priority = 1)
    public void validateBlankEmailField() {
        // Click on 'Forgot Password?'
        driver.findElement(By.xpath("//a[normalize-space()='Forgot Password?']")).click();

        // Leave email field blank and click Proceed
        driver.findElement(By.xpath("//abx-button[@name='proceed']//div[@id='staticElement']")).click();

        // Capture error message
        WebElement errorMessage = driver.findElement(By.xpath("//body//abx-modal"));
        String actualError = errorMessage.getText();
        System.out.println("Error Message: " + actualError);

        // Validate error message
        Assert.assertTrue(actualError.contains("Email field is mandatory"), "Expected error message for blank email field is missing!");
    }

    @Test(priority = 2)
    public void validateInvalidEmailFormat() {
        // Input invalid email format
        driver.findElement(By.cssSelector("#id_17364975061786841")).sendKeys("invalid_email");
        driver.findElement(By.xpath("//abx-button[@name='proceed']//div[@id='staticElement']")).click();

        // Capture error message
        WebElement errorMessage = driver.findElement(By.xpath("//body//abx-modal"));
        String actualError = errorMessage.getText();
        System.out.println("Error Message: " + actualError);

        // Validate error message
        Assert.assertTrue(actualError.contains("Invalid email format"), "Expected error message for invalid email is missing!");
    }

    @Test(priority = 3)
    public void validateNonRegisteredEmail() {
        // Input non-registered email
        driver.findElement(By.cssSelector("#id_17364975061786841")).clear();
        driver.findElement(By.cssSelector("#id_17364975061786841")).sendKeys("nonregistered@example.com");
        driver.findElement(By.xpath("//abx-button[@name='proceed']//div[@id='staticElement']")).click();

        // Capture error message
        WebElement errorMessage = driver.findElement(By.xpath("//body//abx-modal"));
        String actualError = errorMessage.getText();
        System.out.println("Error Message: " + actualError);

        // Validate error message
        Assert.assertTrue(actualError.contains("Email not found"), "Expected error message for non-registered email is missing!");
    }

    @Test(priority = 4)
    public void validateValidEmail() {
        // Input valid email
        driver.findElement(By.cssSelector("#id_17364975061786841")).clear();
        driver.findElement(By.cssSelector("#id_17364975061786841")).sendKeys("abhilashap699@gmail.com");
        driver.findElement(By.xpath("//abx-button[@name='proceed']//div[@id='staticElement']")).click();

        // Capture success message
        WebElement successMessage = driver.findElement(By.xpath("//body//abx-modal"));
        String actualMessage = successMessage.getText();
        System.out.println("Success Message: " + actualMessage);

        // Validate success message
        Assert.assertTrue(actualMessage.contains("Reset link sent to your email"), "Expected success message is missing!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
