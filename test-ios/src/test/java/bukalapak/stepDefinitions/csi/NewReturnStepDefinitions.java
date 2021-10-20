package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class NewReturnStepDefinitions extends TestInstrument implements En {
    public NewReturnStepDefinitions() {
        And("^user is on Komplain Page$", () -> {
            bukalapak.newReturnPage().userOnPengajuanKomplainPage();
        });

        And("^user choose return reason in Pengajuan Komplain page$", () -> {
            bukalapak.newReturnPage().userChooseReasonBarangRusak();
        });

        And("^user upload image for return$", () -> {
            bukalapak.newReturnPage().uploadImageReturn();
        });

        And("^user choose solution refund for return$", () -> {
            bukalapak.newReturnPage().userOnPilihSolusiPage();
            bukalapak.newReturnPage().chooseRefundSolution();
        });

        And("^user choose solution replacement for return$", () -> {
            bukalapak.newReturnPage().userOnPilihSolusiPage();
            bukalapak.newReturnPage().chooseReplaceSolution();
        });

        And("user choose solution replacement", () -> {
            bukalapak.newReturnPage().chooseReplaceWithoutNext();
        });

        And("^user choose solution addition for return$", () -> {
            bukalapak.newReturnPage().userOnPilihSolusiPage();
            bukalapak.newReturnPage().chooseAdditionSolution();
        });

        And("^user confirm return solution$", () -> {
            bukalapak.newReturnPage().userOnKonfirmasiPengajuanKomplainPage();
        });

        Then("^user success to create return$", () -> {
            bukalapak.newReturnPage().userOnNewReturnPage();
            bukalapak.newReturnPage().userONewReturnDetailPage();
        });

        Then("stepper of item retur will be showed in detail komplain page", () -> {
            bukalapak.diskusiKomplainPage().tapDetailKomplain();
            bukalapak.detailKomplainPage().validateItemReturnStepper();
        });

        Then("status of retur solution will be showed in detail komplain page", () -> {
            bukalapak.diskusiKomplainPage().tapDetailKomplain();
            bukalapak.detailKomplainPage().validateStatusSolusi();
        });

        And("user input resi for item return", () -> {
           bukalapak.diskusiKomplainPage().tapButtonPilihPengiriman();
           bukalapak.diskusiKomplainPage().inputPengirimanRetur();
        });

        Then("detail resi will show in Detail Komplain page", () -> {
            bukalapak.diskusiKomplainPage().tapElement("diskusi_komplain_detail_button", 10);
            bukalapak.detailKomplainPage().validateResiItemReturn();
        });

        And("seller received return item", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().waitForVisibilityOf("diskusi_komplain_terima_button");
            bukalapak.diskusiKomplainPage().tapElement("diskusi_komplain_terima_button", 10);
            bukalapak.diskusiKomplainPage().tapElement("diskusi_komplain_confirm_item_button", 10);
        });

        And("buyer confirm received return item", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().confirmReceivedReturnItem();
        });

        Then("message complaint finish is showed", () -> {
            bukalapak.diskusiKomplainPage().validateFinishedReturnComplaint();
        });

        And("user validate resi manual returned item", () -> {
            bukalapak.diskusiKomplainPage().tapElement("diskusi_komplain_detail_button");
            bukalapak.detailKomplainPage().validateResiItemReturn();
        });

        And("user cancel submit retur on Komplain Page", () -> {
            bukalapak.newReturnPage().cancelSubmitRetur();
        });

        And("complaint retur has been cancelled", () -> {
            bukalapak.detailPembelianMarketplacePage().userOnDetailPembelianPage();
        });

        And("user tap on Solusi Komplain in Konfirmasi Page", () -> {
            bukalapak.newReturnPage().tapComplaintSolution();
        });

        And("user change solution become replacement item", () -> {
            bukalapak.newReturnPage().changeToReplaceSolution();
        });

        When("^user change solution become \"([^\"]*)\" solution$", (String solution) -> {
            bukalapak.newReturnPage().changeReturnSolution(solution);
        });

        And("^user accept change solution with item (not\\s)?to be returned$", (String args) -> {
            Boolean item = args == null;
            bukalapak.newReturnPage().tapConfirmChangeSolution(item);
        });

        And("solution become replacement item", () -> {
            bukalapak.newReturnPage().validateReplacementSolution();
        });

        And("user tap on Detail Masalah Komplain in Konfirmasi Page", () -> {
            bukalapak.newReturnPage().tapdetailprolem();
        });

        And("user change problem description become \"([^\"]*)\"", (String desc) -> {
            bukalapak.newReturnPage().typeAndEnterValue("deskripsi_masalah_return", desc);
            bukalapak.newReturnPage().tapElement("save_changes");
        });

        And("user upload one other image", () -> {
            bukalapak.newReturnPage().uploadOtherImageReturn();
        });

        Then("description will become \"([^\"]*)\"", (String desc) -> {
            bukalapak.newReturnPage().validateDesc(desc);
        });

        And("user click lanjut without choosing solution retur", () -> {
            bukalapak.newReturnPage().userOnPilihSolusiPage();
            bukalapak.newReturnPage().tapElement("lanjut_retur");
        });

        Then("display \"([^\"]*)\" error notification", (String message) -> {
            bukalapak.newReturnPage().validateErrorMsg(message);
        });

        And("user upload two image for return", () -> {
            bukalapak.newReturnPage().uploadTwoImage();
        });

        Then("display info \"([^\"]*)\" in Konfirmasi Page", (String image) -> {
            bukalapak.newReturnPage().validateCountImage(image);
        });

        And("user choose solution refund with nominal more than maximum", () -> {
            bukalapak.newReturnPage().userOnPilihSolusiPage();
            bukalapak.newReturnPage().refundWithFillNominal();
        });

        And("user tap Lanjut button", () -> {
            bukalapak.newReturnPage().tapElement("lanjut_retur");
        });

        Then("display maximum error notification", () -> {
            bukalapak.newReturnPage().showErrorMaxNominal();
        });

        Then("user go to Komplain Page", () -> {
            bukalapak.newReturnPage().tapKomplainButton();
        });

        And("user tap lanjut button on Pengajuan Komplain Page", () -> {
            bukalapak.newReturnPage().tapElement("lanjut_retur");
        });

        Then(" Then display \"([^\"]*)\" error notification", (String error) -> {
            bukalapak.newReturnPage().validatePhotosErrorMsg(error);
        });

        And("user tab ubah alamat button", () -> {
            bukalapak.newReturnPage().waitForVisibilityOf("ubah_alamat");
            bukalapak.newReturnPage().tapElement("ubah_alamat");
        });

        And("user choose diferent address", () -> {
            bukalapak.newReturnPage().chooseAddress();
        });

        Then("adress has been updated", () -> {
            bukalapak.newReturnPage().validateAddres();
        });

        Then("user upload an image for return", () -> {
            bukalapak.newReturnPage().uploadAnImageReturn();
        });

        Then("user remove an image from return", () -> {
            bukalapak.newReturnPage().removeAnImageReturn();
        });

        Then("user edit image for return", () -> {
            bukalapak.newReturnPage().editImageReturn();
        });
    }
}
