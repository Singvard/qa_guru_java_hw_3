package models;

/**
 * Перечисление, представляющее хобби и увлечения.
 * Используется для указания интересов студентов.
 *
 * <p>Каждое хобби хранится в формате UPPER_CASE, но при преобразовании в строку
 * отображается в читаемом формате с заглавной первой буквой и остальными строчными.
 *
 * <p>Пример использования:
 * <pre>
 * Hobby hobby = Hobby.SPORTS;
 * System.out.println(hobby); // Выведет "Sports"
 * </pre>
 */
public enum Hobby {
    SPORTS,
    READING,
    MUSIC;

    /**
     * Возвращает название хобби в читаемом формате.
     * Преобразует UPPER_CASE в формат с заглавной первой буквой и остальными строчными.
     *
     * @return название хобби в читаемом формате
     */
    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
