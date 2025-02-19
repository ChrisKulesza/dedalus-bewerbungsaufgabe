package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CurrencyDenomination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/currency")
public class CurrencyController {

    @GetMapping("calculateDenomination")
    public ResponseEntity<CurrencyDenominationDto> calculateCurrencyDenomination(
            @RequestParam(value = "value") double value,
            @RequestParam(value = "newValue") Optional<Double> newValue
    ) {
        if (newValue.isEmpty() || newValue.get() <= 0) {
            var result = new CurrencyDenomination(value);
            var dto = CurrencyDenominationDto.fromCurrencyDenomination(result);

            return ResponseEntity.ok().body(dto);
        }

        var result = new CurrencyDenomination(value, newValue.get());
        var dto = CurrencyDenominationDto.fromCurrencyDenomination(result);

        return ResponseEntity.ok().body(dto);
    }
}
