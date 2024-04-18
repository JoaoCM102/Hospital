import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProxCitaComponent } from './prox-cita.component';

describe('ProxCitaComponent', () => {
  let component: ProxCitaComponent;
  let fixture: ComponentFixture<ProxCitaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProxCitaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProxCitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
