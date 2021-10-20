package bukalapak.stepDefinitions.px;

import bukalapak.TestInstrument;
import bukalapak.data.PXData;
import com.bukalapak.salad.util.Direction;
import cucumber.api.java8.En;

public class EditProductStepDefinitions extends TestInstrument implements En {

    public EditProductStepDefinitions() {

        Given("user is in product edit page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.editProductPage().userOnProductEditPageWithDeeplink(deeplink);
        });

        And("user choose a product to update via Menu Lainnya", () -> {
            bukalapak.editProductPage().editWithMenuLainnya();
        });

        And("user see product in list Barang Dijual", () -> {
            bukalapak.editProductPage().saveProductNameandPrice();
        });

        And("user see product in list Tidak Dijual", () -> {
            bukalapak.editProductPage().saveProductNameandPrice();
        });

        And("user set product name with new name", () -> {
            bukalapak.editProductPage().editProdName();
        });

        And("user set product with new \"([^\"]*)\"", (String prodDesc) -> {
            bukalapak.editProductPage().editProdDesc(prodDesc);

        });

        And("user set product with new product details", () -> {
            bukalapak.editProductPage().editProdDetails();
        });

        Then("verify product name edited successfully", () -> {
            bukalapak.editProductPage().verifyEditProdNameSuccess(PXData.getRandomProductName());
        });

        And("verify product displayed as user set when edit product", () -> {
            bukalapak.editProductPage().verifyEditProdSuccess();
        });

         And("user change product name", () -> {
            bukalapak.editProductPage().editProductName();
        });

        And("user click menu for list product Tidak Dijual", () -> {
            bukalapak.editProductPage().visitProductTidakDijual();
        });

        And("user click menu for list product Barang Dijual", () -> {
            bukalapak.editProductPage().visitProductBarangDijual();
        });

        And("verify previous product successfully added to list Tidak Dijual", () -> {
            bukalapak.editProductPage().verifyProductonListTidakDijual(PXData.getProductName());
        });

        And("verify previous product successfully added to list Barang Dijual", () -> {
            bukalapak.editProductPage().verifyProductonListBarangDijual(PXData.getProductName());
        });

        And("verify previous product successfully deleted on list Barang Dijual", () -> {
            bukalapak.editProductPage().verifyProductDeletedonListBarangDijual(PXData.getProductName());
        });

        And("user set free ongkir for this product", () -> {
            bukalapak.editProductPage().updateFreeOngkir();
        });

        And("user tap on Salin Barang in more option menu", () -> {
            bukalapak.editProductPage().setSalinBarang();
        });

        And("user back to barang dijual", () -> {
            bukalapak.editProductPage().backToBarangDijual();
        });
        
        And("verify product name cloned successfully", () -> {
            bukalapak.editProductPage().verifyCloneProdNameSuccess(PXData.getRandomProductName());
        });

        And("user tap on Set discount in more option menu", () -> {
            bukalapak.editProductPage().clickSetDiscount();
        });

        And("^user set nominal discount with \"([^\"]*)\"$", (String discountNom) -> {
            bukalapak.editProductPage().setDiscount(discountNom);
        });

        When("user set date and nominal discount", () -> {
            bukalapak.editProductPage().setTanggalMulaiDiskon();
            bukalapak.editProductPage().setTanggalBerakhirDiskon();
        });

        When("user set discount duration and click Set Diskon button", () -> {
            bukalapak.editProductPage().setTanggalMulaiDiskon();
            bukalapak.editProductPage().setTanggalBerakhirDiskon();
        });

        Then("user click hapus diskon on menu of sell product listing", () -> {
            bukalapak.editProductPage().deleteDiscount();
        });

        And("verify set free ongkir on product set successfully", () -> {
            bukalapak.editProductPage().verifySetFreeOngkirSuccess();
        });

        When("user set date for tutup lapak", () -> {
            bukalapak.editProductPage().setDatePicker();
        });

        Then("user buka lapak that set as tutup lapak before", () -> {
            bukalapak.editProductPage().cancelTutupLapak();
        });

        And("verify product description displayed correctly", () -> {
            bukalapak.editProductPage().verifyProdDescription();
        });

        And("user update courier optional for product and save it", () -> {
            bukalapak.editProductPage().updateCourier();
        });

        And("verify product courier already updated", () -> {
            bukalapak.editProductPage().verifyProductCourier();
        });

        When("user edit product SLA", () -> {
            bukalapak.editProductPage().openDeliveryMenu();
            bukalapak.editProductPage().tapUbahButton();
            bukalapak.iOSBasePage().swipeToDirection(Direction.UP);
        });

        When("user save edit product", () -> {
            bukalapak.editProductPage().tapOnSaveButton();
        });
    }
  }
  