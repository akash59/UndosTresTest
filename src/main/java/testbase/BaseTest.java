package testbase;

import browsermanager.DriverManager;
import browsermanager.DriverManagerFactory;
import browsermanager.DriverType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

//Base Test Class for setting up test suite

public class BaseTest {

    DriverManager driverManager;
    public static WebDriver driver;


    @BeforeTest
    public void beforeTest() {
        //code to read browser type from properties file.
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }

}
