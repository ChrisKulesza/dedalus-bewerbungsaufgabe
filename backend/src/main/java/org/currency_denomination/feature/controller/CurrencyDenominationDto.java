package org.currency_denomination.feature.controller;

import java.util.TreeMap;

public record CurrencyDenominationDto(double value, TreeMap<Double, Integer> denominationValues) {
}
