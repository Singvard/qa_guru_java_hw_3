package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    private static final String DATE_PATTERN = "dd MMMM,yyyy";

    private DateFormatter() {
    }

    /**
     * Преобразует дату к строке в формате {@value DATE_PATTERN}
     *
     * @param date Преобразуемая дата
     * @return Строка в заданном формате
     */
    public static String formateDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH));
    }
}
