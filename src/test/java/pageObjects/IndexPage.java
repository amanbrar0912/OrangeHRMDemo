package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class IndexPage extends BaseClass {
	WebDriver lDriver;
	public IndexPage(WebDriver rdriver) {
		lDriver = rdriver;
		PageFactory.initElements(lDriver, this);
	}
	
	@FindBy(xpath="//input[@id='txtUsername']")
	WebElement txtbxUsername;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement txtbxPassword;
	
	@FindBy(xpath="//input[@id='btnLogin']")
	WebElement btnLogin;
	
	public void enterUsername(String user) {
		txtbxUsername.sendKeys(user);
	}
	public void enterPassword(String pwd) {
		txtbxPassword.sendKeys(pwd);
	}
	public void clickLoginBtn() {
		btnLogin.click();
	}
	public boolean showsError() {
		if(driver.findElement(By.xpath("//span[@id='spanMessage']")).getText().equals("Invalid credentials"))
			return true;
		else 
			return false;
	}
}
