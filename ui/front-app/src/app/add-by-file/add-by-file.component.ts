import { Component, OnInit } from '@angular/core';
import { CsvImportService } from '../services/csv-import.service';
import { CsvImportResponseItem } from '../beans/csv-import-response-item';
import { environment } from 'src/environments/environment';
import { ActivatedRoute } from '@angular/router';
import { NotifyAddService } from '../services/notify-add.service';
import { SearchResultItem } from '../beans/search-result-item';

@Component({
  selector: 'app-add-by-file',
  templateUrl: './add-by-file.component.html',
  styleUrls: ['./add-by-file.component.scss']
})
export class AddByFileComponent implements OnInit {

  selectedFile?: File;
  
  itemsImported: CsvImportResponseItem[] = [];

  assetBaseUrl: string = environment.assetBaseUrl;

  userUuid: string = '';

  conditions: any[] = [
    {label: 'Mint', value: 'M'},
    {label: 'Near Mint', value: 'NM'},
    {label: 'Excellent', value: 'EX'},
    {label: 'Good', value: 'GD'},
    {label: 'Light Played', value: 'LP'},
    {label: 'Played', value: 'PL'},
    {label: 'Poor', value: 'POOR'}
  ];

  constructor(private csvImportService: CsvImportService, private route: ActivatedRoute, private notifyAddService: NotifyAddService) { 
    this.userUuid = this.route.snapshot.parent?.paramMap.get('id') || '';
  }
  
  ngOnInit(): void { 
    
  }

  applyToEmpty(event: any) {
    if (this.itemsImported.length) {
      for (let i = 0; i < this.itemsImported.length; i++) {
        if (!this.itemsImported[i].condition) {
          this.itemsImported[i].condition = event.value;
        }
      }
    }
  }

  selectFiles(event: any): void {
    this.selectedFile = event.target.files.item(0);
  }

  upload(): void {
    this.csvImportService.importCsv(this.selectedFile).subscribe(data => {
      this.itemsImported = this.itemsImported.concat(data.items);
      this.selectedFile = undefined;
    });
  }

  remove(item: CsvImportResponseItem) {
    this.itemsImported = this.itemsImported.filter(elt => elt.uuid != item.uuid);
  }

  addCards() {
    this.csvImportService.addCards(this.userUuid, this.itemsImported).subscribe(() => {
      this.itemsImported = [];
      this.notifyAddService.notifyAdd(new SearchResultItem);
    });
  }

}
