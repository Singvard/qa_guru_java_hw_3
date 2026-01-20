package selenide.hw5;

import models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
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

        student = new Student(firstName, lastName, email, address);
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
