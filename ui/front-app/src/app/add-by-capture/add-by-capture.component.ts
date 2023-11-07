import { Component, OnInit } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';
import {Subject, Observable} from 'rxjs';
import { ImageDetectionService } from '../services/image-detection.service';
import { ImageDetectionRequest } from '../beans/image-detection-request';

@Component({
  selector: 'app-add-by-capture',
  templateUrl: './add-by-capture.component.html',
  styleUrls: ['./add-by-capture.component.scss']
})
export class AddByCaptureComponent implements OnInit {

  private trigger: Subject<void> = new Subject<void>();

  webcamImage: WebcamImage;

  constructor(private imageDetectionService: ImageDetectionService) { }

  ngOnInit(): void { }

  public triggerSnapshot(): void {
    this.trigger.next();
  }

  public handleImage(webcamImage: WebcamImage): void {
    this.webcamImage = webcamImage;
    const data = new ImageDetectionRequest;
    data.imageData = webcamImage.imageAsBase64;
    this.imageDetectionService.detect(data).subscribe();
  }

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

}
