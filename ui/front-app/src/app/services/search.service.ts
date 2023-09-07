import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { Observable, catchError } from 'rxjs';
import { SearchResult } from '../beans/search-result';

@Injectable({
  providedIn: 'root'
})
export class SearchService extends AbstractAppService {

  private searchUrl = `${environment.baseUrl}/search`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  search(query: string): Observable<SearchResult> {
    return this.http.get<SearchResult>(`${this.searchUrl}?query=${query}`).pipe(catchError(this.throwError()));
  }

}
