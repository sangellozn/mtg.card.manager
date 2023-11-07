import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomeComponent } from './home/home.component';
import { UserCollectionComponent } from './user-collection/user-collection.component';
import { SetsContentComponent } from './sets-content/sets-content.component';
import { NoSetSelectedComponent } from './no-set-selected/no-set-selected.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { RechercheAvanceeComponent } from './recherche-avancee/recherche-avancee.component';
import { AddByCaptureComponent } from './add-by-capture/add-by-capture.component';
import { AddByFileComponent } from './add-by-file/add-by-file.component';

const routes: Routes = [
  { path: 'mcm', component: HomeComponent },
  { path: 'mcm/admin', component: AdminHomeComponent}, 
  { path: 'mcm/:id', component: UserCollectionComponent,
    children: [
      { path: '', component: NoSetSelectedComponent},
      { path: 'sets/:code', component: SetsContentComponent },
      { path: 'recherche-avancee', component: RechercheAvanceeComponent },
      { path: 'add-by-file', component: AddByFileComponent }
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
