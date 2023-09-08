import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { Observable, catchError, map, tap, using } from 'rxjs';
import { UserInfo } from '../beans/user-info';
import { Sets } from '../beans/sets';
import { SearchResultItem } from '../beans/search-result-item';
import { NotifyAddService } from './notify-add.service';
import { UserSet } from '../beans/user-set';
import { UserCard } from '../beans/user-card';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractAppService {

  private userUrl = `${environment.baseUrl}/users`;

  constructor(http: HttpClient, messageService: MessageService, private notifyAddService: NotifyAddService) {
    super(http, messageService);
  }

  getUserInfos(): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>(this.userUrl).pipe(catchError(this.throwError()));
  }

  getUserSets(uuid: string): Observable<Sets[]> {
    return this.http.get<Sets[]>(`${this.userUrl}/${uuid}/sets`).pipe(catchError(this.throwError()));
  }

  getSetContent(uuid: string, code: string): Observable<UserSet> {
    return this.http.get<UserSet>(`${this.userUrl}/${uuid}/sets/${code}`).pipe(catchError(this.throwError()));
  }

  addItem(uuid: string, searchResultItem: SearchResultItem): Observable<void> {
    let url = `${this.userUrl}/${uuid}`;
    if (searchResultItem.type === 'SETS') {
      url += `/sets/${searchResultItem.id}`;
    } else if (searchResultItem.type === 'CARDS') {
      url += `/cards/${searchResultItem.id}`;
    }

    return this.http.post<void>(url, null).pipe(
      tap(() => this.messageService.add({ severity: 'success', summary: 'Ajout réussi', detail: 'La carte/set a été ajouté.' })),
      tap(() => this.notifyAddService.notifyAdd(searchResultItem)),
      catchError(this.throwError()));
  }

  updateUserCardInfo(userUuid: string, userCard: UserCard): Observable<void> {
    return this.http.put<void>(`${this.userUrl}/${userUuid}/cards/${userCard.cardUuid}`, userCard)
      .pipe(catchError(this.throwError()));
  }

}
