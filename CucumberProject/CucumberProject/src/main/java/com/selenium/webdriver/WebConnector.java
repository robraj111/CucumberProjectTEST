package com.selenium.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.reports.ExtentManager;

public class WebConnector {
	
	WebDriver driver=null;
	public Properties prop;
	public ExtentReports rep;
	public ExtentTest test;
	
// ******************  Initialise Properties object in constructor    ******************************************
	public WebConnector() {
		if(prop==null) {
			
	   try {
		prop=new Properties();
		FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
	    prop.load(fs);
	 
	   }catch(IOException e) {
		   e.printStackTrace();
	    }
	   
	 }
  }
	
// ******************  Open Browser     ******************************************
	
	public void openBrowser(String browser) {
		browser=prop.getProperty(browser);
		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Ravibabu\\Selenium\\Browsers\\Test\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(browser.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Ravibabu\\Selenium\\Browsers\\Test\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browser.equals("IE"))
			driver=new InternetExplorerDriver();
		else if (browser.equals("Edge"))
			driver=new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
//***********************  Launch app URL ***************************************
	public void launchUrl(String url) {
		driver.get(prop.getProperty(url));
		
		
	}

// ******************  Click function     ******************************************
	
	public void click(String locatorKey) {
		pauseTest(2);
		getObject(locatorKey).click();
	}
	
	
// ******************  Type value function     ******************************************
	
	public void type(String locatorKey, String data) {
		
		getObject(locatorKey).sendKeys(prop.getProperty(data));
	}
	
	// ******************  clear selected text from test field  ******************************************
	
		public void clearText(String locatorKey) {
			getObject(locatorKey).clear();
			
		}
	
//************************* quit browser *************************************
	public void quitBrowser() {
		if(driver!=null)
			driver.quit();
	}
	
//************************ Thread.slpeep ( passe execution)**************************************

	public void pauseTest(int TimeInSec) {
		
		try {
			Thread.sleep(TimeInSec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
//***********************  Find element function     **************************************	
	
  public WebElement getObject(String locatorKey) {
		WebElement e=null;
		WebDriverWait wait= new WebDriverWait(driver,10);
	  
		try {
			if(locatorKey.contains("_xpath")) {
			e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(locatorKey))));
			}
			else if(locatorKey.contains("_id")) {
			e=driver.findElement(By.id(prop.getProperty(locatorKey)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(locatorKey))));
			}
			else if(locatorKey.contains("_name")) {
			e=driver.findElement(By.name(prop.getProperty(locatorKey)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(locatorKey))));
			}
			else if(locatorKey.contains("_css")) {
				e=driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(locatorKey))));
				}
			else {
			System.out.println("Locator name is not exists==> "+locatorKey);	
			reportFailure("Locator name not exist : "+locatorKey );
		    }
			
	    } catch(Exception ex) {
			ex.printStackTrace();
			reportFailure("Could not found locator : "+locatorKey);
		}
		return e;
		
	}
	
  
  
 // ************   Check whether element is present or not  ***************************
  
    public boolean isElementPresent(String locatorKey) {
    	
    	List<WebElement> e=null;
    	try {
			if(locatorKey.contains("_xpath")) 
			e=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
			
			else if(locatorKey.contains("_id")) 
			e=driver.findElements(By.id(prop.getProperty(locatorKey)));
			
			else if(locatorKey.contains("_name")) 
			e=driver.findElements(By.name(prop.getProperty(locatorKey)));
			
			else if(locatorKey.contains("_css")) 
				e=driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
				
			else {
			System.out.println("Locator name is not exists==> "+locatorKey);
			reportFailure("Locator name is not exists==> "+locatorKey);
			
		    }
			
	    } catch(Exception ex) {
			ex.printStackTrace();
			reportFailure("Could not found locator==> "+locatorKey);
		}
    	
    	if(e.size()==0)
    		return false;
    	else
    				
    	return true;
    	
    }
  
 //************************** Login function ***************************************************
    public void doLogin(String username, String password) {
    	reportInfo("login to rediffmoney");
    	
    	openBrowser("browser");
    	launchUrl("RediffMoney");
    	click("loginLink_xpath");
       	type("username_xpath",username);
       	type("password_xpath",password);
    	click("submitButton_xpath");
    	
    	reportInfo("Logged in successfully");
    	    	
    }
  
    
    public void acceptAlert() {
    	
    	WebDriverWait wait = new WebDriverWait(driver,10);
    	wait.until(ExpectedConditions.alertIsPresent());
    	
    	driver.switchTo().alert().accept();
    	
    }
    
    
    
    
  /*#########################################################################################
    
             *******************APP RELATED FUNCTIONS **************
  
  ###########################################################################################*/
  
    
 //********************************** Login validation********************************
    
    public void loginValidation(String expectedResult) {
    	
    	boolean result=isElementPresent("accountNmae_xpath");
    	String actualResult="";
    	if(result)
    		actualResult="success";
    	else
    		actualResult="failure";
    	
    	if(!expectedResult.equals(actualResult))
    		reportFailure("Login is not success");    	
    }
 
 // *******************  Logout validation **************************************************
    
public void logoutValidation(String expectedResult) {
    	
    	boolean result=isElementPresent("loginagainLink_xpath");
    	String actualResult="";
    	if(result)
    		actualResult="success";
    	else
    		actualResult="failure";
    	
    	if(!expectedResult.equals(actualResult))
    		reportFailure("Logout is not success");    	
    }
   
//*******************  when login failed with invalid credentials ***********************
public void problemInLogin(String expectedResult) {
	
	boolean result=isElementPresent("errorlogin_xpath");
	String actualResult="";
	if(result)
		actualResult="fail";
	else
		actualResult="success";
	
	if(!expectedResult.equals(actualResult))
		reportFailure("Logged in successfully with invlaid credentials");    	
}


//********************** check selected drop down value*****************************

public void validateDropdownSelectedValue(String portfolioName,String locatorKey) {
	pauseTest(2);
	WebElement dropdown= getObject(locatorKey);
	Select sel=new Select(dropdown);
	String actualValue=sel.getFirstSelectedOption().getText();
	String expectedValue=prop.getProperty(portfolioName);
	if(!expectedValue.equals(actualValue))
		reportFailure("Could not create Portfolio");
	else
		reportPass("Portfolio created successfully");
		
}

public void checkValueInDropdown(String locatorKey, String portfolioName) {
	pauseTest(2);
	boolean result=false;
	WebElement dropdown= getObject(locatorKey);
	Select sel=new Select(dropdown);
	List<WebElement> web= sel.getOptions();
	for (int i=0; i<web.size();i++) {
		if(web.get(i).getText().contains(prop.getProperty(portfolioName))) {
			result=true;
			break;
		}

	}
	if(!result)
		reportPass("Portfolio has been deleted successfully");
		else
			reportFailure("Portfolio has not been deleted successfully");
	
}

public void isValuselectDropdownValue(String locatorKey,String data) {
	WebElement dropdown= getObject(locatorKey);
	Select sel=new Select(dropdown);
	sel.selectByVisibleText(prop.getProperty(data));
	reportInfo(data+" has been selected from dropdown : "+locatorKey);
	
	
}



    
/*################################################################################################
    
		*************   Initialise reports  and Flush reports *********************

##################################################################################################*/

//*********************** Initialise reports *******************************
    public void initReport(String scenarioName) {
    	rep=ExtentManager.getReports();
    	test=rep.createTest(scenarioName);
    	test.log(Status.INFO, " Starting "+scenarioName);
    	
    }
    
 //*************************  Flush Reports  ********************************
    public void quitRep() {
    	if(rep!=null)
    		rep.flush();
    }
    
/*################################################################################################
    
	*************   Logging in reports *********************

##################################################################################################*/   
 
 // ************************ Log info report *********************************
    
   public void reportInfo(String msg) {
	   test.log(Status.INFO, msg);
   }
   
// ************************   Log pass test in report    *********************************
   
   public void reportPass(String msg) {
	   test.log(Status.PASS, msg);
   }

// ************************   Fail test in report and Testng    *********************************
   
   public void reportFailure(String msg) {
	   test.log(Status.FAIL, msg);
	   takeScreenShot();
	   Assert.fail(msg);
   }
   
   
// ************************   Taking Screenshot    *********************************
   public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
			FileUtils.copyFile(scrFile, new File((ExtentManager.screenshotFolderPath)+"//"+screenshotFile));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		//put screenshot file in reports
		try {
			
         //test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath((ExtentManager.screenshotFolderPath)+"//"+screenshotFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
}
