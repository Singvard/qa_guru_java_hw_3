package testdata;

import models.*;

import java.time.LocalDate;
import java.util.List;

public class StudentBuilder {
    private String firstName = "Имя";
    private String lastName = "Фамилия";
    private String email = "test@test.test";
    private Gender gender = Gender.MALE;
    private String phoneNumber = "9876543210";
    private LocalDate birthdate = LocalDate.of(1990, 8, 12);
    private List<String> addedSubjects = List.of("Maths", "English", "Computer Science", "Arts");
    private List<String> deletedSubjects = List.of("Arts");
    private List<Hobby> hobbies = List.of(Hobby.SPORTS, Hobby.MUSIC);
    private String picturePath = "avatar.png";
    private String address =
                    """
                    123456,
                    г. Москва,
                    ул. Пушкина,
                    д. 5,
                    кв. 109
                    """;
    private State state = State.RAJASTHAN;
    private City city = City.JAISELMER;

    /**
     * Устанавливает имя студента.
     *
     * @param firstName имя студента
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Устанавливает фамилию студента.
     *
     * @param lastName фамилия студента
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Устанавливает адрес электронной почты студента.
     *
     * @param email адрес электронной почты
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Устанавливает пол студента.
     *
     * @param gender пол студента
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Устанавливает номер телефона студента.
     *
     * @param phoneNumber номер телефона
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Устанавливает дату рождения студента.
     *
     * @param birthdate дата рождения
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    /**
     * Устанавливает список предметов, добавленных студенту.
     *
     * @param addedSubjects список названий предметов
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withAddedSubjects(List<String> addedSubjects) {
        this.addedSubjects = addedSubjects;
        return this;
    }

    /**
     * Устанавливает список предметов, удалённых у студента.
     *
     * @param deletedSubjects список названий предметов
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withDeletedSubjects(List<String> deletedSubjects) {
        this.deletedSubjects = deletedSubjects;
        return this;
    }

    /**
     * Устанавливает список хобби студента.
     *
     * @param hobbies список хобби
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    /**
     * Создаёт и возвращает новый экземпляр StudentBuilder с настройками по умолчанию.
     *
     * @return новый экземпляр StudentBuilder
     */
    public static StudentBuilder defaultStudent() {
        return new StudentBuilder();
    }

    /**
     * Устанавливает путь к изображению (аватару) студента.
     *
     * @param picturePath путь к файлу изображения
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    /**
     * Устанавливает адрес студента.
     *
     * @param address адрес студента (многострочный)
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Устанавливает штат/область проживания студента.
     *
     * @param state штат/область
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withState(State state) {
        this.state = state;
        return this;
    }

    /**
     * Устанавливает город проживания студента.
     *
     * @param city город
     * @return текущий экземпляр StudentBuilder для цепочки вызовов
     */
    public StudentBuilder withCity(City city) {
        this.city = city;
        return this;
    }

    /**
     * Создаёт и возвращает объект {@link Student} с текущими настройками строителя.
     *
     * @return новый объект Student
     */
    public Student build() {
        return new Student(
                firstName,
                lastName,
                email,
                gender,
                phoneNumber,
                birthdate,
                addedSubjects,
                deletedSubjects,
                hobbies,
                picturePath,
                address,
                state,
                city
        );
    }
}
