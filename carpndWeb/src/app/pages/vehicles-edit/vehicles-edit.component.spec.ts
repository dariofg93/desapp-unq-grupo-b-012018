import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesEditComponent } from './vehicles-edit.component';

describe('VehiclesEditComponent', () => {
  let component: VehiclesEditComponent;
  let fixture: ComponentFixture<VehiclesEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclesEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclesEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
