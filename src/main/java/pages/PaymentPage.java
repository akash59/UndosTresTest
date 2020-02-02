package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import pageactions.UserActions;

//Page Object Class for Payment Page
public class PaymentPage {

    private static final Logger logger = LogManager.getLogger(PaymentPage.class);
    private static final By cardName = By.xpath(".//*[@class='card-info-box']//input[@name='cardname']");
    private static final By cardNumber = By.xpath(".//*[@class='card-info-box']//input[@name='cardno']");
    private static final By month = By.xpath(".//*[@class = 'card-info-box']//input[@name='expmonth']");
    private static final By year = By.xpath(".//*[@class = 'card-info-box']//input[@name='expyear']");
    private static final By cvv = By.xpath(".//*[@class = 'card-info-box']//input[@name='cvvno']");
    private static final By loadingSpinner = By.xpath(".//div[contains(@class, 'centrarAnything')]//div[@class='spinLoader']");
    private static final By chooseTarjeta = By.xpath("//p[text()='Tarjeta']/preceding-sibling::h4");
    private static final By email = By.xpath("//label[text()='Correo electrónico']/preceding-sibling::input");
    private static final By form_submit = By.xpath(".//*[@id='paylimit']");

    public void enterCardDetails(String cardNameVal, String cardNumVal, String monthVal, String yearVal, String cvvVal, String emailVal) {

        logger.info("Entering the Card details...");
        UserActions.click(chooseTarjeta);
        UserActions.enter(cardName, cardNameVal);
        UserActions.enter(cardNumber, cardNumVal);
        UserActions.enter(month, monthVal);
        UserActions.enter(year, yearVal);
        UserActions.enter(cvv, cvvVal);
        UserActions.enter(email, emailVal);
        UserActions.click(form_submit);
        UserActions.waitForInvisibilityOfElementLocatedBy(loadingSpinner, 20);
    }



}
