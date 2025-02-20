package org.currency_denomination.feature.domain;

public enum CalculationType {
    DENOMINATION("denomination"),
    DIFFERENCE("difference");

    private final String value;

    CalculationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
