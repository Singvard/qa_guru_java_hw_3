package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpeningHours(
        @JsonProperty("mondayToFriday") String mondayToFriday,
        String saturday,
        String sunday,
        @JsonProperty("is24hours") boolean is24hours,
        List<String> holidays
) {
}
