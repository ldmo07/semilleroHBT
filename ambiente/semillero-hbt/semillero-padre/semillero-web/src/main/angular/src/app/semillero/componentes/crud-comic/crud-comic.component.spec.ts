import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudComicComponent } from './crud-comic.component';

describe('CrudComicComponent', () => {
  let component: CrudComicComponent;
  let fixture: ComponentFixture<CrudComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrudComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
