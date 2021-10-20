package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DigitalGoodsCheckoutPage extends VpBasePage {

    public DigitalGoodsCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateDigitalGoodsCheckbox() {
        waitForVisibilityOf("digital_goods_checkout_checkbox", 20);
    }

    public void tapOnCheckbox(String option) {
        String checkoutCheckbox = getElementValue("digital_goods_checkout_checkbox");

        swipeUpToElement("digital_goods_checkout_checkbox");

        if ((option.equals("ticks") && (checkoutCheckbox.equals("false"))) || (option.equals("unticks") && (checkoutCheckbox.equals("true")))) {
            tapElement("digital_goods_checkout_checkbox");
        }
    }

    public void validateDigitalGoodsCheckoutFee(String state) {
        int maxSwipe = 3;

        do {
            swipeUp(0.7, 0.1);
            maxSwipe--;
        } while(!isElementVisible("checkout_non_marketplace_rincian_harga_text") && maxSwipe-- >= 0);

        if (state.trim().isEmpty()) {
            assertEquals(getIntFromRp("digital_goods_checkout_insurance_fee_detail_text"), InsuranceData.getDigitalGoodsFee());
        } else {
            verifyElementNotExist("digital_goods_checkout_insurance_fee_detail_text");
        }
        nativeSwipeDown();
    }

    public void validateDigitalGoodsCardSectionDisplayed() {
        verifyElementExist("digital_goods_checkout_description_text", 15, "the Digital Goods description should be exist");
        verifyElementExist("digital_goods_checkout_product_title_text");
        InsuranceData.setProductName(getText("digital_goods_checkout_product_title_text"));
        InsuranceData.setDigitalGoodsFee(getIntFromRp("digital_goods_checkout_insurance_fee_text"));
    }

    public void tapOnBenefitButton() {
        tapElement("digital_goods_checkout_benefit_button");
    }

    public void tapOnTncButton() {
        tapElement("digital_goods_checkout_tnc_button");
    }

    public void tapOnOkButtonBenefitSection() {
        tapElement("digital_goods_benefit_ok_button");
    }

    public void validateBenefitSectionDisplayed() {
        assertEquals(getIntFromRp("digital_goods_benefit_insurance_fee_text"), InsuranceData.getDigitalGoodsFee());
        verifyElementExist("digital_goods_benefit_information_header_text");
        verifyElementExist("digital_goods_benefit_title_text");
        verifyElementExist("digital_goods_benefit_description_text");
        verifyElementExist("digital_goods_benefit_ok_button");
    }

    public void validateDigitalGoodsCheckboxChecked() {
        verifyElementExist("digital_goods_checkout_checkbox");
        String checkoutCheckbox = getElementValue("digital_goods_checkout_checkbox");
        validateValue().equalsTrue(checkoutCheckbox.equals("true"));
    }

    public void validateTncSectionDisplayed() {
        verifyElementExist("digital_goods_tnc_title_text");
        verifyElementExist("digital_goods_tnc_category_button");
    }

    public void validateAllTabDisplayed() {
        String[] tabs = {"Kebakaran", "Kecelakaan Diri", "Layar HP"};

        for (String tab : tabs) {
            tapOnTncSectionTab(tab);
        }
    }

    private void tapOnTncSectionTab(String tab) {
        switch (tab) {
            case "Kebakaran":
                tapElement("digital_goods_tnc_tab_kebakaran_button");
                break;
            case "Kecelakaan Diri":
                tapElement("digital_goods_tnc_tab_kecelakaan_diri_button");
                break;
            case "Layar HP":
                tapElement("digital_goods_tnc_tab_layar_hp_button");
                break;
            default:
                LogUtil.info("Invalid Digital Goods Tab Property");
                break;
        }
    }

    public void tapOnOkButtonTncSection() {
        swipeUpToElement("digital_goods_tnc_ok_button", 10);
        tapElement("digital_goods_tnc_ok_button");
    }

    public void validateDigitalGoodsInvoiceFee(String state) {
        if (state.trim().isEmpty()) {
            assertEquals(getIntFromRp("digital_goods_checkout_insurance_fee_detail_text"), InsuranceData.getDigitalGoodsFee());
        } else {
            verifyElementNotExist("digital_goods_invoice_insurance_fee_detail_text");
        }
    }

    public void validateOnProductDetailPage() {
        changeContext().toWebview();
        validateExist("digital_goods_product_detail_banner_image", 10);
        assertTextContains(InsuranceData.getProductName(),
                getText("digital_goods_product_detail_title_text"));
    }

    public void validateInsuranceDetailStandalone() {
        changeContext().toWebview();
        validateExist("digital_goods_product_detail_banner_image", 10);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
