package com.syntax.class01;

import org.testng.annotations.Test;

public class TestNGDependencies {
	@Test
	public void login() {
		System.out.println("Test that logges into teh application and has login steps");
	}

	@Test(dependsOnMethods = "login")
	public void checkReport() {
		System.out.println("Test that checkers reports and has navigation to the report steps");
	}
}
