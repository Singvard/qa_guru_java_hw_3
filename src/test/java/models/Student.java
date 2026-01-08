package models;

import utils.DateFormatter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий студента с его персональными данными и атрибутами.
 * Реализован в виде record, что обеспечивает автоматическую генерацию методов
 * доступа, equals(), hashCode() и toString() для всех полей.
 *
 * <p>Класс также предоставляет удобные методы для форматирования данных студента
 * в читаемый вид для отображения в пользовательском интерфейсе или отчетах.
 *
 * <p>Пример использования:
 * <pre>
 * Student student = new Student(
 *     "Иван",
 *     "Иванов",
 *     "ivan@example.com",
 *     Gender.MALE,
 *     "9876543210",
 *     LocalDate.of(1999, 5, 15),
 *     List.of("Maths", "Physics"),
 *     List.of("Arts"),
 *     List.of(Hobby.SPORTS, Hobby.READING),
 *     "avatar.jpg",
 *     "ул. Ленина, д. 10",
 *     State.RAJASTHAN,
 *     City.JAISELMER
 * );
 * </pre>
 *
 * @see Gender
 * @see Hobby
 * @see State
 * @see City
 * @see DateFormatter
 */
public record Student(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        String phoneNumber,
        LocalDate birthdate,
        List<String> addedSubjects,
        List<String> deletedSubjects,
        List<Hobby> hobbies,
        String picturePath,
        String address,
        State state,
        City city
) {
    /**
     * Возвращает полное имя студента в формате "Имя Фамилия".
     *
     * @return полное имя студента
     */
    public String studentFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Возвращает дату рождения студента в отформатированном строковом виде.
     * Форматирование выполняется с помощью {@link DateFormatter#formateDateToString(LocalDate)}.
     *
     * @return отформатированная дата рождения
     * @see DateFormatter#formateDateToString(LocalDate)
     */
    public String studentDateOfBirth() {
        return DateFormatter.formateDateToString(birthdate);
    }

    /**
     * Возвращает список изучаемых предметов студента.
     * Список формируется как разность между {@link #addedSubjects} и {@link #deletedSubjects}.
     * Предметы выводятся в виде строки, разделенной запятыми.
     *
     * @return строка с перечислением изучаемых предметов
     */
    public String studentSubjects() {
        return addedSubjects.stream()
                .filter(subject -> !deletedSubjects.contains(subject))
                .collect(Collectors.joining(", "));
    }

    /**
     * Возвращает хобби студента в виде строки, разделенной запятыми.
     *
     * @return строка с перечислением хобби
     */
    public String studentHobbies() {
        return hobbies.stream()
                .map(Hobby::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * Преобразует многострочный адрес в однострочный формат.
     * Удаляются пустые строки, и все строки объединяются через пробел.
     *
     * @return однострочное представление адреса
     */
    public String studentFlatAddress() {
        return address.lines()
                .map(String::strip)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining(" "));
    }

    /**
     * Возвращает полное местоположение студента в формате "Штат Город".
     *
     * @return строковое представление местоположения
     */
    public String studentFullLocation() {
        return state + " " + city;
    }
}
