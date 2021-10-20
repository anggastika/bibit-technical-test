package bukalapak.pageObject;

import bukalapak.data.CSIData;
import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KomplainPage extends BasePage {

    public KomplainPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKomplainPage() {
        if (isElementVisible("komplain_oke_button", 10)) {
            tapOkeButton();
        }
        waitForVisibilityOf("komplain_title", 5);
        waitForVisibilityOf("komplain_pembeli_tab", 5);
        waitForVisibilityOf("komplain_pelapak_tab", 5);
        waitForVisibilityOf("komplain_add_button", 5);
        waitForVisibilityOf("komplain_search_button", 5);
        HelperData.setLastActionPage(new KomplainPage(iosDriver));
    }

    public void tapOkeButton() {
        tapElement("komplain_oke_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void searchTransaction(String keyword) {
        String tmpKeyword = keyword;

        switch (keyword){
            case "noTagihan" :
                tmpKeyword= CSIData.getNomorTagihan();
                break;
            case "noTransaksi":
                tmpKeyword = TransactionData.getNomorTransaksi();
                break;
            default:
                break;
        }
        tapElement("komplain_search_button");
        IOSElement searchBox = getElement("komplain_search_textbox", 15);
        searchBox.setValue(tmpKeyword + "\n");
        waitForVisibilityOf("komplain_card", 60);
    }

    public void tapAddKomplain() {
        tapElement("komplain_add_button");
    }

    public void tapFirstTransaction() {
        waitForVisibilityOf("komplain_card", 10);
        tapElement("komplain_card");
    }

    public void goToKomplainPageViaDeepLink() {
        openDeepLink("https://www.bukalapak.com/complaint_platform");
        HelperData.setLastActionPage(new KomplainPage(iosDriver));
    }

    public void chooseKomplain(String komplain) {
        tapElement(constructLocator("komplain_pengajuan_option", komplain));
        tapElement("komplain_kirim_button");
    }

    public void validateStatusKomplain(String statusKomplain) {
        assertEquals(statusKomplain, getTextFromElement("komplain_status"));
    }

    public void tapFilter(String complainState) {
        tapElement(constructLocator("komplain_filter_button", complainState));
        waitForVisibilityOf("komplain_card", 10);
    }

    public void validateProductnName(String productName) {
        String complainTitle = getTextFromElement("komplain_first_card_title");
        validateValue().contains(productName, complainTitle.toLowerCase());
    }

    public void tapSellerTab() {
        tapElement("komplain_seller_tab");
    }

    public void tapBuyerTab() {
        tapElement("komplain_buyer_tab");
    }
}
