import { Component, OnInit } from '@angular/core';
import { Card } from 'primeng/card';
import { CardImagerySetsUpdate } from 'src/app/beans/card-imagery-sets-update';
import { AdministrationService } from 'src/app/services/administration.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {

  constructor(private administrationService: AdministrationService) { }

  ngOnInit(): void {
    
  }

  recupererImage(): void {
    this.administrationService.updateCardImagery(new CardImagerySetsUpdate).subscribe();
  }

}
