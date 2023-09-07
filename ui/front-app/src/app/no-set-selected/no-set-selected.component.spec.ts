import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoSetSelectedComponent } from './no-set-selected.component';

describe('NoSetSelectedComponent', () => {
  let component: NoSetSelectedComponent;
  let fixture: ComponentFixture<NoSetSelectedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoSetSelectedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoSetSelectedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
