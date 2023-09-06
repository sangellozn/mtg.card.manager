import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MessageService } from "primeng/api";
import { Observable, of, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AbstractAppService {

    constructor(protected http: HttpClient, protected messageService: MessageService) { }

    handleError<T>(result?: T) {
        return (error: any): Observable<T> => {
            this.messageService.add({severity:'error', summary: error.statusText, detail: error.message});
            return of(result as T);
        }
    }

    throwError() {
        return (error: any): Observable<never> => {
            this.messageService.add({severity:'error', summary: error.statusText, detail: error.message });
            return throwError(error);
        }
    }

}