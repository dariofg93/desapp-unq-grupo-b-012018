import { User } from './../models/user'

export class BookingRequest {
	id_request: number;
  state: string;
  requester: User;
  totalHours: number;
  dateTimeOfReservation: Date;
  hoursOfTheReservation: number;
}