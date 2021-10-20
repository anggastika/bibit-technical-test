package bukalapak.pageObject.investment;

import bukalapak.data.*;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import java.util.*;
import static bukalapak.TestInstrument.dotenv;

public class BukaEmasPage extends BasePage {

    private List<String> trxFilterTypes = Arrays.asList(
            "Pembelian",
            "Penjualan",
            "Bonus",
            "Kasih Emas",
            "Tebar Emas"
    );

    private List<String> trxFilterStates = Arrays.asList(
            "Menunggu Pembayaran",
            "Selesai",
            "Dibatalkan",
            "Kedaluwarsa"
    );

    public BukaEmasPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    private void validateWebViewPageTitle(String expectedValue) {
        validateExist("BE_WEBVIEW_HEADER", 10);
        validateValue().equals(expectedValue, getText("BE_WEBVIEW_HEADER"));
    }

    private void validateAccountSummarySection() {
        validateExist("BE_TOTAL_INVEST", 10);
        if (!isElementExist("BE_TOTAL_PROFIT", 10)) {
            validateExist("BE_TOTAL_UNIT", 10);
        }
        validateExist("BE_SUMMARY_TOGGLE", 5);
    }

    private void validateSharingSection() {
        try {
            if (!isElementExist("BE_TRANSFER_GOLD")) {
                webViewSwipeToElement("BE_TRANSFER_GOLD");
            }
            validateExist("BE_TRANSFER_GOLD", 10);
            validateExist("BE_REQUEST_GOLD", 5);
            validateExist("BE_SEND_GOLD", 5);
        } catch (Exception e) {
            LogUtil.info("P2P feature disabled");
        }
    }

    public void verifyWebViewDisplayed() {
        delay(1000); // need delay to waiting bukaemas page fully displayed
        changeContext().toWebview();
        checkKasihEmasPopUp();
        checkGoldForecastOnboarding();
        validateAccountSummarySection();
        validateWebViewPageTitle("BukaEmas");
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void verifyBuyPriceChart() {
        webViewSwipeToElement("BE_CURRENT_BUY_PRICE");
        validateExist("BE_CURRENT_BUY_PRICE",5);
        validateExist("BE_PRICE_CHART", 5);
    }

    public void verifySalePriceChart() {
        webViewTapOnElement("BE_SALE_CHART_TAB");
        validateExist("BE_CURRENT_SALE_PRICE",3);
        validateExist("BE_PRICE_CHART", 5);
    }

    private void checkKasihEmasPopUp() {
        if (isElementExist("BE_KASIH_EMAS_RECEIVE_POPUP_TITLE", 5)) {
            tapElement("BE_KASIH_EMAS_RECEIVE_POPUP_CLOSE_BTN");
        }
    }

    public void validateBannerSectionDisplayed() {
        validateExist("BE_BANNER_PROMO_CONTENT",5);
    }

    public void tapBannerPromo() {
        tapElement("BE_BANNER_PROMO", 3);
    }

    public void validatePromoDetail() {
        if (iosDriver.getContext().contains("NATIVE")) {
            changeContext().toNative();
            validateExist("BE_NATIVE_PROMO_DETAIL", 10);
        } else {
            validateExist("BE_PROMO_DETAIL", 10);
        }
    }

    // region Gold Forecast

    private void checkGoldForecastOnboarding() {
        if (isElementExist("BE_GOLD_FORECAST_ONBOARDING", 5)) {
            doGoldForecastOnboarding();
            webViewSwipeToElement("BE_TOTAL_INVEST");
        }
    }

    private void doGoldForecastOnboarding() {
        validateGoldForecastOnboardDisplayed("first step");
        tapElement("BE_GOLD_FORECAST_ONBOARDING_NEXT_BTN");
        validateGoldForecastOnboardDisplayed("second step");
        tapElement("BE_GOLD_FORECAST_ONBOARDING_FINISH_BTN");
    }

    private void validateGoldForecastOnboardDisplayed(String onboardingStep) {
        if(onboardingStep.equals("first step")) {
            validateForecastFirstOnboardingStep();
        } else if (onboardingStep.equals("second step")) {
            validateForecastSecondOnboardingStep();
        } else {
            Assert.fail("Invalid forecast onboarding step!");
        }
    }

    private void validateForecastFirstOnboardingStep() {
        validateExist("BE_GOLD_FORECAST_ONBOARDING", 3);
        validateExist("BE_GOLD_FORECAST_ONBOARDING_CONTENT");
        validateExist("BE_GOLD_FORECAST_ONBOARDING_NEXT_BTN");
    }

    private void validateForecastSecondOnboardingStep() {
        validateExist("BE_GOLD_FORECAST_ONBOARDING", 3);
        validateExist("BE_GOLD_FORECAST_ONBOARDING_CONTENT");
        validateExist("BE_GOLD_FORECAST_ONBOARDING_BACK_BTN");
        validateExist("BE_GOLD_FORECAST_ONBOARDING_FINISH_BTN");
    }

    public void validateGoldForecastSectionDisplayed() {
        if (isElementExist("BE_GOLD_FORECAST_SECTION", 5)) {
            validateExist("BE_GOLD_FORECAST_SECTION_HEADER");
            validateExist("BE_GOLD_FORECAST_SECTION_ICON");
            validateExist("BE_GOLD_FORECAST_SECTION_PREDICTION");
            validateExist("BE_GOLD_FORECAST_SECTION_INFO");
            validateExist("BE_GOLD_FORECAST_SECTION_CHEVRON");
            InvestmentData.setGoldForecastDatePrediction(getGoldForecastDatePrediction());
            InvestmentData.setGoldForecastPricePrediction(getGoldForecastPricePrediction());
        } else {
            Assert.fail("Gold Forecast Section not displayed");
        }
    }

    private String getGoldForecastDatePrediction() {
        return getText("BE_GOLD_FORECAST_SECTION_HEADER");
    }

    private String getGoldForecastPricePrediction() {
        return getText("BE_GOLD_FORECAST_SECTION_PREDICTION");
    }

    public void tapGoldForecastSection() {
        tapElement("BE_GOLD_FORECAST_SECTION_CHEVRON");
    }

    public void verifyGoldForecastPage() {
        String forecastDate = InvestmentData.getGoldForecastDatePrediction();
        String forecastPrice = InvestmentData.getGoldForecastPricePrediction();

        //delay for waiting page to render
        delay(3000);
        validateGoldForecastSummarySection();
        validateValue().contains(forecastDate, getGoldForecastSummaryDate());
        validateValue().contains(forecastPrice, getGoldForecastSummaryPrediction());
        validateGoldForecastPageInfo();
        validateWebViewPageTitle("Prediksi Emas");
    }

    private String getGoldForecastSummaryDate() {
        return getText("BE_GOLD_FORECAST_PAGE_HEADER_TITLE");
    }

    private String getGoldForecastSummaryPrediction() {
        return getText("BE_GOLD_FORECAST_PAGE_TITLE");
    }

    private void validateGoldForecastSummarySection() {
        validateExist("BE_GOLD_FORECAST_PAGE_DESCRIPTION", 5);
        validateExist("BE_GOLD_FORECAST_PAGE_PREDICTION_TITLE");
        validateExist("BE_GOLD_FORECAST_PAGE_PREDICTION_ITEM");
        validateExist("BE_GOLD_FORECAST_PAGE_PURCHASE_BTN");
        validateExist("BE_GOLD_FORECAST_PAGE_REDEEM_BTN");
        checkForecastMarketInfo();
        validateExist("BE_GOLD_FORECAST_PAGE_HISTORY");
    }

    private void checkForecastMarketInfo() {
        if (isElementExist("BE_GOLD_FORECAST_PAGE_MARKET_INFO")) {
            validateExist("BE_GOLD_FORECAST_PAGE_MARKET_INFO_ITEM");
        }
    }

    private void validateGoldForecastPageInfo() {
        validateExist("BE_GOLD_FORECAST_PAGE_INFO");
        webViewTapOnElement("BE_GOLD_FORECAST_PAGE_INFO_CHEVRON");
        validateExist("BE_GOLD_FORECAST_PAGE_INFO_CONTENT", 2);
    }

    public void tapGoldPredictionPageTransactionBtn(String button) {
        if (button.equalsIgnoreCase("beli")) {
            tapGoldPredictionPagePurchaseBtn();
        } else if (button.equalsIgnoreCase("jual")) {
            tapGoldPredictionPageRedeemBtn();
        } else {
            Assert.fail("Invalid Bukaemas transaction button!");
        }
    }

    public void tapGoldPredictionPagePurchaseBtn() {
        tapElement("BE_GOLD_FORECAST_PAGE_PURCHASE_BTN");
    }

    public void tapGoldPredictionPageRedeemBtn() {
        tapElement("BE_GOLD_FORECAST_PAGE_REDEEM_BTN");
    }

    public void tapGoldPredictionPageHistoryBtn() {
        tapElement("BE_GOLD_FORECAST_PAGE_HISTORY");
    }

    public void tapOnTrxPageGoldForecastPrediction() {
        tapElement("BE_TRX_FORECAST_PREDICTION");
    }

    public void verifyGoldPredictionHistoryPage() {
        validateExist("BE_GOLD_FORECAST_HISTORY_PAGE_TITLE", 5);
        validateExist("BE_GOLD_FORECAST_HISTORY_PAGE_PREDICTION_TITLE");
        validateExist("BE_GOLD_FORECAST_HISTORY_PAGE_PREDICTION_ITEM");
        validateWebViewPageTitle("Prediksi hari lainnya");
    }

    public void tapGoldPredictionHistoryItem() {
        int predictionHistoryItem = randomize().number(getTotalGoldPredictionHistoryItem());
        InvestmentData.setGoldForecastDatePrediction(getGoldForecastHistoryDatePrediction(predictionHistoryItem));
        InvestmentData.setGoldForecastPricePrediction(getGoldForecastHistoryPricePrediction(predictionHistoryItem));
        webViewTapOnElement("BE_GOLD_FORECAST_HISTORY_PAGE_PREDICTION_ITEM_CONTAINER", predictionHistoryItem);
    }

    private int getTotalGoldPredictionHistoryItem() {
        return getElementsPresent("BE_GOLD_FORECAST_HISTORY_PAGE_PREDICTION_ITEM").size();
    }

    private String getGoldForecastHistoryDatePrediction(int item) {
        return getText("BE_GOLD_FORECAST_HISTORY_PAGE_ITEM_DATE", item);
    }

    private String getGoldForecastHistoryPricePrediction(int item) {
        return getText("BE_GOLD_FORECAST_HISTORY_PAGE_ITEM_PRICE", item);
    }

    public void verifyGoldPredictionHistoryPopup() {
        String forecastDate = InvestmentData.getGoldForecastDatePrediction();
        String forecastPrice = InvestmentData.getGoldForecastPricePrediction();

        validateExist("BE_GOLD_FORECAST_HISTORY_POPUP_TITLE", 5);
        validateValue().contains(forecastDate, getGoldForecastHistoryPopupDatePrediction());
        validateValue().contains(forecastPrice, getGoldForecastHistoryPopupPricePrediction());
        validateExist("BE_GOLD_FORECAST_HISTORY_POPUP_DESCRIPTION");
        validateExist("BE_GOLD_FORECAST_HISTORY_POPUP_PREDICTION_TITLE");
        validateExist("BE_GOLD_FORECAST_HISTORY_POPUP_PREDICTION_ITEM");
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private String getGoldForecastHistoryPopupDatePrediction() {
        return getText("BE_GOLD_FORECAST_HISTORY_POPUP_DATE");
    }

    private String getGoldForecastHistoryPopupPricePrediction() {
        return getText("BE_GOLD_FORECAST_HISTORY_POPUP_PRICE");
    }

    // endregion Gold Forecast

    public void verifySeeTransactionBtn() {
        validateExist("BE_LIHAT_TRX_BTN", 5);
        validateValue().equals("Lihat transaksi", getText("BE_LIHAT_TRX_BTN"));
    }

    public void tapOnLihatTransaksiBtn() {
        tapElement("BE_LIHAT_TRX_BTN");
    }

    public void verifyTransactionListDisplayed() {
        //add delay to wait transactions to load
        delay(1500);
        validateExist("BE_TRX_LIST_SECTION", 5);
        validateExist("BE_TRX_LIST_TAG_OPTIONS", 5);
        validateExist("BE_FILTER_TRX_BTN", 5);
        validateWebViewPageTitle("Riwayat transaksi");
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private boolean isWebElementSelected(String locator) {
        return getElement(locator, 5)
                .getAttribute("className")
                .contains("is-active");
    }

    public void validateQuickFilterTagIsInAllTypes() {
        validateValue().equalsTrue(isWebElementSelected("BE_TRX_LIST_TAG_OPTION_SEMUA_STATE"));
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void selectQuickFilter(String option) {
        if (!isWebElementSelected(constructLocator("BE_TRX_LIST_TAG_OPTION_STATE", option))) {
            validateExist(constructLocator("BE_TRX_LIST_TAG_OPTION_STATE", option));
            tapElement(constructLocator("BE_TRX_LIST_TAG_OPTION_STATE", option));
        }
        InvestmentData.setTrxFilterType(option);
    }

    public void validateQuickFilterTagOptionIsSelected() {
        validateValue().equalsTrue(isWebElementSelected(constructLocator(
                "BE_TRX_LIST_TAG_OPTION_STATE", InvestmentData.getTrxFilterType())),
                "Tag option not activated!");
    }

    public void validateFilterCheckIcon() {
        validateExist("BE_TRX_LIST_CHECK_ICON");
    }

    public void validateFilterUnCheckIcon() {
        verifyElementNotExist("BE_TRX_LIST_CHECK_ICON");
    }

    private int getTotalTransactionList() {
        verifyElementExist("BE_TRX_LIST_CARD");
        return getElements("BE_TRX_LIST_CARD", 5).size();
    }

    public void tapOnFilterTransactionBtn() {
        tapElement("BE_TRX_LIST_FILTER_BTN");
    }

    public void tapOnResetFilterBtn() {
        tapElement("BE_TRX_FILTER_RESET_BTN");
    }

    public void validateTransactionFilterModalDisplayed() {
        validateExist("BE_TRX_FILTER_OVERLAY_MODAL", 5);
        validateExist("BE_TRX_FILTER_DATE_FIELD", 3);
        validateExist("BE_TRX_FILTER_TYPE_SECTION", 3);
        validateExist("BE_TRX_FILTER_STATE_SECTION", 3);
        validateElementWithText("BE_TRX_FILTER_OVERLAY_MODAL_TITLE", "Filter");
    }

    public void tapOnFilterApplyBtn() {
        tapElement("BE_TRX_FILTER_APPLY_BTN");
    }

    public void tapOnDateField() {
        tapElement("BE_TRX_FILTER_DATE_FIELD");
    }

    public void validateDatePickerDisplayed() {
        validateExist("BE_DATE_PICKER", 10);
        validateExist("BE_DATE_PICKER_MONTH_YEAR");
        validateExist("BE_DATE_PICKER_CALENDAR");
        validateExist("BE_DATE_PICKER_CHOOSE_BTN");
        validateElementWithText("BE_DATE_PICKER_OVERLAY_MODAL_TITLE", "Pilih Tanggal Transaksi");
    }

    public void selectAlchemyDatePicker(String date, String month, String year) throws Exception {
        InvestmentData.setFilterDate(date);
        InvestmentData.setFilterMonth(month);
        InvestmentData.setFilterYear(year);
    }

    private String getDatePickerValue() {
        return getElementAttributeValue("BE_TRX_FILTER_DATE_FIELD", "value");
    }

    private String getFormatSelectedDates() {
        String[] dateValue = {
                InvestmentData.getFilterDate(),
                InvestmentData.getFilterMonth(),
                InvestmentData.getFilterYear()
        };
        return String.join(" ", dateValue);
    }

    public void validateDateValueIsSelected() {
        String selectedDates = getFormatSelectedDates();
        InvestmentData.setFilterDateValue(selectedDates);
        // validate expected date value
        validateValue().equals(selectedDates, getDatePickerValue(), "Value of date picker doesn't match");
    }

    public void verifyTransactionCardList(int index) {
        // need to delay related to rendering the transaction list
        delay(1000);
        verifyElementsExist("BE_TRX_LIST_CARD", index, 3, "Transaction card list not exist");
    }

    private String getTransactionListTypeText(int index) {
        verifyTransactionCardList(index);
        return getText("BE_TRX_LIST_CARD_TITLE", index)
                .split("\\d+", 2)[0]
                .trim();
    }

    private String getTransactionListTotalGramText(int index) {
        String cardTitle = getText("BE_TRX_LIST_CARD_TITLE", index);
        return cardTitle.substring(cardTitle.split("\\d+", 2)[0].trim().length() + 1).trim();
    }

    private String getTransactionListTotalPriceText(int index) {
        verifyTransactionCardList(index);
        return getText("BE_TRX_LIST_CARD_AMOUNT", index);
    }

    private String getTransactionListStateText(int index) {
        verifyTransactionCardList(index);
        return getText("BE_TRX_LIST_CARD_STATE", index);
    }

    private String getOnlyNumbers(String text) {
        return text.replaceAll("[^\\d]", "");
    }

    public void tapOnTransactionList() {
        verifyElementExist("BE_TRX_LIST_CARD");
        int randomTrxList = randomize().number(getTotalTransactionList());
        // Set Data
        InvestmentData.setTrxListType(getTransactionListTypeText(randomTrxList));
        InvestmentData.setUnitPurchase(getTransactionListTotalGramText(randomTrxList));
        InvestmentData.setNominalPurchase(getTransactionListTotalPriceText(randomTrxList));
        InvestmentData.setTrxListStatus(getTransactionListStateText(randomTrxList));
        webViewTapOnElement("BE_TRX_LIST_CARD", randomTrxList);
    }

    private void validateTransactionFilterRadioBtn(String typeName) {
        validateExist(constructLocator("BE_TRX_FILTER_RADIO_BTN", typeName));
    }

    private void tapOnTransactionFilterRadioBtn(String typeName) {
        webViewTapOnElement(constructLocator("BE_TRX_FILTER_RADIO_BTN", typeName));
    }

    public void selectFilterTransactionType(String... type) {
        // Get selecting transaction state...
        String getRandomType = trxFilterTypes.get(randomize().number(trxFilterTypes.size()));
        String getTrxType = (type[0] != null) ? type[0].replaceAll("\"", "") : getRandomType;
        InvestmentData.setTrxFilterType(getTrxType);
        InvestmentData.setTrxListType(getTrxType);
        // Select filter transaction type
        validateTransactionFilterRadioBtn(getTrxType);
        tapOnTransactionFilterRadioBtn(getTrxType);
    }

    public void verifyRadioBtnCheckedAfterSelection() {
        validateChecked(
                constructLocator(
                        "BE_TRX_FILTER_RADIO_BTN_STATE", InvestmentData.getTrxFilterType()
                ), InvestmentData.getTrxFilterType() + " radio button not checked after selection"
        );
    }

    private void validateTransactionFilterCheckbox(String stateName) {
        validateExist(constructLocator("BE_TRX_FILTER_CHECKBOX", stateName));
    }

    private void tapOnTransactionFilterCheckBox(String stateName) {
        webViewTapOnElement(constructLocator("BE_TRX_FILTER_CHECKBOX", stateName));
    }

    public void selectFilterTransactionState(String... state) {
        // Get selecting transaction state...
        String getRandomType = trxFilterStates.get(randomize().number(trxFilterStates.size()));
        String getTrxState = (state[0] != null) ? state[0].replaceAll("\"", "") : getRandomType;
        InvestmentData.setTrxFilterState(getTrxState);
        // Select transaction state...
        validateTransactionFilterCheckbox(getTrxState);
        tapOnTransactionFilterCheckBox(getTrxState);
    }

    public void verifyCheckBoxCheckedAfterSelection() {
        validateChecked(
                constructLocator(
                        "BE_TRX_FILTER_CHECKBOX_STATE", InvestmentData.getTrxFilterState()
                ), InvestmentData.getTrxFilterState() + " checkbox not checked after selection"
        );
    }

    public void validateTransactionListFilterByType() {
        for (int i=0; i < getTotalTransactionList(); i++) {
            assertTrue(getTransactionListTypeText(i)
                            .contains(InvestmentData.getTrxFilterType()),
                    "Transaction list type index " + i + " have not contains value");
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void validateTransactionListFilterByState() {
        for (int i=0; i < getTotalTransactionList(); i++) {
            assertEquals(
                    InvestmentData.getTrxFilterState(),
                    getTransactionListStateText(i),
                    "Transaction list state index " + i + " have doesn't match value"
            );
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void validateTransactionListFilterCombination() {
        for (int i=0; i < getTotalTransactionList(); i++) {
            // validate by type
            assertEquals(
                    InvestmentData.getTrxFilterType(),
                    getTransactionListTypeText(i),
                    "Transaction list type index " + i + " have doesn't match value"
            );
            // validate by state
            assertEquals(
                    InvestmentData.getTrxFilterState(),
                    getTransactionListStateText(i),
                    "Transaction list state index " + i + " have doesn't match value"
            );
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private String getTrxListTypeLowerCase() {
        return InvestmentData.getTrxListType().toLowerCase();
    }

    private String getTrxDetailElementTranslate() {
        String trxTypeTranslate = null;
        switch (getTrxListTypeLowerCase()) {
            case "pembelian":
                trxTypeTranslate = "purchase";
                break;
            case "penjualan":
                trxTypeTranslate = "redeem";
                break;
            case "bonus":
                trxTypeTranslate = "promo";
                break;
            case "bonus tebar emas":
            case "tebar emas":
                trxTypeTranslate = "transfer-receiver";
                break;
            case "kasih emas":
                trxTypeTranslate = "transfer-sender";
                break;
            default:
                Assert.fail("Invalid transaction type");
                break;
        }
        return trxTypeTranslate;
    }

    public void tapBackBtnWebview() {
        validateExist("BE_BACK_BTN", 5);
        webViewTapOnElement("BE_BACK_BTN");
    }

    public void tapBeliLagiBtn() {
        webViewTapOnElement("BE_BELI_LAGI_BTN");
    }

    public void validatePaymentMethodPage() {
        changeContext().toNative();
        validateExist("BE_CHECKOUT_HEADER", 10);
    }

    private void selectPaymentMethodNative(String paymentMethod, String bankName) {
        validateExist(constructLocator("BE_PAYMENT_METHOD", paymentMethod),10);
        tapElement(constructLocator("BE_PAYMENT_METHOD", paymentMethod));
        if (bankName != null && bankName.trim().isEmpty()){
            tapElement(constructLocator("BE_BANK_NAME_VA", bankName));
        }
        if (InvestmentData.getProductTypeBukaEmas().equalsIgnoreCase("cicilan")) {
            swipeUpToElement("BE_TOTAL_PURCHASE_CICILEMAS_PAYMENT_METHOD_PAGE");
            validateExist("BE_TOTAL_PURCHASE_CICILEMAS_PAYMENT_METHOD_PAGE");
        } else {
            swipeUpToElement("BE_TOTAL_PURCHASE_PAYMENT_METHOD_PAGE");
            validateExist("BE_TOTAL_PURCHASE_PAYMENT_METHOD_PAGE");
        }
    }

    private void selectPaymentMethodWebview(String paymentMethod, String bankName) {
        webViewSwipeToElement(constructLocator("BE_PAYMENT_METHOD_UPDATE_PAYMENT", paymentMethod));
        webViewTapOnElement(constructLocator("BE_PAYMENT_METHOD_UPDATE_PAYMENT", paymentMethod));
        if (bankName != null){
            webViewTapOnElement(constructLocator("BE_BANK_NAME_VA_UPDATE_PAYMENT", bankName));
        }
        try {
            webViewSwipeToElement("BE_TOTAL_PURCHASE_PAYMENT_UPDATE_PAYMENT");
            validateExist("BE_TOTAL_PURCHASE_PAYMENT_UPDATE_PAYMENT");
        } catch (Exception e) {
            webViewSwipeToElement("BE_TOTAL_PURCHASE_PAYMENT_UPDATE_PAYMENT_INSTALLMENT");
            validateExist("BE_TOTAL_PURCHASE_PAYMENT_UPDATE_PAYMENT_INSTALLMENT");
        }
    }

    public void selectPaymentMethod(String paymentMethod, String bankName) {
        if (iosDriver.getContext().contains("NATIVE")){
            selectPaymentMethodNative(paymentMethod, bankName);
        } else if (iosDriver.getContext().contains("WEBVIEW")) {
            selectPaymentMethodWebview(paymentMethod, bankName);
        } else {
            Assert.fail("Context native or webview not found");
        }
        InvestmentData.setPaymentMethod(paymentMethod);
    }

    public void tapBayarBtn() {
        swipeUpToElement("BE_BAYAR_BTN");
        tapElement("BE_BAYAR_BTN");
    }

    public void tapBayarBtnUpdatePayment() {
        webViewSwipeToElement("BE_BAYAR_BTN_UPDATE_PAYMENT");
        webViewTapOnElement("BE_BAYAR_BTN_UPDATE_PAYMENT");
    }

    public void validateUpdatePaymentMethodPage() {
        validateExist("BE_PAYMENT_METHOD_TITLE_UPDATE_PAYMENT", 15);
        validateValue().equals("Pilih Metode Pembayaran", getText("BE_PAYMENT_METHOD_TITLE_UPDATE_PAYMENT"));
    }

    public void validateConfirmPaymentPage() {
        validateExist("BE_NOTIFICATION_CONFIRM_PAYMENT", 15);
        validateExist("BE_BATAS_PEMBAYARAN_CONFIRM_PAYMENT", 5);
        if (!isElementExist("BE_NO_TAGIHAN_CONFIRM_PAYMENT", 10)) {
            validateExist("BE_NO_PESANAN_CONFIRM_PAYMENT", 5);
        }
        InvestmentData.setTotalNilaiOrder(getTotalAmountConfirmPaymentPage());
    }

    public void validateInvoicePage() {
        changeContext().toNative();
        validateExist("BE_JUMLAH_TAGIHAN_INVOICE", 10);
        validateExist("BE_BATAS_PEMBAYARAN_INVOICE");
        validateExist("BE_BANK_TRANSFER_LIST_INVOICE", 5);
        validateValue().equalsTrue(!getNomorTagihanInvoice().isEmpty());
    }

    private String getTotalAmountConfirmPaymentPage() {
        return getText("BE_AMOUNT_CONFIRM_PAYMENT").replaceAll("[^\\d]", "");
    }

    private String getNomorTagihanInvoice() {
        swipeUpToElement("BE_NO_TAGIHAN_INVOICE");
        return getText("BE_NO_TAGIHAN_INVOICE");
    }

    public void tapLihatDetailPesananBtn() {
        webViewSwipeToElement("BE_LIHAT_DETAIL_PESANAN_BTN");
        tapElement("BE_LIHAT_DETAIL_PESANAN_BTN");
    }

    public void tapDetailsTrxBtn() {
        tapElement("BE_LIHAT_DETAIL_TRX_BTN");
    }

    public void tapUbahPembayaranBtn() {
        waitFor(3);
        tapElement("BE_UBAH_BTN");
    }

    public void tapSemuaTentangBukaEmas() {
        webViewTapOnElement("BE_SEMUA_TENTANG_BE");
    }

    public void validateSemuaTentangBukaEmasPage() {
        validateExist("BE_QA_SECTION", 5);
        validateExist("BE_JUAL_BELI_SECTION");
        validateExist("BE_CICIL_SECTION");
        validateExist("BE_VIDEO_SECTION",5);
        validateExist("BE_ARTICLE_SECTION",5);
    }

    public void tapFirstArticle() {
        webViewTapOnElement("BE_FIRST_ARTICLE");
    }

    public void tapLihatArtikelLainnyaBtn(){
        webViewTapOnElement("BE_SERBA_SERBI_LIHAT_ARTIKEL_BTN");
        changeContext().toNative();
    }

    public void validateArticleDetail() {
        changeContext().toNative();
        validateExist("BE_ARTICLE_DETAIL", 5);
    }

    private void validateTrxDetailInformationTitle() {
        String getLocator = constructLocator("BE_TRX_DETAIL_INFORMATION_TEXT", getTrxDetailElementTranslate());
        validateExist(getLocator, "Transaction Detail Information type section not found!");
        validateValue().equalsTrue(getText(getLocator).equals("Informasi " + InvestmentData.getTrxListType().toLowerCase()));
    }

    private void validateTrxDetailState() {
        String getLocator = constructLocator("BE_TRX_DETAIL_STATE", getTrxDetailElementTranslate());
        validateExist("BE_TRX_DETAIL_STATE", 20);
        validateValue().equals(InvestmentData.getTrxFilterState(), getText(getLocator), "Transaction detail state doesn't match!");
    }

    public void validateTransactionDetail() {
        String expectListType = "Detail " + getTrxListTypeLowerCase();
        validateExist("BE_TRX_DETAIL_INVOICE_ID", 30);
        validateTrxDetailInformationTitle();
        validateTrxDetailState();
        validateWebViewPageTitle(expectListType);
    }

    public void validateTransactionDetailFromFilter() {
        String expectListType = "Detail " + getTrxFilterTypeLowerCase();
        validateExist("BE_TRX_DETAIL_INVOICE_ID", 30);
        validateTrxDetailInformationTitle();
        validateTrxDetailState();
        validateWebViewPageTitle(expectListType);
    }

    public void validateTrxDetailFromRedeem(){
        changeContext().toWebview();
        validateExist("BE_TRX_DETAIL_INVOICE_ID_REDEEM", 30);
        validateExist("BE_TRX_DETAIL_INFORMATION_TEXT");
        validateExist("BE_TRX_DETAIL_STATE");
        validateWebViewPageTitle("Detail penjualan");
        if (InvestmentData.getCrossSellingTransaction()) {
            verifyCrossSellingRedemptionDetails();
        }
    }

    private void verifyCrossSellingRedemptionDetails() {
        checkTransactionRemitted();
        validateExist("BE_TRX_DETAIL_INFORMATION_BUKAREKSA_TEXT", 10);
        validateExist("BE_TRX_DETAIL_LIHAT_DETAIL_TRANSAKSI_BUKAREKSA");
        InvestmentData.setNominalPurchase(getRemittedRedeemAmount());
        if (InvestmentData.getCrossSellingState().equalsIgnoreCase("bwr")){
            verifyBwrInfoDisplayed();
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private String getRemittedRedeemAmount() {
        return getText("BE_TRX_DETAIL_TOTAL_REMITTED_AMOUNT");
    }

    private void checkTransactionRemitted() {
        try {
            delay(5000); //wait for transaction to be remitted in background before refreshing
            refreshWebview();
            validateExist("BE_TRX_DETAIL_REMITTED_STATE", 5);
        } catch (AssertionError e) {
            delay(5000); //wait again in case transaction is not yet remitted
            refreshWebview();
            validateExist("BE_TRX_DETAIL_REMITTED_STATE", 5);
        }
    }

    private void verifyBwrInfoDisplayed() {
        validateExist("BE_CROSS_SELLING_BWR_INFO", 5);
        validateExist("BE_CROSS_SELLING_MULAI_DAFTAR_BTN");
    }

    private void validateTrxDetailFromBukaReksaPackage() {
        //already in webview context so if we call validateTrxDetailFormPurchase it will change context to webview again
        validateExist("BE_TRX_DETAIL_INVOICE_ID", 10);
        validateExist("BE_TRX_DETAIL_INFORMATION_TITLE");
        validateExist("BE_TRX_DETAIL_INFORMATION_TEXT");
        validateExist("BE_TRX_DETAIL_STATE");
        validateWebViewPageTitle("Detail pembelian");
        validateValue().equals(getOnlyNumbers(InvestmentData.getBukaEmasPackagePurchaseNominal()),
                getOnlyNumbers(getText("BE_TRX_DETAIL_TOTAL_AMOUNT")), "Purchase amount mismatched!");
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void validateTrxDetailFromPurchase(){
        changeContext().toWebview();
        validateExist("BE_TRX_DETAIL_INFORMATION_TITLE",10);
        validateExist("BE_TRX_DETAIL_INFORMATION_TEXT",10);
        validateExist("BE_TRX_DETAIL_STATE",5);
        validateExist("BE_TRX_DETAIL_INVOICE_ID", 10);
        InvestmentData.setNominalPurchase(getTotalPurchaseAmount());
        validateWebViewPageTitle("Detail pembelian");
    }

    private void validateTrxDetailFromInstallmentSubscription() {
        changeContext().toWebview();
        validateExist("BE_TRX_DETAIL_INVOICE_ID", 30);
        validateExist("BE_TRX_DETAIL_INFORMATION_TITLE");
        validateExist("BE_TRX_DETAIL_TOTAL_AMOUNT");
        validateExist("BE_TRX_DETAIL_INVOICE_STATE");
        validateExist("BE_TRX_DETAIL_INSTALLMENT_INFO_BTN");
        InvestmentData.setNominalPurchase(getTotalPurchaseAmount());
        validateWebViewPageTitle("Detail cicilan");
    }

    public void validateTransactionDetail(String page) {
        switch (page) {
            case "filter":
                validateTransactionDetailFromFilter();
                break;
            case "invoice":
                validateTrxDetailFromPurchase();
                break;
            case "redeem":
                validateTrxDetailFromRedeem();
                break;
            case "package":
                validateTrxDetailFromBukaReksaPackage();
                break;
            case "installment subscription":
                validateTrxDetailFromInstallmentSubscription();
                break;
            default:
                Assert.fail("Unknown source page");
        }
    }

    private String getInvoiceDate() {
        return getText("BE_TRX_DETAIL_INVOICE_DATE").split(",")[0];
    }

    public void validateTransactionInvoiceDate() {
        validateValue().equals(InvestmentData.getFilterDateValue(), getInvoiceDate(),
                "Transaction Detail invoice date doesn't match!");
    }

    public void tapOnBeliEmasBtn() {
        waitFor(3); //static wait to prevent race condition
        tapElement("BE_PURCHASE_BTN");
    }

    public void tapOnMinEmasPrice() {
        changeContext().toNative();
        tapElement("BE_PURCHASE_MIN_PRICE_ALT");
        changeContext().toWebview();
    }

    public void tapOnBeliEmasConfirmBtn() {
        tapElement("BE_PURCHASE_CONFIRM_BTN");
        changeContext().toNative();
    }

    public void tapBeliBtnOnEntryPoint() {
        webViewTapOnElement("BE_PURCHASE_BTN_ENTRY_POINT");
    }

    public void tapJualBtnOnEntryPoint() {
        webViewTapOnElement("BE_REDEEM_BTN_ENTRY_POINT");
    }

    private String getNominalTrxText() {
        return getText("BE_FIELD_LABEL");
    }

    public void validateBeliEmasPage() {
        validateExist("BE_UPDATE_DATE_TIME", 10);
        validateExist("BE_TODAY_PRICE");
        validateExist("BE_CHANGES_PRICE");
        validateExist("BE_TRX_FORM_SECTION");
        validateExist("BE_TERM_COND");
        validateValue().equals("Nominal pembelian", getNominalTrxText(), "Harga(Rp) is not default selected");
        validateTransactionPageGoldForecast();
        validateWebViewPageTitle("Beli emas");
    }

    public void validateJualEmasPage() {
        validateExist("BE_UPDATE_DATE_TIME", 10);
        validateExist("BE_TODAY_PRICE");
        validateExist("BE_CHANGES_PRICE");
        validateExist("BE_TRX_FORM_SECTION");
        validateExist("BE_TERM_COND");
        validateValue().equals("Nominal penjualan", getNominalTrxText(), "Harga(Rp) is not default selected");
        validateTransactionPageGoldForecast();
        validateWebViewPageTitle("Jual emas");
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private void validateTransactionPageGoldForecast() {
        if (InvestmentData.isFromGoldForecast()) {
            validateExist("BE_TRX_FORECAST_TITLE");
            verifyTrxPageGoldForecastPrediction();
        }
    }

    private void verifyTrxPageGoldForecastPrediction() {
        String homeForecast = InvestmentData.getGoldForecastPricePrediction().toLowerCase();
        String trxForecast = getText("BE_TRX_FORECAST_PREDICTION").toLowerCase();
        if (homeForecast.contains("bearish")) {
            validateValue().contains("turun", trxForecast);
        } else if (homeForecast.contains("bullish")) {
            validateValue().contains("naik", trxForecast);
        } else if (homeForecast.contains("sideways")) {
            validateValue().contains("stabil", trxForecast);
        } else {
            Assert.fail("Gold Forecast not valid!");
        }
    }

    public void validateActiveTab(String tab) {
        //need delay to handle redirection from cicilan list page
        if (tab.equals("Cicilan")) {
            delay(1000);
        }
        validateExist("BE_ACTIVE_TAB", 10);
        validateValue().equals(tab, getText("BE_ACTIVE_TAB"));
    }

    public void tapRadioBtn(String menu) {
        webViewTapOnElement(constructLocator("BE_MENU_RADIO_BTN", menu));
    }

    public void inputPurchaseAmount(String amount, String productType) {
        InvestmentData.setProductTypeBukaEmas(productType);
        if (productType.equalsIgnoreCase("cicilan")) {
            InvestmentData.setMonthlyInstallmentNominal(amount);
        } else {
            InvestmentData.setNominalPurchase(amount);
        }
        webViewTypeElementValue("BE_NOMINAL_FIELD", amount);
        //wait for installment calculation if cicilan
        if (productType.equalsIgnoreCase("cicilan")) {
            waitFor(2);
        }
    }

    public void inputPurchaseUnit(String unit,  String productType) {
        InvestmentData.setProductTypeBukaEmas(productType);
        if (productType.toLowerCase().equals("cicilan")) {
            InvestmentData.setInstallmentGoldWeight(unit);
        } else {
            InvestmentData.setUnitPurchase(unit);
        }
        getElementPresent("BE_UNIT_FIELD").clear();
        typeValue("BE_UNIT_FIELD", unit);
    }

    private String getErrorMessageText() {
        return getText("BE_ERROR_SNACKBAR");
    }

    public void validateInstallmentErrorMessage() {
        int unitFromInput = parseIntegerFromText(InvestmentData.getInstallmentGoldWeight());
        if (unitFromInput < 1) {
            validateExist("BE_ERROR_SNACKBAR", 3);
            validateValue().equals("Berat minimum 1 gram",getErrorMessageText(), "Invalid error message");
        } else if (unitFromInput > 100) {
            validateExist("BE_ERROR_SNACKBAR", 3);
            validateValue().equals("Mohon maaf, cicilan maksimum 100gram",getErrorMessageText(), "Invalid error message");
        }
    }


    public void validateTransactionValueError(String type) {
        validateExist("BE_INPUT_ERROR_TEXT", 2);
        String errorText = getText("BE_INPUT_ERROR_TEXT");
        switch (type.toLowerCase()) {
            case "maximum purchase":
                validateValue().contains("Maksimum pembelian", errorText);
                break;
            case "minimum purchase":
                validateValue().contains("Minimum pembelian", errorText);
                break;
            case "maximum redeem":
                validateValue().contains("Maksimum penjualan", errorText);
                break;
            case "minimum redeem":
                validateValue().contains("Minimum penjualan", errorText);
                break;
            default:
                Assert.fail("Invalid amount type!");
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void tapBeliBtnOnTrxPoint() {
        webViewTapOnElement("BE_PURCHASE_BTN_TRX_POINT");
    }

    private String getTrxFilterTypeLowerCase() {
        return InvestmentData.getTrxFilterType().toLowerCase();
    }

    public void verifyLatestTrasactionOnFirstRow() {
        validateExist("BE_PRICE_LATEST_TRX", 5);
        if (InvestmentData.getAutoInvestBuyerAmount() == null) {
            validateValue().equals(InvestmentData.getNominalPurchase(), getPriceLatestTrx());
        } else {
            validateValue().equals(InvestmentData.getAutoInvestBuyerAmount().replaceAll("[^\\d]", ""), getPriceLatestTrx());
        }
    }

    public void tapFirstPurchaseOnList() {
        webViewTapOnElement("BE_FIRST_PURCHASE_HISTORY_ON_LIST", 0);
    }

    private String getPriceLatestTrx() {
        return getText("BE_PRICE_LATEST_TRX", 0).replaceAll("[^\\d]", "");
    }

    public void inputRedeemAmount(String amount) {
        typeValue("BE_NOMINAL_FIELD", amount);
        InvestmentData.setNominalRedeem(amount);
    }

    public void inputRedeemUnit(String unit) {
        typeValue("BE_UNIT_FIELD", unit);
        InvestmentData.setUnitRedeem(unit);
    }

    public void tapJualBtnOnTrxPoint() {
        webViewTapOnElement("BE_REDEEM_BTN_TRX_POINT");
    }

    public void validateRedeemConfirmPopup() {
        validateExist("BE_CONFIRMATION_VALUE", 15);
        validateExist("BE_ESTIMATION_VALUE");
        validateExist("BE_AKUN_PENERIMA");
        if (InvestmentData.getCrossSellingTransaction()) {
            verifyBukaReksaSubscribeInfo();
        }
        validateValue().equals("Konfirmasi penempatan dana", getPopupTitle());
    }

    public void verifyDanaOptionDisabled() {
        validateNotExist("BE_DANA_OPTION", 5);
    }

    private void verifyBukaReksaSubscribeInfo() {
        validateValue().contains("Akun BukaReksa", getRedemptionReceiverAccount());
        validateValue().contains(UserData.getUsername(), getRedemptionReceiverAccount());
        validateValue().contains(InvestmentData.getProductNameBukaReksa(), getRedemptionConfirmationProduct());
        validateExist("BE_CROSS_SELLING_PRODUCT_NAV");
        validateExist("BE_CROSS_SELLING_PRODUCT_UNIT_ESTIMATION");
    }

    private String getRedemptionReceiverAccount() {
        return getText("BE_AKUN_PENERIMA");
    }

    private String getRedemptionConfirmationProduct() {
        return getText("BE_CROSS_SELLING_PRODUCT_NAME");
    }

    private String getPopupTitle() {
        validateExist("BE_REDEEM_CONFIRM_POPUP_TITLE", 3);
        return getText("BE_REDEEM_CONFIRM_POPUP_TITLE");
    }

    public void tapLanjutBtn() {
        if (InvestmentData.getCrossSellingTransaction()) {
            tapCrossSellingTncDisclaimerCheckbox();
        }
        webViewTapOnElement("BE_LANJUT_BTN");
    }

    private void tapCrossSellingTncDisclaimerCheckbox() {
        webViewTapOnElement("BE_CROSS_SELLING_TNC_CHECKBOX");
    }

    public void validateVerificationPage() {
        if(isElementExist("BE_PASSWORD_VERIFICATION_NATIVE_FIELD", 10)) {
            verifyElementExist("BE_VERIFICATION_NATIVE_BTN");
            verifyElementExist("BE_VERIFICATION_NATIVE_HEADER");
        } else {
            validateExist("BE_PASSWORD_VERIFICATION_FIELD", 10);
            validateExist("BE_VERIFICATION_BTN");
            validateWebViewPageTitle("Verifikasi Akun");
        }
    }

    public void inputVerificationPassword(String password) {
        if(isElementExist("BE_PASSWORD_VERIFICATION_NATIVE_FIELD",3)){
            typeAndEnterValue("BE_PASSWORD_VERIFICATION_NATIVE_FIELD", password);
        } else {
            typeValue("BE_PASSWORD_VERIFICATION_FIELD", password);
        }
    }

    public void tapResetPasswordBtn() {
        webViewTapOnElement("BE_RESET_BTN");
    }

    public void validateLupaPasswordPage() {
        changeContext().toNative();
        validateExist("BE_RESET_PASSWORD_HEADER", 20);
    }

    public void validateRedeemPaymentSelectionPopup() {
        validateExist("BE_REDEEM_CONFIRM_POPUP_TITLE", 10);
        validateValue().equals("Pilihan akun penerima", getPopupTitle());
    }

    public void tapRedeemPaymentOption(String target) {
        webViewTapOnElement(constructLocator("BE_REDEEM_PAYMENT_OPTIONS", target));
        webViewTapOnElement("BE_LANJUT_BTN");
        if (target.contains("BukaReksa")) {
            InvestmentData.setCrossSellingTransaction(true);
        }
    }

    public void tapVerificationBtn() {
        webViewTapOnElement("BE_VERIFICATION_BTN");
    }

    public void validatePasswordVerificationErrorMessage() {
        validateExist("BE_ERROR_SNACKBAR", 3);
        validateValue().equals("Password salah, cek lagi ya.", getErrorMessageText(), "Invalid error message");
    }

    public void verifyPackageMutualFundBullionOnTrxList() {
        //will only check the first or top positioned transaction with nominal from bukareksa package
        changeContext().toWebview();
        verifyTransactionListDisplayed();
        validateExist(constructLocator("BE_TRX_LIST_TRX_SPECIFIC_PURCHASE_PRICE", InvestmentData.getBukaEmasPackagePurchaseNominal()));
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    public void tapOnPackageMutualFundBullionTransaction() {
        //will always tap on first transaction with purchase nominal from bukareksa package
        tapElement(constructLocator("BE_TRX_LIST_TRX_SPECIFIC_PURCHASE_PRICE", InvestmentData.getBukaEmasPackagePurchaseNominal()));
    }

    public void tapOnBukaEmasSharingSection(String option) {
        validateSharingSection();
        switch (option) {
            case "Kasih Emas":
                tapElement("BE_TRANSFER_GOLD");
                break;
            case "Minta Emas":
                tapElement("BE_REQUEST_GOLD");
                break;
            case "Tebar Emas":
                tapElement("BE_SEND_GOLD");
                break;
            default:
                Assert.fail("Invalid BukaEmas Transfer type!");
        }
    }

    public void verifyKasihEmasTransactionDetail(String method) {
        //need delay for page to refresh
        delay(2000);
        changeContext().toWebview();
        validateKasihEmasInfo();
        validateKasihEmasState(method);
    }

    private void validateKasihEmasState(String method) {
        switch(method) {
            case "link":
                validateValue().equals("Menunggu diklaim", getText("BE_TRX_DETAIL_STATE"));
                validateExist("BE_TRX_DETAIL_KASIH_EMAS_SHARE_BTN", 3);
                validateExist("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN", 3);
                break;
            case "direct":
                validateValue().equals("Selesai", getText("BE_TRX_DETAIL_STATE"));
                break;
            case "cancelled":
                //need delay for page to refresh
                delay(500);
                validateValue().equals("Dibatalkan", getText("BE_TRX_DETAIL_STATE"));
                validateNotExist("BE_TRX_DETAIL_KASIH_EMAS_SHARE_BTN", 3);
                validateNotExist("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN", 3);
                break;
            default:
                Assert.fail("Invalid Kasih Emas transaction state!");
                break;
        }
    }

    private void validateKasihEmasInfo() {
        String userType = InvestmentData.getKasihEmasSearchType();
        validateKasihEmasUsername(userType);
        validateValue().equals(InvestmentData.getTransferEmasUnit(), getText("BE_TRX_DETAIL_KASIH_EMAS_UNIT"));
        validateValue().equals(InvestmentData.getKasihEmasMessage(), getText("BE_TRX_DETAIL_KASIH_EMAS_MESSAGE"));
    }

    private void validateKasihEmasUsername(String userType) {
        validateExist("BE_TRX_DETAIL_KASIH_EMAS_RECEIVER", 10);
        String censoredName = InvestmentData.getKasihReceiverUsername().substring(0,2);
        String trxDetailName = getText("BE_TRX_DETAIL_KASIH_EMAS_RECEIVER");
        switch(userType) {
            case "username":
                validateValue().equals(InvestmentData.getKasihReceiverUsername(), getText("BE_TRX_DETAIL_KASIH_EMAS_RECEIVER"));
                break;
            case "phone":
                validateValue().contains(censoredName, trxDetailName);
                break;
            case "link":
                validateValue().equals("-", getText("BE_TRX_DETAIL_KASIH_EMAS_RECEIVER"));
                break;
            default:
                Assert.fail("Invalid user search type!");
                break;
        }
    }

    public void cancelKasihEmasTransaction() {
        tapElement("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN");
        verifyCancelKasihEmasModal();
        tapElement("BE_TRX_DETAIL_BATAL_KASIH_EMAS_CONFIRM_BTN");
    }

    private void verifyCancelKasihEmasModal() {
        validateExist("BE_TRX_DETAIL_BATAL_KASIH_EMAS_HEADER", 3);
        validateExist("BE_TRX_DETAIL_BATAL_KASIH_EMAS_CONFIRM_BTN", 3);
        validateExist("BE_TRX_DETAIL_BATAL_KASIH_EMAS_CANCEL_BTN", 3);
    }

    public void verifyTebarEmasTransactionDetail(String state) {
        changeContext().toWebview();
        //need delay for page to refresh
        delay(1000);
        validateTebarEmasInfo();
        validateTebarEmasState(state);
    }

    private void validateTebarEmasInfo() {
        refreshWebview();
        String tebarEmasDetailUnit = getText("BE_TRX_DETAIL_KASIH_EMAS_UNIT").replace(" gram", "");
        validateValue().equals(InvestmentData.getTransferEmasUnit(), tebarEmasDetailUnit);
    }

    private void validateTebarEmasState(String state) {
        switch(state) {
            case "active":
                validateValue().equals("Menunggu diklaim", getText("BE_TRX_DETAIL_STATE"));
                validateExist("BE_TRX_DETAIL_TEBAR_EMAS_LEADERBOARD_BTN", 3);
                validateExist("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN", 3);
                break;
            case "cancelled":
                validateValue().equals("Dibatalkan", getText("BE_TRX_DETAIL_STATE"));
                validateNotExist("BE_TRX_DETAIL_TEBAR_EMAS_LEADERBOARD_BTN", 3);
                validateNotExist("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN", 3);
                break;
            case "claimed":
                validateValue().equals("Selesai", getText("BE_TRX_DETAIL_STATE"));
                validateExist("BE_TRX_DETAIL_TEBAR_EMAS_LEADERBOARD_BTN", 3);
                validateNotExist("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN", 3);
                break;
            default:
                Assert.fail("Invalid Tebar Emas transaction state!");
        }
    }

    public void tapOnLihatPemenangBtn() {
        tapElement("BE_TRX_DETAIL_TEBAR_EMAS_LEADERBOARD_BTN");
    }

    public void cancelTebarEmasTransaction() {
        changeContext().toWebview();
        validateTebarEmasInfo();
        tapElement("BE_TRX_DETAIL_SHARING_EMAS_CANCEL_BTN");
        verifyCancelTebarEmasModal();
        tapElement("BE_TRX_DETAIL_BATAL_TEBAR_EMAS_CONFIRM_BTN");
    }

    private void verifyCancelTebarEmasModal() {
        validateExist("BE_TRX_DETAIL_BATAL_TEBAR_EMAS_HEADER", 3);
        validateExist("BE_TRX_DETAIL_BATAL_TEBAR_EMAS_CONFIRM_BTN", 3);
        validateExist("BE_TRX_DETAIL_BATAL_TEBAR_EMAS_CANCEL_BTN", 3);
    }

    public void tapBukaEmasHeaderBackBtn() {
        validateExist("BE_BACK_BTN", 5);
        webViewTapOnElement("BE_BACK_BTN");
    }

    public void verifyTrxTabAction(String action, String menu) {
        if (!action.toLowerCase().contains("active")) {
            tapProductTypeTab(menu);
            validateActiveTab(menu);
        }
        validateActiveTab(menu);
    }

    public void tapProductTypeTab(String tab) {
        webViewTapOnElement(constructLocator("BE_TAB_MENU", tab));
    }

    public void validateNoTagihan() {
        validateValue().equals(DANAData.getNoTagihan(),getInvoiceID());
    }

    public void validateTotalTagihan() {
        validateValue().equals(InvestmentData.getAutoInvestBuyerAmount().replaceAll("[^\\d]", ""),getTotalPurchaseAmount());
    }

    private String getTotalPurchaseAmount() {
        return getText("BE_TRX_DETAIL_TOTAL_AMOUNT").replaceAll("[^\\d]", "");
    }

    private String getPaymentMethod() {
        return getText("BE_TRX_DETAIL_PAYMENT_METHOD");
    }

    private String getInvoiceID() {
        return getText("BE_TRX_DETAIL_INVOICE_ID");
    }

    public void validatePaymentMethod() {
        String paymentMethod = getPaymentMethod();
        String expectedPaymentMethod = MtxData.getPaymentMethod();
        if(expectedPaymentMethod.equals("Transfer Bank")) {
            validateValue().equals(expectedPaymentMethod, paymentMethod + " Bank");
        } else if(expectedPaymentMethod.equals("Transfer Bank Otomatis")) {
            validateValue().equals("Virtual Account", paymentMethod);
        } else {
            validateValue().equals(expectedPaymentMethod, paymentMethod);
        }
    }

    public void inputAndValidateBukaEmasVoucher() {
        swipeUpToElement("BE_VOUCHER_CHECKBOX");
        tapElement("BE_VOUCHER_CHECKBOX");
        //after tap BE_VOUCHER_CHECKBOX element, voucher field will expand and sometimes Gunakan button will not visible
        if(!isElementVisible("BE_VOUCHER_GUNAKAN_BTN")) {
            swipeUp();
        }
        typeValue("BE_VOUCHER_CODE_FIELD", dotenv.get("BUKAEMAS_VOUCHER"));
        tapElement("BE_VOUCHER_GUNAKAN_BTN");
        waitForVisibilityOf("BE_VOUCHER_INFOTEXT", 5);
        validateValue().contains("Bonus unit akan kamu dapatkan jika tidak melakukan penjualan BukaEmas",
                getCheckoutVoucherInfoText(), "Invalid BukaEmas voucher!");
    }

    private String getCheckoutVoucherInfoText() {
        return getText("BE_VOUCHER_INFOTEXT");
    }

    public void validateRedeemVoucherCancel() {
        validateExist("BE_VOUCHER_REDEEM_CANCELLATION", 5);
    }

    //region cross selling

    public void verifyCrossSellingSection() {
        validateExist("BE_HOMEPAGE_CROSS_SELLING_SECTION", 10);
        verifyCrossSellingInfo();
    }

    private void verifyCrossSellingInfo() {
        validateExist("BE_HOMEPAGE_CROSS_SELLING_TITLE");
        validateExist("BE_HOMEPAGE_CROSS_SELLING_CLOSE_BTN");
        validateExist("BE_HOMEPAGE_CROSS_SELLING_PRODUCT_NAME");
        InvestmentData.setProductNameBukaReksa(getText("BE_HOMEPAGE_CROSS_SELLING_PRODUCT_NAME"));
        validateExist("BE_HOMEPAGE_CROSS_SELLING_PRODUCT_TYPE");
        validateExist("BE_HOMEPAGE_CROSS_SELLING_PRODUCT_RETURN");
    }

    public void tapOnLihatDetailCrossSelling() {
        webViewTapOnElement("BE_HOMEPAGE_CROSS_SELLING_LIHAT_DETAIL");
    }

    public void verifyCrossSellingDetailPopup() {
        validateExist("BE_CROSS_SELLING_DETAIL_TITLE", 10);
        validateExist("BE_CROSS_SELLING_DETAIL_FIRST_POINT");
        validateExist("BE_CROSS_SELLING_DETAIL_FIRST_INFO");
        validateExist("BE_CROSS_SELLING_DETAIL_SECOND_POINT");
        validateExist("BE_CROSS_SELLING_DETAIL_SECOND_INFO");
        validateExist("BE_CROSS_SELLING_DETAIL_THIRD_POINT");
        validateExist("BE_CROSS_SELLING_DETAIL_THIRD_INFO");
        webViewTapOnElement("BE_CROSS_SELLING_DETAIL_CLOSE_BTN");
    }

    public void verifyCrossSellingState(String state) {
        switch(state.toLowerCase()) {
            case "investor":
            case "bwr":
                validateExist("BE_CROSS_SELLING_BUKAREKSA_ENABLED", 10);
                InvestmentData.setCrossSellingState(state);
                break;
            case "under minimum bukareksa subscription":
                verifyCrossSellingErrorMessage("Min. penjualan");
                break;
            case "bwr over maximum subscription":
                verifyCrossSellingErrorMessage("Akumulasi penjualan ke BukaReksa maks. Rp 1juta.");
                break;
            case "unconfirmed user":
                verifyCrossSellingErrorMessage("Mohon verifikasi email dan no. HP akun Bukalapak");
                break;
            case "admin rejected":
                verifyCrossSellingErrorMessage("Mohon verifikasi info dulu di BukaReksa");
                break;
            default:
                Assert.fail("Invalid cross selling state!");
                break;
        }
        HelperData.setLastActionPage(new BukaEmasPage(iosDriver));
    }

    private void verifyCrossSellingErrorMessage(String message){
        validateExist("BE_CROSS_SELLING_REDEMPTION_INFOTEXT", 10);
        validateValue().contains(message, getCrossSellingRedemptionInfoText());
    }

    private String getCrossSellingRedemptionInfoText() {
        return getText("BE_CROSS_SELLING_REDEMPTION_INFOTEXT");
    }

    public void tapLihatDetailTransaksiLink() {
        InvestmentData.setTrxListType("Pembelian"); //BukaReksa transaction type
        InvestmentData.setTrxListStatus("Berhasil Dibayar"); //BukaReksa product type
        webViewTapOnElement("BE_TRX_DETAIL_LIHAT_DETAIL_TRANSAKSI_BUKAREKSA");
        changeContext().toNative();
    }

    public void tapOnMulaiDaftarBtn() {
        webViewTapOnElement("BE_CROSS_SELLING_MULAI_DAFTAR_BTN");
        changeContext().toNative();
    }

    //endregion cross selling

    //region installment

    public void chooseInstallmentTenure(String tenure) {
        tapElement(constructLocator("BE_INSTALLMENT_TENURE_OPTION", tenure));
        InvestmentData.setInstallmentTenure(tenure);
    }

    public void verifyInstallmentInformation() {
        //adding some delay to make sure installment information finish rendering
        delay(1500);
        webViewSwipeToElement("BE_INSTALLMENT_TNC");
        if (isElementExist("BE_INSTALLMENT_GOLD_WEIGHT")) {
            InvestmentData.setInstallmentGoldWeight(getText("BE_INSTALLMENT_GOLD_WEIGHT").replaceAll("[^\\d]", ""));
        } else {
            InvestmentData.setMonthlyInstallmentNominal(getText("BE_MONTHLY_INSTALLMENT_NOMINAL_INFO").replaceAll("[^\\d]", ""));
        }
        validateExist("BE_INSTALLMENT_INFO_TITLE", 3);
        validateValue().equals(InvestmentData.getMonthlyInstallmentNominal(), getText("BE_MONTHLY_INSTALLMENT_NOMINAL_INFO").replaceAll("[^\\d]", ""), "Installment nominal mismatched!");
        validateExist("BE_FIRST_INSTALLMENT_PAYMENT_INFO_TITLE", 3);
        validateValue().equals(InvestmentData.getMonthlyInstallmentNominal(), getText("BE_INSTALLMENT_FIRST_PAYMENT").replaceAll("[^\\d]", ""), "Installment nominal mismatched!");
        InvestmentData.setTotalInstallmentCheckoutPayment(getText("BE_INSTALLMENT_TOTAL_PAYMENT_NOMINAL"));
    }

    public void verifyStartInstallmentConfirmation() {
        validateExist("BE_START_INSTALLMENT_CONFIRMATION_MODAL_TITLE", 3);
        validateValue().equals(InvestmentData.getInstallmentGoldWeight(), getText("BE_START_INSTALLMENT_CONFIRMATION_GOLD_WEIGHT").replaceAll("[^\\d]", ""), "Installment weight mismatched!");
        validateValue().contains(InvestmentData.getInstallmentTenure(), getText("BE_START_INSTALLMENT_CONFIRMATION_INSTALLMENT_TENURE"), "Installment tenure mismatched!");
        validateValue().equals(InvestmentData.getTotalInstallmentCheckoutPayment(), getText("BE_START_INSTALLMENT_CONFIRMATION_FIRST_PAYMENT_NOMINAL"), "First payment mismatched!");
        validateExist("BE_START_INSTALLMENT_CONFIRMATION_TNC", 3);
    }

    public void tapOnInstallmentTncCheckbox() {
        tapElement("BE_START_INSTALLMENT_CONFIRMATION_CHECKBOX");
    }

    public void tapOnInstallmentBayarBtn() {
        tapElement("BE_START_INSTALLMENT_CONFIRMATION_BAYAR_BTN");
    }

    public void tapOnLihatInfoCicilanBtn() {
        tapElement("BE_TRX_DETAIL_INSTALLMENT_INFO_BTN");
    }

    public void verifyInfoCicilanPage() {
        validateExist("BE_INFO_CICILAN_INSTALLMENT_NUMBER",3);
        validateExist("BE_INFO_CICILAN_HOW_TO_LINK", 3);
        validateExist("BE_INFO_CICILAN_TAGIHAN_AKTIF_TITLE", 3);
        validateExist("BE_INFO_CICILAN_NEXT_PAYMENT_TITLE", 3);
        validateValue().equals(InvestmentData.getMonthlyInstallmentNominal(), getText("BE_INFO_CICILAN_MONTHLY_PAYMENT").replaceAll("[^\\d]", ""), "Monthly installment nominal mismatched!");
    }

    public void tapOnPortofolioSummaryChevron() {
        webViewTapOnElement("BE_SUMMARY_TOGGLE");
    }

    public void verifyPortofolioSummary() {
        validateExist("BE_PORTFOLIO_EMAS_TUNAI", 3);
        validateExist("BE_PORTFOLIO_EMAS_TUNAI_BALANCE", 3);
        validateExist("BE_PORTFOLIO_EMAS_TUNAI_PROFIT", 3);
        validateExist("BE_PORTFOLIO_EMAS_CICILAN", 3);
        validateExist("BE_PORTFOLIO_EMAS_CICILAN_BALANCE", 3);
        validateExist("BE_PORTFOLIO_EMAS_CICILAN_PROFIT", 3);
    }

    public void tapOnCicilanBtninHomepage(String type) {
        if (type.equalsIgnoreCase("mulai")) {
            webViewTapOnElement("BE_MULAI_CICILAN_BTN");
        } else {
            webViewTapOnElement("BE_LIHAT_CICILAN_BTN");
        }
    }

    public void isOnCicilEmasListPage() {
        //add delay for loading cicil emas list page
        delay(1500);
        validateExist("BE_SALDO_CICILAN_TEXT", 3);
        validateExist("BE_SALDO_CICILAN_AMOUNT", 3);
        validateExist("BE_SALDO_CICILAN_WEIGHT", 3);
        validateExist("BE_CICIL_LAGI_BTN", 3);
    }

    public void tapOnCicilLagiLink() {
        webViewTapOnElement("BE_CICIL_LAGI_BTN");
    }

    //endregion installment

    //endregion

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //Digital Widget
    public void validateBalanceFromDigitalWidget() {
        String digitalWidgetBalance = removeStringAfterComma(InvestmentData.getTotalPortfolio());
        validateValue().contains(digitalWidgetBalance, getBukaEmasBalanceTotalText());
    }

    public String getBukaEmasBalanceTotalText(){
        return getText("BE_TOTAL_INVEST");
    }
}
