import { TestBed } from '@angular/core/testing';

import { ClientCalculationServiceService } from './client-calculation-service.service';

describe('ClientCalculationServiceService', () => {
  let service: ClientCalculationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientCalculationServiceService);
  });

  // const testCases = [
  //   { input: 10, expectedCount: 1, denomination: 10 },
  //   { input: 20, expectedCount: 1, denomination: 20 },
  //   { input: 50, expectedCount: 1, denomination: 50 },
  //   { input: 100, expectedCount: 1, denomination: 100 },
  //   { input: 200, expectedCount: 1, denomination: 200 },
  // ];
  // testCases.forEach(({ input, expectedCount, denomination }) => {
  //   it(`should calculate denomination for euro value ${input}`, () => {
  //     const result = service.forEuro(input);
  //     expect(result.get(denomination)).toEqual(expectedCount);
  //   });
  // });
});
