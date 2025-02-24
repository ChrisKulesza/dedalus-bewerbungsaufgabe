package org.currency_denomination.feature.domain;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public class DenominationResult {
    double valueForDenomination;
    double valueForDifference;
    Map<Double, Integer> denominations;
    Map<Double, Integer> denominationsForDifference;
    private final CalculationType calculationType;
    private final Currency currency;

    public DenominationResult(
            double valueForDenomination,
            Function<Double, Map<Double, Integer>> calculator,
            Currency currency
    ) {
        this.valueForDenomination = valueForDenomination;
        this.valueForDifference = 0;
        this.denominations = calculator.apply(valueForDenomination);
        this.denominationsForDifference = new TreeMap<>();
        calculationType = CalculationType.DENOMINATION;
        this.currency = currency;
    }

    public DenominationResult(
            double valueForDenomination,
            double valueForDifference,
            Function<Double, Map<Double, Integer>> calculator,
            Currency currency
    ) {
        this.valueForDenomination = valueForDenomination;
        this.valueForDifference = valueForDifference;
        this.denominations = calculator.apply(valueForDenomination);
        this.denominationsForDifference = calculator.apply(valueForDifference);
        calculationType = CalculationType.DIFFERENCE;
        this.currency = currency;
    }

    public double getValueForDenomination() {
        return valueForDenomination;
    }

    public double getValueForDifference() {
        return valueForDifference;
    }

    public Map<Double, Integer> getDenominations() {
        return denominations;
    }

    public Map<Double, Integer> getDenominationsDifference() {
        return denominationsForDifference;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public Currency getCurrency() {
        return currency;
    }
}
