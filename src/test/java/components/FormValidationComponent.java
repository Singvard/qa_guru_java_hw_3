package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.PageUtils;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    private static final SelenideElement FORM = $(ID_USER_FORM);
    private static final SelenideElement FIRST_NAME = $(ID_FIRST_NAME);
    private static final SelenideElement LAST_NAME = $(ID_LAST_NAME);
    private static final SelenideElement USER_EMAIL = $(ID_USER_EMAIL);
    private static final ElementsCollection GENDER_LABELS = $$(GENDER_LABELS_SELECTOR);
    private static final SelenideElement USER_NUMBER = $(ID_USER_NUMBER);
    private static final SelenideElement DATE_OF_BIRTH = $(ID_DATE_OF_BIRTH_INPUT);
    private static final ElementsCollection HOBBY_LABELS = $$(HOBBY_LABELS_SELECTOR);
    private static final SelenideElement CURRENT_ADDRESS = $(ID_CURRENT_ADDRESS);

    public FormValidationComponent verifyFormWasValidated() {
        FORM.shouldHave(cssClass(WAS_VALIDATED));
        return this;
    }

    public FormValidationComponent verifyFirstNameHasRedBorder() {
        PageUtils.checkCssValue(FIRST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    public FormValidationComponent verifyFirstNameHasWarning() {
        PageUtils.checkCssValue(FIRST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    public FormValidationComponent verifyLastNameHasRedBorder() {
        PageUtils.checkCssValue(LAST_NAME, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    public FormValidationComponent verifyLastNameHasWarning() {
        PageUtils.checkCssValue(LAST_NAME, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    public FormValidationComponent verifyEmailHasGreenBorder() {
        PageUtils.checkCssValue(USER_EMAIL, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    public FormValidationComponent verifyEmailHasApproval() {
        PageUtils.checkCssValue(USER_EMAIL, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    public FormValidationComponent verifyAllGenderOptionsAreRed() {
        PageUtils.checkAllElementsCssValue(GENDER_LABELS, CSS_COLOR, RGBA_220_53_69_1);
        return this;
    }

    public FormValidationComponent verifyMobileNumberHasRedBorder() {
        PageUtils.checkCssValue(USER_NUMBER, CSS_BORDER_COLOR, RGB_220_53_69);
        return this;
    }

    public FormValidationComponent verifyMobileNumberHasWarning() {
        PageUtils.checkCssValue(USER_NUMBER, CSS_BACKGROUND_IMAGE, WARNING_IMAGE_URL);
        return this;
    }

    public FormValidationComponent verifyDateOfBirthHasGreenBorder() {
        PageUtils.checkCssValue(DATE_OF_BIRTH, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    public FormValidationComponent verifyDateOfBirthHasApproval() {
        PageUtils.checkCssValue(DATE_OF_BIRTH, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }

    public FormValidationComponent verifyAllHobbiesOptionsAreGreen() {
        PageUtils.checkAllElementsCssValue(HOBBY_LABELS, CSS_COLOR, RGBA_40_167_69_1);
        return this;
    }

    public FormValidationComponent verifyAddressHasGreenBorder() {
        PageUtils.checkCssValue(CURRENT_ADDRESS, CSS_BORDER_COLOR, RGB_40_167_69);
        return this;
    }

    public FormValidationComponent verifyAddressHasApproval() {
        PageUtils.checkCssValue(CURRENT_ADDRESS, CSS_BACKGROUND_IMAGE, APPROVAL_IMAGE_URL);
        return this;
    }
}
