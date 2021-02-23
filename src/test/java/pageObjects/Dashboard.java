package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	WebDriver lDriver;

	public Dashboard(WebDriver driver) {
		lDriver = driver;
		PageFactory.initElements(lDriver, this);
	}
	
	@FindBy(xpath="//div[@class='head']//h1")
	WebElement txtTitle;

	public String getTitle() {
		return txtTitle.getText();
	}
	
	public String getUrl() {
		return lDriver.getCurrentUrl();
	}
}
