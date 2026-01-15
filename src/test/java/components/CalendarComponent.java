package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    private static final String ID_DATE_OF_BIRTH_INPUT = "#dateOfBirthInput";
    private static final String CSS_DATEPICKER_YEAR = ".react-datepicker__year-select";
    private static final String CSS_DATEPICKER_MONTH = ".react-datepicker__month-select";
    private static final String CSS_DATEPICKER_DAY = "div.react-datepicker__day:not(.react-datepicker__day--outside-month)";
    private static final SelenideElement CALENDAR = $(ID_DATE_OF_BIRTH_INPUT);
    private static final SelenideElement YEAR = $(CSS_DATEPICKER_YEAR);
    private static final SelenideElement MONTH = $(CSS_DATEPICKER_MONTH);
    private static final ElementsCollection DAY = $$(CSS_DATEPICKER_DAY);

    public void selectDate(LocalDate date) {
        CALENDAR.click();
        var year = Integer.toString(date.getYear());
        YEAR.selectOption(year);
        var month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        MONTH.selectOption(month);
        var day = Integer.toString(date.getDayOfMonth());
        DAY.findBy(text(day)).click();
    }
}
