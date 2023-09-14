import { Component, OnInit } from '@angular/core';
import { Stats } from '../beans/stats';
import { StatsService } from '../services/stats.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-no-set-selected',
  templateUrl: './no-set-selected.component.html',
  styleUrls: ['./no-set-selected.component.scss']
})
export class NoSetSelectedComponent implements OnInit {

  stats: Stats = new Stats;

  constructor(private statsService: StatsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const uuid = this.route.snapshot.parent?.paramMap.get('id') || '';
    this.statsService.get(uuid).subscribe(stats => this.stats = stats);
  }

}
