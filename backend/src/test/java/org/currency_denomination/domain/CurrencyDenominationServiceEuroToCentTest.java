package org.currency_denomination.domain;

import org.currency_denomination.feature.domain.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyDenominationServiceEuroToCentTest {
    @ParameterizedTest
    @CsvSource({
            "20, 2000",
            "20.01, 2001",
            "20.99, 2099",
            "21.01, 2101",
    })
    public void shouldReturnTheCorrectEuroValueInCent(double value, int expectedValueInCent) {
        var valueInCent = CurrencyService.EuroToCent(value);
        Assertions.assertEquals(expectedValueInCent, valueInCent);
    }
}
