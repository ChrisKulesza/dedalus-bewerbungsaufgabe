import { effect, inject, Injectable, signal, untracked } from '@angular/core';
import { DenominationResponse } from '../DenominationResponse';
import { DenominationFormType } from '../calculation-form/DenominationFormType';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, finalize, Observable, of, Subject, tap } from 'rxjs';
import { ClientCalculationServiceService } from '../clientCalculationServiceService/client-calculation-service.service';
import { isNotNullAndNotUndefined } from '../../../typeGuards';

/**
 * Service to calculate denomination values on both the server and client sides.
 *
 * Calculation is triggered by changes to the `formData` signal.
 * The service provides Observables for both successful DenominationResponse results and error messages.
 * The initial request is prevented by using the `skipCallback` flag.
 *
 * @class CalculateDenominationService
 */
@Injectable({
  providedIn: 'root',
})
export class CalculateDenominationService {
  private formData = signal<DenominationFormType>(DenominationFormType.initialState());
  private response = new Subject<DenominationResponse | null>();
  private isLoading = signal<boolean>(false);
  private error = new Subject<string | undefined>();

  private readonly httpClient = inject(HttpClient);
  private readonly clientCalculationService = inject(ClientCalculationServiceService);

  // Prevent a request from being sent during the initial effect run
  private skipCallback = true;

  /**
   * Effect that watches for changes on the `formData` signal to trigger denomination calculation.
   * If the formData indicates server calculation, it calls the server API.
   * Otherwise, it uses the client calculation service.
   */
  private formDataChangedEffect = effect(() => {
    this.formData();

    untracked(() => {
      if (!this.skipCallback && this.formData().caclulateOnServer) {
        this.isLoading.set(true);

        // Server calculation
        this.calculateDenominationFor(this.formData())
          .pipe(
            tap({
              next: (response) => {
                this.response.next(response);
              },
              error: (err) => {
                this.error.next(err.message);
              },
            }),
            catchError((err) => {
              this.error.next(err.message);
              return of(null);
            }),
            finalize(() => this.isLoading.set(false))
          )
          .subscribe();
      }

      // Client calculation
      if (!this.skipCallback && !this.formData().caclulateOnServer) {
        this.isLoading.set(true);
        this.clientCalculationService
          .calculateDenominationsFor(this.formData())
          .pipe(
            tap({
              next: (response) => {
                this.response.next(DenominationResponse.fromDenominationResult(response));
              },
            }),
            catchError((err) => {
              this.error.next(err.message);
              return of(null);
            }),
            finalize(() => this.isLoading.set(false))
          )
          .subscribe();
      }

      this.skipCallback = false;
    });
  });

  setFormData(formData: DenominationFormType): void {
    this.formData.set(formData);
  }

  getDenominationResult() {
    return this.response.asObservable();
  }

  getError() {
    return this.error.asObservable();
  }

  private calculateDenominationFor(formData: DenominationFormType): Observable<DenominationResponse> {
    let params = new HttpParams();

    if (isNotNullAndNotUndefined(formData.valueForDenomination)) {
      params = params.set('valueForDenomination', formData.valueForDenomination);
    }

    if (isNotNullAndNotUndefined(formData.valueForDifference)) {
      params = params.set('valueForDifference', formData.valueForDifference);
    }

    return this.httpClient.get<DenominationResponse>('http://localhost:8080/api/denomination/calculateForEuro', {
      params: params,
    });
  }
}
