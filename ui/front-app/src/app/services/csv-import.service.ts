import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { Observable, catchError, tap } from 'rxjs';
import { CsvImportResponse } from '../beans/csv-import-response';
import { CsvImportResponseItem } from '../beans/csv-import-response-item';

@Injectable({
  providedIn: 'root'
})
export class CsvImportService extends AbstractAppService {

  private csvImportUrl = `${environment.baseUrl}/csv-import`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  importCsv(file?: File) : Observable<CsvImportResponse> {
    if (file) {

      const formData: FormData = new FormData();
      
      formData.append('file', file);
      
      return this.http.post<CsvImportResponse>(`${this.csvImportUrl}`, formData).pipe(
        tap(() => this.messageService.add({ severity: 'success', summary: 'Import réussi', detail: "L'import du fichier CSV a été effectué avec succès !" })),
        catchError(this.throwError())
        );
    }
    throw new Error('File must be defined !');
  }

  addCards(userUuid: string, items: CsvImportResponseItem[]): Observable<void> {
    return this.http.post<void>(`${this.csvImportUrl}/${userUuid}/cards`, items).pipe(
      tap(() => this.messageService.add({ severity: 'success', summary: 'Ajout réussi', detail: "L'ajout des cartes a été effectué avec succès !" })),
      catchError(this.throwError())
    );
  }

}
