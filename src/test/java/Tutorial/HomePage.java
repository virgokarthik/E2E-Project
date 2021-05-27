package Tutorial;

import java.io.IOException;

import resources.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.landingPage;
import pageObjects.loginPage;
import resources.base;

public class HomePage extends base {
	
//public static Logger log = LogManager.getLogger(base.class.getName());

	@Test(dataProvider = "getData")
	
	

	public void basePageNavigation(String username, String Pwd) throws IOException

	{

		driver = initializeDriver();
	//	log.info("Initialized the Driver");
		// driver.get("http://qaclickacademy.com");

		// Use either extends or create a class to call methods

		landingPage lp = new landingPage(driver);
		String text = lp.feature().getText();
	//	Log.info("The course name as shown on the page is " +text);
		Assert.assertEquals("FEATURED COURSES", text);
	//	Log.info("The actual and the expected course names match");
		Assert.assertTrue(lp.navbar().isDisplayed());
	//	Log.info("The navigation bar is displayed");

		
				
		lp.getLogin().click();
		loginPage ln = new loginPage(driver);
		ln.user().sendKeys(username);
		ln.password().sendKeys(Pwd);
		ln.login().click();
		String errmsg =	ln.geterror().getText();
		Assert.assertTrue(errmsg, true);
	//	log.info("Login failed due to incorrect credentials");
			
		driver.close();
		

	}

	@DataProvider

	public Object[][] getData() {

		// Rows is first array argument = test scenario = different data test
		// Columns is data for that scenario

		Object[][] data = new Object[2][2];
		data[0][0] = "user1";
		data[0][1] = "pwd1";

		data[1][0] = "user2";
		data[1][1] = "pwd2";

		return data;

	}

}
