package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.utils.Constants;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiReturCheckoutPage extends BasePage {

    public AsuransiReturCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapOnCheckbox(boolean isSellerTwo) {
        if (!isSellerTwo) {
            swipeUpToElement("ASURANSI_RETUR_SECTION_CHECKBOX");
            tapElement("ASURANSI_RETUR_SECTION_CHECKBOX");
        } else {
            swipeUpToElement("ASURANSI_RETUR_SECTION_TWO_CHECKBOX");
            tapElement("ASURANSI_RETUR_SECTION_TWO_CHECKBOX");
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateAsuransiReturSection() {
        swipeUpToElement("ASURANSI_RETUR_SECTION_TNC_DESCRIPTION");
        for (int i = 0; i < Constants.FIVE_SECS_TIMEOUT; i++) {
            if (!isElementVisible("ASURANSI_RETUR_SECTION_CHECKBOX")) {
                swipeUp(0.8, 0.3);
            } else {
                break;
            }
        }
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_TITLE_TEXT");
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_CHECKBOX");
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_DESCRIPTION_TEXT");
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_PRICE_TEXT");
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_PELAJARI_BUTTON");

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateCheckboxAsuransiRetur(boolean isSellerTwo, boolean isChecked) {
        if (!isSellerTwo) {
            swipeUpToElement("ASURANSI_RETUR_SECTION_CHECKBOX");
            String checkedAttribute = getElementAttributeValue("ASURANSI_RETUR_SECTION_CHECKBOX", "value");
            validateValue().equals(isChecked, Boolean.parseBoolean(checkedAttribute));
        } else {
            swipeUpToElement("ASURANSI_RETUR_SECTION_TWO_CHECKBOX");
            String checkedAttribute = getElementAttributeValue("ASURANSI_RETUR_SECTION_TWO_CHECKBOX", "value");
            validateValue().equals(isChecked, Boolean.parseBoolean(checkedAttribute));
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateSectionTNC(boolean isDisplayed) {
        if (isDisplayed) {
            verifyElementDisplayed("ASURANSI_RETUR_SECTION_TNC_DESCRIPTION");
        } else {
            validateNotDisplayed("ASURANSI_RETUR_SECTION_TNC_DESCRIPTION", "Asuransi Retur TNC section has not displayed");
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void setReturAsuransiFee(boolean isSellerTwo, boolean isChangeQty) {
        swipeUpToElement("ASURANSI_RETUR_SECTION_PRICE_TEXT");
        verifyElementDisplayed("ASURANSI_RETUR_SECTION_PRICE_TEXT");

        if (isChangeQty) {
            InsuranceData.setTempFee(getIntegerFromTextElement("ASURANSI_RETUR_SECTION_PRICE_TEXT"));
        } else {
            InsuranceData.setReturInsuranceFee(getIntegerFromTextElement("ASURANSI_RETUR_SECTION_PRICE_TEXT"));
        }

        if (isSellerTwo) {
            swipeUpToElement("ASURANSI_RETUR_SECTION_TWO_PRICE_TEXT");
            for (int i = 0; i < Constants.FIVE_SECS_TIMEOUT; i++) {
                if (!isElementVisible("ASURANSI_RETUR_SECTION_TWO_PRICE_TEXT")) {
                    swipeUp(0.8, 0.3);
                } else {
                    break;
                }
            }
            verifyElementDisplayed("ASURANSI_RETUR_SECTION_TWO_PRICE_TEXT");
            InsuranceData.setReturInsuranceFee(InsuranceData.getReturInsuranceFee() + getIntegerFromTextElement("ASURANSI_RETUR_SECTION_TWO_PRICE_TEXT"));
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateTotalFeeOnAsuransiReturSection() {
        validateValue().notEquals(InsuranceData.getReturInsuranceFee(), InsuranceData.getLogisticInsurancePrice());
        InsuranceData.setReturInsuranceFee(InsuranceData.getReturInsuranceFee() + InsuranceData.getTempFee());

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateCheckoutAsuransiReturFee(boolean isDisplayed) {
        nativeSwipeUp();

        if (isDisplayed) {
            swipeUpToElement(constructLocator("ASURANSI_RETUR_CHECKOUT_PRICE_TEXT", getRpFromPrice(InsuranceData.getReturInsuranceFee())));
            verifyElementDisplayed(constructLocator("ASURANSI_RETUR_CHECKOUT_PRICE_TEXT", getRpFromPrice(InsuranceData.getReturInsuranceFee())));
        } else {
            swipeUpToElement("checkout_marketplace_alchemy_rincian_harga_text");
            validateNotDisplayed(constructLocator("ASURANSI_RETUR_CHECKOUT_PRICE_TEXT", getRpFromPrice(InsuranceData.getReturInsuranceFee())));
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void validateInvoiceDetailAsuransiReturFee(boolean isDisplayed) {
        if (isDisplayed) {
            verifyElementDisplayed(constructLocator("ASURANSI_RETUR_INVOICE_DETAIL_PRICE_TEXT", getRpFromPrice(InsuranceData.getReturInsuranceFee())));
        } else {
            validateNotDisplayed(constructLocator("ASURANSI_RETUR_INVOICE_DETAIL_PRICE_TEXT", getRpFromPrice(InsuranceData.getReturInsuranceFee())), "Asuransi Retur fee on invoice detail has not displayed");
        }

        HelperData.setLastActionPage(new AsuransiReturCheckoutPage(iosDriver));
    }

    public void resetAsuransiReturFee() {
        InsuranceData.setReturInsuranceFee(0);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
