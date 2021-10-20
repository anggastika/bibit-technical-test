package bukalapak.pageObject.mtx;

import bukalapak.data.CheckoutData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class CheckoutRevampMarketplacePage extends BasePage {

    public CheckoutRevampMarketplacePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void isOnCheckoutPage() {
        verifyElementExist("TITLE_CO_TEXT", 60, "checkout page is not displayeds");
        verifyElementExist("ADDRESS_NAME_CO_SECTION", 5, "checkout page is not displayeds");
        CheckoutData.setAddressName(getElementValue("ADDRESS_NAME_CO_SECTION"));
    }

    public void navigateSection(String locator) {
        if (!isElementExist(locator, 5)) {
            try {
                swipeUpToElement(locator);
                LogUtil.info("Swipe up to find the " + locator + " co section!");
            } catch (AssertionError e) {
                LogUtil.info("Swipe down to find the " + locator + " co section!");
                swipeDownToElement(locator);
            }
        }
        verifyElementExist(locator, 5, locator + " co section is not found!");
    }

    public void tapSection(String locator) {
        navigateSection(locator);
        tapElement(locator);
    }

    public void tapButton(String locator) {
        waitForElementClickable(locator, 15);
        tapElement(locator);
    }


    public void validateLabel(String label_name) {
        if (!isElementExist("TITLE_CO_TEXT")) {
            tapElement("back_icon");
        }
        verifyElementExist(constructLocator("PRODUCT_LABEL_CO_TEXT", label_name), 20, "label not shown");
    }

    public void validateqty() {
        String qtyActual = getTextFromElement("PRODUCT_QUANTITY_CO_TEXT").replace("Jumlah: ", "");
        assertEquals(CheckoutData.getProductQuantity(), qtyActual, "qty doesn't match");
    }

    public void validateSectionFromCartOrTrayATC(String sectionName) {
        switch (sectionName) {
            case "product name":
                assertEquals(CheckoutData.getProductName(), getTextFromElement("PRODUCT_NAME_CO_TEXT"), "product name doesn't match");
                break;
            case "quantity product":
                validateqty();
                break;
            case "product price":
                assertEquals(CheckoutData.getProductPrice(), getIntegerFromTextElement("PRODUCT_PRICE_CO_TEXT"), "product price doesn't match");
                break;
            case "slashed product price":
                assertEquals(CheckoutData.getSlashedProductPrice(), getIntegerFromTextElement("PRODUCT_SLISHED_PRICE_CO_TEXT"), "slashed product price doesn't match");
                break;
            case "variant product":
                validateVariant();
                break;
            default:
                break;
        }
    }

    public void validateSectionFromCart(String sectionName) {
        int sizeProductFromCart = CheckoutData.getCartDetails().size();
        for (int i = 0; i < sizeProductFromCart; i++) {
            swipeDownToElement("ADDRESS_CO_SECTION", 5);
            switch (sectionName) {
                case "product name":
                    String productName = CheckoutData.getCartDetails().get(i).get("PRODUCT_NAME").toString();
                    String elementProductName = constructLocator("SPECIFIC_PRODUCT_NAME", productName);
                    swipeUpToElement(elementProductName, 3);
                    validateDisplayed(elementProductName);
                    break;
                case "product price":
                    String productPrice = CheckoutData.getCartDetails().get(i).get("ITEM_PRICE").toString();
                    String elementProductPrice = constructLocator("SPECIFIC_PRODUCT_PRICE", getRpFromNumber(Integer.parseInt(productPrice)).replace("Rp", ""));
                    swipeUpToElement(elementProductPrice, 3);
                    validateDisplayed(elementProductPrice);
                    break;
                case "quantity product":
                    String productQty = CheckoutData.getCartDetails().get(i).get("QTY").toString();
                    String elementProductQty = constructLocator("SPECIFIC_PRODUCT_QTY", "Jumlah: "+productQty);
                    swipeUpToElement(elementProductQty, 3);
                    validateDisplayed(elementProductQty);
                    break;
                default:
                    Assert.fail("Invalid Section");
                    break;
            }
        }
    }

    public void waitLoadingCoSection() {
        waitForElementClickable("BUY_CO_BUTTON", 10);
    }

    public void tapAddressSection() {
        waitLoadingCoSection();
        navigateSection("ADDRESS_CO_SECTION");
        tapSection("ADDRESS_CO_SECTION");
    }

    public void tapSwitch(String locator, String state) {
        verifyElementExist(locator, 15, locator + " switch element not found!");
        String swtState = getElementValue(locator);
        if (state.equals("on") && swtState.equals("true")) {
            LogUtil.info("Switch " + locator + " is already on!");
        } else if (state.equals("off") && swtState.equals("false")) {
            LogUtil.info("Switch " + locator + " is already off!");
        } else {
            tapElement(locator);
        }
    }

    public void typeEditText(String locator, String input) {
        verifyElementExist(locator, 30, locator + " edit text element not found!");
        typeAndEnterValue(locator, input);
    }

    public void tapEditDropshipper() {
       tapElement("DROPSHIPPER_CO_EDIT_BTN");
    }

    public void validateDropshipperSheet() {
        verifyElementExist("DROPSHIPPER_NAME_CO_FIELD", 10, "Sheet dropshipper not shown");
        verifyElementExist("DROPSHIPPER_ADDITIONAL_CO_FIELD");
    }

    public void inputDropshipperData(String dropshipperName, String dropshipperAdditionalInfo) {
        typeEditText("DROPSHIPPER_NAME_CO_FIELD", dropshipperName);
        CheckoutData.setDropshipperName(dropshipperName);
        if (dropshipperAdditionalInfo != null) {
            typeEditText("DROPSHIPPER_ADDITIONAL_CO_FIELD", dropshipperAdditionalInfo);
            CheckoutData.setDropshipperAdditionalInfo(dropshipperAdditionalInfo);
        }
    }

    public void saveDropshipper() {
        tapButton("DROPSHIPPER_SAVE_CO_BUTTON");
    }

    public void setDropshipper(String dropshipperName, String dropshipperAdditionalInfo) {
        waitLoadingCoSection();
        navigateSection("DROPSHIPPER_CO_TOOGLE");
        tapSwitch("DROPSHIPPER_CO_TOOGLE", "on");
        validateDropshipperSheet();
        inputDropshipperData(dropshipperName, dropshipperAdditionalInfo);
        saveDropshipper();
    }

    public void validateDropshipperInfo(String dropshipperAdditionalInfo) {
        if (dropshipperAdditionalInfo != null ) {
            String dropshipper_Expected = CheckoutData.getDropshipperName() + " - " + CheckoutData.getDropshipperAdditionalInfo();
            assertEquals(dropshipper_Expected, getText("DROPSHIPPER_CO_TEXT"));
        } else {
            assertEquals(CheckoutData.getDropshipperName(), getText("DROPSHIPPER_CO_TEXT"));
        }
        verifyElementExist("DROPSHIPPER_CO_EDIT_BTN", 5, "edit button dropshipper not shown");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void validateDropshipperAfterReToggleOn() {
        assertTextContains(CheckoutData.getDropshipperName(), getText("DROPSHIPPER_CO_TEXT"),"failed re-toggle on dropshipper");
        verifyElementExist("DROPSHIPPER_CO_EDIT_BTN", 5, "edit button dropshipper not shown");
    }

    public void validateDataDropshipperSheet() {
        assertEquals(CheckoutData.getDropshipperName(), getElementValue("DROPSHIPPER_NAME_CO_FIELD"));
        assertEquals(CheckoutData.getDropshipperAdditionalInfo(), getElementValue("DROPSHIPPER_ADDITIONAL_CO_FIELD"));
    }

    public void editDropshipper(String dropshipperName, String dropshipperAdditionalInfo) {
        tapEditDropshipper();
        validateDropshipperSheet();
        validateDataDropshipperSheet();
        inputDropshipperData(dropshipperName, dropshipperAdditionalInfo);
        saveDropshipper();
    }

    public void changeToAnotherAddress() {
        tapAddressSection();
        waitForVisibilityOf("checkout_marketplace_second_address_icon");
        tapElement("checkout_marketplace_second_address_icon");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void tapCatatanPelapak(String sellerName) {
        String locator = constructLocator("BUYER_NOTES_CO_BUTTON", sellerName);
        verifyElementExist(locator, 10, "button catatan pelapak not shown");
        tapElement(locator);
    }

    public void setCatatanPelapak(String catatanPelapak, String sellerName) {
        tapCatatanPelapak(sellerName);
        verifyElementExist("BUYER_NOTES_CO_SHEET", 10, "buyer notes sheet not shown");
        typeEditText("BUYER_NOTES_CO_FIELD", catatanPelapak);
        CheckoutData.setBuyerNotes(getTextFromElement("BUYER_NOTES_CO_FIELD"));
        tapElement("BUYER_NOTES_SAVE_BUTTON", 10);
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void validateInfoBuyerInfo(String state, String sellerName) {
        String locatorInfoBuyerNotes = constructLocator("BUYER_NOTES_INFO_CO_TEXT", sellerName);
        switch (state) {
            case "can" :
                verifyElementExist(locatorInfoBuyerNotes, 10, "buyer notes not reseted");
                break;
            case "cant" :
                verifyElementNotExist(locatorInfoBuyerNotes);
                break;
            default:
                Assert.fail("Invalid state");
                break;
        }
    }

    public void validateSuccessfullyAddedCatatanPelapak(String sellerName) {
        verifyElementExist("BUYER_NOTES_SUCCESS_CO_SNACKBAR", 10, "snackbar not shown");
        String buyerNotesExpected = "Catatan: " + CheckoutData.getBuyerNotes();
        String locatorInfoBuyerNotes = constructLocator("BUYER_NOTES_INFO_CO_TEXT", sellerName);
        swipeToLocator(locatorInfoBuyerNotes);
        assertEquals(buyerNotesExpected, getText(locatorInfoBuyerNotes));
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void tapOnPelajariButton() {
        if (isElementExist("TNC_LOGISTIC_INSURANCE_CO_TEXT", 10)) {
            swipeUpToElement("TNC_LOGISTIC_INSURANCE_CO_TEXT");
            tapElement("TNC_LOGISTIC_INSURANCE_CO_TEXT");
        }
    }

    public void tapOnMengertiButton() {
        tapElement("checkout_marketplace_insurance_benefit_mengerti_button");
    }

    public void tapCheckbox(String locator, String state) {
        navigateSection(locator);
        String cbState = getElementValue(locator);
        if (state.equals("check") && cbState.equals("true")) {
            LogUtil.info("Checkbox " + locator + " is already checked!");
        } else if (state.equals("check") && cbState.equals("false")) {
            tapElement(locator);
            delay(2000);
            validateValue().equals("true", getElementValue(locator));
        } else if (state.equals("uncheck") && cbState.equals("true")) {
            tapElement(locator);
            delay(2000);
            validateValue().equals("false", getElementValue(locator));
        } else {
            validateValue().equals("false", getElementValue(locator));
            LogUtil.info("Checkbox " + locator + " is already unchecked!");
        }
    }

    public int getIndexCheckBoxMixPayment(String type) {
        int index = 0;
        if (type.equalsIgnoreCase("Saldo")) {
            int size = getMultipleElement().withLocator("MIX_PAYMENT_CO_CHECKBOX").size();
            index = (size > 1) ? 1 : 0;
        }
        return index;
    }

    public void tapCheckboxMixPayment(String state, String type) {
        String locator = "MIX_PAYMENT_CO_CHECKBOX";
        int index = getIndexCheckBoxMixPayment(type);
        String cbState = getMultipleElement().withLocator(locator).get(index).getAttribute("value");
        if (state.equalsIgnoreCase("check") && cbState.equalsIgnoreCase("true")) {
            LogUtil.info("Checkbox " + locator + " is already checked!");
        } else if (state.equalsIgnoreCase("check") && cbState.equalsIgnoreCase("false")) {
            tapElements(locator, index);
            delay(3000);
            validateValue().equals("true", getMultipleElement().withLocator(locator).get(index).getAttribute("value"));
        } else if (state.equalsIgnoreCase("uncheck") && cbState.equalsIgnoreCase("true")) {
            tapElements(locator, index);
            delay(3000);
            validateValue().equals("false", getMultipleElement().withLocator(locator).get(index).getAttribute("value"));
        } else {
            LogUtil.info("Checkbox " + locator + " is already unchecked!");
        }
    }

    public void tapCheckboxMixPayment(String locator, String state, int index) {
        String cbState = getMultipleElement().withLocator(locator).get(index).getAttribute("value");
        if (state.equals("check") && cbState.equals("true")) {
            LogUtil.info("Checkbox " + locator + " is already checked!");
        } else if (state.equals("check") && cbState.equals("false")) {
            tapElements(locator, index);
            delay(3000);
            validateValue().equals("true", getMultipleElement().withLocator(locator).get(index).getAttribute("value"));
        } else if (state.equals("uncheck") && cbState.equals("true")) {
            tapElements(locator, index);
            delay(3000);
            validateValue().equals("false", getMultipleElement().withLocator(locator).get(index).getAttribute("value"));
        } else {
            LogUtil.info("Checkbox " + locator + " is already unchecked!");
        }
    }

    public void setMixPayment(String amount, String type) {
        int cbSize = getMultipleElement().withLocator("MIX_PAYMENT_CO_CHECKBOX").size();
        if (type.equals("DANA")) {
            typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 0, "");
            delay(3000);
            typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 0, amount);
            CheckoutData.setAmountMixpaymentDANA(convertPriceToInt(getText("MIX_PAYMENT_CO_INPUT", 0)));
            tapCheckboxMixPayment("MIX_PAYMENT_CO_CHECKBOX", "check", 0);
        } else if (type.equals("Saldo")) {
            if (cbSize > 1) {
                typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 1, "");
                delay(3000);
                typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 1, amount);
                CheckoutData.setAmountMixpaymentSaldo(convertPriceToInt(getText("MIX_PAYMENT_CO_INPUT", 1)));
                tapCheckboxMixPayment("MIX_PAYMENT_CO_CHECKBOX", "check", 1);
            } else {
                typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 0, "");
                delay(3000);
                typeAndEnterValue("MIX_PAYMENT_CO_INPUT", 0, amount);
                CheckoutData.setAmountMixpaymentSaldo(convertPriceToInt(getText("MIX_PAYMENT_CO_INPUT", 0)));
                tapCheckboxMixPayment("MIX_PAYMENT_CO_CHECKBOX", "check", 0);
            }
        }
    }

    public void setProductInsurance(String state) {
        waitLoadingCoSection();
        navigateSection("INSURANCE_PRODUCT_CO_CHECKBOX");
        tapCheckbox("INSURANCE_PRODUCT_CO_CHECKBOX", state);
        CheckoutData.setProductInsuranceNominal(convertPriceToInt(getTextFromElement("PRICE_INSURANCE_CO_TEXT")));
        tapSection("TNC_INSURANCE_PRODUCT_CO_SECTION");
        verifyElementExist("PRODUCT_INSURANCE_CO_SHEET", 10, "Product insurance sheet is not displayed!");
        tapButton("PRODUCT_INSURANCE_CO_SHEET_BUTTON");
        verifyElementExist("INSURANCE_PRODUCT_CO_SECTION", 10, "Product insurance sheet is not displayed!");
    }

    public void setLogisticInsurance(String state) {
        waitLoadingCoSection();
        navigateSection("LOGISTIC_INSURANCE_CO_CHECKBOX");
        tapCheckbox("LOGISTIC_INSURANCE_CO_CHECKBOX", state);
        waitLoadingCoSection();
        navigateSection("PRICE_LOGISTIC_INSURANCE_CO_TEXT");
        verifyElementExist("PRICE_LOGISTIC_INSURANCE_CO_TEXT", 10, "Logistic insurance price is not displayed!");
        CheckoutData.setLogisticInsuranceNominal(convertPriceToInt(getTextFromElement("PRICE_LOGISTIC_INSURANCE_CO_TEXT")));
    }

    public void setSellerInsurance(String state) {
        waitLoadingCoSection();
        navigateSection("RETUR_INSURANCE_CO_CHECKBOX");
        tapCheckbox("RETUR_INSURANCE_CO_CHECKBOX", state);
        waitLoadingCoSection();
        navigateSection("PRICE_RETUR_INSURANCE_CO_TEXT");
        verifyElementExist("PRICE_RETUR_INSURANCE_CO_TEXT", 10, "Seller insurance price is not displayed!");
        CheckoutData.setReturInsuranceNominal(convertPriceToInt(getTextFromElement("PRICE_RETUR_INSURANCE_CO_TEXT")));
    }

    public void setAutoInvest(String state) {
        waitLoadingCoSection();
        tapCheckbox("CROSS_SELLING_BUKAEMAS_CO_CHECKBOX", state);
        waitLoadingCoSection();
        navigateSection("PRICE_CROSS_SELLING_BUKAEMAS_CO_TEXT");
        verifyElementExist("PRICE_CROSS_SELLING_BUKAEMAS_CO_TEXT", 10, "Auto invest price is not displayed!");
        CheckoutData.setCrossSelingBukaemasNominal(getIntFromRp(getTextFromElement("PRICE_CROSS_SELLING_BUKAEMAS_CO_TEXT")));
    }

    public void tapPilihButtonCourier() {
        waitForElementClickable("COURIER_LIST_CO_BUTTON", 10);
        tapElement("COURIER_LIST_CO_BUTTON");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void chooseCourierName(String courierName) {
        String courierSelected = getTextFromElement("COURIER_SELECTED_NAME_CO");
        if (courierSelected.equals(courierName)) {
            CheckoutData.setCourierShippingName(courierSelected);
            CheckoutData.setCourierShippingNominal(getIntFromRp(getText("SHIPPING_PRICE_CO_TEXT")));
        } else {
            String courierNameElement = constructLocator("COURIER_LIST_NAME_CO", courierName);
            tapElement("COURIER_SELECTED_NAME_CO");
            swipeUpToElement(courierNameElement);
            tapElement(courierNameElement, 10);
            CheckoutData.setCourierShippingName(getTextFromElement(courierNameElement));
            tapPilihButtonCourier();
        }
    }

    public void chooseGroupCourier(String groupCourierName) {
        String courierGroupSelected = getTextFromElement("COURIER_GROUP_NAME_CO");
        if (courierGroupSelected.equals(groupCourierName)) {
            CheckoutData.setGroupCourierSHippingName(courierGroupSelected);
        } else {
            String groupCourierNameElement = constructLocator("GROUP_COURIER_LIST_NAME_CO", groupCourierName);
            tapElement("COURIER_GROUP_NAME_CO");
            tapElement(groupCourierNameElement, 10);
            CheckoutData.setGroupCourierSHippingName(getTextFromElement(groupCourierNameElement));
            tapPilihButtonCourier();
        }
    }

    public void chooseCourier(String groupCourierName, String courierName) {
        swipeUpToElement("SHIPPING_CO_SECTION");
        chooseGroupCourier(groupCourierName);
        chooseCourierName(courierName);
    }

    public void validateSelectedGroupCourier() {
        waitLoadingCoSection();
        verifyElementExist("COURIER_GROUP_NAME_CO", 5, "courier group section not shown");
        assertEquals(CheckoutData.getGroupCourierSHippingName(), getTextFromElement("COURIER_GROUP_NAME_CO"));
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void validateSelectedCourier() {
        verifyElementExist("COURIER_SELECTED_NAME_CO", 5, "courier section not shown");
        assertEquals(CheckoutData.getCourierShippingName(), getTextFromElement("COURIER_SELECTED_NAME_CO"));
        CheckoutData.setCourierShippingNominal(convertPriceToInt(getTextFromElement("SHIPPING_PRICE_CO_TEXT")));
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void selectPaymentMethod(String paymentMethod, String service) {
        navigateSection("NAME_PAYMENT_CO_TEXT");
        tapElement("NAME_PAYMENT_CO_TEXT");
        swipeUpToElement(constructLocator("checkout_marketplace_payment_method_name", paymentMethod));
        tapElement(constructLocator("checkout_marketplace_payment_method_name", paymentMethod));

        if (!(paymentMethod.equalsIgnoreCase("saldo")
                || paymentMethod.equalsIgnoreCase("dana")
                || paymentMethod.equalsIgnoreCase("transfer bank manual"))) {
            swipeUpToElement(constructLocator("checkout_marketplace_payment_method_service", service));
            tapElement(constructLocator("checkout_marketplace_payment_method_service", service));
            CheckoutData.setPaymentService(service);
        }
        CheckoutData.setPaymentMethod(paymentMethod);
    }

    public void usePaymentMethod() {
        swipeUpToElement("checkout_gunakan_metode_ini_button");
        tapElement("checkout_gunakan_metode_ini_button");
        waitFor(5);
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void validateDefaultPayment(String paymentName) {
        swipeUpToElement("NAME_PAYMENT_CO_TEXT");
        switch (paymentName) {
            case "Empty" :
                assertEquals("Belum pilih metode pembayaran", getElementValue("NAME_PAYMENT_CO_TEXT"), "payment default not " + paymentName);
                validateDisplayed("ERROR_RED_PAYMENT_CO_TEXT");
                break;
            case "DANA" :
                assertEquals("Saldo DANA", getElementValue("NAME_PAYMENT_CO_TEXT"), "payment default not " + paymentName);
                swipeUpToElement("BALANCE_PAYMENT_CO_TEXT");
                verifyElementExist("BALANCE_PAYMENT_CO_TEXT");
                break;
            case "Virtual Account" :
                assertTextContains("Virtual Account", getElementValue("NAME_PAYMENT_CO_TEXT"), "payment default not " + paymentName);
                break;
            case "Transfer Manual" :
                assertEquals("Transfer Bank", getElementValue("NAME_PAYMENT_CO_TEXT"), "payment default not " + paymentName);
                break;
            default:
                Assert.fail("Invalid payment name");
                break;
        }
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void toggleOnMixpayment() {
        navigateSection("MIXPAYMENT_CO_SWITCH");
        tapSwitch("MIXPAYMENT_CO_SWITCH", "on");
        verifyElementExist("ATUR_JUMLAH_MIXPAYMENT_CO_SWITCH", 5, "toggle on mixpayment gagal");
    }

    public void tapSimpanButtonMixpayment() {
        tapElement("MIX_PAYMENT_CO_SAVE_BUTTON");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void tapAturJumlahMixpayment() {
        tapSection("ATUR_JUMLAH_MIXPAYMENT_CO_SWITCH");
    }

    public void validateTncInsuranceCheckoutSubmission(String state) {
        if (state.equals("can")) {
            verifyElementExist("TNC_INSURANCE_CO_TEXT", 10, "tnc insurance not shown");
        } else {
            verifyElementNotExist("TNC_INSURANCE_CO_TEXT");
        }
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void validateMixpaymentNotAppear() {
        swipeUpToElement("CHOOSE_PAYMENT_CO_SECTION");
        verifyElementNotExist("MIXPAYMENT_BALANCE_CO_TEXT");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public int getPembulatanTextView() {
        if (isElementExist("ROUNDING_NOMINAL_SUMMARY_CO_TEXT", 2)) {
            return convertPriceToInt(getText("ROUNDING_NOMINAL_SUMMARY_CO_TEXT"));
        } else {
            return 0;
        }
    }

    public void validationPembulatanTooltipInfoText() {
        verifyElementExist("ROUNDING_INFO_MSG_SUMMARY_CO_TEXT", 15, "sheet pembulatan info not shown");
        tapElement("ROUNDING_INFO_SHEET_SUMMARY_CO_BUTTON_CLOSE");
    }

    public void setRoundedAmount() {
        delay(3000);
        CheckoutData.setRoundedAmount(getPembulatanTextView());
    }

    public void validationPembulatanText() {
        swipeUpToElement("ROUNDING_SUMMARY_CO_TEXT");
        verifyElementExist("ROUNDING_SUMMARY_CO_TEXT", 5, "text pembulatan not shown");
    }

    public void tapOnPembulatanTooltip() {
        tapElement("ROUNDING_SUMMARY_CO_TOOLTIP");
    }

    public int getTotalHargaBarangTextView() {
        navigateSection("TOTAL_HARGA_BARANG_SUMMARY_CO_TXT");
        int totalHargaBarangActual = convertPriceToInt(getText("TOTAL_HARGA_BARANG_NOMINAL_SUMMARY_CO_TXT"));
        return totalHargaBarangActual;
    }

    public void validateTotalHargaBarang(int totalHargaBarang) {
        assertEquals(getTotalHargaBarangTextView(), totalHargaBarang);
    }

    private int getBiayaKirimTextView() {
        swipeUpToElement("ONGKOS_KIRIM_SUMMARY_CO_TXT");
        int biayaKirimValue = convertPriceToInt(getText("ONGKOS_KIRIM_NOMINAL_SUMMARY_CO_TXT"));
        assertEquals(CheckoutData.getCourierShippingNominal(), biayaKirimValue, "ongkos kirim tidak sesuai");
        return biayaKirimValue;
    }

    public void validationBiayaKirim(int biayaKirim) {
        assertEquals(getBiayaKirimTextView(), biayaKirim);
    }

    private int getPerlindunganBarangTextView() {
        if (isElementExist("ASURANSI_BARANG_SUMMARY_CO_TXT")) {
            int perlindunganBarangValue = convertPriceToInt(getText("ASURANSI_BARANG_NOMINAL_SUMMARY_CO_TXT"));
            assertEquals(CheckoutData.getProductInsuranceNominal(), perlindunganBarangValue, "nominal perlindungan barang tidak sesuai");
            return perlindunganBarangValue;
        } else {
            return 0;
        }
    }

    private int getPerlindunganPengirimanTextView() {
        if (isElementExist("ASURANSI_PENGIRIMAN_SUMMARY_CO_TXT")) {
            int perlindunganPengirimanValue = convertPriceToInt(getTextFromElement("ASURANSI_PENGIRIMAN_NOMINAL_SUMMARY_CO_TXT"));
            assertEquals(CheckoutData.getLogisticInsuranceNominal(), perlindunganPengirimanValue, "nominal perlindungan pengiriman tidak sesuai");
            return perlindunganPengirimanValue;
        } else {
            return 0;
        }
    }

    private int getPerlindunganReturTextView() {
        waitLoadingCoSection();
        navigateSection("BIAYA_LAYANAN_SUMMARY_CO_TXT");
        if (isElementExist("ASURANSI_RETUR_SUMMARY_CO_TXT")) {
            int perlindunganReturValue = convertPriceToInt(getTextFromElement("ASURANSI_RETUR_NOMINAL_SUMMARY_CO_TXT"));
            assertEquals(CheckoutData.getReturInsuranceNominal(), perlindunganReturValue, "nominal perlindungan retur tidak sesuai");
            return perlindunganReturValue;
        } else {
            return 0;
        }
    }

    private int getBiayaPelayananTextView() {
        int biayaLayanan = 0;
        navigateSection("BIAYA_LAYANAN_SUMMARY_CO_TXT");
        String valueBiayaLayanan = getText("BIAYA_LAYANAN_NOMINAL_SUMMARY_CO_TXT");
        if (!valueBiayaLayanan.equalsIgnoreCase("Gratis")) {
            biayaLayanan = convertPriceToInt(valueBiayaLayanan);
        }
        return biayaLayanan;
    }

    private int getCrossSellingBukaemasTextView() {
        if (isElementExist("BELI_OTOMATIS_BUKAEMAS_SUMMARY_CO_TXT")) {
            int crossSellingBukaemasValue = convertPriceToInt(getTextFromElement("BELI_OTOMATIS_BUKAEMAS_NOMINAL_SUMMARY_CO_TXT"));
            assertEquals(CheckoutData.getCrossSelingBukaemasNominal(), crossSellingBukaemasValue, "nominal cross selling bukaemas tidak sesuai");
            return crossSellingBukaemasValue;
        } else {
            return 0;
        }
    }

    public void setTotalBiayaLainnya() {
        setDataCheckoutRevamp();
        swipeUpToElement("TITLE_SUMMARY_CO_TXT");
        swipeUp(2);
        int biayaLainnya = getPembulatanTextView() + getPerlindunganPengirimanTextView() + getPerlindunganBarangTextView() +
                getPerlindunganReturTextView() + getCrossSellingBukaemasTextView() + getBiayaPelayananTextView();
        CheckoutData.setTotalBiayaLainnya(biayaLainnya);
    }

    private int getTotalBelanjaTextView() {
        verifyElementExist("TOTAL_AMOUNT_SUMMARY_CO_TEXT", 10, "amount total pembayaran not shown");
        return convertPriceToInt(getElementValue("TOTAL_AMOUNT_SUMMARY_CO_TEXT"));
    }

    public void validationTotalBelanja() {
        int totalBelanja = (CheckoutData.getProductPrice() * Integer.parseInt(CheckoutData.getProductQuantity())) +
                CheckoutData.getCourierShippingNominal() +
                CheckoutData.getTotalBiayaLainnya() -
                CheckoutData.getAmountMixpaymentDANA() -
                CheckoutData.getAmountMixpaymentSaldo();
        assertEquals(getTotalBelanjaTextView(), totalBelanja);
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void clickBayarButton() {
        CheckoutData.setTotalPaymentCheckout(getTotalBelanjaTextView());
        tapElement("BUY_CO_BUTTON", 10);
    }

    public void tapVoucherSection() {
        tapSection("VOUCHER_CO_SECTION");
    }

    public void setVoucherPelapak(String sellerName) {
        //waiting loading voucher list page
        delay(2000);
        String voucherSellerLocator = constructLocator("VOUCHER_SELLER_CO_RADIO_BUTTON", sellerName);
        String nominalVoucherLocator = constructLocator("VOUCHER_SELLER_CO_NOMINAL_DISCOUNT_TEXT", sellerName);
        tapElement(voucherSellerLocator);
        CheckoutData.setNominalVoucher(getText(nominalVoucherLocator));
        tapButton("VOUCHER_SELLER_CO_BUTTON_PAKAI_VOUCHER");
    }

    public void validateVoucherApplied() {
        navigateSection("VOUCHER_SUCCESS_APPLIED_CO_LABEL");
        verifyElementExist("VOUCHER_SUCCESS_APPLIED_CO_LABEL");
        verifyElementExist("VOUCHER_CO_NOMINAL_LABEL");
        verifyElementExist("VOUCHER_CO_REMOVE_BUTTON");
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void changeAddress(String addressLabel) {
        waitLoadingCoSection();
        tapAddressSection();
        tapSection(constructLocator("ADDRESS_LABEL_CO_TEXT", addressLabel));
        verifyElementExist("ADDRESS_NAME_CO_SECTION", 10, "Address name is not displayed");
        CheckoutData.setAddressName(getText("ADDRESS_NAME_CO_SECTION"));
        HelperData.setLastActionPage(new CheckoutRevampMarketplacePage(iosDriver));
    }

    public void toggleOffDropshipper() {
        waitLoadingCoSection();
        tapSwitch("DROPSHIPPER_CO_TOOGLE", "off");
        verifyElementNotExist("DROPSHIPPER_CO_EDIT_BTN");
    }

    public void reToggleOnDropShipper() {
        waitLoadingCoSection();
        tapSwitch("DROPSHIPPER_CO_TOOGLE", "on");
    }

    private String getValueInsuranceProductCheckbox() {
        return getElementValue("INSURANCE_PRODUCT_CO_CHECKBOX");
    }

    private String getValueLogisticProductCheckbox() {
        return getElementValue("LOGISTIC_INSURANCE_CO_CHECKBOX");
    }

    private String getValueReturInsuranceCheckbox() {
        return getElementValue("RETUR_INSURANCE_CO_CHECKBOX");
    }

    private String getValueBukaEmasCheckbox() {
        swipeDownToElement("ADDRESS_NAME_CO_SECTION", 2);
        return getElementValue("CROSS_SELLING_BUKAEMAS_CO_CHECKBOX");
    }

    private void setDataCheckoutRevamp() {
        swipeDownToElement("ADDRESS_NAME_CO_SECTION", 2);
        CheckoutData.setAddressName(getElementValue("ADDRESS_NAME_CO_SECTION"));
        CheckoutData.setProductName(getElementValue("PRODUCT_NAME_CO_TEXT"));
        CheckoutData.setCourierShippingNominal(convertPriceToInt(getTextFromElement("SHIPPING_PRICE_CO_TEXT")));
        if (isElementExist("INSURANCE_PRODUCT_CO_CHECKBOX") && getValueInsuranceProductCheckbox().equals("true")) {
            CheckoutData.setProductInsuranceNominal(convertPriceToInt(getElementValue("PRICE_INSURANCE_CO_TEXT")));
        }
        if (getValueLogisticProductCheckbox().equals("true")) {
            CheckoutData.setLogisticInsuranceNominal(convertPriceToInt(getElementValue("PRICE_LOGISTIC_INSURANCE_CO_TEXT")));
        }
        if (getValueReturInsuranceCheckbox().equals("true")) {
            CheckoutData.setReturInsuranceNominal(convertPriceToInt(getElementValue("PRICE_RETUR_INSURANCE_CO_TEXT")));
        }
        if (getValueBukaEmasCheckbox().equals("true")) {
            CheckoutData.setCrossSelingBukaemasNominal(getIntFromRp(getElementValue("PRICE_CROSS_SELLING_BUKAEMAS_CO_TEXT")));
        }
    }

    public void validateVariant() {
        String variantActual = getTextFromElement("VARIANT_INFO_CO_TEXT").replace("Varian: ", "");
        assertEquals(CheckoutData.getProductVariant(), variantActual, "product variant doesn't match");
    }

    public void backFromCheckoutPage() {
        tapElement("BACK_CO_BUTTON");
        waitForVisibilityOf("CONFIRMATION_EXIT_CO_SHEET", 5);
        tapElement("CONFIRMATION_EXIT_CO_YES_BUTTON");
    }
}
