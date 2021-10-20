package bukalapak.stepDefinitions.xpr;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * IMPORTANT NOTES:
 * Please simplify the test steps (from each page object class) into one line only in each features steps.
 */

public class CekKurirOngkirProdDetailStepDefinitions extends TestInstrument implements En {
    public CekKurirOngkirProdDetailStepDefinitions() {
        Given("user is in \"cek_kurir_ongkir_ProductDetail\" page", () -> {
            bukalapak.cekKurirOngkirProductDetailPage().userOnCekKurirOngkirProductDetailPage();
        });

        When("user click \"([^\"]*)\" element \"([^\"]*)\" times", (String sign, String total) -> {

            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA("J&T REG");
            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA("GO-SEND Same Day");
            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA("JNE Trucking");
            bukalapak.cekKurirOngkirProductDetailPage().changeTotalProductInProducDetail(total);
        });

        When("user have default address in Province (\"([^\"]*)\")", (String nameProvinceDefault) -> {
            bukalapak.cekKurirOngkirProductDetailPage().validateNameProvinceDefault(nameProvinceDefault);
        });

        When("seller provide courier (\"([^\"]*)\"), (\"([^\"]*)\"), and (\"([^\"]*)\")", (String courier1, String courier2, String courier3) -> {
            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA(courier1);
            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA(courier2);
            bukalapak.cekKurirOngkirProductDetailPage().setShippingPriceAndETA(courier3);
        });

        When("user select City (\"([^\"]*)\") on Cek Ongkir Product Detail", (String city) -> {
            bukalapak.cekKurirOngkirProductDetailPage().chooseElementLocationOnCekOngkir(city);
        });

        When("user select Province (\"([^\"]*)\") on Cek Ongkir Product Detail", (String province) -> {
            bukalapak.cekKurirOngkirProductDetailPage().chooseElementLocationOnCekOngkir(province);
        });

        When("user select Kecamatan (\"([^\"]*)\") on Cek Ongkir Product Detail", (String kecamatan) -> {
            bukalapak.cekKurirOngkirProductDetailPage().chooseElementLocationOnCekOngkirGeocoder(kecamatan);
        });

        Then("validate courier price is (\"([^\"]*)\")", (String status) -> {
            bukalapak.cekKurirOngkirProductDetailPage().validateFeeAndETA(status, "J&T REG", "GO-SEND Same Day",
                    "JNE Trucking");
            bukalapak.cekKurirOngkirProductDetailPage().userOnCekKurirOngkirProductDetailPage();
        });

        Then("validate new location are shown correctly", () -> {
            bukalapak.cekKurirOngkirProductDetailPage().validateLocationAfterChanged();
        });

        Then("validate notif (\"([^\"]*)\") is shown if area is out of service", (String warningNotif) -> {
            bukalapak.cekKurirOngkirProductDetailPage().validateServiceAreaError("GO-SEND Same Day", warningNotif);
            bukalapak.cekKurirOngkirProductDetailPage().userOnCekKurirOngkirProductDetailPage();
        });

        And("user click cek ongkos kirim", () -> {
            bukalapak.cekKurirOngkirProductDetailPage().clickCekOngkosKirim();
        });

        And("user change the address",()->{
            bukalapak.cekKurirOngkirProductDetailPage().clickUbahButton();
        });

        And("^user validate Kurir pilihan section on Cek Ongkir Product Detail", () -> {
            bukalapak.cekKurirOngkirProductDetailPage().validateKurirSection();
        });
    }

}
