package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BukaReksaTransactionPage extends BasePage {

    private List<String> transactionFilterTypes = Arrays.asList(
            "Pembelian",
            "Penjualan",
            "Bonus"
    );

    private List<String> transactionFilterProductTypes = Arrays.asList(
            "Pasar Uang",
            "Saham",
            "Pendapatan Tetap",
            "Campuran"
    );

    private List<String> transactionFilterStates = Arrays.asList(
            "Selesai",
            "Kedaluwarsa"
    );

    public BukaReksaTransactionPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isTransactionListPageDisplayed() {
        checkSearchField();
        checkFilterButton();
        checkTransactionTitlePage();
    }

    public void tapOnFirstTrxtList() {
        tapElement("TRX_FIRST_ON_LIST");
    }

    public void tapOnTransactionBackBtn() {
        tapElement("TRX_BACK_BUTTON");
        waitFor(10);
    }

    public void isTransactionFilterPageDisplayed() {
        validateExist("TRX_FILTER_HEADER_TITLE",3);
        isElementExist("TRX_FILTER_RESET_BTN", 5);
    }

    public void tapOnDateDropdown() {
        tapElement("TRX_FILTER_DATE_DROPDOWN");
    }

    public void selectAlchemyDatePicker(String date, String month, String year) {
        InvestmentData.setFilterDate(date);
        InvestmentData.setFilterMonth(month);
        InvestmentData.setFilterYear(year);
    }

    /**
     * Parsing alchemy date picker format into Indonesia locale
     *
     * @param dateValue is getting from the selected alchemy date picker
     */
    private void parseDatePickerIntoLocaleIND(String dateValue) {
        String finalDate;
        Locale indonesia = new Locale("in", "ID");
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", indonesia);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("d MMM yyyy", indonesia);
        try {
            Date date = dateFormat.parse(dateValue);
            finalDate = newDateFormat.format(date);
            if (finalDate.contains("Agu")) {
                finalDate = finalDate.replace("Agu", "Agt");
            }
            InvestmentData.setFilterDateValue(finalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getFilterDateValue() {
        verifyElementExist("TRX_FILTER_DATE_VALUE");
        return getText("TRX_FILTER_DATE_VALUE");
    }

    public void verifyFilterTrxIsSelected() {
        String[] dateValue = {
                InvestmentData.getFilterDate(),
                InvestmentData.getFilterMonth(),
                InvestmentData.getFilterYear()
        };
        String dateValueSpaces = String.join(" ", dateValue);
        parseDatePickerIntoLocaleIND(dateValueSpaces);
        validateValue().equals(InvestmentData.getFilterDateValue(), getFilterDateValue(), "Value of date picker doesn't match");
    }

    public void isOnBukaReksaTransactionDetailPage() {
        validateExist("TRANSACTION_DETAIL_INFORMATION_TITLE_TEXT", 20);
        String trxListType = InvestmentData.getTrxListType().toLowerCase();

        if (trxListType.equals("pembelian") || trxListType.equals("transaksi rutin")) {
            validateValue().equals("Informasi Pembelian", getDetailTrxTitleText(),
                    "Transaction detail doesn't match!");
            validatePurchaseInformation();
        } else {
            validateValue().equals("Informasi Penjualan", getDetailTrxTitleText(),
                    "Transaction detail doesn't match!");
            validateSalesInformation();
        }
    }

    public void verifyInvoiceDateCreated() {
        tapOnBillingInformationDropdown();
        validateExist(
                constructLocator("TRANSACTION_DETAIL_INVOICE_DATE", InvestmentData.getFilterDateValue()),
                3, "Invoice date created doesn't match");
    }

    public void tapOnTransactionFilterBtn() {
        tapElement("TRX_LIST_FILTER_BTN");
    }

    public void tapOnApplyTransactionFilterBtn() {
        tapElement("TRX_FILTER_APPLY_BTN");
    }

    public void tapOnTrxProductList() {
        int randomTrxList = randomize().number(getTotalTransactionList());
        // Set Data
        InvestmentData.setProductNameBukaReksa(getTrxListNameText(randomTrxList));
        InvestmentData.setTrxListStatus(getTrxListStateText(randomTrxList));
        InvestmentData.setTrxListType(getTrxListTypeText(randomTrxList));
        tapElement("TRX_PRODUCT_LIST", randomTrxList);
    }

    public void tapOnMulaiDaftarButton() {
        validateExist("TRX_POPUP_BWR_MULAI_DAFTAR", 3, "Mulai daftar button not displayed");
        tapElement("TRX_POPUP_BWR_MULAI_DAFTAR");
    }

    public void selectFilterTransactionType() {
        String trxTypeVal = transactionFilterTypes.get(randomize().number(transactionFilterTypes.size()));
        InvestmentData.setTrxFilterType(trxTypeVal);
        tapElement(constructLocator("TRX_FILTER_TYPE_RADIO_BTN", trxTypeVal));
        validateDisplayed(constructLocator("TRX_FILTER_TYPE_RADIO_BTN_SELECTED",trxTypeVal ));
    }

    public void selectFilterTransactionProductType() {
        String stateVal;
        boolean radioBtnState = false;
        int productTypeVal = transactionFilterProductTypes.size();
        while (!radioBtnState) {
            stateVal = transactionFilterProductTypes.get(randomize().number(productTypeVal));
            if (isElementExist(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal), 5)) {
                InvestmentData.setProductTypeBukaReksa(stateVal);
                tapElement(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal));
                radioBtnState = true;
                validateDisplayed(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX_SELECTED", stateVal));
            } else {
                swipeUpToElement(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal));
            }
        }
    }

    public void selectFilterTransactionState() {
        String stateVal;
        boolean radioBtnState = false;
        int productType = transactionFilterStates.size();
        while (!radioBtnState) {
            stateVal = transactionFilterStates.get(randomize().number(productType));
            if (isElementExist(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal), 3)) {
                InvestmentData.setTrxFilterState(stateVal);
                tapElement(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal));
                radioBtnState = true;
                validateDisplayed(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX_SELECTED", stateVal));
            } else {
                swipeUpToElement(constructLocator("TRX_FILTER_PRODUCT_TYPE_CHECKBOX", stateVal));
            }
        }
    }

    public void verifyAllTrxTypeRadioAsDefaultChecked() {
        validateDisplayed("TRX_FILTER_TYPE_RADIO_BTN_SEMUA_SELECTED",
                "Transaction type radio button still not checked");
    }

    public void verifyAllProductTypeCheckBoxAsDefaultChecked() {
        if (isElementExist(
                "TRX_FILTER_PRODUCT_TYPE_TXT", 3)) {
            swipeUpToElement("TRX_FILTER_PRODUCT_TYPE_TXT");
        } else {
            swipeUpToElement("TRX_FILTER_TYPE_TXT");
            swipeUpToElement("TRX_FILTER_PRODUCT_TYPE_TXT");
        }
        validateDisplayed("TRX_FILTER_PRODUCT_TYPE_CHECKBOX_SEMUA_SELECTED",
                "Product type checkbox not checked as default");
    }

    public void verifyTransactionListAreFilteredByType() {
        // need delay because the element rendering needed
        delay(1000);
        int totalTrxList = getTotalTransactionList();
        for (int i = 0; i < totalTrxList; i++) {
            validateValue().equals(InvestmentData.getTrxFilterType(), getTrxListTypeText(i),
                    "Transaction type text doesn't match!");
        }
    }

    public void verifyTransactionListAreFilteredByState() {
        // need delay because the element rendering needed
        delay(500);
        int totalTrxList = getTotalTransactionList();
        for (int i = 0; i < totalTrxList; i++) {
            validateValue().equals(InvestmentData.getTrxFilterState(), getTrxListStateText(i),
                    "Transaction state text doesn't match!");
        }
    }

    public void verifyPopUpForceRegisBukaReksa() {
        validateExist("TRX_POPUP_BWR_TITLE", 5, "Popup bwr title not displayed");
        validateExist("TRX_POPUP_BWR_BODY", 5, "Popup bwr body not displayed");
    }

    public void verifyProductTypeValue() {
        if(!isElementVisible("TRX_DETAIL_COMPONENT_JENIS_PRODUK")) {
            swipeUpToElement("transaction_detail_bukabantuan");
        }
        validateValue().equals(InvestmentData.getProductTypeBukaReksa(), getProductTypeText(),
                "BukaReksa Product type doesn't match");
    }

    public void verifyPaymentMethod() {
        String paymentMethod = TransactionData.getPaymentMethod();
        if (paymentMethod.toLowerCase().matches("bukadompet|saldo")) {
            try {
                validateValue().equals("Saldo", getPaymentMethodText(), "Payment method doesn't match");
            } catch (AssertionError e) {
                validateValue().equals("BukaDompet", getPaymentMethodText(), "Payment method doesn't match");
            }
        } else {
            validateValue().contains(paymentMethod, getPaymentMethodText(), "Payment method doesn't match");
        }
    }

    public void verifyInvoiceTotalAmountText() {
        if (!isElementExist("BILLING_INFORMATION_TOTAL_PRICE", 3)) {
            tapOnBillingInformationDropdown();
        }
        validateValue().equals("Rp" + InvestmentData.getNominalPurchase(),
                getInvoiceTotalPriceText().replace(".", ""), "Amount doesn't match");
    }

    public void verifyRedemptionDetailPage() {
        waitForVisibilityOf("TRX_DETAIL_REDEMPTION_HEADER");
        validateDisplayed("TRX_DETAIL_REDEMPTION_TITLE");
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getRedemptionDetailProductName(), "Product name is not equal!");
    }

    public void validateRedemptionType() {
        validateValue().equals(InvestmentData.getNominalRedemption(), getRedemptionDetailNominal(), "Redemption Amount is not equal!");
        validateValue().contains("BukaDompet", getRedemptionDetailMethod());
    }

    private void tapOnBillingInformationDropdown() {
        tapElement("TRANSACTION_DETAIL_BILLING_INFO");
    }

    private void validatePurchaseInformation() {
        swipeUpToElement("TRANSACTION_DETAIL_INFORMATION_TITLE_TEXT");
        validateExist("TRANSACTION_DETAIL_INFORMATION_TITLE_TEXT", 5);
        validateValue().equals(InvestmentData.getTrxListStatus(), getSubscriptionStatusText(), "Bukareksa Purchase status doesn't match");
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameText(), "BukaReksa Product name doesn't match");
        if (InvestmentData.getCrossSellingTransaction()) {
            verifyCrossSellingPurchaseAmount();
        }
    }

    private void verifyCrossSellingPurchaseAmount() {
        tapElement("TRANSACTION_DETAIL_INVOICE_CHEVRON");
        validateExist("BILLING_INFORMATION_TOTAL_PURCHASE", 5);
        validateValue().equals(InvestmentData.getNominalPurchase(),
                getTotalPembelianPriceText(), "Amount doesn't match");
    }

    private String getTotalPembelianPriceText() {
        return getText("BILLING_INFORMATION_TOTAL_PURCHASE");
    }

    public void verifyInvoiceTotalPurchaseText() {
        if (TransactionData.getPaymentMethod().contains("Transfer")) {
            validateValue().equals("Rp" + TransactionData.getTotalTagihanTransfer(),
                    getTotalPembelianPriceText().replace(".", ""), "Amount doesn't match");
        } else {
            verifyInvoiceTotalAmountText();
        }

    }

    private void validateSalesInformation() {
        validateExist("TRANSACTION_DETAIL_INFORMATION_TITLE_TEXT", 5);
        validateValue().equals(InvestmentData.getTrxListStatus(), getRedemptionStatusText(), "Bukareksa Purchase status doesn't match");
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameText(), "BukaReksa Product name doesn't match");
        validateValue().equals(InvestmentData.getProductTypeBukaReksa(), getProductTypeText(), "BukaReksa Product type doesn't match");
    }

    public void verifyLihatTransaksidiBukaemasBtn() {
        validateExist("REXA_PKG_BUKAEMAS_TRANSACTION_LINK",3);
    }

    private void checkSearchField() {
        isElementExist("TRX_LIST_SEARCH_FIELD", 5);
    }

    private void checkTransactionTitlePage() {
        validateExist("TRX_HEADER_TITLE",5);
    }

    private void checkFilterButton() {
        isElementExist("TRX_LIST_FILTER_BTN", 2);
    }

    private int getTotalTransactionList() {
        verifyElementExist("TRX_PRODUCT_LIST");
        int totalList = getElementsPresent("TRX_PRODUCT_LIST").size();
        int totalResult = 0;
        int maxList = 3;
        if (totalList > maxList) {
            totalResult = totalList - (totalList - maxList);
        } else if (totalList != 0 && totalList < maxList || totalList == maxList) {
            totalResult = totalList;
        } else {
            Assert.fail("No transaction list displayed!");
        }
        return totalResult;
    }

    private String getTrxListNameText(int index) {
        return getText("TRX_LIST_PRODUCT_NAME", index);
    }

    private String getTrxListStateText(int index) {
        return getText("TRX_LIST_STATE", index);
    }

    private String getTrxListTypeText(int index) {
        return getText("TRX_LIST_TYPE", index);
    }

    private String getProductTypeText() {
        return getText("TRX_DETAIL_COMPONENT_JENIS_PRODUK");
    }

    private String getDetailTrxTitleText() {
        return getText("TRANSACTION_DETAIL_INFORMATION_TITLE_TEXT");
    }

    private String getSubscriptionStatusText() {
        return getText("TRANSACTION_DETAIL_STATUS_PEMBELIAN");
    }

    private String getRedemptionStatusText() {
        return getText("TRANSACTION_DETAIL_STATUS_PENJUALAN");
    }

    private String getProductNameText() {
        return getText("TRANSACTION_DETAIL_PRODUK_REKSA");
    }

    private String getPaymentMethodText() {
        if (!isElementExist("TRANSACTION_DETAIL_PAYMENT_METHOD", 10)) {
            tapElement("TRANSACTION_DETAIL_INFORMASI_PESANAN_DROPDOWN");
        }
        return getText("TRANSACTION_DETAIL_PAYMENT_METHOD");
    }

    private String getInvoiceTotalPriceText() {
        return getText("BILLING_INFORMATION_TOTAL_PRICE");
    }

    private String getRedemptionDetailProductName() { return getText("TRX_DETAIL_REDEMPTION_PRODUCT_NAME"); }

    private String getRedemptionDetailMethod() {
        return getText("TRX_DETAIL_REDEMPTION_STATUS_DESC");
    }

    private String getRedemptionDetailNominal() {
        return getText("TRX_DETAIL_REDEMPTION_PRODUCT_AMOUNT").replaceAll("[^\\d]", "");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
