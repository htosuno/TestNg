package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task1 {
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
	public void test() throws InterruptedException {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("Hum@nhrm123");
		driver.findElement(By.id("btnLogin")).click();
//		Thread.sleep(1000);
		driver.findElement(By.linkText("PIM")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Add Employee")).click();
		Thread.sleep(2000);
		WebElement fullName=driver.findElement(By.className("hasTopFieldHelp"));
		WebElement employeeId= driver.findElement(By.xpath("//label[@for='employeeId']"));
		WebElement photograph= driver.findElement(By.xpath("//label[@for='photofile']"));

		
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertTrue(fullName.isDisplayed(), "Full Name label is not displayed");;
		softAssertion.assertTrue(employeeId.isDisplayed(), "Employee ID label is not displayed");;
		softAssertion.assertTrue(photograph.isDisplayed(), "Photograph label is not displayed");;
		
		driver.findElement(By.id("firstName")).sendKeys("John");
		driver.findElement(By.id("lastName")).sendKeys("Wick");
		driver.findElement(By.id("photofile")).sendKeys("C:\\Users\\htosu\\Downloads\\john-wic.jpg");
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(3000);
		String name=driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
		softAssertion.assertEquals(name, "John", "Employee not added succesfully");
		
		softAssertion.assertAll();

		


		
		
	}

}
