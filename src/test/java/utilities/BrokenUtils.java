package utilities;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseClass;

public class BrokenUtils extends BaseClass {
	public int countBrokenImages() throws ClientProtocolException, IOException {
		int iBrokenImgCount = 0;
		List<WebElement> image_list = driver.findElements(By.tagName("img"));
		System.out.println("No. of Images on this page = "+image_list.size());
		for (WebElement img : image_list) {
			if (img != null) {
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(img.getAttribute("src"));
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() != 200) {
					iBrokenImgCount++;
				}
			}
		}
		return iBrokenImgCount;
	}
}
