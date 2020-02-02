package pageactions;

import testbase.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class UserActions extends BaseTest
{

    private static final String ARGUMENTS_SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true);";
    private static final Logger logger = LogManager.getLogger(UserActions.class);
    private static final String SELECTING_OPTION_ON_ELEMENT = "Selecting option {} on the element";

    //Enter text into an element
    public static void enter(By locator, String input)
    {
        waitForVisibilityOfElement(locator, 30);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(input);
    }

    //Click on the Web Element
    public static void click(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, 30, 1500);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //Wait for element to be invisible
    public static Boolean waitForInvisibilityOfElementLocatedBy(By locator, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return true;
    }

    //Get element text
    public static String getText(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return (wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText());
    }

    //Switch to iFrame using ID
    public static boolean switchToFrameByID(int frameLocator)
    {
        boolean found = false;
        try
        {
            Thread.sleep(2000);
            driver.switchTo().frame(frameLocator);
            found = true;
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }

        return found;
    }

    //Get Web Element on the page
    public static WebElement getWebElement(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //Check if the element is displayed on the page
    public static Boolean isDisplayed(By locator, int timeout)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
        return true;
    }

    //Simulate javascript click
    public static void jSClick(WebElement element)
    {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    //Simulate Mouse Hover using (x,y) coordinates and perform click
    public static void mouseHoverToAPositionAndClick(WebElement element, int x, int y) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(5000);
        action.moveToElement(element, x, y).click().build().perform();
    }

    //Simulate Mouse Hover and perform click
    public static void mouseHoverAndClick(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(2000);
        action.moveToElement(element).click().build().perform();
    }

    //Switch back to default content
    public static void switchToMainWindow() throws InterruptedException {
        Thread.sleep(2000);
        driver.switchTo().defaultContent();
    }

    //scroll element into view
    public static void scrollElementIntoView(By locator) throws InterruptedException
    {
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript(ARGUMENTS_SCROLL_INTO_VIEW, driver.findElement(locator));
        Thread.sleep(500);
    }

    //Wait for element to be clickable
    public static void waitForElementClickability(By element, int timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


   //Wait for element to be visible
    public static WebElement waitForVisibilityOfElement(By locator, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, 1000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}

