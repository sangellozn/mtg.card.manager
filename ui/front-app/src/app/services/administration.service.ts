import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { CardImagerySetsUpdate } from '../beans/card-imagery-sets-update';
import { Observable, catchError, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdministrationService extends AbstractAppService {

  private adminUrl = `${environment.baseUrl}/admin`;

  private updateDatabaseUrl = `${environment.mcmDbUpdateBaseUrl}/admin/db/update`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  updateCardImagery(data: CardImagerySetsUpdate): Observable<void> {
    return this.http.post<void>(`${this.adminUrl}/cards/update-imagery`, data).pipe(
      tap(() => this.messageService.add({ severity: 'success', summary: 'Demande réussie', detail: 'La mise à jour des images est en cours !' })),
      catchError(this.throwError()));
  }

  updateDatabase(): Observable<void> {
    return this.http.post<void>(this.updateDatabaseUrl, null).pipe(
      tap(() => this.messageService.add({ severity: 'success', summary: 'Demande réussie', detail: 'La mise à jour de la base de données en cours !' })),
      catchError(this.throwError()));
  }

}
