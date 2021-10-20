package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class OneKlikAccountCheckoutStepDefinitions extends TestInstrument implements En {

    public OneKlikAccountCheckoutStepDefinitions() {

        Given("user is in \"oneklik_account_checkout\" page", () -> {
            bukalapak.oneKlikAccountCheckoutPage().userOnOneKlikAccountCheckoutPage();
        });

        And("user verify that onboarding text is existed", () -> {
            bukalapak.iOSBasePage().verifyElementExist("oneklik_onboarding_text");
        });

        And("user verify that button Hubungkan Akun is existed", () -> {
            try {
                bukalapak.iOSBasePage().verifyElementExist("oneklik_hubungkan_akun_button");
            } catch (Exception e) {
                bukalapak.iOSBasePage().tapElement("alchemy_back_button");
                bukalapak.iOSBasePage().swipeUpToElement("metode_pembayaran_oneklik_text");
                bukalapak.iOSBasePage().tapElement("metode_pembayaran_oneklik_text");
                bukalapak.iOSBasePage().verifyElementExist("oneklik_hubungkan_akun_button");
            }
        });

        When("user tap button Hubungkan Akun", () -> {
            bukalapak.iOSBasePage().tapElement("oneklik_hubungkan_akun_button");
        });
    }
}
