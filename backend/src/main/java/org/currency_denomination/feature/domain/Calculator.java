package org.currency_denomination.feature.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Calculator {
    /**
     * Calculates the denominations for a given value in euros.
     * The method converts the value to cents and determines the number of each denomination needed.
     *
     * @param value the value in euros to be converted into denominations
     * @return a {@link Map} where the keys are the denomination values and the values are the counts of each denomination
     */
    public static Map<Double, Integer> forEuro(double value) {
        var currentValueInCent = euroToCent(value);
        
        var results = new TreeMap<Double, Integer>(Collections.reverseOrder());
        
        for (var currencyValue : EuroDenominationValue.values()) {
            var valueToCheckAgainst = euroToCent(currencyValue.getValue());

            if (currentValueInCent >= valueToCheckAgainst) {
                var count = currentValueInCent / valueToCheckAgainst;
                results.put(currencyValue.getValue(), count);
                currentValueInCent -= (count * valueToCheckAgainst);
            } else {
                results.put(currencyValue.getValue(), 0);
            }
        }

        return Collections.unmodifiableMap(results);
    }

    /**
     * Converts a value in euros to cents to avoid rounding errors in double calculations.
     * This method uses BigDecimal for precise arithmetic operations.
     *
     * @param value the value in euros to be converted to cents
     * @return the equivalent value in cents as an {@code integer}
     */
    private static int euroToCent(double value) {
        var centPerEuro = "100";
        var factor = new BigDecimal(centPerEuro);
        return Math.round(factor.multiply(new BigDecimal(value)).floatValue());
    }
}
