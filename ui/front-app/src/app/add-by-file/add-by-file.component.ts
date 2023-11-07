import { Component, OnInit } from '@angular/core';
import { CsvImportService } from '../services/csv-import.service';

@Component({
  selector: 'app-add-by-file',
  templateUrl: './add-by-file.component.html',
  styleUrls: ['./add-by-file.component.scss']
})
export class AddByFileComponent implements OnInit {

  selectedFile: File;

  constructor(private csvImportService: CsvImportService) { }
  
  ngOnInit(): void { }

  selectFiles(event: any): void {
    this.selectedFile = event.target.files.item(0);
  }

  upload(): void {
    this.csvImportService.importCsv(this.selectedFile).subscribe();
  }

}
