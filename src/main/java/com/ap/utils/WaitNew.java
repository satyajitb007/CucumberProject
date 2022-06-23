package com.ap.utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitNew {
	static WebDriverWait wait;
	public static void untilJqueryIsDone(WebDriver driver, Duration timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
			if (!isJqueryCallDone)
				System.out.println("JQuery call is in Progress");
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}


	public static void untilPageLoadComplete(WebDriver driver, Duration timeoutInSeconds) {
		until(driver, (d) -> {
			Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState")
					.equals("complete");
			if (!isPageLoaded)
				System.out.println("Document is loading");
			return isPageLoaded;
		}, timeoutInSeconds);
	}


	@SuppressWarnings("deprecation")
	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Duration timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void waitForObject(WebDriver driver)

	{
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	}

	public static void waitForPageLoading(WebDriver driver)

	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	public static void waitForAlert(WebDriver driver) throws InterruptedException

	{
		int i = 0;
		while (i++ < 5)
			try {
				Alert alert = driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
				WaitNew.untilJqueryIsDone(driver, Duration.ofSeconds(30));
				System.out.println("Accepted the alert successfully.");
				break;
			} catch (NoAlertPresentException e)

			{
				System.err.println("Error came while waiting for the alert popup. " + e.getMessage());
				Thread.sleep(1000);
				continue;
			}
	}
	
	public static void explicitWaitPresenceofElement(WebDriver driver, By ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(ele));
	}
	public static void explicitWaitVisibilityeofElement(WebDriver driver, By ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(ele));
	}
	public static void explicitWaitElementToBeClickable(WebDriver driver, By ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
	}
}
