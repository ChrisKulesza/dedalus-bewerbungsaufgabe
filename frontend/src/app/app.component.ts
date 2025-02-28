import { Component } from '@angular/core';
import { CalculationFormComponent } from './features/currencyDenomination/calculation-form/calculation-form.component';
import { DenominationResultTableComponent } from './features/currencyDenomination/denomination-result-table/denomination-result-table.component';

@Component({
  selector: 'app-root',
  imports: [CalculationFormComponent, DenominationResultTableComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
