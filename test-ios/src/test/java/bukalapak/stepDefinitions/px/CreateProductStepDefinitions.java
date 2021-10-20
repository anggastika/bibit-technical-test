package bukalapak.stepDefinitions.px;

import bukalapak.TestInstrument;
import bukalapak.data.PXData;
import cucumber.api.java8.En;

public class CreateProductStepDefinitions extends TestInstrument implements En {

    public CreateProductStepDefinitions() {

        And("user input product name in field Nama Barang", () -> {
            bukalapak.createProductPage().setProdName();
        });

        And("user upload product images from gallery", () -> {
            bukalapak.createProductPage().uploadProductImages();
        });

        And("user input product price in field Harga Barang", () -> {
            bukalapak.createProductPage().typeProdPrice();
        });

        And("user input product stock in field Stok Barang", () -> {
            bukalapak.createProductPage().typeProdStock();
        });

        And("user input product weight in field Berat Barang", () -> {
            bukalapak.createProductPage().typeProdWeight();
        });

        And("user input or set product description", () -> {
            bukalapak.createProductPage().typeProdDesc();
        });

        And("user set product category", () -> {
            bukalapak.createProductPage().setProdCategory();
        });

        And("user input merk of product", () -> {
            bukalapak.createProductPage().typeProdBrand();
        });

        And("user input product assurance", () -> {
            bukalapak.createProductPage().setProdAssurance();
        });

        And("user input video description of product", () -> {
            bukalapak.createProductPage().setProdVideo();
        });

        And("user set harga grosir of product", () -> {
            bukalapak.createProductPage().setGrosirPrice();
        });

        And("user set label or etalase of product", () -> {
            bukalapak.createProductPage().setProdLabel();
        });

        And("user set product category with variant available", () -> {
            bukalapak.createProductPage().setProdCategorywithVariant();
        });

        And("user completes product specification for Fashion Pria Kaos", () -> {
            bukalapak.createProductPage().setSpecsVariantKaosPria();
        });

        And("user completes variant for Fashion Pria Kaos", () -> {
            bukalapak.createProductPage().setVariantKaosPria();
        });

        And("user completes each Daftar Variant for Fashion Pria Kaos", () -> {
            bukalapak.createProductPage().uploadEachProductVariant("create_product_variant_1");
            bukalapak.createProductPage().uploadEachProductVariant("create_product_variant_2");
        });

        And("user finish filling up product detail and upload the product", () -> {
            bukalapak.createProductPage().uploadNewProduct();
        });

        And("user save new cloned product", () -> {
            bukalapak.createProductPage().uploadCloneProduct();
        });

        Then("verify product successfully uploaded", () -> {
            bukalapak.createProductPage().verifyCreateProdSuccess(PXData.getRandomProductName());
        });

        Then("verify product successfully added to list Barang Dijual", () -> {
            bukalapak.createProductPage().verifyProductonList(PXData.getRandomProductName());
        });

        And("verify product variant displayed as user set when create product", () -> {
            bukalapak.createProductPage().verifyCreateProdVariantCorrect();
        });

        And("verify product wholesale displayed as user set when create product", () -> {
            bukalapak.createProductPage().verifyCreateProdWholesaleCorrect();
        });

        When("user fill product name to \"([^\"]*)\" in sell product main page", (String randomProdName) -> {
            bukalapak.createProductPage().editProdName(randomProdName + bukalapak.createProductPage().getRandomNum(1000));
        });

        And("user is in product detail page", () -> {
            bukalapak.createProductPage().isonPDPpage();
        });

        And("user preview recent uploaded product", () -> {
            bukalapak.createProductPage().goToPreview();
        });

        And("user go to product information on product detail page", () -> {
            bukalapak.createProductPage().goToProductInfo();
        });

        And("user is in Jual Barang page", () -> {
            bukalapak.createProductPage().userOnCreateProductPage();
        });

        And("^user duplicated product with name \"([^\"]*)\"$", (String productName) -> {
            bukalapak.createProductPage().inputProductName(productName);
        });

        And("user access create product page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.createProductPage().goToCreateProductPageWithDeeplink(deeplink);
        });

        And("^user will (not )?see freeze information and (not )?see self unfreeze button$", (String freezeInfo, String selfUnfreezeBtn)  -> {
            bukalapak.createProductPage().validateFreeze(freezeInfo,selfUnfreezeBtn);
        });
    }
}
