package selenide.hw5;

import models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.DateFormatter;
import utils.RandomData;
import utils.StateCityMapper;

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

    private static final String PICTURE_PATH = "avatar.png";

    private RegistrationPage page;
    private Student student;

    @BeforeEach
    void setUp() {
        page = new RegistrationPage();
        var randomizer = new RandomData();
        var firstName = randomizer.randomFirstName();
        var lastName = randomizer.randomLastName();
        var email = randomizer.randomEmail();
        var gender = randomizer.randomGender();
        var phoneNumber = randomizer.random10DigitsPhoneNumber();
        var birthdate = randomizer.randomBirthdate();
        var addedSubjects = randomizer.randomAddedSubjects();
        var hobbies = randomizer.randomHobbies();
        var address = randomizer.randomAddress();
        var city = randomizer.randomCity();
        var state = StateCityMapper.getStateForCity(city);

        student = new Student(
                firstName,
                lastName,
                email,
                gender,
                phoneNumber,
                birthdate,
                addedSubjects,
                hobbies,
                PICTURE_PATH,
                address,
                state,
                city
        );
    }

    @Test
    void testFormWithAllFields() {
        page.openPage()
                .removeAds()
                .setFirstName(student.firstName())
                .setLastName(student.lastName())
                .setEmail(student.email())
                .setGender(student.gender())
                .setPhoneNumber(student.phoneNumber())
                .setBirthday(student.birthdate())
                .setSubjects(student.addedSubjects())
                .setHobbies(student.hobbies())
                .uploadPicture(student.picturePath())
                .setAddress(student.address())
                .setState(student.state())
                .setCity(student.city())
                .submit();

        var expectedFullName = student.studentFullName();
        var expectedEmail = student.email();
        var expectedGender = student.gender().toString();
        var expectedPhoneNumber = student.phoneNumber();
        var expectedBirthdate = student.studentDateOfBirth();
        var expectedSubjects = student.studentSubjects();
        var expectedHobbies = student.studentHobbies();
        var expectedPicture = student.picturePath();
        var expectedAddress = student.studentFlatAddress();
        var expectedStateAndCity = student.studentFullLocation();

        page.modal()
                .checkVisibility()
                .checkTitle()
                .checkColumnTitles()
                .checkCellValue(STUDENT_NAME_LABEL, expectedFullName)
                .checkCellValue(STUDENT_EMAIL_LABEL, expectedEmail)
                .checkCellValue(GENDER_LABEL, expectedGender)
                .checkCellValue(MOBILE_LABEL, expectedPhoneNumber)
                .checkCellValue(DATE_OF_BIRTH_LABEL, expectedBirthdate)
                .checkCellValue(SUBJECTS_LABEL, expectedSubjects)
                .checkCellValue(HOBBIES_LABEL, expectedHobbies)
                .checkCellValue(PICTURE_LABEL, expectedPicture)
                .checkCellValue(ADDRESS_LABEL, expectedAddress)
                .checkCellValue(STATE_AND_CITY_LABEL, expectedStateAndCity);
    }

    @Test
    void testFormWithMandatoryFields() {
        page.openPage()
                .removeAds()
                .setFirstName(student.firstName())
                .setLastName(student.lastName())
                .setPhoneNumber(student.phoneNumber())
                .setGender(student.gender())
                .submit();

        var expectedFullName = student.studentFullName();
        var expectedGender = student.gender().toString();
        var expectedPhoneNumber = student.phoneNumber();
        var expectedBirthdate = DateFormatter.formateDateToString(LocalDate.now());

        page.modal()
                .checkVisibility()
                .checkTitle()
                .checkColumnTitles()
                .checkCellValue(STUDENT_NAME_LABEL, expectedFullName)
                .checkEmptyCell(STUDENT_EMAIL_LABEL)
                .checkCellValue(GENDER_LABEL, expectedGender)
                .checkCellValue(MOBILE_LABEL, expectedPhoneNumber)
                .checkCellValue(DATE_OF_BIRTH_LABEL, expectedBirthdate)
                .checkEmptyCell(SUBJECTS_LABEL)
                .checkEmptyCell(HOBBIES_LABEL)
                .checkEmptyCell(PICTURE_LABEL)
                .checkEmptyCell(ADDRESS_LABEL)
                .checkEmptyCell(STATE_AND_CITY_LABEL);
    }

    @Test
    void testEmptyFormSending() {
        page.openPage()
                .removeAds()
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
