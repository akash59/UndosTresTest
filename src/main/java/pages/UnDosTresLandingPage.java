package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import pageactions.UserActions;

//Page Object Class for Landing Page

public class UnDosTresLandingPage {

    private static final Logger logger = LogManager.getLogger(UnDosTresLandingPage.class);
    private static final By landingPageText = By.xpath(".//h1[contains(text(), 'Recarga Celular')]");
    private static final By operator = By.xpath(".//div[@to-do='mobile']//input[@name='operator']");
    private static final By number = By.xpath(".//div[@to-do='mobile']//input[@name='mobile']");
    private static final By activeCategory = By.xpath(".//div[contains(@class, 'category active')]");
    private static final By amount = By.xpath(".//div[@to-do='mobile']//input[@suggest='mobile-operator_amount']");
    private static final By loadingSpinner = By.xpath(".//div[contains(@class, 'centrarAnything')]//div[@class='spinLoader']");
    private static final By recharge = By.xpath(".//div[@to-do='mobile']//button[text()='Siguiente']");


    public boolean verifyLandingPage() {

        boolean isDisplayed = false;

        try {
            logger.info("Verifying if the user is on the landing page");
            UserActions.waitForVisibilityOfElement(landingPageText, 30);
            isDisplayed = UserActions.isDisplayed(landingPageText, 10);
        }

        catch (Exception e) {
            logger.error("VerifyLandingPage function failed ..." +e.getMessage());
            throw new ElementNotVisibleException("Element is not visible" +e.getStackTrace());
        }

        return isDisplayed;
    }


    public void selectOperator(String operatorName) {

        try {
            logger.info("Select operator as " +operatorName);
            UserActions.click(operator);
            By oprToBeSelected = By.xpath("//div[@class='suggestion']//li[@data-show='"+operatorName+"']");
            UserActions.waitForElementClickability(oprToBeSelected, 10);
            UserActions.click(oprToBeSelected);
        }

        catch (Exception e) {
            logger.error("Error selecting operator " +e.getMessage());
            throw new ElementNotSelectableException("Error selecting operator " +e.getStackTrace());
        }
    }

    public void enterNumber(String mobileNumber) {

        try {
            logger.info("Entering number in the field as "+number);
            UserActions.enter(number, mobileNumber);
        }

        catch (Exception e) {
            logger.error("Error entering number " +e.getMessage());
            throw new ElementNotInteractableException("Error selecting operator " +e.getStackTrace());
        }
    }

    public void selectAmount(String rechargeAmount) {

        try {
            logger.info("Selecting recharge amount");
            UserActions.click(amount);
            UserActions.isDisplayed(activeCategory, 10);
            By amount_Suggestion = By.xpath(".//ul/li[@data-show='"+rechargeAmount+"']");
            UserActions.scrollElementIntoView(amount_Suggestion);
            UserActions.click(amount_Suggestion);
        }

        catch (Exception e) {
            logger.error("Error selecting amount " +e.getMessage());
            throw new ElementNotVisibleException("Error selecting amount " +e.getStackTrace());
        }
    }

    public void rechargeMobile() {
        try {
            logger.info("Proceed to recharge mobile..");
            UserActions.click(recharge);
            UserActions.waitForInvisibilityOfElementLocatedBy(loadingSpinner,30);
        }

        catch(Exception e) {
            logger.error("Error recharging mobile " +e.getMessage());
            throw new ElementNotInteractableException("Error recharging mobile " +e.getStackTrace());
        }
    }


}
