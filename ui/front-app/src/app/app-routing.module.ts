import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { UserCollectionComponent } from './user-collection/user-collection.component';
import { SetsContentComponent } from './sets-content/sets-content.component';
import { NoSetSelectedComponent } from './no-set-selected/no-set-selected.component';

const routes: Routes = [
  { path: 'mcm', component: HomeComponent },
  { path: 'mcm/:id', component: UserCollectionComponent,
    children: [
      { path: '', component: NoSetSelectedComponent},
      { path: 'sets/:code', component: SetsContentComponent }
    ] 
  },
  { path: '', redirectTo: '/mcm', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
