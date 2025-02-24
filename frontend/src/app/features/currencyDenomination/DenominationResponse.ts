import { CurrencyValue } from './CurrencyValue';
import { CalculationType } from './CalculationType';

export class DenominationResponse {
  valueForDenomination: number;
  valueForDifference: number | null;
  denominations: CurrencyValue[];
  calculationType: CalculationType;
  currency: string;

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
}
