package bukalapak.stepDefinitions.martech;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import bukalapak.data.GMTData;

import static org.junit.Assert.fail;

public class VaccineStepDefinitions extends TestInstrument implements En {

    public VaccineStepDefinitions() {
        When("user click \"([^\"]*)\" button on vaccine registration page", (String vaccineButton) -> {
            switch (vaccineButton) {
                case "Daftar Vaksin Sekarang":
                    bukalapak.vaccinePage().userClickDaftarVaksinSekarang();
                    break;
                case "Buat Janji":
                    bukalapak.vaccinePage().userClickBuatJanji();
                    break;
                case "Lacak Jadwal":
                    bukalapak.vaccinePage().userClickOnLacakJadwal();
                    break;
                case "Kembali ke halaman utama":
                    bukalapak.vaccinePage().userClickOnBackToHome();
                    break;
                default:
                    fail(String.format("%s step not implemented yet", vaccineButton));
                    break;
            }
        });

        Then("user should see the list of faskes", () -> {
            bukalapak.vaccinePage().verifyVaccineRegistrationLocationPage();
        });

        When("user click \"Baca sekarang\" button on info pop-up", () ->{
            bukalapak.vaccinePage().userClickBacaSekarang();
        });

        Then("user should see the info page", () -> {
            bukalapak.vaccinePage().verifyVaccineInfoPage();
        });

        Then("user click \"Saya Mengerti dan Setuju\" on info page", () -> {
            bukalapak.vaccinePage().clickAgreeVaccineInfo();
        });

        When("user is on Vaccination Schedule Selection landing page", () -> {
            bukalapak.vaccinePage().validateSheduleSelectionPage();
        });

        When("user click \"Ubah\" to change the date", () -> {
            bukalapak.vaccinePage().clickChangeDateButton();
        });

        When("user click \"Pilih Tanggal\" button", () -> {
            bukalapak.vaccinePage().clickSelectDate();
        });

        When("user select time schedule at \"([^\"]*)\"", (String time) -> {
            bukalapak.vaccinePage().selectTimeSlot(time);
        });

        When("user select date at \"([^\"]*)\" in this month", (String date) -> {
            bukalapak.vaccinePage().selectDate(date);
        });

        When("user click \"Lanjutkan\" button", () -> {
            bukalapak.vaccinePage().clickLanjutkan();
        });

        When("user is on Personal Information landing page", () -> {
            bukalapak.vaccinePage().validatePersonsalInformationPage();
        });

        When("user input Name field with \"([^\"]*)\"", (String name) -> {
            bukalapak.vaccinePage().inputName(name);
        });

        When("user input NIK field with \"([^\"]*)\"", (String nik) -> {
            bukalapak.vaccinePage().inputNIK(nik);
        });

        Then("user should see \"([^\"]*)\" on Registration Detail page", (String nik) -> {
            bukalapak.vaccinePage().validateTransactionDetailPage(nik);
        });

        When("user should see \"([^\"]*)\" meesage on Registration page", (String message) -> {
            bukalapak.vaccinePage().validateTrackNIKMessage(message);
        });

        Then("user should see Vaccine Registration Home page", () -> {
            bukalapak.vaccinePage().validateVaccineRegistrationHomePage();
        });

        When("user register vaccine with correct data", () -> {
            String date = bukalapak.vaccinePage().getDateNextWeek();

            bukalapak.vaccinePage().userClickDaftarVaksinSekarang();
            bukalapak.vaccinePage().userClickBuatJanji();
            bukalapak.vaccinePage().userClickBacaSekarang();
            bukalapak.vaccinePage().verifyVaccineInfoPage();
            bukalapak.vaccinePage().clickAgreeVaccineInfo();
            bukalapak.vaccinePage().validateSheduleSelectionPage();
            bukalapak.vaccinePage().clickChangeDateButton();
            bukalapak.vaccinePage().selectDate(date);
            bukalapak.vaccinePage().clickSelectDate();
            bukalapak.vaccinePage().selectTimeSlot("08.00 - 09.00");
            bukalapak.vaccinePage().clickLanjutkan();
            bukalapak.vaccinePage().inputPersonalInformation("correct");
        });

        Then("user should see the registration detail", () -> {
            String nik = GMTData.getNIK();

            bukalapak.vaccinePage().validateTransactionDetailPage(nik);
        });

        Then("user should can track the registration detail", () -> {
            String nik = GMTData.getNIK();
            String firstName = dotenv.get("VACCINE_FIRST_NAME");
            String lastName = dotenv.get("VACCINE_LAST_NAME");

            bukalapak.vaccinePage().userClickOnBackToHome();
            bukalapak.vaccinePage().userClickOnLacakJadwal();
            bukalapak.vaccinePage().inputName(firstName + " " + lastName);
            bukalapak.vaccinePage().inputNIK(nik);
            bukalapak.vaccinePage().validateTransactionDetailPage(nik);
        });

        Then("user should see the Ticket Category field", () -> {
            bukalapak.vaccinePage().verifyTicketCategoryField();
        });

        Then("user can select Ticket Category", () -> {
            bukalapak.vaccinePage().selectTicketCategory();
        });
    }
}
