package steps;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.PicoTestContext;

public class AmazonSearchStepDefenition {

	public PicoTestContext picoTestContext;
	public WebDriver driver;
	public String url = "https://www.amazon.in/";

	public AmazonSearchStepDefenition(PicoTestContext picoTestContext) {
		this.picoTestContext = picoTestContext;
		driver = picoTestContext.browserFactory.invokeBrowser();
	}

	@Given("User should navigate to Ebay page")
	public void user_should_navigate_to_ebay_page() {
		driver.get(url);
		System.out.println(driver.getTitle());

	}

	@When("User should enter the product and select the category")
	public void user_should_enter_the_product_and_select_the_category() {
		WebElement search, category;

		search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		search.sendKeys("Oneplus");

		category = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		Select select = new Select(category);
		select.selectByVisibleText("Electronics");
	}

	@When("User should enter the product and select the category from below list")
	public void user_should_enter_the_product_and_select_the_category_from_below_list(DataTable dataTable) {
		List<Map<String, String>> inputs = dataTable.asMaps();
		WebElement search, category;

		for (Map<String, String> input : inputs) {
			search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			search.sendKeys(input.get("productName"));
			System.out.println(input.get("productName"));

			category = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
			Select select = new Select(category);
			select.selectByVisibleText(input.get("productCategory"));
			System.out.println(input.get("productCategory"));
			
			search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			search.clear();
		}
		
	}
	
	@When("^User should enter the product (\\w+) and select the category ([^0-9]+) from feature$")
	public void user_should_enter_the_product_iphone_and_select_the_category_electronics_from_feature(String productName, String productCategory) {
		WebElement search, category;

		search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		search.sendKeys(productName);

		category = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		Select select = new Select(category);
		select.selectByVisibleText(productCategory);
	}

	@When("User click on search buttom")
	public void user_click_on_search_buttom() {
		WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit']"));
		searchButton.click();

		WebElement search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		search.clear();
	}

	@Then("Validate the search result")
	public void validate_the_search_result() {

		WebElement wholeResult = driver.findElement(By.xpath(
				"//span[@data-component-type='s-result-info-bar']//div[@class='a-section a-spacing-small a-spacing-top-small']"));
		String result = wholeResult.getText();
		String[] split1 = result.split("of");
		String[] split2 = split1[0].split("-");
		String split3 = split2[1].replace(" ", "");
		int finalCount = Integer.parseInt(split3);
		System.out.println("Number of results are " + finalCount);
		if(finalCount==24) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}
	}

}
