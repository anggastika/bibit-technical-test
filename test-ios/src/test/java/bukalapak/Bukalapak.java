package bukalapak;

import bukalapak.pageObject.*;
import bukalapak.pageObject.bee.bukaMobil.*;
import bukalapak.pageObject.bee.bukaSend.Native.BukaSendPage;
import bukalapak.pageObject.bee.bukaSend.WebView.BukaSendWebViewFormPage;
import bukalapak.pageObject.bee.bukaSend.WebView.BukaSendWebViewLandingPage;
import bukalapak.pageObject.dana.DANATopUpCheckoutPage;
import bukalapak.pageObject.games.KumpulinkPage;
import bukalapak.pageObject.games.LuckyDipPage;
import bukalapak.pageObject.games.PohonRejekiPage;
import bukalapak.pageObject.gro.NabungDiskonPage;
import bukalapak.pageObject.investment.*;
import bukalapak.pageObject.lsf.LiveStreamingWebPage;
import bukalapak.pageObject.martech.ReferralLinkPage;
import bukalapak.pageObject.martech.VaccinePage;
import bukalapak.pageObject.mtx.CheckoutRevampMarketplacePage;
import bukalapak.pageObject.pnl.BRICeriaPage;
import bukalapak.pageObject.mtx.TransactionListRevampPage;
import bukalapak.pageObject.pnl.LinkJualBeliPage;
import bukalapak.pageObject.pnl.RecurringTransactionDetailPage;
import bukalapak.pageObject.pnl.RecurringTransactionFormPage;
import bukalapak.pageObject.prom.PROMBasePage;
import bukalapak.pageObject.vouchers.VoucherDetailKaryawanLapakPage;
import bukalapak.pageObject.vouchers.VoucherDetailPage;
import bukalapak.pageObject.vouchers.VoucherkuDetailPage;
import bukalapak.pageObject.vouchers.VoucherkuPromoPage;
import bukalapak.pageObject.vp.HistoryTransactionDetailPage;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import bukalapak.pageObject.vp.base.VpConfirmationCheckOutPage;
import bukalapak.pageObject.vp.base.VpInvoiceDetailsPage;
import bukalapak.pageObject.vp.base.VpTransactionHistoryPage;
import bukalapak.pageObject.vp.insurance.*;
import bukalapak.pageObject.vp.insurance.asuransiperjalanan.AsuransiPerjalananLandingPage;
import bukalapak.pageObject.vp.insurance.bukatabungan.BukaTabunganLandingPage;
import bukalapak.pageObject.vp.postpaid.*;
import bukalapak.pageObject.vp.prepaid.listrikprabayar.ListrikPrabayarCheckOutPage;
import bukalapak.pageObject.vp.prepaid.listrikprabayar.ListrikPrabayarInvoiceDetailsPage;
import bukalapak.pageObject.vp.prepaid.listrikprabayar.ListrikPrabayarLandingPage;
import bukalapak.pageObject.vp.prepaid.listrikprabayar.ListrikPrabayarTransactionHistoryPage;
import bukalapak.pageObject.vp.prepaid.paketdata.PaketDataCheckOutPage;
import bukalapak.pageObject.vp.prepaid.paketdata.PaketDataInvoiceDetailsPage;
import bukalapak.pageObject.vp.prepaid.paketdata.PaketDataLandingPage;
import bukalapak.pageObject.vp.prepaid.paketdata.PaketDataTransactionHistoryPage;
import bukalapak.pageObject.vp.prepaid.pulsaprabayar.PulsaPrabayarCheckOutPage;
import bukalapak.pageObject.vp.prepaid.pulsaprabayar.PulsaPrabayarInvoiceDetailPage;
import bukalapak.pageObject.vp.prepaid.pulsaprabayar.PulsaPrabayarLandingPage;
import bukalapak.pageObject.vp.prepaid.uangelektronik.UangElektronikCheckOutPage;
import bukalapak.pageObject.vp.prepaid.uangelektronik.UangElektronikInvoiceDetailsPage;
import bukalapak.pageObject.vp.prepaid.uangelektronik.UangElektronikLandingPage;
import bukalapak.pageObject.vp.prepaid.uangelektronik.UangElektronikTransactionHistoryPage;
import bukalapak.pageObject.vp.tix.giftCard.GiftCardCheckoutPage;
import bukalapak.pageObject.vp.tix.giftCard.GiftCardInvoiceDetailPage;
import bukalapak.pageObject.vp.tix.giftCard.GiftCardLandingPage;
import bukalapak.pageObject.vp.tix.giftCard.GiftCardProductDetailPage;
import bukalapak.pageObject.vp.tix.hotel.*;
import bukalapak.pageObject.vp.tix.subscription.*;
import bukalapak.pageObject.vp.tix.tiketBus.*;
import bukalapak.pageObject.vp.tix.tiketEvent.TiketEventBookingPage;
import bukalapak.pageObject.vp.tix.tiketEvent.TiketEventCheckoutPage;
import bukalapak.pageObject.vp.tix.tiketEvent.TiketEventLandingPage;
import bukalapak.pageObject.vp.tix.tiketEvent.TiketEventTransactionDetailPage;
import bukalapak.pageObject.vp.tix.tiketKereta.*;
import bukalapak.pageObject.vp.tix.tiketpesawat.*;
import bukalapak.utils.APICall;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @author Irfan Mauludin, 26/09/19
 */
public class Bukalapak {

    private IOSDriver<IOSElement> iosDriver;

    public Bukalapak(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }

    public APICall apiCall() {
        return new APICall();
    }

    @Deprecated
    /**
     * This method is deprecated. The methods (which are inherited from BasePage class) from a page object class,
     * must be invoked from the child class itself, instead of BasePage.
     */
    public BasePage basePage() {
        return new BasePage(iosDriver);
    }

    public IOSBasePage iOSBasePage() {
        return new IOSBasePage(iosDriver);
    }

    public AkulakuPage akulakuPage() {
        return new AkulakuPage(iosDriver);
    }

    public AkunPage akunPage() {
        return new AkunPage(iosDriver);
    }

    public AlamatBaruDanEditPage alamatBaruDanEditPage() {
        return new AlamatBaruDanEditPage(iosDriver);
    }

    public AlamatPage alamatPage() {
        return new AlamatPage(iosDriver);
    }

    public AttachmentMenuPage attachmentMenuPage() {
        return new AttachmentMenuPage(iosDriver);
    }

    public BarangDijualPage barangDijualPage() {
        return new BarangDijualPage(iosDriver);
    }

    public BarangDrafPage barangDrafPage() {
        return new BarangDrafPage(iosDriver);
    }

    public BCAKlikPayPage bcaKlikPayPage() {
        return new BCAKlikPayPage(iosDriver);
    }

    public BelumDikirimPage belumDikirimPage() {
        return new BelumDikirimPage(iosDriver);
    }

    public BelumDiprosesPage belumDiprosesPage() {
        return new BelumDiprosesPage(iosDriver);
    }

    public BpjsKesehatanPage bpjsKesehatanPage() {
        return new BpjsKesehatanPage(iosDriver);
    }

    public BRIEpayPage briEpayPage() {
        return new BRIEpayPage(iosDriver);
    }

    public BuatVoucherPage buatVoucherPage() {
        return new BuatVoucherPage(iosDriver);
    }

    public BukaBantuanPage bukaBantuanPage() {
        return new BukaBantuanPage(iosDriver);
    }

    public BukaBantuanPremiumPage bukaBantuanPremiumPage() {
        return new BukaBantuanPremiumPage(iosDriver);
    }

    public BukaEmasPage bukaEmasPage() {
        return new BukaEmasPage(iosDriver);
    }

    public BukaIklanFAQPage bukaIklanFAQPage() {
        return new BukaIklanFAQPage(iosDriver);
    }

    public BukaIklanPage bukaIklanPage() {
        return new BukaIklanPage(iosDriver);
    }

    public BukaIklanReportPage bukaIklanReportPage() {
        return new BukaIklanReportPage(iosDriver);
    }

    public BukamallAlchemyPage bukamallAlchemyPage() {
        return new BukamallAlchemyPage(iosDriver);
    }

    public BukamallBrandPage bukamallBrandPage() {
        return new BukamallBrandPage(iosDriver);
    }

    public BukamallBrandPDPPage bukamallBrandPDPPage() {
        return new BukamallBrandPDPPage(iosDriver);
    }

    public BukamallBrandProductSearchResultPage bukamallBrandProductSearchResultPage() {
        return new BukamallBrandProductSearchResultPage(iosDriver);
    }

    public BukamallBrandSearchResultPage bukamallBrandSearchResultPage() {
        return new BukamallBrandSearchResultPage(iosDriver);
    }

    public BukamallBrandTerbaruAlchemyPage bukamallBrandTerbaruAlchemyPage() {
        return new BukamallBrandTerbaruAlchemyPage(iosDriver);
    }

    public BukamallBrandTerbaruPage bukamallBrandTerbaruPage() {
        return new BukamallBrandTerbaruPage(iosDriver);
    }

    public BukamallCatalogPage bukamallCatalogPage() {
        return new BukamallCatalogPage(iosDriver);
    }

    public BukamallCategoryPageAlchemyPage bukamallCategoryPageAlchemyPage() {
        return new BukamallCategoryPageAlchemyPage(iosDriver);
    }

    public BukamallProdukPilihanPage bukamallProdukPilihanPage() {
        return new BukamallProdukPilihanPage(iosDriver);
    }

    public BukaMobilPage bukaMobilPage() {
        return new BukaMobilPage(iosDriver);
    }

    public BukaMobilOnboardingPage bukaMobilOnboardingPage() {
        return new BukaMobilOnboardingPage(iosDriver);
    }

    public BukaMobilClassificationPage bukaMobilClassificationPage() {
        return new BukaMobilClassificationPage(iosDriver);
    }

    public BukaMobilBrandPage bukaMobilBrandPage() {
        return new BukaMobilBrandPage(iosDriver);
    }

    public BukaMobilCarSelectionPage bukaMobilCarSelectionPage() {
        return new BukaMobilCarSelectionPage(iosDriver);
    }

    public BukaMobilChatPage bukaMobilChatPage() {
        return new BukaMobilChatPage(iosDriver);
    }

    public BukaMobilSearchResultPage bukaMobilSearchResultPage() {
        return new BukaMobilSearchResultPage(iosDriver);
    }

    public BukaMobilPDP bukaMobilPDP() {
        return new BukaMobilPDP(iosDriver);
    }

    public BukaMobilProposalPage bukaMobilProposalPage() {
        return new BukaMobilProposalPage(iosDriver);
    }

    public BukaMobilTransactionPage bukaMobilTransactionPage() {
        return new BukaMobilTransactionPage(iosDriver);
    }

    public BukaQuranPage bukaQuranPage() {
        return new BukaQuranPage(iosDriver);
    }

    public BukaQuranSurahPage bukaQuranSurahPage() {
        return new BukaQuranSurahPage(iosDriver);
    }

    public BukaQuranSurahSearchPage bukaQuranSurahSearchPage() {
        return new BukaQuranSurahSearchPage(iosDriver);
    }

    public BukaReksaPage bukaReksaPage() {
        return new BukaReksaPage(iosDriver);
    }

    public BukaReksaHomePage bukaReksaHomePage() {
        return new BukaReksaHomePage(iosDriver);
    }

    public BukaReksaPortofolioPage bukaReksaPortofolioPage() {
        return new BukaReksaPortofolioPage(iosDriver);
    }

    public BukaReksaProductPage bukaReksaProductPage() {
        return new BukaReksaProductPage(iosDriver);
    }

    public BukaReksaProfilePage bukaReksaProfilePage() {
        return new BukaReksaProfilePage(iosDriver);
    }

    public BukaReksaRecurringPage bukaReksaRecurringPage() {
        return new BukaReksaRecurringPage(iosDriver);
    }

    public BukaReksaTransactionPage bukaReksaTransactionPage() {
        return new BukaReksaTransactionPage(iosDriver);
    }

    public BukaReksaInstantInvestPage bukaReksaInvestasiButtonPage() {
        return new BukaReksaInstantInvestPage(iosDriver);
    }

    public BukaReksaGoalInvestmentPage bukaReksaGoalInvestmentPage() {
        return new BukaReksaGoalInvestmentPage(iosDriver);
    }

    public InvestasikuPage investasikuPage() {
        return new InvestasikuPage(iosDriver);
    }

    public BukaReksaPackagePage bukaReksaPackagePage() {
        return new BukaReksaPackagePage(iosDriver);
    }

    public BukaSendPage bukaSendPage() {
        return new BukaSendPage(iosDriver);
    }

    public BukaSendWebViewLandingPage bukaSendWebViewLandingPage() {
        return new BukaSendWebViewLandingPage(iosDriver);
    }

    public BukaSendWebViewFormPage bukaSendWebViewFormPage() {
        return new BukaSendWebViewFormPage(iosDriver);
    }

    public BukareviewArticleDetailPage bukareviewArticleDetailPage() {
        return new BukareviewArticleDetailPage(iosDriver);
    }

    public BukareviewAuthorPage bukareviewAuthorPage() {
        return new BukareviewAuthorPage(iosDriver);
    }

    public BukareviewCategoriesPage bukareviewCategoriesPage() {
        return new BukareviewCategoriesPage(iosDriver);
    }

    public BukareviewHomePage bukareviewHomePage() {
        return new BukareviewHomePage(iosDriver);
    }

    public BukareviewSearchPage bukareviewSearchPage() {
        return new BukareviewSearchPage(iosDriver);
    }

    public BukaReviewTagTypeSubCategoryPage bukaReviewTagTypeSubCategoryPage() {
        return new BukaReviewTagTypeSubCategoryPage(iosDriver);
    }

    public BukaGlobalPage bukaGlobalPage() {
        return new BukaGlobalPage(iosDriver);
    }

    public CableTvPage cableTvPage() {
        return new CableTvPage(iosDriver);
    }

    public CableTvCheckoutPage cableTvCheckoutPage() {
        return new CableTvCheckoutPage(iosDriver);
    }

    public CartPage cartPage() {
        return new CartPage(iosDriver);
    }

    public CatalogProductListingPage catalogProductListingPage() {
        return new CatalogProductListingPage(iosDriver);
    }

    public CatalogReviewListingPage catalogReviewListingPage() {
        return new CatalogReviewListingPage(iosDriver);
    }

    public CategoryFashionWanitaPage categoryFashionWanitaPage() {
        return new CategoryFashionWanitaPage(iosDriver);
    }

    public CategoryPage categoryPage() {
        return new CategoryPage(iosDriver);
    }

    public CekKurirOngkirProductDetailPage cekKurirOngkirProductDetailPage() {
        return new CekKurirOngkirProductDetailPage(iosDriver);
    }

    public CetakPesananPenjualanPage cetakPesananPenjualanPage() {
        return new CetakPesananPenjualanPage(iosDriver);
    }

    public ChatAssistantPage chatAssistantPage() {
        return new ChatAssistantPage(iosDriver);
    }

    public ChatListPage chatListPage() {
        return new ChatListPage(iosDriver);
    }

    public ChatRoomPage chatRoomPage() {
        return new ChatRoomPage(iosDriver);
    }

    public CheckoutMarketplaceConfirmationPage checkoutMarketplaceConfirmationPage() {
        return new CheckoutMarketplaceConfirmationPage(iosDriver);
    }

    public CheckoutMarketplacePage checkoutMarketplacePage() {
        return new CheckoutMarketplacePage(iosDriver);
    }

    public CheckoutNonMarketplacePage checkoutNonMarketplacePage() {
        return new CheckoutNonMarketplacePage(iosDriver);
    }

    public CheckoutNonMarketplaceConfirmationPage checkoutNonMarketplaceConfirmationPage() {
        return new CheckoutNonMarketplaceConfirmationPage(iosDriver);
    }

    public CheckoutPage checkoutPage() {
        return new CheckoutPage(iosDriver);
    }

    public ChooseCourierPage chooseCourierPage() {
        return new ChooseCourierPage(iosDriver);
    }

    public CicilanTanpaKartuKreditPage cicilanTanpaKartuKreditPage() {
        return new CicilanTanpaKartuKreditPage(iosDriver);
    }

    public CimbClickPage cimbClickPage() {
        return new CimbClickPage(iosDriver);
    }

    public ContinueCreditCardPage continueCreditCardPage() {
        return new ContinueCreditCardPage(iosDriver);
    }

    public CreateEtalasePage createEtalasePage() {
        return new CreateEtalasePage(iosDriver);
    }

    public CreateProductPage createProductPage() {
        return new CreateProductPage(iosDriver);
    }

    public CreditCardBillPage creditCardBillPage() {
        return new CreditCardBillPage(iosDriver);
    }

    public CreditCardDetailPage creditCardDetailPage() {
        return new CreditCardDetailPage(iosDriver);
    }

    public CreditCardListPage creditCardListPage() {
        return new CreditCardListPage(iosDriver);
    }

    public CreditCardPage creditCardPage() {
        return new CreditCardPage(iosDriver);
    }

    public ApplyCCPage applyCCPage() {
        return new ApplyCCPage(iosDriver);
    }

    public DaftarPage daftarPage() {
        return new DaftarPage(iosDriver);
    }

    public DaftarPembelianPremiumPage daftarPembelianPremiumPage() {
        return new DaftarPembelianPremiumPage(iosDriver);
    }

    public DANABindingPage danaBindingPage() {
        return new DANABindingPage(iosDriver);
    }

    public DANAPaymentPage danaPaymentPage() {
        return new DANAPaymentPage(iosDriver);
    }

    public DANADonateCreditsPage danaDonateCreditsPage() {
        return new DANADonateCreditsPage(iosDriver);
    }

    public DANAVoucherkuPage danaVoucherkuPage() {
        return new DANAVoucherkuPage(iosDriver);
    }

    public DANAPengaturanPembayaranPage danaPengaturanPembayaranPage() {
        return new DANAPengaturanPembayaranPage(iosDriver);
    }

    public DANATopUpCheckoutPage danaTopUpCheckoutPage() {
        return new DANATopUpCheckoutPage(iosDriver);
    }

    public DatePickerAlchemyPage datePickerAlchemyPage() {
        return new DatePickerAlchemyPage(iosDriver);
    }

    public DetailKomplainPage detailKomplainPage() {
        return new DetailKomplainPage(iosDriver);
    }

    public NewReturnPage newReturnPage() {
        return new NewReturnPage(iosDriver);
    }

    public DetailPembelianMarketplacePage detailPembelianMarketplacePage() {
        return new DetailPembelianMarketplacePage(iosDriver);
    }

    public DetailPenjualanPage detailPenjualanPage() {
        return new DetailPenjualanPage(iosDriver);
    }

    public DetailTransaksiPenjualanAlchemyPage detailTransaksiPenjualanAlchemyPage() {
        return new DetailTransaksiPenjualanAlchemyPage(iosDriver);
    }

    public DiscoverPage discoverPage() {
        return new DiscoverPage(iosDriver);
    }

    public DiskusiKomplainPage diskusiKomplainPage() {
        return new DiskusiKomplainPage(iosDriver);
    }

    public DopePage dopePage() {
        return new DopePage(iosDriver);
    }

    public EditRekeningPage editRekeningPage() {
        return new EditRekeningPage(iosDriver);
    }

    public EditProductPage editProductPage() {
        return new EditProductPage(iosDriver);
    }

    public EsamsatPage esamsatPage() {
        return new EsamsatPage(iosDriver);
    }

    public FavoritePage favoritePage() {
        return new FavoritePage(iosDriver);
    }

    public FilterProductPage filterProductPage() {
        return new FilterProductPage(iosDriver);
    }

    public FlashDealProductListPage flashDealProductListPage() {
        return new FlashDealProductListPage(iosDriver);
    }

    public GeraiPage geraiPage() {
        return new GeraiPage(iosDriver);
    }

    public HighlightPage highlightPage() {
        return new HighlightPage(iosDriver);
    }

    public HistoryPage historyPage() {
        return new HistoryPage(iosDriver);
    }

    public HomePage homePage() {
        return new HomePage(iosDriver);
    }

    public IklanLapakPage iklanLapakPage() {
        return new IklanLapakPage(iosDriver);
    }

    public InfluencerPage influencerPage() {
        return new InfluencerPage(iosDriver);
    }

    public InputResiAlchemyPage inputResiAlchemyPage() {
        return new InputResiAlchemyPage(iosDriver);
    }

    public InputResiPage inputResiPage() {
        return new InputResiPage(iosDriver);
    }

    public InspirationPage inspirationPage() {
        return new InspirationPage(iosDriver);
    }

    public InspirasiPenjualanPage inspirasiPenjualanPage() {
        return new InspirasiPenjualanPage(iosDriver);
    }

    public InternetBankingPage internetBankingPage() {
        return new InternetBankingPage(iosDriver);
    }

    public InvoiceDetailBukaPengirimanPage invoiceDetailBukaPengirimanPage() {
        return new InvoiceDetailBukaPengirimanPage(iosDriver);
    }

    public InvoiceDetailNonMarketplacePage invoiceDetailNonMarketplacePage() {
        return new InvoiceDetailNonMarketplacePage(iosDriver);
    }

    public InvoiceDetailPage invoiceDetailPage() {
        return new InvoiceDetailPage(iosDriver);
    }

    public InvoiceListPage invoiceListPage() {
        return new InvoiceListPage(iosDriver);
    }

    public JasaPengirimanPage jasaPengirimanPage() {
        return new JasaPengirimanPage(iosDriver);
    }

    public JasaPengirimanProductPage jasaPengirimanProductPage() {
        return new JasaPengirimanProductPage(iosDriver);
    }

    public KaryawanLapakPage karyawanLapakPage() {
        return new KaryawanLapakPage(iosDriver);
    }

    public KirimBarangBukaSendPage kirimBarangBukaSendPage() {
        return new KirimBarangBukaSendPage(iosDriver);
    }

    public KomplainPage komplainPage() {
        return new KomplainPage(iosDriver);
    }

    public KonfirmasiTerimaBarangPage konfirmasiTerimaBarangPage() {
        return new KonfirmasiTerimaBarangPage(iosDriver);
    }

    public KredivoPage kredivoPage() {
        return new KredivoPage(iosDriver);
    }

    public KumpulinkWebPage kumpulinkWebPage() {
        return new KumpulinkWebPage(iosDriver);
    }

    public KumpulinkPage kumpulinkPage() {
        return new KumpulinkPage(iosDriver);
    }

    public DailyCheckinWebPage dailycheckinWebPage() {
        return new DailyCheckinWebPage(iosDriver);
    }

    public DailyTaskWebPage dailyTaskWebPage() {
        return new DailyTaskWebPage(iosDriver);
    }

    public LiveStreamingWebPage liveStreamingWebPage() {
        return new LiveStreamingWebPage(iosDriver);
    }

    public LapakmuPage lapakmuPage() {
        return new LapakmuPage(iosDriver);
    }

    public ReportPage reportPage() {
        return new ReportPage(iosDriver);
    }

    public LinkAjaPage linkAjaPage() {
        return new LinkAjaPage(iosDriver);
    }

    public LoginPage loginPage() {
        return new LoginPage(iosDriver);
    }

    public MerchantPage merchantPage() {
        return new MerchantPage(iosDriver);
    }

    public MessageTemplatePage messageTemplatePage() {
        return new MessageTemplatePage(iosDriver);
    }

    public MetodePembayaranPage metodePembayaranPage() {
        return new MetodePembayaranPage(iosDriver);
    }

    public MidtransPage midtransPage() {
        return new MidtransPage(iosDriver);
    }

    public MPNG3FormPage mpng3FormPage() {
        return new MPNG3FormPage(iosDriver);
    }

    public MPNG3InfoPage mpng3InfoPage() {
        return new MPNG3InfoPage(iosDriver);
    }

    public MPNG3InquiryPage mpng3InquiryPage() {
        return new MPNG3InquiryPage(iosDriver);
    }

    public MPNG3InvoiceDetailPage mpng3InvoiceDetailPage() {
        return new MPNG3InvoiceDetailPage(iosDriver);
    }

    public MPNG3PaymentPage mpng3PaymentPage() {
        return new MPNG3PaymentPage(iosDriver);
    }

    public MultifinancePage multifinancePage() {
        return new MultifinancePage(iosDriver);
    }

    public KartuKreditCheckoutPage kartuKreditCheckoutPage() {
        return new KartuKreditCheckoutPage(iosDriver);
    }

    public KartuKreditInvoiceDetailPage kartuKreditInvoiceDetailPage() {
        return new KartuKreditInvoiceDetailPage(iosDriver);
    }

    public NewUserWidgetPage newUserWidgetPage() {
        return new NewUserWidgetPage(iosDriver);
    }

    public NotificationsPage notificationsPage() {
        return new NotificationsPage(iosDriver);
    }

    public OnTripPage onTripPage() {
        return new OnTripPage(iosDriver);
    }

    public OTPPage otpPage() {
        return new OTPPage(iosDriver);
    }

    public PajakDaerahConfirmDataPage pajakDaerahConfirmDataPage() {
        return new PajakDaerahConfirmDataPage(iosDriver);
    }

    public PajakDaerahCustomerInfoPage pajakDaerahCustomerInfoPage() {
        return new PajakDaerahCustomerInfoPage(iosDriver);
    }

    public PajakDerahCheckoutPage pajakDerahCheckoutPage() {
        return new PajakDerahCheckoutPage(iosDriver);
    }

    public PajakDaerahInvoiceDetailPage pajakDaerahInvoiceDetailPage() {
        return new PajakDaerahInvoiceDetailPage(iosDriver);
    }

    public PajakDaerahPage pajakDaerahPage() {
        return new PajakDaerahPage(iosDriver);
    }

    public PantauanSainganPage pantauanSainganPage() {
        return new PantauanSainganPage(iosDriver);
    }

    public PantauBarangSainganPage pantauBarangSainganPage() {
        return new PantauBarangSainganPage(iosDriver);
    }

    public PantauBarangSainganFormPage pantauBarangSainganFormPage() {
        return new PantauBarangSainganFormPage(iosDriver);
    }

    public PaymentConfirmationPage paymentConfirmationPage() {
        return new PaymentConfirmationPage(iosDriver);
    }

    public PaymentPage paymentPage() {
        return new PaymentPage(iosDriver);
    }

    public PaymentStatusPage paymentStatusPage() {
        return new PaymentStatusPage(iosDriver);
    }

    public PdamPage pdamPage() {
        return new PdamPage(iosDriver);
    }

    public PDAMInvoiceDetailPage pdamInvoiceDetailPage() {
        return new PDAMInvoiceDetailPage(iosDriver);
    }

    public PDAMCheckoutPage pdamCheckoutPage() {
        return new PDAMCheckoutPage(iosDriver);
    }

    public MultifinanceInvoiceDetailPage multifinanceInvoiceDetailPage() {
        return new MultifinanceInvoiceDetailPage(iosDriver);
    }

    public BPJSInvoiceDetailPage bpjsInvoiceDetailPage() {
        return new BPJSInvoiceDetailPage(iosDriver);
    }

    public BPJSCheckoutPage bpjsCheckoutPage() {
        return new BPJSCheckoutPage(iosDriver);
    }

    public CableTvInvoiceDetailPage cableTvInvoiceDetailPage() {
        return new CableTvInvoiceDetailPage(iosDriver);
    }

    public PelangganPelapakPage pelangganPelapakPage() {
        return new PelangganPelapakPage(iosDriver);
    }

    public PemasukanPage pemasukanPage() {
        return new PemasukanPage(iosDriver);
    }

    public GantiPeriodePendapatanPage gantiPeriodePendapatanPage() {
        return new GantiPeriodePendapatanPage(iosDriver);
    }

    public PembeliPrioritasBerhentiBerlanggananPage pembeliPrioritasBerhentiBerlanggananPage() {
        return new PembeliPrioritasBerhentiBerlanggananPage(iosDriver);
    }

    public PembeliPrioritasCheckoutPage pembeliPrioritasCheckoutPage() {
        return new PembeliPrioritasCheckoutPage(iosDriver);
    }

    public PembeliPrioritasFreeTrialPage pembeliPrioritasFreeTrialPage() {
        return new PembeliPrioritasFreeTrialPage(iosDriver);
    }

    public PembeliPrioritasPage pembeliPrioritasPage() {
        return new PembeliPrioritasPage(iosDriver);
    }

    public PembeliPrioritasRiwayatCashbackPage pembeliPrioritasRiwayatCashbackPage() {
        return new PembeliPrioritasRiwayatCashbackPage(iosDriver);
    }

    public PembeliPrioritasTagihanPage pembeliPrioritasTagihanPage() {
        return new PembeliPrioritasTagihanPage(iosDriver);
    }

    public PembiayaanTunaiPage pembiayaanTunaiPage() {
        return new PembiayaanTunaiPage(iosDriver);
    }

    public PembiayaanTunaiOnBoardingPage pembiayaanTunaiOnBoardingPage() {
        return new PembiayaanTunaiOnBoardingPage(iosDriver);
    }

    public PembiayaanTunaiDetailPage pembiayaanTunaiDetailPage() {
        return new PembiayaanTunaiDetailPage(iosDriver);
    }

    public PembiayaanTunaiDashboardPage pembiayaanTunaiDashboardPage() {
        return new PembiayaanTunaiDashboardPage(iosDriver);
    }

    public PembiayaanTunaiCalculatorPage pembiayaanTunaiCalculatorPage() {
        return new PembiayaanTunaiCalculatorPage(iosDriver);
    }

    public PembiayaanTunaiListPartnerPage pembiayaanTunaiListPartnerPage() {
        return new PembiayaanTunaiListPartnerPage(iosDriver);
    }

    public PembiayaanTunaiTakePhotoPage pembiayaanTunaiTakePhotoPage() {
        return new PembiayaanTunaiTakePhotoPage(iosDriver);
    }

    public PembiayaanTunaiKycPreviewPage pembiayaanTunaiKycPreviewPage() {
        return new PembiayaanTunaiKycPreviewPage(iosDriver);
    }

    public PembiayaanTunaiPartnerWebviewPage pembiayaanTunaiPartnerWebviewPage() {
        return new PembiayaanTunaiPartnerWebviewPage(iosDriver);
    }

    public PembiayaanTunaiKycPage pembiayaanTunaiKycPage() {
        return new PembiayaanTunaiKycPage(iosDriver);
    }

    public PendapatanPage pendapatanPage() {
        return new PendapatanPage(iosDriver);
    }

    public FinancingHomePage financingHomePage() {
        return new FinancingHomePage(iosDriver);
    }

    public PengajuanKomplainPage pengajuanKomplainPage() {
        return new PengajuanKomplainPage(iosDriver);
    }

    public PengaturanAkunPage pengaturanAkunPage() {
        return new PengaturanAkunPage(iosDriver);
    }

    public PengaturanPembayaranPage pengaturanPembayaranPage() {
        return new PengaturanPembayaranPage(iosDriver);
    }

    public PengaturanRekeningPage pengaturanRekeningPage() {
        return new PengaturanRekeningPage(iosDriver);
    }

    public PilihAlamatUtamaPage pilihAlamatUtamaPage() {
        return new PilihAlamatUtamaPage(iosDriver);
    }

    public PilihCicilanKredivoPage pilihCicilanKredivoPage() {
        return new PilihCicilanKredivoPage(iosDriver);
    }

    public PilihNomorKartuPage pilihNomorKartuPage() {
        return new PilihNomorKartuPage(iosDriver);
    }

    public PolaPemasukanPage polaPemasukanPage() {
        return new PolaPemasukanPage(iosDriver);
    }

    public PostpaidBasePage postpaidBasePage() {
        return new PostpaidBasePage(iosDriver);
    }

    public PostpaidElectricityPage postpaidElectricityPage() {
        return new PostpaidElectricityPage(iosDriver);
    }

    public PostpaidElectricityNonBillPage postpaidElectricityNonBillPage() {
        return new PostpaidElectricityNonBillPage(iosDriver);
    }

    public ListrikPascabayarInvoiceDetailPage listrikPascabayarInvoiceDetailPage() {
        return new ListrikPascabayarInvoiceDetailPage(iosDriver);
    }

    public ListrikPascabayarCheckoutPage listrikPascabayarCheckoutPage() {
        return new ListrikPascabayarCheckoutPage(iosDriver);
    }

    public PenerimaanNegaraLandingPage penerimaanNegaraLandingPage() {
        return new PenerimaanNegaraLandingPage(iosDriver);
    }

    public BukaRumahLandingPage bukaRumahLandingPage() {
        return new BukaRumahLandingPage(iosDriver);
    }

    public BukaRumahPropertyApplyPage bukaRumahPropertyApplyPage() {
        return new BukaRumahPropertyApplyPage(iosDriver);
    }

    public PremiAsuransiLandingPage premiAsuransiLandingPage() {
        return new PremiAsuransiLandingPage(iosDriver);
    }

    public AddonIndihomePage addonIndihomePage() {
        return new AddonIndihomePage(iosDriver);
    }

    public AddOnIndihomeCheckoutPage addOnIndihomeCheckoutPage() {
        return new AddOnIndihomeCheckoutPage(iosDriver);
    }

    public PostpaidPhoneCreditPage postpaidPhoneCreditPage() {
        return new PostpaidPhoneCreditPage(iosDriver);
    }

    public PulsaPascabayarCheckoutPage pulsaPascabayarCheckoutPage() {
        return new PulsaPascabayarCheckoutPage(iosDriver);
    }

    public PulsaPascabayarInvoiceDetailPage pulsaPascabayarInvoiceDetailPage() {
        return new PulsaPascabayarInvoiceDetailPage(iosDriver);
    }

    public PremiAsuransiCheckOutPage premiAsuransiCheckOutPage() {
        return new PremiAsuransiCheckOutPage(iosDriver);
    }

    public AsuransiBepergianLandingPage asuransiBepergianLandingPage() {
        return new AsuransiBepergianLandingPage(iosDriver);
    }

    public AsuransiBepergianDetailPurchasedPage asuransiBepergianDetailPurchasedPage() {
        return new AsuransiBepergianDetailPurchasedPage(iosDriver);
    }

    public InsuranceProductDetailPage insuranceProductDetailPage() {
        return new InsuranceProductDetailPage(iosDriver);
    }

    public LeadGeneratorLandingPage leadGeneratorLandingPage() {
        return new LeadGeneratorLandingPage(iosDriver);
    }

    public LeadGeneratorSuccessRegistrationPage leadGeneratorSuccessRegistrationPage() {
        return new LeadGeneratorSuccessRegistrationPage(iosDriver);
    }

    public LeadGeneratorDetailPage leadGeneratorDetailPage() {
        return new LeadGeneratorDetailPage(iosDriver);
    }

    public AsuransiSepedaLandingPage asuransiSepedaLandingPage() {
        return new AsuransiSepedaLandingPage(iosDriver);
    }

    public AsuransiCovidLandingPage asuransiCovidLandingPage() {
        return new AsuransiCovidLandingPage(iosDriver);
    }

    public AsuransiPerjalananLandingPage asuransiPerjalananLandingPage() {
        return new AsuransiPerjalananLandingPage(iosDriver);
    }

    public PerformaLapakPage performaLapakPage() {
        return new PerformaLapakPage(iosDriver);
    }

    public PremiumDashboardPage premiumDashboardPage() {
        return new PremiumDashboardPage(iosDriver);
    }

    public PremiumLandingAlchemyPage premiumLandingAlchemyPage() {
        return new PremiumLandingAlchemyPage(iosDriver);
    }

    public PremiumLandingPage premiumLandingPage() {
        return new PremiumLandingPage(iosDriver);
    }

    public PrepaidPhoneCreditPage prepaidPhoneCreditPage() {
        return new PrepaidPhoneCreditPage(iosDriver);
    }

    public PaketDataLandingPage paketDataLandingPage() {
        return new PaketDataLandingPage(iosDriver);
    }

    public PaketDataCheckOutPage paketDataCheckOutPage() {
        return new PaketDataCheckOutPage(iosDriver);
    }

    public PulsaPrabayarCheckOutPage pulsaPrabayarCheckOutPage() {
        return new PulsaPrabayarCheckOutPage(iosDriver);
    }

    public PulsaPrabayarInvoiceDetailPage pulsaPrabayarInvoiceDetailPage() {
        return new PulsaPrabayarInvoiceDetailPage(iosDriver);
    }

    public PaketDataInvoiceDetailsPage paketDataInvoiceDetailsPage() {
        return new PaketDataInvoiceDetailsPage(iosDriver);
    }

    public PaketDataTransactionHistoryPage paketDataTransactionHistoryPage() {
        return new PaketDataTransactionHistoryPage(iosDriver);
    }

    public AddonIndihomeInvoiceDetailPage addonIndihomeInvoiceDetailPage() {
        return new AddonIndihomeInvoiceDetailPage(iosDriver);
    }

    public VpCheckOutPage vpCheckOutPage() {
        return new VpCheckOutPage(iosDriver);
    }

    public VpConfirmationCheckOutPage vpConfirmationCheckOutPage() {
        return new VpConfirmationCheckOutPage(iosDriver);
    }

    public VpInvoiceDetailsPage vpInvoiceDetailsPage() {
        return new VpInvoiceDetailsPage(iosDriver);
    }

    public ListrikPrabayarLandingPage listrikPrabayarLandingPage() {
        return new ListrikPrabayarLandingPage(iosDriver);
    }

    public ListrikPrabayarCheckOutPage listrikPrabayarCheckOutPage() {
        return new ListrikPrabayarCheckOutPage(iosDriver);
    }

    public ListrikPrabayarInvoiceDetailsPage listrikPrabayarInvoiceDetailsPage() {
        return new ListrikPrabayarInvoiceDetailsPage(iosDriver);
    }

    public ListrikPrabayarTransactionHistoryPage listrikPrabayarTransactionHistoryPage() {
        return new ListrikPrabayarTransactionHistoryPage(iosDriver);
    }

    public GramediaDigitalPage gramediaDigitalPage() {
        return new GramediaDigitalPage(iosDriver);
    }

    public HappyFreshPage happyFreshPage() {
        return new HappyFreshPage(iosDriver);
    }

    public VpTransactionHistoryPage vpTransactionHistoryPage() {
        return new VpTransactionHistoryPage(iosDriver);
    }

    public AsuransiMotorPage asuransiMotorPage() {
        return new AsuransiMotorPage(iosDriver);
    }

    public AsuransiBepergianRegistrationPage asuransiBepergianRegistrationPage() {
        return new AsuransiBepergianRegistrationPage(iosDriver);
    }

    public DetailAsuransiMotorPage detailAsuransiMotorPage() {
        return new DetailAsuransiMotorPage(iosDriver);
    }

    public AsuransiKesehatanPage asuransiKesehatanPage() {
        return new AsuransiKesehatanPage(iosDriver);
    }

    public ProductCatalogDetailPage productCatalogDetailPage() {
        return new ProductCatalogDetailPage(iosDriver);
    }

    public ProductDetailsPage productDetailsPage() {
        return new ProductDetailsPage(iosDriver);
    }

    public ProductListingPage productListingPage() {
        return new ProductListingPage(iosDriver);
    }

    public PromoPage promoPage() {
        return new PromoPage(iosDriver);
    }

    public PromotedAturBudgetHarianPage promotedAturBudgetHarianPage() {
        return new PromotedAturBudgetHarianPage(iosDriver);
    }

    public PromotedKeywordAturPromosiPage promotedKeywordAturPromosiPage() {
        return new PromotedKeywordAturPromosiPage(iosDriver);
    }

    public PromotedKeywordPage promotedKeywordPage() {
        return new PromotedKeywordPage(iosDriver);
    }

    public PromotedLoanDetailPage promotedLoanDetailPage() {
        return new PromotedLoanDetailPage(iosDriver);
    }

    public PromotedPushPage promotedPushPage() {
        return new PromotedPushPage(iosDriver);
    }

    public PromotedPushFreeTrialPage promotedPushFreeTrialPage() {
        return new PromotedPushFreeTrialPage(iosDriver);
    }

    public PromotedPushEndFreeTrialPage promotedPushEndFreeTrialPage() {
        return new PromotedPushEndFreeTrialPage(iosDriver);
    }

    public PromotedPushTambahPromosiPage promotedPushTambahPromosiPage() {
        return new PromotedPushTambahPromosiPage(iosDriver);
    }

    public PromotedPushOtomatisPage promotedPushOtomatisPage() {
        return new PromotedPushOtomatisPage(iosDriver);
    }

    public PromotedTambahBudgetPromosiPage promotedTambahBudgetPromosiPage() {
        return new PromotedTambahBudgetPromosiPage(iosDriver);
    }

    public ProductsCampaignPage productsCampaignPage() {
        return new ProductsCampaignPage(iosDriver);
    }

    public PromotedPushSuccessPaymentPage promotedPushSuccessPaymentPage() {
        return new PromotedPushSuccessPaymentPage(iosDriver);
    }

    public PromotedPushInvoiceDetailPage promotedPushInvoiceDetailPage() {
        return new PromotedPushInvoiceDetailPage(iosDriver);
    }

    public PromotedPushLoanDetailPage promotedPushLoanDetailPage() {
        return new PromotedPushLoanDetailPage(iosDriver);
    }

    public PromotedPushSatuanPage promotedPushSatuanPage() {
        return new PromotedPushSatuanPage(iosDriver);
    }

    public PromotedPushBarangGrupPage promotedPushBarangGrupPage() {
        return new PromotedPushBarangGrupPage(iosDriver);
    }

    public ManajemenPelangganPage manajemenPelangganPage() {
        return new ManajemenPelangganPage(iosDriver);
    }

    public PulsaPrabayarLandingPage pulsaPrabayarLandingPage() {
        return new PulsaPrabayarLandingPage(iosDriver);
    }

    public UangElektronikLandingPage uangElektronikLandingPage() {
        return new UangElektronikLandingPage(iosDriver);
    }

    public UangElektronikCheckOutPage uangElektronikCheckOutPage() {
        return new UangElektronikCheckOutPage(iosDriver);
    }

    public UangElektronikInvoiceDetailsPage uangElektronikInvoiceDetailsPage() {
        return new UangElektronikInvoiceDetailsPage(iosDriver);
    }

    public UangElektronikTransactionHistoryPage uangElektronikTransactionHistoryPage() {
        return new UangElektronikTransactionHistoryPage(iosDriver);
    }

    public ProdukDigitalPage produkDigitalPage() {
        return new ProdukDigitalPage(iosDriver);
    }

    public PushPage pushPage() {
        return new PushPage(iosDriver);
    }

    public RegisterPage registerPage() {
        return new RegisterPage(iosDriver);
    }

    public ResetPasswordPage resetPasswordPage() {
        return new ResetPasswordPage(iosDriver);
    }

    public SaldoBukalapakPage saldoBukalapakPage() {
        return new SaldoBukalapakPage(iosDriver);
    }

    public SaldoRevampPage saldoRevampPage() {
        return new SaldoRevampPage(iosDriver);
    }

    public SebarPromosiOnboardingPage sebarPromosiOnboardingPage() {
        return new SebarPromosiOnboardingPage(iosDriver);
    }

    public SebarPromosiPage sebarPromosiPage() {
        return new SebarPromosiPage(iosDriver);
    }

    public BuatSebarPromosiPage buatSebarPromosiPage() {
        return new BuatSebarPromosiPage(iosDriver);
    }

    public SearchCartPopUpPage searchCartPopUpPage() {
        return new SearchCartPopUpPage(iosDriver);
    }

    public SearchMessagePage searchMessagePage() {
        return new SearchMessagePage(iosDriver);
    }

    public SearchOmniPage searchOmniPage() {
        return new SearchOmniPage(iosDriver);
    }

    public SearchPage searchPage() {
        return new SearchPage(iosDriver);
    }

    public SetBudgetPromotedPushPage setBudgetPromotedPushPage() {
        return new SetBudgetPromotedPushPage(iosDriver);
    }

    public SLAPage slaPage() {
        return new SLAPage(iosDriver);
    }

    public StatusBerlanggananPremiumPage statusBerlanggananPremiumPage() {
        return new StatusBerlanggananPremiumPage(iosDriver);
    }

    public SuperSecretNinjaPage superSecretNinjaPage() {
        return new SuperSecretNinjaPage(iosDriver);
    }

    public SuperSellerLandingPage superSellerLandingPage() {
        return new SuperSellerLandingPage(iosDriver);
    }

    public SuperSellerStatusProgramPage superSellerStatusProgramPage() {
        return new SuperSellerStatusProgramPage(iosDriver);
    }

    public TambahKartuCCPage tambahKartuCCPage() {
        return new TambahKartuCCPage(iosDriver);
    }

    public TambahKaryawanPage tambahKaryawanPage() {
        return new TambahKaryawanPage(iosDriver);
    }

    public TambahRekeningPage tambahRekeningPage() {
        return new TambahRekeningPage(iosDriver);
    }

    public TambahSaldoPage tambahSaldoPage() {
        return new TambahSaldoPage(iosDriver);
    }

    public TelkomPage telkomPage() {
        return new TelkomPage(iosDriver);
    }

    public TelkomInvoiceDetailPage telkomInvoiceDetailPage() {
        return new TelkomInvoiceDetailPage(iosDriver);
    }

    public TelkomCheckoutPage telkomCheckoutPage() {
        return new TelkomCheckoutPage(iosDriver);
    }

    public TopupDanaPage topupDanaPage() {
        return new TopupDanaPage(iosDriver);
    }

    public TopUpPage topUpPage() {
        return new TopUpPage(iosDriver);
    }

    public TransactionDetailPage transactionDetailPage() {
        return new TransactionDetailPage(iosDriver);
    }

    public TransactionListPage transactionListPage() {
        return new TransactionListPage(iosDriver);
    }

    public TransactionPenjualanPage transactionPenjualanPage() {
        return new TransactionPenjualanPage(iosDriver);
    }

    public TransaksiQBPage transaksiQBPage() {
        return new TransaksiQBPage(iosDriver);
    }

    public TransferBankPage transferBankPage() {
        return new TransferBankPage(iosDriver);
    }

    public TravelBusConfirmationOrderPage travelBusConfirmationOrderPage() {
        return new TravelBusConfirmationOrderPage(iosDriver);
    }

    public TravelBusDetailOrderPage travelBusDetailOrderPage() {
        return new TravelBusDetailOrderPage(iosDriver);
    }

    public TravelBusInvoiceDetailPage travelBusInvoiceDetailPage() {
        return new TravelBusInvoiceDetailPage(iosDriver);
    }

    public TravelBusPassengerFormPage travelBusPassengerFormPage() {
        return new TravelBusPassengerFormPage(iosDriver);
    }

    public TravelBusSchedulePage travelBusScheduleFormPage() {
        return new TravelBusSchedulePage(iosDriver);
    }

    public TravelBusSeatingPage travelBusSeatingPage() {
        return new TravelBusSeatingPage(iosDriver);
    }

    public TravelBusTicketPage travelBusTicketPage() {
        return new TravelBusTicketPage(iosDriver);
    }

    public TravelBusSchedulePage travelBusSchedulePage() {
        return new TravelBusSchedulePage(iosDriver);
    }

    public TravelBusCheckoutPage travelBusCheckoutPage() {
        return new TravelBusCheckoutPage(iosDriver);
    }

    public TravelTrainDetailOrderPage travelTrainDetailOrderPage() {
        return new TravelTrainDetailOrderPage(iosDriver);
    }

    public TravelTrainLandingPage travelTrainLandingPage() {
        return new TravelTrainLandingPage(iosDriver);
    }

    public TravelTrainSeatingPage travelTrainSeatingPage() {
        return new TravelTrainSeatingPage(iosDriver);
    }

    public TravelTrainSchedulePage travelTrainSchedulePage() {
        return new TravelTrainSchedulePage(iosDriver);
    }

    public TravelTrainPassengerFormPage travelTrainPassengerFormPage() {
        return new TravelTrainPassengerFormPage(iosDriver);
    }

    public TravelTrainInvoiceDetailPage travelTrainInvoiceDetailPage() {
        return new TravelTrainInvoiceDetailPage(iosDriver);
    }

    public TravelTrainCheckoutPage travelTrainCheckoutPage() {
        return new TravelTrainCheckoutPage(iosDriver);
    }

    public TravelTrainBuyerContactPage tiketKeretaBuyerContactPage() {
        return new TravelTrainBuyerContactPage(iosDriver);
    }

    public TravelHotelLandingPage travelHotelLandingPage() {
        return new TravelHotelLandingPage(iosDriver);
    }

    public TravelHotelSearchResultPage travelHotelSearchResultPage() {
        return new TravelHotelSearchResultPage(iosDriver);
    }

    public TravelHotelDetailPage travelHotelDetailPage() {
        return new TravelHotelDetailPage(iosDriver);
    }

    public TravelHotelRoomListingPage travelHotelRoomListingPage() {
        return new TravelHotelRoomListingPage(iosDriver);
    }

    public TravelHotelBookingDetailPage travelHotelBookingDetailPage() {
        return new TravelHotelBookingDetailPage(iosDriver);
    }

    public TravelSubscriptionLandingPage travelSubscriptionLandingPage() {
        return new TravelSubscriptionLandingPage(iosDriver);
    }

    public TravelSubscriptionDetailPage travelSubscriptionDetailPage() {
        return new TravelSubscriptionDetailPage(iosDriver);
    }

    public TravelSubscriptionCheckoutPage travelSubscriptionCheckoutPage() {
        return new TravelSubscriptionCheckoutPage(iosDriver);
    }

    public TravelSubscriptionTnCPage travelSubscriptionTnCPage() {
        return new TravelSubscriptionTnCPage(iosDriver);
    }

    public TravelSubscriptionHowToBuyPage travelSubscriptionHowToBuyPage() {
        return new TravelSubscriptionHowToBuyPage(iosDriver);
    }

    public TravelSubscriptionTransactionDetailPage travelSubscriptionTransactionDetailPage() {
        return new TravelSubscriptionTransactionDetailPage(iosDriver);
    }

    public GiftCardLandingPage giftCardLandingPage() {
        return new GiftCardLandingPage(iosDriver);
    }

    public GiftCardCheckoutPage giftCardCheckoutPage() {
        return new GiftCardCheckoutPage(iosDriver);
    }

    public GiftCardInvoiceDetailPage giftCardInvoiceDetailPage() {
        return new GiftCardInvoiceDetailPage(iosDriver);
    }

    public GiftCardProductDetailPage productDetailPageGiftCard() {
        return new GiftCardProductDetailPage(iosDriver);
    }

    public TiketPesawatLandingPage tiketPesawatLandingPage() {
        return new TiketPesawatLandingPage(iosDriver);
    }

    public TiketPesawatSearchPage tiketPesawatSearchPage() {
        return new TiketPesawatSearchPage(iosDriver);
    }

    public TiketPesawatBookingPage tiketPesawatBookingPage() {
        return new TiketPesawatBookingPage(iosDriver);
    }

    public TiketPesawatCheckoutPage tiketPesawatCheckoutPage() {
        return new TiketPesawatCheckoutPage(iosDriver);
    }

    public TiketPesawatInvoiceDetailPage tiketPesawatInvoiceDetailPage() {
        return new TiketPesawatInvoiceDetailPage(iosDriver);
    }

    public KuponLandingPage kuponLandingPage() {
        return new KuponLandingPage(iosDriver);
    }

    public KuponMerchantListPage kuponMerchantListPage() {
        return new KuponMerchantListPage(iosDriver);
    }

    public KuponListPage kuponListPage() {
        return new KuponListPage(iosDriver);
    }

    public KuponDetailPage kuponDetailPage() {
        return new KuponDetailPage(iosDriver);
    }

    public KuponCheckoutPage kuponCheckoutPage() {
        return new KuponCheckoutPage(iosDriver);
    }

    public KuponInvoiceDetailPage kuponInvoiceDetailPage() {
        return new KuponInvoiceDetailPage(iosDriver);
    }

    public TiketEventLandingPage tiketEventLandingPage() {
        return new TiketEventLandingPage(iosDriver);
    }

    public TiketEventBookingPage tiketEventBookingPage() {
        return new TiketEventBookingPage(iosDriver);
    }

    public TiketEventTransactionDetailPage tiketEventTransactionDetailPage() {
        return new TiketEventTransactionDetailPage(iosDriver);
    }

    public TiketEventCheckoutPage tiketEventCheckoutPage() {
        return new TiketEventCheckoutPage(iosDriver);
    }

    public VpBasePage vpBasePage() {
        return new VpBasePage(iosDriver);
    }

    public bukalapak.pageObject.vp.base.VpBasePage newVpBasePage() {
        return new bukalapak.pageObject.vp.base.VpBasePage(iosDriver);
    }

    public UlasanInstanCheckoutPage ulasanInstanCheckoutPage() {
        return new UlasanInstanCheckoutPage(iosDriver);
    }

    public UlasanInstanInformasiBerlanggananPage ulasanInstanInformasiBerlanggananPage() {
        return new UlasanInstanInformasiBerlanggananPage(iosDriver);
    }

    public UlasanInstanInvoicePage ulasanInstanInvoicePage() {
        return new UlasanInstanInvoicePage(iosDriver);
    }

    public UlasanInstanKonfirmasiBerhentiPage ulasanInstanKonfirmasiBerhentiPage() {
        return new UlasanInstanKonfirmasiBerhentiPage(iosDriver);
    }

    public UlasanInstanLandingPage ulasanInstanLandingPage() {
        return new UlasanInstanLandingPage(iosDriver);
    }

    public UlasanInstanOnboardingPage ulasanInstanOnboardingPage() {
        return new UlasanInstanOnboardingPage(iosDriver);
    }

    public UlasanInstanPilihanPaketPage ulasanInstanPilihanPaketPage() {
        return new UlasanInstanPilihanPaketPage(iosDriver);
    }

    public UlasanInstanBeliPakaiBukaDompetPage ulasanInstanBeliPakaiBukaDompetPage() {
        return new UlasanInstanBeliPakaiBukaDompetPage(iosDriver);
    }

    public VirtualAccountPage virtualAccountPage() {
        return new VirtualAccountPage(iosDriver);
    }

    public VoucherDetailKaryawanLapakPage voucherDetailKaryawanLapakPage() {
        return new VoucherDetailKaryawanLapakPage(iosDriver);
    }

    public TambahKataKunciPage tambahKataKunciPage() {
        return new TambahKataKunciPage(iosDriver);
    }

    public SpinAndWinPage spinAndWinPage() {
        return new SpinAndWinPage(iosDriver);
    }

    public LuckyDipPage luckyDipPage() {
        return new LuckyDipPage(iosDriver);
    }


    public DailyGiftboxPage dailyGiftboxPage() {
        return new DailyGiftboxPage(iosDriver);
    }

    public FunroomPage funroomPage() {
        return new FunroomPage(iosDriver);
    }

    public FunroomAllGamesPage funroomAllGamesPage() {
        return new FunroomAllGamesPage(iosDriver);
    }

    public GameKurirPage gameKurirPage() {
        return new GameKurirPage(iosDriver);
    }

    public MoneyTornadoPage moneyTornadoPage() {
        return new MoneyTornadoPage(iosDriver);
    }

    public MatchTheCardPage matchTheCardPage() {
        return new MatchTheCardPage(iosDriver);
    }

    public GalaxyTowerPage galaxyTowerPage() {
        return new GalaxyTowerPage(iosDriver);
    }

    public StarCatcherPage starCatcherPage() {
        return new StarCatcherPage(iosDriver);
    }

    public FeedbackPage feedbackPage() {
        return new FeedbackPage(iosDriver);
    }

    public PromotedPushInstantPage promotedPushInstantPage() {
        return new PromotedPushInstantPage(iosDriver);
    }

    public AskRatingAppsPage askRatingAppPage() {
        return new AskRatingAppsPage(iosDriver);
    }

    public ProductReviewPage productReviewPage() {
        return new ProductReviewPage(iosDriver);
    }

    public ReviewerProfilePage reviewerProfilePage() {
        return new ReviewerProfilePage(iosDriver);
    }

    public BarangTerfavoritPage barangTerfavoritPage() {
        return new BarangTerfavoritPage(iosDriver);
    }

    public KataKunciPopulerPage kataKunciPopulerPage() {
        return new KataKunciPopulerPage(iosDriver);
    }

    public TerlarisDanKurangLarisPage terlarisDanKurangLarisPage() {
        return new TerlarisDanKurangLarisPage(iosDriver);
    }

    public PilihKategoriPage pilihKategoriPage() {
        return new PilihKategoriPage(iosDriver);
    }

    public OneKlikAccountCheckoutPage oneKlikAccountCheckoutPage() {
        return new OneKlikAccountCheckoutPage(iosDriver);
    }

    public OneKlikRegisterNewAccountPage oneKlikRegisterNewAccountPage() {
        return new OneKlikRegisterNewAccountPage(iosDriver);
    }

    public ProductReviewFormPage productReviewFormPage() {
        return new ProductReviewFormPage(iosDriver);
    }

    public PraRegisPage praRegisPage() {
        return new PraRegisPage(iosDriver);
    }

    public MenuUlasanKamuPage menuUlasanKamuPage() {
        return new MenuUlasanKamuPage(iosDriver);
    }

    public SerbuSeruPage serbuSeruPage() {
        return new SerbuSeruPage(iosDriver);
    }

    public ReferralPage referralPage() {
        return new ReferralPage(iosDriver);
    }

    public LapakTerlarisPage lapakTerlarisPage() {
        return new LapakTerlarisPage(iosDriver);
    }

    public BarangTerlarisPage barangTerlarisPage() {
        return new BarangTerlarisPage(iosDriver);
    }

    public HubungkanDANAPage hubungkanDANAPage() {
        return new HubungkanDANAPage(iosDriver);
    }

    public RincianPromosiPage rincianPromosiPage() {
        return new RincianPromosiPage(iosDriver);
    }

    public VerifyPhoneNumberPage verifyPhoneNumberPage() {
        return new VerifyPhoneNumberPage(iosDriver);
    }

    public DeeplinkTesterPage deeplinkTesterPage() {
        return new DeeplinkTesterPage(iosDriver);
    }

    public VoucherDetailPage voucherDetailPage() {
        return new VoucherDetailPage(iosDriver);
    }

    public MitraBukalapakPage mitraBukalapakPage() {
        return new MitraBukalapakPage(iosDriver);
    }

    public AsuransiReturCheckoutPage asuransiReturCheckoutPage() {
        return new AsuransiReturCheckoutPage(iosDriver);
    }

    public RecurringPage recurringPage() {
        return new RecurringPage(iosDriver);
    }

    public VerifyEmailPage verifyEmailPage() {
        return new VerifyEmailPage(iosDriver);
    }

    public AutoInvestPage autoInvestPage() {
        return new AutoInvestPage(iosDriver);
    }

    public EditPasswordPage editPasswordPage() {
        return new EditPasswordPage(iosDriver);
    }

    public NewUserZonePage newUserZonePage() {
        return new NewUserZonePage(iosDriver);
    }

    public EditPhonePage editPhonePage() {
        return new EditPhonePage(iosDriver);
    }

    public PROMBasePage promBasePage() {
        return new PROMBasePage(iosDriver);
    }

    public ChangeEmailPage changeEmailPage() {
        return new ChangeEmailPage(iosDriver);
    }

    public ReferralLinkPage referralLinkPage() {
        return new ReferralLinkPage(iosDriver);
    }

    public KasihTebarEmasPage kasihTebarEmasPage() {
        return new KasihTebarEmasPage(iosDriver);
    }

    public SuperSellerEngagementPage superSellerEngagementPage() {
        return new SuperSellerEngagementPage(iosDriver);
    }

    public SuperSellerBonusPage superSellerBonusPage() {
        return new SuperSellerBonusPage(iosDriver);
    }

    public SuperSellerMutationPage superSellerMutationPage() {
        return new SuperSellerMutationPage(iosDriver);
    }

    public DigitalGoodsCheckoutPage digitalGoodsCheckoutPage() {
        return new DigitalGoodsCheckoutPage(iosDriver);
    }

    public KonfirmasiKirimPage konfirmasiKirimPage() {
        return new KonfirmasiKirimPage(iosDriver);
    }

    public PromotedPushDetailCampaignPage promotedPushDetailCampaignPage() {
        return new PromotedPushDetailCampaignPage(iosDriver);
    }

    public VoucherkuPromoPage voucherkuPromoPage() {
        return new VoucherkuPromoPage(iosDriver);
    }

    public VoucherkuDetailPage voucherkuDetailPage() {
        return new VoucherkuDetailPage(iosDriver);
    }

    public ReferralCreditsPage referralCreditsPage() {
        return new ReferralCreditsPage(iosDriver);
    }

    public FaqPage faqPage() {
        return new FaqPage(iosDriver);
    }

    public ResurrectionZoneWebPage resurrectionZoneWebPage() {
        return new ResurrectionZoneWebPage(iosDriver);
    }

    public BukaAsuransiPage bukaAsuransiPage() {
        return new BukaAsuransiPage(iosDriver);
    }

    public AsuransiTambahanPage asuransiTambahanPage() {
        return new AsuransiTambahanPage(iosDriver);
    }

    public RiwayatKomplainPage riwayatKomplainPage() {
        return new RiwayatKomplainPage(iosDriver);
    }

    public CreditsListPage creditsListPage() {
        return new CreditsListPage(iosDriver);
    }

    public KaryawanLapakLogAktivitasPage karyawanLapakLogAktivitasPage() {
        return new KaryawanLapakLogAktivitasPage(iosDriver);
    }

    public HistoryTransactionDetailPage historyTransactionDetailPage() {
        return new HistoryTransactionDetailPage(iosDriver);
    }

    public SuperSellerOptOutPage superSellerOptOutPage() {
        return new SuperSellerOptOutPage(iosDriver);
    }

    public SuperSellerOptOutSurveyPage superSellerOptOutSurveyPage() {
        return new SuperSellerOptOutSurveyPage(iosDriver);
    }

    public SuperSellerPilihPaketPage superSellerPilihPaketPage() {
        return new SuperSellerPilihPaketPage(iosDriver);
    }

    public PotongHargaPage potongHargaPage() {
        return new PotongHargaPage(iosDriver);
    }

    public RiwayatPerangkatPage riwayatPerangkatPage() {
        return new RiwayatPerangkatPage(iosDriver);
    }

    public DANAAccountPage danaAccountPage() {
        return new DANAAccountPage(iosDriver);
    }

    public RecurringTransactionFormPage recurringTransactionFormPage() {
        return new RecurringTransactionFormPage(iosDriver);
    }

    public RecurringTransactionDetailPage recurringTransactionDetailPage() {
        return new RecurringTransactionDetailPage(iosDriver);
    }

    public BukaPolyWebPage bukaPolyWebPage() {
        return new BukaPolyWebPage(iosDriver);
    }

    public PengaturanPembayaranCreditCardPage pengaturanPembayaranCreditCardPage() {
        return new PengaturanPembayaranCreditCardPage(iosDriver);
    }

    public PengaturanPembayaranTambahKartuPage pengaturanPembayaranTambahKartuPage() {
        return new PengaturanPembayaranTambahKartuPage(iosDriver);
    }

    public PasswordCc3DsWebviewPage passwordCc3DsWebviewPage() {
        return new PasswordCc3DsWebviewPage(iosDriver);
    }

    public PromotedRiwayatKlikPage promotedRiwayatKlikPage() {
        return new PromotedRiwayatKlikPage(iosDriver);
    }

    public CloseStorePage closeStorePage() {
        return new CloseStorePage(iosDriver);
    }

    public ProductDetailFlashDealPage productDetailFlashDealPage() {
        return new ProductDetailFlashDealPage(iosDriver);
    }

    public ProductForSellPage productForSellPage() {
        return new ProductForSellPage(iosDriver);
    }

    public ProductNotForSellPage productNotForSellPage() {
        return new ProductNotForSellPage(iosDriver);
    }

    public PohonRejekiPage pohonRejekiPage() {
        return new PohonRejekiPage(iosDriver);
    }

    public NabungDiskonPage nabungDiskonPage() {
        return new NabungDiskonPage(iosDriver);
    }

    public CODPage codPage() {
        return new CODPage(iosDriver);
    }

    public PembatalanTransaksiPage pembatalanTransaksiPage() {
        return new PembatalanTransaksiPage(iosDriver);
    }

    public ClaimRewardsOutsideAppsPage newUserPartnerRewardsPage() {
        return new ClaimRewardsOutsideAppsPage(iosDriver);
    }

    public PembekuanLapakPage pembekuanLapakPage() {
        return new PembekuanLapakPage(iosDriver);
    }

    public SellerListingPage sellerListingPage() {
        return new SellerListingPage(iosDriver);
    }

    public DANACashierPage danaCashierPage() {
        return new DANACashierPage(iosDriver);
    }

    public SaldoPage saldoPage() {
        return new SaldoPage(iosDriver);
    }

    public InformVoucherAfterRegisterPage informVoucherAfterRegisterPage() {
        return new InformVoucherAfterRegisterPage(iosDriver);
    }

    public BukamartLandingPage bukamartLandingPage() {
        return new BukamartLandingPage(iosDriver);
    }

    public CartDialogPage cartDialogPage() {
        return new CartDialogPage(iosDriver);
    }

    public PromoDetailPage promoDetailPage() {
        return new PromoDetailPage(iosDriver);
    }

    public EditProfilPage editProfilPage() {
        return new EditProfilPage(iosDriver);
    }

    public VaccinePage vaccinePage() {
        return new VaccinePage(iosDriver);
    }

    public TemanCuanPage temanCuanPage() {
        return new TemanCuanPage(iosDriver);
    }

    public BukaBantuanWebviewPage bukaBantuanWebviewPage() {
        return new BukaBantuanWebviewPage(iosDriver);
    }

    public LinkJualBeliPage linkJualBeliPage() {
        return new LinkJualBeliPage(iosDriver);
    }

    public CheckoutRevampMarketplacePage checkoutRevampMarketplacePage() {
        return new CheckoutRevampMarketplacePage(iosDriver);
    }

    public ChooseCategoryPage chooseCategoryPage() {
        return new ChooseCategoryPage(iosDriver);
    }

    public BRICeriaPage briCeriaPage() {
        return new BRICeriaPage(iosDriver);
    }

    public TransactionListRevampPage transactionListRevampPage() {
        return new TransactionListRevampPage(iosDriver);
    }

    public AsuransiCovidFormPengajuanPage asuransiCovidFormPengajuanPage() {
        return new AsuransiCovidFormPengajuanPage(iosDriver);
    }

    public AsuransiCovidCheckoutPage asuransiCovidCheckoutPage() {
        return new AsuransiCovidCheckoutPage(iosDriver);
    }

    public AsuransiCovidInvoiceDetailPage asuransiCovidInvoiceDetailPage() {
        return new AsuransiCovidInvoiceDetailPage(iosDriver);
    }

    public BukaTabunganLandingPage bukatabunganLandingPage() {
        return new BukaTabunganLandingPage(iosDriver);
    }
}
