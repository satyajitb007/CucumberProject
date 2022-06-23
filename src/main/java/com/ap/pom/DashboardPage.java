package com.ap.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
	public WebDriver driver;
	private By signInButton = By.xpath("//a[contains(text(),'Sign in')]");
	private By search_field = By.id("search_query_top");
	private By globalSearch_container = By.cssSelector("div.ac_results>ul>li");
	
	public DashboardPage(WebDriver driver2) {
		this.driver = driver2;
	}
	
	public WebElement dashbaordPage_afterloggedin() {
		return driver.findElement(signInButton);
	}
	
	public WebElement globalSearch() {
		return driver.findElement(search_field);
	}
	
	public List<WebElement> listOfItems(){
		return driver.findElements(getGlobalSearch_container());
		
	}

	public By getGlobalSearch_container() {
		return globalSearch_container;
	}

	public void setGlobalSearch_container(By globalSearch_container) {
		this.globalSearch_container = globalSearch_container;
	}
	public By getSearch_field() {
		return search_field;
	}

	public void setsearch_field(By search_field) {
		this.search_field = search_field;
	}
	}

