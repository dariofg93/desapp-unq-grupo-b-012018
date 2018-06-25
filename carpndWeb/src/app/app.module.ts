import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { NgModule,LOCALE_ID } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from "@angular/forms";
import {NgxPaginationModule} from 'ngx-pagination';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { UsersEditComponent } from './pages/users-edit/users-edit.component';
import { VehiclesEditComponent } from './pages/vehicles-edit/vehicles-edit.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { MapComponent } from './components/google/map/map.component';
import { HeaderComponent } from './components/navbar/header/header.component';
import { FooterComponent } from './components/navbar/footer/footer.component';
import { GenericRestService } from './services/generic/generic-rest.service';
import { AuthService } from './services/auth/auth.service';
import { UserService } from './services/user/user.service';
import { DateService } from './services/date/date.service';
import { PublicationService } from './services/publication/publication.service';
import { CallbackComponent } from './components/callback/callback.component';
import { LoginComponent } from './pages/login/login.component';
import { NewVehicleComponent } from './pages/new-vehicle/new-vehicle.component';
import { SeeRequestComponent } from './pages/see-request/see-request.component';
import { SearchVehicleComponent } from './pages/search-vehicle/search-vehicle.component';
import { NewPublicationComponent } from './pages/new-publication/new-publication.component';
import { PublicationListComponent } from './pages/publication-list/publication-list.component';
import { PublicationDetailsComponent } from './pages/publication-details/publication-details.component';

// Routes Constants:
const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'users/:id',
    component: UsersEditComponent
  },
  {
    path: 'searchVehicle',
    component: SearchVehicleComponent
  },
  {
    path: 'vehicles/new',
    component: NewVehicleComponent
  },
  {
    path: 'publications',
    component: PublicationListComponent
  },
  {
    path: 'publications/new',
    component: NewPublicationComponent
  },
  {
    path: 'publications/:id',
    component: PublicationDetailsComponent
  },
  {
    path: 'vehicles/:id',
    component: VehiclesEditComponent
  },
  {
    path: 'requests/:id',
    component: SeeRequestComponent
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
    UsersEditComponent,
    VehiclesEditComponent,
    PageNotFoundComponent,
    MapComponent,
    HeaderComponent,
    FooterComponent,
    CallbackComponent,
    LoginComponent,
    NewVehicleComponent,
    SeeRequestComponent,
    SearchVehicleComponent,
    NewPublicationComponent,
    PublicationListComponent,
    PublicationDetailsComponent
  ],
  imports: [
    NgxPaginationModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [
    DatePipe,
    GenericRestService,
    AuthService,
    UserService,
    DateService,
    PublicationService,
    { 
      provide: LOCALE_ID, useValue: localStorage.getItem('language')? 
                                      localStorage.getItem('language'): 
                                      'en-US' 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}