import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetsContentListComponent } from './sets-content-list.component';

describe('SetsContentListComponent', () => {
  let component: SetsContentListComponent;
  let fixture: ComponentFixture<SetsContentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetsContentListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetsContentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
