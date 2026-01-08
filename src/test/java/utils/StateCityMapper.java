package utils;

import models.City;
import models.State;

import java.util.*;

/**
 * Утилитарный класс для маппинга между штатами и городами.
 * Предоставляет методы для получения городов по штату и штата по городу.
 *
 * <p>Использует статическую инициализацию для создания соответствий между
 * штатами и наборами городов. Все возвращаемые коллекции являются неизменяемыми.
 *
 * <p>Пример использования:
 * <pre>
 * // Получить города штата Раджастхан
 * Set&lt;City&gt; rajasthanCities = StateCityMapper.getStateCities(State.RAJASTHAN);
 *
 * // Получить штат для города Джайпур
 * State state = StateCityMapper.getStateForCity(City.JAIPUR);
 * </pre>
 *
 * @see State
 * @see City
 */
public class StateCityMapper {
    private static final EnumMap<State, Set<City>> REGION_CITIES = new EnumMap<>(State.class);

    static {
        REGION_CITIES.put(State.NCR, EnumSet.of(City.DELHI, City.GURGAON, City.NOIDA));
        REGION_CITIES.put(State.UTTAR_PRADESH, EnumSet.of(City.AGRA, City.LUCKNOW, City.MERRUT));
        REGION_CITIES.put(State.HARYANA, EnumSet.of(City.KARNAL, City.PANIPAT));
        REGION_CITIES.put(State.RAJASTHAN, EnumSet.of(City.JAIPUR, City.JAISELMER));
    }

    /**
     * Возвращает набор городов для указанного штата.
     * Возвращаемый набор является неизменяемым.
     *
     * @param state штат, для которого нужно получить города
     * @return неизменяемый набор городов, принадлежащих указанному штату
     * @throws NullPointerException если {@code state} равен {@code null}
     */
    public static Set<City> getStateCities(State state) {
        return Collections.unmodifiableSet(
                REGION_CITIES.getOrDefault(state, EnumSet.noneOf(City.class))
        );
    }

    /**
     * Возвращает штат, к которому принадлежит указанный город.
     *
     * @param city город, для которого нужно найти соответствующий штат
     * @return штат, содержащий указанный город
     * @throws NoSuchElementException если город не найден ни в одном штате
     * @throws NullPointerException если {@code city} равен {@code null}
     */
    public static State getStateForCity(City city) {
        return REGION_CITIES.entrySet().stream()
                .filter(entry -> entry.getValue().contains(city))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }
}
