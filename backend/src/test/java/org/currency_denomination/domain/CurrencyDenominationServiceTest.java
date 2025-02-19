package org.currency_denomination.domain;

import org.currency_denomination.feature.domain.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyDenominationServiceTest {
    private final CurrencyService service = new CurrencyService();

    @ParameterizedTest
    @CsvSource({
            "10, 10, 1",
            "20, 20, 1",
            "50, 50, 1",
            "100, 100, 1",
            "200, 200, 1"
    })
    public void shouldReturnOnePartDenomination_ForWholeEuroValue(
            double value,
            double firstDenomination,
            int expectedFirstCount
    ) {
        var result = service.calculateDenominationFor(value);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(1, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstDenomination)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstDenomination))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "250, 200, 1, 50, 1",
            "300, 200, 1, 100, 1"
    })
    public void shouldReturnTwoPartDenomination_ForWholeEuroValue(
            double value,
            double firstDenomination,
            int expectedFirstCount,
            double secondDenomination,
            int expectedSecondCount
    ) {
        var result = service.calculateDenominationFor(value);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(2, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstDenomination)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstDenomination)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondDenomination)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondDenomination))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "260, 200, 1, 50, 1, 10, 1",
            "310, 200, 1, 100, 1, 10, 1"
    })
    public void shouldReturnThreePartDenomination_ForWholeEuroValue(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount
    ) {
        var result = service.calculateDenominationFor(value);
        Assertions.assertTrue(result.containsKey(firstPart));
        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(3, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.get(thirdPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "280, 200, 1, 50, 1, 20, 1, 10, 1",
            "330, 200, 1, 100, 1, 20, 1, 10, 1"
    })
    public void shouldReturnFourPartDenomination_ForWholeEuroValue(
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
        var result = service.calculateDenominationFor(value);
        Assertions.assertTrue(result.containsKey(firstPart));
        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(4, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.get(thirdPart)),

                // Fourth denomination part
                () -> Assertions.assertTrue(result.containsKey(fourthPart)),
                () -> Assertions.assertEquals(expectedFourthCount, (int) result.get(fourthPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "20.50, 20, 1, .5, 1",
            "20.05, 20, 1, .05, 1",
            "20.02, 20, 1, .02, 1",
            "20.01, 20, 1, .01, 1",
    })
    public void shouldReturnTwoPartDenomination_ForValueCents(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount
    ) {
        var result = service.calculateDenominationFor(value);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(2, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "20.60, 20, 1, .5, 1, .1, 1",
            "20.06, 20, 1, .05, 1, .01, 1",
            "20.03, 20, 1, .02, 1, .01, 1",
    })
    public void shouldReturnThreePartDenomination_ForValueCents(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount
    ) {
        var result = service.calculateDenominationFor(value);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(3, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.get(thirdPart))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "20.99, 20, 1, .5, 1, .2, 2, .05, 1, .02, 2",
    })
    public void shouldReturnFivePartDenomination_ForValueCents(
            double value,
            double firstPart,
            int expectedFirstCount,
            double secondPart,
            int expectedSecondCount,
            double thirdPart,
            int expectedThirdCount,
            double fourthPart,
            int expectedFourthCount,
            double fifthPart,
            int expectedFifthCount
    ) {
        var result = service.calculateDenominationFor(value);

        Assertions.assertAll("Result map contains all expected denomination keys and values",
                () -> Assertions.assertEquals(5, result.entrySet().stream().filter(e -> e.getValue() > 0).count()),

                // First denomination part
                () -> Assertions.assertTrue(result.containsKey(firstPart)),
                () -> Assertions.assertEquals(expectedFirstCount, (int) result.get(firstPart)),

                // Second denomination part
                () -> Assertions.assertTrue(result.containsKey(secondPart)),
                () -> Assertions.assertEquals(expectedSecondCount, (int) result.get(secondPart)),

                // Third denomination part
                () -> Assertions.assertTrue(result.containsKey(thirdPart)),
                () -> Assertions.assertEquals(expectedThirdCount, (int) result.get(thirdPart)),

                // Fourth denomination part
                () -> Assertions.assertTrue(result.containsKey(fourthPart)),
                () -> Assertions.assertEquals(expectedFourthCount, (int) result.get(fourthPart)),

                // Fifth denomination part
                () -> Assertions.assertTrue(result.containsKey(fifthPart)),
                () -> Assertions.assertEquals(expectedFifthCount, (int) result.get(fifthPart))
        );
    }
}
