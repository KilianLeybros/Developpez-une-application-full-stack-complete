import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakeHeaderComponent } from './fake-header.component';

describe('FakeHeaderComponent', () => {
  let component: FakeHeaderComponent;
  let fixture: ComponentFixture<FakeHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FakeHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FakeHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
