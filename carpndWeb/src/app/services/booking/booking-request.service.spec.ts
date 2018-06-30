import { TestBed, inject } from '@angular/core/testing';

import { BookingRequestService } from './booking-request.service';

describe('BookingRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BookingRequestService]
    });
  });

  it('should be created', inject([BookingRequestService], (service: BookingRequestService) => {
    expect(service).toBeTruthy();
  }));
});
