import { Component, OnDestroy } from '@angular/core';
import { CalculateDenominationService } from '../service/calculate-denomination.service';
import { AsyncPipe, DecimalPipe, NgTemplateOutlet } from '@angular/common';
import { TableModule } from 'primeng/table';
import { CalculationType } from '../CalculationType';
import { CardModule } from 'primeng/card';
import { DenominationResponse } from '../DenominationResponse';
import { Observable, Subject, takeUntil } from 'rxjs';
import { CurrencyValue } from '../CurrencyValue';

@Component({
  selector: 'app-denomination-result-table',
  imports: [TableModule, CardModule, AsyncPipe, DecimalPipe, NgTemplateOutlet],
  templateUrl: './denomination-result-table.component.html',
  styleUrl: './denomination-result-table.component.css',
})
export class DenominationResultTableComponent implements OnDestroy {
  firstColumnHeaderName = 'Schein/Münze';
  response$: Observable<DenominationResponse | null>;
  error$: Observable<string | undefined>;

  destroy$: Subject<void> = new Subject();

  constructor(private readonly _calculateDenominationService: CalculateDenominationService) {
    this.response$ = this._calculateDenominationService.getDenominationResult().pipe(takeUntil(this.destroy$));
    this.error$ = this._calculateDenominationService.getError().pipe(takeUntil(this.destroy$));
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

  rowTrackBy(index: number, row: CurrencyValue): string {
    return `${row.value}`;
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
