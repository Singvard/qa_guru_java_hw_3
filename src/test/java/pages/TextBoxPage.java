package pages;

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

    public TextBoxPage openPage() {
        open(PAGE_ADDRESS);
        PageUtils.muteAds();
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        $(ID_USER_NAME).setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        $(ID_USER_EMAIL).setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        $(ID_CURRENT_ADDRESS).setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        $(ID_PERMANENT_ADDRESS).setValue(permanentAddress);
        return this;
    }

    public void submit() {
        $(ID_SUBMIT).click();
    }

    public TextBoxPage verifyFullName(Student student) {
        PageUtils.checkTextValue(ID_NAME, NAME_TEMPLATE.formatted(student.studentFullName()));
        return this;
    }

    public TextBoxPage verifyEmail(Student student) {
        PageUtils.checkTextValue(ID_EMAIL, EMAIL_TEMPLATE.formatted(student.email()));
        return this;
    }

    public TextBoxPage verifyCurrentAddress(Student student) {
        PageUtils.checkTextValue(ID_OUTPUT, ID_CURRENT_ADDRESS,
                CURRENT_ADDRESS_TEMPLATE.formatted(student.studentFlatAddress()));
        return this;
    }

    public TextBoxPage verifyPermanentAddress(Student student) {
        PageUtils.checkTextValue(ID_OUTPUT, ID_PERMANENT_ADDRESS,
                PERMANENT_ADDRESS_TEMPLATE.formatted(student.studentFlatAddress()));
        return this;
    }
}
