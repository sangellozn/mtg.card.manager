import { Component, OnInit } from '@angular/core';
import { SearchResultItem } from '../beans/search-result-item';
import { SearchService } from '../services/search.service';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';

interface AutoCompleteCompleteEvent {
  originalEvent: Event;
  query: string;
}

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent implements OnInit {

  searchResultItems: SearchResultItem[] = [];

  selectedResult: any;

  constructor(private searchService: SearchService, private userService: UserService, 
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    
  }

  search(event: AutoCompleteCompleteEvent): void {
    this.searchService.search(event.query).subscribe(searchResult => this.searchResultItems = searchResult.results);
  }

  onSelect(item: SearchResultItem): void {
    const uuid = this.route.snapshot.paramMap.get('id') || '';
    this.userService.addItem(uuid, item).subscribe();
    this.selectedResult = null;
  }

}
