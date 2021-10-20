package bukalapak.data;

import java.util.ArrayList;

public class InsuranceData {

    private static int gadgetPrice;
    private static int goodsPremi;
    private static int logisticInsurancePrice;
    private static int returInsuranceFee;
    private static int tempFee;
    private static int sumFee;
    private static ArrayList<Integer> fee = new ArrayList<>();
    private static int digitalGoodsFee;
    private static String date;
    private static String insuredName;
    private static String insuredEmail;
    private static String insuredPhone;
    private static String chassisMotor;
    private static String plateNumberMotor;
    private static String brandMotor;
    private static String modelMotor;
    private static int insurancePrice = 0;
    private static String productName;
    private static String limitClaim;
    private static String validityPeriode;
    private static String premiValue;
    private static String covidInsurancedPrice;
    private static boolean promoActive = true;

    public static int getGadgetPrice() { return gadgetPrice; }

    public static void setGadgetPrice(int gadgetPrice) {
        InsuranceData.gadgetPrice = gadgetPrice;
    }

    public static int getGoodsPremi() { return goodsPremi; }

    public static void setGoodsPremi(int goodsPremi) {
        InsuranceData.goodsPremi = goodsPremi;
    }

    public static int getLogisticInsurancePrice() {
        return logisticInsurancePrice;
    }

    public static void setLogisticInsurancePrice(int fee) {
        InsuranceData.logisticInsurancePrice = fee;
    }

    public static int getReturInsuranceFee() {
        return returInsuranceFee;
    }

    public static void setReturInsuranceFee(int fee) {
        InsuranceData.returInsuranceFee = fee;
    }

    public static void setTempFee(int fee) {
        InsuranceData.tempFee = fee;
    }

    public static int getTempFee() {
        return tempFee;
    }

    public static int getDigitalGoodsFee() {
        return digitalGoodsFee;
    }

    public static void setDigitalGoodsFee(int digitalGoodsFee) {
        InsuranceData.digitalGoodsFee = digitalGoodsFee;
    }

    public static void setDate(String date) {
        InsuranceData.date = date;
    }

    public static String getDate() {
        return date;
    }

    public static void setInsuranceFee(int fee) {
        InsuranceData.fee.add(fee);
    }

    public static ArrayList<Integer> getInsuranceFee() {
        return fee;
    }

    public static void setSumFee(int sumFee) {
        InsuranceData.sumFee = sumFee;
    }

    public static int getSumFee() {
        return sumFee;
    }

    public static void setInsuredName(String insuredName) {
        InsuranceData.insuredName = insuredName;
    }

    public static String getInsuredEmail() {
        return insuredEmail;
    }

    public static void setInsuredEmail(String insuredEmail) {
        InsuranceData.insuredEmail = insuredEmail;
    }

    public static String getInsuredPhone() {
        return insuredPhone;
    }

    public static void setInsuredPhone(String insuredPhone) {
        InsuranceData.insuredPhone = insuredPhone;
    }

    public static String getChassisMotor() {
        return chassisMotor;
    }

    public static void setChassisMotor(String chassisMotor) {
        InsuranceData.chassisMotor = chassisMotor;
    }

    public static String getPlateNumberMotor() {
        return plateNumberMotor;
    }

    public static void setPlateNumberMotor(String plateNumberMotor) {
        InsuranceData.plateNumberMotor = plateNumberMotor;
    }

    public static String getBrandMotor() {
        return brandMotor;
    }

    public static void setBrandMotor(String brandMotor) {
        InsuranceData.brandMotor = brandMotor;
    }

    public static String getModelMotor() {
        return modelMotor;
    }

    public static void setModelMotor(String modelMotor) {
        InsuranceData.modelMotor = modelMotor;
    }

    public static void setInsurancePrice(int insurancePrice) {
        InsuranceData.insurancePrice = insurancePrice;
    }

    public static void setProductName(String productName) {
        InsuranceData.productName = productName;
    }

    public static void setLimitClaim(String limitClaim) {
        InsuranceData.limitClaim = limitClaim;
    }

    public static String getProductName() { return productName;
    }

    public static String getLimitClaim() { return limitClaim;
    }

    public static Integer getInsurancePrice() {
        return insurancePrice;
    }

    public static String getInsuredName() {
        return insuredName;
    }

    public static String getCovidInsurancedPrice() {
        return covidInsurancedPrice;
    }

    public static void setCovidInsurancedPrice(String covidInsurancedPrice) {
        InsuranceData.covidInsurancedPrice = covidInsurancedPrice;
    }
    public static String getValidityPeriode() {
        return validityPeriode;
    }

    public static void setValidityPeriode(String validityPeriode) {
        InsuranceData.validityPeriode = validityPeriode;
    }

    public static String getPremiValue() {
        return premiValue;
    }

    public static void setPremiValue(String premiValue) {
        InsuranceData.premiValue = premiValue;
    }

    public static boolean isPromoActive() {
        return promoActive;
    }

    public static void setPromoActive(boolean status) {
        InsuranceData.promoActive = status;
    }
}
