import { Injectable } from '@angular/core';
import { AbstractAppService } from './abstract-app.service';
import { HttpClient } from '@angular/common/http';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class SetsService extends AbstractAppService {

  constructor(http: HttpClient, messageService: MessageService) {
    super(http, messageService);
  }

  // FIXME utile ?

}
