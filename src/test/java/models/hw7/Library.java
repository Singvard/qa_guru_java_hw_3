package models.hw7;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Library(
        String name,
        String address,
        @JsonProperty("establishedYear") int establishedYear,
        @JsonProperty("squareMeters") double squareMeters,
        @JsonProperty("hasReadingRooms") boolean hasReadingRooms,
        List<Book> books,
        List<Employee> employees,
        OpeningHours openingHours
) {
}
