package com.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 * vb脚本的内容
Dim $filePath = "C:\2.jpg"
Dim $dialogTitle = "打开"
WinActivate($dialogTitle)
WinWaitActive($dialogTitle)
Sleep(1*1000)
ControlSetText($dialogTitle,"","Edit1",$filePath)
ControlClick($dialogTitle,"","Button1")
Sleep(1*1000)
 * 
 * */


public class Upload {
	WebDriver driver = null;

	@BeforeTest
	public void beforeTest() {
		//Chrome浏览器
		System.setProperty("webdriver.chrome.driver", "./plugin/driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		//IE浏览器
		/*System.setProperty("webdriver.ie.driver", "./plugin/driver/IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(ieCapabilities);*/
	}

	@Test
	public void autoIT_Upload() throws InterruptedException {
		driver.get("file:///D:/demo.html");
		//定位“选择文件”按钮
		WebElement loadBtn = driver.findElement(By.id("load"));
		//点击该按钮，打开选择文件的Windows弹窗
		loadBtn.click();
		//指定脚本文件的位置
		String path = "D:/upload.exe";
		execShell(path);
		Thread.sleep(6000);
	}

	@AfterTest
	public void afterTest() {
		if(driver != null)
			driver.close();
	}
	
	//定义一个工具类，执行Autoit的脚本文件
	
	public static void execShell(String path){
		Runtime r = Runtime.getRuntime();
		try {
			r.exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
