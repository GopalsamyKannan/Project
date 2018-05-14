package com.appium.android;

/*
 * DesiredCapabilities - https://appium.io/docs/en/writing-running-appium/caps/
 * 
 * program working fine on Demo Emulator2 
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class UpdateApp {

	public static void main(String[] args) throws MalformedURLException {

		// Create object of DesiredCapabilities class                             
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("automationName", "Appium"); //Specify name of the automation

		cap.setCapability("platformName", "Android"); // platform name
		
		cap.setCapability("deviceName","XT1063"); //Specify the device name 
		
		cap.setCapability("udid", "ENUL6303030010"); //Give Device ID of your mobile phone

		cap.setCapability("platformVersion", "6.0"); //Platform version
		
		cap.setCapability("app", "<apk file path>"); //Specify apk file to be installed
		
		cap.setCapability("appPackage", "io.selendroid.testapp"); // specify the application package that we copied from appium

		cap.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity"); // specify the application activity that we copied from appium
		
		cap.setCapability("noReset", "true"); //To avoid clearing the Android local data
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap); 
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Do some Actions
		
		//Since i am not sure straight forward command to reinstall the app by keeping data, below methods can be used to update
		
		//Reinstall an existing app & keeping its data by 'adb' command
		//adb install -r <new_apk_file_path>
		
		//Traditional way - uninstall & install by 'adb' command 
		//adb uninstall <old apk file>
		//adb install <new apk file path>
		
		//by driver
		//driver.installApp("<apk file path>");
		//driver.removeApp("<packageName as String>")
		
		//starting the appium server by 'fullReset' option
		//with fullReset option,we can always uninstall the app and then automatically install the new version.
		
		//it uninstall the app and re-installs each time you re-run your suite
		//cap.setCapability("noResetValue","false");
		
	}
}
