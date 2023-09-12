import { Component, Input, OnInit } from '@angular/core';
import { Card } from '../beans/card';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sets-content-grid',
  templateUrl: './sets-content-grid.component.html',
  styleUrls: ['./sets-content-grid.component.scss']
})
export class SetsContentGridComponent implements OnInit {
  
  @Input() cards: Card[] = [];

  assetBaseUrl: string = environment.assetBaseUrl;

  ngOnInit(): void {
    
  }

  getCardPossessions(card : Card): any[] {
    const baseData = [
      { type: 'M', qty: 0, severity: 'success'},
      { type: 'NM', qty: 0, severity: 'success'},
      { type: 'EX', qty: 0, severity: 'info'},
      { type: 'GD', qty: 0, severity: 'info'},
      { type: 'LP', qty: 0, severity: 'warning'},
      { type: 'PL', qty: 0, severity: 'warning'},
      { type: 'POOR', qty: 0, severity: 'danger'}
    ];

    for (let p of card.possessions) {
      if (p.qteM > 0) { baseData[0].qty += 1; }
      if (p.qteNM > 0) { baseData[1].qty += 1; }
      if (p.qteEX > 0) { baseData[2].qty += 1; }
      if (p.qteGD > 0) { baseData[3].qty += 1; }
      if (p.qteLP > 0) { baseData[4].qty += 1; }
      if (p.qtePL > 0) { baseData[5].qty += 1; }
      if (p.qtePoor > 0) { baseData[6].qty += 1; }
    }

    return baseData.filter(item => item.qty > 0);
  }

  getCardName(card: Card): string {
    if (card.cardForeignData?.name) {
      return card.cardForeignData.name;
    }

    return card.name;
  }

}
