package models;

import utils.DateFormatter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record Student(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        String phoneNumber,
        LocalDate birthdate,
        List<String> addedSubjects,
        List<String> deletedSubjects,
        List<Hobby> hobbies,
        String picturePath,
        String address,
        State state,
        City city
) {

    public String studentFullName() {
        return firstName + " " + lastName;
    }

    public String studentDateOfBirth() {
        return DateFormatter.formateDateToString(birthdate);
    }

    public String studentSubjects() {
        return addedSubjects.stream()
                .filter(subject -> !deletedSubjects.contains(subject))
                .collect(Collectors.joining(", "));
    }

    public String studentHobbies() {
        return hobbies.stream()
                .map(Hobby::toString)
                .collect(Collectors.joining(", "));
    }

    public String studentFlatAddress() {
        return address.lines()
                .map(String::strip)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String studentFullLocation() {
        return state + " " + city;
    }
}
