package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.Calculator;
import org.currency_denomination.feature.domain.DenominationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/denomination")
public class CurrencyController {

    @GetMapping("calculateForEuro")
    public ResponseEntity<DenominationResultDto> calculateForEuro(
            @RequestParam(value = "value") double value,
            @RequestParam(value = "valueNew") Optional<Double> valueNew
    ) {
        if (valueNew.isEmpty() || valueNew.get() <= 0) {
            var result = new DenominationResult(value, Calculator::forEuro);
            var dto = DenominationResultDto.fromDenominationResult(result);

            return ResponseEntity.ok().body(dto);
        }
        
        var result = new DenominationResult(value, valueNew.get(), Calculator::forEuro);
        var dto = DenominationResultDto.fromDenominationResult(result);

        return ResponseEntity.ok().body(dto);
    }
}
