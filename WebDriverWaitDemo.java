package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.utils.InitMessage;

public class WaitDemo {

	WebDriver driver = null;
	Log log = LogFactory.getLog(AlertDemo.class);
	
	

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"./lib/driver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test1(){
		PropertyConfigurator.configure("./conf/log4j.properties");
		InitMessage.setTestMessage("Testing", AlertDemo.class);
		driver.get("file:///D:/demo.html");
		driver.findElement(By.xpath("//div[@id='wait']/input")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		ExpectedCondition<Boolean> textAppeared = ExpectedConditions.textToBePresentInElement(By.xpath("//div[@id='display']/div"), "wait for display");
		if(wait.until(textAppeared)){
			log.info("在页面上出现要查找的文字");
		}else {
			log.error("指定的文字没有出现在页面上 ");
		}
	}

	@Test
	public void test2(){
		PropertyConfigurator.configure("./conf/log4j.properties");
		InitMessage.setTestMessage("Testing", AlertDemo.class);
		driver.get("file:///D:/demo.html");
		driver.findElement(By.xpath("//div[@id='wait']/input")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("//div[@id='display']/div"));
			}
			
		});
		
		if(element.getText().contains("wait for display")){
			
			log.info("---在页面上出现要查找的文字---");
		}else {
			log.error("--指定的文字没有出现在页面上--- ");
		}
		
		
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.quit();
	}
}
