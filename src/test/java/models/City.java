package models;

/**
 * Перечисление, представляющее города.
 * Предоставляет список городов, используемых в системе для указания места проживания.
 *
 * <p>Каждый город хранится в формате UPPER_CASE, но при преобразовании в строку
 * отображается в читаемом формате с заглавной первой буквой и остальными строчными.
 *
 * <p>Пример использования:
 * <pre>
 * City city = City.JAIPUR;
 * System.out.println(city); // Выведет "Jaipur"
 * </pre>
 *
 * @see State
 */
public enum City {
    DELHI,
    GURGAON,
    NOIDA,
    AGRA,
    LUCKNOW,
    MERRUT,
    KARNAL,
    PANIPAT,
    JAIPUR,
    JAISELMER;

    /**
     * Возвращает название города в читаемом формате.
     * Преобразует UPPER_CASE в формат с заглавной первой буквой и остальными строчными.
     *
     * @return название города в читаемом формате
     */
    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
