package selenide;

import models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.StudentBuilder;
import utils.DateFormatter;

import java.time.LocalDate;

class Homework5Test extends BaseTest {


    private static final String STUDENT_NAME_LABEL = "Student Name";
    private static final String STUDENT_EMAIL_LABEL = "Student Email";
    private static final String GENDER_LABEL = "Gender";
    private static final String MOBILE_LABEL = "Mobile";
    private static final String DATE_OF_BIRTH_LABEL = "Date of Birth";
    private static final String SUBJECTS_LABEL = "Subjects";
    private static final String HOBBIES_LABEL = "Hobbies";
    private static final String PICTURE_LABEL = "Picture";
    private static final String ADDRESS_LABEL = "Address";
    private static final String STATE_AND_CITY_LABEL = "State and City";

    private RegistrationPage page;
    private Student student;

    @BeforeEach
    void setUp() {
        page = new RegistrationPage();
        student = StudentBuilder.defaultStudent().build();
    }

    @Test
    void testFormWithAllFields() {
        page.openPage()
                .setFirstName(student.firstName())
                .setLastName(student.lastName())
                .setEmail(student.email())
                .setGender(student.gender())
                .setPhoneNumber(student.phoneNumber())
                .setBirthday(student.birthdate())
                .setSubjects(student.addedSubjects())
                .deleteSubjects(student.deletedSubjects())
                .setHobbies(student.hobbies())
                .uploadPicture(student.picturePath())
                .setAddress(student.address())
                .setState(student.state())
                .setCity(student.city())
                .submit();

        page.modal()
                .checkVisibility()
                .checkTitle()
                .checkColumnTitles()
                .checkCellValue(STUDENT_NAME_LABEL, student.studentFullName())
                .checkCellValue(STUDENT_EMAIL_LABEL, student.email())
                .checkCellValue(GENDER_LABEL, student.gender().toString())
                .checkCellValue(MOBILE_LABEL, student.phoneNumber())
                .checkCellValue(DATE_OF_BIRTH_LABEL, student.studentDateOfBirth())
                .checkCellValue(SUBJECTS_LABEL, student.studentSubjects())
                .checkCellValue(HOBBIES_LABEL, student.studentHobbies())
                .checkCellValue(PICTURE_LABEL, student.picturePath())
                .checkCellValue(ADDRESS_LABEL, student.studentFlatAddress())
                .checkCellValue(STATE_AND_CITY_LABEL, student.studentFullLocation());
    }

    @Test
    void testFormWithMandatoryFields() {
        page.openPage()
                .setFirstName(student.firstName())
                .setLastName(student.lastName())
                .setPhoneNumber(student.phoneNumber())
                .setGender(student.gender())
                .submit();

        page.modal()
                .checkVisibility()
                .checkTitle()
                .checkColumnTitles()
                .checkCellValue(STUDENT_NAME_LABEL, student.studentFullName())
                .checkEmptyCell(STUDENT_EMAIL_LABEL)
                .checkCellValue(GENDER_LABEL, student.gender().toString())
                .checkCellValue(MOBILE_LABEL, student.phoneNumber())
                .checkCellValue(DATE_OF_BIRTH_LABEL, DateFormatter.formateDateToString(LocalDate.now()))
                .checkEmptyCell(SUBJECTS_LABEL)
                .checkEmptyCell(HOBBIES_LABEL)
                .checkEmptyCell(PICTURE_LABEL)
                .checkEmptyCell(ADDRESS_LABEL)
                .checkEmptyCell(STATE_AND_CITY_LABEL);
    }

    @Test
    void testEmptyFormSending() {
        page.openPage()
                .submit();

        page.modal()
                .checkInvisibility();

        page.validation()
                .verifyFormWasValidated()
                .verifyFirstNameHasRedBorder()
                .verifyFirstNameHasWarning()
                .verifyLastNameHasRedBorder()
                .verifyLastNameHasWarning()
                .verifyEmailHasGreenBorder()
                .verifyEmailHasApproval()
                .verifyAllGenderOptionsAreRed()
                .verifyMobileNumberHasRedBorder()
                .verifyMobileNumberHasWarning()
                .verifyDateOfBirthHasGreenBorder()
                .verifyDateOfBirthHasApproval()
                .verifyAllHobbiesOptionsAreGreen()
                .verifyAddressHasGreenBorder()
                .verifyAddressHasApproval();
    }
}
