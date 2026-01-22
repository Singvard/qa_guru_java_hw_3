package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Employee(
        @JsonProperty("employee_id") String id,
        @JsonProperty("full_name") String fullName,
        Position position,
        Department department,
        double salary,
        @JsonProperty("yearsOfService") int yearsOfService
) {
}
