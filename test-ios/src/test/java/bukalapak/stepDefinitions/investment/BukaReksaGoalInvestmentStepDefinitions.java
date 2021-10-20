package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaGoalInvestmentStepDefinitions extends TestInstrument implements En {

    public BukaReksaGoalInvestmentStepDefinitions() {

        // region goal investment

        When("user tap goal investment section in portofolio page", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapOnGoalInvestmentSection();
        });

        Then("user should be able to see Tujuan Investasi page displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyGoalInvestmentPageDisplayed();
        });

        And("user delete all goal investment in the list", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().deleteAllGoalInvestment();
        });

        Then("user should be able to see Tujuan Investasi page is empty", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyTujuanInvestasiPageEmpty();
        });

        When("user tap on Tujuan Lainnya in goal investment entry point", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapTujuanLainIcon();
        });

        Then("user should be able to see Apa tujuan investasi kamu page displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyApaTujuanInvestasiKamuDisplayed();
        });

        And("user choose one of goal investment type", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().chooseGoalInvestmentType();
        });

        Then("user should be able to see Atur Tujuan Investasi Form", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyAturTujuanInvestasiPageDisplayed();
        });

        When("user inputs \"([^\"]*)\" to Berapa jumlah yang mau ditabung field", (String targetAmount) -> {
            bukalapak.bukaReksaGoalInvestmentPage().inputTargetAmount(targetAmount);
        });

        And("user choose target time for goal investment", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().chooseTargetTime();
        });

        Then("user can see suggestion invest amount per month", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyInvestPerMonthSuggestion();
        });

        When("user tap Lanjut lihat simulasi button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapLanjutLihatSimulasiButton();
        });

        Then("user should be able to see create goal investment success message", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifySuccessCreateGoalInvestmentMessageDisplayed();
        });

        When("user select one of goals investment", () ->{
            bukalapak.bukaReksaGoalInvestmentPage().tapGoalInvestCard();
        });

        Then("user should be able to see dashboard goal investment page displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyDashboardInvestPageDisplayed();
        });

        When("user tap on transaction history button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapHistoryTransactionBtn();
        });

        Then("verify goal investment transaction page displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyGoalInvestTrxListDisplayed();
        });

        When("user tap on transaction settings button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapGoalInvestSettingsBtn();
        });

        Then("user should be able to see goal invest settings option", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifySettingOptionDisplayed();
        });

        When("user tap on \"([^\"]*)\" Tujuan Investasi button", (String button) -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapGoalInvestSettingsOption(button);
        });

        Then("user should be able to see Nama Tujuan Investasi modal displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyNamaTujuanInvestasiModalDisplayed();
        });

        When("user tap on Goal Investment Name in Atur Tujuan Investasi Form", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapGoalInvestName();
        });

        When("user change Tujuan Investasi name to \"([^\"]*)\"", (String name) -> {
            bukalapak.bukaReksaGoalInvestmentPage().inputNamaTujuanInvestasi(name);
        });

        When("user tap on Simpan nama tujuan investasi button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapSimpanNamaInvestasiBtn();
        });

        Then("user verify nama tujuan investasi updated", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyGoalInvestNameUpdated();
        });

        Then("user should be able to see delete modal confirmation displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyDeleteConfirmationDisplayed();
        });

        When("user tap on hapus tujuan investasi button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapOnHapusTujuanBtn();
        });

        Then("user should be able to see tujuan investasi removed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyDeleteSnackbarDisplayed();
        });

        When("user tap on Beli Paket Rekomendasi from selected goal", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapBeliPaketRekomendasiBtn();
        });

        When("user tap on Buat Tujuan Investasi baru", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapBuatTujuanBaruBtn();
        });

        Then("user should be able to see Mulai Investasi page displayed", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyMulaiInvestasiPageDisplayed();
        });

        When("user tap on Beli paket rekomendasi button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapBeliPaketRekomendasiBtnMulaiInv();
        });

        Then("user should be able to see processing investor goal investment error toast", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyProcessingUserErrorMessageDisplayed();
        });

        Then("user should be able to see bwr subscribe modal in Mulai Investasi page", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyBWRModalDisplayed();
        });

        And("user should see Beli paket rekomendasi modal", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyBeliPaketRekomendasiModalDisplayed();
        });

        And("user inputs \"([^\"]*)\" monthly package nominal amount", (String monthlyAmountState) -> {
            bukalapak.bukaReksaGoalInvestmentPage().inputMonthlyAmount(monthlyAmountState);
        });

        Then("user should see \"([^\"]*)\" goal investment nominal error", (String minMaxError) -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyGoalInvestmentNominalErrorDisplayed(minMaxError);
        });

        And("user can see total goals purchase info", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().verifyTotalGoalsPurchaseInfo();
        });

        And("user tap on goals terms and condition chekbox", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapOnGoalsTncChekbox();
        });

        And("user tap on Lanjutkan Pembayaran button", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapOnLanjutkanPembayaranButton();
        });

        Then("user should be able to see new goal investment created", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().validateGoalInvestmentIsCreated();
        });
        // end region

    }
}
