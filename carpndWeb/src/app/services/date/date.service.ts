import { Inject,Injectable,LOCALE_ID,Component } from '@angular/core';
import { DatePipe,registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

registerLocaleData(localeEs, 'es-AR');

@Injectable()
export class DateService {

  constructor(
  	private datepipe: DatePipe
 	) { }

 	public shortDate(date: number): String {
    return this.datepipe.transform(new Date(date),'shortDate');
  }

  public shortTime(date: number): String {
    return this.datepipe.transform(new Date(date),'shortTime');
  }
}
