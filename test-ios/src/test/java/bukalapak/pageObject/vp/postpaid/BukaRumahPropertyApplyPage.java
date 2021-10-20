package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.PostpaidBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaRumahPropertyApplyPage extends PostpaidBasePage {

    public BukaRumahPropertyApplyPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateListPropertyWithArea() {
        changeContext().toWebview();
        verifyElementExist("postpaid_buka_rumah_list_property_area", 20, "Property not found");
        validateElementWithText("postpaid_buka_rumah_list_property_area", PostpaidData.getArea());
        verifyElementsExist("postpaid_buka_rumah_list_property_image", 0);
        HelperData.setLastActionPage(new BukaRumahPropertyApplyPage(iosDriver));
    }

    public void chooseTopPropertyOnListProperty() {
        setPropertyData();
        tapElements("postpaid_buka_rumah_list_property_image", 0);
    }

    private void setPropertyData() {
        String propertyType = getTextFromElement("postpaid_buka_rumah_list_property_type", 0).split(" ")[0].trim();

        PostpaidData.setPropertyName(getTextFromElement("postpaid_buka_rumah_list_property_name", 0));
        PostpaidData.setPropertyType(propertyType.toUpperCase());
        PostpaidData.setPropertyBuildingArea(getTextFromElement("postpaid_buka_rumah_list_property_wide", 0).split(" ")[1].trim());
        PostpaidData.setPropertySurfaceArea(getTextFromElement("postpaid_buka_rumah_list_property_wide", 2).split(" ")[1].trim());
        PostpaidData.setPropertyPrice(getIntFromRp(getTextFromElement("postpaid_buka_rumah_list_property_price", 0)));
    }

    public void validateDetailPropertyData() {
        String actualBuildingArea = getTextFromElement("postpaid_buka_rumah_detail_property_wide", 0).split(" ")[2].trim();
        String actualSurfaceArea = getTextFromElement("postpaid_buka_rumah_detail_property_wide", 1).split(" ")[2].trim();
        int actualPrice = getIntFromRp(getTextFromElement("postpaid_buka_rumah_detail_property_price"));

        verifyElementExist("postpaid_buka_rumah_detail_property_type", 20, "Property not found");
        validateElementWithText("postpaid_buka_rumah_detail_property_type", PostpaidData.getPropertyType().toUpperCase());
        validateElementWithText("postpaid_buka_rumah_detail_property_name", PostpaidData.getPropertyName());
        validateValue().equals(PostpaidData.getPropertyBuildingArea(), actualBuildingArea);
        validateValue().equals(PostpaidData.getPropertySurfaceArea(), actualSurfaceArea);
        validateValue().equals(PostpaidData.getPropertyPrice(), actualPrice);
        HelperData.setLastActionPage(new BukaRumahPropertyApplyPage(iosDriver));
    }

    public void tapOnAjukanKPRButton() {
        tapElement("postpaid_buka_rumah_ajukan_KPR_button");
    }

    public void tapOnLanjutkanIsiFormButton() {
        tapElement("postpaid_buka_rumah_lanjutkan_isi_form_button");
    }

    public void validateFormKPR() {
        verifyElementExist("postpaid_buka_rumah_submission_form_identity_number", 20, "Form KPR cannot be loaded");
        validateElementWithText("postpaid_buka_rumah_submission_form_info_text", "Info pribadi");
        validateElementWithText("postpaid_buka_rumah_submission_form_description", "Lengkapi info pribadi kamu sesuai KTP");
        verifyElementExist("postpaid_buka_rumah_submission_form_full_name");
        verifyElementExist("postpaid_buka_rumah_submission_form_phone_number");
        swipeUpToElement("postpaid_buka_rumah_submission_form_continue_button");
        verifyElementExist("postpaid_buka_rumah_submission_form_email");
        verifyElementExist("postpaid_buka_rumah_submission_form_address");
        verifyElementExist("postpaid_buka_rumah_submission_form_profession");
        verifyElementExist("postpaid_buka_rumah_submission_form_income");
        verifyElementExist("postpaid_buka_rumah_submission_form_referral_code");
        HelperData.setLastActionPage(new BukaRumahPropertyApplyPage(iosDriver));
    }

    public void validateRiwayatisEmptyMessage() {
        changeContext().toNative();
        verifyElementExist("postpaid_buka_rumah_riwayat_transaksi_empty_message", 20, "History transaction cannot be loaded");
        verifyElementExist("postpaid_buka_rumah_riwayat_transaksi_header");
        verifyElementExist("postpaid_buka_rumah_riwayat_transaksi_empty_title");
        HelperData.setLastActionPage(new BukaRumahPropertyApplyPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
