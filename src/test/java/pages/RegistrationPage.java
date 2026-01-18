package pages;

import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;
import components.FormValidationComponent;
import components.ModalComponent;
import models.*;
import utils.PageUtils;

import java.time.LocalDate;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    private static final String PAGE_ADDRESS = "/automation-practice-form";
    private static final String ID_FIRST_NAME = "#firstName";
    private static final String ID_LAST_NAME = "#lastName";
    private static final String ID_USER_EMAIL = "#userEmail";
    private static final String ID_GENDER_WRAPPER = "#genterWrapper";
    private static final String ID_USER_NUMBER = "#userNumber";
    private static final String ID_SUBJECTS_INPUT = "#subjectsInput";
    private static final String ID_HOBBIES_WRAPPER = "#hobbiesWrapper";
    private static final String ID_UPLOAD_PICTURE = "#uploadPicture";
    private static final String ID_CURRENT_ADDRESS = "#currentAddress";
    private static final String ID_STATE = "#state";
    private static final String ID_CITY = "#city";
    private static final String ID_SUBMIT = "#submit";

    private static final SelenideElement FIRST_NAME = $(ID_FIRST_NAME);
    private static final SelenideElement LAST_NAME = $(ID_LAST_NAME);
    private static final SelenideElement USER_EMAIL = $(ID_USER_EMAIL);
    private static final SelenideElement GENDER_WRAPPER = $(ID_GENDER_WRAPPER);
    private static final SelenideElement USER_NUMBER = $(ID_USER_NUMBER);
    private static final SelenideElement SUBJECTS_INPUT = $(ID_SUBJECTS_INPUT);
    private static final SelenideElement HOBBIES_WRAPPER = $(ID_HOBBIES_WRAPPER);
    private static final SelenideElement UPLOAD_PICTURE = $(ID_UPLOAD_PICTURE);
    private static final SelenideElement CURRENT_ADDRESS = $(ID_CURRENT_ADDRESS);
    private static final SelenideElement STATE = $(ID_STATE);
    private static final SelenideElement CITY = $(ID_CITY);
    private static final SelenideElement SUBMIT = $(ID_SUBMIT);

    private final ModalComponent modal = new ModalComponent();
    private final CalendarComponent calendar = new CalendarComponent();
    private final FormValidationComponent validation = new FormValidationComponent();

    public ModalComponent modal() {
        return modal;
    }

    public FormValidationComponent validation() {
        return validation;
    }

    public RegistrationPage openPage() {
        open(PAGE_ADDRESS);
        return this;
    }

    public RegistrationPage removeAds() {
        PageUtils.muteAds();
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        FIRST_NAME.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        LAST_NAME.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        USER_EMAIL.setValue(email);
        return this;
    }

    public RegistrationPage setGender(Gender gender) {
        GENDER_WRAPPER.$(byText(gender.toString())).click();
        return this;
    }

    public RegistrationPage setPhoneNumber(String phoneNumber) {
        USER_NUMBER.setValue(phoneNumber);
        return this;
    }

    public RegistrationPage setBirthday(LocalDate birthday) {
        calendar.selectDate(birthday);
        return this;
    }

    public RegistrationPage setSubjects(List<Subject> subjects) {
        SUBJECTS_INPUT.click();
        subjects.forEach(subject -> {
            SUBJECTS_INPUT.setValue(subject.toString().toLowerCase().substring(0, 1));
            $(byText(subject.toString())).scrollTo().click();
        });
        return this;
    }

    public RegistrationPage setHobbies(List<Hobby> hobbies) {
        hobbies.forEach(hobby ->
                HOBBIES_WRAPPER.$(byText(hobby.toString())).click()
        );
        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        UPLOAD_PICTURE.uploadFromClasspath(path);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        CURRENT_ADDRESS.setValue(address);
        return this;
    }

    public RegistrationPage setState(State state) {
        STATE.click();
        $(byText(state.getName())).click();
        return this;
    }

    public RegistrationPage setCity(City city) {
        CITY.click();
        $(byText(city.toString())).click();
        return this;
    }

    public void submit() {
        SUBMIT.click();
    }
}
