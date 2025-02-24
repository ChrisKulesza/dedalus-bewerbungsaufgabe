import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { DenominationFormControlType, DenominationFormType } from './DenominationFormType';
import { CalculateDenominationService } from '../service/calculate-denomination.service';
import { ToggleButton } from 'primeng/togglebutton';
import { Message } from 'primeng/message';
import { NgIf } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputNumber } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-calculation-form',
  imports: [ReactiveFormsModule, ToggleButton, Message, NgIf, ButtonModule, InputNumber, InputTextModule],
  templateUrl: './calculation-form.component.html',
  styleUrl: './calculation-form.component.css',
})
export class CalculationFormComponent {
  constructor(private readonly calculationService: CalculateDenominationService) {}

  formBuilder = inject(FormBuilder);
  form = this.formBuilder.group<DenominationFormControlType>({
    valueForDenomination: new FormControl<number | null>(null, {
      validators: [Validators.required],
    }),
    valueForDifference: new FormControl<number | null>(null),
    caclulateOnServer: new FormControl<boolean | null>(true),
  });

  isFormControlInValid = <Key extends keyof DenominationFormType>(key: Key) => {
    return this.form.controls[key].hasError('required') && this.form.controls[key].touched;
  };

  handleClickOnCaclulate = (): void => {
    this.calculationService.setFormData(this.form.getRawValue());
  };

  handleOnFocus($event: Event) {
    ($event.target as HTMLInputElement).select();
  }

  clearFormControlForKey<Key extends keyof DenominationFormType>(key: Key) {
    console.log('key', key);
    this.form.controls[key].setValue(null as any);
  }
}
