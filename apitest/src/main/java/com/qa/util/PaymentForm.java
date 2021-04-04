package com.qa.util;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PaymentForm {
	
	static String CardNumber="4242424242424242";
	static String Exp_Month="02";
	static String Exp_Year="2022";
	static String CVV="123";
	public static void InitiatePayment(String PaymentURL,String Amount) throws Exception {
		
		System.setProperty("webdriver.chrome.driver","D:\\Vinod\\Eclipse\\Chrome\\chromedriver_win32\\chromedriver.exe");  
        
        // Instantiate a ChromeDriver class.     
		WebDriver driver=new ChromeDriver();  
		driver.navigate().to(PaymentURL);  
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.findElement(By.id("CardNumber")).sendKeys(CardNumber);
		Select Expiry_Month = new Select(driver.findElement(By.id("ExpMonth")));
		Expiry_Month.selectByVisibleText(Exp_Month);
		Select Expiry_Year = new Select(driver.findElement(By.id("ExpYear")));
		Expiry_Year.selectByVisibleText(Exp_Year);
		driver.findElement(By.id("CardCvv")).sendKeys(CVV);
		driver.findElement(By.id("CardHolderName")).sendKeys("John Smith Holder");
		WebElement amount=driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[2]/div[1]/form[1]/div[1]/div[4]/div[1]/input[1]"));
		System.out.println("Collect Card Page Amount:"+amount.getAttribute(Amount));
		
		driver.findElement(By.xpath("//body/div[4]/div[1]/div[2]/div[1]/form[1]/div[2]/div[1]/button[1]")).click();
		TakeScreenshots.takeSnapShot(driver);
		Thread.sleep(5000);
		driver.close();
		
	}
	

}
