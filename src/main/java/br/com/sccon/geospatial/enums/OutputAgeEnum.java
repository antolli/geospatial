package br.com.sccon.geospatial.enums;

public enum OutputAgeEnum {
    DAYS("days"),
    MONTHS("months"),

    YEARS("years");

    private final String value;

    OutputAgeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Boolean fromValue(String value) {
        for (OutputAgeEnum format : OutputAgeEnum.values()) {
            if (format.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
