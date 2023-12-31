import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { MessageService } from 'primeng/api';

import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ScrollTopModule } from 'primeng/scrolltop';
import { ToastModule } from 'primeng/toast';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { TagModule } from 'primeng/tag';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CardModule } from 'primeng/card';
import { ProgressBarModule } from 'primeng/progressbar';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputSwitchModule } from 'primeng/inputswitch';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { UserCollectionComponent } from './user-collection/user-collection.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { SetsListComponent } from './sets-list/sets-list.component';
import { SetsListItemComponent } from './sets-list-item/sets-list-item.component';
import { SetsContentComponent } from './sets-content/sets-content.component';
import { NoSetSelectedComponent } from './no-set-selected/no-set-selected.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { SetsContentGridComponent } from './sets-content-grid/sets-content-grid.component';
import { CardDetailsComponent } from './card-details/card-details.component';
import { SetsContentListComponent } from './sets-content-list/sets-content-list.component';
import { RechercheAvanceeComponent } from './recherche-avancee/recherche-avancee.component';
import { AddByCaptureComponent } from './add-by-capture/add-by-capture.component';
import { WebcamModule } from 'ngx-webcam';
import { AddByFileComponent } from './add-by-file/add-by-file.component';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    HomeComponent,
    UserCollectionComponent,
    SearchBarComponent,
    SetsListComponent,
    SetsListItemComponent,
    SetsContentComponent,
    NoSetSelectedComponent,
    AdminHomeComponent,
    SetsContentGridComponent,
    CardDetailsComponent,
    SetsContentListComponent,
    RechercheAvanceeComponent,
    AddByCaptureComponent,
    AddByFileComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ScrollTopModule,
    ToastModule,
    ButtonModule,
    ProgressSpinnerModule,
    AutoCompleteModule,
    FormsModule,
    TableModule,
    InputTextModule,
    InputNumberModule,
    TagModule,
    SelectButtonModule,
    CardModule,
    ProgressBarModule,
    DialogModule,
    WebcamModule,
    DropdownModule,
    InputSwitchModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
