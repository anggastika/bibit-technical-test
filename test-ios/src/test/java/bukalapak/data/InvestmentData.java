package bukalapak.data;

public class InvestmentData {
    private static double goldBalanceBefore;
    private static double goldBalanceAfter;
    private static String totalHargaEmasString;
    private static double totalHargaEmas;
    private static double hargaEmasPerGram;
    private static String totalPembelian;
    private static String totalPenjualan;
    private static String investmentNominal;
    private static String investmentProduct;
    private static String beliRutinDate;
    private static boolean beliRutinActivation = false;
    private static String nominalRedemptionProduct;
    private static String quickFilter;
    private static boolean favoriteProductExist=false;
    private static boolean rexaOnboarding = false;
    private static String productTypeBukaReksa;
    private static String productRiskBukaReksa;
    private static String investmentManagerBukaReksa;
    private static String productNameBukaReksa;
    private static String productNABBukaReksa;
    private static boolean productDetailState = false;
    private static String sortOption;
    private static String nominalPurchase;
    private static String trxFilterType;
    private static String trxFilterState;
    private static String productPortofolioValue;
    private static String trxListType;
    private static String trxListStatus;
    private static String unitRedemptionProduct;
    private static String totalPortfolio;
    private static String recurringAmount;
    private static String autoInvestBuyerAmount;
    private static String autoInvestSellerAmount;
    private static String filterDate;
    private static String filterMonth;
    private static String filterYear;
    private static String filterDateValue;
    private static String unitPurchase;
    private static String paymentMethod;
    private static String totalNilaiOrder;
    private static String nominalRedeem;
    private static String unitRedeem;
    private static String installmentGoldWeight;
    private static String goalInvestName;
    private static String nominalTopupToDANA;
    private static String packageNameBukareksa;
    private static String goalInvestMinimumMonthlyAmount;
    private static boolean fromInvestasikuPage = false;
    private static boolean fromAturTujuanForm;
    private static boolean userHaveGoalInvestProduct;
    private static String currentBukaemasBalance;
    private static String kasihEmasSearchType;
    private static String kasihReceiverUsername;
    private static String transferEmasUnit;
    private static String kasihEmasMessage;
    private static String tebarEmasReceiverAmount;
    private static String goldForecastDatePrediction;
    private static String goldForecastPricePrediction;
    private static boolean fromGoldForecast = false;
    private static String bukaEmasTransactionId;
    private static String bukaEmasPackagePurchaseNominal;
    private static boolean fromBukaReksaPackage = false;
    private static boolean autoInvestActive;
    private static boolean disclaimerState = false;
    private static boolean onBoardingState = false;
    private static boolean crossSellingTransaction = false;
    private static String crossSellingState;
    private static String limitDANAamount;
    private static String installmentTenure;
    private static String monthlyInstallmentNominal;
    private static String totalInstallmentCheckoutPayment;
    private static String productTypeBukaEmas;

    //BukaEmas
    public static double getGoldBalanceBefore() {
        return goldBalanceBefore;
    }

    public static void setGoldBalanceBefore(double goldBalanceBefore) {
        InvestmentData.goldBalanceBefore = goldBalanceBefore;
    }

    public static double getGoldBalanceAfter() {
        return goldBalanceAfter;
    }

    public static void setGoldBalanceAfter(double goldBalanceAfter) {
        InvestmentData.goldBalanceAfter = goldBalanceAfter;
    }

    public static String getTotalHargaEmasString() {
        return totalHargaEmasString;
    }

    public static void setTotalHargaEmasString(String totalHargaEmasString) {
        InvestmentData.totalHargaEmasString = totalHargaEmasString;
    }

    public static double getTotalHargaEmas() {
        return totalHargaEmas;
    }

    public static void setTotalHargaEmas(double totalHargaEmas) {
        InvestmentData.totalHargaEmas = totalHargaEmas;
    }

    public static double getHargaEmasPerGram() {
        return hargaEmasPerGram;
    }

    public static void setHargaEmasPerGram(double hargaEmasPerGram) {
        InvestmentData.hargaEmasPerGram = hargaEmasPerGram;
    }

    public static String getTotalPembelian() {
        return totalPembelian;
    }

    public static void setTotalPembelian(String totalPembelian) {
        InvestmentData.totalPembelian = totalPembelian;
    }

    public static String getTotalPenjualan() {
        return totalPenjualan;
    }

    public static void setTotalPenjualan(String totalPenjualan) {
        InvestmentData.totalPenjualan = totalPenjualan;
    }

    public static void setCurrentBukaemasBalance(String currentBukaemasBalance) {
        InvestmentData.currentBukaemasBalance = currentBukaemasBalance;
    }

    public static String getCurrentBukaemasBalance() {
        return currentBukaemasBalance;
    }

    public static void setKasihEmasSearchType(String kasihEmasSearchType) {
        InvestmentData.kasihEmasSearchType = kasihEmasSearchType;
    }

    public static String getKasihEmasSearchType() {
        return kasihEmasSearchType;
    }

    public static void setKasihReceiverUsername(String kasihReceiverUsername) {
        InvestmentData.kasihReceiverUsername = kasihReceiverUsername;
    }

    public static String getKasihReceiverUsername() {
        return kasihReceiverUsername;
    }

    public static void setTransferEmasUnit(String transferEmasUnit) {
        InvestmentData.transferEmasUnit = transferEmasUnit;
    }

    public static String getTransferEmasUnit() {
        return transferEmasUnit;
    }

    public static void setKasihEmasMessage(String kasihEmasMessage) {
        InvestmentData.kasihEmasMessage = kasihEmasMessage;
    }

    public static String getKasihEmasMessage() {
        return kasihEmasMessage;
    }

    public static void setTebarEmasReceiverAmount(String tebarEmasReceiverAmount) {
        InvestmentData.tebarEmasReceiverAmount = tebarEmasReceiverAmount;
    }

    public static String getTebarEmasReceiverAmount() { return tebarEmasReceiverAmount; }

    //BukaReksa

    public static boolean getRexaOnboarding() {
        return rexaOnboarding;
    }

    public static void setRexaOnboarding(boolean val) {
        rexaOnboarding = val;
    }

    public static String getNominalRedemption() {
        return nominalRedemptionProduct;
    }

    public static void setNominalRedemptionProduct(String nominalPenjualanProduct) {
        InvestmentData.nominalRedemptionProduct = nominalPenjualanProduct;
    }

    public static String getUnitRedemptionProduct() {return unitRedemptionProduct; }

    public static void setUnitRedemptionProduct(String unitPenjualanProduct) {InvestmentData.unitRedemptionProduct = unitPenjualanProduct; }

    public static String getInvestmentNominal() {
        return investmentNominal;
    }

    public static void setInvestmentNominal(String investmentNominal) {
        InvestmentData.investmentNominal = investmentNominal;
    }

    public static String getInvestmentProduct() {
        return investmentProduct;
    }

    public static void setInvestmentProduct(String investmentProduct) {
        InvestmentData.investmentProduct = investmentProduct;
    }

    //Goal Investment
    public static String getBeliRutinDate() {
        return beliRutinDate;
    }

    public static void setBeliRutinDate(String beliRutinDate) {
        InvestmentData.beliRutinDate = beliRutinDate;
    }

    public static boolean getBeliRutinActivation() {
        return beliRutinActivation;
    }

    public static void setBeliRutinActivation(boolean beliRutinActivation) {
        InvestmentData.beliRutinActivation = beliRutinActivation;
    }

    public static String getQuickFilter() {
        return quickFilter;
    }

    public static void setQuickFilter(String quickFilter) {
        InvestmentData.quickFilter = quickFilter;
    }

    public static boolean getFavoriteProductExist(){return favoriteProductExist;}

    public static void setFavoriteProductExist(boolean favoriteProductExist) {
        InvestmentData.favoriteProductExist = favoriteProductExist;
    }

    public static void setInvestmentManagerBukaReksa(String investmentManagerBukaReksa) {
        InvestmentData.investmentManagerBukaReksa = investmentManagerBukaReksa;
    }

    public static String getProductTypeBukaReksa() {
        return productTypeBukaReksa;
    }

    public static void setProductTypeBukaReksa(String productTypeBukaReksa) {
        InvestmentData.productTypeBukaReksa = productTypeBukaReksa;
    }

    public static String getProductRiskBukaReksa() {
        return productRiskBukaReksa;
    }

    public static void setProductRiskBukaReksa(String productRiskBukaReksa) {
        InvestmentData.productRiskBukaReksa = productRiskBukaReksa;
    }

    public static String getInvestmentManagerBukaReksa() {
        return investmentManagerBukaReksa;
    }

    public static String getProductNameBukaReksa() {
        return productNameBukaReksa;
    }

    public static void setProductNameBukaReksa(String productNameBukaReksa) {
        InvestmentData.productNameBukaReksa = productNameBukaReksa;
    }

    public static String getProductNABBukaReksa() {
        return productNABBukaReksa;
    }

    public static void setProductNABBukaReksa(String productNABBukaReksa) {
        InvestmentData.productNABBukaReksa = productNABBukaReksa;
    }

    public static boolean getProductDetailState() { return productDetailState; }

    public static void setProductDetailState(boolean productDetailState) {
        InvestmentData.productDetailState = productDetailState;
    }

    public static String getSortOption() {
        return sortOption;
    }

    public static void setSortOption(String sortOption) {
        InvestmentData.sortOption = sortOption;
    }

    public static String getNominalPurchase() {
        return nominalPurchase;
    }

    public static void setNominalPurchase(String nominalPurchase) {
        InvestmentData.nominalPurchase = nominalPurchase;
    }

    public static String getTrxFilterType() {
        return trxFilterType;
    }

    public static void setTrxFilterType(String trxFilterType) {
        InvestmentData.trxFilterType = trxFilterType;
    }

    public static String getTrxFilterState() {
        return trxFilterState;
    }

    public static void setTrxFilterState(String trxFilterState) {
        InvestmentData.trxFilterState = trxFilterState;
    }

    public static String getProductPortofolioValue() {
        return productPortofolioValue;
    }

    public static void setProductPortofolioValue(String productPortofolioValue) {
        InvestmentData.productPortofolioValue = productPortofolioValue;
    }

    public static String getTrxListStatus() {
        return trxListStatus;
    }

    public static void setTrxListStatus(String trxListStatus) {
        InvestmentData.trxListStatus = trxListStatus;
    }

    public static String getTrxListType() {
        return trxListType;
    }

    public static void setTrxListType(String trxListType) {
        InvestmentData.trxListType = trxListType;
    }

    public static String getTotalPortfolio() {
        return totalPortfolio;
    }

    public static void setTotalPortfolio(String totalPortfolio) {
        InvestmentData.totalPortfolio = totalPortfolio;
    }

    public static String getFilterDate() {
        return filterDate;
    }

    public static void setFilterDate(String filterDate) {
        InvestmentData.filterDate = filterDate;
    }

    public static String getFilterMonth() {
        return filterMonth;
    }

    public static void setFilterMonth(String filterMonth) {
        InvestmentData.filterMonth = filterMonth;
    }

    public static String getFilterYear() {
        return filterYear;
    }

    public static void setFilterYear(String filterYear) {
        InvestmentData.filterYear = filterYear;
    }

    public static String getFilterDateValue() {
        return filterDateValue;
    }

    public static void setFilterDateValue(String filterDateValue) {
        InvestmentData.filterDateValue = filterDateValue;
    }

    public static String getUnitPurchase() {
        return unitPurchase;
    }

    public static void setUnitPurchase(String unitPurchase) {
        InvestmentData.unitPurchase = unitPurchase;
    }
  
      public static String getRecurringAmount() {
        return recurringAmount;
    }

    public static void setRecurringAmount(String recurringAmount) {
        InvestmentData.recurringAmount = recurringAmount;
    }

    public static String getAutoInvestBuyerAmount() {
        return autoInvestBuyerAmount;
    }

    public static void setAutoInvestBuyerAmount(String autoInvestBuyerAmount) {
        InvestmentData.autoInvestBuyerAmount = autoInvestBuyerAmount;
    }

    public static String getAutoInvestSellerAmount() {
        return autoInvestSellerAmount;
    }

    public static void setAutoInvestSellerAmount(String autoInvestSellerAmount) {
        InvestmentData.autoInvestSellerAmount = autoInvestSellerAmount;
    }

    public static boolean getAutoInvestActive() {
        return autoInvestActive;
    }

    public static void setAutoInvestActive(boolean autoInvestActive) {
        InvestmentData.autoInvestActive = autoInvestActive;
    }

    public static void setPaymentMethod(String paymentMethod) {
        InvestmentData.paymentMethod = paymentMethod;
    }

    public static String getPaymentMethod() {
        return paymentMethod;
    }

    public static void setTotalNilaiOrder(String totalNilaiOrder) {
        InvestmentData.totalNilaiOrder = totalNilaiOrder;
    }

    public static String getTotalNilaiOrder() {
        return totalNilaiOrder;
    }

    public static String getNominalRedeem() {
        return nominalRedeem;
    }

    public static void setNominalRedeem(String nominalRedeem) {
        InvestmentData.nominalRedeem = nominalRedeem;
    }

    public static String getUnitRedeem() {
        return unitRedeem;
    }

    public static void setUnitRedeem(String unitRedeem) {
        InvestmentData.unitRedeem = unitRedeem;
    }

    public static void setInstallmentGoldWeight(String installmentGoldWeight) {
        InvestmentData.installmentGoldWeight = installmentGoldWeight;
    }

    public static String getInstallmentGoldWeight() {
        return installmentGoldWeight;
    }

    public static String getGoalInvestName() {
        return goalInvestName;
    }

    public static void setGoalInvestName(String goalInvestName) {
        InvestmentData.goalInvestName = goalInvestName;
    }
  
    public static String getNominalTopUpToDANA() {
        return nominalTopupToDANA;
    }

    public static void setNominalTopUpToDANA(String nominalTopupToDANA) {
        InvestmentData.nominalTopupToDANA = nominalTopupToDANA;
    }

    public static String getPackageNameBukareksa() {
        return packageNameBukareksa;
    }

    public static void setPackageNameBukareksa(String packageNameBukareksa) {
        InvestmentData.packageNameBukareksa = packageNameBukareksa;
    }

    public static void setGoalInvestMinimumMonthlyAmount(String goalInvestMinimumMonthlyAmount){
        InvestmentData.goalInvestMinimumMonthlyAmount = goalInvestMinimumMonthlyAmount;
    }

    public static String getGoalInvestMinimumMonthlyAmount() {
        return goalInvestMinimumMonthlyAmount;
    }

    public static boolean getFromInvestasikuPage() {
        return fromInvestasikuPage;
    }

    public static void setFromInvestasikuPage(boolean fromInvestasikuPage) {
        InvestmentData.fromInvestasikuPage = fromInvestasikuPage;
    }

    public static boolean getUpdateNameFromAturTujuanForm() { return fromAturTujuanForm; }

    public static void setUpdateNameFromAturTujuanForm(boolean fromAturTujuanForm) {
        InvestmentData.fromAturTujuanForm = fromAturTujuanForm;
    }

    public static boolean getUserHaveGoalInvestProduct() {return userHaveGoalInvestProduct;}

    public static void setUserHaveGoalInvestProduct(boolean userHaveGoalInvestProduct) {
        InvestmentData.userHaveGoalInvestProduct = userHaveGoalInvestProduct;
    }

    public static void setGoldForecastDatePrediction(String goldForecastDate) {
        InvestmentData.goldForecastDatePrediction = goldForecastDate;
    }

    public static String getGoldForecastDatePrediction() {
        return goldForecastDatePrediction;
    }

    public static void setGoldForecastPricePrediction(String goldForecastPrice) {
        InvestmentData.goldForecastPricePrediction = goldForecastPrice;
    }

    public static String getGoldForecastPricePrediction() {
        return goldForecastPricePrediction;
    }

    public static boolean isFromGoldForecast() {
        return fromGoldForecast;
    }

    public static void setFromGoldForecast(boolean fromGoldForecast) {
        InvestmentData.fromGoldForecast = fromGoldForecast;
    }

    public static String getBukaEmasTransactionId() {
        return bukaEmasTransactionId;
    }

    public static void setBukaEmasTransactionId(String bukaEmasTransactionId) {
        InvestmentData.bukaEmasTransactionId = bukaEmasTransactionId;
    }

    public static String getBukaEmasPackagePurchaseNominal() {
        return bukaEmasPackagePurchaseNominal;
    }

    public static void setBukaEmasPackagePurchaseNominal(String bukaEmasPackagePurchaseNominal) {
        InvestmentData.bukaEmasPackagePurchaseNominal = bukaEmasPackagePurchaseNominal;
    }

    public static boolean getFromBukaReksaPackage() {
        return fromBukaReksaPackage;
    }

    public static void setFromBukaReksaPackage(boolean fromBukaReksaPackage) {
        InvestmentData.fromBukaReksaPackage = fromBukaReksaPackage;
    }

    public static boolean getDisclaimerState() {
        return disclaimerState;
    }

    public static void setDisclaimerState(boolean disclaimerState) {
        InvestmentData.disclaimerState = disclaimerState;
    }

    public static boolean getOnBoardingState() {
        return onBoardingState;
    }

    public static void setOnBoardingState(boolean onBoardingState) {
        InvestmentData.onBoardingState = onBoardingState;
    }

    public static boolean getCrossSellingTransaction() {
        return crossSellingTransaction;
    }

    public static void setCrossSellingTransaction(boolean crossSellingTransaction) {
        InvestmentData.crossSellingTransaction = crossSellingTransaction;
    }

    public static String getCrossSellingState() {
        return crossSellingState;
    }

    public static void setCrossSellingState(String crossSellingState) {
        InvestmentData.crossSellingState = crossSellingState;
    }

    public static String getLimitDANAamount() {
        return limitDANAamount;
    }

    public static void setLimitDANAamount(String limitDANAamount) {
        InvestmentData.limitDANAamount = limitDANAamount;
    }

    public static void setInstallmentTenure(String installmentTenure) {
        InvestmentData.installmentTenure = installmentTenure;
    }

    public static String getInstallmentTenure() {
        return installmentTenure;
    }

    public static void setMonthlyInstallmentNominal(String monthlyInstallmentNominal) {
        InvestmentData.monthlyInstallmentNominal = monthlyInstallmentNominal;
    }

    public static String getMonthlyInstallmentNominal() {
        return monthlyInstallmentNominal;
    }

    public static void setTotalInstallmentCheckoutPayment(String totalInstallmentCheckoutPayment) {
        InvestmentData.totalInstallmentCheckoutPayment = totalInstallmentCheckoutPayment;
    }

    public static String getTotalInstallmentCheckoutPayment() {
        return totalInstallmentCheckoutPayment;
    }

    public static void setProductTypeBukaEmas(String productTypeBukaEmas) {
        InvestmentData.productTypeBukaEmas = productTypeBukaEmas;
    }

    public static String getProductTypeBukaEmas() {
        return productTypeBukaEmas;
    }
}
