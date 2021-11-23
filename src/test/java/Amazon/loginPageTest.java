package Amazon;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginPageTest {

	WebDriver driver;
	By SigninLinkID = By.id("nav-link-accountList-nav-line-1");
	By username = By.id("ap_email");
	By SignInText = By.xpath("//h1[@class='a-spacing-small']");
	By continueBtn = By.xpath("//input[@id='continue']");
	By password = By.id("ap_password");
	By signInBtn = By.id("signInSubmit");

	@BeforeSuite

	public void beforeSuite() {
		System.out.println("Before Suite....Test data preparation");
	}

	@BeforeTest(enabled = false)
	public void beforeTest() {
		System.out.println("BeforeTest.....DB connect");
	}

	@BeforeClass

	public void beforeClass() {
		System.out.println("Before Class....data from DB");
	}

	@BeforeTest
	@Parameters({ "url", "browser" })
	public void setUp(String url, String browserName) {

		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("microsoft edge")) {

			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
			
		}
		else if (browserName.equals("ie")) {

			WebDriverManager.iedriver().setup();
			
			driver = new InternetExplorerDriver();
		} 
		else
			System.out.println("Please pass the correct browser");

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void SignInTest() {
		driver.findElement(SigninLinkID).click();
		Assert.assertTrue(driver.findElement(SignInText).isDisplayed());
		System.out.println("Test Method...completed");
	}

	@Test(priority = 2)
	public void verifyTitleTest() {
		Assert.assertEquals(driver.getTitle(), "Amazon Sign In");
	}

	@Test(priority = 3)
	@Parameters({ "username", "password" })
	public void loginTest(String uname, String pwd) {
		//driver.findElement(SigninLinkID).click();
		driver.findElement(username).sendKeys(uname);
		driver.findElement(continueBtn).click();
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signInBtn).click();

		Assert.assertEquals(driver.findElement(SigninLinkID).getText(), "Hello, Shiv");

	}

	@AfterTest(enabled = false)
	public void afterMethod() {

		System.out.println("After Method .... method closed");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class....DB data work done");
	}

	@AfterTest(enabled = false)
	public void afterTest() {
		System.out.println("After Test....DB closed");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite....closed the data");
	}

}
