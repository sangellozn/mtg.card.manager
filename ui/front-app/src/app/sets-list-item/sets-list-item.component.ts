import { Component, Input } from '@angular/core';
import { Sets } from '../beans/sets';

@Component({
  selector: 'app-sets-list-item',
  templateUrl: './sets-list-item.component.html',
  styleUrls: ['./sets-list-item.component.scss']
})
export class SetsListItemComponent {

  @Input() items: Sets[];

}
