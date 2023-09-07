import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { MessageService } from 'primeng/api';

import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { ScrollTopModule } from 'primeng/scrolltop';
import { ToastModule } from 'primeng/toast';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { UserCollectionComponent } from './user-collection/user-collection.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import { SetsListComponent } from './sets-list/sets-list.component';
import { SetsListItemComponent } from './sets-list-item/sets-list-item.component';
import { SetsContentComponent } from './sets-content/sets-content.component';
import { NoSetSelectedComponent } from './no-set-selected/no-set-selected.component';

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
    NoSetSelectedComponent
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
    AutoCompleteModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
