package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.data.*;
import com.bukalapak.salad.util.LogUtil;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class CheckoutMarketplaceStepDefinitions extends TestInstrument implements En {
    private static String totalPrice, totalItemsPrice, totalShippingFee, totalInsuranceFee = "";
    private static Boolean isMixPayment = false;
    private String firstAddress = "";

    public CheckoutMarketplaceStepDefinitions() {

        // mtx
        And("^user checkout item from product detail page( with url \"([^\"]*)\")?$", (String url) -> {
            if (url == null) {
                bukalapak.checkoutMarketplacePage().openDeepLink(dotenv.get("PRODUCT_LINK"));
            } else {
                bukalapak.checkoutMarketplacePage().openDeepLink(url);
            }
            bukalapak.productDetailsPage().userOnProductDetailPage();
            bukalapak.productDetailsPage().tapBuyNowButton();
        });

        And("^user redirected to checkout page$", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        And("user validate item from tray popup atc on checkout page", () -> {
            bukalapak.checkoutMarketplacePage().validateItemFromTrayATC();
        });

        And("^user select \"([^\"]*)\" for payment method( using \"([^\"]*)\")? ([^\"]*)?$", (String method, String service, String isUseMixPayment) -> {
            bukalapak.checkoutMarketplacePage().selectPaymentMethod(method, service);
            bukalapak.checkoutMarketplacePage().usePaymentMethod();
            if (isUseMixPayment.equals("without mixpayment")) {
                isMixPayment = false;
                bukalapak.checkoutMarketplacePage().untickAllMixPayment();
            } else if (isUseMixPayment.equals("with mixpayment")) {
                isMixPayment = true;
            }
        });

        And("^user go to summary checkout section$", () -> {
            bukalapak.checkoutMarketplacePage().goToCheckoutSummary();
        });

        And("^user click change mixpayment button$", () -> {
            bukalapak.checkoutMarketplacePage().clickChangeMixPaymentButton();
        });

        And("^user set amount Rp.\"([^\"]*)\" mixpayment using (DANA|Saldo)$", (String amount, String type) -> {
            bukalapak.checkoutMarketplacePage().setMixpayment(amount, type);
        });


        And("^user set amount mixpayment is (equal|greater than|less than) total payment using (DANA|Saldo)$", (String condition, String type) -> {
            Integer mixpaymentInput = bukalapak.checkoutMarketplacePage().setMixpaymentInput(condition, bukalapak.checkoutMarketplacePage().convertPriceToInt(MtxData.getTotalPaymentCheckout()));
            bukalapak.checkoutMarketplacePage().setMixpayment(mixpaymentInput.toString(), type);
        });

        And("^user (tick|untick) mixpayment using (DANA|Saldo) on mixpayment modal page$", (String state, String type) -> {
            bukalapak.checkoutMarketplacePage().clickCheckboxMixPayment(state, type);
        });

        And("^user (tick|untick) mixpayment using (DANA|Saldo)$", (String state, String type) -> {
            bukalapak.checkoutMarketplacePage().clickCheckboxMixPaymentOnCheckout(state, type);
        });

        And("^user click save mixpayment button on mixpayment modal page$", () -> {
            bukalapak.checkoutMarketplacePage().tapElement("checkout_marketplace_mixpayment_screen_save");
        });

        And("^user( can't)? click pay with selected payment method$", (String flag) -> {
            bukalapak.checkoutMarketplacePage().tapPayButton();
        });

        And("^user( not)? redirected to successful payment page with method \"([^\"]*)\"$", (String flag, String method) -> {
            bukalapak.checkoutMarketplacePage().verifyConfirmationPage(method);
        });

        And("^user click see info detail invoice$", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().clickLihatTagihanPembayaran();
        });

        And("^user close 3rd party webview page$", () -> {
            bukalapak.checkoutMarketplacePage().close3rdPartyWebview();
        });

        Then("^user verify total product price checkout match with product price product detail$", () -> {
            bukalapak.checkoutMarketplacePage().verifyTotalProductPrice();
        });

        Then("user verify total product price checkout match with product price cart", () -> {
            bukalapak.checkoutMarketplacePage().verifyTotalProductPriceFromCart();
        });

        Then("^user verify mixpayment (DANA|Saldo) (ticked|unticked)$", (String type, String state) -> {
            bukalapak.checkoutMarketplacePage().verifyMixpaymentTicked(type, state);
        });

        Then("^user redirected 3rd party webview page with method \"([^\"]*)\"", (String service) -> {
            bukalapak.checkoutMarketplacePage().verify3rdPartyWebview(service);
        });

        Then("^user verify deduction mixpayment (DANA|Saldo) in summary checkout section$", (String type) -> {
            bukalapak.checkoutMarketplacePage().verifyMixPaymentDeduction(type);
        });

        Then("^user verify mix payment dana amount equals to mix payment saldo amount$", () -> {
            bukalapak.checkoutMarketplacePage().verifyWithOtherMixPaymentDeduction();
        });

        Then("^user verify message \"([^\"]*)\" bellow mixpayment (DANA|Saldo) input section$", (String message, String type) -> {
            bukalapak.checkoutMarketplacePage().verifyMixPaymentValidation(message, type);
        });
        // end mtx

        Given("user is in \"checkout_marketplace\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        Given("user is in \"checkout\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        Given("user is in \"checkout_shipment\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        Given("user tap on payment method button", () -> {
            bukalapak.checkoutMarketplacePage().tapOnPaymentMethodBtn();
        });

        Given("user is in \"BukaBantuan Indicated Dropshipper\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnBukaBantuanIndicatedDropshipperPage();
        });

        Given("user is in \"mixpayment input\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnMixPaymentInputPage();
        });

        When("user choose \"([^\"]*)\" with \"([^\"]*)\" for payment method in Checkout Marketplace Page", (String paymentMethod, String paymentChoose) -> {
            bukalapak.checkoutMarketplacePage().chooseNewPaymentMethod(paymentMethod, paymentChoose);
            bukalapak.checkoutMarketplacePage().usePaymentMethod();
        });

        And("user check current address in Checkout Marketplace Page", () -> {
            firstAddress = bukalapak.checkoutMarketplacePage().getAddress();
            TransactionData.setAddress(firstAddress);
        });

        And("user check current Rincian Harga in Checkout Marketplace Page", () -> {
            totalShippingFee = bukalapak.checkoutMarketplacePage().getTotalShippingFee();
            totalItemsPrice = bukalapak.checkoutMarketplacePage().getTotalItemsFee();
            totalInsuranceFee = bukalapak.checkoutMarketplacePage().getTotalAllVisibleInsurance();
            TransactionData.setTotalItemsPrice(totalItemsPrice);
            TransactionData.setTotalShippingFee(totalShippingFee);
            TransactionData.setInsuranceFee(totalInsuranceFee);
        });

        And("user verify Total Pembayaran in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().verifyTransactionTotalPembayaran(isMixPayment);
        });

        And("user check current totalPrice in Checkout Marketplace Page", () -> {
            if (isMixPayment) {
                totalPrice = bukalapak.checkoutMarketplacePage().getTotalPrice(true, totalItemsPrice, totalShippingFee);
            } else {
                totalPrice = bukalapak.checkoutMarketplacePage().getTotalPrice(false, totalItemsPrice, totalShippingFee);
            }
            TransactionData.setTotalPrice(totalPrice);
        });

        And("^user choose \"([^\"]*)\" as payment method in Checkout Marketplace Page", (String payment) -> {
            if (payment.equals("Saldo")) {
                bukalapak.checkoutMarketplacePage().choosePaymentMethodSaldo();
            } else {
                bukalapak.checkoutMarketplacePage().choosePaymentMethod(payment);
            }
        });

        Then("^user must redirected to Checkout page$", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        And("^user tap Pilih Metode Pembayaran$", () -> {
            bukalapak.checkoutMarketplacePage().isOnPaymentMethodPage();
        });

        And("user click Bayar in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().clickBayarButton();
        });

        And("user click dropshipper checkbox in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().tapDropshipperCheckbox();
        });

        And("user click Bayar sebagian dengan Bukadompet or Credits in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().tapBayarSebagianBukadompetOrCredits();
            isMixPayment = true;
        });

        Then("user verify rounded amount because total belanja less than 10k in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().verifyRoundedAmount();
        });

        Then("user verify no rounded amount on checkout page in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().verifyNoRoundedAmount();
        });

        And("user type \"([^\"]*)\" for dropshipper in Checkout Marketplace Page", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().verifyDropshipperPopup();
            bukalapak.checkoutMarketplacePage().dropshipperAs(arg0);
        });

        And("user add catatan \"([^\"]*)\" for pelapak in Checkout Marketplace Page", (String notes) -> {
            bukalapak.checkoutMarketplacePage().tapCatatanLink();
            bukalapak.checkoutMarketplacePage().verifyCatatanPopup();
            bukalapak.checkoutMarketplacePage().writeCatatanForSeller(notes);
        });

        And("user chooses \"([^\"]*)\" as the courier in Checkout Marketplace Page", (String courier) -> {
            bukalapak.checkoutMarketplacePage().chooseCourier(courier);
        });

        And("user click Bayar Sekarang Button in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().tapBayarSekarangButton();
        });

        Then("user is in cimb page and quit from transaction page in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().verifyOnCimbPage();
            bukalapak.checkoutMarketplacePage().quitFromCimbPage();
        });

        And("user click Bayar Sekarang Button with VA payment method", () -> {
            bukalapak.checkoutMarketplacePage().tapBayarSekarangButtonWithVAPaymentMethod();
        });

        When("user memorizes the total payment", () -> {
            bukalapak.checkoutMarketplacePage().setTransactionData();
        });

        And("user check current Address", () -> {
            bukalapak.checkoutMarketplacePage().getCurrentAddress();
        });

        And("user check shipping price", () -> {
            bukalapak.checkoutMarketplacePage().getCurrentShippingFee();
        });

        And("user check new address", () -> {
            bukalapak.checkoutMarketplacePage().getNewAddress();
        });

        Then("validate app will open back to checkout page", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        And("validate address is changed to be selected address", () -> {
            bukalapak.checkoutMarketplacePage().validateChangingAddress();
        });

        And("validate shipping price will adjust to new shipping price based on new address selected", () -> {
            bukalapak.checkoutMarketplacePage().validateShippingFeeAddress();
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });


        When("^user click courier category \"([^\"]*)\" and service \"([^\"]*)\"$", (String category, String courierName) -> {
            bukalapak.checkoutMarketplacePage().tapCategorySection();
            bukalapak.chooseCategoryPage().userOnChooseCategoryPage();
            bukalapak.chooseCategoryPage().chooseCategory(category);
            bukalapak.checkoutMarketplacePage().tapCourierSection();
        });

        And("user verify select courier", () -> {
            bukalapak.checkoutMarketplacePage().tapOnKurirDropdown();
        });

        Then("validate Jasa Pengiriman  is updated with \"([^\"]*)\" as courier", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().validateChangingCourier(arg0);
        });

        Then("validate shipping fee is \"([^\"]*)\"", (String fee) -> {
            bukalapak.checkoutMarketplacePage().validateShippingFee(fee);
        });
        And("validate new estimate prices are shown correctly", () -> {
            bukalapak.checkoutMarketplacePage().validateShippingfeeIsShown();
        });

        And("validate \"([^\"]*)\" as estimate time arrived is shown", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().validateEstimateTime(arg0);
        });

        And("validate data new address \"([^\"]*)\" is saved successfully", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutMarketplacePage().validateNewAddressisadded(arg0);
        });

        When("user click dropdown product total to be \"([^\"]*)\"", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().addTotalProductAlchemy(Integer.parseInt(arg0));
        });

        And("validate shipping fee is not changed", () -> {
            bukalapak.checkoutMarketplacePage().validateShippingFeeAfterChangingQuantityAlchemy(false);
        });

        And("validate shipping fee is increased", () -> {
            bukalapak.checkoutMarketplacePage().validateShippingFeeAfterChangingQuantityAlchemy(true);
        });

        And("user save price and courier", () -> {
            bukalapak.checkoutMarketplacePage().setHargaPerBarang(Integer.valueOf(bukalapak.checkoutMarketplacePage().getPriceGoods()));
        });

        Then("user is in BCA KlikPay page and quit from transaction page in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().verifyOnBCAKlikPayPage();
            bukalapak.checkoutMarketplacePage().quitFromBCAKlikPayPage();
        });

        When("^(.*) user should see Pembeli Prioritas section in checkout$", (String status) -> {
            bukalapak.checkoutMarketplacePage().checkPembeliPrioritasSection(status);
        });

        When("^user click Pembeli Prioritas checkbox for (usage|cross-selling)$", (String transactionType) -> {
            bukalapak.checkoutMarketplacePage().clickPembeliPrioritasCheckbox(transactionType);
        });

        Then("^user verify info cashback from Pembeli Prioritas$", () -> {
            bukalapak.checkoutMarketplacePage().checkPembeliPrioritasInfoCashback();
        });

        When("user click shipping address in Checkout Marketplace Page", () -> {
            bukalapak.checkoutMarketplacePage().clickAddressContainer();
        });

        Then("^user verify successfully \"([^\"]*)\" address$", (String arg0) -> {
            bukalapak.checkoutMarketplacePage().validateNotificationSuccessfullAddAddress(arg0);
        });

        When("user change shipping address to created address", () -> {
            bukalapak.checkoutMarketplacePage().changeShippingAddress(CartData.getExpectedName());
        });

        Then("user verify changed address selected as shipping address$", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutMarketplacePage().validateChangeShippingAddress(CartData.getExpectedTitle(), CartData.getExpectedAddress());
        });

        Then("user back to checkout from alamat list page$", () -> {
            bukalapak.checkoutMarketplacePage().backToCheckoutAlchemyPage();
        });

        And("user click Bayar Dengan \"([^\"]*)\" Button in Checkout Marketplace Page", (String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().tapBayarDenganButton(paymentMethod);
        });

        Then("user verify only valid item appear on checkout alchemy page", () -> {
            bukalapak.checkoutMarketplacePage().validateItemOnCheckout(CartData.getCartDetails());
        });

        Then("user verify only selected item appear on checkout alchemy page", () -> {
            bukalapak.checkoutMarketplacePage().validateItemOnCheckout(CartData.getSelectedItemOnCart());
        });

        Then("user should not see mix payment on payment method", () -> {
            bukalapak.checkoutMarketplacePage().validateMixpaymentNotAppear();
        });

        When("user set total price to above minimum payment", () -> {
            bukalapak.checkoutMarketplacePage().addItemAboveMinimumPayment();
        });

        When("user verify total price is changed", () -> {
            bukalapak.checkoutMarketplacePage().validatePriceIsChanged(TransactionData.getTotalPayment());
        });

        And("user verify Pembeli Prioritas error info because (.*)", (String errorInfo) -> {
            bukalapak.checkoutMarketplacePage().checkErrorPriority(errorInfo);
        });

        When("user verify default selected courier", () -> {
            bukalapak.checkoutMarketplacePage().validateDefaultCourier();
        });

        When("user tap on courier", () -> {
            bukalapak.checkoutMarketplacePage().tapCourierArea();
        });

        Then("user verify default selected courier is on the first row", () -> {
            bukalapak.checkoutMarketplacePage().validateFirstRecommendationCourier(TransactionData.getCourierRecommendation());
        });

        Then("user should see catalog name in checkout note section", () -> {
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutMarketplacePage().validateCatalogTitleOnCheckout(WOWData.getCatalogTitle());
        });

        When("user change courier to \"([^\"]*)\" and \"([^\"]*)\"", (String group, String courier) -> {
            bukalapak.checkoutMarketplacePage().selectCourier(group, courier);
        });

        When("user tap Pilih button", () -> {
            bukalapak.checkoutMarketplacePage().tapPilihButton();
        });

        When("user verify \"([^\"]*)\" selected as courier", (String courier) -> {
            bukalapak.checkoutMarketplacePage().validateSelectedCourier(courier);
        });

        When("user verify courier fee", () -> {
            bukalapak.checkoutMarketplacePage().validateCourierFee();
        });

        When("user change address to second address", () -> {
            bukalapak.checkoutMarketplacePage().changeToSecondAddress();
        });

        When("user verify courier fee is changed", () -> {
            bukalapak.checkoutMarketplacePage().validateCourierFeeIsChange(TransactionData.getAddress());
        });

        When("^user verify transaction info for (.*)$", (String priorityTransaction) -> {
            bukalapak.checkoutMarketplacePage().validatePriorityBuyerMarketplaceTransaction(priorityTransaction);
        });

        When("^user check Saldo as chosen payment$", () -> {
            bukalapak.checkoutMarketplacePage().checkChosenPaymentSaldo();
        });

        And("validate shipping fee in courier list and ongkos kirim", () -> {
            bukalapak.checkoutMarketplacePage().validateShippingFeeAndOngkir();
        });

        And("user see info about Priority Buyer expire info", () -> {
            bukalapak.checkoutMarketplacePage().checkPriorityBuyerExpiredInfo();
        });

        When("^user set total product to be ([0-9]*)$", (String qty) -> {
            bukalapak.checkoutMarketplacePage().setQuantityProduct(Integer.parseInt(qty));
        });

        Then("^user set total price to below (DANA|Credits)$", (String payment) -> {
            bukalapak.apiCall().retrievePaymentsInfo();
            bukalapak.apiCall().retrievePaymentsInfoVirtualAccount();
            bukalapak.checkoutMarketplacePage().setSelectedPaymentMethodForMixPayment();
            bukalapak.checkoutMarketplacePage().setTotalPrice(
                    payment.equals("DANA")
                            ? UserData.getOwnedDANA() - TransactionData.roundingCounterAmount
                            : UserData.getOwnedCredits() - TransactionData.roundingCounterAmount,
                    false);
        });

        When("^user set total price to (above|below) DANA plus Credits plus Minimum on Checkout Alchemy$", (String state) -> {
            bukalapak.apiCall().retrievePaymentsInfo();
            bukalapak.apiCall().retrievePaymentsInfoVirtualAccount();
            bukalapak.checkoutMarketplacePage().setSelectedPaymentMethodForMixPayment();
            bukalapak.checkoutMarketplacePage().setTotalPrice(
                    state.equals("above")
                            ? UserData.getOwnedDANA() + UserData.getOwnedCredits() + TransactionData.roundingCounterAmount + TransactionData.getMinimumPaymentForMixPayment()
                            : UserData.getOwnedDANA() + UserData.getOwnedCredits() - TransactionData.roundingCounterAmount + TransactionData.getMinimumPaymentForMixPayment(),
                    state.equals("above"));

        });

        When("^user set total price to (above|below) DANA plus Credits on Checkout Alchemy$", (String state) -> {
            bukalapak.apiCall().retrievePaymentsInfo();
            bukalapak.apiCall().retrievePaymentsInfoVirtualAccount();
            bukalapak.checkoutMarketplacePage().setSelectedPaymentMethodForMixPayment();
            bukalapak.checkoutMarketplacePage().setTotalPrice(
                    state.equals("above")
                            ? UserData.getOwnedDANA() + UserData.getOwnedCredits() + TransactionData.roundingCounterAmount
                            : UserData.getOwnedDANA() + UserData.getOwnedCredits() - TransactionData.roundingCounterAmount,
                    state.equals("above"));
        });

        When("^user (tick|untick) mix payment (DANA|Credits)$", (String tick, String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().setMultipleMixPayment(paymentMethod, tick);
        });

        Then("^user verify mix payment (DANA|Credits) fully used$", (String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmount(
                    paymentMethod, paymentMethod.equals("DANA")
                            ? UserData.getOwnedDANA()
                            : UserData.getOwnedCredits());
        });

        Then("^user verify total price is (not )?decreased by (DANA|Credits)$", (String state, String payment) -> {
            bukalapak.checkoutMarketplacePage().validateTotalPrice(
                    TransactionData.getExpectedTotalBill() - TransactionData.getUsedCredits() - TransactionData.getUsedDANA()
            );
        });

        Then("^user verify mix payment (DANA|Credits) not used$", (String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentNotUsed(paymentMethod);
        });

        Then("^user verify mix payment (DANA|Credits|Saldo)? automatically adjusted to minimum amount$", (String paymentMethod) -> {
            bukalapak.apiCall().retrievePaymentsInfo();
            bukalapak.apiCall().retrievePaymentsInfoVirtualAccount();
            bukalapak.checkoutMarketplacePage().setSelectedPaymentMethodForMixPayment();
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmount(paymentMethod, TransactionData.getExpectedTotalBill() - TransactionData.getMinimumPaymentForMixPayment());
        });

        Then("^user verify mix payment (DANA|Credits) is halved$", (String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmount(
                    paymentMethod, paymentMethod.equals("DANA")
                            ? TransactionData.getUsedDANA() / 2
                            : TransactionData.getUsedCredits() / 2);
        });

        Then("^user verify mix payment (DANA|Credits) amount equals to mix payment (DANA|Credits) amount$", (String paymentMethod, String counter) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmount(
                    paymentMethod, counter.equals("DANA")
                            ? TransactionData.getUsedDANA()
                            : TransactionData.getUsedCredits());
        });

        Then("user verify \"([^\"]*)\" snack bar (does not )?exist", (String snackBarMsg, String exist) -> {
            bukalapak.checkoutMarketplacePage().validateSnackbarMsg(snackBarMsg, exist);
        });

        Then("^user should see mix payment (DANA|Credits) on set mix payment screen is (not )?checked$", (String payment, String state) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentStateOnSetMixPaymentScreen(payment, state);
        });

        When("^user (tick|untick) mix payment (DANA|Credits) on set mix payment screen$", (String state, String payment) -> {
            bukalapak.checkoutMarketplacePage().setMixPaymentOnSetMixPaymentScreen(payment, state);
        });

        Then("^user verify no mix payment (DANA|Credits)$", (String payment) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentNotAvailable(payment);
        });

        Then("^user (reduce|increase) item quantity by (\\d)$", (String state, String qty) -> {
            if (state.equals("reduce")) {
                bukalapak.checkoutMarketplacePage().updateQuantityItem("-", Integer.parseInt(qty));
            } else if (state.equals("increase")) {
                bukalapak.checkoutMarketplacePage().updateQuantityItem("+", Integer.parseInt(qty));
            }
        });

        Then("^user verify mix payment (DANA|Credits) (ticked|unticked)$", (String payment, String state) -> {
            Boolean mixState = state.equals("ticked");
            bukalapak.checkoutMarketplacePage().validateMultipleMixPaymentState(payment, mixState);
        });

        When("^user set quantity to (\\d+) on checkout alchemy$", (Integer qty) -> {
            bukalapak.checkoutMarketplacePage().setQuantityProduct(qty);
        });

        When("^user clear mix payment amount on mix payment (DANA|Credits)$", (String payment) -> {
            bukalapak.checkoutMarketplacePage().clearInputMixPaymentOnSetMixPayment(payment);
        });

        Then("^user should see mix payment (DANA|Credits) amount is blank$", (String payment) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmountOnSetMixPayment(payment, 0);
        });

        When("^user input mix payment (DANA|Credits) to total (DANA|Credits)$", (String payment, String amount) -> {
            bukalapak.checkoutMarketplacePage().inputMixPaymentOnSetMixPayment(
                    payment, amount.equals("DANA")
                            ? UserData.getOwnedDANA()
                            : UserData.getOwnedCredits());
        });

        Then("^user verify mix payment (DANA|Credits) amount is inputted correctly$", (String payment) -> {
            bukalapak.checkoutMarketplacePage().validateMixPaymentAmount(
                    payment, payment.equals("DANA")
                            ? TransactionData.getUsedDANA()
                            : TransactionData.getUsedCredits());
        });

        Then("user uncheck Delivery Protection checkbox", () -> {
            bukalapak.checkoutMarketplacePage().uncheckDeliveryProtectionCheckBox();
        });

        Then("^user verify catatan pelapak contains \"([^\"]*)\" check (is|is not) updated$", (String sellerNote,
                                                                                               String actionType) -> {
            switch (actionType) {
                case "is": {
                    bukalapak.checkoutMarketplacePage().verifyCatatanPelapakUpdated(sellerNote);
                    break;
                }
                case "is not": {
                    String expectedSellerNote = TransactionData.getSavedSellerNotes().get(sellerNote);
                    String notExpectedSellerNote = TransactionData.getInputtedSellerNotes().get(sellerNote);
                    bukalapak.checkoutMarketplacePage().verifyCatatanPelapakNotUpdated(expectedSellerNote, notExpectedSellerNote);
                    break;
                }
                default:
                    LogUtil.info("action type " + actionType + " is not defined");
                    break;
            }
        });

        And("^user exit from checkout_marketplace page$", () -> {
            bukalapak.checkoutMarketplacePage().exitFromCheckout();
        });

        And("^user set catatan pelapak to \"([^\"]*)\" for seller \"([^\"]*)\"( but not saved)?$", (String sellerNote, String sellerName, String isNotSaved) -> {
            String savedSellerNote = TransactionData.getSavedSellerNotes().get(sellerName);
            if (savedSellerNote == null) {
                savedSellerNote = "";
            }
            // Determine which button to be clicked based on previously saved seller note
            if (savedSellerNote.isEmpty()) {
                bukalapak.checkoutMarketplacePage().tapCatatanPelapak(sellerName);
            } else {
                bukalapak.checkoutMarketplacePage().tapUbahCatatanPelapak(sellerName);
            }

            bukalapak.checkoutMarketplacePage().setCatatanPelapak(sellerNote, sellerName);

            // Determine which button to be clicked based on the user want save this seller note or not
            if (isNotSaved == null) {
                bukalapak.checkoutMarketplacePage().tapSaveCatatanPelapak();
            } else {
                bukalapak.checkoutMarketplacePage().closeCatatanPelapakSheet();
            }
        });

        Then("user uncheck Mix Payment checkbox", () -> {
            bukalapak.checkoutMarketplacePage().uncheckMixPaymentCheckbox();
        });

        And("user check \"([^\"]*)\" Virtual Account number in Instruction Page", (String bank) -> {
            bukalapak.checkoutMarketplacePage().verifyBankVANumber(bank);
        });

        And("user click Ubah Metode Pembayaran in Instruction Page", () -> {
            bukalapak.checkoutMarketplacePage().tapUbahPembayaran();
        });

        //Old Checkout - by DANA
        And("user choose \"([^\"]*)\" payment method in old checkout page", (String paymentMethod) -> {
            bukalapak.checkoutMarketplacePage().chooseOldPaymentMethod(paymentMethod);
        });

        And("user click Bayar in old checkout page", () -> {
            bukalapak.checkoutMarketplacePage().tapBayar();
        });

        And("user tap lanjutkan when payment method va already have bill", () -> {
            bukalapak.checkoutMarketplacePage().tapLanjutkanPembayaran();
        });
        //end by DANA

        // Begin - VP Insurance
        And("user passed the insurance coach mark", () -> {
            bukalapak.checkoutMarketplacePage().tapOnNantiSajaButton();
        });

        And("user click pelajari for information insurance", () -> {
            bukalapak.checkoutMarketplacePage().tapOnPelajariButton();
            bukalapak.checkoutMarketplacePage().tapOnMengertiButton();
        });
        //End - VP Insurance

        //voucher section
        And("user scroll up to entry point payment method", () -> {
            bukalapak.checkoutMarketplacePage().scrollUpToPaymentMethodPage();
        });

        And("^user tap Bayar Sekarang button", () -> {
            bukalapak.checkoutMarketplacePage().clickBayarButton();
        });

        When("user create transaction and check invoice", () -> {
            bukalapak.checkoutMarketplacePage().clickBayarButton();
            bukalapak.checkoutMarketplacePage().tapOnLihatTagihanButton();
        });

        And("^user see voucher pelapak \"([^\"]*)\"$", (String pelapakName) -> {
            bukalapak.checkoutMarketplacePage().validatePelapak(pelapakName);
        });

        And("^user (can not )?select voucher \"([^\"]*)\" from list$", (String appear, String voucherName) -> {
            if (appear == null) {
                bukalapak.checkoutMarketplacePage().selectVoucherku(true, voucherName);
            } else {
                bukalapak.checkoutMarketplacePage().selectVoucherku(false, voucherName);
            }
        });

        When("^user has selected voucher with voucher \"([^\"]*)\" on seller \"([^\"]*)\"$",
                (String voucher, String seller) -> {
                    bukalapak.checkoutMarketplacePage().selectVoucherList(voucher, seller);
                });

        And("user has applied all selected voucher", () -> {
            bukalapak.checkoutMarketplacePage().tapOnUseOnSelectedVoucher();
        });

        Then("user should see all vouchers benefit on checkout marketplace", (DataTable table) -> {
            bukalapak.checkoutMarketplacePage().validateVouchersBenefit(table);
        });

        Then("user should see all vouchers benefit on installment page", (DataTable table) -> {
            bukalapak.checkoutMarketplacePage().validateVouchersBenefitCC(table);
        });

        And("^user tap voucher field$", () -> {
            bukalapak.checkoutMarketplacePage().tapVoucherField();
        });

        And("^user type voucher code \"([^\"]*)\"$", (String voucherName) -> {
            bukalapak.checkoutMarketplacePage().typeVoucherCode(voucherName);
        });

        And("^user type voucher \"([^\"]*)\" on voucher field$", (String voucherName) -> {
            bukalapak.checkoutMarketplacePage().typeVoucherWithoutUsed(voucherName);
        });

        When("^user has inputted voucher bukalapak with \"([^\"]*)\"$", (String code) -> {
            bukalapak.checkoutMarketplacePage().setVoucherBukalapak(code);
        });

        And("^user close pup up voucher use X button$", () -> {
            bukalapak.checkoutMarketplacePage().closePopUpVoucherCode();
        });

        And("^user is shown error message voucher \"([^\"]*)\"$", (String voucherMessage) -> {
            bukalapak.checkoutMarketplacePage().validateVoucherMessage(voucherMessage);
        });

        And("^user is shown success message voucher \"([^\"]*)\"$", (String voucherMessage) -> {
            bukalapak.checkoutMarketplacePage().validateVoucherMessage(voucherMessage);
        });

        And("^user validate message voucher \"([^\"]*)\"$", (String voucherMessage) -> {
            bukalapak.checkoutMarketplacePage().validateVoucherMessage(voucherMessage);
        });

        And("^user see message \"([^\"]*)\"$", (String messageText) -> {
            bukalapak.checkoutMarketplacePage().validateMessage(messageText);
        });

        Then("^user should (not )?see deduction price from voucher", (String appear) -> {
            if (appear == null) {
                bukalapak.checkoutMarketplacePage().validateDeductedVoucher(true);
            } else {
                bukalapak.checkoutMarketplacePage().validateDeductedVoucher(false);
            }
        });

        Then("^user (not )?see deduction price from voucher and pay in cc page", (String appear) -> {
            if (appear == null) {
                bukalapak.checkoutMarketplacePage().validateDeductedVoucherCC(true);
            } else {
                bukalapak.checkoutMarketplacePage().validateDeductedVoucherCC(false);
            }
        });

        And("^user see pop up continue pay without voucher$", () -> {
            bukalapak.checkoutMarketplacePage().validatePopUpWithoutVoucher();
        });

        And("^user clear voucher text field use X button$", () -> {
            bukalapak.checkoutMarketplacePage().clearVoucherTextField();
        });

        And("^user close pop up input voucher$", () -> {
            bukalapak.checkoutMarketplacePage().closePopUpVoucher();
        });

        And("^user is shown price detail of Potongan voucher Bukalapak as \"([^\"]*)\"$", (String potonganHarga) -> {
            bukalapak.checkoutMarketplacePage().verifyPotonganVoucherApplied(potonganHarga);
        });

        And("user applied all selected voucher", () -> {
            bukalapak.checkoutMarketplacePage().tapOnMultiPakai();
        });

        //end voucher

        //Investment
        When("user scroll down to BukaEmas autoinvest section in checkout page", () -> {
            bukalapak.checkoutMarketplacePage().scrollToBukaeEmasAutoInvestSection();
        });

        And("user validate BukaEmas autoinvest checkbox", () -> {
            bukalapak.checkoutMarketplacePage().validateBukaEmasAutoInvestCheckbox();
        });

        And("user checked autoinvest checkbox in checkout page", () -> {
            bukalapak.checkoutMarketplacePage().tapAutoInvestCheckbox();
        });

        Then("user validate BukaEmas autoinvest amount in checkout page", () -> {
            bukalapak.checkoutMarketplacePage().validateBukaEmasAutoInvest();
        });

        When("user open direct checkout link \"([^\"]*)\"", (String link) -> {
            bukalapak.checkoutMarketplacePage().goToCheckoutUsingDirectCheckoutLink(link);
        });

        Then("user validate product \"([^\"]*)\" with price \"([^\"]*)\"", (String name, String price) -> {
            bukalapak.checkoutMarketplacePage().validateItemPrice(name, price);
        });

        Then("user is in instruksi pembayaran page with payment method \"([^\"]*)\"$", (String method) -> {
            bukalapak.checkoutMarketplacePage().userIsOnInstruksiPembayaranPage();
            bukalapak.checkoutMarketplacePage().verifyConfirmationPage(method);
        });

        When("^user go to rincian harga section$", () -> {
            bukalapak.checkoutMarketplacePage().scrollToRincianHarga();
        });

        Then("user tap voucher section", () -> {
            bukalapak.checkoutMarketplacePage().tapVoucherSection();
        });

        Then("user clear voucher text field", () -> {
            bukalapak.checkoutMarketplacePage().clearVoucherField();
        });

        Then("user visit checkout link \"([^\"]*)\" succesfully", (String link) -> {
            bukalapak.checkoutMarketplacePage().verifyDirectCheckoutSuccess(link);
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        Then("user go back to checkout page", () -> {
            bukalapak.checkoutMarketplacePage().tapBackButton();
        });

        Then("user scroll up to quantity", () -> {
            bukalapak.checkoutMarketplacePage().goToQuantitySection();
        });

        Then("user type voucher \"([^\"]*)\" and applied", (String voucherCode) -> {
            bukalapak.checkoutMarketplacePage().typeVoucherAndApplied(voucherCode);
        });

        Then("^user validate voucher applied message \"([^\"]*)\"$", (String voucherMessage) -> {
            bukalapak.checkoutMarketplacePage().validateAppliedVoucherMessage(voucherMessage);
            bukalapak.checkoutMarketplacePage().tapPakaiMultipleVoucher();
        });

        Then("^validate biaya pelayanan COD \"([^\"]*)\"$", (String biayaPelayan) -> {
            bukalapak.checkoutMarketplacePage().validateBiayaPelayanCOD(biayaPelayan);
        });

        Then("user validate placeholder buyer notes", () -> {
            bukalapak.checkoutMarketplacePage().validatePlaceholderBuyerNotes();
        });

        Then("^user select \"([^\"]*)\" as courier$", (String courier) -> {
            bukalapak.checkoutMarketplacePage().selectCourier(courier);
        });
        Then("user is in \"checkout algebra MP\" page", () -> {
            bukalapak.checkoutMarketplacePage().userOnAlgebraMPCheckoutPage();
        });

        When("^user input non binding credit card on \"([^\"]*)\"$", (String page) -> {
            bukalapak.checkoutMarketplacePage().typeOnUnbindingCardField(dotenv.get("CC_NUMBER"), dotenv.get("CC_CVV"), page);
        });

        When("^user input binding credit card on \"([^\"]*)\"$", (String page) -> {
            bukalapak.checkoutMarketplacePage().typeOnBindingCardField(dotenv.get("CC_CVV"), page);
        });
    }
}
