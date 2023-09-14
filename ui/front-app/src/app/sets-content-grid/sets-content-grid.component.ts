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

  showDetail: boolean = false;

  selectedCard: Card;

  assetBaseUrl: string = environment.assetBaseUrl;

  ngOnInit(): void {
    
  }

  showCardDetail(card: Card): void {
    this.selectedCard = card;
    this.showDetail = true;
  }

  onCloseCardDetail(): void {
    this.selectedCard = new Card;
    this.showDetail = false;
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
      baseData[0].qty += p.qteM;
      baseData[1].qty += p.qteNM;
      baseData[2].qty += p.qteEX;
      baseData[3].qty += p.qteGD;
      baseData[4].qty += p.qteLP;
      baseData[5].qty += p.qtePL;
      baseData[6].qty += p.qtePoor;
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
