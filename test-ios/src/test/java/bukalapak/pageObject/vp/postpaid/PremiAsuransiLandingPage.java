package bukalapak.pageObject.vp.postpaid;

import bukalapak.pageObject.BasePage;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.PostpaidBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by Ferawati H.P on 07/10/2020.
 */
public class PremiAsuransiLandingPage extends PostpaidBasePage {

    public PremiAsuransiLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        skipOnboarding("PREMI_ASURANSI_COACH_MARK_NEXT", "PREMI_ASURANSI_COACH_MARK_DONE");
        validateDisplayed("PREMI_ASURANSI_HEADER");
        HelperData.setLastActionPage(new PremiAsuransiLandingPage(iosDriver));
    }

    public void tapOnHistoryTransactionIcon() {
        tapElement("PREMI_ASURANSI_HISTORY_TRANSACTION_ICON");
    }

    public void inputPolisNumber(String type) {
        String number = type.equals("valid") ? dotenv.get("POLIS_NUMBER_VALID") : dotenv.get("POLIS_NUMBER_INVALID");

        typeAndEnterValueWithTimeOut("PREMI_ASURANSI_POLIS_NUMBER_FIELD", number);
        PostpaidData.setCustomerNumber(number);
    }

    public void validatePolisFormatIsInvalid() {
        waitForVisibilityOf("PREMI_ASURANSI_INVALID_POLIS_FORMAT", 5);
        HelperData.setLastActionPage(new PremiAsuransiLandingPage(iosDriver));
    }

    public void choosePolisProvider() {
        tapElement("PREMI_ASURANSI_CHOOSE_POLIS_PROVIDER");
        tapElement("PREMI_ASURANSI_ALLIANZ_PROVIDER");
    }

    public void tapOnBayarButton() {
        swipeUpToElement("PREMI_ASURANSI_BAYAR_BUTTON");
        tapElement("PREMI_ASURANSI_BAYAR_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
