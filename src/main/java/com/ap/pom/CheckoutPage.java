package com.ap.pom;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ap.utils.stringManipulationFunc;


public class CheckoutPage {
	public WebDriver driver;
	By checkout_button = By.xpath("//a[@title=\"View my shopping cart\"]");
	By total_product = By.xpath("//table[@id=\"cart_summary\"]/tfoot/tr/td[@id=\"total_product\"]");
	private Map<String, Double> hmap;
	
	public CheckoutPage(WebDriver driverR, Map<String, Double> hmapR) {
		this.driver=driverR;
		this.hmap=hmapR;
	}
	
	public double Combinedprice_of_all_products() {
		double sumPrice_of_individual_product = 0;
		System.out.println(hmap);
		for (String key: hmap.keySet()) {
		    System.out.println("key : " + key);
		    sumPrice_of_individual_product+=hmap.get(key);
		}
		return sumPrice_of_individual_product;
	}
	public double click_on_cart() {
		driver.findElement(checkout_button).click();
		String totalAmount_text = driver.findElement(total_product).getText();
		double total_cart_price = stringManipulationFunc.getDouble_value_from_String(totalAmount_text);
		return total_cart_price;
	}

}
