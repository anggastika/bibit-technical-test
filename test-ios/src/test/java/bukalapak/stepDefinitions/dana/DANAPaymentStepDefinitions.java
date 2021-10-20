package bukalapak.stepDefinitions.dana;

import bukalapak.TestInstrument;
import bukalapak.data.DANAData;
import cucumber.api.java8.En;

public class DANAPaymentStepDefinitions extends TestInstrument implements En {

    public DANAPaymentStepDefinitions() {

        Given("user is in \"DANA_binding\" page", () -> {
            bukalapak.danaBindingPage().userOnDANABindingPage();
        });

        And("^user select DANA as payment method$", () -> {
            bukalapak.danaPaymentPage().selectDANAasPaymentMethod();
        });

        And("^user select DANA as a payment$", () -> {
            bukalapak.danaPaymentPage().selectDANAasPayment();
        });

        And("^user select DANA at Metode Pembayaran page$", () -> {
            bukalapak.danaPaymentPage().selectDANAinMetodePembayaran();
            bukalapak.danaPaymentPage().verifyDANAPaymentSelectionScreen();
        });

        And("^user select DANA unbind as payment method$", () -> {
            bukalapak.danaPaymentPage().selectDANAUnbindAsPaymentMethod();
        });

        Then("^user is redirected to DANA OTP page$", () -> {
            bukalapak.danaPaymentPage().verifyIsOnDANAOTPPage();
        });

        Then("^user is redirected to success page$", () -> {
            bukalapak.danaPaymentPage().verifyIsOnSuccessPage();
        });

        And("^user tap Bayar dengan DANA button$", () -> {
            bukalapak.danaPaymentPage().tapBayarDenganDANAButton();
            bukalapak.danaPaymentPage().handleDANAConfirmPage();
        });

        And("^user close DANA OTP page$", () -> {
            bukalapak.danaPaymentPage().closeOTPPage();
        });

        And("^user just close DANA OTP page$", () -> {
            bukalapak.danaPaymentPage().verifyIsOnDANAOTPPage();
            bukalapak.danaPaymentPage().justCloseOTPPage();

        });

        And("^user tap back from DANA PIN or OTP page$", () -> {
            bukalapak.danaPaymentPage().backPINOTPDANA();
        });

        And("user unset auto apply DANA voucher", () -> {
            bukalapak.danaPaymentPage().unsetAutoApplyDANAVoucher();
        });

        When("^user select (registered|new) DANA Card Payment as payment method$", (String state) -> {
            bukalapak.danaPaymentPage().setDANACardPayment(state);
        });

        When("^user select (registered|new) DANA Card Payment$", (String state) -> {
            bukalapak.danaPaymentPage().setDANACreditCardPayment(state);
        });

        And("^user input CVV number$", () -> {
            bukalapak.danaPaymentPage().inputCVV("registered");
        });

        And("^user tap Bayar button in DANA card payment$", () -> {
            bukalapak.danaPaymentPage().tapBayar();
        });

        And("^user tap on isi detail kartu button$", () -> {
            bukalapak.danaPaymentPage().tapIsiDetailKartu();
        });

        And("^user fill Card infromation with \"([^\"]*)\"$", (String cardNumber) -> {
            bukalapak.danaPaymentPage().typeCardNumber(cardNumber);
            bukalapak.danaPaymentPage().fillDetailCard();
        });

        Then("^user verify DANA voucher for \"([^\"]*)\" is visible in Checkout page$", (String user) -> {
            bukalapak.apiCall().setUserAuthv4(user);
            Integer totalVoucher = bukalapak.apiCall().getDANAPocketTotal();
            bukalapak.danaPaymentPage().isDANAVoucherExist(totalVoucher);
        });

        And("^user verify payment method at invoice detail is ([^\"]*)$", (String paymentMethod) -> {
                    bukalapak.danaPaymentPage().verifyDANAPaymentMethod(paymentMethod);
                });

        And("^user check mix payment Dana \\+ Credits is auto apply$", () -> {
            bukalapak.danaPaymentPage().validationMixPaymentSummarySaldoAndDana();
        });

        Then("^DANA Card Payment is showing error message of limitation amount$", () -> {
            bukalapak.danaPaymentPage().cardPaymentLimitationAmount();
        });

        And("^user check mix payment Dana and Credits is auto apply$", () -> {
            bukalapak.danaPaymentPage().validationMixPaymentSummaryDANAandCredits();
        });

        And("^user check mix payment DANA is auto apply$", () -> {
            bukalapak.danaPaymentPage().validationMixPaymentSummaryDANA();
        });

        And("^client check mutation list of (DANA|Credits|Mix) refund transaction for \"([^\"]*)\" user$", (String payMethod, String user) -> {
            String invoiceNo = DANAData.getNoTagihan();
            String trxNo = DANAData.getNoTrx();

            String[] mutation = bukalapak.apiCall().getDANARefundMutation(payMethod, user);
            bukalapak.danaPaymentPage().verifyDANARefundMutation(trxNo, invoiceNo, mutation, payMethod);
        });

        And("^user verify DANA \"([^\"]*)\" mutation$", (String state) -> {
            bukalapak.danaPaymentPage().verifyDANAMutationTab(state);
        });

        And("user mix payment DANA with Credits", () -> {
            bukalapak.danaPaymentPage().mixPaymentDANAWithCredits();
        });

        And("^user set mix payment ([^\"]*) with Rp100$", (String mix) -> {
            bukalapak.danaPaymentPage().tapUbahMixPayment();
            switch (mix) {
                case "DANA":
                    bukalapak.danaPaymentPage().setMixPayment("dana");
                    bukalapak.danaPaymentPage().unsetMixPayment("saldo");
                    break;
                case "Saldo":
                    bukalapak.danaPaymentPage().setMixPayment("saldo");
                    bukalapak.danaPaymentPage().unsetMixPayment("dana");
                    break;
                case "Credits":
                    bukalapak.danaPaymentPage().setMixPayment("credits");
                    bukalapak.danaPaymentPage().unsetMixPayment("dana");
                    break;
                case "DANA_Credits":
                    bukalapak.danaPaymentPage().setMixPayment("dana");
                    bukalapak.danaPaymentPage().unsetMixPayment("credits");
                    break;
                case "Both":
                    bukalapak.danaPaymentPage().setMixPayment("dana");
                    bukalapak.danaPaymentPage().setMixPayment("saldo");
                    break;
                default:
                    bukalapak.danaPaymentPage().setMixPayment("dana");
                    bukalapak.danaPaymentPage().setMixPayment("credits");
                    break;
            }
            bukalapak.danaPaymentPage().tapSimpanMixPayment();
        });

        And("user unset all auto apply mixpayment", () -> {
            bukalapak.danaPaymentPage().tapUbahMixPayment();
            bukalapak.danaPaymentPage().hideKeyboardMixpayment();
            bukalapak.danaPaymentPage().unsetMixPayment("saldo");
            bukalapak.danaPaymentPage().unsetMixPayment("dana");
            bukalapak.danaPaymentPage().tapSimpanMixPayment();
        });

        And("user unset auto apply mixpayment", () -> {
            bukalapak.danaPaymentPage().tapUbahMixPayment();
            bukalapak.danaPaymentPage().hideKeyboardMixpayment();
            bukalapak.danaPaymentPage().unsetMixPayment("dana");
            bukalapak.danaPaymentPage().tapSimpanMixPayment();
        });

        And("user unset auto apply credits or saldo as mixpayment", () -> {
            bukalapak.danaPaymentPage().unsetAutoMixPayment();
        });

        And("user unset auto apply triple mixpayment", () -> {
            bukalapak.danaPaymentPage().unsetAutoTripleMixPayment();
        });

        And("^user verify mix payment with DANA$", () -> {
            bukalapak.danaPaymentPage().verifyMixpaymentWithDANA();
        });

        And("^user verify card payment fee of \"([^\"]*)\" percent$", (String paymentFeeEnv) -> {
            bukalapak.danaPaymentPage().verifyDanaCardPaymentFee(paymentFeeEnv);
        });

        And("^user verify mix payment with Saldo$", () -> {
            bukalapak.danaPaymentPage().verifyMixpaymentWithSaldo();
        });

        And("^user tap Lihat Tagihan on DANA success page$", () -> {
            bukalapak.danaPaymentPage().tapLihatTagihanOnDanaSuccessPage();
        });

        And("^user check BL Voucher reduction on detail (card|invoice)$", (String state) -> {
            bukalapak.danaPaymentPage().verifyBLVoucherReduction(state);
        });

        And("^user cek Mutasi Refund Mix Payment with DANA$", () -> {
            bukalapak.danaPaymentPage().tapBackFromDetailPembelian();
            bukalapak.danaPaymentPage().verifykNoTagihan();
        });

        And("^user buy product with price (<|>) Rp 10.000 on Barang Favorite menu$", (String state) -> {
            bukalapak.homePage().tapBarangFavoritMenu();
            if (state.equals("<")) {
                bukalapak.favoritePage().tapProductName("barang_dana_single_payment");
            } else {
                bukalapak.favoritePage().tapProductName("barang_dana_mix_payment");
            }
            bukalapak.productListingPage().checkIsOnProductDetail();
            bukalapak.iOSBasePage().tapElement("button_beli_sekarang");
        });

        And("^user choose \"([^\"]*)\" on payment method \"([^\"]*)\"$", (String subPayMethod, String payMethod) -> {
            bukalapak.danaPaymentPage().selectMixPaymentMethodWithDANA(payMethod);
            bukalapak.danaPaymentPage().selectSubPaymentMethod(subPayMethod);
        });

        And("^user go to Detail Invoice after pay the transaction$", () -> {
            bukalapak.danaPaymentPage().verifyIsOnSuccessPage();
            bukalapak.paymentStatusPage().tapSeePaymentDetailButton();
        });

        And("^user \"([^\"]*)\" cancel payment via API after close \"([^\"]*)\" web view$", (String user, String ibank) -> {
            bukalapak.danaPaymentPage().closeInternetBanking(ibank);
            bukalapak.apiCall().setUserAuthv4(user);
            bukalapak.apiCall().retrieveTransaction(DANAData.getNoTrx().substring(2));
            bukalapak.apiCall().cancelUnpaidInvoice(DANAData.getInvoiceID());
        });

        And("^user \"([^\"]*)\" cancel payment via API after transaction using bank transfer$", (String user) -> {
            bukalapak.danaPaymentPage().setDANATagihanTrx();
            bukalapak.apiCall().setUserAuthv4(user);
            bukalapak.apiCall().retrieveTransaction(DANAData.getNoTrx().substring(2));
            bukalapak.apiCall().cancelUnpaidInvoice(DANAData.getInvoiceID());
        });
        And("^user \"([^\"]*)\" refund DANA transaction via API", (String user) -> {
            bukalapak.danaPaymentPage().setDANATagihanTrx();
            bukalapak.apiCall().setUserAuthv4(user);
            bukalapak.apiCall().setTransactionState(DANAData.getNoTrx().substring(2), "cancelled");
        });

        When("^user change payment to \"([^\"]*)\"$", (String payMethod) -> {
            bukalapak.danaPaymentPage().setChangeTo(payMethod);
            bukalapak.danaPaymentPage().tapBayarOnOldCheckout();
            bukalapak.paymentStatusPage().tapSeePaymentDetailOrderMP();
            bukalapak.detailPembelianMarketplacePage().verifyPaymentMethod(payMethod);
        });

        And("^unbind user select DANA as payment method$", () -> {
            bukalapak.danaPaymentPage().selectDANAasPaymentMethodForUnbindUsers();
        });

        And("unbind user select DANA as payment method for vp product", () -> {
            bukalapak.danaPaymentPage().selectDANAasVPPaymentMethodForUnbindUsers();
        });

        And("unbind user select SALDO as payment method for vp product", () -> {
            bukalapak.danaPaymentPage().selectSALDOasVPPaymentMethodForUnbindUsers();
        });

        And("^unbind user select DANA as payment method for serbu seru$", () -> {
            bukalapak.danaPaymentPage().selectDANAAsSerbuSeruPaymentMethod();
        });

        Then("^user see the dana payment selection screen$", () -> {
            bukalapak.danaPaymentPage().verifyDANAPaymentSelectionScreen();
        });


        Then("^user see the dana topup screen$", () -> {
            bukalapak.danaPaymentPage().verifyDANATopupScreen();
        });

        And("^user chooses DANA as payment method$", () -> {
            bukalapak.danaPaymentPage().selectDANA();
        });

        When("user select DANA as payment method at VP chekcout", () -> {
            bukalapak.danaPaymentPage().selectMetodePembayaranForVPProduct();
            bukalapak.danaPaymentPage().selectDANAinVPMetodePembayaranScreen();
        });

        And("^user is in DANA payment method page$", () -> {
            bukalapak.danaPaymentPage().verifyDANAPaymentPage();
        });

        //TODO Check the usage of this step
        And("^user top up DANA via Metode Lain using Saldo Rp (.*)$", (String amount) -> {
            bukalapak.danaPaymentPage().topUpDANAViaMetodeLain(amount);
        });

        Then("^user select top up from Metode Lain at Alchemy (MP|VP) Checkout$", (String page) -> {
            bukalapak.danaPaymentPage().selectTopUpDANAMetodeLainCheckout(page);
        });

        And("^user select top up from BukaDompet at Alchemy (MP|VP) Checkout$", (String page) -> {
            bukalapak.danaPaymentPage().selectTopUpDANAFromDompetCheckout(page);
        });

        And("user input top up amount Rp(.*) from Saldo BukaDompet", (String amount) -> {
            bukalapak.danaPaymentPage().inputTopupBDAmount(amount);
        });

        And("^user input minimum top up amount from Saldo BukaDompet$", () -> {
            bukalapak.danaPaymentPage().inputTopupBDAmount("100");
        });
        
        When("user tap Pindahin ke DANA button on Alchemy Checkout", () -> {
            bukalapak.danaPaymentPage().tapConfirmTopUpBDCheckout();
        });

        Then("user see top up success message and redirected back to DANA payment method page", () -> {
            bukalapak.danaPaymentPage().verifyTopUpBDCheckoutSuccess();
            bukalapak.danaPaymentPage().verifyDANAPaymentPage();
        });

        Then("user see success binding message on Alchemy VP Checkout page", () -> {
            bukalapak.danaPaymentPage().verifySuccessBindAlchemyVPCheckoutPage();
        });

        And("^user click Bayar Dengan Button in Checkout Marketplace Page$", () -> {
            bukalapak.danaPaymentPage().tapBayarDenganButton();
        });

        And("user tap Ubah Metode Pembayaran", () -> {
            bukalapak.danaPaymentPage().tapUbahPembayaran();
        });

        And("user select DANA and get Freeze Information", () -> {
            bukalapak.danaPaymentPage().tapDANAPaymentMethod();
            bukalapak.danaPaymentPage().verifyInfoDANAFreeze();
        });

        And("user select DANA and get Freeze Information Because Saldo Freeze", () -> {
            bukalapak.danaPaymentPage().tapDANAPaymentMethod();
            bukalapak.danaPaymentPage().verifyInfoDANAFreezeBySaldoFreeze();
        });

        And("user verify there is no Top Up BukaDompet option", () -> {
            bukalapak.danaPaymentPage().verifyNoTopupWithSaldo();
        });

        When("user frozen select DANA as payment method", () -> {
            bukalapak.danaPaymentPage().selectMetodePembayaranForVPProduct();
            bukalapak.danaPaymentPage().selectDANAinVPMetodePembayaranScreen();
        });

        Then("user able to see information about DANA Frozen info", () -> {
            bukalapak.danaPaymentPage().verifyDANAFrozenAlchemyVPCheckout();
        });

        And("user verify error message of invalid card number", () -> {
            bukalapak.danaPaymentPage().verifyInvalidCardError();
        });

        Then("user validate DANA saved card", () -> {
            bukalapak.danaPaymentPage().selectMetodePembayaran();
            bukalapak.danaPaymentPage().selectDANAinMetodePembayaran();
            bukalapak.danaPaymentPage().handleCardPaymentOnboarding();
            bukalapak.danaPaymentPage().verifyDanaSavedCard();
        });

        And("^user verify mix payment with BukaDompet", () -> {
            bukalapak.danaPaymentPage().verifyMixpaymentWithBukaDompet();
        });

        Then("not enough DANA balance message must displaying", () -> {
            bukalapak.danaPaymentPage().notEnoughDanaError();
        });

        Then("user validate Hubungkan on checkout page", () -> {
            bukalapak.danaPaymentPage().selectMetodePembayaran();
            bukalapak.danaPaymentPage().unbindDanaError();
        });

        And("user redirect to Kendala Teknis transaction page", () -> {
            bukalapak.danaPaymentPage().userOnKendalaTeknisPage();
        });

        Then("user verify show pop up verification phone number on Alchemy Marketplace Checkout page", () -> {
            bukalapak.danaPaymentPage().verifyPopUpVerificationPhoneAlchemyMPCheckout();
        });

        And("user skip payment onboarding at vp page", () -> {
                    bukalapak.danaPaymentPage().skipVpPaymentOndboarding();
        });

        And("user go to checkout page via deeplink with total payment below than Rp 20.000", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("CHECKOUT_TOTAL_LESS_THAN_RP20K_DEEPLINK"));
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        When("user tap Metode Pembayaran on Checkout MP Page", () -> {
            bukalapak.danaPaymentPage().selectMetodePembayaran();
            bukalapak.metodePembayaranPage().userOnMetodePembayaranPage();
        });

        Then("user select Saldo and verify Saldo freeze on old MP checkout", () -> {
            bukalapak.danaPaymentPage().verifySaldoFreezeOldMPCheckout();
        });

        And("user select DANA and get Freeze Information Because Saldo Freeze on old MP checkout", () -> {
            bukalapak.danaPaymentPage().verifyInfoDANAFreezeBySaldoFreezeOldMPCheckout();
        });

        And("bound user select DANA as payment method for serbu seru", () -> {
            bukalapak.danaPaymentPage().selectDANAAsSerbuSeruPaymentMethod();
        });

        And("^user \"([^\"]*)\" set initial balance before transaction$", (String credUsr) -> {
            DANAData.setDanaBalance(bukalapak.apiCall().getDanaBalance(credUsr));
            DANAData.setCreditsBalance(bukalapak.apiCall().getCreditsBalance(credUsr));
            DANAData.setBukaDompetBalance(bukalapak.apiCall().getDompetBalance(credUsr));
        });

        And("^user get total amount transaction on marketplace alchemy checkout$", () -> {
            bukalapak.danaPaymentPage().setTotalAmountTrx();
        });

        And("^user \"([^\"]*)\" validate balance after transaction with \"([^\"]*)\"$", (String credUsr, String trx) -> {
            int dana = bukalapak.apiCall().getDanaBalance(credUsr);
            int credits = bukalapak.apiCall().getCreditsBalance(credUsr);
            int dompet = bukalapak.apiCall().getDompetBalance(credUsr);

            bukalapak.danaPaymentPage().validateBalanceAfterTrx(trx, dana, credits, dompet);
        });

        And("^user \"([^\"]*)\" validate balance after refund", (String credUsr) -> {
            int dana = bukalapak.apiCall().getDanaBalance(credUsr);
            int credits = bukalapak.apiCall().getCreditsBalance(credUsr);
            int dompet = bukalapak.apiCall().getDompetBalance(credUsr);

            bukalapak.danaPaymentPage().validateBalanceAfterRefund(dana, credits, dompet);
        });

        And("^user wait transaction status until paid$", () -> {
            bukalapak.danaPaymentPage().validateTrxPaid();
        });

        And("^user wait transaction status until refunded", () -> {
            bukalapak.danaPaymentPage().validateTrxRefunded();
        });

        And("^user \"([^\"]*)\" validate balance after topup Rp([^\"]*)", (String credUsr, String topup) -> {
            int dana = bukalapak.apiCall().getDanaBalance(credUsr);
            int credits = bukalapak.apiCall().getCreditsBalance(credUsr);
            int dompet = bukalapak.apiCall().getDompetBalance(credUsr);

            bukalapak.danaPaymentPage().validateBalanceAfterTopup(dana, credits, dompet, Integer.parseInt(topup));
        });
    }
}
