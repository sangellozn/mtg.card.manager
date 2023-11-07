import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { ImageDetectionRequest } from '../beans/image-detection-request';
import { Observable, catchError, tap } from 'rxjs';
import { ImageDetectionResult } from '../beans/image-detection-result';

@Injectable({
  providedIn: 'root'
})
export class ImageDetectionService  extends AbstractAppService {

  private imageDetectionUrl = `${environment.baseUrl}/image-detection`;

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  detect(data: ImageDetectionRequest): Observable<ImageDetectionResult> {
    return this.http.post<ImageDetectionResult>(`${this.imageDetectionUrl}`, data).pipe(catchError(this.throwError()));
  }

}
