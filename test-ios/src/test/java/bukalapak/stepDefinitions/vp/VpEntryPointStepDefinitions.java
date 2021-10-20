package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;
import static org.junit.Assert.fail;

/**
 * Created by Ayu Musfita on 24/02/20.
 */
public class VpEntryPointStepDefinitions extends TestInstrument implements En {
    public VpEntryPointStepDefinitions(){

        Given("^user (?:navigates to|had been on) the (.*) landing page(?: via URL)?( without passes the coach mark)?$", (String product, String flag) -> {
            ChoiceSelector.of(product.toLowerCase())
                    .when("pulsa prabayar", () -> {
                        bukalapak.iOSBasePage().openDeepLink("/bl/pulsa");
                        bukalapak.pulsaPrabayarLandingPage().validateOnPage();
                    })
                    .when("pulsa flash deal", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/flash-deal");
                      bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
                    })
                    .when("gramedia digital", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/appexchange/gramedia-digital");
                    })
                    .when("happyfresh", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/appexchange/happyfresh");
                    })
                    .when("listrik pascabayar", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/listrik-pln/tagihan-listrik");
                        bukalapak.postpaidElectricityPage().validateOnPage();
                    })
                    .when("tiket kereta", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/kereta-api");
                        bukalapak.travelTrainLandingPage().skipTravelInfoPopup();
                        bukalapak.travelTrainLandingPage().validateOnPage();
                    })
                    .when("hotel", () -> {
                        bukalapak.iOSBasePage().openDeepLink("https://www.bukalapak.com/hotel");
                        bukalapak.travelHotelLandingPage().validateOnPage();
                    })
                    .when("subscription", () -> {
                        bukalapak.homePage().isOnHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/paket-langganan");
                        bukalapak.travelSubscriptionLandingPage().validateOnPage();
                    })
                    .when("tiket pesawat", () -> {
                        bukalapak.iOSBasePage().openDeepLink("/tiket-pesawat");
                        bukalapak.tiketPesawatLandingPage().validateOnPage();
                    })
                    .when("gift card", () -> {
                        bukalapak.homePage().isOnHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/gift-card");
                        bukalapak.giftCardLandingPage().validateOnPage();
                    })
                    .when("tiket bus", () -> {
                        bukalapak.homePage().isOnHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/tiket-bus");
                        bukalapak.travelBusTicketPage().validateOnBusTicketingPage();
                    })
                    .when("kupon", () -> {
                        bukalapak.homePage().isOnHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/kupon");
                        bukalapak.kuponLandingPage().validateOnPage();
                    })
                    .when("event", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/tiket-event");
                        bukalapak.tiketEventLandingPage().validateOnPage();
                    })
                    .when("uang elektronik", () -> {
                        bukalapak.iOSBasePage().openDeepLink("/bl/uang-elektronik");
                        bukalapak.uangElektronikLandingPage().validateOnPage();
                    })
                    .when("paket data", () -> {
                      bukalapak.iOSBasePage().openDeepLink("/bl/paket-data");
                      bukalapak.paketDataLandingPage().validateOnPage();
                    })
                    .when("listrik prabayar", () -> {
                        bukalapak.listrikPrabayarLandingPage().checkDownTime();
                        bukalapak.iOSBasePage().openDeepLink("/listrik-pln/token-listrik");
                        bukalapak.listrikPrabayarLandingPage().validateOnPage();
                    })
                    .when("pulsa pascabayar", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/pulsa/pascabayar");
                        bukalapak.postpaidPhoneCreditPage().isDisplayed();
                    })
                    .when("asuransi bepergian", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/asuransi-mudik");
                      bukalapak.asuransiBepergianLandingPage().validateOnPage();
                    })
                    .when("pdam", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/air-pdam");
                        bukalapak.pdamPage().isDisplayed();
                    })
                    .when("asuransi motor", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/asuransi-motor");
                      bukalapak.asuransiMotorPage().validateOnPage();
                    })
                    .when("bpjs kesehatan", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/bpjs-kesehatan");
                        bukalapak.bpjsKesehatanPage().skipOnBoarding();
                        bukalapak.bpjsKesehatanPage().verifyPageDisplayed();
                    })
                    .when("multifinance", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/angsuran-kredit");
                      bukalapak.multifinancePage().isDisplayed();
                    })
                    .when("lead generator Asuransi Mobil", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/konsultasi-asuransi/asuransi-mobil?id=2");
                        bukalapak.leadGeneratorLandingPage().validateOnPage("Asuransi Mobil");
                    })
                    .when("telkom", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/telkom-indihome");
                        bukalapak.telkomPage().isDisplayed();
                    })
                    .when("kartu kredit", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/tagihan-kartu-kredit");
                        bukalapak.creditCardBillPage().isDisplayed();
                    })
                    .when("esamsat", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/bl/esamsat");
                        bukalapak.esamsatPage().chooseArea();
                        bukalapak.esamsatPage().verifyPageDisplayed();
                    })
                    .when("pajak pbb", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/pajak-daerah");
                        bukalapak.pajakDaerahPage().isDisplayed();
                    })
                    .when("non taglis", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/listrik-pln/non-taglis");
                        bukalapak.postpaidElectricityNonBillPage().skipOnBoarding();
                        bukalapak.postpaidElectricityNonBillPage().verifyPageDisplayed();
                    })
                    .when("addon-indihome", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/addon-indihome");
                        bukalapak.addonIndihomePage().validateOnPage();
                    })
                    .when("tv kabel", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/tv-kabel");
                        bukalapak.cableTvPage().validateonpage();
                    })
                    .when("ajukan kartu kredit", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/ajukan-kartu-kredit");
                        bukalapak.applyCCPage().validateOnPage();
                    })
                    .when("penerimaan negara", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/bukapajak");
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("premi asuransi", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/bayar-premi-asuransi");
                      bukalapak.premiAsuransiLandingPage().validateOnPage();
                    })
                    .when("bukaAsuransi", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/bukaasuransi");
                        bukalapak.bukaAsuransiPage().validateOnPage();
                    })
                    .when("Asuransi Kesehatan", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/asuransi-kesehatan");
                        bukalapak.asuransiKesehatanPage().validateOnPage();
                    })
                    .when("asuransi tambahan", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/asuransi-tambahan");
                        bukalapak.asuransiTambahanPage().validateOnPage();
                    })
                    .when("bukarumah", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/bukarumah");
                        bukalapak.bukaRumahLandingPage().validateOnBukaRumahLandingPage();
                    })
                    .when("asuransi sepeda", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/asuransi-sepeda");
                        bukalapak.asuransiSepedaLandingPage().validateOnPage();
                    })
                    .when("asuransi covid", () -> {
                      bukalapak.homePage().skipHomePage();
                      bukalapak.iOSBasePage().openDeepLink("/asuransi-covid-mandiri");
                      bukalapak.asuransiCovidLandingPage().userOnAsuransiCovidLandingPage();
                    })
                    .when("bukatabungan", () -> {
                        bukalapak.homePage().skipHomePage();
                        bukalapak.iOSBasePage().openDeepLink("/payment-services/digital-banking/landing");
                    })
                    .orElse(() -> fail(String.format("%s step not implemented yet", product)));
        });

        When("^user navigates to the (.*) landing page via category$", (String product) -> {
            bukalapak.homePage().clickLihatSemua("Categories");
            bukalapak.categoryPage().tapOnCategoryOrProduct("E-Voucher & Tiket");
            bukalapak.categoryPage().validateOnEvoucherCategoryPage();
            ChoiceSelector.of(product.toLowerCase())
                    .when("hotel", () -> {
                        bukalapak.categoryPage().tapOnCategoryOrProduct("Hotel");
                    })
                    .when("listrik prabayar", () -> {
                      bukalapak.categoryPage().tapOnCategoryOrProduct("Listrik Prabayar");
                    })
                    .when("uang elektronik", () -> {
                        bukalapak.categoryPage().tapOnCategoryOrProduct("Uang Elektronik");
                    })
                    .when("pulsa prabayar", () -> {
                        bukalapak.categoryPage().tapOnCategoryOrProduct("Pulsa Prabayar");
                    })
                    .when("event", () -> {
                        bukalapak.categoryPage().tapOnCategoryOrProduct("Event");
                    })
                    .when("gift card", () -> {
                        bukalapak.categoryPage().tapOnCategoryOrProduct("Gift Card");
                    })
                    .orElse(() -> fail(String.format("%s step not implemented yet", product)));
        });

        When("^user navigates to product landing page with deeplink \"([^\"]*)\"$", (String url) -> {
            bukalapak.homePage().skipHomePage();
            bukalapak.iOSBasePage().openDeepLink(url);
        });

        When("^user check (.*) (discount|cashback) voucher available$", (String product, String voucherType) -> {
            bukalapak.vpBasePage().validateVoucherValidationText(dotenv.get(product.toUpperCase()+"_VOUCHER_"+voucherType.toUpperCase()));
        });

        When("^user copies available promo code on the (?:.*) landing page$", () -> {
            bukalapak.newVpBasePage().copyAvailablePromoCode();
        });

        When("the promo code will have copied", () -> {
            bukalapak.iOSBasePage().verifyCopiedText(TransactionData.getAvailablePromoCode());
        });

        Given("^user validate the (.*) landing page$", (String product) -> {
            ChoiceSelector.of(product.toLowerCase())
                    .when("pulsa prabayar", () -> {
                        bukalapak.pulsaPrabayarLandingPage().validateOnPage();
                    })
                    .when("pulsa flash deal", () -> {
                        bukalapak.flashDealProductListPage().userOnProductListFlashDeal();
                    })
                    .when("gramedia digital", () -> {
                        bukalapak.iOSBasePage().openDeepLink("/appexchange/gramedia-digital");
                    })
                    .when("happyfresh", () -> {
                        bukalapak.iOSBasePage().openDeepLink("/appexchange/happyfresh");
                    })
                    .when("listrik pascabayar", () -> {
                        bukalapak.postpaidElectricityPage().validateOnPage();
                    })
                    .when("tiket kereta", () -> {
                        bukalapak.travelTrainLandingPage().skipTravelInfoPopup();
                        bukalapak.travelTrainLandingPage().validateOnPage();
                    })
                    .when("hotel", () -> {
                        bukalapak.travelHotelLandingPage().validateOnPage();
                    })
                    .when("subscription", () -> {
                        bukalapak.travelSubscriptionLandingPage().validateOnPage();
                    })
                    .when("tiket pesawat", () -> {
                        bukalapak.tiketPesawatLandingPage().validateOnPage();
                    })
                    .when("gift card", () -> {
                        bukalapak.giftCardLandingPage().validateOnPage();
                    })
                    .when("tiket bus", () -> {
                        bukalapak.travelBusTicketPage().validateOnBusTicketingPage();
                    })
                    .when("kupon", () -> {
                        bukalapak.kuponLandingPage().validateOnPage();
                    })
                    .when("event", () -> {
                        bukalapak.tiketEventLandingPage().validateOnPage();
                    })
                    .when("uang elektronik", () -> {
                        bukalapak.uangElektronikLandingPage().validateOnPage();
                    })
                    .when("paket data", () -> {
                        bukalapak.paketDataLandingPage().validateOnPage();
                    })
                    .when("bukamobil", () -> {
                        bukalapak.bukaMobilPage().onBukaMobilLandingPage();
                    })
                    .when("listrik prabayar", () -> {
                        bukalapak.listrikPrabayarLandingPage().validateOnPage();
                    })
                    .when("pulsa pascabayar", () -> {
                        bukalapak.postpaidPhoneCreditPage().isDisplayed();
                    })
                    .when("asuransi bepergian", () -> {
                        bukalapak.asuransiBepergianLandingPage().validateOnPage();
                    })
                    .when("pdam", () -> {
                        bukalapak.pdamPage().isDisplayed();
                    })
                    .when("asuransi motor", () -> {
                        bukalapak.asuransiMotorPage().validateOnPage();
                    })
                    .when("bpjs kesehatan", () -> {
                        bukalapak.bpjsKesehatanPage().verifyPageDisplayed();
                    })
                    .when("multifinance", () -> {
                        bukalapak.multifinancePage().isDisplayed();
                    })
                    .when("lead generator Asuransi Mobil", () -> {
                        bukalapak.leadGeneratorLandingPage().validateOnPage("Asuransi Mobil");
                    })
                    .when("telkom", () -> {
                        bukalapak.telkomPage().isDisplayed();
                    })
                    .when("kartu kredit", () -> {
                        bukalapak.creditCardBillPage().isDisplayed();
                    })
                    .when("esamsat", () -> {
                        bukalapak.esamsatPage().chooseArea();
                        bukalapak.esamsatPage().verifyPageDisplayed();
                    })
                    .when("pajak pbb", () -> {
                        bukalapak.pajakDaerahPage().isDisplayed();
                    })
                    .when("non taglis", () -> {
                        bukalapak.postpaidElectricityNonBillPage().skipOnBoarding();
                        bukalapak.postpaidElectricityNonBillPage().verifyPageDisplayed();
                    })
                    .when("addon-indihome", () -> {
                        bukalapak.addonIndihomePage().validateOnPage();
                    })
                    .when("tv kabel", () -> {
                        bukalapak.cableTvPage().validateonpage();
                    })
                    .when("ajukan kartu kredit", () -> {
                        bukalapak.applyCCPage().validateOnPage();
                    })
                    .when("penerimaan negara", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar paspor", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar KUA", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar denda tilang", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar pph final", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar ppn", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar pph 21", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar sbn", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("bayar bea", () -> {
                        bukalapak.penerimaanNegaraLandingPage().validateOnPenerimaanNegaraLandingPage();
                    })
                    .when("premi asuransi", () -> {
                        bukalapak.premiAsuransiLandingPage().validateOnPage();
                    })
                    .when("bukaAsuransi", () -> {
                        bukalapak.bukaAsuransiPage().validateOnPage();
                    })
                    .when("Asuransi Kesehatan", () -> {
                        bukalapak.asuransiKesehatanPage().validateOnPage();
                    })
                    .when("asuransi tambahan", () -> {
                        bukalapak.asuransiTambahanPage().validateOnPage();
                    })
                    .when("bukarumah", () -> {
                        bukalapak.bukaRumahLandingPage().validateOnBukaRumahLandingPage();
                    })
                    .when("asuransi sepeda", () -> {
                        bukalapak.asuransiSepedaLandingPage().validateOnPage();
                    })
                    .orElse(() -> fail(String.format("%s step not implemented yet", product)));
        });
    }
}
