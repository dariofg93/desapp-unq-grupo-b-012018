import { Pipe, PipeTransform } from '@angular/core';

import { Publication } from './../../models/publication';

@Pipe({name: 'searchByCategory'})
export class CategoryPipe implements PipeTransform {

  transform(publications: Publication[], concretType: string): Publication[] {
    return publications.filter(function(p){ return concretType? p.publishedVehicle.category.concretType == concretType: true });
  }

}
