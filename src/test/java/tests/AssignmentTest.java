package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ConfirmationPopup;
import pages.PaymentPage;
import pages.UnDosTresLandingPage;
import testbase.BaseTest;

//Test Class to write all the TESTNG tests
public class AssignmentTest extends BaseTest {


    //Test Data
    private static final String MOBILE_NO = "5523261151";
    private static final String OPERATOR = "Telcel";
    private static final String AMOUNT = "$10 (Recarga Saldo)";
    private static final String CARD_NAME = "Test";
    private static final String CARD_NUM = "4111111111111111";
    private static final String MONTH = "11";
    private static final String YEAR = "2025";
    private static final String CVV = "111";
    private static final String EMAIL = "test@test.com";
    private static final String USERNAME = "marze.zr@gmail.com";
    private static final String PASSWORD = "123456";
    private static final String CONFIRMATION_FAIL_MSG = "Correo/Movil o contraseña no válido!";

    UnDosTresLandingPage landingPage = new UnDosTresLandingPage();
    PaymentPage paymentPage = new PaymentPage();
    ConfirmationPopup popup = new ConfirmationPopup();
    private static final Logger logger = LogManager.getLogger(AssignmentTest.class);

    @Test(description = "This test is for Undostres interview", testName = "Assignment Test")
    public void launchUndoStresTest() {
        driver.get("http://prueba.undostres.com.mx");
        Assert.assertTrue(driver.getTitle().trim().contains("Recargas en línea Telcel"));
        Assert.assertTrue(landingPage.verifyLandingPage());
        logger.trace("Landing Page opened successfully");
        landingPage.selectOperator(OPERATOR);
        landingPage.enterNumber(MOBILE_NO);
        landingPage.selectAmount(AMOUNT);
        landingPage.rechargeMobile();
        paymentPage.enterCardDetails(CARD_NAME, CARD_NUM, MONTH, YEAR, CVV, EMAIL);
        popup.enterConfirmationDetailsAndSubmit(USERNAME, PASSWORD);
        Assert.assertNotEquals(popup.getConfirmationMsg(), CONFIRMATION_FAIL_MSG,
                "Confirmation failed as error message is displayed on the page "+ popup.getConfirmationMsg());
    }


}
