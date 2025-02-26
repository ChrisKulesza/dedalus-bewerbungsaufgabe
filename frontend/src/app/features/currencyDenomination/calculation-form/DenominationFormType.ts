import { GenericFormControlType } from './GenericFormControlType';

export class DenominationFormType {
  readonly valueForDenomination: number | null;
  readonly valueForDifference: number | null;
  readonly calculateOnServer: boolean | null;

  constructor(
    valueForDenomination: number | null,
    valueForDifference: number | null,
    calculateOnServer: boolean | null
  ) {
    this.valueForDenomination = valueForDenomination;
    this.valueForDifference = valueForDifference;
    this.calculateOnServer = calculateOnServer;
  }

  static initialState(): DenominationFormType {
    return new DenominationFormType(null, null, true);
  }
}

export type DenominationFormControlType = GenericFormControlType<DenominationFormType>;
