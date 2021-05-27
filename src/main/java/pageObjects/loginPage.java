package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginPage {

	public WebDriver driver;

	public loginPage(WebDriver driver) {
		this.driver = driver;
	}

	By username = By.id("user_email");
	By pwd = By.id("user_password");
	By submit = By.cssSelector("input[name='commit']");
	By errormessage = By.cssSelector("div[role='alert']");

	public WebElement user()

	{
		return driver.findElement(username);
	}

	public WebElement password()

	{
		return driver.findElement(pwd);
	}

	public WebElement login()

	{
		return driver.findElement(submit);
	}
	
	public WebElement geterror()

	{
		return driver.findElement(errormessage);
	}

}
