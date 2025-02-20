package org.currency_denomination.feature.domain;

import java.util.TreeMap;
import java.util.function.Function;

public class DenominationResult {
    double value;
    double valueNew;
    TreeMap<Double, Integer> denominationsForValue;
    TreeMap<Double, Integer> denominationsForValueNew;
    private final CalculationType calculationType;

    public DenominationResult(double value, Function<Double, TreeMap<Double, Integer>> calculator) {
        this.value = value;
        this.valueNew = 0;
        this.denominationsForValue = calculator.apply(value);
        this.denominationsForValueNew = new TreeMap<>();
        calculationType = CalculationType.DENOMINATION;
    }

    public DenominationResult(double value, double valueNew, Function<Double, TreeMap<Double, Integer>> calculator) {
        this.value = value;
        this.valueNew = valueNew;
        this.denominationsForValue = calculator.apply(value);
        this.denominationsForValueNew = calculator.apply(valueNew);
        calculationType = CalculationType.DIFFERENCE;
    }

    public double getValue() {
        return value;
    }

    public double getValueNew() {
        return valueNew;
    }

    public TreeMap<Double, Integer> getDenominationsForValue() {
        return denominationsForValue;
    }

    public TreeMap<Double, Integer> getDenominationsForValueNew() {
        return denominationsForValueNew;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }
}
