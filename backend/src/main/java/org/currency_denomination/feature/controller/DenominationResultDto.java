package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CalculationType;
import org.currency_denomination.feature.domain.DenominationResult;

import java.util.Collections;
import java.util.Objects;
import java.util.TreeMap;

public class DenominationResultDto {
    private final double value;
    private final double valueNew;
    private final TreeMap<Double, String> denominations;
    private final String calculationType;

    private DenominationResultDto(
            double value,
            TreeMap<Double, Integer> denominationsForValue,
            double valueNew,
            TreeMap<Double, Integer> denominationsForValueNew,
            CalculationType calculationType
    ) {
        this.value = value;
        this.valueNew = valueNew;
        this.denominations = calculateDifferenceBetweenNewAndOldValue(denominationsForValue, denominationsForValueNew);
        this.calculationType = calculationType.getValue();
    }

    private DenominationResultDto(
            double value,
            TreeMap<Double, Integer> denominationsForValue,
            CalculationType calculationType
    ) {
        this.value = value;
        this.valueNew = 0;
        this.denominations = convertToStringValueMap(denominationsForValue);
        this.calculationType = calculationType.getValue();
    }

    private static TreeMap<Double, String> convertToStringValueMap(TreeMap<Double, Integer> map) {
        var reversedMap = new TreeMap<Double, String>(Collections.reverseOrder());
        map.forEach((key, v) -> reversedMap.put(key, v.toString()));
        return reversedMap;
    }

    public static DenominationResultDto fromDenominationResult(DenominationResult result) {
        if (result.getValueNew() == 0 && result.getDenominationsForValueNew().isEmpty()) {
            return new DenominationResultDto(
                    result.getValue(),
                    result.getDenominationsForValue(),
                    result.getCalculationType()
            );
        }

        return new DenominationResultDto(
                result.getValue(),
                result.getDenominationsForValue(),
                result.getValueNew(),
                result.getDenominationsForValueNew(),
                result.getCalculationType()
        );
    }

    private static TreeMap<Double, String> calculateDifferenceBetweenNewAndOldValue(
            TreeMap<Double, Integer> denominationsForValue,
            TreeMap<Double, Integer> denominationsForValueNew
    ) {
        var map = new TreeMap<Double, String>(Collections.reverseOrder());
        denominationsForValue.forEach((key, value) -> {
            var valueNew = denominationsForValueNew.get(key);

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

    public String getCalculationType() {
        return calculationType;
    }
}
