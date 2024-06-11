package br.com.sccon.geospatial.enums;

public enum OutputSalaryEnum {
    FULL("full"),
    MIN("min");

    private final String value;

    OutputSalaryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Boolean fromValue(String value) {
        for (OutputSalaryEnum format : OutputSalaryEnum.values()) {
            if (format.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
