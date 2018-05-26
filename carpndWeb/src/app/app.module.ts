import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule,LOCALE_ID } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from "@angular/forms";

import { VARIABLES } from './configs/variables';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { UsersEditComponent } from './pages/users-edit/users-edit.component';
import { UsersListComponent } from './pages/users-list/users-list.component';
import { VehiclesEditComponent } from './pages/vehicles-edit/vehicles-edit.component';
import { VehiclesListComponent } from './pages/vehicles-list/vehicles-list.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { MapComponent } from './components/google/map/map.component';
import { HeaderComponent } from './components/navbar/header/header.component';
import { FooterComponent } from './components/navbar/footer/footer.component';
import { GenericRestService } from './services/generic/generic-rest.service';

// Routes Constants:
const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'users',
    component: UsersListComponent
  },
  {
    path: 'users/:id',
    component: UsersEditComponent
  },
  {
    path: 'vehicles',
    component: VehiclesListComponent
  },
  {
    path: 'vehicles/:id',
    component: VehiclesEditComponent
  },
  { 
    path: '',
    redirectTo: '',
    pathMatch: 'full'
  },
  { 
    path: '**', 
    component: PageNotFoundComponent 
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UsersListComponent,
    UsersEditComponent,
    VehiclesListComponent,
    VehiclesEditComponent,
    PageNotFoundComponent,
    MapComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [
    GenericRestService,
    { provide: LOCALE_ID, useValue: VARIABLES.getI18n() }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}