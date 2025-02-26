export class EuroDenominationValues {
  static getValues() {
    return new Map<string, number>([
      ['TwoHundredEuros', 200],
      ['OneHundredEuros', 100],
      ['FiftyEuros', 50],
      ['TwentyEuros', 20],
      ['TenEuros', 10],
      ['FiveEuros', 5],
      ['TwoEuros', 2],
      ['OneEuro', 1],
      ['FiftyCents', 0.5],
      ['TwentyCents', 0.2],
      ['TenCents', 0.1],
      ['FifeCents', 0.05],
      ['TwoCents', 0.02],
      ['OneCent', 0.01],
    ]);
  }
}
