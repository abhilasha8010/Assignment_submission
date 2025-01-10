package NoKodr.Nokodr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("WebDriver.chrome.driver","/Users/Owner/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//navigate The Page
		driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
		//System.out.println("Title is : " +driver.getTitle());
		String actualTitle = driver.getTitle();
		String expectedTitle = "noKodr";
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("Test Passed "+" Actual Title is :" + actualTitle);
		}
		else
		{
			System.out.println("Test Failed"+" Expected Title is :"+ expectedTitle);
		}
		//Close Browser
		driver.quit();
	}

}
