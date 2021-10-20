package bukalapak.pageObject;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FinancingHomePage extends BasePage {
    public FinancingHomePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void selectProduct(String product) {
        waitFor(10);
        tapElement(constructLocator("financinghomepage_product_text", product));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void isInFinancingHomePage() {
        waitFor(5);
        verifyElementExist("financinghomepage_pembiayaantunai_text");
        verifyElementExist("financinghomepage_bukacicilan_text");
        verifyElementExist("financinghomepage_bukamodal_text");
    }

    public void verifyAccountPopUpShown() {
        waitForVisibilityOf("financinghomepage_verify_pop_up_text", 10);
        verifyElementExist("financinghomepage_verify_pop_up_text");
    }
}
