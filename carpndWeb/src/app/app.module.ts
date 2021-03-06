import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule,HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { NgModule,LOCALE_ID } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { NgxPaginationModule } from 'ngx-pagination';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { OrderModule } from 'ngx-order-pipe'
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

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
import { BookingRequestService } from './services/booking/booking-request.service';

import { CallbackComponent } from './components/callback/callback.component';
import { LoginComponent } from './pages/login/login.component';
import { NewVehicleComponent } from './pages/new-vehicle/new-vehicle.component';
import { SearchVehicleComponent } from './pages/search-vehicle/search-vehicle.component';
import { NewPublicationComponent } from './pages/new-publication/new-publication.component';
import { PublicationListComponent } from './pages/publication-list/publication-list.component';
import { PublicationDetailsComponent } from './pages/publication-details/publication-details.component';
import { RequestDetailsComponent } from './pages/request-details/request-details.component';
import { CategoryPipe } from './pipes/category/category.pipe';
import { LocalityPipe } from './pipes/locality/locality.pipe';
import { MapEditComponent } from './components/map-edit/map-edit.component';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

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
    component: RequestDetailsComponent
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
    SearchVehicleComponent,
    NewPublicationComponent,
    PublicationListComponent,
    PublicationDetailsComponent,
    RequestDetailsComponent,
    CategoryPipe,
    LocalityPipe,
    MapEditComponent
  ],
  imports: [
    OrderModule,
    NgxPaginationModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
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
    BookingRequestService,
    { 
      provide: LOCALE_ID, useValue: localStorage.getItem('language')? 
                                      localStorage.getItem('language'): 
                                      'en-US' 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}