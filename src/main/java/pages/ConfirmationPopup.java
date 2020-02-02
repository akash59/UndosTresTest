package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pageactions.UserActions;

//Page Object Class for Confirmation Box
public class ConfirmationPopup {

    private static final Logger logger = LogManager.getLogger(ConfirmationPopup.class);
    private static final By userName = By.id("usrname");
    private static final By password = By.id("psw");
    private static final By submit = By.xpath(".//button[@name='loginbtn']");
    private static final By captcha = By.xpath(".//div[@class='recaptcha-checkbox-borderAnimation']");
    private static final By popup = By.xpath("//div[@class='modal-body']");
    private static final int frameID = 0;
    private static final By loadingSpinner = By.xpath(".//div[contains(@class, 'centrarAnything')]//div[@class='spinLoader']");
    private static final By confirm_error = By.id("add_err");

    public boolean isPopUpPresent() {

        boolean flag = false;

        try {
            logger.info("Verifying if confirmation popup is opened on the page");
            UserActions.waitForVisibilityOfElement(popup, 20);
            flag = UserActions.isDisplayed(popup, 20);

        } catch (Exception e) {
            logger.error("Some occur occurred inside isPopUpPresent() method" +e.getMessage());
            throw new ElementNotVisibleException(e.getStackTrace().toString());
        }

        return flag;
    }

    public void clickCaptcha() {

        try {
            UserActions.switchToFrameByID(frameID);
            WebElement captchaEle = UserActions.getWebElement(captcha);
            Point point = captchaEle.getLocation();
            int xCord = point.getX();
            System.out.println("Position of the captcha from left side is "+xCord +" pixels");
            int yCord = point.getY();
            System.out.println("Position of the captcha from top side is "+yCord +" pixels");
            UserActions.mouseHoverToAPositionAndClick(captchaEle, xCord, yCord);
        }

        catch (Exception e) {
            logger.error("Failed filling details into confirmation popup");
            throw new ElementNotInteractableException("Problem interacting with Captcha " +e.getStackTrace());
        }
    }

    public void enterConfirmationDetailsAndSubmit(String userNameVal, String passwordVal) {

        try {
            if(isPopUpPresent()) {
                logger.info("Entering details in the popup present on the page");
                UserActions.enter(userName, userNameVal);
                UserActions.enter(password, passwordVal);
                clickCaptcha();
                UserActions.switchToMainWindow();
                UserActions.scrollElementIntoView(submit);
                UserActions.mouseHoverAndClick(UserActions.getWebElement(submit));
                UserActions.waitForInvisibilityOfElementLocatedBy(loadingSpinner, 20);
            } else {
                logger.error("No popup is present on the page");
            }
        }

        catch (Exception e) {
            logger.error("enterConfirmationDetails() method failed " +e.getMessage());
            throw new ElementNotInteractableException("enterConfirmationDetails() method failed" +e.getStackTrace());
        }
    }

    public String getConfirmationMsg() {
        return UserActions.getText(confirm_error).trim();
    }






}
