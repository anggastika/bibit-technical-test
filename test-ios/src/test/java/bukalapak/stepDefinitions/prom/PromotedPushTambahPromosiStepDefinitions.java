package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushTambahPromosiStepDefinitions extends TestInstrument implements En {

    public PromotedPushTambahPromosiStepDefinitions() {
        And("user is in Tambah promosi page", () -> {
            bukalapak.promotedPushTambahPromosiPage().userOnTambahPromosiPage();
        });

        //promoted satuan
        And("user tick checkbox first product", () -> {
            bukalapak.promotedPushTambahPromosiPage().tickCheckboxFirstProduct();
        });

        And("user click lanjut button", () -> {
            bukalapak.promotedPushTambahPromosiPage().clickLanjutButton();
        });

        When("^user search product as \"([^\"]*)\" on Promoted Push - Tambah Promosi page$", (String product) -> {
            bukalapak.promBasePage().searchProduct(product);
        });

        //promoted grup
        And("on Tambah Promosi page, select (.*) first products", (Integer totalProducts) -> {
            bukalapak.promotedPushTambahPromosiPage().tickSomeProducts(totalProducts);
        });

        And("on Tambah Promosi page, user tap checkbox Pilih Semua Barang", () -> {
            bukalapak.promotedPushTambahPromosiPage().tickCheckboxPilihSemua();
        });

        And("user taps Buat Grup promoted button", () -> {
            bukalapak.promotedPushTambahPromosiPage().clickBuatGrupButton();
        });
    }
}
