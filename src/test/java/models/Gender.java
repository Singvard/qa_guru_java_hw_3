package models;

public enum Gender {
    MALE,
    FEMALE,
    OTHER;


    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
