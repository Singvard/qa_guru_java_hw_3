package models;

public enum Hobby {
    SPORTS,
    READING,
    MUSIC;

    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
