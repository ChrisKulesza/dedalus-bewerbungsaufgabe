package org.currency_denomination.feature.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DenominationResultTest {

    @ParameterizedTest
    @CsvSource({
            "10, 10, 1",
            "20, 20, 1",
            "50, 50, 1",
            "100, 100, 1",
            "200, 200, 1"
    })
    public void should_return_denominationResult_with_one_denomination_for_euro_currency(
            double value,
            double firstDenomination,
            int expectedFirstCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(1, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstDenomination)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstDenomination))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "250, 200, 1, 50, 1",
            "300, 200, 1, 100, 1"
    })
    public void should_return_denominationResult_with_two_denominations_for_euro_currency(
            double value,
            double firstDenomination,
            int expectedFirstCount,
            double secondDenomination,
            int expectedSecondCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(2, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstDenomination)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstDenomination)),

                // Second denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(secondDenomination)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.getDenominationsForValue().get(secondDenomination))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "260, 200, 1, 50, 1, 10, 1",
            "310, 200, 1, 100, 1, 10, 1"
    })
    public void should_return_denominationResult_with_three_denominations_for_euro_currency(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);
        
        Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart));
        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(3, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.getDenominationsForValue().get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.getDenominationsForValue().get(thirdPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "280, 200, 1, 50, 1, 20, 1, 10, 1",
            "330, 200, 1, 100, 1, 20, 1, 10, 1"
    })
    public void should_return_denominationResult_with_four_denominations_for_euro_currency(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount,
            double fourthPart,
            int expectedFourthCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);
        
        Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart));
        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(4, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.getDenominationsForValue().get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.getDenominationsForValue().get(thirdPart)),

                // Fourth denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(fourthPart)),
                () -> Assertions.assertEquals(expectedFourthCount, (int) result.getDenominationsForValue().get(fourthPart))
        );
    }
    
    @ParameterizedTest
    @CsvSource({
            "20.50, 20, 1, .5, 1",
            "20.05, 20, 1, .05, 1",
            "20.02, 20, 1, .02, 1",
            "20.01, 20, 1, .01, 1",
    })
    public void should_return_denominationResult_with_two_denominations_for_euroCent_currency(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(2, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.getDenominationsForValue().get(secondPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "20.60, 20, 1, .5, 1, .1, 1",
            "20.06, 20, 1, .05, 1, .01, 1",
            "20.03, 20, 1, .02, 1, .01, 1",
    })
    public void should_return_denominationResult_with_three_denominations_for_euroCent_currency(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount
    ) {
        var result = new DenominationResult(value, Calculator::forEuro);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(3, result.getDenominationsForValue().entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.getDenominationsForValue().get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.getDenominationsForValue().get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.getDenominationsForValue().containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.getDenominationsForValue().get(thirdPart))
        );
    }
}
