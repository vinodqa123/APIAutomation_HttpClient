package com.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class IRCTCS {

	@Test()
public void test() throws InterruptedException {
	
		
		System.setProperty("webdriver.chrome.driver","D:\\Vinod\\Eclipse\\Chrome\\chromedriver_win32\\chromedriver.exe");  
        
        // Instantiate a ChromeDriver class.     
		WebDriver driver=new ChromeDriver();  
		driver.navigate().to("https://www.irctc.co.in/nget/train-search");  
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
		
		Thread.sleep(5000);
		driver.close();
		
	}
	
}
