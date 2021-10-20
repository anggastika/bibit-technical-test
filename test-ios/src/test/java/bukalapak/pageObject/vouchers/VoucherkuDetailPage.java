package bukalapak.pageObject.vouchers;

import bukalapak.data.HelperData;
import bukalapak.data.SubsidiesData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class VoucherkuDetailPage extends BasePage {


    private static final String COMING_SOON_COPY = "Coming soon";
    private static final String START_VALID = "Mulai berlaku";

    public VoucherkuDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateVoucherkuRevampDetail() {
        verifyElementExist("VOUCHERKU_DETAIL_REVAMP_TITLE", 5, "Element is not exist.");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_INFO");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_END_DATE");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_START_END_DATE_COPY");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_MIN_TRX");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_MIN_TRX_COPY");
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_TERM_AND_USAGE_INFO");
        if (isElementExist("VOUCHERKU_DETAIL_REVAMP_USE_VOUCHER", 5)) {
            tapElement("VOUCHERKU_DETAIL_REVAMP_USE_VOUCHER");
        }
        HelperData.setLastActionPage(new VoucherkuDetailPage(iosDriver));
    }

    public void validateVoucherkuInformation() {
        tapOnVoucherInformationTerms();
        validateVoucherInformationTermsAndUsageInfoText();
        tapOnVoucherInformationUsageInfo();
        validateVoucherInformationTermsAndUsageInfoText();
        HelperData.setLastActionPage(new VoucherkuDetailPage(iosDriver));
    }

    public void validateCopiedVoucherCode() {
        String copiedVoucherCode = iosDriver.getClipboardText();
        validateValue().equals(SubsidiesData.getVoucherCode(), copiedVoucherCode);
    }

    public void validateVoucherComingSoon() {
        waitForVisibilityOf("VOUCHERKU_DETAIL_REVAMP_START_END_DATE_COPY", 20);
        validateElementWithText("VOUCHERKU_DETAIL_REVAMP_START_END_DATE_COPY", START_VALID);
        validateElementWithText("VOUCHERKU_DETAIL_REVAMP_END_DATE", COMING_SOON_COPY);
        HelperData.setLastActionPage(new VoucherkuDetailPage(iosDriver));
    }

    public void tapOnUseVoucherButton() {
        waitForVisibilityOf("VOUCHERKU_DETAIL_REVAMP_USE_VOUCHER", 15);
        tapElement("VOUCHERKU_DETAIL_REVAMP_USE_VOUCHER");
    }

    public void tapOnTermsAndUsageInfo() {
        waitForVisibilityOf("VOUCHERKU_DETAIL_REVAMP_TERM_AND_USAGE_INFO", 5);
        tapElement("VOUCHERKU_DETAIL_REVAMP_TERM_AND_USAGE_INFO");
    }

    public void validateVoucherInformationDraggableIsDisplayed() {
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_VOUCHER_INFORMATION_DRAGGABLE");
    }

    public void tapOnVoucherInformationTerms() {
        waitForVisibilityOf("VOUCHERKU_DETAIL_REVAMP_TERMS_TAB", 5);
        tapElement("VOUCHERKU_DETAIL_REVAMP_TERMS_TAB");

    }

    public void tapOnVoucherInformationUsageInfo() {
        waitForVisibilityOf("VOUCHERKU_DETAIL_REVAMP_USAGE_INFO_TAB", 5);
        tapElement("VOUCHERKU_DETAIL_REVAMP_USAGE_INFO_TAB");
    }

    public void validateVoucherInformationTermsAndUsageInfoText() {
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_TERM_USAGE_INFO_TEXT");
    }

    public void validateVoucherCodeOnUsageInfo() {
        tapOnVoucherInformationUsageInfo();
        validateDisplayed("VOUCHERKU_DETAIL_REVAMP_VOUCHER_CODE");
        setVoucherCode();
    }

    public void validateVoucherCodeOnUsageInfo(String voucherCode) {
        validateElementContainsText("VOUCHERKU_DETAIL_REVAMP_VOUCHER_CODE", voucherCode);

    }

    public void closeVoucherInformationDraggable() {
        tapElement("VOUCHERKU_DETAIL_REVAMP_CLOSE_BUTTON");
    }

    public void setVoucherCode() {
        String voucher = getText("VOUCHERKU_DETAIL_REVAMP_VOUCHER_CODE");
        String voucherCode = voucher.split(" ")[2].trim();
        SubsidiesData.setVoucherCode(voucherCode);
    }

    public void validateVoucherkuBeforeClaim() {
        if (isElementExist("voucher_detail_tnc_before_claim", 5)) {
            verifyElementNotExist("VOUCHERKU_DETAIL_REVAMP_TERM_USAGE_INFO_TEXT");
        } else {
            validateVoucherkuRevampDetail();
        }
    }

    public void validateProductList() {
        swipeUpToElement("VOUCHER_DETAIL_REVAMP_PRODUCT_LIST");
        validateExist("VOUCHER_DETAIL_REVAMP_PRODUCT_LIST", 5);
        HelperData.setLastActionPage(new VoucherkuDetailPage(iosDriver));
    }

    public void tapOnProductList() {
        tapElement("VOUCHER_DETAIL_REVAMP_PRODUCT_LIST", 5);
    }

    public void validateSuccessApplied() {
        validateExist("VOUCHER_DETAIL_SUCEESS_APPLIED", 10);
    }
}
