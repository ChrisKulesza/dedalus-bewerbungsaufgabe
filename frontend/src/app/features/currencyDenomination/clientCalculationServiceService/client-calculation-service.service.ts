import { Injectable } from '@angular/core';
import { DenominationResult } from './DenominationResult';
import { Calculator } from './Calculator';
import { CurrencySymbol } from '../CurrencySymbol';
import { DenominationFormType } from '../calculation-form/DenominationFormType';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClientCalculationServiceService {
  calculateDenominationsFor(formData: DenominationFormType): Observable<DenominationResult | null> {
    const { valueForDenomination, valueForDifference } = formData;

    if (!valueForDenomination) {
      return of(null);
    }

    const result =
      !valueForDifference || valueForDifference <= 0
        ? DenominationResult.fromDenominationResult(valueForDenomination, Calculator.forEuro, CurrencySymbol.EURO)
        : DenominationResult.fromDenominationResultForDifference(
            valueForDenomination,
            valueForDifference,
            Calculator.forEuro,
            CurrencySymbol.EURO
          );

    return of(result);
  }
}
