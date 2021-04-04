package com.qa.util;

import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TakeScreenshots {
	
	static String Scrshot_CollectCardPage="Payment";
	public static void takeSnapShot(WebDriver webdriver) throws Exception{
		
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(webdriver);

		ImageIO.write(screenshot.getImage(), "jpg", new File("D:\\Vinod\\Eclipse\\Screenshots\\"+Scrshot_CollectCardPage+"ElementScreenshot.jpg"));

    }

}
