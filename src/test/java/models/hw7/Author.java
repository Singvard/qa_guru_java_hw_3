package models.hw7;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record Author(
        String firstName,
        String lastName,
        String nationality,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        List<String> awards
) {
}
