import { CurrencyValue } from './CurrencyValue';
import { CalculationType } from './CalculationType';
import { isNotNullAndNotUndefined } from '../../typeGuards';
import { DenominationResult } from './clientCalculationService/DenominationResult';

export class DenominationResponse {
  readonly valueForDenomination: number;
  readonly valueForDifference: number | null;
  readonly denominations: CurrencyValue[];
  readonly calculationType: CalculationType;
  readonly currency: string;

  constructor(
    valueForDenomination: number,
    valueForDifference: number | null,
    denominations: CurrencyValue[],
    calculationType: CalculationType,
    currency: string
  ) {
    this.valueForDenomination = valueForDenomination;
    this.valueForDifference = valueForDifference;
    this.denominations = denominations;
    this.calculationType = calculationType;
    this.currency = currency;
  }

  static fromDenominationResult(result: DenominationResult | null): DenominationResponse | null {
    if (!result) return null;

    return !isNotNullAndNotUndefined(result.valueForDifference)
      ? new DenominationResponse(
          result.valueForDenomination,
          null,
          CurrencyValue.fromDenominations(result.denominations),
          result.calculationType,
          result.currency
        )
      : new DenominationResponse(
          result.valueForDenomination,
          result.valueForDifference,
          CurrencyValue.fromDenominationsForDifference(result.denominations, result.denominationsForDifference),
          result.calculationType,
          result.currency
        );
  }
}
