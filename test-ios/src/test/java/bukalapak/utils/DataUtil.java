package bukalapak.utils;

import bukalapak.data.*;
import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.data.vp.prepaid.UangElektronikData;
import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.data.vp.tix.TiketPesawatData;

public class DataUtil {

    public static void setDataAfterReset() {
        PulsaPrabayarData.setOnboarded(false);
        UserData.setUsername("");
        HomeData.setDanaOnBoardingState(false);
        HomeData.setOnBoardingState(false);
        KuponData.setCoachMarked(false);
        TiketPesawatData.setCoachMarked(false);
        TiketKeretaData.setCoachMarked(false);
        BusData.setCoachMarked(false);
        EventData.setCoachMarked(false);
        HelperData.setLastActionPage(null);
        SearchData.setOnBoard(false);
        InvestmentData.setDisclaimerState(false);
        InvestmentData.setOnBoardingState(false);
        BukaMobilData.setOnboarded(false);
        UserData.setLoggedIn(false);
        UserData.setFirstOpenApp(true);
        ListrikPrabayarData.setOnboarded(false);
        UangElektronikData.setInfoSkipped(false);
        PNRData.setIsOnRecoDOH(false);
    }

    public static void setDataAfterLogout() {
        UserData.setUsername("");
        UserData.setLoggedIn(false);
        HomeData.setOnBoardingState(false);
        BukaMobilData.setOnboarded(false);
    }

    public static void setDataAfterScenario() {
        UangElektronikData.setCardNumber("");
        InvestmentData.setFromBukaReksaPackage(false);
        InvestmentData.setFromInvestasikuPage(false);
        CategoryData.getRecoProductList().clear();
        TransactionData.getSavedSellerNotes().clear();
        CheckoutData.resetCheckoutData();
    }
}
