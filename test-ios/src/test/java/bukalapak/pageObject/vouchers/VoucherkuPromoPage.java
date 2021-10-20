package bukalapak.pageObject.vouchers;

import bukalapak.data.HelperData;
import bukalapak.data.SubsidiesData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.ProductDetailsPage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NotFoundException;

public class VoucherkuPromoPage extends BasePage {
    public VoucherkuPromoPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void assertVoucherEmptyPage() {
        if (isElementVisible("voucherku_promo_page")) {
            assertTrue(isElementVisible("voucherku_promo_page", 10));
            validateValue().equalsTrue(getTextFromElement("voucherku_promo_page").contains("Ada Fitur Baru lho!"));
            validateElementVisible("voucherku_lihat_button");
            tapElement("voucherku_lihat_button", 5);
        } else {
            assertTrue(isElementVisible("Voucherku_empty_content", 10));
            validateElementVisible("voucherku_empty_invitation_text");
        }
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void assertVoucherkuCard() {
        if (isElementVisible("voucherku_pakai_button", 10)) {
            verifyElementsExist("voucherku_filter_type", 1);
            validateElementVisible("voucherku_card");
            assertTrue(isElementVisible("voucherku_card", 10));
        } else {
            verifyElementsExist("voucherku_filter_type", 0);
        }
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void tapOnVoucherkuCard() {
        verifyElementExist("voucherku_card");
        tapElement("voucherku_card", 5);
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void selectFilterVoucherku(String categoryName) {
        switch (categoryName) {
            case "Semua":
                verifyElementsExist("voucherku_filter_type", 0);
                tapElements("voucherku_filter_type", 0, 5);
                break;
            case "Cashback":
                if (isElementExist("voucherku_filter_type")) {
                    tapElements("voucherku_filter_type", 1, 5);
                }
                break;
            case "Ongkos Kirim":
                if (isElementExist("voucherku_filter_type")) {
                    tapElements("voucherku_filter_type", 2, 5);
                }
                break;
            case "Potongan Langsung":
                if (isElementExist("voucherku_filter_type")) {
                    tapElements("voucherku_filter_type", 3, 5);
                }
                break;
            default:
                LogUtil.error("Cannot find elements!");
                break;
        }
    }

    public void verifyFilterVoucherku(String filterName) {
        if (isElementVisible("voucherku_lihat_button")) {
            tapElement("voucherku_lihat_button", 5);
        }
        switch (filterName) {
            case "Semua":
                assertVoucherkuCard();
                break;
            case "Cashback":
                assertVoucherkuCard();
                break;
            case "Ongkos Kirim":
                assertVoucherkuCard();
                break;
            case "Potongan Langsung":
                assertVoucherkuCard();
                break;
            default:
                assertVoucherEmptyPage();
                break;
        }
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void tapOnCTAListVoucher() {
        verifyElementExist("voucherku_pakai_button");
        tapElement("voucherku_pakai_button", 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void validateDetailVoucherku() {
        validateExist("voucherku_banner_detail_voucher", 5);
        if (isElementExist("voucherku_syarat_detail_voucher")) {
            validateExist("voucherku_howto_detail_voucher", 5);
            validateExist("voucherku_salin_detail", 5);
        }
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void tapOnCTADetailVoucher() {
        tapElement("voucherku_salin_detail", 5);
    }

    public void copyVoucherCodePDP() {
        tapElement("voucherku_promo_copy_txt_pdp", 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void validateSuccessCopy() {
        validateExist("voucherku_success_copy", 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void backToPDPPage() {
        validateExist("voucherku_back_btn", 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void selectVoucherkuPage(String voucherName) {
        validateExist("voucherku_card", 5);
        SubsidiesData.setVoucherName(voucherName);
        swipeUpToElement(constructLocator("voucherku_type", voucherName), 20);
        tapElement(constructLocator("voucherku_type", voucherName), 5);
    }

    public void selectVoucherPDP(String voucherName) {
        SubsidiesData.setVoucherName(voucherName);
        swipeUpToElement(constructLocator("voucherku_type", voucherName));
        tapElement(constructLocator("voucherku_type", voucherName), 5);
    }

    public void tapUseVoucherku(String voucherName) {
        validateExist("voucherku_card", 5);
        SubsidiesData.setVoucherName(voucherName);
        swipeUpToElement(constructLocator("voucherku_promo_page_btn_pakai_voucher", voucherName));
        tapElement(constructLocator("voucherku_promo_page_btn_pakai_voucher", voucherName), 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void dismissUseVoucherku() {
        tapElement("voucherku_promo_page_dismiss_use_voucher", 10);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void validateVoucherkuDetailPage(String voucherName) {
        validateValue().contains(SubsidiesData.getVoucherName(), getTextFromElement(constructLocator("voucherku_title_detail_voucher", voucherName)));
    }

    public void validateVoucherSellerSection() {
        validateExist("voucher_seller_text", 5);
    }

    public void valideteNoVoucherClaim() {
        validateNotExist("voucherku_card", 5);
    }

    public void validateVoucherHistory() {
        tapElement("voucherku_promo_history_entry_point", 5);
    }

    public void userIsOnVoucherHistory() {
        validateExist("voucherku_promo_history_header", 5);
        validateExist("voucherku_promo_history_card", 5);
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void validateVoucherPelapakonPDP(String appear, String voucherName) {
        switch (appear) {
            case "should":
                swipeUpToElement(constructLocator("voucherku_type", voucherName));
                validateExist(constructLocator("voucherku_type", voucherName));
                break;
            case "should not":
                validateNotExist(constructLocator("voucherku_type", voucherName), 5);
            default:
                LogUtil.error("Cannot find elements!", new NotFoundException());
                break;
        }
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyVoucherOnPromoPage() {
        verifyElementExist("promo_page");
        HelperData.setLastActionPage(new VoucherkuPromoPage(iosDriver));
    }
}
