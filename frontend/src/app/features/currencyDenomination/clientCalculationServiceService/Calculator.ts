import { EuroDenominationValue } from './EuroDenominationValue';

export class Calculator {
  static forEuro(value: number): Map<number, number> {
    let currentValueInCent = Calculator.euroToCent(value);
    const results = new Map<number, number>();

    for (let currencyValue of EuroDenominationValue.values()) {
      const valueToCheckAgainst = Calculator.euroToCent(currencyValue);

      if (currentValueInCent >= valueToCheckAgainst) {
        const count = Math.floor(currentValueInCent / valueToCheckAgainst);
        results.set(currencyValue, count);
        currentValueInCent -= count * valueToCheckAgainst;
      } else {
        results.set(currencyValue, 0);
      }
    }

    return results;
  }

  private static euroToCent(value: number): number {
    const centPerEuro = 100;
    return Math.round(centPerEuro * value);
  }
}
