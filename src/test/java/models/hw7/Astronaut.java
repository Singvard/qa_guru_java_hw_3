package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Astronaut(
        String name,
        String rank,
        String role,
        int age,
        boolean firstFlight,

        @JsonProperty("missions")
        int totalMissions,

        @JsonProperty("eva_time")
        Integer evaMinutes, // время, проведённое в открытом космосе

        @JsonProperty("notes")
        String specialNotes
) {
}
