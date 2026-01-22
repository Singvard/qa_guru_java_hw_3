package models.hw7;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Genre {
    @JsonProperty("ХУДОЖЕСТВЕННАЯ_ЛИТЕРАТУРА")
    FICTION,

    @JsonProperty("НЕХУДОЖЕСТВЕННАЯ_ЛИТЕРАТУРА")
    NON_FICTION,

    @JsonProperty("ТЕХНИЧЕСКАЯ_ЛИТЕРАТУРА")
    TECHNICAL,

    @JsonProperty("БИОГРАФИЯ")
    BIOGRAPHY,

    @JsonProperty("АНТИУТОПИЯ")
    DYSTOPIAN,

    @JsonProperty("ФЭНТЕЗИ")
    FANTASY,

    @JsonProperty("НАУЧНАЯ_ФАНТАСТИКА")
    SCIENCE_FICTION,

    @JsonProperty("ДЕТЕКТИВ")
    DETECTIVE,

    @JsonProperty("ИСТОРИЧЕСКИЙ_РОМАН")
    HISTORICAL,

    @JsonProperty("ПОЭЗИЯ")
    POETRY
}
