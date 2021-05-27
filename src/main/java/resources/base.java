package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browsername = System.getProperty("browser");
		String urlname = prop.getProperty("URL");
		System.out.println(browsername);

		if (browsername.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if (browsername.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.get(urlname);
			driver.manage().window().maximize();
		}

		else if (browsername.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
			FirefoxOptions opfirefox = new FirefoxOptions();
			opfirefox.setHeadless(true);
			driver = new FirefoxDriver(opfirefox);
			driver.get(urlname);
			driver.manage().window().maximize();
		}

		else if (browsername.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\msedgedriver.exe");
			driver.get(urlname);
			driver.manage().window().maximize();
		}

		else if (browsername.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.get(urlname);
			driver.manage().window().maximize();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		System.out.println(destinationFile);
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

	/*
	 * String screenshotsDir = System.getProperty("user.dir"); String filename =
	 * testCaseName+"_"+".png"; Path screenshotPath = Paths.get(screenshotsDir,
	 * filename); File SrcFile =
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * FileUtils.copyFile(SrcFile, screenshotPath.toFile());
	 */

}
