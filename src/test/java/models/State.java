package models;

/**
 * Перечисление, представляющее штаты/области.
 * Используется для указания места проживания или регистрации.

 *
 * <p>Пример использования:
 * <pre>
 * State state = State.RAJASTHAN;
 * System.out.println(state.getName()); // Выведет "Rajasthan"
 * </pre>
 *
 * @see City
 */
public enum State {
    NCR("NCR"),
    UTTAR_PRADESH("Uttar Pradesh"),
    HARYANA("Haryana"),
    RAJASTHAN("Rajasthan");

    private final String name;

    State(String name) {
        this.name = name;
    }

    /**
     * Возвращает человекочитаемое название штата.
     *
     * @return название штата в читаемом формате
     */
    public String getName() {
        return name;
    }
}
