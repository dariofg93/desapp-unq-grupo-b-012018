import { User } from './../models/user'
import { BookingState } from './../models/booking-state'

export class BookingRequest {
	id_request: number;
  state: BookingState;
  requester: User;
  totalHours: number;
  dateTimeOfReservation: Date;
  hoursOfTheReservation: number;
}