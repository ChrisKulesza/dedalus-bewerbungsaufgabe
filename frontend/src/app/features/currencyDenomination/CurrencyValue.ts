export class CurrencyValue {
  value: number;
  count: string;

  constructor(value: number, count: string) {
    this.value = value;
    this.count = count;
  }

  static fromDenominations(denominations: Map<number, number>): CurrencyValue[] {
    const currencyValues: CurrencyValue[] = [];
    denominations.forEach((v, k) => currencyValues.push(new CurrencyValue(k, v.toString())));
    return currencyValues;
  }

  static fromDenominationsForDifference(
    denominations: Map<number, number>,
    denominationsDifference: Map<number, number>
  ): CurrencyValue[] {
    const currencyValues: CurrencyValue[] = [];

    denominations.forEach((v, k) => {
      const valueForDifference = denominationsDifference.get(k);

      if (valueForDifference !== undefined && (valueForDifference !== v || (valueForDifference > 0 && v > 0))) {
        const difference = CurrencyValue.caclulateDifferenceValue(valueForDifference - v);
        currencyValues.push(new CurrencyValue(k, difference));
      }
    });

    return currencyValues;
  }

  private static caclulateDifferenceValue(value: number): string {
    return value <= 0 ? `${value}` : `+${value}`;
  }
}
