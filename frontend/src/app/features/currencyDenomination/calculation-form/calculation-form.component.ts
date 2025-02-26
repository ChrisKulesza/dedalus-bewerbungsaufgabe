import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { DenominationFormControlType, DenominationFormType } from './DenominationFormType';
import { CalculateDenominationService } from '../service/calculate-denomination.service';
import { ToggleButton } from 'primeng/togglebutton';
import { Message } from 'primeng/message';
import { NgTemplateOutlet } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputNumber } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-calculation-form',
  imports: [ReactiveFormsModule, ToggleButton, Message, ButtonModule, InputNumber, InputTextModule, NgTemplateOutlet],
  templateUrl: './calculation-form.component.html',
  styleUrl: './calculation-form.component.css',
})
export class CalculationFormComponent {
  constructor(private readonly _calculationService: CalculateDenominationService) {}

  private _formBuilder = inject(FormBuilder);
  form = this._formBuilder.group<DenominationFormControlType>({
    valueForDenomination: new FormControl<number | null>(null, {
      validators: [Validators.required],
    }),
    valueForDifference: new FormControl<number | null>(null),
    calculateOnServer: new FormControl<boolean | null>(true),
  });

  isFormControlInValid = <Key extends keyof DenominationFormType>(key: Key) => {
    return this.form.controls[key].hasError('required') && this.form.controls[key].touched;
  };

  handleClickOnCalculate = (): void => {
    this._calculationService.setFormData(this.form.getRawValue());
  };

  handleOnFocus($event: Event): void {
    ($event.target as HTMLInputElement).select();
  }
}
