package pages;

import components.CalendarComponent;
import components.FormValidationComponent;
import components.ModalComponent;
import models.City;
import models.Gender;
import models.Hobby;
import models.State;
import utils.PageUtils;

import java.time.LocalDate;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private static final String PAGE_ADDRESS = "/automation-practice-form";
    private static final String ID_FIRST_NAME = "#firstName";
    private static final String ID_LAST_NAME = "#lastName";
    private static final String ID_USER_EMAIL = "#userEmail";
    private static final String ID_GENDER_WRAPPER = "#genterWrapper";
    private static final String ID_USER_NUMBER = "#userNumber";
    private static final String ID_SUBJECTS_INPUT = "#subjectsInput";
    private static final String CSS_ADDED_SUBJECTS = ".subjects-auto-complete__multi-value";
    private static final String CSS_SUBJECT_REMOVER = ".subjects-auto-complete__multi-value__remove";
    private static final String ID_HOBBIES_WRAPPER = "#hobbiesWrapper";
    private static final String ID_UPLOAD_PICTURE = "#uploadPicture";
    private static final String ID_CURRENT_ADDRESS = "#currentAddress";
    private static final String ID_STATE = "#state";
    private static final String ID_CITY = "#city";
    private static final String ID_SUBMIT = "#submit";

    private final ModalComponent modal = new ModalComponent();
    private final CalendarComponent calendar = new CalendarComponent();
    private final FormValidationComponent validation = new FormValidationComponent();

    public ModalComponent modal() {
        return modal;
    }

    public FormValidationComponent validation() {
        return validation;
    }

    /**
     * Открыть страницу регистрации нового пользователя.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage openPage() {
        open(PAGE_ADDRESS);
        PageUtils.muteAds();
        return this;
    }

    /**
     * Заполнить поле имени пользователя.
     *
     * @param firstName Имя
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setFirstName(String firstName) {
        $(ID_FIRST_NAME).setValue(firstName);
        return this;
    }

    /**
     * Заполнить поле фамилии пользователя.
     *
     * @param lastName Фамилия
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setLastName(String lastName) {
        $(ID_LAST_NAME).setValue(lastName);
        return this;
    }

    /**
     * Заполнить поле электронной почты пользователя.
     *
     * @param email Адрес электронной почты
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setEmail(String email) {
        $(ID_USER_EMAIL).setValue(email);
        return this;
    }

    /**
     * Указать пол пользователя.
     *
     * @param gender Пол
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setGender(Gender gender) {
        $(ID_GENDER_WRAPPER).$(byText(gender.toString())).click();
        return this;
    }

    /**
     * Заполнить поле телефонного номера пользователя.
     *
     * @param phoneNumber Номер телефона
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setPhoneNumber(String phoneNumber) {
        $(ID_USER_NUMBER).setValue(phoneNumber);
        return this;
    }

    /**
     * Указать дату рождения пользователя.
     *
     * @param birthday Дата рождения
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setBirthday(LocalDate birthday) {
        calendar.selectDate(birthday);
        return this;
    }

    /**
     * Указать учебные предметы, посещаемые пользователем.
     *
     * @param subjects Список посещаемых предметов
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setSubjects(List<String> subjects) {
        $(ID_SUBJECTS_INPUT).click();
        subjects.forEach(subject -> {
            $(ID_SUBJECTS_INPUT).setValue(subject.toLowerCase().substring(0, 1));
            $(byText(subject)).scrollTo().click();
        });
        return this;
    }

    /**
     * Удалить учебные предметы, посещаемые пользователем.
     *
     * @param subjects Список удаляемых предметов
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage deleteSubjects(List<String> subjects) {
        subjects.forEach(subject ->
                $$(CSS_ADDED_SUBJECTS)
                        .findBy(text(subject))
                        .$(CSS_SUBJECT_REMOVER)
                        .click()
        );
        return this;
    }

    /**
     * Указать хобби пользователя.
     *
     * @param hobbies Список хобби
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setHobbies(List<Hobby> hobbies) {
        hobbies.forEach(hobby ->
                $(ID_HOBBIES_WRAPPER).$(byText(hobby.toString())).click()
        );
        return this;
    }

    /**
     * Загрузить фото пользователя (аватар).
     *
     * @param path Путь к файлу изображения
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage uploadPicture(String path) {
        $(ID_UPLOAD_PICTURE).uploadFromClasspath(path);
        return this;
    }

    /**
     * Указать текущий адрес пользователя.
     *
     * @param address Адрес
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setAddress(String address) {
        $(ID_CURRENT_ADDRESS).setValue(address);
        return this;
    }

    /**
     * Указать штат пользователя.
     *
     * @param state Штат
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setState(State state) {
        $(ID_STATE).click();
        $(byText(state.getName())).click();
        return this;
    }

    /**
     * Указать город пользователя.
     *
     * @param city Город
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage setCity(City city) {
        $(ID_CITY).click();
        $(byText(city.toString())).click();
        return this;
    }

    /**
     * Кликнуть кнопку SUBMIT
     */
    public void submit() {
        $(ID_SUBMIT).click();
    }
}
