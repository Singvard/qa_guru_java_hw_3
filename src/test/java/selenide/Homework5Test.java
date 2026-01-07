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
                .checkCellValue("Student Name", student.studentFullName())
                .checkCellValue("Student Email", student.email())
                .checkCellValue("Gender", student.gender().toString())
                .checkCellValue("Mobile", student.phoneNumber())
                .checkCellValue("Date of Birth", student.studentDateOfBirth())
                .checkCellValue("Subjects", student.studentSubjects())
                .checkCellValue("Hobbies", student.studentHobbies())
                .checkCellValue("Picture", student.picturePath())
                .checkCellValue("Address", student.studentFlatAddress())
                .checkCellValue("State and City", student.studentFullLocation());
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
                .checkCellValue("Student Name", student.studentFullName())
                .checkEmptyCell("Student Email")
                .checkCellValue("Gender", student.gender().toString())
                .checkCellValue("Mobile", student.phoneNumber())
                .checkCellValue("Date of Birth", DateFormatter.formateDateToString(LocalDate.now()))
                .checkEmptyCell("Subjects")
                .checkEmptyCell("Hobbies")
                .checkEmptyCell("Picture")
                .checkEmptyCell("Address")
                .checkEmptyCell("State and City");
    }
}
