package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import bukalapak.data.InvestmentData;
import bukalapak.data.UserData;
import cucumber.api.java8.En;
import org.junit.Assert;

/**
 * @author Fajar Kurniawan, 09/06/20
 */

public class BukaEmasStepDefinitions extends TestInstrument implements En {

    public BukaEmasStepDefinitions() {

        Then("^user can see BukaEmas webview displayed", () -> {
            bukalapak.bukaEmasPage().verifyWebViewDisplayed();
        });

        And("^user can see buy price chart",  () -> {
            bukalapak.bukaEmasPage().verifyBuyPriceChart();
        });

        And("^user verify sale price chart",  () -> {
            bukalapak.bukaEmasPage().verifySalePriceChart();
        });

        When("user verify BukaEmas banner displayed", () -> {
            bukalapak.bukaEmasPage().validateBannerSectionDisplayed();
        });

        When("user tap on BukaEmas banner", () -> {
            bukalapak.bukaEmasPage().tapBannerPromo();
        });

        When("user should be able to see BukaEmas promo detail", () -> {
            bukalapak.bukaEmasPage().validatePromoDetail();
        });

        When("^user tap on Lihat Transaksi button", () -> {
            bukalapak.bukaEmasPage().verifySeeTransactionBtn();
            bukalapak.bukaEmasPage().tapOnLihatTransaksiBtn();
        });

        Then("^user can see BukaEmas Transaction List displayed", () -> {
            bukalapak.bukaEmasPage().verifyTransactionListDisplayed();
        });

        Then("^user can see the quick filter tag will be reset to all types of options", () -> {
            bukalapak.bukaEmasPage().validateQuickFilterTagIsInAllTypes();
        });

        When("^user select quick filter with tag \"([^\"]*)\" option", (String option) -> {
            bukalapak.bukaEmasPage().selectQuickFilter(option);
        });

        And("^user can see their selected quick filter tag option", () -> {
            bukalapak.bukaEmasPage().validateQuickFilterTagOptionIsSelected();
        });

        When("^user tap on Filter Transaksi button", () -> {
            bukalapak.bukaEmasPage().tapOnFilterTransactionBtn();
        });

        Then("^user can see Transaction Filter modal displayed", () -> {
            bukalapak.bukaEmasPage().validateTransactionFilterModalDisplayed();
        });

        When("^user select filter transaction type(?: with (.*) option)?$", (String type) -> {
            bukalapak.bukaEmasPage().selectFilterTransactionType(type);
        });

        When("user can see transaction type radio button is checked", () -> {
            bukalapak.bukaEmasPage().verifyRadioBtnCheckedAfterSelection();
        });

        When("^user select filter transaction state(?: with (.*) option)?$", (String state) -> {
            bukalapak.bukaEmasPage().selectFilterTransactionState(state);
        });

        When("user can see transaction state checkbox is checked", () -> {
            bukalapak.bukaEmasPage().verifyCheckBoxCheckedAfterSelection();
        });

        Then("^user tap on Transaction Filter Apply button", () -> {
            bukalapak.bukaEmasPage().tapOnFilterApplyBtn();
        });

        Then("^user can see Transaction Filter check icon", () -> {
            bukalapak.bukaEmasPage().validateFilterCheckIcon();
        });

        Then("^user can't see Transaction Filter check icon", () -> {
            bukalapak.bukaEmasPage().validateFilterUnCheckIcon();
        });

        Then("^user validate transaction list result after filtering by \"([^\"]*)\"", (String method) -> {
            switch (method) {
                case "Type":
                    bukalapak.bukaEmasPage().validateTransactionListFilterByType();
                    break;
                case "State":
                    bukalapak.bukaEmasPage().validateTransactionListFilterByState();
                    break;
                case "Combination":
                    bukalapak.bukaEmasPage().validateTransactionListFilterCombination();
                    break;
                default:
                    Assert.fail(method + " isn't recognize method type");
            }
        });

        When("^user tap on Date field section", () -> {
            bukalapak.bukaEmasPage().tapOnDateField();
        });

        When("^user can see Date Picker modal displayed", () -> {
            bukalapak.bukaEmasPage().validateDatePickerDisplayed();
        });

        When("^user select filter by date \"([^\"]*)\" month \"([^\"]*)\" year \"([^\"]*)\"",
                (String date, String month, String year) -> {
                    bukalapak.bukaEmasPage().selectAlchemyDatePicker(date, month, year);
                    bukalapak.datePickerAlchemyPage().selectDatePicker(date, month, year);
                });

        When("^user can see Date Picker value is updated", () -> {
            bukalapak.bukaEmasPage().validateDateValueIsSelected();
        });

        When("^user tap on Transaction List card", () -> {
            bukalapak.bukaEmasPage().tapOnTransactionList();
        });

        When("^user validate result after filtering transaction by Date", () -> {
            bukalapak.bukaEmasPage().tapOnTransactionList();
            bukalapak.bukaEmasPage().validateTransactionDetail();
            bukalapak.bukaEmasPage().validateTransactionInvoiceDate();
        });

        When("^user reset transaction filter", () -> {
            bukalapak.bukaEmasPage().tapOnFilterTransactionBtn();
            bukalapak.bukaEmasPage().validateTransactionFilterModalDisplayed();
            bukalapak.bukaEmasPage().tapOnResetFilterBtn();
            bukalapak.bukaEmasPage().tapOnFilterApplyBtn();
        });

        And("^user buy gold from BukaEmas by price", () -> {
            bukalapak.bukaEmasPage().tapOnBeliEmasBtn();
            bukalapak.bukaEmasPage().tapOnMinEmasPrice();
            bukalapak.bukaEmasPage().tapOnBeliEmasConfirmBtn();
        });

        When("^user tap on Beli button",  () -> {
            bukalapak.bukaEmasPage().tapBeliBtnOnEntryPoint();
        });

        Then("^validate Beli Emas page displayed", () -> {
            bukalapak.bukaEmasPage().validateBeliEmasPage();
        });

        And("^validate \"([^\"]*)\" tap active", (String activeTab) -> {
            bukalapak.bukaEmasPage().validateActiveTab(activeTab);
        });

        When("^user (tap|see active) emas \"([^\"]*)\" tab$", (String action, String menu) -> {
            bukalapak.bukaEmasPage().verifyTrxTabAction(action, menu);
        });

        And("^user select \"([^\"]*)\" radio button", (String menu) -> {
            bukalapak.bukaEmasPage().tapRadioBtn(menu);
        });

        When("^user input purchase amount by \"([^\"]*)\"(?: for emas \"(.*)\")?$",  (String amount, String productType) -> {
            bukalapak.bukaEmasPage().inputPurchaseAmount(amount, productType);
        });

        When("^user input purchase unit by \"([^\"]*)\"(?: for emas \"(.*)\")?$",  (String unit, String productType) -> {
            bukalapak.bukaEmasPage().inputPurchaseUnit(unit, productType);
        });

        Then("^user verify \"([^\"]*)\" transaction value error", (String errorType) -> {
            bukalapak.bukaEmasPage().validateTransactionValueError(errorType);
        });

        Then("^user verify “minimum” transaction “purchase” error message", () -> {
            bukalapak.bukaEmasPage().validateInstallmentErrorMessage();
        });

        Then("^user verify “maximum” transaction “purchase” error message", () -> {
            bukalapak.bukaEmasPage().validateInstallmentErrorMessage();
        });

        And("^tap on Beli button", () -> {
            bukalapak.bukaEmasPage().tapBeliBtnOnTrxPoint();
        });

        When("^user tap on Jual button",  () -> {
            bukalapak.bukaEmasPage().tapJualBtnOnEntryPoint();
        });

        Then("^validate Jual Emas page displayed", () -> {
            bukalapak.bukaEmasPage().validateJualEmasPage();
        });

        When("^user input \"([^\"]*)\" as redemption amount", (String amount) -> {
            bukalapak.bukaEmasPage().inputRedeemAmount(amount);
        });

        When("^user input \"([^\"]*)\" as redemption unit", (String unit) -> {
            bukalapak.bukaEmasPage().inputRedeemUnit(unit);
        });

        And("^tap on Jual button", () -> {
            bukalapak.bukaEmasPage().tapJualBtnOnTrxPoint();
        });

        Then("^user validate redemption confirmation popup", () -> {
            bukalapak.bukaEmasPage().validateRedeemConfirmPopup();
        });

        Then("user should be able to see DANA option disabled", () -> {
            bukalapak.bukaEmasPage().verifyDanaOptionDisabled();
        });

        When("^user tap on Lanjut button", () -> {
            bukalapak.bukaEmasPage().tapLanjutBtn();
        });

        Then("^user validate verification page displayed", () -> {
            bukalapak.bukaEmasPage().validateVerificationPage();
        });

        When("^user tap on webview reset password button", () -> {
            bukalapak.bukaEmasPage().tapResetPasswordBtn();
        });

        Then("^user validate Lupa password page displayed", () -> {
            bukalapak.bukaEmasPage().validateLupaPasswordPage();
        });

        Then("^validate redeem payment selection modal displayed$", () -> {
            bukalapak.bukaEmasPage().validateRedeemPaymentSelectionPopup();
        });

        Then("^user select \"([^\"]*)\" as redemption option", (String target) -> {
            bukalapak.bukaEmasPage().tapRedeemPaymentOption(target);
        });

        Then("^user input valid password", () -> {
            bukalapak.bukaEmasPage().inputVerificationPassword(UserData.getPassword());
        });

        Then("^user input invalid password", () -> {
            bukalapak.bukaEmasPage().inputVerificationPassword(UserData.getPassword()+"fail");
        });

        Then("^user tap verifikasi button on verification page", () -> {
            bukalapak.bukaEmasPage().tapVerificationBtn();
        });

        Then("^user verify invalid password error message on verification page", () -> {
            bukalapak.bukaEmasPage().validatePasswordVerificationErrorMessage();
        });

        Then("^user see transaction detail page(?: from \"(.*)\")?$", (String page) -> {
            bukalapak.bukaEmasPage().validateTransactionDetail(page);
        });

        Then("^user see latest transaction on the first row",  () -> {
            bukalapak.bukaEmasPage().verifyLatestTrasactionOnFirstRow();
        });

        When("^user tap back button from detail page",  () -> {
            bukalapak.bukaEmasPage().tapBackBtnWebview();
        });

        When("^user tap on first purchased transaction on list",  () -> {
            bukalapak.bukaEmasPage().tapFirstPurchaseOnList();
        });

        When("^user tap beli lagi button",  () -> {
            bukalapak.bukaEmasPage().tapBeliLagiBtn();
        });

        Then("^redirect to payment method page",  () -> {
            bukalapak.bukaEmasPage().validatePaymentMethodPage();
        });

        And("^User select \"([^\"]*)\" (?:to \"(.*)\" )?as payment method", (String paymentMethod, String bankName) -> {
            bukalapak.bukaEmasPage().selectPaymentMethod(paymentMethod, bankName);
        });

        And("^Tap on Bayar button on payment page", () -> {
            bukalapak.bukaEmasPage().tapBayarBtn();
        });

        And("^Tap on Bayar button on update payment page", () -> {
            bukalapak.bukaEmasPage().tapBayarBtnUpdatePayment();
        });

        Then("^redirect to update payment method page",  () -> {
            bukalapak.bukaEmasPage().validateUpdatePaymentMethodPage();
        });

        Then("^validate confirm payment page displayed",  () -> {
            bukalapak.bukaEmasPage().validateConfirmPaymentPage();
        });

        When("^user tap Lihat Detail Pesanan button confirmation page",  () -> {
            bukalapak.bukaEmasPage().tapLihatDetailPesananBtn();
        });

        Then("^redirect to invoice page",  () -> {
            bukalapak.bukaEmasPage().validateInvoicePage();
        });

        When("^user tap Detail Transaksi button",  () -> {
            bukalapak.bukaEmasPage().tapDetailsTrxBtn();
        });

        When("^user tap on ubah pembayaran button", () -> {
            bukalapak.bukaEmasPage().tapUbahPembayaranBtn();
        });

        When("^user tap Semua Tentang BukaEmas section",  () -> {
            bukalapak.bukaEmasPage().tapSemuaTentangBukaEmas();
        });

        Then("^user see Semua Tentang BukaEmas page displayed",  () -> {
            bukalapak.bukaEmasPage().validateSemuaTentangBukaEmasPage();
        });

        When("^user tap first article",  () -> {
            bukalapak.bukaEmasPage().tapFirstArticle();
        });

        When("user tap on Lihat artikel lainnya in serba serbi article section", () -> {
            bukalapak.bukaEmasPage().tapLihatArtikelLainnyaBtn();
        });

        Then("^user see article detail",  () -> {
            bukalapak.bukaEmasPage().validateArticleDetail();
        });

        And("^user tap on \"([^\"]*)\" button in sharing section$", (String option) -> {
            bukalapak.bukaEmasPage().tapOnBukaEmasSharingSection(option);
        });

        And("^user should see \"([^\"]*)\" Kasih Emas transaction detail$", (String method) -> {
            bukalapak.bukaEmasPage().verifyKasihEmasTransactionDetail(method);
        });

        And("^user cancels Kasih Emas transaction$", () -> {
            bukalapak.bukaEmasPage().cancelKasihEmasTransaction();
        });

        And("^user should see \"([^\"]*)\" Tebar Emas transaction detail$", (String state) -> {
            bukalapak.bukaEmasPage().verifyTebarEmasTransactionDetail(state);
        });

        And("^user tap on Lihat Pemenang button$", () -> {
            bukalapak.bukaEmasPage().tapOnLihatPemenangBtn();
        });

        And("^user cancels Tebar Emas transaction$", () -> {
            bukalapak.bukaEmasPage().cancelTebarEmasTransaction();
        });

        And("^user should see Gold Forecast section$", () -> {
            bukalapak.bukaEmasPage().validateGoldForecastSectionDisplayed();
        });

        And("^user tap on Gold Forecast section$", () -> {
            bukalapak.bukaEmasPage().tapGoldForecastSection();
        });

        And("^user should see Gold Forecast page$", () -> {
            bukalapak.bukaEmasPage().verifyGoldForecastPage();
        });

        And("^user tap on \"([^\"]*)\" button in Gold Forecast page$", (String button) -> {
            bukalapak.bukaEmasPage().tapGoldPredictionPageTransactionBtn(button);
            InvestmentData.setFromGoldForecast(true);
        });

        And("^user go to Gold Forecast History Page$", () -> {
            bukalapak.bukaEmasPage().tapGoldPredictionPageHistoryBtn();
        });

        And("^user should see Gold Forecast History Page$", () -> {
            bukalapak.bukaEmasPage().verifyGoldPredictionHistoryPage();
        });

        And("^user tap on Gold Forecast History Item$", () -> {
            bukalapak.bukaEmasPage().tapGoldPredictionHistoryItem();
        });

        And("^user should see Gold Forecast Prediction Popup$", () -> {
            bukalapak.bukaEmasPage().verifyGoldPredictionHistoryPopup();
        });

        And("^user tap Gold Forecast prediction in transaction page$", () -> {
           bukalapak.bukaEmasPage().tapOnTrxPageGoldForecastPrediction();
        });

        And("^user tap on BukaEmas Header back button$", () -> {
            bukalapak.bukaEmasPage().tapBukaEmasHeaderBackBtn();
        });

        And("^user verify emas & reksa package transaction detail in bukaemas page$", () -> {
            bukalapak.bukaEmasPage().verifyPackageMutualFundBullionOnTrxList();
            bukalapak.bukaEmasPage().tapOnPackageMutualFundBullionTransaction();
        });

        Then("user validate autoinvest detail transaction is match with data given", () -> {
            bukalapak.bukaEmasPage().validateNoTagihan();
            bukalapak.bukaEmasPage().validateTotalTagihan();
            bukalapak.bukaEmasPage().validatePaymentMethod();
        });

        And("user input bukaemas promo code and validate voucher is applied", () -> {
            bukalapak.bukaEmasPage().inputAndValidateBukaEmasVoucher();
        });

        And("user should be able to see BukaEmas promo cancellation information", () -> {
            bukalapak.bukaEmasPage().validateRedeemVoucherCancel();
        });

        And("user should see cross selling section in  bukaemas homepage", () -> {
            bukalapak.bukaEmasPage().verifyCrossSellingSection();
        });

        And("user tap on Lihat detail in cross selling section", () -> {
            bukalapak.bukaEmasPage().tapOnLihatDetailCrossSelling();
        });

        And("user should see cross selling detail popup", () -> {
            bukalapak.bukaEmasPage().verifyCrossSellingDetailPopup();
        });

        And("user verify cross selling popup for \"([^\"]*)\"", (String state) -> {
            bukalapak.bukaEmasPage().verifyCrossSellingState(state);
        });

        And("user tap on Lihat detail transaksi in transaction detail page", () -> {
            bukalapak.bukaEmasPage().tapLihatDetailTransaksiLink();
        });

        And("user tap on Mulai Daftar Button in transaction detail page", () -> {
            bukalapak.bukaEmasPage().tapOnMulaiDaftarBtn();
        });

        And("^user choose \"([^\"]*)\" bulan as installment tenure$", (String tenure) -> {
            bukalapak.bukaEmasPage().chooseInstallmentTenure(tenure);
        });

        And("^user should see installment information$", () -> {
            bukalapak.bukaEmasPage().verifyInstallmentInformation();
        });

        And("^user should see start installment confirmation modal$", () -> {
            bukalapak.bukaEmasPage().verifyStartInstallmentConfirmation();
        });

        And("^user tap on installment tnc checkbox$", () -> {
            bukalapak.bukaEmasPage().tapOnInstallmentTncCheckbox();
        });

        And("^user tap on bayar button in start installment confirmation modal$", () -> {
            bukalapak.bukaEmasPage().tapOnInstallmentBayarBtn();
        });

        And("^user tap on Lihat info cicilan button$", () -> {
            bukalapak.bukaEmasPage().tapOnLihatInfoCicilanBtn();
        });

        And("^user should see Info cicilan page$", () -> {
            bukalapak.bukaEmasPage().verifyInfoCicilanPage();
        });

        And("^user tap on BukaEmas portofolio summary chevron$", () -> {
            bukalapak.bukaEmasPage().tapOnPortofolioSummaryChevron();
        });

        And("^user should see their bukaemas portfolio summary$", () -> {
            bukalapak.bukaEmasPage().verifyPortofolioSummary();
        });

        And("^user tap on \"([^\"]*)\" cicilan button in homepage$", (String type) -> {
            bukalapak.bukaEmasPage().tapOnCicilanBtninHomepage(type);
        });

        And("^user should be able to see Cicil emas list page$", () -> {
            bukalapak.bukaEmasPage().isOnCicilEmasListPage();
        });

        And("^user tap on Cicil Lagi link$", () -> {
            bukalapak.bukaEmasPage().tapOnCicilLagiLink();
        });

        And("user should see BukaEmas balance similar to digital widget balance", () -> {
            bukalapak.bukaEmasPage().validateBalanceFromDigitalWidget();
        });
    }
}
