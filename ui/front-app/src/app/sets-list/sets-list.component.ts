import { Component, OnInit } from '@angular/core';
import { Sets } from '../beans/sets';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sets-list',
  templateUrl: './sets-list.component.html',
  styleUrls: ['./sets-list.component.scss']
})
export class SetsListComponent implements OnInit {

  sets: Sets[] = [];

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const uuid = this.route.snapshot.paramMap.get('id') || '';
    this.userService.getUserSets(uuid).subscribe(sets => this.sets = sets);
  }

}
