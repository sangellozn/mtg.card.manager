import { Component, Input, OnInit } from '@angular/core';
import { Card } from '../beans/card';
import { UserCard } from '../beans/user-card';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { UserSet } from '../beans/user-set';

@Component({
  selector: 'app-sets-content-list',
  templateUrl: './sets-content-list.component.html',
  styleUrls: ['./sets-content-list.component.scss']
})
export class SetsContentListComponent implements OnInit {

  @Input() userSet: UserSet = new UserSet;

  assetBaseUrl: string = environment.assetBaseUrl;

  showDetail: boolean = false;

  selectedCard: Card;

  constructor(private userService: UserService, private route: ActivatedRoute) { }

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

  onBlur(userCard: UserCard): void {
    const uuid = this.route.snapshot.parent?.paramMap.get('id') || '';
    this.userService.updateUserCardInfo(uuid, userCard).subscribe();
  }

  getCardName(card: Card): string {
    if (card.cardForeignData?.name) {
      return card.cardForeignData.name;
    }

    return card.name;
  }

  getManaCostArray(card: Card): string[] | null {
    if (card.manaCost) {
      return card.manaCost.match(/\{[0-9A-Z]+\}/g);
    }

    return null;
  }

  getRarityLabel(label: string): string {
    if (label === 'uncommon') {
      return 'Unco';
    }

    if (label === 'common') {
      return 'Commune';
    }

    if (label === 'rare') {
      return 'Rare';
    }

    if (label === 'mythic') {
      return 'Mythique';
    }

    return label;
  }

}
