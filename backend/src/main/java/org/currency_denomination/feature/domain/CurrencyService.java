package org.currency_denomination.feature.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.TreeMap;

@Service
public class CurrencyService {
    /**
     * @param value the Euro value to calculate denominations for
     * @return a TreeMap containing the currency denominations as keys and the count of each denomination as values
     */
    public TreeMap<Double, Integer> calculateDenominationFor(double value) {
        var currentValueInCent = EuroToCent(value);

        var results = new TreeMap<Double, Integer>(Collections.reverseOrder());

        for (var currencyValue : CurrencyDenominationValue.values()) {
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
