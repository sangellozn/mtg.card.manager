import { TestBed } from '@angular/core/testing';

import { NotifyAddService } from './notify-add.service';

describe('NotifyAddService', () => {
  let service: NotifyAddService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotifyAddService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
