package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HardAssertionExample {
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

	@Test(groups="regression")
	public void titleValidation() {
		String expectedTitle = "Human Management Systems";
		String actualTitle = driver.getTitle();// "Human Management System"
//		System.out.println("titleValidation");		
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("-- Code after assertion --");
		
	}

	@Test(groups="regression")
	public void logoValidation() {
		WebElement element = driver.findElement(By.xpath("//div[@id='divLogo']/img"));
		System.out.println("logoValidation");
		
		Assert.assertTrue(element.isDisplayed());
			}

	@Test(groups="regression")
	public void loginFormText() {
		String expectedText="LOGIN Panel";
		WebElement loginForm=driver.findElement(By.id("logInPanelHeading"));
		System.out.println("loginFormText");
		
		Assert.assertEquals(loginForm.getText(), expectedText, "Text in the Login panel is not matching");
		
	}

}
