package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CurrencyDenomination;

import java.util.Collections;
import java.util.Objects;
import java.util.TreeMap;

public class CurrencyDenominationDto {
    private final double value;
    private final double valueNew;
    private final TreeMap<Double, String> denominations;

    private CurrencyDenominationDto(
            double value,
            TreeMap<Double, Integer> denominationsForValue,
            double valueNew,
            TreeMap<Double, Integer> denominationsForNewValue
    ) {
        this.value = value;
        this.valueNew = valueNew;
        this.denominations = calculateDifferenceBetweenNewAndOldValue(denominationsForValue, denominationsForNewValue);
    }

    private CurrencyDenominationDto(
            double value,
            TreeMap<Double, Integer> denominationsForValue
    ) {
        this.value = value;
        this.valueNew = 0;
        this.denominations = convertToStringValueMap(denominationsForValue);
    }

    private static TreeMap<Double, String> convertToStringValueMap(TreeMap<Double, Integer> map) {
        var reversedMap = new TreeMap<Double, String>(Collections.reverseOrder());
        map.forEach((key, v) -> reversedMap.put(key, v.toString()));
        return reversedMap;
    }

    public static CurrencyDenominationDto fromCurrencyDenomination(CurrencyDenomination result) {
        if (result.getValueNew() == 0 && result.getDenominationsForNewValue().isEmpty()) {
            return new CurrencyDenominationDto(result.getValue(), result.getDenominationsForValue());
        }

        return new CurrencyDenominationDto(
                result.getValue(),
                result.getDenominationsForValue(),
                result.getValueNew(),
                result.getDenominationsForNewValue()
        );
    }

    private static TreeMap<Double, String> calculateDifferenceBetweenNewAndOldValue(
            TreeMap<Double, Integer> denominationsForValue,
            TreeMap<Double, Integer> denominationsForNewValue
    ) {
        var map = new TreeMap<Double, String>(Collections.reverseOrder());
        denominationsForValue.forEach((key, value) -> {
            var valueNew = denominationsForNewValue.get(key);

            if (!Objects.equals(valueNew, value) || valueNew > 0 && value > 0) {
                map.put(key, calculateDifference(valueNew - value));
            }
        });

        return map;
    }

    private static String calculateDifference(int value) {
        return value <= 0 ? String.format("%,d", value) : String.format("+%,d", value);
    }

    public double getValue() {
        return value;
    }

    public double getValueNew() {
        return valueNew;
    }

    public TreeMap<Double, String> getDenominations() {
        return denominations;
    }
}
