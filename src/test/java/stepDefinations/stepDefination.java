package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ap.pom.AddToCart_Page;
import com.ap.pom.CheckoutPage;
import com.ap.pom.DashboardPage;
import com.ap.utils.WaitNew;
import com.ap.utils.initBrowser;
import com.ap.utils.stringManipulationFunc;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;


//@RunWith(Cucumber.class)
public class stepDefination extends initBrowser {
	static DashboardPage dp;
	static AddToCart_Page acp;
	static Map<String, Double> hmap = new HashMap<>();;
	static CheckoutPage cp;
	
	public static void object_init(WebDriver driver) {
		dp = new DashboardPage(driver);
		acp = new AddToCart_Page(driver);
		cp = new CheckoutPage(driver, hmap);
	}

	@Given("Intialize the browser with {string}")
	public void intialize_the_browser_with(String arg) {
		driver = startBrowser(arg);
	}

	@And("Navigate to {string} site")
	public void navigate_to_site(String url) {
		object_init(driver);
		driver.get(url);
		String text = dp.dashbaordPage_afterloggedin().getText();
		Assert.assertEquals("Sign in", text);
	}

	@When("^User searches the (.+) in global search$")
	public void user_searches_the_in_global_search(String string) {
		object_init(driver);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(dp.globalSearch().isDisplayed());
		dp.globalSearch().sendKeys(string);
		WaitNew.explicitWaitPresenceofElement(driver, dp.getGlobalSearch_container());
		sa.assertAll();
	}

	@And("^User clicks on the product (.+)$")
	public void user_clicks_on_the_product(String arg) {
		object_init(driver);
		List<WebElement> elements = dp.listOfItems();
		for (WebElement e : elements) {
			if (e.getText().contains(arg))
				e.click();
			else
				System.out.println("no product is found");
		}
	}

	@And("^User selects the require (.+) & (.+) for the (.+)$")
	public Map<String, Double> user_selects_the_require(String string, String string2, String arg) {
		String price_text = "";
		double product_price;
		object_init(driver);
		acp.quantity_select().clear();
		acp.quantity_select().sendKeys(string2);

		driver.findElement(acp.size_dropdown_field()).click();
		Select select = acp.selectDrodpwn_option(string);
		select.selectByVisibleText(string);
		price_text = acp.product_price().getText();
		product_price = stringManipulationFunc.getDouble_value_from_String(price_text);
		int data = stringManipulationFunc.String_to_integer(string2);
		if (hmap.containsKey(arg))
			hmap.put(arg, hmap.get(arg) + product_price);
		else
			hmap.put(arg, product_price*data);
		return hmap;
	}

	@When("User adds the product into the cart by clicking in Add to cart button")
	public void user_adds_the_product_into_the_cart_by_clicking_in_add_to_cart_button() {
		object_init(driver);
		acp.product_add();
		acp.click_continue_shopping();
		WaitNew.explicitWaitVisibilityeofElement(driver, dp.getSearch_field());
	}

	@Then("^Verify that the (.+) is successfully added in cart$")
	public boolean verify_that_the_is_successfully_added_in_cart(String string) {
		object_init(driver);
		List<WebElement> elements = acp.listOfItems();
		for (WebElement e : elements) {
			System.out.println(e.getAttribute("title"));
			if (e.getAttribute("title").contains(string))
				return true;
		}
		return false;
	}

	@Then("User navigates to checkout page")
	public void user_navigates_to_checkout_page() {
		double total_price_of_each_item = cp.Combinedprice_of_all_products();
		double total_cart_value = cp.click_on_cart();
		System.out.println(total_price_of_each_item);
		System.out.println(total_cart_value);
		Assert.assertTrue(total_price_of_each_item==total_cart_value);
	}
	
	@Then("Close the browser")
	public void close_the_browser() {
		driver.quit();
	}
}
