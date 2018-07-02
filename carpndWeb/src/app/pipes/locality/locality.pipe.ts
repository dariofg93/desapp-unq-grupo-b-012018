import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'locality'
})
export class LocalityPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    return null;
  }

}
