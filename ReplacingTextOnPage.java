package Selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ReplacingTextOnPage {

	@Test
	public void Read_UI_Values() throws Exception
	{
		
		System.setProperty("webdriver.chrome.driver","C:\\Gopal\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		driver.get("http://joesnypizzalv.com/");
		String textToBePresent = "INTERNATIONAL AWARD WINNING PIZZERIA";
		WebElement slogan;
		
		try{
			
			boolean flag = driver.getPageSource().contains(textToBePresent);
			if(flag)
			{
				slogan=driver.findElement(By.xpath("//div[@class='slogan']"));
				System.out.println("Slogan Text Before Changing: "+slogan.getText());
				
				//update new text
				((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('slogan')[0].innerHTML = 'Welcome to Test<br/><center>2013, 2014, 2015</center>';");
				System.out.println("Slogan Text After Changing: "+slogan.getText());
				Thread.sleep(5000); //Waiting to see the change of text and to take screenshot
				TakesScreenshot snap = ((TakesScreenshot) driver);
				File src = snap.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File("C:\\Gopal\\ScreenAfterChangingSlogan.jpg"));
				System.out.println("Screenshot has been taken");
			}
			else
				System.out.println("Given Text is not present on webpage");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		driver.quit();
	}
}
