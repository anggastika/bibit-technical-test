package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PXData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class BarangDrafPage extends BasePage {

    public BarangDrafPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private String getItemDraftText() {
        return getText("barang_draf_verify_text");
    }

    private String getItemDeletedText() {
        return getText("barang_draf_deleted_text");
    }

    public void validateProductDraftMatch() {
        if (isElementVisible("barang_draf_verify_text")) {
            assertEquals(PXData.getRandomProductName(), getItemDraftText());
        } else {
            assertTrue(isElementVisible("barang_draf_verify_text"), "Product name doesn't match");
        }
    }

    public void validateProductDraftDeleted() {
        if (isElementVisible("barang_draf_deleted_text")) {
            assertEquals("Barang Draf Kosong", getItemDeletedText());
        } else if (!PXData.getRandomProductName().equals(getTextFromElement("barang_draf_verify_text"))) {
            Assert.fail("Another product exist");
        } else {
            assertTrue(isElementVisible("barang_draf_deleted_text", 5), "Product name still exist");
        }
        HelperData.setLastActionPage(new BarangDrafPage(iosDriver));
    }

    public void validateProductDraftSuccessfullyDeleted() {
        try {
            validateExist("barang_draf_alert_success_delete", 10);
        }catch(Exception e) {
            validateExist("barang_draf_alert_success_delete_label", 10);
        }
        HelperData.setLastActionPage(new BarangDrafPage(iosDriver));
    }
    
    public void userOnPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnBarangDrafPage();
    }

    public void userOnBarangDrafPage() {
        verifyElementExist("barang_draf_page_title");
        HelperData.setLastActionPage(new BarangDrafPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
