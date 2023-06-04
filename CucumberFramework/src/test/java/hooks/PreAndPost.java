package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import util.PicoTestContext;

public class PreAndPost {

	public PicoTestContext picoTestContext;
	public WebDriver driver;

	public PreAndPost(PicoTestContext picoTestContext) {
		this.picoTestContext = picoTestContext;
		driver = picoTestContext.browserFactory.invokeBrowser();
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

	@AfterStep
	public void afterSteps(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshotAsBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshotAsBytes, "image/png", "failureScreen");
		}
	}
}
