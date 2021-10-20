package bukalapak.pageObject.vouchers;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.ProductDetailsPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class VoucherDetailPage extends BasePage {
    public VoucherDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void assertVoucherDetailPage() {
        assertTrue(isElementVisible("voucher_expiry_label",30));
        validateElementVisible("kode_voucher_label");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void assertVoucherInfo(String voucherInfo) {
        tapElement(constructLocator("voucher_info_tab", voucherInfo));
        assertTrue(isElementVisible("voucher_info_content",10));
        assertNotSame("", getElementValue("voucher_info_content"));
    }

    public void assertVoucherCode() {
        validateDisplayed("kode_voucher");
    }

    public void assertVoucherCopyButton() {
        validateDisplayed("voucher_copy_button");
    }

    public void tapVoucherCopyButton() {
        tapElement("voucher_copy_button");
    }

    public void snackbarMessage(String message) {
        waitForVisibilityOf(constructLocator("snackbar_msg", message),10);
    }

    public void assertHeaderImage() {
        assertTrue(isElementEnabled("voucher_header_image"));
    }

    public void validateEmptyPopularPage() {
        assertTrue(isElementExist("product_empty_popular_title"));
        isElementVisible("product_empty_popular_content", 15);
        isElementVisible("lihat_promo_popular_btn", 15);
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }
}
