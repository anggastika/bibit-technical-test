package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PantauanSainganPage extends BasePage {

    public PantauanSainganPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPantauanSaingan() {
        verifyElementExist("pantau_barang_saingan_back_button");
        verifyElementExist("pantauan_saingan_setting_button");
        verifyElementExist("pantauan_saingan_terakhir_diperbarui_text");
        verifyElementExist("pantauan_saingan_perbarui_button");
        verifyElementExist("pantauan_saingan_barang_saingan_tab");
        verifyElementExist("pantauan_saingan_barang_saya_tab");
        HelperData.setLastActionPage(new PantauanSainganPage(iosDriver));
    }

    public void verifyKeywordTitle(String keyword) {
        assertEquals(keyword, getTextFromElement("pantauan_saingan_title"));
    }

    public void verifySorter(String sorter) {
        waitForVisibilityOf(constructLocator("pantauan_saingan_sorter", sorter));
    }

    public void verifyNotificationToggle(String notifyStatus) {
        if (notifyStatus.equals("on")) {
            validateValue().equals("1", getElementValue("pantauan_saingan_notif_toggle"));
        } else {
            validateValue().equals("0", getElementValue("pantauan_saingan_notif_toggle"));
        }
        HelperData.setLastActionPage(new PantauanSainganPage(iosDriver));
    }

    public void verifyPerbaruiSection() {
        validateDisplayed("pantauan_saingan_perbarui_button");
        if (isElementEnabled("pantauan_saingan_perbarui_button")) {
            tapElement("pantauan_saingan_perbarui_button");
        }
        waitForVisibilityOf("pantauan_saingan_perbarui_button", 15);
        validateDisabled("pantauan_saingan_perbarui_button");
        waitForVisibilityOf("pantau_barang_saingan_back_button");
        HelperData.setLastActionPage(new PantauanSainganPage(iosDriver));
    }

    public void verifyListOfBarangSaingan() {
        verifyBarangSainganSections(1);
        verifyBarangSainganSections(2);
        verifyBarangSainganSections(3);
    }

    private void verifyBarangSainganSections(int ranks) {
        waitForVisibilityOf(constructLocator("pantauan_saingan_barang_saingan_product_rank_text", ranks), 15);
        validateValue().equals( ranks + 1 + "", getTextFromElement(constructLocator("pantauan_saingan_barang_saingan_product_rank_text", ranks)));
        validateValue().equalsFalse(getTextFromElement(constructLocator("pantauan_saingan_barang_saingan_product_title_text", ranks)).isEmpty());
        validateValue().equalsFalse(getTextFromElement(constructLocator("pantauan_saingan_barang_saingan_product_seller_text", ranks)).isEmpty());
        validateValue().equalsTrue(getTextFromElement(constructLocator("pantauan_saingan_barang_saingan_product_sold_text", ranks)).matches("\\d+ terjual"));
        validateRpFormat(constructLocator("pantauan_saingan_barang_saingan_product_price_text", ranks));
    }

    public void tapBarangSaingan(int index) {
        tapElement(constructLocator("pantauan_saingan_barang_saingan_product_rank_text", index));
    }

    public void verifyListOfBarangSaya() {
        waitForVisibilityOf(constructLocator("pantauan_saingan_barang_saya_product_rank_text", "1"));
        validateValue().equalsTrue(getTextFromElement(constructLocator("pantauan_saingan_barang_saya_product_rank_text", "1")).matches(">?\\d+"));
        validateValue().equalsFalse(getTextFromElement(constructLocator("pantauan_saingan_barang_saya_product_title_text", "1")).isEmpty());
        validateValue().equalsFalse(getTextFromElement(constructLocator("pantauan_saingan_barang_saya_product_seller_text", "1")).isEmpty());
        validateValue().equalsTrue(getTextFromElement(constructLocator("pantauan_saingan_barang_saya_product_sold_text", "1")).matches("\\d+ terjual"));
        validateRpFormat(constructLocator("pantauan_saingan_barang_saya_product_price_text", "1"));
        validateDisplayed("pantauan_saingan_barang_saya_product_push_button");
    }

    public void verifyPromotedPush() {
        validateDisplayed("pantauan_saingan_barang_saya_product_promoted_button");
        tapElement("pantauan_saingan_barang_saya_product_promoted_button");
        waitForVisibilityOf("promoted_push_satuan_title_text", 15);
        tapElement("alchemy_navbar_back_button");
    }

    public void verifyUbahButton() {
        validateDisplayed("pantauan_saingan_barang_saya_product_ubah_button");
        tapElement("pantauan_saingan_barang_saya_product_ubah_button");
        validateDisplayed("edit_product_ubah_info_title");
        tapElement("edit_product_batal_button");
        waitForVisibilityOf("pantauan_saingan_barang_saya_product_ubah_button");
        HelperData.setLastActionPage(new PantauanSainganPage(iosDriver));
    }

    public void verifyPushBukadompetPopup() {
        waitForVisibilityOf("pantauan_saingan_push_popup_beli_bukadompet_button");
        waitForVisibilityOf("pantauan_saingan_push_popup_nanti_button");
        validateDisplayed("pantauan_saingan_push_popup_beli_title_text");
        validateDisplayed("pantauan_saingan_push_popup_beli_bukadompet_desc_text");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
