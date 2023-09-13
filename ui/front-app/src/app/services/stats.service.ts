import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { Observable, catchError } from 'rxjs';
import { Stats } from '../beans/stats';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatsService extends AbstractAppService {

  private statsUrl = `${environment.baseUrl}/stats`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  get(userUuid: string): Observable<Stats> {
    return this.http.get<Stats>(`${this.statsUrl}/${userUuid}`).pipe(catchError(this.throwError()));
  }

}
