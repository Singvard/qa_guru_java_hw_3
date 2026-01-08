package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Утилитарный класс для форматирования дат.
 * Предоставляет методы для преобразования дат в строки в заданном формате.
 *
 * <p>Класс имеет приватный конструктор, так как предназначен только для
 * статического использования и не должен инстанциироваться.
 *
 * <p>Пример использования:
 * <pre>
 * LocalDate date = LocalDate.of(1990, 8, 12);
 * String formattedDate = DateFormatter.formateDateToString(date);
 * // Результат: "12 August,1990"
 * </pre>
 */
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
