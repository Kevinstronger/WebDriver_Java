package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadOnly {
	WebDriver driver = null;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "./plugin/driver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test
	public void readonly_input() throws InterruptedException {
		driver.get("file:///D:/location/locator.html");
		//方法一
		/*String remove_readonly_js = "$('#readonly').removeAttr('readonly')";
		if(driver instanceof JavascriptExecutor){
			((JavascriptExecutor)driver).executeScript(remove_readonly_js);
		}
		driver.findElement(By.id("readonly")).sendKeys("Bingo");*/
		//方法二
		/*JavascriptExecutor js = null;
		if(driver instanceof JavascriptExecutor){
			js = (JavascriptExecutor)driver;
		}
		js.executeScript("$('#readonly').removeAttr('readonly')");
		driver.findElement(By.id("readonly")).sendKeys("Method 2");*/
		//方法三
		String set_value_readonly_js = "$('#readonly').attr('value', 'Method3')";
		if(driver instanceof JavascriptExecutor){
			((JavascriptExecutor)driver).executeScript(set_value_readonly_js);
		}		
		Thread.sleep(5000);
		
	}

	@AfterTest
	public void afterTest() {
		if(driver != null)
			driver.close();
	}

}
