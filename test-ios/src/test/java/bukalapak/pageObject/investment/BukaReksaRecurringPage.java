package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaReksaRecurringPage extends BasePage {

    public BukaReksaRecurringPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnRexaRecurringTrxDetail() {
        if (isElementVisible("REXA_RECURRING_ONBOARDING")) {
            doRecurringHistoryOnboarding();
        }
        validateDisplayed("REXA_RECURRING_DETAIL");
        validateDisplayed("REXA_RECURRING_TOGGLE");
    }

    public void isOnRecurringForm() {
        verifyElementDisplayed("REXA_RECURRING_FORM_PRODUCT_NAME");
        verifyElementDisplayed("REXA_RECURRING_PAYMENT_METHOD");
        verifyElementDisplayed("REXA_RECURRING_ACTIVATE_TRX_BTN");
    }

    public void isOnProductListing() {
        verifyElementDisplayed("REXA_RECURRING_PRODUCT_PAGE");
        verifyQuickFilterDisplayed();
    }

    public void checkRexaRecurringFilter() {
        while (!isElementExist("REXA_RECURRING_FILTER", 3)) {
            nativeSwipeLeftToElement("RECURRING_FILTER_SCROLL_FRAGMENT");
        }
    }

    public void checkRexaRecurringCard() {
        doPullRefresh(2);
        verifyElementExist("REXA_RECURRING_CARD");
    }

    public void tapOnAktifkanKembaliBtn() {
        tapElement("REXA_RECURRING_ACTIVATE_BTN", Direction.UP);
    }

    public void tapOnBukaReksaRecurringCard() {
        tapElement("RECURRING_BUKAREKSA_CARD", 10);
    }

    public void tapOnRecurringToggle() {
        tapElement("REXA_RECURRING_TOGGLE");
    }

    public void tapOnRecurringBackBtn() {
        tapElement("RECURRING_BACK_BUTTON", 5);
    }

    public void tapOnProductDropdown() {
        verifyElementDisplayed("REXA_RECURRING_PRODUCT_DROPDOWN");
        tapElement("REXA_RECURRING_PRODUCT_DROPDOWN");
    }

    public void tapOnproductQuickFilter(String productFilter) {
        switch (productFilter.toLowerCase()) {
            case "portofolio":
                tapElement("REXA_QUICK_FILTERS_PORTOFOLIO");
                break;
            case "syariah":
                tapElement("REXA_QUICK_FILTERS_PRODUK_SYARIAH");
                break;
            case "konvensional":
                tapElement("REXA_QUICK_FILTERS_PRODUK_KONVENSIONAL");
                break;
            default:
                Assert.fail(productFilter.toUpperCase() + " isn't a filter");
        }
    }

    public void validateRecurringProductName() {
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getText("REXA_RECURRING_FIRST_PRODUCT_NAME"));
    }

    public void tapOnReksaProduct() {
        InvestmentData.setProductNameBukaReksa(getText("REXA_RECURRING_PRODUCT_CARD_NAME"));
        tapElement("REXA_RECURRING_PRODUCT_CARD_NAME");
    }

    public void tapOnRecurringCheckBox() {
        tapElement("REXA_RECURRING_CHECK_BOX");
    }

    public void tapOnAktifkanTransaksiRutinBtn() {
        tapElement("REXA_RECURRING_ACTIVATE_TRX_BTN");
    }

    public void inputRecurringAmount(String amount) {
        InvestmentData.setRecurringAmount(amount);
        tapElement("REXA_RECURRING_INPUT_AMOUNT_FIELD");
        typeAndEnterValue("REXA_RECURRING_INPUT_AMOUNT_FIELD", amount);
    }

    public void chooseRecurringPaymentMethod() {
        tapRecurringPaymentMethod();
        isOnRecurringPaymentMethod();
        taponDANA();
        tapGunakanMetodeIniBtn();
    }

    public void validateRecurringState(String state) {
        // need delay for waiting page fully load
        delay(5000);
        switch (state.toLowerCase()) {
            case "active":
                validateExist("REXA_RECURRING_TRX_STATE_ACTIVE",3);
                break;
            case "deactive":
                validateEnabled("REXA_RECURRING_TRX_STATE_INACTIVE");
                break;
            default:
                Assert.fail(state.toLowerCase() + " isn't a recurring state!");
        }
    }

    public void verifyQuickFilterDisplayed() {
        verifyElementExist("REXA_QUICK_FILTERS_PORTOFOLIO");
        verifyElementExist("REXA_QUICK_FILTERS_SYARIAH");
        verifyElementExist("REXA_QUICK_FILTERS_PRODUK_KONVENSIONAL");
    }

    public void verifyRecurringTrxDetailDisplayed() {
        swipeUpToElement("REXA_RECURRING_INFO");
        verifyElementDisplayed("REXA_RECURRING_TRANSACTION_DETAIL");
        validateRecurringAmount();
        verifyElementDisplayed("REXA_RECURRING_PURCHASE_FEE");
    }

    public void showRecurringSnackbar(String snackbar) {
        switch (snackbar.toLowerCase()) {
            case "failed":
                validateDisplayed("REXA_RECURRING_ERROR_SNACKBAR");
                break;
            case "success":
                validateDisplayed("REXA_RECURRING_SUCCESS_SNACKBAR");
                break;
            default:
                Assert.fail(snackbar.toLowerCase() + " isn't a message");
        }
    }

    private void doRecurringHistoryOnboarding() {
        validateDisplayed("REXA_RECURRING_ONBOARDING");
        tapElement("REXA_RECURRING_ONBOARDING_LANJUT_BTN");
    }

    private void isOnRecurringPaymentMethod() {
        verifyElementDisplayed("REXA_RECURRING_PAYMENT_INFO");
        verifyElementDisplayed("REXA_RECURRING_DANA");
    }

    private void tapRecurringPaymentMethod() {
        tapElement("REXA_RECURRING_PAYMENT_METHOD");
    }

    private void taponDANA() {
        tapElement("REXA_RECURRING_DANA");
        verifyElementDisplayed("REXA_RECURRING_DANA_DETAIL_BALANCE");
    }

    private void tapGunakanMetodeIniBtn() {
        tapElement("REXA_RECURRING_USE_PAYMENT_METHOD_BTN");
    }

    private void validateRecurringAmount() {
        validateValue().equals(getRecurringInputAmount(),
                getRincianTransactionRecurringAmount(), "Amount doesn't match");
    }

    private String getRincianTransactionRecurringAmount() {
        return getText("REXA_RECURRING_AMOUNT");
    }

    private String getRecurringInputAmount() {
        int recurringAmount = Integer.parseInt(InvestmentData.getRecurringAmount());
        return getRpFromPrice(recurringAmount);
    }

    public void verifyRecurringCheckoutTransaction() {
        if (!isElementExist("REXA_RECURRING_CREATE_RECURRING_BTN", 5)) {
            swipeUpToElement("REXA_RECURRING_CREATE_RECURRING_BTN");
        }
        verifyElementExist("REXA_RECURRING_CHECKOUT_RECURRING_SECTION_TITLE");
        verifyElementExist("REXA_RECURRING_CHECKOUT_RECURRING_SECTION_DESC");
        verifyElementExist("REXA_RECURRING_CHECKOUT_TRANSACTION_CARD");
        verifyElementExist("REXA_RECURRING_CREATE_RECURRING_BTN");
    }

    public void tapOnJadikanTrxRutinBtn() {
        tapElement("REXA_RECURRING_CREATE_RECURRING_BTN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
