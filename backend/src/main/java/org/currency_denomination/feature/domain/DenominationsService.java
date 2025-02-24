package org.currency_denomination.feature.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DenominationsService {
    public DenominationResult calculateDenominationsFor(
            double valueForDenomination,
            Optional<Double> valueForDifference
    ) {
        return (valueForDifference.isEmpty())
               ? new DenominationResult(valueForDenomination, Calculator::forEuro, Currency.EURO)
               : new DenominationResult(
                       valueForDenomination,
                       valueForDifference.get(),
                       Calculator::forEuro,
                       Currency.EURO
               );
    }
}
