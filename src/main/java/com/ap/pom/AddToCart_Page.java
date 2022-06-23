package com.ap.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddToCart_Page {
	
	public WebDriver driver;
	By quantity_field = By.id("quantity_wanted");
	By size_dropdown_field = By.xpath("//*[@id='attributes']//following::div[@id='uniform-group_1']/select");
	By add_to_cart_button = By.xpath("//p[@id='add_to_cart']/button");
	By cart_products_loc = By.xpath("//dl[@class='products']/dt/a");
	By continue_shopping_ele = By.xpath("//*[@id=\"layer_cart\"]//following-sibling::div/span[@title='Continue shopping']");
	By price_ele = By.id("our_price_display");
	By product_price = By.xpath("//span[@id='our_price_display']");
	
	public AddToCart_Page(WebDriver driver2) {
		this.driver = driver2;
	}
	public WebElement quantity_select() {
		return driver.findElement(quantity_field);
	}
	
	public WebElement product_price() {
		return driver.findElement(product_price);
	}
	
	public void product_add() {
		driver.findElement(add_to_cart_button).click();
	}
	public Select selectDrodpwn_option(String str) {
		Select select = new Select(driver.findElement(size_dropdown_field));
		return select;
	}
	public By size_dropdown_field() {
		return size_dropdown_field;
	}
	public void size_dropdown_field(By size_dropdown_field) {
		this.size_dropdown_field = size_dropdown_field;
	}
	
	public List<WebElement> listOfItems(){
		return driver.findElements(cart_products_loc);
		
	}
	
	public void click_continue_shopping() {
		driver.findElement(continue_shopping_ele).click();
	}
}
