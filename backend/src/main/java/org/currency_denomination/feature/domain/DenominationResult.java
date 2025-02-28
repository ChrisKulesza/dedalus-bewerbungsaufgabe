package org.currency_denomination.feature.domain;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;

public class DenominationResult {
    private double valueForDenomination;
    private Optional<Double> valueForDifference;
    private Map<Double, Integer> denominations;
    private Map<Double, Integer> denominationsForDifference;
    private final CalculationType calculationType;
    private final Currency currency;

    public DenominationResult(
            double valueForDenomination,
            Function<Double, Map<Double, Integer>> calculator,
            Currency currency
    ) {
        this.valueForDenomination = valueForDenomination;
        this.valueForDifference = Optional.empty();
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
        this.valueForDifference = Optional.of(valueForDifference);
        this.denominations = calculator.apply(valueForDenomination);
        this.denominationsForDifference = calculator.apply(valueForDifference);
        calculationType = CalculationType.DIFFERENCE;
        this.currency = currency;
    }

    public double getValueForDenomination() {
        return valueForDenomination;
    }

    public Optional<Double> getValueForDifference() {
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
