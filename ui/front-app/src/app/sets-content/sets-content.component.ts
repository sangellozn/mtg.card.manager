import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { UserSet } from '../beans/user-set';
import { UserService } from '../services/user.service';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { UserCard } from '../beans/user-card';

@Component({
  selector: 'app-sets-content',
  templateUrl: './sets-content.component.html',
  styleUrls: ['./sets-content.component.scss']
})
export class SetsContentComponent implements OnInit {

  userSet: UserSet = new UserSet;

  loading: boolean = true;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

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
  
}
