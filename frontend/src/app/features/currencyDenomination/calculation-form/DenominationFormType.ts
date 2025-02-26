import { GenericFormControlType } from './GenericFormControlType';

export class DenominationFormType {
  readonly valueForDenomination: number | null;
  readonly valueForDifference: number | null;
  readonly caclulateOnServer: boolean | null;

  constructor(
    valueForDenomination: number | null,
    valueForDifference: number | null,
    caclulateOnServer: boolean | null
  ) {
    this.valueForDenomination = valueForDenomination;
    this.valueForDifference = valueForDifference;
    this.caclulateOnServer = caclulateOnServer;
  }

  static initialState(): DenominationFormType {
    return new DenominationFormType(null, null, true);
  }
}

export type DenominationFormControlType = GenericFormControlType<DenominationFormType>;
