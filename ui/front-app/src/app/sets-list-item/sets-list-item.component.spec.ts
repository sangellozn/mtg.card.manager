import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetsListItemComponent } from './sets-list-item.component';

describe('SetsListItemComponent', () => {
  let component: SetsListItemComponent;
  let fixture: ComponentFixture<SetsListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetsListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetsListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
