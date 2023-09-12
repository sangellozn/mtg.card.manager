import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetsContentGridComponent } from './sets-content-grid.component';

describe('SetsContentGridComponent', () => {
  let component: SetsContentGridComponent;
  let fixture: ComponentFixture<SetsContentGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetsContentGridComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetsContentGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
