package com.test;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwithWindow {
	WebDriver driver = null;
	ArrayList<String> list = new ArrayList<String>();
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"./lib/driver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test3() throws InterruptedException {
		driver.get("file:///D:/demo.html");
		String oriWin = driver.getWindowHandle();
		System.out.println("原始窗口为："+oriWin);
		list.add(oriWin);
		driver.findElement(By.xpath("//div[@id='open']/a")).click();
		Thread.sleep(1000);
		Set<String> handles = driver.getWindowHandles();
		for(String handle : handles) {
			if(list.indexOf(handle) == -1) {
				WebDriverWait wait = new WebDriverWait(driver, 3);
				wait.until(new ExceptWindow(handle));
				System.out.println(handle);
				list.add(handle);
			}
		}
		System.out.println("已经切换到了"+ driver.getWindowHandle());
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest() {
		driver.quit();
	}
	
	static class ExceptWindow implements ExpectedCondition<WebDriver> {
		private String id;
		public ExceptWindow(String id) {
			this.id = id;
		}

		@Override
		public WebDriver apply(WebDriver d) {
			return d.switchTo().window(id);
		}
	}
}


