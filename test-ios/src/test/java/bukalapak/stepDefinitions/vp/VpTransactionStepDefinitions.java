package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

/**
 * Created by Ferawati Hartanti on 02/06/20.
 */
public class VpTransactionStepDefinitions extends TestInstrument implements En {
    public VpTransactionStepDefinitions() {

         When("^user (?:buys|had bought) a (.*) product with a valid data$", (String product) -> {
             switch (product.toLowerCase()) {

                 // region PREPAID
                 case "pulsa prabayar":
                     bukalapak.pulsaPrabayarLandingPage().typeOnFieldPhoneNumber(dotenv.get("PULSA_PRABAYAR_TRANSACTION_PHONE_NUMBER"));
                     bukalapak.pulsaPrabayarLandingPage().tapAvailableDenomination();
                     bukalapak.pulsaPrabayarLandingPage().validateRincianInformasi(true);
                     bukalapak.pulsaPrabayarLandingPage().tapOnBayarButton();
                     break;
                 case "paket data roaming":
                 case "paket data":
                     bukalapak.paketDataLandingPage().typeOnFieldNomorTelepon(dotenv.get("PAKET_DATA_TRANSACTION_PHONE_NUMBER"));
                     if (product.contains("Roaming")) bukalapak.paketDataLandingPage().tapOnTabRoaming();
                     bukalapak.paketDataLandingPage().validateDenomination(true);
                     bukalapak.paketDataLandingPage().tapAvailableDenomination();
                     bukalapak.paketDataLandingPage().validateDenominationDetails(true);
                     bukalapak.paketDataLandingPage().tapOnButtonBayar();
                     break;
                 case "listrik prabayar":
                     bukalapak.listrikPrabayarLandingPage().typeOnFieldIdPelanggan(dotenv.get("LISTRIK_PRABAYAR_TRANSACTION_CUSTOMER_ID"));
                     bukalapak.listrikPrabayarLandingPage().validateDenomination();
                     bukalapak.listrikPrabayarLandingPage().tapOnDenomination(2);
                     bukalapak.listrikPrabayarLandingPage().validateRincianInformasi(true);
                     bukalapak.listrikPrabayarLandingPage().tapOnDenomination(1);
                     bukalapak.listrikPrabayarLandingPage().validateRincianInformasi(true);
                     bukalapak.listrikPrabayarLandingPage().tapOnButtonBayar();
                     break;
                 case "uang elektronik mandiri":
                 case "uang elektronik bri":
                 case "uang elektronik bni":
                     String partner = product.substring(product.lastIndexOf(" ") + 1).toUpperCase();
                     bukalapak.uangElektronikLandingPage().typeOnFieldNomorKartu(dotenv.get("UANG_ELEKTRONIK_TRANSACTION_CARD_" + partner));
                     bukalapak.uangElektronikLandingPage().setOnFieldJenisKartu(partner);
                     bukalapak.uangElektronikLandingPage().validateDenomination(true);
                     bukalapak.uangElektronikLandingPage().tapOnDenomination(2);
                     bukalapak.uangElektronikLandingPage().validateTotalTagihan();
                     bukalapak.uangElektronikLandingPage().tapOnDenomination(1);
                     bukalapak.uangElektronikLandingPage().validateTotalTagihan();
                     bukalapak.uangElektronikLandingPage().tapOnButtonBayar();
                     break;
                 // endregion

                 // region POSTPAID
                 case "pulsa pascabayar":
                     bukalapak.postpaidPhoneCreditPage().typeCustomerNumber("valid");
                     bukalapak.postpaidPhoneCreditPage().tapOnBayarButton();
                     break;
                 case "pdam":
                     bukalapak.pdamPage().typeCustomerNumber("valid");
                     bukalapak.pdamPage().pickArea(dotenv.get("PDAM_AREA"));
                     bukalapak.pdamPage().setInquireData();
                     bukalapak.pdamPage().tapOnBayarButton();
                     break;
                 case "bpjs kesehatan":
                     bukalapak.bpjsKesehatanPage().typeCustomerNumber("valid");
                     bukalapak.bpjsKesehatanPage().chooseMonth();
                     bukalapak.bpjsKesehatanPage().validateInquiryResult();
                     bukalapak.bpjsKesehatanPage().setInquireData();
                     bukalapak.bpjsKesehatanPage().tapOnBayarButton();
                     bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
                     break;
                 case "multifinance":
                     bukalapak.multifinancePage().pickBiller("Kredivo");
                     bukalapak.multifinancePage().typeCustomerNumber("valid");
                     bukalapak.multifinancePage().tapOnBayarButton();
                     bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
                     break;
                 case "telkom":
                     bukalapak.telkomPage().typeCustomerNumber(dotenv.get("TELKOM_CUSTOMER_NUMBER"));
                     bukalapak.telkomPage().setInquireData();
                     bukalapak.telkomPage().tapOnBayarButton();
                     break;
                 case "kartu kredit":
                     bukalapak.creditCardBillPage().typeNominalPayment("100000");
                     bukalapak.creditCardBillPage().typeCustomerNumber(dotenv.get("KARTU_KREDIT_CIMB_NUMBER"));
                     bukalapak.creditCardBillPage().pickBank("CIMB NIAGA");
                     bukalapak.creditCardBillPage().tapOnBayarButton();
                     break;
                 case "esamsat":
                     bukalapak.esamsatPage().typeVehicleNumber("valid");
                     bukalapak.esamsatPage().typeNIKNumber(dotenv.get("ESAMSAT_KTP"));
                     bukalapak.esamsatPage().submitDataEsamsat();
                     break;
                 case "pajak pbb":
                     bukalapak.pajakDaerahPage().inputValidProvince();
                     bukalapak.pajakDaerahPage().inputValidCity();
                     bukalapak.pajakDaerahPage().inputValidSPPT(dotenv.get("PAJAK_DAERAH_YEAR_2019"));
                     bukalapak.pajakDaerahPage().inputValidNOP();
                     break;
                 case "addon indihome":
                     bukalapak.addonIndihomePage().selectService(dotenv.get("ADDON_INDIHOME_SERVICE"));
                     bukalapak.addonIndihomePage().inputCustomerNumber(dotenv.get("ADDON_INDIHOME_VALID_NUMBER"));
                     bukalapak.addonIndihomePage().selectChannel();
                     bukalapak.addonIndihomePage().setInquireData(dotenv.get("ADDON_INDIHOME_SERVICE"));
                     bukalapak.addonIndihomePage().submitData();
                     break;
                 case "tv kabel":
                     bukalapak.cableTvPage().typeCustomerNumber("valid");
                     bukalapak.cableTvPage().pickBiller(dotenv.get("TV_KABEL_BILLER"));
                     bukalapak.cableTvPage().setInquireData();
                     bukalapak.cableTvPage().tapOnBayarButton();
                     break;
                 case "listrik pascabayar":
                     bukalapak.postpaidElectricityPage().typeCustomerNumber("valid");
                     bukalapak.postpaidElectricityPage().setInquireData();
                     bukalapak.postpaidElectricityPage().tapOnBayarButton();
                     bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
                     break;
                 case "premi asuransi":
                     bukalapak.premiAsuransiLandingPage().choosePolisProvider();
                     bukalapak.premiAsuransiLandingPage().inputPolisNumber("valid");
                     bukalapak.premiAsuransiLandingPage().tapOnBayarButton();
                     break;
                 // endregion

                 default:
                     fail(String.format("%s step not implemented yet", product));
             }
        });

        And("^user should be redirect to Virtual Product page for \"([^\"]*)\"$", (String product) -> {
            bukalapak.vpBasePage().verifyGeneralVpPage(product);
        });

        When("user copies promo code on the Tiket Kereta landing page", () -> {
          bukalapak.vpBasePage().tapSalin();
        });

        Then("the selected promo for Tiket Kereta will have copied", () -> {
          bukalapak.vpBasePage().verifyPromoTersalin();
        });

        Then("user buy pulsa prabayar \"([^\"]*)\" for \"([^\"]*)\"", (String denom, String number) -> {
            bukalapak.pulsaPrabayarLandingPage().typeOnFieldPhoneNumber(number);
            bukalapak.pulsaPrabayarLandingPage().selectDenom(denom);
            bukalapak.pulsaPrabayarLandingPage().tapOnBayarButton();
        });
    }
}
