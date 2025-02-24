import { TestBed } from '@angular/core/testing';

import { CalculateDenominationService } from './calculate-denomination.service';

describe('CalculateDenominationService', () => {
  let service: CalculateDenominationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateDenominationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
