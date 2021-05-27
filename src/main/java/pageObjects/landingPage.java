package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class landingPage {

	public WebDriver driver;

	By loginlink = By.cssSelector("a[href*='sign_in']");
	By featuretext = By.xpath("//h2[contains(text(),'Featured Courses')]");
	By navigationbar = By.cssSelector("ul[class*= 'nav']");

	public landingPage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement getLogin()

	{
		return driver.findElement(loginlink);
	}

	public WebElement feature()

	{
		return driver.findElement(featuretext);
	}
	
	
	public WebElement navbar()

	{
		return driver.findElement(navigationbar);
	}

}
