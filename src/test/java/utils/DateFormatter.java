package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter {

    private static final String DATE_PATTERN = "dd MMMM,yyyy";

    private DateFormatter() {
    }

    public static String formateDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH));
    }
}
