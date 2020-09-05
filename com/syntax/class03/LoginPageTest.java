package com.syntax.class03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest {
	public static WebDriver driver;

	public static String url = "http://166.62.36.207/humanresources/symfony/web/index.php/auth/login";

	@BeforeMethod(alwaysRun = true)
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);
//		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	
	@Test(dataProvider="getData")
	public void multipleLogin(String username, String password) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		String welcomeText = driver.findElement(By.id("welcome")).getText();
		Assert.assertTrue(welcomeText.contains(username.subSequence(0, 3)));
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] data= {
			{"Admin", "Hum@nhrm123"},
			{"JohnTest", "Syntax123!"},
			{"KokaL", "Kokaytvyn$12345"}
		};
		return data;
	}
}
