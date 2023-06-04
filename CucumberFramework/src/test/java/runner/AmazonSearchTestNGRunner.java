package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "./src/test/resources/Features/AmazonSearch.feature",
		glue = {"steps","hooks"},
		monochrome = true,
		dryRun = false,
		tags = "@Smoke",
		plugin = {"pretty", "html:target/Reports/Search.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class AmazonSearchTestNGRunner extends AbstractTestNGCucumberTests {
	
	
}
