package selenide;

import models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.StudentBuilder;

class TextBoxTest extends BaseTest {
    private TextBoxPage page;
    private Student student;

    @BeforeEach
    void setUp() {
        page = new TextBoxPage();
        student = StudentBuilder.defaultStudent()
                .withEmail("name_surname@test.net")
                .build();
    }

    @Test
    void testFormWithAllFields() {
        page.openPage()
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
