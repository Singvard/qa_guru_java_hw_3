package selenide;

import models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.StudentBuilder;
import utils.RandomData;

class TextBoxTest extends BaseTest {
    private TextBoxPage page;
    private Student student;

    @BeforeEach
    void setUp() {
        page = new TextBoxPage();
        var randomizer = new RandomData();
        var firstName = randomizer.randomFirstName();
        var lastName = randomizer.randomLastName();
        var email = randomizer.randomEmail();
        var address = randomizer.randomAddress();

        student = StudentBuilder.defaultStudent()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withAddress(address)
                .build();
    }

    @Test
    void testFormWithAllFields() {
        page.openPage()
                .removeAds()
                .setFullName(student.studentFullName())
                .setEmail(student.email())
                .setCurrentAddress(student.address())
                .setPermanentAddress(student.address())
                .submit();

        page.verifyFullName(student)
                .verifyEmail(student)
                .verifyCurrentAddress(student)
                .verifyPermanentAddress(student);
    }
}
