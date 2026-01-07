package pages;

import models.City;
import models.Gender;
import models.Hobby;
import models.State;
import utils.PageUtils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private static final String PAGE_ADDRESS = "/automation-practice-form";
    private static final String ID_FIRST_NAME = "#firstName";
    private static final String ID_LAST_NAME = "#lastName";
    private static final String ID_USER_EMAIL = "#userEmail";
    private static final String ID_GENDER_WRAPPER = "#genterWrapper";
    private static final String ID_USER_NUMBER = "#userNumber";
    private static final String ID_DATE_OF_BIRTH_INPUT = "#dateOfBirthInput";
    private static final String CSS_DATEPICKER_YEAR = ".react-datepicker__year-select";
    private static final String CSS_DATEPICKER_MONTH = ".react-datepicker__month-select";
    private static final String CSS_DATEPICKER_DAY = "div.react-datepicker__day:not(.react-datepicker__day--outside-month)";
    private static final String ID_SUBJECTS_INPUT = "#subjectsInput";
    private static final String CSS_ADDED_SUBJECTS = ".subjects-auto-complete__multi-value";
    private static final String CSS_SUBJECT_REMOVER = ".subjects-auto-complete__multi-value__remove";
    private static final String ID_HOBBIES_WRAPPER = "#hobbiesWrapper";
    private static final String ID_UPLOAD_PICTURE = "#uploadPicture";
    private static final String ID_CURRENT_ADDRESS = "#currentAddress";
    private static final String ID_STATE = "#state";
    private static final String ID_CITY = "#city";
    private static final String ID_SUBMIT = "#submit";
    private static final String CSS_MODAL_SHOW = ".modal.show";
    private static final String CSS_MODAL_TITLE = ".modal-title";
    private static final String TITLE = "Thanks for submitting the form";
    private static final String CSS_COLUMN_TITLE = ".modal-body table thead th";
    private static final String LABEL = "Label";
    private static final String VALUES = "Values";
    private static final String XPATH_ROW_VALUES_TEMPLATE = "//td[text()='%s']/following-sibling::td";
    private static final String ID_USER_FORM = "#userForm";
    private static final String WAS_VALIDATED = "was-validated";
    private static final String CSS_BORDER_COLOR = "border-color";
    private static final String RGB_220_53_69 = "rgb(220, 53, 69)";
    private static final String RGB_40_167_69 = "rgb(40, 167, 69)";
    private static final String CSS_COLOR = "color";
    private static final String RGBA_220_53_69_1 = "rgba(220, 53, 69, 1)";
    private static final String RGBA_40_167_69_1 = "rgba(40, 167, 69, 1)";
    private static final String CSS_BACKGROUND_IMAGE = "background-image";
    private static final String WARNING_IMAGE_URL = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' " +
            "width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' " +
            "r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' " +
            "fill='%23dc3545' stroke='none'/%3e%3c/svg%3e\")";
    private static final String APPROVAL_IMAGE_URL = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' " +
            "width='8' height='8' viewBox='0 0 8 8'%3e%3cpath fill='%2328a745' d='M2.3 6.73L.6 4.53c-.4-1.04.46-1.4 " +
            "1.1-.8l1.1 1.4 3.4-3.8c.6-.63 1.6-.27 1.2.7l-4 4.6c-.43.5-.8.4-1.1.1z'/%3e%3c/svg%3e\")";
    private static final String CUSTOM_CONTROL_LABEL = " label.custom-control-label";
    private static final String GENDER_LABELS = ID_GENDER_WRAPPER + CUSTOM_CONTROL_LABEL;
    private static final String HOBBY_LABELS = ID_HOBBIES_WRAPPER + CUSTOM_CONTROL_LABEL;


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
        $(ID_DATE_OF_BIRTH_INPUT).click();
        var year = Integer.toString(birthday.getYear());
        $(CSS_DATEPICKER_YEAR).selectOption(year);
        var month = birthday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        $(CSS_DATEPICKER_MONTH).selectOption(month);
        var day = Integer.toString(birthday.getDayOfMonth());
        $$(CSS_DATEPICKER_DAY)
                .findBy(text(day))
                .click();
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

    /**
     * Проверить отображение модального окна.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkModalVisibility() {
        $(CSS_MODAL_SHOW).shouldBe(visible);
        return this;
    }

    /**
     * Проверить скрытость модального окна.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkModalInvisibility() {
        $(CSS_MODAL_SHOW).shouldNotBe(visible);
        return this;
    }

    /**
     * Проверить заголовок модального окна.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkModalTitle() {
        $(CSS_MODAL_TITLE).shouldHave(text(TITLE));
        return this;
    }

    /**
     * Проверить шапку таблицы (названия столбцов).
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkColumnTitles() {
        $$(CSS_COLUMN_TITLE).shouldHave(texts(LABEL, VALUES));
        return this;
    }

    /**
     * Проверить ячейки строки (метка и значение).
     *
     * @param rowLabel Метка строки
     * @param value    Значение
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkCellValue(String rowLabel, String value) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldHave(text(value));
        return this;
    }

    /**
     * Проверить, что ячейка строки пуста.
     *
     * @param rowLabel Метка строки
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkEmptyCell(String rowLabel) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldBe(empty);
        return this;
    }

    /**
     * Проверить, что форма была валидирована.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkFormWasValidated() {
        $(ID_USER_FORM).shouldHave(cssClass(WAS_VALIDATED));
        return this;
    }

    /**
     * Проверить, что поле имени имеет красную границу.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkFirstNameHasRedBorder() {
        PageUtils.checkCssValue(ID_FIRST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле имени имеет предупреждающий значок
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkFirstNameHasWarning() {
        PageUtils.checkCssValue(ID_FIRST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле фамилии имеет красную границу.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkLastNameHasRedBorder() {
        PageUtils.checkCssValue(ID_LAST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле фамилии имеет предупреждающий значок.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkLastNameHasWarning() {
        PageUtils.checkCssValue(ID_LAST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле электронной почты имеет зелёную границу.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkEmailHasGreenBorder() {
        PageUtils.checkCssValue(ID_USER_EMAIL, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    /**
     * Проверить, что поле электронной почты имеет одобрительный значок.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkEmailHasApproval() {
        PageUtils.checkCssValue(ID_USER_EMAIL, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что все радиокнопки пола красного цвета.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkAllGenderOptionsAreRed() {
        PageUtils.checkAllElementsCssValue(GENDER_LABELS, CSS_COLOR, RGBA_220_53_69_1);
        return this;
    }

    /**
     * Проверить, что поле мобильного номера имеет красную границу.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkMobileNumberHasRedBorder() {
        PageUtils.checkCssValue(ID_USER_NUMBER, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле мобильного номера имеет предупреждающий значок.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkMobileNumberHasWarning() {
        PageUtils.checkCssValue(ID_USER_NUMBER, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле даты рождения имеет зелёную границу.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkDateOfBirthHasGreenBorder() {
        PageUtils.checkCssValue(ID_DATE_OF_BIRTH_INPUT, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    /**
     * Проверить, что поле даты рождения имеет одобрительный значок.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkDateOfBirthHasApproval() {
        PageUtils.checkCssValue(ID_DATE_OF_BIRTH_INPUT, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что все чекбоксы хобби зелёного цвета.
     *
     * @return Текущий объект RegistrationPage для цепочки вызовов
     */
    public RegistrationPage checkAllHobbiesOptionsAreGreen() {
        PageUtils.checkAllElementsCssValue(HOBBY_LABELS, CSS_COLOR, RGBA_40_167_69_1);
        return this;
    }
}
