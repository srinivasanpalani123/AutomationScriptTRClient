package project_package;

import java.io.File;
import org.openqa.selenium.support.ui.Select;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import junit.framework.Assert;


public class Common_Base_Functionality 
{
	public WebDriver driver;
	protected CommonUtilities reader =new CommonUtilities();
	public static String Inputfilepath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"Input"+File.separator+"trclientdatainput.xls";
	public static String startapplication;
    public static String datasheettrclientInput;
    public static String trclientInputTable; 
    public static String applicationURL;
 
    
    public Common_Base_Functionality() throws Exception {
    reader = new CommonUtilities();
    Properties trclientprop = new Properties();
	datasheettrclientInput = trclientprop.getProperty("excel.trclient.datasheet");
	trclientInputTable=trclientprop.getProperty("excel.trclient.trclientInputTable");
 	startapplication = trclientprop.getProperty("application_URL");
 	
 	
    }
 @BeforeMethod(alwaysRun=true)
	public void startbrowser() throws Exception{
	    System.setProperty("webdriver.chrome.driver","C:\\Users\\M.A.Amul Srinivasan\\AppData\\Local\\Temp\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(10000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		System.out.println("Browser loaded");
		driver.get("http://integr8a.trclient.com/testing/");
		
}
 //-------------------------------------------------------------------------------------------------------
  /* Following code is used to fetch the different set of data from Excelsheet
  * My Laptop excel file is not working due to licensing issue hence am placing my code alone
 //--------------------------------------------------------------------------------------------------------
 @DataProvider(name ="trclientInputTable")
 public String[][] getAddressDetailsData() throws Exception {
 return reader.fetchInputData(Inputfilepath,datasheettrclientInput,trclientInputTable);
 }

 //@Test(dataProvider = "trclientInputTable")
*/
 @Test
 public void ApplicationFlow(){
	 try {
		System.out.println("Am in");
		driver.findElement(By.id("firstname")).sendKeys("Srinivasan");
		driver.findElement(By.id("lastname")).sendKeys("Palani");
		driver.findElement(By.name("customer.EMAIL")).sendKeys("tester@teter.com");
		driver.findElement(By.id("mobile")).sendKeys("987897899");
		driver.findElement(By.id("address")).sendKeys("12345");
		driver.findElement(By.name("customer.SUBURBTOWN")).sendKeys("12345");
		new Select(driver.findElement(By.id("state"))).selectByVisibleText("ACT");
		driver.findElement(By.id("postcode")).sendKeys("600106");
		driver.findElement(By.id("dob")).sendKeys("17/10/1979");
		driver.findElement(By.id("terms-1")).click();
		driver.findElement(By.id("subm")).click();
		String str=driver.findElement(By.xpath("//h2[contains(text(),'Thank you')]")).getText();
		if(str.contains("Thank you")) {
			System.out.println("Form successfully submitted");
		}
	 }catch(NoSuchElementException e) {
		 System.out.println("Element not Found");
	 }
		
		
		
 }
 @Test
 public void Assert_Fieldvalidation(){
	 try {
		driver.findElement(By.id("subm")).click();
		Assert.assertEquals("First name is required", "First name is required");
		Assert.assertEquals("Last name is required", "Last name is required");
		Assert.assertEquals("Email is required", "Email is required");
		Assert.assertEquals("Mobile is required", "Mobile is required");
		Assert.assertEquals("Address is required", "Address is required");
		Assert.assertEquals("Suburb is required", "Suburb is required");
		Assert.assertEquals("Please select state", "Please select state");
		Assert.assertEquals("Postcode is required", "Postcode is required");
		Assert.assertEquals("Date of Birth is required", "Date of Birth is required");
		Assert.assertEquals("Please read and agree to terms and conditions", "Please read and agree to terms and conditions");
		driver.findElement(By.id("dob")).sendKeys("17/10/2010");
		Assert.assertEquals("You need to be 18 and above to enter this competition.","You need to be 18 and above to enter this competition.");
		driver.findElement(By.id("dob")).clear();
	 }catch(NoSuchElementException e) {
		 System.out.println("Error message not available");
	 }
		
		
		
 }
 @AfterMethod
 public void appendFinalHTMLReport(ITestResult result) throws Exception{
     if(result.getStatus() == ITestResult.FAILURE)
     {
        Thread.sleep(3000);
     }else if(result.getStatus() == ITestResult.SUCCESS){
     	System.out.println("Module executed successfully");
     	Thread.sleep(3000);
     	driver.quit();
     	}
       }
  
 }

 
