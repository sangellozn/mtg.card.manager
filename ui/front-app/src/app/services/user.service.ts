import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { Observable, catchError } from 'rxjs';
import { UserInfo } from '../beans/user-info';
import { Sets } from '../beans/sets';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractAppService {

  private userUrl = `${environment.baseUrl}/users`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  getUserInfos(): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>(this.userUrl).pipe(catchError(this.throwError()));
  }

  getUserSets(uuid: string): Observable<Sets[]> {
    return this.http.get<Sets[]>(`${this.userUrl}/${uuid}/sets`).pipe(catchError(this.throwError()));
  }

}
