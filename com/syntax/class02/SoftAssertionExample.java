package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionExample {
	public static WebDriver driver;

	public static String url = "http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
//		driver.manage().window().maximize();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void invalidLoginError() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("qwerty123");
		driver.findElement(By.id("btnLogin")).click();

		SoftAssert softAssertion = new SoftAssert();
		String expectedErrorMessage1 = "Invalid credential";
		WebElement errorMassage = driver.findElement(By.id("spanMessage"));

		softAssertion.assertEquals(errorMassage.getText(), expectedErrorMessage1);

		Thread.sleep(2000);
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("btnLogin")).click();

		String expectedErrorMessage2 = "Password cannot be empt";
		softAssertion.assertEquals(errorMassage.getText(), expectedErrorMessage2);
		Thread.sleep(2000);
		
		softAssertion.assertAll();// throw all assertions , must be executed to see fail

	}
}
