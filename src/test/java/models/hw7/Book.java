package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Book(
        String isbn,
        String title,
        Author author,
        @JsonProperty("publicationYear") int publicationYear,
        Genre genre,
        int pages,
        @JsonProperty("isAvailable") boolean isAvailable,
        List<String> tags
) {
}
