package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ReferralCreditsPage extends BasePage {

    public ReferralCreditsPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void scrollDownToAjakBerhadiah(){
        swipeUpToElement("ajak_berhadiah_akun_text");
        tapElement("ajak_berhadiah_akun_text");
    }

    public void dismissModalReferral(){
        if (isElementVisible("mau_coba_button")) {
            tapElement("mau_coba_button");
        }
    }

    public void verifyOnReferralDashboard(){
        waitFor(5);
        validateExist("ajak_berhadiah_navbar");
    }

    public void validatePhoneVerificationButton(){
        validateExist("verify_phone_button");
        goToHomePage();
    }

    public void validateBelanjaDulu(){
        validateExist("belanja_dulu_button");
        goToHomePage();
    }

    public void validateModalMaaf(){
        validateExist("hadiah_msg_text", 10);
        tapElement("aku_mau_button");
    }

    public void validateMaxDownline(){
        validateExist("referral_max_rewards");
        goToHomePage();
    }

    public void emptyStateDownline(){
        validateExist("referral_no_downline");
        tapElement("referral_no_downline");
    }

    public void verifyOnReferralRewards(){
        changeContext().toWebview();
        validateExist("referral_reward_page_text", 5);
    }

    public void verifyEmptyBerlangsungDownline(){
        validateExist("empty_berlangsung_downline_text");
    }

    public void tapOnSelesaiTab(){
        webViewTapOnElement("selesai_tab");
    }

    public void verifyEmptySelesaiDownline(){
        tapOnSelesaiTab();
        validateExist("empty_selesai_downline_text");
        changeContext().toNative();
        goToHomePage();
    }

    public void referralVerifyGiftNotif(){
        validateDisplayed("referral_have_gift");
        tapElement("referral_have_gift");
    }

    public void referralVerifyHaveGift(){
        validateExist("buka_hadiah_btn");
    }

    public void validateVerifiedDownline(){
        validateExist("verified_downline_name_text", 15);
    }

    public void validateUnverifiedDownline(){
        validateExist("registered_downline_progress_text");
    }

    public void validateFinishedDownline(){
        validateExist("success_downline_icon");
    }

    public void validateFailedDownline(){
        validateExist("gagal_downline_text");
    }

    public void validateCekCreditsLink(){
        webViewTapOnElement("cek_credits_text");
        changeContext().toNative();
        validateExist("saldo_title", 5);
        HelperData.setLastActionPage(new ReferralCreditsPage(iosDriver));
    }

    public void tapBackAfterRegisterReferral(){
        changeContext().toNative();
        backToHomePage();
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
