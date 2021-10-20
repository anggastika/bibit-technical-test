package bukalapak.pageObject.investment;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaReksaGoalInvestmentPage extends BasePage {

    public BukaReksaGoalInvestmentPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    // region goal investment

    private void validateWebViewPageTitle(String expectedValue) {
        validateExist("REXA_GOAL_INV_HEADER_TITLE", 10);
        validateValue().equals(expectedValue, getText("REXA_GOAL_INV_HEADER_TITLE"));
    }

    public void tapOnGoalInvestmentSection() {
        if(isElementExist("REXA_GOAL_INVESTMENT_SECTION", 10)) {
            tapElement("REXA_GOAL_INVESTMENT_SECTION");
            InvestmentData.setUserHaveGoalInvestProduct(true);
            changeContext().toWebview();
        } else {
            tapElement("REXA_GOAL_INVESTMENT_TUJUAN_LAIN_BTN");
        }
    }

    public void checkUserHaveGoalInvestment() {
        if (isElementExist("REXA_GOAL_INVESTMENT_SECTION")) {
            InvestmentData.setUserHaveGoalInvestProduct(true);
        } else if(isElementExist("REXA_GOAL_INVESTMENT_TUJUAN_LAIN_BTN")) {
            InvestmentData.setUserHaveGoalInvestProduct(false);
        } else {
            LogUtil.info("Goal Investment section not exist!");
        }
    }

    public void validateActiveGoalInvestmentSectionDisplayed() {
        validateExist("REXA_GOAL_INVESTMENT_SECTION");
        validateExist("REXA_GOAL_INVESTMENT_SALDO_0_SECTION");
    }

    public void tapBeliPaketRekomendasiBtn() {
        tapElement("REXA_GOAL_INV_BELI_PAKET_REKOMENDASI_BTN");
    }

    public void tapBuatTujuanBaruBtn() {
        tapElement("REXA_GOAL_INV_BUAT_TUJUAN_INVESTASI_BTN");
    }

    public void tapBeliPaketRekomendasiBtnMulaiInv() {
        webViewTapOnElement("REXA_MULAI_INV_BELI_PAKET_BUTTON");
    }

    public void verifyGoalInvestmentPageDisplayed() {
        validateExist("REXA_GOAL_INV_BUAT_TUJUAN_INVESTASI_BTN", 10);
        validateWebViewPageTitle("Tujuan Investasi");
        validateExist("REXA_GOAL_INV_BELI_PAKET_REKOMENDASI_BTN", 5);
        validateExist("REXA_GOAL_INV_GOAL_NAME");
        validateExist("REXA_GOAL_INV_CURRENT_INVEST");
    }

    public void deleteAllGoalInvestment() {
        while(!isElementExist("REXA_GOAL_INV_EMPTY_PAGE")) {
            try {
                tapGoalInvestCard();
                verifyDashboardInvestPageDisplayed();
                tapGoalInvestSettingsBtn();
                verifySettingOptionDisplayed();
                tapGoalInvestSettingsOption("hapus");
                tapOnHapusTujuanBtn();
                verifyDeleteSnackbarDisplayed();
            } catch (Exception e) {
                break;
            }
        }
    }

    public void tapTujuanLainIcon() {
        tapElement("REXA_GOAL_INVESTMENT_TUJUAN_LAIN_BTN");
        InvestmentData.setUserHaveGoalInvestProduct(false);
    }

    public void verifyTujuanInvestasiPageEmpty() {
        validateExist("REXA_GOAL_INV_EMPTY_PAGE", 3);
    }

    public void validateGoalInvestmentIsCreated() {
        validateWebViewPageTitle("Tujuan Investasi");
        validateValue().equals(InvestmentData.getGoalInvestName(), getGoalInvestNameText(0), "Goal Investment baru tidak ditemukan");
    }

    public void verifyApaTujuanInvestasiKamuDisplayed() {
        if(InvestmentData.getUserHaveGoalInvestProduct()) {
            validateExist("REXA_GOAL_INV_CHOOSE_GOAL_ITEM", 5);
            validateExist("REXA_GOAL_INV_CHOOSE_GOAL_ICON");
            validateWebViewPageTitle("Apa tujuan investasi kamu?");
        } else {
            validateExist("REXA_GOAL_INV_CHOOSE_GOAL_NATIVE_ITEM");
            validateExist("REXA_GOAL_INV_CHOOSE_GOAL_NATIVE_ICON");
            validateExist("REXA_GOAL_INV_APA_TUJUAN_HEADER");
        }
    }

    public void verifyAturTujuanInvestasiPageDisplayed() {
        validateExist("REXA_GOAL_INV_TARGET_TOTAL_TEXT_FIELD",3);
        getText("REXA_GOAL_INV_GOAL_NAME_HEADER").equals(InvestmentData.getGoalInvestName());
        validateExist("REXA_GOAL_INV_TARGET_TIME_FIELD");
    }

    public void chooseGoalInvestmentType() {
        int randomGoalInvestType = randomize().number(getTotalGoalInvestType());
        InvestmentData.setGoalInvestName(getGoalInvestTypeText(randomGoalInvestType));
        if(InvestmentData.getUserHaveGoalInvestProduct()){
            webViewTapOnElement("REXA_GOAL_INV_CHOOSE_GOAL_ITEM", randomGoalInvestType);
        } else {
            tapElements("REXA_GOAL_INV_CHOOSE_GOAL_NATIVE_ITEM", randomGoalInvestType);
            changeContext().toWebview();
        }
    }

    public void verifyProcessingUserErrorMessageDisplayed() {
        validateExist("REXA_GOAL_INV_SNACKBAR", 3);
        validateValue().equals("Pendaftaran kamu sedang diproses.", getSnackbarText(), "Invalid message");
    }

    public void verifySuccessCreateGoalInvestmentMessageDisplayed() {
        validateExist("REXA_GOAL_INV_SNACKBAR", 3);
        validateValue().equals("Berhasil membuat Tujuan Investasi kamu!", getSnackbarText(), "Invalid message");
    }

    public void verifyMulaiInvestasiPageDisplayed() {
        //wait for webview fully displayed
        waitFor(10);
        validateExist("REXA_MULAI_INV_DESC");
        validateExist("REXA_MULAI_INV_PACKAGE_OPTION");
        validateExist("REXA_MULAI_INV_PIE_CHART");
        validateExist("REXA_MULAI_INV_BELI_PAKET_BUTTON");
        validateWebViewPageTitle("Mulai Investasi");
        tapChevron();
        validateExist("REXA_MULAI_INV_LAMA_INVESTASI_LABEL");
        validateExist("REXA_MULAI_INV_PER_MONTH_LABEL");
        validateExist("REXA_MULAI_INV_DEPOSITO_LABEL");
        validateExist("REXA_MULAI_INV_GRAFIK_SIMULASI");
        validateExist("REXA_MULAI_INV_GRAFIK_LEGEND");
        InvestmentData.setGoalInvestMinimumMonthlyAmount(getMinimumMonthlyAmount());
        HelperData.setLastActionPage(new BukaReksaGoalInvestmentPage(iosDriver));
    }

    public void verifyBWRModalDisplayed() {
        validateExist("REXA_MULAI_INV_BWR_POPUP",5);
        validateExist("REXA_MULAI_INV_BWR_POPUP");
        validateExist("REXA_MULAI_INV_BWR_TITLE");
        validateExist("REXA_MULAI_INV_BWR_DAFTAR_BTN");
        HelperData.setLastActionPage(new BukaReksaGoalInvestmentPage(iosDriver));
    }

    public void verifyBeliPaketRekomendasiModalDisplayed() {
        validateExist("REXA_GOAL_INV_BELI_PAKET_MODAL_TITLE",5);
        validateExist("REXA_GOAL_INV_BELI_PAKET_MODAL_CLOSE_BTN");
        validateExist("REXA_GOAL_INV_TOTAL_AMOUNT_TEXT_FIELD");
        validateExist("REXA_GOAL_INV_BELI_PAKET_MODAL_DESC");
    }

    public void inputMonthlyAmount(String monthlyAmountState){
        String monthlyAmountTextField = "REXA_GOAL_INV_TOTAL_AMOUNT_TEXT_FIELD";
        tapElement(monthlyAmountTextField, 3);
        backSpaceTextField(monthlyAmountTextField);
        switch (monthlyAmountState.toLowerCase()) {
            case "below":
                int monthlyAmountInt = getIntFromRp(InvestmentData.getGoalInvestMinimumMonthlyAmount())/2;
                typeValue(monthlyAmountTextField, String.valueOf(monthlyAmountInt));
                break;
            case "above":
                typeValue(monthlyAmountTextField, TestInstrument.dotenv.get("GOAL_INVESTMENT_MAX_AMOUNT"));
                break;
            case "valid":
                int nominal = getIntFromRp(InvestmentData.getGoalInvestMinimumMonthlyAmount())+10000;
                typeValue(monthlyAmountTextField, String.valueOf(nominal));
                InvestmentData.setNominalPurchase(String.valueOf(nominal));
                break;
            default:
                Assert.fail(monthlyAmountState + " isn't an option!");
        }
    }

    public void verifyTotalGoalsPurchaseInfo() {
        validateExist("REXA_GOAL_INV_TOTAL_PURCHASE",3);
    }

    public void tapOnGoalsTncChekbox() {
        tapElement("REXA_GOAL_INV_TNC_CHECKBOX");
    }

    public void tapOnLanjutkanPembayaranButton() {
        tapElement("REXA_GOAL_INV_LANJUTKAN_PEMBAYARAN");
        changeContext().toNative();
    }

    public void inputTargetAmount(String targetAmount) {
        verifyElementExist("REXA_GOAL_INV_TARGET_TOTAL_TEXT_FIELD");
        typeValue("REXA_GOAL_INV_TARGET_TOTAL_TEXT_FIELD", targetAmount);
    }

    public void chooseTargetTime() {
        // need delay to wait modal fully opened
        delay(1000);
        tapElement("REXA_GOAL_INV_TARGET_TIME_FIELD", 5);
        tapElement("REXA_GOAL_INV_SIMPAN_TARGET_BTN", 5);
    }

    public void verifyInvestPerMonthSuggestion() {
        validateExist("REXA_GOAL_INV_SUGGESTION_AMOUNT_TEXTFIELD",3);
        validateExist("REXA_GOAL_INV_SUGGESTION_AMOUNT_DESC",3);
    }

    public void tapLanjutLihatSimulasiButton() {
        tapElement("REXA_GOAL_INV_LANJUT_LIHAT_SIMULASI_BTN");
    }

    public void verifyGoalInvestmentNominalErrorDisplayed(String minMax) {
        switch(minMax.toLowerCase()) {
            case "minimum":
                validateValue().contains(". Yuk, tambahin jumlahnya!", getErrorText("REXA_GOAL_INV_BELI_PAKET_ERROR"), "Invalid message");
                break;
            case "maximum":
                validateValue().equals("Maks. Rp2.000.000.000.", getErrorText("REXA_GOAL_INV_BELI_PAKET_ERROR"), "Invalid message");
                break;
            default:
                Assert.fail(minMax + " isn't an option!");
        }
    }

    private void tapChevron() {
        webViewTapOnElement("REXA_MULAI_INV_CHEVRON");
    }

    private String getErrorText(String element) {
        return getText(element);
    }

    private int getTotalGoalInvestList() {
        return getElementsPresent("REXA_GOAL_INV_CARD").size();
    }

    private int getTotalGoalInvestType() {
        int totalGoalType;
        if(InvestmentData.getUserHaveGoalInvestProduct()) {
            totalGoalType = getElementsPresent("REXA_GOAL_INV_CHOOSE_GOAL_ITEM").size();
        } else {
            totalGoalType = getElementsPresent("REXA_GOAL_INV_CHOOSE_GOAL_NATIVE_ITEM").size();
        }
        return totalGoalType;
    }

    private String getMinimumMonthlyAmount() {
        return getText("REXA_MULAI_INV_PER_MONTH_LABEL");
    }

    private String getGoalInvestmentDashboardMonthlyAmount() {
        return getText("REXA_GOAL_INV_AMOUNT_ADVICE_VALUE");
    }

    private String getGoalInvestNameText(int index) {
        return getText("REXA_GOAL_INV_GOAL_NAME", index);
    }

    private String getGoalInvestTypeText(int index) {
        String goalInvestTypeText;
        if(InvestmentData.getUserHaveGoalInvestProduct()) {
            goalInvestTypeText = getText("REXA_GOAL_INV_CHOOSE_GOAL_ITEM", index);
        } else {
            goalInvestTypeText = getText("REXA_GOAL_INV_CHOOSE_GOAL_NATIVE_ITEM", index);
        }
        return goalInvestTypeText;
    }

    public void tapGoalInvestCard() {
        int randomGoalInvestList = randomize().number(getTotalGoalInvestList());
        InvestmentData.setGoalInvestName(getGoalInvestNameText(randomGoalInvestList));
        webViewTapOnElement("REXA_GOAL_INV_CARD", randomGoalInvestList);
    }

    public void verifyDashboardInvestPageDisplayed() {
        validateExist("REXA_GOAL_INV_TARGET_AMOUNT", 10);
        validateExist("REXA_GOAL_INV_CURRENT_AMOUNT");
        validateExist("REXA_GOAL_INV_LIST_TRX_BTN");
        validateExist("REXA_GOAL_INV_SETTINGS_BTN");
        webViewTapOnElement("REXA_GOAL_INV_CURRENT_AMOUNT");
        verifyGoalInvestDetailDisplayed();
        InvestmentData.setGoalInvestMinimumMonthlyAmount(getGoalInvestmentDashboardMonthlyAmount());
        validateWebViewPageTitle(InvestmentData.getGoalInvestName());
    }

    public void tapGoalInvestSettingsBtn() {
        tapElement("REXA_GOAL_INV_SETTINGS_BTN");
    }

    public void tapHistoryTransactionBtn() {
        tapElement("REXA_GOAL_INV_LIST_TRX_BTN");
    }

    public void verifySettingOptionDisplayed() {
        validateExist("REXA_GOAL_INV_SETTINGS_MODAL", 5);
        validateExist("REXA_GOAL_INV_CHANGE_GOAL_NAME_BTN");
        validateExist("REXA_GOAL_INV_DELETE_GOAL_BTN");
    }

    public void tapGoalInvestSettingsOption(String button) {
        switch (button.toLowerCase()) {
            case "ubah nama":
                webViewTapOnElement("REXA_GOAL_INV_CHANGE_GOAL_NAME_BTN");
                InvestmentData.setUpdateNameFromAturTujuanForm(false);
                break;
            case "hapus":
                webViewTapOnElement("REXA_GOAL_INV_DELETE_GOAL_BTN");
                break;
            default:
                Assert.fail(button + "isn't an option!");
        }
    }

    public void verifyDeleteConfirmationDisplayed() {
        validateExist("REXA_GOAL_INV_DELETE_MODAL_CONFIRMATION");
        validateExist("REXA_GOAL_INV_HAPUS_TUJUAN_BTN");
        validateExist("REXA_GOAL_INV_BATAL_BTN");
    }

    public void tapOnHapusTujuanBtn() {
        webViewTapOnElement("REXA_GOAL_INV_HAPUS_TUJUAN_BTN");
    }

    private String getSnackbarText() {
        return getText("REXA_GOAL_INV_SNACKBAR");
    }

    public void verifyDeleteSnackbarDisplayed() {
        validateExist("REXA_GOAL_INV_SNACKBAR", 3);
        validateValue().equals("Tujuan investasi sudah dihapus", getSnackbarText(), "Invalid message");
    }

    public void tapOnGoalInvestDetail() {
        webViewTapOnElement("REXA_GOAL_INV_CURRENT_AMOUNT");
    }

    public void verifyGoalInvestDetailDisplayed() {
        validateExist("REXA_GOAL_INV_GRAPH", 10);
        validateExist("REXA_GOAL_INV_GOAL_GROWTH");
        validateExist("REXA_GOAL_INV_AMOUNT_ADVICE");
        validateExist("REXA_GOAL_INV_TOTAL_INVEST");
    }

    public void verifyGoalInvestTrxListDisplayed() {
        if (isElementExist("REXA_GOAL_INV_TRX_PAGE", 10)) {
            verifyElementExist("REXA_GOAL_INV_TRX_TYPE");
            verifyElementExist("REXA_GOAL_INV_TRX_PRODUCT_NAME");
            verifyElementExist("REXA_GOAL_INV_TRX_AMOUNT");
            verifyElementExist("REXA_GOAL_INV_TRX_STATE");
        } else {
            verifyElementExist("REXA_GOAL_INV_TRX_EMPTY");
            LogUtil.info("Investor don't have transaction!");
        }
    }

    public void verifyNamaTujuanInvestasiModalDisplayed() {
        validateExist("REXA_GOAL_INV_NAMA_TUJUAN_INVESTASI_MODAL", 5);
        validateExist("REXA_GOAL_INV_NAME_FIELD");
        validateExist("REXA_GOAL_INV_SAVE_NAME_BTN");
    }

    public void tapGoalInvestName() {
        tapElement("REXA_GOAL_INV_GOAL_NAME_HEADER");
        InvestmentData.setUpdateNameFromAturTujuanForm(true);
    }

    public void inputNamaTujuanInvestasi(String goalName) {
        tapElement("REXA_GOAL_INV_NAME_FIELD");
        backSpaceTextField("REXA_GOAL_INV_NAME_FIELD");
        typeValue("REXA_GOAL_INV_NAME_FIELD", goalName);
        InvestmentData.setGoalInvestName(goalName);
    }

    public void tapSimpanNamaInvestasiBtn() {
        webViewTapOnElement("REXA_GOAL_INV_SAVE_NAME_BTN");
    }

    public void verifyGoalInvestNameUpdated() {
        delay(1000); // waiting page loaded
        if(InvestmentData.getUpdateNameFromAturTujuanForm()){
            getText("REXA_GOAL_INV_GOAL_NAME_HEADER").equals(InvestmentData.getGoalInvestName());
        } else {
            validateWebViewPageTitle(InvestmentData.getGoalInvestName());
        }
    }

    // end region

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
