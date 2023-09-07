import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetsContentComponent } from './sets-content.component';

describe('SetsContentComponent', () => {
  let component: SetsContentComponent;
  let fixture: ComponentFixture<SetsContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetsContentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetsContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
