import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddByCaptureComponent } from './add-by-capture.component';

describe('AddByCaptureComponent', () => {
  let component: AddByCaptureComponent;
  let fixture: ComponentFixture<AddByCaptureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddByCaptureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddByCaptureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
