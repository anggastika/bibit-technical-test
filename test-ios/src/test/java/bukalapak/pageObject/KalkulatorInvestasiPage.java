package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KalkulatorInvestasiPage extends BasePage {
    
    public KalkulatorInvestasiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKalkulatorInvestasiPage() {
        verifyElementExist("investment_kalkulator_investment_title");
        verifyElementExist("investment_cari_produk_button");
        HelperData.setLastActionPage(new KalkulatorInvestasiPage(iosDriver));
    }

    public void clickKalkulatorInvestasi() {
        allowPopup();
        tapElement("home_blhome_tab");
        swipeDownToElement("home_lainnya_widget");
        tapElement("home_lainnya_widget");
        swipeDownToElement("widget_kalkulator_finansial_widget");
        tapElement("widget_kalkulator_finansial_widget");
        waitForElementClickable("finansial_investasi_widget", 10);
        tapElement("finansial_investasi_widget");

        HelperData.setLastActionPage(new KalkulatorKPRPage(iosDriver));
    }

    public void clickBeliProdukIniButton() {
        tapElement("investment_beli_produk_ini_button");
    }

    public void enterGoalInvestment(String goalInvestment) {
        tapElement("investment_goal_dropdown");
        tapElement("investment_" + goalInvestment + "_dropdown");
    }

    public void verifyCalculationResult(String totalInvestment, String period) {
        double estimatedProfit = getEstimatedProfit();
        double monthlySaving = getMonthlySaving();
        double expectedSaving = getExpectedSaving(totalInvestment, estimatedProfit, period);
        assertTrue(monthlySaving == expectedSaving, "Tabungan berdasarkan perhitungan seharusnya " + expectedSaving + ". " +
                        "Tabungan yang tertera di apps " + monthlySaving);

    }

    public void verifyPengaturanBeliRutin() {
        assertTrue(InvestmentData.getInvestmentNominal().
                        equals(getTextFromElement("belirutin_total_pembelian_text")), "Total pembelian berbeda");
    }

    public void tapLanjutButton() {
        tapElement("checkout_non_marketplace_lanjut_button");
    }

    public double getEstimatedProfit() {
        String estimatedProfit = getTextFromElement("investment_estimasi_profit_text").replace("%", "");
        double estimatedProfitDouble = Double.parseDouble(estimatedProfit);
        LogUtil.info("Perkiraan Keuntungan= " + estimatedProfitDouble);
        return estimatedProfitDouble;
    }

    public double getMonthlySaving() {
        String monthlySavingString = getTextFromElement("investment_elm_tabungan", 0).replace(".", "").replace(",", ".").replace("Rp", "");
        double monthlySaving = Double.parseDouble(monthlySavingString);
        LogUtil.info("Tabungan per Bulan= " + monthlySaving);
        return monthlySaving;
    }

    public double getExpectedSaving(String totalInvestment, double estimatedProfit, String period) {
        //PMT = ( FV * r) / [(1+r)^n] - 1]
        //PMT= value of monthly payment, r = interest rate for the period, n = the number of periods.
        double r = (estimatedProfit / 100) / 12;
        double n = Double.parseDouble(period) * 12;
        double nominal = Double.parseDouble(totalInvestment);
        double expectedSaving = Math.round((nominal * r) / ((Math.pow((1 + r), n)) - 1));
        LogUtil.info("Ekspektasi Tabungan per Bulan= " + expectedSaving);
        return expectedSaving;
    }

    public void setInvestmentPerMonth() {
        InvestmentData.setInvestmentNominal(getElementValue("investment_tabungan_perbulan_text"));
    }

    public void setInvestmentProduct() {
        InvestmentData.setInvestmentProduct(getElementValue("investment_nama_produk_text"));
    }

    public void goToHomePage() {
        for(int i=0;i<4;i++){
            tapElement("base_back_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
