package bukalapak.pageObject.martech;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by koryang on 03/09/20.
 */

public class ReferralLinkPage extends BasePage {

    public ReferralLinkPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    // referral link feature on ajak-ajak berhadiah screen
    public void userOnAjakAjakBerhadiahPage() {
        if (isElementVisible("ajak_ajak_berhadiah_cara_dapetin_kado_label", 10)) {
            tapElement("ajak_ajak_berhadiah_close_icon");
        }
        waitForVisibilityOf("ajak_ajak_berhadiah_label", 30);
        verifyElementExist("referral_share_sheet_description",10,"element not exist");
        HelperData.setLastActionPage(new ReferralLinkPage(iosDriver));
    }

    public void userInTemanCuanPage() {
        waitForVisibilityOf("teman_cuan_label", 30);
        verifyElementExist("teman_cuan_share_sheet_description",10,"element not exist");
        HelperData.setLastActionPage(new ReferralLinkPage(iosDriver));
    }

    public void userNotInTemanCuanPage() {
        waitFor(3); // need wait for open browser
        verifyElementNotExist("teman_cuan_label");
        HelperData.setLastActionPage(new ReferralLinkPage(iosDriver));
    }

    public void clickTambahHadiahButton() {
        waitForElementClickable("ajak_ajak_berhadiah_tambah_hadiah_button", 20);
        tapElement("ajak_ajak_berhadiah_tambah_hadiah_button", 10);
    }

    public void clickShareToSocMed(String type){
        waitForVisibilityOf("referral_share_link_form", 30);
        tapElement(constructLocator("referral_share_socmed_icon", type));
    }

    public void clickShareTemanCuanToSocMed(String type) {
        waitForVisibilityOf("teman_cuan_share_link_form", 30);
        tapElement(constructLocator("referral_share_socmed_icon", type));
    }

    public void clickUndangTemanButton() {
        waitForVisibilityOf("teman_cuan_undang_teman_button", 30);
        tapElement("teman_cuan_undang_teman_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateOpenAjakAjakScreen() {
        waitForElementClickable("ajak_ajak_berhadiah_aku_juga_mau", 20);
        tapElement("ajak_ajak_berhadiah_aku_juga_mau");
        validateDisplayed("ajak_ajak_berhadiah_tambah_hadiah_button");
        HelperData.setLastActionPage(new ReferralLinkPage(iosDriver));
    }

    public String getBranchLinkFromReferralLink() {
        String sharedLink = iosDriver.getClipboardText();
        return sharedLink.substring(sharedLink.indexOf("https://bl.app.link/"));
    }
}
