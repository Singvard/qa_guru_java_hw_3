package selenide;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class Homework3Test {
    public static final String MULTILINE_ADDRESS =
            """
            123456,
            г. Москва,
            ул. Пушкина,
            д. 5,
            кв. 109
            """;

    @Test
    void testForm() {
        //ARRANGE
        // Страница тестируемой формы
        open("https://demoqa.com/automation-practice-form");

        // Заполнить поле имени
        $("#firstName").setValue("Имя");

        // Заполнить поле фамилии
        $("#lastName").setValue("Фамилия");

        // Заполнить поле электронной почты
        $("#userEmail").setValue("test@test.test");

        // Выбрать радиокнопку пола
        $(byText("Male")).click();

        // Заполнить поле мобильного телефона
        $("#userNumber").setValue("9876543210");

        // Заполнить поле даты рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__month-select").selectOption("August");
        $$("div.react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(text("12"))
                .click();


        // Заполнить поле учебных предметов с автодополнением и множественным выбором
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("a");
        $(byText("Maths")).scrollTo().click();
        $("#subjectsInput").setValue("s");
        $(byText("English")).scrollTo().click();
        $("#subjectsInput").setValue("o");
        $(byText("Computer Science")).scrollTo().click();
        $("#subjectsInput").setValue("r");
        $(byText("Arts")).scrollTo().click();
        $$(".subjects-auto-complete__multi-value")
                .findBy(text("Arts"))
                .$(".subjects-auto-complete__multi-value__remove")
                .click();

        // Проставить чекбоксы хобби
        $(byText("Sports")).click();
        $(byText("Music")).click();

        // Загрузить аватар пользователя
        $("#uploadPicture").uploadFile(new File("src/test/resources/avatar.png"));

        // Заполнить поле адреса (многострочное)
        $("#currentAddress").setValue(MULTILINE_ADDRESS);

        // Выбрать штат в выпадающем списке штатов
        $("#state").click();
        $(byText("Rajasthan")).click();
        // Выбрать город в выпадающем списке городов
        $("#city").click();
        $(byText("Jaiselmer")).click();

        //ACT
        $("#submit").click();

        // ASSERT
        $(".modal.show").shouldBe(visible);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $$(".modal-body table thead th").shouldHave(texts("Label", "Values"));
        $x("//td[text()='Student Name']/following-sibling::td")
                .shouldHave(text("Имя Фамилия"));
        $x("//td[text()='Student Email']/following-sibling::td")
                .shouldHave(text("test@test.test"));
        $x("//td[text()='Gender']/following-sibling::td")
                .shouldHave(text("Male"));
        $x("//td[text()='Mobile']/following-sibling::td")
                .shouldHave(text("9876543210"));
        $x("//td[text()='Date of Birth']/following-sibling::td")
                .shouldHave(text("12 August,1990"));
        $x("//td[text()='Subjects']/following-sibling::td")
                .shouldHave(text("Maths, English, Computer Science"));
        $x("//td[text()='Hobbies']/following-sibling::td")
                .shouldHave(text("Sports, Music"));
        $x("//td[text()='Picture']/following-sibling::td")
                .shouldHave(text("avatar.png"));
        $x("//td[text()='Address']/following-sibling::td")
                .shouldHave(text("123456, г. Москва, ул. Пушкина, д. 5, кв. 109"));
        $x("//td[text()='State and City']/following-sibling::td")
                .shouldHave(text("Rajasthan Jaiselmer"));
    }
}
