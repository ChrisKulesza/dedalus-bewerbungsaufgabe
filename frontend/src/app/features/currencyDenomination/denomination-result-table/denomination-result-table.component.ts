import { Component } from '@angular/core';
import { CalculateDenominationService } from '../service/calculate-denomination.service';
import { AsyncPipe, DecimalPipe, NgTemplateOutlet } from '@angular/common';
import { TableModule } from 'primeng/table';
import { CalculationType } from '../CalculationType';
import { CardModule } from 'primeng/card';
import { DenominationResponse } from '../DenominationResponse';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-denomination-result-table',
  imports: [TableModule, CardModule, AsyncPipe, DecimalPipe, NgTemplateOutlet],
  templateUrl: './denomination-result-table.component.html',
  styleUrl: './denomination-result-table.component.css',
})
export class DenominationResultTableComponent {
  firstColumnHeaderName = 'Schein/Münze';
  response$: Observable<DenominationResponse | null>;
  error$: Observable<string | undefined>;

  constructor(private readonly _calculateDenominationService: CalculateDenominationService) {
    this.response$ = this._calculateDenominationService.getDenominationResult();
    this.error$ = this._calculateDenominationService.getError();
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
