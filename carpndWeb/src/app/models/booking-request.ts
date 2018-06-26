import { User } from './../models/user'
import { BookingState } from './../models/booking-state'

export class BookingRequest {
	id: number;
  state: BookingState;
  requester: User;
  totalHours: number;
  reservationDateTime: Date;
  hoursOfTheReservation: number;
}