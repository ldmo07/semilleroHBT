import { Component, OnInit } from '@angular/core';
import { EjemploService } from '../../services/ejemplo.service';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private router: Router, private ejemploservicio: EjemploService) { }

  ngOnInit() {
  }

}
