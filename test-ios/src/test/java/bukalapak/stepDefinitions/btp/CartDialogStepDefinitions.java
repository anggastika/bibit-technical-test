package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.utils.DataUtil;
import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class CartDialogStepDefinitions extends TestInstrument implements En {
    public CartDialogStepDefinitions() {

        Then("user should see tray add to cart with title Berhasil masuk keranjang", () -> {
            bukalapak.cartDialogPage().validateTrayATC();
        });

        And("^user see \"([^\"]*)\" on tray add to cart$", (String trayATCComponent) -> {
            bukalapak.cartDialogPage().validateTrayATCDetail(trayATCComponent);
        });

        And("user tap lihat keranjang on tray popup atc", () -> {
            bukalapak.cartDialogPage().tapLihatKeranjang();
        });

        And("user tap Lanjut Bayar on tray popup ATC from Beli Sekarang PDP", () -> {
            bukalapak.cartDialogPage().tapLanjutBayarTrayATCFromBeliSekarangPDP();
        });

        And("user validate delete product popup", () -> {
            bukalapak.cartDialogPage().validatePopupDeleteProduct();
        });

        And("user tap delete product on popup", () -> {
            bukalapak.cartDialogPage().tapDeleteOnPopup();
        });

        And("user tap delete and save favorite product on popup", () -> {
            bukalapak.cartDialogPage().tapDeleteAndFavOnPopup();
        });

        And("user validate snackbar Barang Berhasil Dihapus", () -> {
            bukalapak.cartPage().validateSnackBar();
        });

        And("^user validate tray popup atc( is not displayed)?$", (String flag) -> {
            bukalapak.cartDialogPage().validateTrayATCPopup(flag);
        });

        And("user delete product on tray popup atc", () -> {
            bukalapak.cartDialogPage().tapRemoveIcon();
        });

        And("user tap lihat keranjang button on tray popup atc", () -> {
            bukalapak.cartDialogPage().tapLihatKeranjangBtn();
        });

        And("user tap bayar button on tray popup atc", () -> {
            bukalapak.cartDialogPage().tapBayarBtn();
        });

        And("user click close button berhasil masuk keranjang", () -> {
            bukalapak.cartDialogPage().closeTrayAtc();
        });

        And("^user update quantity \"([^\"]*)\" to \"([^\"]*)\" on tray popup atc$", (String productName, String qty) -> {
            bukalapak.cartDialogPage().updateQtyTrayATCPopup(productName, qty);
        });

        And("user validate product price has been updated change grosir quantity", () -> {
            bukalapak.cartDialogPage().validateProductPriceGrosir();
        });

        And("^user validate quantity product \"([^\"]*)\" on tray popup atc is \"([^\"]*)\"$", (String productName, String qty) -> {
            bukalapak.cartDialogPage().validateProductQuantityOnTrayATC(productName, qty);
        });

        And("user validate product on cart from tray pop up atc", () -> {
            bukalapak.cartPage().validateProductOnCart();
        });

        And("user tap close tray add to cart", () -> {
            bukalapak.cartDialogPage().tapCloseTrayATC();
        });

        And("^user validate variant \"([^\"]*)\" on product \"([^\"]*)\"$", (String variant, String product) -> {
            bukalapak.cartDialogPage().validateVariantProduct(variant, product);
        });

        And("^user should see product \"([^\"]*)\" is disabled and have text \"([^\"]*)\" text$", (String productName, String errorMsg) -> {
            bukalapak.cartDialogPage().validateErrorMsg(productName, errorMsg);
        });

        And("user should see discount price in tray atc", () -> {
            bukalapak.cartDialogPage().validateSlishedProductPrice();
        });

        And("^user delete product \"([^\"]*)\" on tray atc$", (String productName) -> {
            bukalapak.cartDialogPage().removeSpecificProduct(productName);
        });

        And("^user validate product \"([^\"]*)\" is (not )?displayed$", (String productName, String flag) -> {
            bukalapak.cartDialogPage().validateSpecificProduct(productName, flag);
        });

        // hooks
        After(new String[]{"@add-to-cart and @mtx"}, (Scenario scenario) -> {
            iosDriver.resetApp();
            DataUtil.setDataAfterReset();
        });
    }
}
