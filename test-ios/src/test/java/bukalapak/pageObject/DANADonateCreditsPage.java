package bukalapak.pageObject;

import bukalapak.data.DANAData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class DANADonateCreditsPage extends BasePage {

    public DANADonateCreditsPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    // Auto Donate Credits
    public void verifyAutoDonatePage() {
        String[] element = {"auto_donate_page_header", "auto_donate_page_desc", "auto_donate_page_amount_desc",
                "auto_donate_page_amount_value", "auto_donate_page_attention", "auto_donate_page_tnc_header",
                "auto_donate_page_detail_btn"};

        verifyElementListExist(element);
        tapElement("auto_donate_page_tnc_btn");
        verifyElementExist("auto_donate_page_tnc_value");
    }

    public void verifyAutoDonateBanner() {
        waitForVisibilityOf("saldo_bl_page_saldo_dana",30);
        tapElement("credits_tab_with_red_dot", 20);
        verifyElementExist("auto_donate_banner_desc");
        tapElement("auto_donate_banner_atur_btn");
    }

    public void verifyAutoDonateUnregPopup() {
        String[] element = {"auto_donate_unreg_popup_header", "auto_donate_unreg_popup_desc",
                "auto_donate_unreg_popup_ignore_btn", "auto_donate_unreg_popup_stop_btn"};

        tapElement("auto_donate_page_checkbox");
        tapElement("auto_donate_page_save_btn");
        verifyElementListExist(element);
        HelperData.setLastActionPage(new DANADonateCreditsPage(iosDriver));
    }

    public void verifyAutoDonateCancelPopup() {
        String[] element = {"auto_donate_cancel_popup_header", "auto_donate_cancel_popup_desc",
                "auto_donate_cancel_popup_cancel_btn", "auto_donate_cancel_popup_save_btn"};

        tapElement("auto_donate_page_checkbox");
        tapElement("auto_donate_page_back_btn");
        verifyElementListExist(element);
        HelperData.setLastActionPage(new DANADonateCreditsPage(iosDriver));
    }

    //TODO this step is temporary and will be deleted if bug has been fixed by iOS dev
    public void relaunchApp() {
        iosDriver.terminateApp("com.bukalapak.ios");
        iosDriver.activateApp("com.bukalapak.ios");
    }

    //Donate Credits (BukaDonasi)
    public void accessDonateCreditsLink(String amount) {
        DANAData.setAmountDonation(amount);
        String url = dotenv.get("DANA_CREDITS_DONATION_DEEPLINK") + amount;
        openDeepLink(url);
    }

    public void verifyDonateCreditsPage() {
        String[] element = {"donate_credits_page_header", "donate_credits_page_bg", "donate_credits_popup_header",
                "donate_credits_popup_desc", "donate_credits_popup_back_btn", "donate_credits_popup_cancel_btn"};

        verifyElementListExist(element);
        verifyElementExist(constructLocator("donate_credits_amount", DANAData.getAmountDonation()));
    }

    public void verifyDonateCreditsTrx(String state) throws Exception {
        tapElement("donate_credits_popup_yes_btn");
        if (state.equals("succeeds")) {
            verifySuccessDonateCredits();
        } else if (state.equals("fails")) {
            waitForVisibilityOf("donate_credits_trx_fail", 20);
            tapElement("donate_credits_trx_back_btn");
        } else {
            throw new Exception("Donate Credits state is only succeeds or fails");
        }
        HelperData.setLastActionPage(new DANADonateCreditsPage(iosDriver));
    }

    private void verifySuccessDonateCredits() {
        waitForVisibilityOf("donate_credits_trx_success",20);
        verifyElementExist(constructLocator("donate_credits_amount", DANAData.getAmountDonation()));
        tapElement("donate_credits_trx_detail_btn");
        waitForVisibilityOf("donate_credits_trx_bukadonasi_txt",30);
        verifyElementExist("donate_credits_trx_bukadonasi_paid");
    }
}
