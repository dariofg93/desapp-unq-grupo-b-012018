import { TestBed, inject } from '@angular/core/testing';

import { GenericRestService } from './generic-rest.service';

describe('GenericRestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GenericRestService]
    });
  });

  it('should be created', inject([GenericRestService], (service: GenericRestService) => {
    expect(service).toBeTruthy();
  }));
});
