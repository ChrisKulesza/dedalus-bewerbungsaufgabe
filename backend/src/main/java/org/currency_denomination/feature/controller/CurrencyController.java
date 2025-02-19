package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/currency")
public class CurrencyController {
    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CurrencyDenominationDto> calculateCurrencyDenomination(@RequestParam(value = "value") double value) {
        var denominationValues = service.calculateDenominationFor(value);
        var dto = new CurrencyDenominationDto(value, denominationValues);

        return ResponseEntity.ok().body(dto);
    }
}
