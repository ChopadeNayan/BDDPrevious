package com.ecommerce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ecommerce.pages.HomePage;


public class BaseClass {
		public static WebDriver driver ;
	    static FileInputStream fis=null;
	    public static  Logger log = Logger.getLogger(BaseClass.class);
	
	    public static String readProperty(String key) 
	    {
		     log.info("reading a property from property file");
		     Properties prop= new Properties();
		     try
		     {
		    	 fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
		    	 prop.load(fis);		
		     }
		     catch(FileNotFoundException e) 
		     {
		    	 e.printStackTrace();
		     }
		     catch(Exception e) 
		     {
		    	 e.printStackTrace();
		     }
		     log.info("Value found in the property file for the "+key);
		     return prop.getProperty(key);
	    }
	    public static void launchApplication() 
	    {
	    	log.info("launching an application");
	    	driver.get(readProperty("url"));
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    }
	    public static WebDriver initialization() 
	    {
	    	log.info("initialising a browser with name"+readProperty("browser"));
	    	if (readProperty("browser").equals("chrome")) 
	    	{
	    		System.setProperty("webdriver.chrome.driver", "chromedriverAutoIt.exe");
	    		driver = new ChromeDriver();
	    		launchApplication();
	    		log.info("chorme browser initialised");
	    		return driver;
	    	}
	    	else
	    	{
	    		System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
	    		driver = new FirefoxDriver();
	    		log.info("firefox browser initialised");
	    		launchApplication();
	    		return driver;
	    	}
	    }
	    
	    public HomePage loadHomepage() {

	    	HomePage hP = new HomePage(driver);
			return hP;
		}
	    
//	    public HomePage loadHomePageSignIn(WebElement signIn) {
//
//	    	HomePage hPSignIn = new HomePage(driver);
//			return hPSignIn;
//		}

	
////////////////////////////////////////////////////////////////////////////////////////////	
//	public LoginPage loadLoginpage()
//	{
//		
//		LoginPage lp=new LoginPage(driver);
//		return lp;
//	}
}
