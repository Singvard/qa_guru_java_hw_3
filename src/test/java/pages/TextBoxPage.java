package pages;

import com.codeborne.selenide.SelenideElement;
import models.Student;
import utils.PageUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    private static final String PAGE_ADDRESS = "/text-box";
    private static final String ID_USER_NAME = "#userName";
    private static final String ID_USER_EMAIL = "#userEmail";
    private static final String ID_CURRENT_ADDRESS = "#currentAddress";
    private static final String ID_PERMANENT_ADDRESS = "#permanentAddress";
    private static final String ID_SUBMIT = "#submit";
    private static final String ID_NAME = "#name";
    private static final String ID_EMAIL = "#email";
    private static final String ID_OUTPUT = "#output";
    private static final String NAME_TEMPLATE = "Name:%s";
    private static final String EMAIL_TEMPLATE = "Email:%s";
    private static final String CURRENT_ADDRESS_TEMPLATE = "Current Address :%s";
    private static final String PERMANENT_ADDRESS_TEMPLATE = "Permananet Address :%s";

    private static final SelenideElement USER_NAME = $(ID_USER_NAME);
    private static final SelenideElement USER_EMAIL = $(ID_USER_EMAIL);
    private static final SelenideElement CURRENT_ADDRESS = $(ID_CURRENT_ADDRESS);
    private static final SelenideElement PERMANENT_ADDRESS = $(ID_PERMANENT_ADDRESS);
    private static final SelenideElement SUBMIT = $(ID_SUBMIT);
    private static final SelenideElement NAME = $(ID_NAME);
    private static final SelenideElement EMAIL = $(ID_EMAIL);
    private static final SelenideElement OUTPUT = $(ID_OUTPUT);

    public TextBoxPage openPage() {
        open(PAGE_ADDRESS);
        return this;
    }

    public TextBoxPage removeAds() {
        PageUtils.muteAds();
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        USER_NAME.setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        USER_EMAIL.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        CURRENT_ADDRESS.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        PERMANENT_ADDRESS.setValue(permanentAddress);
        return this;
    }

    public void submit() {
        SUBMIT.click();
    }

    public TextBoxPage verifyFullName(Student student) {
        PageUtils.checkTextValue(NAME, NAME_TEMPLATE.formatted(student.studentFullName()));
        return this;
    }

    public TextBoxPage verifyEmail(Student student) {
        PageUtils.checkTextValue(EMAIL, EMAIL_TEMPLATE.formatted(student.email()));
        return this;
    }

    public TextBoxPage verifyCurrentAddress(Student student) {
        PageUtils.checkTextValue(OUTPUT, ID_CURRENT_ADDRESS,
                CURRENT_ADDRESS_TEMPLATE.formatted(student.studentFlatAddress()));
        return this;
    }

    public TextBoxPage verifyPermanentAddress(Student student) {
        PageUtils.checkTextValue(OUTPUT, ID_PERMANENT_ADDRESS,
                PERMANENT_ADDRESS_TEMPLATE.formatted(student.studentFlatAddress()));
        return this;
    }
}
