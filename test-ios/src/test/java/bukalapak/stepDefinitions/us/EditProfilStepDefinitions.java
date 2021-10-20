package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */

public class EditProfilStepDefinitions extends TestInstrument implements En {
    public EditProfilStepDefinitions() {
        When("user is in \"Edit Profil\" page", () -> {
            bukalapak.editProfilPage().userOnEditProfilPage();
        });

        When("^user input \"([^\"]*)\" into Nama field on Edit Profil page$", (String name) -> {
            bukalapak.editProfilPage().typeOnNameTextField(name);
        });

        When("^user input \"([^\"]*)\" into Catatan Lapak field on Edit Profil page$", (String notes) -> {
            bukalapak.editProfilPage().typeOnNotesTextField(notes);
        });

        When("^user input \"([^\"]*)\" into Deskripsi Lapak field on Edit Profil page$", (String description) -> {
            bukalapak.editProfilPage().typeOnDescriptionTextField(description);
        });

        When("^user tap Simpan button on Edit Profil page$", () -> {
            bukalapak.editProfilPage().tapSimpanButtton();
        });

        When("^Nama field on Edit Profil page displays valid name \"([^\"]*)\"$", (String name) -> {
            bukalapak.editProfilPage().validateName(name);
        });

        When("^Catatan Lapak field on Edit Profil page displays valid notes \"([^\"]*)\"$", (String notes) -> {
            bukalapak.editProfilPage().validateNotes(notes);
        });

        When("^Deskripsi Lapak field on Edit Profil page displays valid description \"([^\"]*)\"$", (String description) -> {
            bukalapak.editProfilPage().validateDescription(description);
        });
    }
}
