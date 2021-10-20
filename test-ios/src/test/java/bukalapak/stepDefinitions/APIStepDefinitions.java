package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.*;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class APIStepDefinitions extends TestInstrument implements En {

    public APIStepDefinitions() {
        And("user \"([^\"]*)\" gets cart has been cleared", (String arg0) -> {
            bukalapak.apiCall().setUserAuthv4(arg0);
            bukalapak.apiCall().removeFromCartV4();
        });
        And("user \"([^\"]*)\" gets cart has been added 1 product from seller \"([^\"]*)\" product",
                (String buyer, String seller) -> {
                    bukalapak.apiCall().setUserBasicToken(buyer);
                    bukalapak.apiCall().addToCart(seller, "1");
                });

        And("user \"([^\"]*)\" gets cart has been added \"([^\"]*)\" product from seller \"([^\"]*)\" product",
                (String buyer, String quantity, String seller) -> {
                    bukalapak.apiCall().setUserBasicToken(buyer);
                    bukalapak.apiCall().addToCart(seller, quantity);
                });

        Given("client has an available postpaid (.*) customer number", (String product) -> {
            bukalapak.apiCall().setUserAuthv4("CONFIRMED");
            bukalapak.apiCall().inquirePostpaidNumber(product);
        });

        Given("user \"([^\"]*)\" has cleared non primary address", (String credential) -> {
            bukalapak.apiCall().deleteNonPrimaryAddress(credential);
        });

        When("user \"([^\"]*)\" add item to cart", (String credential, DataTable cartDetail) -> {
            bukalapak.apiCall().setUserAuthv4(credential);
            bukalapak.apiCall().addItemToCart(cartDetail);
        });

        When("user \"([^\"]*)\" get store id", (String storeUsername) -> {
            bukalapak.apiCall().setUserAuthv4("CONFIRMED");
            bukalapak.apiCall().getStoreId(storeUsername);
        });

        When("user add new address with:", (DataTable addressDetails) -> {
            bukalapak.apiCall().setUserAuthv4("CONFIRMED");
            bukalapak.apiCall().addNewAddress(addressDetails);
        });

        When("^seller \"([^\"]*)\" set store status to (close|open)$", (String credentialUser, String status) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().setStoreStatus(credentialUser, status);
        });

        When("^seller \"([^\"]*)\" set \"([^\"]*)\" quantity to (\\d{1,})$", (String credentialUser, String itemName, String quantity) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().setStoreStatus(credentialUser, "open");
            bukalapak.apiCall().setQuantityItemOnStore(itemName, Integer.parseInt(quantity));
        });

        When("^seller \"([^\"]*)\" set item to (not )?for sale status$", (String credentialUser, String state) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            Boolean forSale = (state == null) ? true : false;
            if (forSale) {
                bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), 1000, forSale);
            } else {
                bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), 0, forSale);
            }
        });

        Given("user \"([^\"]*)\" send \"([^\"]*)\" message to \"([^\"]*)\" using API", (String sender, String type, String receiver) -> {
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().sendMessage(type, receiver);
        });

        Given("user \"([^\"]*)\" send \"([^\"]*)\" message to \"([^\"]*)\" using exclusive API", (String sender, String type, String receiver) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().sendMessage(type, receiver, "exclusive");
        });

        Given("user \"([^\"]*)\" delete chat with user \"([^\"]*)\" using API", (String sender, String receiver) -> {
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().deleteChatRoom(receiver);
        });

        When("^seller \"([^\"]*)\" set item stock (below|above) item quantity on cart$", (String credentialUser, String state) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            if (state.equals("above")) {
                bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), CartData.getQuantityItem() + 1, false);
            } else {
                bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), CartData.getQuantityItem() - 1, false);
            }
        });

        When("user \"([^\"]*)\" create transaction", (String credentialUser, DataTable transactiondetail) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().createTransaction(transactiondetail);
        });

        When("user \"([^\"]*)\" make payment with \"([^\"]*)\" method from created transaction's invoice", (String credentialUser, String method) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().makePayment(CSIData.getNomorTagihan(), method);
        });

        And("^user \"([^\"]*)\" check their push balance using API$", (String credentialUser) -> {
            bukalapak.apiCall().setPushBalance(credentialUser);
        });

        When("^user \"([^\"]*)\" has added( (\\d+))? product to cart with product ID \"([^\"]*)\"?$", (String credentialUser, Integer quantity, String productId) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            if (quantity == null) {
                bukalapak.apiCall().addToCartSpecificProductAPIv4(productId, 1);
            } else {
                bukalapak.apiCall().addToCartSpecificProductAPIv4(productId, quantity);
            }
        });

        And("^\"([^\"]*)\" removes all active promoted push products$", (String credentialUser) -> {
            bukalapak.apiCall().removeAllPromotedPushProducts(credentialUser);
        });

        And("^\"([^\"]*)\" sets \"([^\"]*)\" to be an active promoted push product with bid (.*)$", (String credentialUser, String productName, String bid) -> {
            bukalapak.apiCall().setPromotedPushProduct(credentialUser, productName, bid);
        });

        And("^user retrieve total bukadompet balance info from api with user \"([^\"]*)\"$", (String credentialUser) -> {
            PROMData.setSaldoKamu(bukalapak.apiCall().getWalletAmount(credentialUser) + bukalapak.apiCall().getDanaBalance(credentialUser));
        });

        And("^\"([^\"]*)\" stops promoted daily budget$", (String credentialUser) -> {
            bukalapak.apiCall().stopPromotedPushDailyBudget(credentialUser);
        });

        And("^\"([^\"]*)\" turns on promoted daily budget with Loan \"([^\"]*)\"$", (String credentialUser, String loanState) -> {
            bukalapak.apiCall().turnOnPromotedDailyBudget(credentialUser, loanState);
        });

        And("^retrieve promoted push budget for \"([^\"]*)\" using API$", (String credentialUser) -> {
            bukalapak.apiCall().retrievePromotedPushBudget(credentialUser);
        });

        And("^user \"([^\"]*)\" set main address in \"([^\"]*)\"$", (String credentialUser, String selectedTitle) -> {
            bukalapak.apiCall().setUserMainAddressByTitle(credentialUser, selectedTitle);
        });

        Given("^user register new user with:", (DataTable registerDetails) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().setAuthTokenWithoutLogin();
            bukalapak.apiCall().registerNewUser(registerDetails);
        });

        When("^user \"([^\"]*)\" add new address after register with:", (String credentialUser, DataTable addressDetails) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().addNewAddress(addressDetails);
        });

        And("user \"([^\"]*)\" deactive TFA using API$", (String credentialUser) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().deactiveTFA(credentialUser);
        });

        And("^\"([^\"]*)\" deleted product with name \"([^\"]*)\" using API$", (String credentialUser, String productName) -> {
            bukalapak.apiCall().deleteSpecificProduct(credentialUser, productName);
        });

        Then("user \"([^\"]*)\" get created invoice with \"([^\"]*)\" status", (String credentialUser, String status) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().checkTransactionStatus(TransactionData.getIdTransaksi(), status);
        });

        When("user \"([^\"]*)\" process sale transaction to state \"([^\"]*)\"", (String credentialUser, String state) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().setTransactionState(TransactionData.getIdTransaksi(), state);
        });

        When("^user \"([^\"]*)\" get transaction id of (sale|purchase) with status \"([^\"]*)\" and courier \"([^\"]*)\"$", (String credentialUser, String context, String state, String courier) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().getIdTransactionBasedOnCourier(context, courier, state);
        });

        Then("^user \"([^\"]*)\" process transaction with resi manual with carrier \"([^\"]*)\"$", (String credential, String carrier) -> {
            bukalapak.apiCall().setUserAuthv4(credential);
            bukalapak.apiCall().processSentTransaction(carrier);
        });

        And("^user \"([^\"]*)\" create complaint \"([^\"]*)\" category$", (String user, String category) -> {
            bukalapak.apiCall().setUserAuthv4(user);
            bukalapak.apiCall().postContactUs(category);
        });

        Then("^user get created complaint with \"([^\"]*)\" status$", (String status) -> {
            bukalapak.apiCall().checkComplaintStatus(CSIData.getIdComplaint(), status);
        });

        When("^user set complaint status to \"([^\"]*)\"$", (String status) -> {
            bukalapak.apiCall().setComplaintStatus(status);
        });

        // Region Iklan Lapak
        And("\"([^\"]*)\" creates Iklan Lapak with Product IDs as \"([^\"]*)\", category as (\\d+), and budget as \"([^\"]*)\"", (String credentialUser, String productID, Integer category, String budget) -> {
            bukalapak.apiCall().createIklanLapak(credentialUser, productID, category, budget);
        });

        And("\"([^\"]*)\" removes first Iklan Lapak campaign", (String credentialUser) -> {
            bukalapak.apiCall().removeFirstIklanLapakCampaign(credentialUser);
        });

        And("^\"([^\"]*)\" remove all iklan lapak via api$", (String credentialUser) -> {
            bukalapak.apiCall().removeAllIklanLapakCampaign(credentialUser);
        });

        And("^user \"([^\"]*)\" sets (active|dormant) the latest iklan lapak campaign from api$", (String credentialUser, String state) -> {
            bukalapak.apiCall().updateStateLatestIklanLapakCampaign(credentialUser, state);
        });
        // End region Iklan Lapak

        // Region Promoted Push Otomatis or Grup
        And("\"([^\"]*)\" creates Promoted Push Otomatis Budget Limited with product as \"([^\"]*)\", and name as \"([^\"]*)\"", (String credentialUser, String productID, String campaignName) -> {
            bukalapak.apiCall().createPromotedOtomatisBudget(credentialUser, productID, campaignName);
        });

        And("\"([^\"]*)\" removes all Promoted Push Otomatis campaigns", (String credentialUser) -> {
            bukalapak.apiCall().removeAllPromosiOtomatisCampaign(credentialUser);
        });

        And("^user \"([^\"]*)\" has (activated|deactivated) campaigns with title \"([^\"]*)\"$", (String credentialUser, String action, String campaignTitle) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().updateStatusPromotedGroup(campaignTitle, action);
        });

        And("^user \"([^\"]*)\" (activated|deactivated) promoted push product \"([^\"]*)\"$", (String credentialUser, String action, String productName) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().updateStatusPromotedSatuan(action, productName);
        });
        // End region Promoted Push Otomatis or Grup

        And("^user \"([^\"]*)\" retrieve bonus promoted percentage from API$", (String credentialUser) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            PROMData.setPromotedBonusPercentage(bukalapak.apiCall().retrievePromotedBonusPercentage());
        });

        And("^\"([^\"]*)\" retrieve push price$", (String credential) -> {
            bukalapak.apiCall().setUserAuthv4(credential);
            bukalapak.apiCall().getPushPrice();
        });

        //Region Investment
        And("user get total payment for bukaemas redemption", () -> {
            bukalapak.apiCall().getBukaEmasTotalPayment();
        });

        And("user \"([^\"]*)\" check new transaction id", (String credential) -> {
            bukalapak.apiCall().setUserAuthv4(credential);
            bukalapak.apiCall().getBukaEmasTransactionId();
        });
        //End region Investment

        When("^user \"([^\"]*)\" get product id on serbu seru active event$", (String credentialUser) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().getProductIdOnActiveEventLuckyDeals();
        });

        Given("user \"([^\"]*)\" set product \"([^\"]*)\" availability to \"([^\"]*)\"", (String credentialSender, String skuId, String availability) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSender);
            bukalapak.apiCall().setProductAvailability(skuId, availability);
        });

        Given("user \"([^\"]*)\" restock product \"([^\"]*)\" to \"([\\d|^\"]*)\"", (String credentialSender, String productId, String stock) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSender);
            bukalapak.apiCall().restockProduct(productId, stock);
        });

        Given("^seller \"([^\"]*)\" set barang \"([^\"]*)\" di jual and quantity to (\\d{1,})$", (String credentialSender, String procudctName, String quantity) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSender);
            bukalapak.apiCall().setProductAvailabilityAndQuantity(procudctName, quantity);
        });

        When("^user \"([^\"]*)\" get total product by category \"([^\"]*)\"$", (String credentialUser, String category) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().retrieveEventIdOnActiveEventPotongHarga();
            bukalapak.apiCall().retrieveProductByCategoryPotongHarga(category);
        });

        When("^user \"([^\"]*)\" get history potong harga$", (String credentialUser) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().retrieveUserHistoryPotongHarga();
        });

        Given("user \"([^\"]*)\" open store with ID \"([^\"]*)\"", (String credentialSeller, String storeIdEnv) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSeller);
            bukalapak.apiCall().openStore(storeIdEnv);
        });

        Given("user \"([^\"]*)\" has product \"([^\"]*)\" details", (String credentialUser, String productId) -> {
            bukalapak.apiCall().setUserAuthv4(credentialUser);
            bukalapak.apiCall().getProductDetails(productId);
        });

        Given("user \"([^\"]*)\" has (\\d+) active voucher lapak", (String credentialSeller, Integer total) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSeller);
            bukalapak.apiCall().setCurrentTotalActiveVoucherLapak(total);
        });

        Given("user \"([^\"]*)\" has not reach maximum number of active voucher lapak yet", (String credentialSeller) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSeller);
            bukalapak.apiCall().deactivateFirstActiveVoucherLapak();
        });

        Given("user \"([^\"]*)\" deactivates voucher lapak with code \"([^\"]*)\"", (String credentialSeller, String voucherCode) -> {
            bukalapak.apiCall().setUserAuthv4(credentialSeller);
            bukalapak.apiCall().setVoucherLapakStatusWithCode(voucherCode, "inactive");
        });

        When("^user \"([^\"]*)\" get total product of Nabung Diskon$", (String credential) -> {
            bukalapak.apiCall().setUserAuthv4(credential);
            bukalapak.apiCall().retrieveProductNabungDiskon();
        });

        Given("^user create an (active|upcoming) voucher lapak with body:$", (String state, String body) -> {
            bukalapak.apiCall().createVoucherLapakWithBody(body, state);
        });

        Given("^user \"([^\"]*)\" get braze PN \"([^\"]*)\" with \"([^\"]*)\"$", (String externalUserId, String campaignId, String apiToken) -> {
            bukalapak.apiCall().sendBrazePN(externalUserId, campaignId, apiToken);
        });

        Given("user \"([^\"]*)\" (block|unblock) (permanent|temporary) user \"([^\"]*)\" using API", (String sender, String type, String duration, String receiver) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().blockUser(sender, type, duration, receiver);
        });

        Then("user \"([^\"]*)\" received \"([^\"]*)\" message from \"([^\"]*)\"", (String receiver, String messageType, String sender) -> {
            bukalapak.apiCall().setUserAuthv4(receiver);
            bukalapak.apiCall().checkLastMessage(sender, messageType);
        });

        Given("user \"([^\"]*)\" with storename \"([^\"]*)\" get status stores of courier \"([^\"]*)\"", (String credential, String storename, String courier) -> {
            XPRData.setStatusCourier(bukalapak.apiCall().getStatusCourierToko(credential, storename, courier));
        });

        Then("user \"([^\"]*)\" delete all message template using API", (String sender) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().deleteAllMessageTemplates();
        });

        Then("user \"([^\"]*)\" create message template using API", (String sender) -> {
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().createMessageTemplate();
        });

        Then("^user \"([^\"]*)\" remove product \"([^\"]*)\" from seller \"([^\"]*)\" on favorite product$", (String credential, String productName, String seller) -> {
            bukalapak.apiCall().deleteItemFromFav(credential, productName, seller);
        });

        Then("user \"([^\"]*)\" turn off all chat assistant using API", (String sender) -> {
            bukalapak.apiCall().setForAPIcall();
            bukalapak.apiCall().setUserAuthv4(sender);
            bukalapak.apiCall().turnOffChatAssistant();
        });

        Given("user \"([^\"]*)\" cancel transaction", (String credential) -> {
            bukalapak.apiCall().cancelTransaction(credential);
        });
    }
}
