package selenide;

import com.codeborne.selenide.Configuration;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.DateFormatter;

import java.time.LocalDate;
import java.util.List;

class Homework5Test {
    private static final String FULL_HD = "1920x1080";
    private static final String BASE_URL = "https://demoqa.com";
    private static final String FIRST_NAME = "Имя";
    private static final String LAST_NAME = "Фамилия";
    private static final String EMAIL = "test@test.test";
    private static final String PHONE = "9876543210";
    private static final LocalDate BIRTHDATE = LocalDate.of(1990, 8, 12);
    private static final List<String> ADDED_SUBJECTS = List.of("Maths", "English", "Computer Science", "Arts");
    private static final List<String> DELETED_SUBJECTS = List.of("Arts");
    private static final List<Hobby> HOBBIES = List.of(Hobby.SPORTS, Hobby.MUSIC);
    private static final String PICTURE_PATH = "avatar.png";
    private static final String MULTILINE_ADDRESS =
            """
                    123456,
                    г. Москва,
                    ул. Пушкина,
                    д. 5,
                    кв. 109
                    """;

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

    private static RegistrationPage page;
    private static Student student;

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = FULL_HD;
        Configuration.baseUrl = BASE_URL;
        page = new RegistrationPage();
        student = new Student(
                FIRST_NAME,
                LAST_NAME,
                EMAIL,
                Gender.MALE,
                PHONE,
                BIRTHDATE,
                ADDED_SUBJECTS,
                DELETED_SUBJECTS,
                HOBBIES,
                PICTURE_PATH,
                MULTILINE_ADDRESS,
                State.RAJASTHAN,
                City.JAISELMER
        );
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

        page.checkModalVisibility()
                .checkModalTitle()
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

        page.checkModalVisibility()
                .checkModalTitle()
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

        page.checkModalInvisibility()
                .checkFormWasValidated()
                .checkFirstNameHasRedBorder()
                .checkFirstNameHasWarning()
                .checkLastNameHasRedBorder()
                .checkLastNameHasWarning()
                .checkEmailHasGreenBorder()
                .checkEmailHasApproval()
                .checkAllGenderOptionsAreRed()
                .checkMobileNumberHasRedBorder()
                .checkMobileNumberHasWarning()
                .checkDateOfBirthHasGreenBorder()
                .checkDateOfBirthHasApproval()
                .checkAllHobbiesOptionsAreGreen();
    }
}
