package org.currency_denomination.feature.domain;

/**
 * Enum representing the different currency denominations for the Euro.
 */
public enum EuroDenominationValue {
    TwoHundredEuros(200),
    OneHundredEuros(100),
    FiftyEuros(50),
    TwentyEuros(20),
    TenEuros(10),
    FiveEuros(5),
    TwoEuros(2),
    OneEuro(1),
    FiftyCents(.5),
    TwentyCents(.2),
    TenCents(.1),
    FifeCents(.05),
    TwoCents(.02),
    OneCent(.01);

    private final double value;

    EuroDenominationValue(double value) {
        this.value = value;
    }

    /**
     * Returns the denomination value.
     *
     * @return the numerical value of the Euro denomination as a {@code double}
     */
    public double getValue() {
        return value;
    }
}
