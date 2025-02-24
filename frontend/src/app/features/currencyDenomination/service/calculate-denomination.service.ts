import { effect, Injectable, signal, untracked } from '@angular/core';
import { DenominationResponse } from '../DenominationResponse';
import { DenominationFormType } from '../calculation-form/DenominationFormType';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, finalize, Observable, of, Subject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CalculateDenominationService {
  private formData = signal<DenominationFormType>(DenominationFormType.initialState());
  private response = new Subject<DenominationResponse | null>();
  private isLoading = signal<boolean>(false);
  private error = new Subject<string | undefined>();

  private skipCallback = true;
  private formDataChangedEffect = effect(() => {
    this.formData();

    untracked(() => {
      // Prevent a request from being sent during the initial effect run
      if (!this.skipCallback && this.formData().caclulateOnServer) {
        this.isLoading.set(true);

        this.calculateDenominationFor(this.formData())
          .pipe(
            tap({
              next: this.response.next,
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

      this.skipCallback = false;
    });
  });

  constructor(private readonly httpClient: HttpClient) {}

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

    if (formData.valueForDenomination) {
      params = params.set('valueForDenomination', formData.valueForDenomination);
    }
    if (formData.valueForDifference) {
      params = params.set('valueForDifference', formData.valueForDifference);
    }

    return this.httpClient.get<DenominationResponse>('http://localhost:8080/api/denomination/calculateForEuro', {
      params: params,
    });
  }
}
