import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DenominationResultTableComponent } from './denomination-result-table.component';

describe('DenominationResultTableComponent', () => {
  let component: DenominationResultTableComponent;
  let fixture: ComponentFixture<DenominationResultTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DenominationResultTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DenominationResultTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
