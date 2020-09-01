package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dependency {
	public static WebDriver driver;

	public static String url = "http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";

	@BeforeMethod
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test(groups="smoke")
	public void validLogin() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
		String welocomeText = driver.findElement(By.id("welcome")).getText();

		if (welocomeText.contains("Admin")) {
			System.out.println("Admin is logged in. Test pass");
		} else {
			System.out.println("Admin is NOT logged in. Test fail");
		}
	}

	@Test(groups="regression", dependsOnMethods= "titleValidation")
	public void invalidLogin() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("btnLogin")).click();
		String messageText = driver.findElement(By.id("spanMessage")).getText();

		if (messageText.contains("Password cannot be empty")) {
			System.out.println("Message is displayed. Test pass");
		} else {
			System.out.println("Message is NOT displayed. Test fail");
		}
	}

	@Test(groups="regression")
	public void titleValidation() {
		String expectedTitle = "Human Management System";
//		String actualTitle = driver.getTitle();
		String actualTitle = driver.findElement(By.id("spanMessage")).getText();

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Titles match. Test pass");
		} else {
			System.out.println("Titles DO NOT match. Test fail");
		}
	}

}
