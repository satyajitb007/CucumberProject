package com.ap.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class initBrowser {
	protected static WebDriver driver;
	WebDriverWait wait;

	public static WebDriver startBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("mozilla")) {
			driver = WebDriverManager.firefoxdriver().create();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			driver = WebDriverManager.chromedriver().create();
		} else if (browserName.equalsIgnoreCase("IE")) {
			driver = WebDriverManager.iedriver().create();
		}

		WaitNew.waitForPageLoading(driver);
		driver.manage().window().maximize();
		System.out.println("Browxer is up and running");
		return driver;

	}

}
