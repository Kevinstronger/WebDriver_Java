/**
Firefox的信息：
mozilla/5.0 (windows nt 6.1; wow64; rv:29.0) gecko/20100101 firefox/29.0

Chrome的信息：
mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/35.0.1916.114 safari/537.36

IE的信息：
mozilla/4.0 (compatible; msie 8.0; windows nt 6.1; trident/4.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0; infopath.3; .net4.0c; .net4.0e)

*/

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Test1 {
	WebDriver driver = null;

	

	@BeforeTest
	public void beforeTest() {
		/*System.setProperty("webdriver.chrome.driver",
				"./lib/driver/chromedriver.exe");
		driver = new ChromeDriver();*/
		
		driver = new FirefoxDriver();
	}
	
	@Test
	public void test() {
		driver.get("http://www.baidu.com");
		System.out.println(getBrowserInfo(driver));
	}

	@AfterTest(alwaysRun=true)
	public void afterTest() {
		driver.close();
	}
	
	public String getBrowserInfo(WebDriver driver) {
		String js = "return navigator.userAgent.toLowerCase();";
		String value = (String)((JavascriptExecutor) driver).executeScript(js);
		String browser = "当前启动的浏览器是： ";
		String version = ",版本为：";
		if (value.contains("firefox")) {
			browser += "Firefox ";
			version += value.split("firefox/")[1];
		} else if (value.contains("msie")) {
			browser += "IE ";
			version += value.split("msie")[1].split(";")[0];
		} else if (value.contains("chrome")) {
			browser += "Chrome ";
			version += value.split("chrome/")[1].split(" ")[0];
		}  else {

			return "当前浏览器尚未处理";
		}
		return browser + version;
	}
	
}
