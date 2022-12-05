package com.tdc.lms.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static ChromeOptions opt ;
	public static URL url;
	public static DesiredCapabilities caps;
	public static AppiumDriverLocalService service;
	public static UiAutomator2Options options ;
//	public static WebDriver driver;
				
	@BeforeTest(alwaysRun = true)
	@Parameters({"browserName","platform"})
	public void initializeDriver(String browserName,String platform)  throws IOException {

		//properties class
		Properties prop = new Properties();                              
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tdc\\\\lms\\resources\\GlobalData.properties");
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		
	//	String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(platform.equalsIgnoreCase("mobile")) {
			if(browserName.equalsIgnoreCase("chrome")) {
				options = new UiAutomator2Options()
					//	.setBuildToolsVersion("28.0.3")
						.setDeviceName("ZY224R7FX5")
						.setPlatformName("Android")	
						.eventTimings();		
			//	options.setCapability(MobileCapabilityType.AUTO_WEBVIEW,true);
				options.setCapability(MobileCapabilityType.NO_RESET,true);
				options.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.android.chrome");
				options.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chrome.Main");
			//	opt.setExperimentalOption("w3c",true);
			//	options.setApp(System.getProperty("user.dir") + "/VodQA.apk");
				
				service = new AppiumServiceBuilder()
					       .withIPAddress("127.0.0.1")
					       .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
					       .usingPort(4723)
					       .build();
					service.start();
				
//				url = new URL("http://0.0.0.0:4723/wd/hub");
			//	WebDriverManager.chromedriver().setup();			
			//	opt.setExperimentalOption("w3c",false);
				driver.set(new AndroidDriver(service, options));	
				
			//	opt.setExperimentalOption("w3c",false);
				
				
			}	
			}
		
		
		  else { 
			  if(browserName.equalsIgnoreCase("chrome")) {		  
				  WebDriverManager.chromedriver().setup(); 
				  driver.set(new ChromeDriver()); 
			  }
			  else if(browserName.equalsIgnoreCase("edge")){
				  WebDriverManager.edgedriver().setup(); 
				  driver.set(new EdgeDriver()); 
			  } 
			  else if(browserName.equalsIgnoreCase("IE")) { 
				  WebDriverManager.iedriver().setup();
				  driver.set(new InternetExplorerDriver());
			  } 
			  else if(browserName.equalsIgnoreCase("firefox")) {
				  WebDriverManager.firefoxdriver().setup();
				  driver.set(new FirefoxDriver()); 
			  }
			  else if(browserName.equalsIgnoreCase("safari")) {
				  WebDriverManager.safaridriver().setup(); 
				  driver.set(new SafariDriver()); 
			  }
		  }
		 
		
		
		
//		else if(browserName.equalsIgnoreCase("android")) {	
							
//			options.setPlatformName("Android");
		//	caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9");
//			options.setAndroidDeviceSerialNumber("ZY224R7FX5");			
//			options.setBrowserVersion("107");
		//	caps.setCapability(ChromeOptions.CAPABILITY, options);
		//	options.merge(caps);
						
			//Appium server details
			
//			service = new AppiumServiceBuilder()
//				       .withIPAddress("127.0.0.1")
//				       .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
//				       .usingPort(4723)
//				       .build();
//				service.start();

	//		URL url = new URL("http://localhost:4723/wd/hub");
//			driver.set(new AndroidDriver(service, options));
//		}
				

	
	//	driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	driver.get().manage().window().maximize();
	}
	
	  	  	  	 			 	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.get().quit();
	}	  	 	

}