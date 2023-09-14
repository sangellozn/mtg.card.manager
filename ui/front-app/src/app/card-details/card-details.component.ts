import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Card } from '../beans/card';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-card-details',
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.scss']
})
export class CardDetailsComponent implements OnInit {

  @Input() card: Card;

  @Input() show: boolean = false;

  @Output() onClose = new EventEmitter<void>();

  assetBaseUrl: string = environment.assetBaseUrl;

  ngOnInit(): void {
    this.card = new Card;
  }

  onHide(): void {
    this.onClose.emit();
  }

  getCardName(card: Card): string {
    if (card.cardForeignData?.name) {
      return card.cardForeignData.name;
    }

    return card.name;
  }

  getCardText(card: Card): string[] {
    if (card.cardForeignData) {
      return card.cardForeignData.text?.split('\\n');
    }

    return card.text.split('\\n');
  }

  getCardFlavorText(card: Card): string[] {
    if (card.cardForeignData) {
      return card.cardForeignData.flavorText?.split('\\n');
    }

    return card.flavorText.split('\\n');
  }

  getCardType(card: Card) : string {
    if (card.cardForeignData) {
      return card.cardForeignData.type;
    }

    return card.type;
  }

}
