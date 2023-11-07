import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CsvImportResponse } from '../beans/csv-import-response';

@Injectable({
  providedIn: 'root'
})
export class CsvImportService extends AbstractAppService {

  private csvImportUrl = `${environment.baseUrl}/csv-import`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  importCsv(file: File) : Observable<CsvImportResponse> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    return this.http.post<CsvImportResponse>(`${this.csvImportUrl}`, formData);
  }

}
