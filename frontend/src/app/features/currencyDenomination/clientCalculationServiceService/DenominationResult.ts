import { CalculationType } from '../CalculationType';
import { CurrencySymbol } from '../CurrencySymbol';

export class DenominationResult {
  valueForDenomination: number;
  valueForDifference: number | null;
  denominations: Map<number, number>;
  denominationsForDifference: Map<number, number>;
  calculationType: CalculationType;
  currency: CurrencySymbol;

  private constructor(
    valueForDenomination: number,
    valueForDifference: number | null,
    denominations: Map<number, number>,
    denominationsForDifference: Map<number, number>,
    calculationType: CalculationType,
    currency: CurrencySymbol
  ) {
    this.valueForDenomination = valueForDenomination;
    this.valueForDifference = valueForDifference;
    this.denominations = denominations;
    this.denominationsForDifference = denominationsForDifference;
    this.calculationType = calculationType;
    this.currency = currency;
  }

  static fromSomethingForDenomination(
    valueForDenomination: number,
    calculationFunction: (value: number) => Map<number, number>,
    currencySymbol: CurrencySymbol
  ): DenominationResult {
    return new DenominationResult(
      valueForDenomination,
      null,
      calculationFunction(valueForDenomination),
      new Map<number, number>(),
      CalculationType.DENOMINATION,
      currencySymbol
    );
  }

  static fromSomethingWithDifference(
    valueForDenomination: number,
    valueForDifference: number,
    calculationFunction: (value: number) => Map<number, number>,
    currencySymbol: CurrencySymbol
  ): DenominationResult {
    return new DenominationResult(
      valueForDenomination,
      valueForDifference,
      calculationFunction(valueForDenomination),
      calculationFunction(valueForDifference),
      CalculationType.DIFFERENCE,
      currencySymbol
    );
  }
}
