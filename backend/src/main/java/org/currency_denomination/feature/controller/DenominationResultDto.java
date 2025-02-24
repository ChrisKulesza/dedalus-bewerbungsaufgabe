package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CalculationType;
import org.currency_denomination.feature.domain.Currency;
import org.currency_denomination.feature.domain.DenominationResult;

import java.util.*;

public class DenominationResultDto {
    private final double valueForDenomination;
    private final Optional<Double> valueForDifference;
    private final List<Value> denominations;
    private final String calculationType;
    private final String currency;

    private DenominationResultDto(
            double valueForDenomination,
            Map<Double, Integer> denominations,
            double valueForDifference,
            Map<Double, Integer> denominationsDifference,
            CalculationType calculationType,
            Currency currency
    ) {
        this.valueForDenomination = valueForDenomination;
        this.valueForDifference = Optional.of(valueForDifference);
        this.denominations = calculateDenominationDifferenceBetween(denominations, denominationsDifference);
        this.calculationType = calculationType.getValue();
        this.currency = currency.getSymbol();
    }

    private DenominationResultDto(
            double valueForDenomination,
            Map<Double, Integer> denominationsForValue,
            CalculationType calculationType,
            Currency currency
    ) {
        this.valueForDenomination = valueForDenomination;
        this.valueForDifference = Optional.empty();
        this.denominations = toReversedOrderList(denominationsForValue);
        this.calculationType = calculationType.getValue();
        this.currency = currency.getSymbol();
    }

    private static List<Value> toReversedOrderList(Map<Double, Integer> map) {
        var reversedMap = new TreeMap<Double, String>(Collections.reverseOrder());
        map.forEach((key, v) -> reversedMap.put(key, v.toString()));
        return denominationsMapToList(reversedMap);
    }

    public static DenominationResultDto fromDenominationResult(DenominationResult result) {
        if (result.getValueForDifference().isEmpty()) {
            return new DenominationResultDto(
                    result.getValueForDenomination(),
                    result.getDenominations(),
                    result.getCalculationType(),
                    result.getCurrency()
            );
        }

        return new DenominationResultDto(
                result.getValueForDenomination(),
                result.getDenominations(),
                result.getValueForDifference().get(),
                result.getDenominationsDifference(),
                result.getCalculationType(),
                result.getCurrency()
        );
    }

    private static List<Value> calculateDenominationDifferenceBetween(
            Map<Double, Integer> denominations,
            Map<Double, Integer> denominationsDifference
    ) {
        var map = new TreeMap<Double, String>(Collections.reverseOrder());
        denominations.forEach((k, v) -> {
            var valueForDifference = denominationsDifference.get(k);

            if (!Objects.equals(valueForDifference, v) || valueForDifference > 0 && v > 0) {
                map.put(k, calculateDifference(valueForDifference - v));
            }
        });

        return denominationsMapToList(map);
    }

    private static List<Value> denominationsMapToList(TreeMap<Double, String> map) {
        var mappedValues = new ArrayList<Value>();
        map.forEach((k, v) -> mappedValues.add(new Value(k, v)));
        return Collections.unmodifiableList(mappedValues);
    }

    private static String calculateDifference(int value) {
        return value <= 0 ? String.format("%,d", value) : String.format("+%,d", value);
    }

    public double getValueForDenomination() {
        return valueForDenomination;
    }

    public Optional<Double> getValueForDifference() {
        return valueForDifference;
    }

    /**
     * @return An unmodifiable list of {@code Value} objects containing the denominations.
     */
    public List<Value> getDenominations() {
        return Collections.unmodifiableList(denominations);
    }

    public String getCalculationType() {
        return calculationType;
    }

    public String getCurrency() {
        return currency;
    }

    public record Value(double value, String count) {
    }
}
