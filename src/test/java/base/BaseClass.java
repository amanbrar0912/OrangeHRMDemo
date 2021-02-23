package base;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass {
	public static WebDriver driver;

	@BeforeMethod
	public WebDriver intializeDriver() throws IOException {

		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(".\\Configuration\\config.properties");
		property.load(fis);
		String browser = property.getProperty("browser");
		System.out.println("Browser = " + browser);
		String baseURL = property.getProperty("baseURL");
		System.out.println("BaseURL = " + baseURL);
		String driverPath;
		if (browser.equalsIgnoreCase("chrome")) {
			driverPath = property.getProperty("chromeDriverPath");
			System.out.println("chromeDriverPath = " + driverPath);
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("FAILURE: please pass valid internet browser in properties.");
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Tearing down.");
		driver.quit();
	}
	
	public String getScreenShotPath(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String destination = System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+"-"+timeStamp+".png";
		
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

}
