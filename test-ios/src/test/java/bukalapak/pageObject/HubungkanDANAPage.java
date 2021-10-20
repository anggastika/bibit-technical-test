package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.time.Duration;

public class HubungkanDANAPage extends BasePage {

    public HubungkanDANAPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void closeBindingPopup() {
        if (isElementVisible("hubungkan_dana_popup_button_alt", 15)) {
            tapElement("hubungkan_dana_popup_close_alt");
            validateNotExist("hubungkan_dana_popup_button_alt",15);
        }
    }

    //Binding Entrypoint
    public void tapBindingEntrypoint(String page) {
        if (page != null) {
            switch (page) {
                case "Home":
                    bindingHomePage();
                    break;
                case "Onboarding":
                    bindOnboardingDana();
                    break;
                case "Credits Mutation":
                    bindCreditsMutation();
                    break;
                case "Alchemy Marketplace Checkout":
                    bindOnAlchemyMPCheckout("dana");
                    break;
                case "Alchemy Recurring":
                    tapOnAktifkanDana();
                    break;
                case "Alchemy VP Checkout":
                    bindOnAlchemyVPCheckout("dana");
                    break;
                case "Top Up DANA VP":
                case "Serbu Seru Checkout":
                    bindOnTopUpDANAVP();
                    break;
                case "Credits Checkout":
                    bindEntrypointCreditsCheckout("normal bind");
                    break;
                case "Rebind Credits Checkout":
                    bindEntrypointCreditsCheckout("rebind");
                    break;
                case "Algebra VP Checkout":
                    bindEntrypointAlgebraVP();
                    break;
                case "BukaCredits Marketplace":
                    bindOnAlchemyMPCheckout("saldo");
                    break;
                case "BukaCredits VP":
                    bindOnAlchemyVPCheckout("saldo");
                    break;
                case "Credits as Mixpayment":
                    bindEntrypointCreditsMix();
                    break;
                case "Saldo Revamp":
                    LogUtil.info("skip to hubungkan popup validation");
                    break;
                default:
                    tapElement("general_binding_button", 20);
                    tapIfHubungkanPopupExist();
                    break;
            }
        } else {
            Assert.fail("Please define binding entrypoint!");
        }
        tapIfHubungkanPopupExist();
        HelperData.setLastActionPage(new HubungkanDANAPage(iosDriver));
    }

    private void bindOnboardingDana() {
        if (isElementExist("home_allow_button", 5)) {
            tapElement("home_allow_button");
        }
        if (isElementVisible("onboarding_kenalan_dong_button", 5)) {
            tapElement("onboarding_kenalan_dong_button");
            tapElement("onboarding_bind_dana_button");
        } else {
            bindingHomePage();
        }
    }

    private void bindingHomePage() {
        if (!isElementVisible("hubungkan_dana_popup_button", 5)) {
            tapElement("home_akun_tab");
            waitForVisibilityOf("general_binding_button", 20);
            tapElement("home_blhome_tab");
            tapElement("general_binding_button", 20);
        }
    }

    private void tapIfHubungkanPopupExist() {
        if (isElementVisible("hubungkan_dana_popup_button", 5)) {
            tapElement("hubungkan_dana_popup_button");
        } else {
            LogUtil.info("this entrypoint is not using binding popup");
        }
    }

    private void bindCreditsMutation() {
        tapElement("saldo_credits_tab", 20);
        swipeUpToElement("saldo_bind_bukacredits_button");
        tapElement("saldo_bind_bukacredits_button");
    }

    private void bindOnAlchemyMPCheckout(String entrypoint) {
        tapElement("checkout_marketplace_page_metode_pembayaran");
        waitForVisibilityOf("checkout_metode_bayar_name");
        if (entrypoint.equals("saldo")) {
            swipeUpToElement("checkout_marketplace_saldo_option");
            tapElement("checkout_marketplace_saldo_option");
            tapElement("saldo_bind_bukacredits_entrypoint");
        } else {
            swipeUpToElement("checkout_page_dana_hubungkan_DANA");
            tapElement("checkout_page_dana_hubungkan_DANA");
        }
    }

    private void bindEntrypointCreditsCheckout(String state) {
        if (isElementVisible("checkout_marketplace_page_metode_pembayaran")) {
            tapElement("checkout_marketplace_page_metode_pembayaran");
        } else {
            tapElement("checkout_non_marketplace_alchemy_choose_payment_button");
        }
        swipeUpToElement("checkout_page_credits_text");
        verifyElementExist("saldo_bind_bukacredits_txt");
        if (state.equals("rebind")) tapElement("checkout_page_credits_text");
    }

    private void bindOnAlchemyVPCheckout(String entrypoint) {
        if (entrypoint.equals("saldo")) {
            tapElement("saldo_bind_bukacredits_entrypoint");
        } else {
            verifyElementExist("checkout_page_dana_ketentuan_text");
            tapElement("checkout_page_dana_hubungkan_sekarang_DANA_button");
        }
    }

    private void bindOnTopUpDANAVP() {
        swipeUpToElement("topup_dana_vp_aktifkan_sekarang_dana_button");
        for (int i = 0; i < 5; i++) {
            if (isElementVisible("topup_dana_vp_aktifkan_sekarang_dana_button")) {
                tapElement("topup_dana_vp_aktifkan_sekarang_dana_button");
            } else {
                break;
            }
            waitFor(2);
        }
    }

    private void bindEntrypointAlgebraVP() {
        tapElement("checkout_non_marketplace_dana_aktifkan_button");
    }

    private void tapOnAktifkanDana() {
        waitForVisibilityOf("activate_dana_button");
        tapElement("activate_dana_button", 3);
    }

    private void bindEntrypointCreditsMix() {
        if (isElementVisible("checkout_marketplace_mixpayment_txt")) {
            verifyElementExist("checkout_marketplace_credits_mix_binding_btn");
        }
    }

    //After Binding
    public void verifyAfterBinding(String page) {
        if (page != null) {
            switch (page) {
                case "Saldo Bukalapak":
                    afterBindOnSaldoPage();
                    break;
                case "Akun Kamu":
                    waitForVisibilityOf("akun_page_saldo_dana", 20);
                    break;
                case "Home":
                    waitForVisibilityOf("homepage_dana_balance_text", 20);
                    break;
                case "Alchemy Marketplace Checkout":
                    verifyElementNotExist("checkout_page_dana_hubungkan_DANA");
                    break;
                case "Alchemy VP Checkout":
                    verifyElementNotExist("checkout_page_dana_ketentuan_text");
                    break;
                case "Top Up DANA VP":
                    waitForVisibilityOf("topup_dana_vp_screen_title",20);
                    verifyElementNotExist("topup_dana_vp_aktifkan_sekarang_dana_button");
                    break;
                case "Serbu Seru Checkout":
                    verifyElementNotExist("topup_dana_vp_aktifkan_sekarang_dana_button");
                    break;
                case "Algebra VP Checkout":
                    verifyElementNotExist("checkout_non_marketplace_dana_aktifkan_button");
                    verifyElementExist("checkout_non_marketplace_dana_tambah_button");
                    break;
                case "BukaCredits Marketplace":
                case "BukaCredits VP":
                    verifyElementNotExist("saldo_bind_bukacredits_txt");
                    break;
                case "Saldo Revamp":
                    verifyElementNotExist("saldo_revamp_dana_hubungkan_dana_btn");
                    verifyElementNotExist("saldo_revamp_credits_hubungkan_dana_btn");
                    break;
                default:
                    LogUtil.info("should be verified by other step definitions");
                    break;
            }
        } else {
            Assert.fail("Please define binding page!");
        }
        verifyElementNotExist("general_binding_button");
        HelperData.setLastActionPage(new HubungkanDANAPage(iosDriver));
    }

    private void afterBindOnSaldoPage() {
        if (!isElementVisible("saldo_pindahkan_konfirmasi_after_bind_button")) {
            waitForVisibilityOf("saldo_bl_page_saldo_dana", 20);
            tapElement("saldo_back_button");
        }
    }

    //Rebind
    public void validateRebindPopup() {
        waitForVisibilityOf("rebind_popup_tittle", 20);
        verifyElementExist("rebind_popup_txt");
        assertTrue(isElementClickable("rebind_popup_hubungkan_btn"));
        assertTrue(isElementClickable("rebind_popup_lainkali_btn"));
        HelperData.setLastActionPage(new HubungkanDANAPage(iosDriver));
    }

    public void selectRebindOption(String option) {
        switch (option.toLowerCase()) {
            case ("hubungkan"):
                tapElement("rebind_popup_hubungkan_btn");
                break;
            case ("lain kali"):
                tapElement("rebind_popup_lainkali_btn");
                break;
            case ("bukabantuan"):
                tapBukaBantuanByCoordinate(0.9, 0.8); //bottom right
                if (isElementVisible("rebind_popup_txt", 5)) {
                    tapBukaBantuanByCoordinate(0.3, 0.7); //bottom left
                }
                break;
            default:
                Assert.fail("Option " + option + " not found");
                break;
        }
        HelperData.setLastActionPage(new HubungkanDANAPage(iosDriver));
    }

    public void rebindBukaBantuan() {
        if (!isElementVisible("rebind_bukabantuan_link",5)) {
            verifyElementExist("rebind_bukabantuan_tittle");
            verifyElementExist("rebind_bukabantuan_body");
        }
    }

    public void cancelBinding() {
        validateDisplayed("dana_container_back_button");
        validateDisplayed("dana_container_cancel_binding");
        tapElement("dana_container_back_button", 10);
        tapElement("dana_container_cancel_binding", 10);
    }

    private void tapBukaBantuanByCoordinate(double x, double y) {
        IOSElement element = (IOSElement) getElement("rebind_popup_txt");
        int target_x = element.getLocation().x;
        int target_y = element.getLocation().y;
        int width = element.getSize().width;
        int height = element.getSize().height;
        tapElement(target_x + (int) (width * x), target_y + (int) (height * y));
    }
}
