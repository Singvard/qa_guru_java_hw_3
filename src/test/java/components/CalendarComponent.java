package components;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс, моделирующий компонент типа календарь для выбора даты рождения студента.
 */
public class CalendarComponent {
    private static final String ID_DATE_OF_BIRTH_INPUT = "#dateOfBirthInput";
    private static final String CSS_DATEPICKER_YEAR = ".react-datepicker__year-select";
    private static final String CSS_DATEPICKER_MONTH = ".react-datepicker__month-select";
    private static final String CSS_DATEPICKER_DAY = "div.react-datepicker__day:not(.react-datepicker__day--outside-month)";

    /**
     * Указать дату в компоненте календаря.
     *
     * @param date Дата
     */
    public void selectDate(LocalDate date) {
        $(ID_DATE_OF_BIRTH_INPUT).click();
        var year = Integer.toString(date.getYear());
        $(CSS_DATEPICKER_YEAR).selectOption(year);
        var month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        $(CSS_DATEPICKER_MONTH).selectOption(month);
        var day = Integer.toString(date.getDayOfMonth());
        $$(CSS_DATEPICKER_DAY)
                .findBy(text(day))
                .click();
    }
}
