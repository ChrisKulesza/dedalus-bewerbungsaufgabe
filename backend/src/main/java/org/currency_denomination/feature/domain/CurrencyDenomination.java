package org.currency_denomination.feature.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.TreeMap;

public class CurrencyDenomination {
    double value;
    double valueNew;
    TreeMap<Double, Integer> denominationsForValue;
    TreeMap<Double, Integer> denominationsForNewValue;

    public CurrencyDenomination(double value) {
        this.value = value;
        this.denominationsForValue = calculateDenominationFor(value);
        this.denominationsForNewValue = new TreeMap<>();
    }

    public CurrencyDenomination(double value, double valueNew) {
        this.value = value;
        this.valueNew = valueNew;
        this.denominationsForValue = calculateDenominationFor(value);
        this.denominationsForNewValue = calculateDenominationFor(valueNew);
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

    public TreeMap<Double, Integer> getDenominationsForNewValue() {
        return denominationsForNewValue;
    }

    private TreeMap<Double, Integer> calculateDenominationFor(double value) {
        var currentValueInCent = EuroToCent(value);

        var results = new TreeMap<Double, Integer>(Collections.reverseOrder());

        for (var currencyValue : EuroDenominationValue.values()) {
            var valueToCheckAgainst = EuroToCent(currencyValue.getValue());

            if (currentValueInCent >= valueToCheckAgainst) {
                var count = currentValueInCent / valueToCheckAgainst;
                results.put(currencyValue.getValue(), count);
                currentValueInCent -= (count * valueToCheckAgainst);
            } else {
                results.put(currencyValue.getValue(), 0);
            }
        }

        return new TreeMap<>(results);
    }

    /**
     * Converts a Euro value to cents. This method prevents rounding errors in the range of euro cent amounts.
     *
     * @param value the Euro value to convert to cents
     * @return the equivalent value in cents as an integer
     */
    public static int EuroToCent(double value) {
        var centPerEuro = "100";
        var factor = new BigDecimal(centPerEuro);
        return Math.round(factor.multiply(new BigDecimal(value)).floatValue());
    }
}
