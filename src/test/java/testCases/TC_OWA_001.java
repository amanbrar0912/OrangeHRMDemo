package testCases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import pageObjects.Dashboard;
import pageObjects.IndexPage;
import utilities.BrokenUtils;
import utilities.ExcelUtils;

public class TC_OWA_001 extends BaseClass {
	// public WebDriver driver;
	public static Logger logger = LogManager.getLogger(BaseClass.class.getName());

	@Test(dataProvider = "TC_OWA_001_TestData")
	public void TestLogin(String uname, String pword, String isValid) throws ClientProtocolException, IOException {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		// Started the login test
		logger.info("Started Logintest using Orange HRM Login(Positive and Negative)");

		IndexPage ip = new IndexPage(driver);
		ip.enterUsername(uname);
		logger.info("Username entered.");
		ip.enterPassword(pword);
		logger.info("Password entered.");
		ip.clickLoginBtn();
		logger.info("Login button clicked.");
		Dashboard db = new Dashboard(driver);
		BrokenUtils broke = new BrokenUtils();
		System.out.println("Number of broken images : "+broke.countBrokenImages());
		SoftAssert sa = new SoftAssert();
		if (isValid.equalsIgnoreCase("Y")) {
			//Boolean verify = db.validateDashboard();
			//if(verify)
				logger.info("Login is successfull");
			//else
				logger.info("Login is unsuccessfull");
			/*if (db.getUrl().equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/index.php/dashboard")) {
				logger.debug("Assertion passed.");
				sa.assertTrue(true);
			} else {
				logger.debug("Assertion failed.");
				sa.assertTrue(false);
			}*/
		} else {
			if (ip.showsError()) {
				logger.debug("Assertion passed.");
				sa.assertTrue(true);
			} else {
				logger.debug("Assertion failed.");
				sa.assertTrue(false);
			}
		}
		sa.assertAll();
	}

	@DataProvider(name = "TC_OWA_001_TestData")
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\TC_OWA_001_TestData.xlsx";
		String sheetName = "TC_OWA_001";
		return ExcelUtils.getData(filePath, sheetName);
	}
}
