package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CalculationType;
import org.currency_denomination.feature.domain.Calculator;
import org.currency_denomination.feature.domain.Currency;
import org.currency_denomination.feature.domain.DenominationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// ToDo: Mehr Testfälle abdecken und Stückelungen prüfen
public class DenominationResultDtoTest {
    @ParameterizedTest
    @CsvSource({
            "200, 100, 2",
    })
    public void should_return_denominationResult_as_difference_for_euroCent_currency(
            double valueForDenomination,
            double valueForDifference,
            int expectedDenominationsSize
    ) {
        var result = new DenominationResult(
                valueForDenomination,
                valueForDifference,
                Calculator::forEuro,
                Currency.EURO
        );
        var dto = DenominationResultDto.fromDenominationResult(result);

        Assertions.assertAll(
                "Result map contains the difference calculation between old and new Euro value",
                () -> Assertions.assertEquals(expectedDenominationsSize, dto.getDenominations().size()),

                () -> Assertions.assertSame(CalculationType.DIFFERENCE.getValue(), dto.getCalculationType())
        );
    }
}
