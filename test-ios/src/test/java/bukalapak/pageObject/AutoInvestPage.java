package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;

public class AutoInvestPage extends BasePage {

    public AutoInvestPage(IOSDriver<IOSElement> iosDriver) {
        super((iosDriver));
    }

    public void isAutoinvestSectionDisplayed() {
        if (!isElementExist("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN", 5)) {
            swipeUpToElement("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN");
        }
        validateDisplayed("AUTOINVEST_ACCOUNT_SECTION", "Autoinvest doesn't exist!");
        validateExist("AUTOINVEST_ACCOUNT_TOGGLE", 3);
        validateExist("AUTOINVEST_ACCOUNT_DESC", 3);
        validateExist("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN", 3);
        HelperData.setLastActionPage(new AutoInvestPage(iosDriver));
    }

    public void tapOnAutoinvestToggle() {
        tapElement("AUTOINVEST_ACCOUNT_TOGGLE");
    }

    public boolean getAutoinvestToggleState() {
        return Boolean.parseBoolean(getElementValue("AUTOINVEST_ACCOUNT_TOGGLE"));
    }

    public void changeAutoinvestToggleState(String method) {
        switch (method.toLowerCase()) {
            case "activate":
                activateAutoinvest();
                break;
            case "deactivate":
                deactivateAutoinvest();
                break;
            default:
                Assert.fail(method + "isn't a state!");
        }
    }

    public void setAutoinvestToggleActive() {
        if (getAutoinvestToggleState()) {
            activateAutoinvest();
        } else {
            deactivateAutoinvest();
            activateAutoinvest();
        }
        InvestmentData.setAutoInvestActive(true);
    }

    public void activateAutoinvest() {
        tapOnAutoinvestToggle();
        String button = getAutoinvestToggleState() ? "AUTOINVEST_YA_MAU_BTN" : "AUTOINVEST_TETAP_AKTIFKAN_BTN";
        tapElement(button);
    }

    public void deactivateAutoinvest() {
        tapOnAutoinvestToggle();
        String button = getAutoinvestToggleState() ? "AUTOINVEST_BATAL_BTN" : "AUTOINVEST_YA_BERHENTI_BTN";
        tapElement(button);
    }

    public void checkAutoinvestActive() {
        if (isElementExist("AUTOINVEST_SUCCESS_ACTIVATE_SNACKBAR", 5)) {
            LogUtil.info("Autoinvest active!");
        } else {
            LogUtil.info("Autoinvest already active!");
        }
        validateValue().equals(true, getAutoinvestToggleState());
        HelperData.setLastActionPage(new AutoInvestPage(iosDriver));
    }

    public void checkAutoinvestDeactive() {
        if (isElementExist("AUTOINVEST_SUCESS_DEACTIVE_SNACKBAR", 5)) {
            LogUtil.info("Autoinvest deactive!");
        } else {
            LogUtil.info("Autoinvest already deactive!");
        }
        validateValue().equals(false, getAutoinvestToggleState());
        HelperData.setLastActionPage(new AutoInvestPage(iosDriver));
    }

    public void checkAutoinvestStatus(String state) {
        switch (state.toLowerCase()) {
            case "active":
                checkAutoinvestActive();
                break;
            case "deactive":
                checkAutoinvestDeactive();
                break;
            default:
                Assert.fail(state + "isn't a state!");
        }
    }

    public void tapAturJumlahnyaBtn() {
        if(!isElementVisible("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN")) {
            swipeUpToElement("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN");
        }
        tapElement("AUTOINVEST_ACCOUNT_ATUR_JUMLAH_BTN");
    }

    public void isAutoinvestSettingsPageDisplayed() {
        if (isElementExist("AUTOINVEST_ONBOARDING_TITLE", 5)) {
            tapElement("AUTOINVEST_ATUR_BELI_OTOMATIS_BTN");
        }
        verifyElementExist("AUTOINVEST_SETTINGS_PAGE_TITLE");
        verifyElementExist("AUTOINVEST_DESCRIPTION");
        verifyElementExist("AUTOINVEST_SIMPAN_BTN");
    }

    public String getAutoinvestRandomAmount() {
        return String.valueOf(RandomUtils.nextInt(100, 500));
    }

    public void clearAutoinvestBuyerlField() {
        getElementPresent("AUTOINVEST_BUYER_INPUT_FIELD").clear();
    }

    private String getAutoInvestBuyerText() {
        return getTextFromElement("AUTOINVEST_BUYER_INPUT_FIELD");
    }

    public void clearAutoinvestSellerlField() {
        getElementPresent("AUTOINVEST_SELLER_INPUT_FIELD").clear();
    }

    private String getAutoInvestSellerText() {
        return getTextFromElement("AUTOINVEST_SELLER_INPUT_FIELD");
    }

    private boolean getAutoinvestCheckboxBuyerState() {
        return Boolean.parseBoolean(getElementValue("AUTOINVEST_BUYER_CHECKBOX"));
    }

    private boolean getAutoinvestCheckboxSellerState() {
        return Boolean.parseBoolean(getElementValue("AUTOINVEST_SELLER_CHECKBOX"));
    }

    public void tapAutoinvestCheckbox(String method, String type) {
        String checkbox = (type.equals("buyer")) ? "AUTOINVEST_BUYER_CHECKBOX" : "AUTOINVEST_SELLER_CHECKBOX";
        String valueCheckbox = (method.equals("activate")) ? "true" : "false";
        if (!getElementAttributeValue(checkbox, "value").equals(valueCheckbox))
            tapElement(checkbox);
        HelperData.setLastActionPage(new AutoInvestPage(iosDriver));
    }

    public void inputAutoinvestBuyerAmount(String amount) {
        tapElement("AUTOINVEST_BUYER_INPUT_FIELD");
        clearAutoinvestBuyerlField();
        typeAndEnterValue("AUTOINVEST_BUYER_INPUT_FIELD", amount);
    }

    public void inputAutoinvestSellerAmount(String amount) {
        tapElement("AUTOINVEST_SELLER_INPUT_FIELD");
        clearAutoinvestSellerlField();
        typeAndEnterValue("AUTOINVEST_SELLER_INPUT_FIELD", amount);
    }

    public void inputAutoinvestRandomAmount(String type) {
        switch (type.toLowerCase()) {
            case "buyer":
                InvestmentData.setAutoInvestBuyerAmount(getAutoinvestRandomAmount());
                inputAutoinvestBuyerAmount(InvestmentData.getAutoInvestBuyerAmount());
                break;
            case "seller":
                InvestmentData.setAutoInvestSellerAmount(getAutoinvestRandomAmount());
                inputAutoinvestSellerAmount(InvestmentData.getAutoInvestSellerAmount());
                break;
            default:
                Assert.fail(type.toLowerCase() + " isn't a user type");
        }
    }

    public void tapOnSimpanBtn() {
        tapElement("AUTOINVEST_SIMPAN_BTN");
    }

    public void verifySuccessSnackbarDisplayed() {
        verifyElementExist("AUTOINVEST_SUCCESS_CHANGE_SNACKBAR");
    }

    public void verifyFailedSnackbarDisplayed() {
        verifyElementExist("AUTOINVEST_FAILED_CHANGE_SNACKBAR");
    }

    public void verifyInlineErrorMessageDisplayed() {
        verifyElementExist("AUTOINVEST_MINIMUM_AMOUNT_ERROR_MSG");
    }

    public void validateAutoinvestBuyerAmount() {
        validateValue().equals(InvestmentData.getAutoInvestBuyerAmount(), getAutoInvestBuyerText(), "Amount doesn't match!");
    }

    public void validateAutoinvestSellerAmount() {
        validateValue().equals(InvestmentData.getAutoInvestSellerAmount(), getAutoInvestSellerText(), "Amount doesn't match!");
    }

    public void checkAutoinvestBuyerStateinSettingsPage(String state) {
        doPullRefresh(1);
        boolean checkboxState = Boolean.parseBoolean((state.equals("active")) ? "true" : "false");
        validateValue().equals(checkboxState, getAutoinvestCheckboxBuyerState());
    }

    public void checkAutoinvestSellerStateinSettingsPage(String state) {
        doPullRefresh(1);
        boolean checkboxState = Boolean.parseBoolean((state.equals("active")) ? "true" : "false");
        validateValue().equals(checkboxState, getAutoinvestCheckboxSellerState());
    }

    public void checkAutoinvestStateinSettingsPage(String type, String state) {
        switch (type.toLowerCase()) {
            case "buyer":
                checkAutoinvestBuyerStateinSettingsPage(state);
                break;
            case "seller":
                checkAutoinvestSellerStateinSettingsPage(state);
                break;
            default:
                Assert.fail(type + "isn't a type");
        }
    }

    public void deactiveAutoinvest() {
        validateDisplayed("AUTOINVEST_DEACTIVATE_MODAL");
        tapElement("AUTOINVEST_YA_BERHENTI_BTN");
    }

    public void setAutoInvestBuyerAmount() {
        InvestmentData.setAutoInvestBuyerAmount(getAutoInvestBuyerText());
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
