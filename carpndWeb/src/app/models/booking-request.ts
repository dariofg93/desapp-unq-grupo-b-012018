import { User } from './../models/user'
import { BookingState } from './../models/booking-state'
import { BookingStatus } from './../models/booking-status'

export class BookingRequest {
	id: number;
  state: BookingState;
  status: BookingStatus;
  requester: User;
  totalHours: number;
  reservationDateTime: Date;
  hoursOfTheReservation: number;
}