package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class PromotedPushOtomatisPage extends BasePage {

    public PromotedPushOtomatisPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTambahPromotedPush() {
        verifyElementExist("tambah_promoted_push_header");
        verifyElementExist("nama_promosi_label");
        verifyElementExist("detail_promosi_label");
        verifyElementExist("barang_dipromosikan_label");
        verifyElementExist("info_perbedaan_harga");
        verifyElementExist("tambah_promosi_button");
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void tambahPromotedOtomatis() {
        tapElement("promoted_push_otomatis_tab");
        tapElement("tambah_promoted_otomatis_button");
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void inputPromotionName(String promotionName) {
        waitForVisibilityOf("nama_promosi_field");
        typeAndEnterValueWithTimeOut("nama_promosi_field", promotionName);
    }

    public void inputPricePerClick(String bid) {
        typeAndEnterValueWithTimeOut("harga_per_klik_button", bid);
    }

    public void choosePeriode(String periode) {
        if (periode.equalsIgnoreCase("setiap hari")) {
            tapElement("periode_setiap_hari_button");
        } else {
            tapElement("periode_hari_tertentu_button");
            ArrayList<String> option = new ArrayList<>(Arrays.asList(periode.split("\\s*,\\s*")));
            for (String day : option) tapElement(constructLocator("periode_hari_tertentu_option", day));
        }
    }

    public void chooseMaxBudget(String maxBudget) {
        typeAndEnterValueWithTimeOut("max_budget_field", maxBudget);
    }

    public void checkCreatedPromotion(String promotionName) {
        verifyElementExist(constructLocator("title_promosi_text", promotionName));
    }

    public void tapOnPromotionList(String title) {
        tapElement(constructLocator("title_promosi_text", title));
    }

    public void promotionSuccesfullyDeleted(String title) {
        verifyElementNotExist(constructLocator("title_promosi_text", title));
        verifyElementNotExist("home_reco_panel");
    }

    public void userOnEmptyStatePromosiOtomatis() {
        verifyElementExist("promoted_push_otomatis_text");
        verifyElementExist("promoted_push_otomatis_tambah_button");
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void inputUpdateBidValue(String newBid) {
        typeAndEnterValueWithTimeOut("ubah_harga_per_klik", newBid);
    }

    public void inputUpdateMaxBudget(String newMaxBudget) {
        typeAndEnterValueWithTimeOut("ubah_max_budget_field", newMaxBudget);
    }

    public void verifyBidValueUpdated(String newBidValue) {
        verifyElementExist(constructLocator("harga_per_klik_after_text", newBidValue));
    }

    public void verifyMaxBudgetUpdated(String newMaxBudget) {
        verifyElementExist(constructLocator("max_budget_after_text", newMaxBudget));
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void verifyPromosiOtomatisStatus(String title, String status) {
        if (isElementVisible(constructLocator("title_promosi_text", title))) {
            String statusCampaign = getElementValue(constructLocator("status_promosi_text", title));
            if (status.equalsIgnoreCase("active"))
                assertEquals("Aktif", statusCampaign);
            else
                assertEquals("Tidak Aktif", statusCampaign);
        }
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void verifyNamaBarang(String name) {
        waitForVisibilityOf(constructLocator("nama_barang_text", name), 25);
        verifyElementExist(constructLocator("nama_barang_text", name));
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void clickTambahPromosiButton() {
        waitForVisibilityOf("tambah_promosi_otomatis_button", 25);
        tapElement("tambah_promosi_otomatis_button");
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void clickTambahButton() {
        waitForVisibilityOf("tambah_promosi_button", 25);
        tapElement("tambah_promosi_button");
    }

    public void verifyPopupModalUpgradePremiumSeller() {
        waitForVisibilityOf("popup_upgrade_premium_title", 25);
        verifyElementExist("popup_upgrade_premium_title");
        HelperData.setLastActionPage(new PromotedPushOtomatisPage(iosDriver));
    }

    public void deletePromotedPushCampaign() {
        waitForVisibilityOf("promosi_toggle");
        swipeUpToElement("hapus_promosi_button");
        tapElement("hapus_promosi_button");
        waitForVisibilityOf("popup_hapus_button");
        tapElement("popup_hapus_button");
    }

    public void selectProduct(String product) {
        waitForVisibilityOf(constructLocator("nama_barang_text", product), 25);
        tapElement(constructLocator("nama_barang_text", product));
    }

    public void chooseEndDate(Integer periode) {
        swipeUpToElement("periode_hingga_tanggal_checkbox");
        tapElement("periode_hingga_tanggal_checkbox");
        waitForVisibilityOf("periode_pilih_tanggal_dropdown");
        tapCenterOfElement("periode_pilih_tanggal_dropdown");
        waitForVisibilityOf("periode_tanggal_berakhir_text");
        DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d", new Locale("id", "ID"));
        int currentMonth = LocalDate.now().getMonth().getValue();
        int endMonth = LocalDate.now().plusDays(periode).getMonth().getValue();
        LogUtil.info("month info: " + currentMonth + "end month: " + endMonth);
        String endDate = LocalDate.now().plusDays(periode).format(fullDateFormatter);
        LogUtil.info("enddate info: " + endDate);
        LogUtil.info("end month info: " + (currentMonth - endMonth));
        if (currentMonth - endMonth != 0) {
            swipeLeftAtSpecifiedLocator("periode_calendar_section");
            LogUtil.info("need swipe");
        }
        tapElement(constructLocator("periode_end_date", endDate));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
