package org.currency_denomination.feature.domain;

public enum CurrencyDenominationValue {
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

    CurrencyDenominationValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
