import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { SearchResultItem } from '../beans/search-result-item';

@Injectable({
  providedIn: 'root'
})
export class NotifyAddService {

  private addedItem = new Subject<SearchResultItem>();

  notifyAdd(item: SearchResultItem): void {
    this.addedItem.next(item);
  }

  getAsObservable(): Observable<SearchResultItem> {
    return this.addedItem.asObservable();
  }

}
