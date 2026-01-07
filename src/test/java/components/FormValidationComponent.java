package components;

import utils.PageUtils;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;

public class FormValidationComponent {
    private static final String ID_FIRST_NAME = "#firstName";
    private static final String ID_LAST_NAME = "#lastName";
    private static final String ID_USER_EMAIL = "#userEmail";
    private static final String ID_USER_NUMBER = "#userNumber";
    private static final String ID_DATE_OF_BIRTH_INPUT = "#dateOfBirthInput";
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
    private static final String GENDER_LABELS_SELECTOR = "#genterWrapper label.custom-control-label";
    private static final String HOBBY_LABELS_SELECTOR = "#hobbiesWrapper label.custom-control-label";
    private static final String ID_CURRENT_ADDRESS = "#currentAddress";

    /**
     * Проверить, что форма была валидирована.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyFormWasValidated() {
        $(ID_USER_FORM).shouldHave(cssClass(WAS_VALIDATED));
        return this;
    }

    /**
     * Проверить, что поле имени имеет красную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyFirstNameHasRedBorder() {
        PageUtils.checkCssValue(ID_FIRST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле имени имеет предупреждающий значок
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyFirstNameHasWarning() {
        PageUtils.checkCssValue(ID_FIRST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле фамилии имеет красную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyLastNameHasRedBorder() {
        PageUtils.checkCssValue(ID_LAST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле фамилии имеет предупреждающий значок.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyLastNameHasWarning() {
        PageUtils.checkCssValue(ID_LAST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле электронной почты имеет зелёную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyEmailHasGreenBorder() {
        PageUtils.checkCssValue(ID_USER_EMAIL, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    /**
     * Проверить, что поле электронной почты имеет одобрительный значок.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyEmailHasApproval() {
        PageUtils.checkCssValue(ID_USER_EMAIL, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что все радиокнопки пола красного цвета.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyAllGenderOptionsAreRed() {
        PageUtils.checkAllElementsCssValue(GENDER_LABELS_SELECTOR, CSS_COLOR, RGBA_220_53_69_1);
        return this;
    }

    /**
     * Проверить, что поле мобильного номера имеет красную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyMobileNumberHasRedBorder() {
        PageUtils.checkCssValue(ID_USER_NUMBER, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    /**
     * Проверить, что поле мобильного номера имеет предупреждающий значок.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyMobileNumberHasWarning() {
        PageUtils.checkCssValue(ID_USER_NUMBER, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что поле даты рождения имеет зелёную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyDateOfBirthHasGreenBorder() {
        PageUtils.checkCssValue(ID_DATE_OF_BIRTH_INPUT, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    /**
     * Проверить, что поле даты рождения имеет одобрительный значок.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyDateOfBirthHasApproval() {
        PageUtils.checkCssValue(ID_DATE_OF_BIRTH_INPUT, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    /**
     * Проверить, что все чекбоксы хобби зелёного цвета.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyAllHobbiesOptionsAreGreen() {
        PageUtils.checkAllElementsCssValue(HOBBY_LABELS_SELECTOR, CSS_COLOR, RGBA_40_167_69_1);
        return this;
    }

    /**
     * Проверить, что поле адреса имеет зелёную границу.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyAddressHasGreenBorder() {
        PageUtils.checkCssValue(ID_CURRENT_ADDRESS, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    /**
     * Проверить, что поле адреса имеет одобрительный значок.
     *
     * @return Текущий объект FormValidationComponent для цепочки вызовов
     */
    public FormValidationComponent verifyAddressHasApproval() {
        PageUtils.checkCssValue(ID_CURRENT_ADDRESS, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }
}
