package utils;

import models.City;
import models.State;

import java.util.*;

public class StateCityMapper {
    private static final EnumMap<State, Set<City>> REGION_CITIES = new EnumMap<>(State.class);

    static {
        REGION_CITIES.put(State.NCR, EnumSet.of(City.DELHI, City.GURGAON, City.NOIDA));
        REGION_CITIES.put(State.UTTAR_PRADESH, EnumSet.of(City.AGRA, City.LUCKNOW, City.MERRUT));
        REGION_CITIES.put(State.HARYANA, EnumSet.of(City.KARNAL, City.PANIPAT));
        REGION_CITIES.put(State.RAJASTHAN, EnumSet.of(City.JAIPUR, City.JAISELMER));
    }

    public static Set<City> getStateCities(State state) {
        return Collections.unmodifiableSet(
                REGION_CITIES.getOrDefault(state, EnumSet.noneOf(City.class))
        );
    }

    public static State getStateForCity(City city) {
        return REGION_CITIES.entrySet().stream()
                .filter(entry -> entry.getValue().contains(city))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }
}
