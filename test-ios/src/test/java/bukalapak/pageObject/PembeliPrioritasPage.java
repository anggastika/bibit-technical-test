package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PRIOData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import static bukalapak.TestInstrument.dotenv;

public class PembeliPrioritasPage extends BasePage {

    public PembeliPrioritasPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnPembeliPrioritasInfoPage() {
        waitForVisibilityOf("priority_tab_info_text", 15);
        verifyElementExist("priority_tab_status_text");
        verifyElementExist("priority_tab_pembelian_text");
        HelperData.setLastActionPage(new PembeliPrioritasPage(iosDriver));
    }

    public void userOnPembeliPrioritasStatusPage() {
        waitForVisibilityOf("priority_periode_text", 30);
        HelperData.setLastActionPage(new PembeliPrioritasPage(iosDriver));
    }

    public void checkPriorityBenefit(String type, String entryPoint) {
        if (entryPoint.equalsIgnoreCase("landing page")) {
            if (type.equalsIgnoreCase("gold")) {
                assertEquals(String.valueOf(getBenefitNumber()), dotenv.get("PRIO_GOLD_PACKAGE_BENEFIT_ONGKIR"));
            } else {
                assertEquals(String.valueOf(getBenefitNumber()), dotenv.get("PRIO_SILVER_PACKAGE_BENEFIT_ONGKIR"));
            }
        } else if (entryPoint.equalsIgnoreCase("cross-selling")) {
            String crossSellingBenefit = dotenv.get("PRIO_CROSS_SELLING_PACKAGE_BENEFIT_ONGKIR");
            assert crossSellingBenefit != null;
            assertEquals(getBenefitNumber(),
                    Integer.parseInt(crossSellingBenefit) - 1);
        } else {
            Assert.fail("Please choose the available item!");
        }
    }

    private int getBenefitNumber() {
        waitForVisibilityOf("priority_free_shipping_status_text");
        return Integer.parseInt(getElementValue("priority_free_shipping_status_text").substring(0, 3)
                .replaceAll("[^0-9]", ""));
    }

    // Auto-extend
    public void checkAutoExtendSection(boolean state) {
        if (state) {
            waitForVisibilityOf("priority_perpanjangan_tidak_aktif_text", 15);
            verifyElementNotExist("priority_perpanjangan_link");
        } else {
            waitForVisibilityOf("priority_perpanjangan_aktif_text", 15);
            waitForVisibilityOf("priority_perpanjangan_link");
        }
        HelperData.setLastActionPage(new PembeliPrioritasPage(iosDriver));
    }

    public void selectReason(String reason) {
        switch (reason) {
            case "Tidak Menguntungkan":
                tapElement("priority_tidak_menguntungkan_radio_button");
                break;
            case "Lain-lain":
                tapElement("priority_lain_lain_radio_button");
                typeAndEnterValueWithTimeOut("priority_berhenti_text_field", "Testing auto-extend");
                break;
            default:
                LogUtil.info("Reason is not implemented!");
                break;
        }
    }

    public void checkCashbackSection() {
        swipeDownToElement("priority_lihat_selengkapnya_cashback_button");
        verifyElementExist("priority_cashback_section_text");
    }

    public void checkCashbacks() {
        swipeDownToElement("priority_riwayat_cashback_date", 3);
        verifyElementExist("priority_riwayat_cashback_nominal");
        verifyElementExist("priority_riwayat_cashback_transaction_id");
        verifyElementExist("priority_riwayat_cashback_active_date");
        HelperData.setLastActionPage(new PembeliPrioritasPage(iosDriver));
    }

    public void checkFreeShippingUsage() {
        assertEquals(PRIOData.getOngkirBenefit() - 1, getBenefitNumber(), "Benefit isn't used!");
    }

    public void checkPendingPayment(String priorityTab) {
        if (priorityTab.contains("Info")) {
            assertTrue(isElementVisible("priority_pending_info_section", 20),
                    "Info for pembeli prioritas pending payment isn't displayed!");
        } else if (priorityTab.contains("Status")) {
            assertTrue(isElementVisible("priority_lanjutkan_pembayaran_button", 20),
                    "Lanjutkan Pembayaran button isn't displayed!");
        } else {
            validateExist("priority_menunggu_pembayaran_text", "Menunggu Pembayaran text isn't displayed!");
        }
    }

    public void checkFreeTrialSection() {
        swipeDownToElement("priority_trial_promo_text");
        waitForVisibilityOf("priority_trial_info_duration_text");
        waitForVisibilityOf("priority_trial_info_detail_text");
        waitForVisibilityOf("priority_trial_coba_langganan_button");
    }

    // DANA-Section
    public void checkJoinDanaSection(String state) {
        if (state.equalsIgnoreCase("shown")) {
            validateDisplayed("priority_info_unbind_dana_text");
            validateDisplayed("priority_hubungkan_dana_button");
            validateDisplayed("priority_perpanjangan_aktif_text");
        } else {
            validateNotDisplayed("priority_info_unbind_dana_text");
            validateNotDisplayed("priority_hubungkan_dana_button");
            validateDisplayed("priority_perpanjangan_tidak_aktif_text");
        }
    }

    public void tapOnHubungkanDanaButton() {
        tapElement("priority_hubungkan_dana_button");
    }

    public void checkPayWithDanaSection() {
        validateDisplayed("priority_info_bind_dana_text");
        validateDisplayed("priority_dana_balance_text");
    }

    public void tapOnTopUpDanaButton() {
        validateDisplayed("priority_topup_dana_button");
        tapElement("priority_topup_dana_button");
    }

    public void showPopUpHubungkanDana() {
        validateDisplayed("priority_hubungkan_dana_popup_title");
        validateDisplayed("priority_hubungkan_sekarang_dana_button");
        HelperData.setLastActionPage(new PembeliPrioritasPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
