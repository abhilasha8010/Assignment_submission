package NoKodr.Nokodr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {
    WebDriver driver;

   
    By emailField = By.cssSelector("input[name='username'][type='email']");
    By passwordField = By.cssSelector("input[name='password'][type='password']");
    By loginButton = By.cssSelector("div[title='Log In']");
    By errorMessage = By.cssSelector("div.slds-notify_toast.slds-theme_error");
    By successMessage = By.xpath("//h1[contains(text(), 'Account created successfully!')]");

    
    public SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    
    public void setEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
