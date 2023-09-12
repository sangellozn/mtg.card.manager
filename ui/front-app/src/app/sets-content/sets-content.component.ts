import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { UserSet } from '../beans/user-set';
import { UserService } from '../services/user.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Subscription, filter } from 'rxjs';
import { UserCard } from '../beans/user-card';
import { NotifyAddService } from '../services/notify-add.service';
import { Card } from '../beans/card';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sets-content',
  templateUrl: './sets-content.component.html',
  styleUrls: ['./sets-content.component.scss']
})
export class SetsContentComponent implements OnInit {

  userSet: UserSet = new UserSet;

  loading: boolean = true;

  notifyAddSubscription: Subscription;

  assetBaseUrl: string = environment.assetBaseUrl;

  displayOptions: any[] = [
    { icon: 'pi pi-list', label: 'Liste', component: 'list' },
    { icon: 'pi pi-table', label: 'Tableau', component: 'grid' }
  ];

  selectedDisplayOption: any = this.displayOptions[0];

  constructor(private userService: UserService, private router: Router, 
    private route: ActivatedRoute, private notifyAddService: NotifyAddService) {
      this.notifyAddSubscription = this.notifyAddService.getAsObservable().subscribe(item => {
        if (item.type === 'CARDS' && this.userSet.cards.find(card => card.uuid === item.id)) {
          this.load();
        }
      });
  }

  ngOnInit(): void {
    this.load();
    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.loading = true;
      this.load();
    });
  }

  onBlur(userCard: UserCard): void {
    const uuid = this.route.snapshot.parent?.paramMap.get('id') || '';
    this.userService.updateUserCardInfo(uuid, userCard).subscribe();
  }

  private load() {
    const uuid = this.route.snapshot.parent?.paramMap.get('id') || '';
    const code = this.route.snapshot.paramMap.get('code') || '';
    this.userService.getSetContent(uuid, code).subscribe(set => {
      this.userSet = set;
      this.loading = false;
    });
  }

  getCardName(card: Card): string {
    if (card.cardForeignData?.name) {
      return card.cardForeignData.name;
    }

    return card.name;
  }

  getProgressValue(): string {
    const val = this.userSet.cards.flatMap(card => card.possessions).reduce((acc, currentValue) => {
      if (currentValue.qteEX > 0 || currentValue.qteGD > 0 || currentValue.qteLP > 0 || currentValue.qteM || currentValue.qteNM
        || currentValue.qtePL > 0 || currentValue.qtePoor > 0) {
        return acc + 1;
      }
      return acc;
    } , 0) / this.userSet.cards.length;
    return val.toFixed(2);
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
