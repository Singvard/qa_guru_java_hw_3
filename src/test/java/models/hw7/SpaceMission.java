package models.hw7;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record SpaceMission(
        String missionName,
        String spacecraft,
        String country,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime launchDateTime,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime landingDateTime,

        @JsonProperty("duration")
        int durationMinutes,

        @JsonProperty("crew")
        List<Astronaut> crewMembers,

        String missionObjective,
        boolean evaPerformed, // был ли выход в открытый космос
        double orbitAltitudeKm,
        int orbitsCompleted,

        @JsonProperty("status")
        String missionStatus
) {
}
