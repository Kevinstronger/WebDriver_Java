	public void beforeTest() {
		//Chrome浏览器
		System.setProperty("webdriver.chrome.driver", "./plugin/driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		//IE浏览器
		System.setProperty("webdriver.ie.driver", "./plugin/driver/IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(ieCapabilities);
	}
