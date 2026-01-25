package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MissionList(
        @JsonProperty("missions")
        List<SpaceMission> missions
) {
}
