import { Component, OnInit } from '@angular/core';
import { SearchResultItem } from '../beans/search-result-item';
import { SearchService } from '../services/search.service';

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

  constructor(private searchService: SearchService) {}

  ngOnInit(): void {
    
  }

  search(event: AutoCompleteCompleteEvent): void {
    this.searchService.search(event.query).subscribe(searchResult => this.searchResultItems = searchResult.results);
  }

  onSelect(item: SearchResultItem): void {
    console.log(item);
  }

}
