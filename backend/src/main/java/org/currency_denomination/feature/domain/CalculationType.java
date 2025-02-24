package org.currency_denomination.feature.domain;

/**
 * Enum representing the calculation type used for determining the {@link DenominationResult}.
 * This can either be a direct denomination calculation or a difference calculation
 * between the new and old values.
 */
public enum CalculationType {
    DENOMINATION("Denomination"),
    DIFFERENCE("Difference");

    private final String value;

    CalculationType(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the calculation type.
     *
     * @return the value of the calculation type as a {@link String}
     */
    public String getValue() {
        return value;
    }
}
