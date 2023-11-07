import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddByFileComponent } from './add-by-file.component';

describe('AddByFileComponent', () => {
  let component: AddByFileComponent;
  let fixture: ComponentFixture<AddByFileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddByFileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddByFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
