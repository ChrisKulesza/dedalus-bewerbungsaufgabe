package org.currency_denomination.feature.controller;

import org.currency_denomination.feature.domain.DenominationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/denomination")
public class DenominationsController {

    private final DenominationsService service;

    public DenominationsController(DenominationsService service) {
        this.service = service;
    }

    @GetMapping("calculateForEuro")
    public ResponseEntity<DenominationResultDto> calculateForEuro(
            @RequestParam(value = "valueForDenomination") double valueForDenomination,
            @RequestParam(value = "valueForDifference") Optional<Double> valueForDifference
    ) {
        var result = service.calculateDenominationsFor(valueForDenomination, valueForDifference);
        var dto = DenominationResultDto.fromDenominationResult(result);

        return ResponseEntity.ok().body(dto);
    }
}
