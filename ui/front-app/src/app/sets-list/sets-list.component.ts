import { Component, OnDestroy, OnInit } from '@angular/core';
import { Sets } from '../beans/sets';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { NotifyAddService } from '../services/notify-add.service';

@Component({
  selector: 'app-sets-list',
  templateUrl: './sets-list.component.html',
  styleUrls: ['./sets-list.component.scss']
})
export class SetsListComponent implements OnInit, OnDestroy {

  sets: Sets[] = [];

  loading: boolean = true;

  notifyAddSubscription: Subscription;

  constructor(private userService: UserService, private route: ActivatedRoute, private notifyAddService: NotifyAddService) {
    this.notifyAddSubscription = this.notifyAddService.getAsObservable().subscribe(item => {
      this.loadSets();
    });
  }
  ngOnDestroy(): void {
    this.notifyAddSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadSets();
  }

  loadSets(): void {
    const uuid = this.route.snapshot.paramMap.get('id') || '';
    this.userService.getUserSets(uuid).subscribe(sets => {
      this.sets = sets;
      this.loading = false;
    });
  }

}
