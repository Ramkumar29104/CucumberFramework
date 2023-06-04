package util;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserFactory {

	public WebDriver driver;
	public int browser = 1;

	public WebDriver invokeBrowser() {
		if (driver == null) {
			System.out.println("Choose the Browser");
			System.out.println("1.Chrome");
			System.out.println("2.Firefox");

			switch (browser) {
			case 1:
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				System.setProperty("webdriver.http.factory", "jdk-http-client");
				driver = new ChromeDriver();
				if(browser==1) {
					System.out.println("Browser input is " + browser + ".So, Invoking the Chrome Browser");
				}else {
					System.out.println("Browser input is " + browser + ".So, Invoking the Firefox Browser");
				}
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				break;

			case 2:
				System.out.println("User Input is " + 2 + ". So invoking Firefox Browser");
				driver = new FirefoxDriver();
				break;
			}
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
}
