package org.currency_denomination.feature.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.TreeMap;

public class Calculator {
    public static TreeMap<Double, Integer> forEuro(double value) {
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

        return new TreeMap<>(results);
    }

    public static int euroToCent(double value) {
        var centPerEuro = "100";
        var factor = new BigDecimal(centPerEuro);
        return Math.round(factor.multiply(new BigDecimal(value)).floatValue());
    }
}
