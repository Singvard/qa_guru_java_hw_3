package models;

/**
 * Перечисление, представляющее гендерную принадлежность.
 * Используется для указания пола студента.
 *
 * <p>Каждое значение хранится в формате UPPER_CASE, но при преобразовании в строку
 * отображается в читаемом формате с заглавной первой буквой и остальными строчными.
 *
 * <p>Пример использования:
 * <pre>
 * Gender gender = Gender.FEMALE;
 * System.out.println(gender); // Выведет "Female"
 * </pre>
 */
public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    /**
     * Возвращает название гендера в читаемом формате.
     * Преобразует UPPER_CASE в формат с заглавной первой буквой и остальными строчными.
     *
     * @return название гендера в читаемом формате
     */
    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
