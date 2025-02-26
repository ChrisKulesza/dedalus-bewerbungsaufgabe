import { Component, OnInit } from '@angular/core';
import { CalculateDenominationService } from '../service/calculate-denomination.service';
import { NgIf } from '@angular/common';
import { TableModule } from 'primeng/table';
import { CalculationType } from '../CalculationType';
import { CardModule } from 'primeng/card';
import { DenominationResponse } from '../DenominationResponse';

@Component({
  selector: 'app-denomination-result-table',
  imports: [NgIf, TableModule, CardModule],
  templateUrl: './denomination-result-table.component.html',
  styleUrl: './denomination-result-table.component.css',
})
export class DenominationResultTableComponent implements OnInit {
  firstColumnHeaderName = 'Schein/Münze';
  response: DenominationResponse | null = null;
  error: string | undefined;

  constructor(private readonly _calculateDenominationService: CalculateDenominationService) {}

  ngOnInit() {
    this._calculateDenominationService.getDenominationResult().subscribe((value) => {
      this.response = value;
    });
    this._calculateDenominationService.getError().subscribe((value) => {
      this.error = value;
    });
  }

  getSecondColumnHeaderName(calculationType: CalculationType | undefined) {
    switch (calculationType) {
      case CalculationType.DENOMINATION:
        return 'Anzahl';
      case CalculationType.DIFFERENCE:
        return 'Differenz';
      default:
        return 'Ungültiger Typ';
    }
  }
}
