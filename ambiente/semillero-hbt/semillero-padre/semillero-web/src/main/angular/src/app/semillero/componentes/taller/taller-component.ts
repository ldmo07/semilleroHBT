import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {EjemploService} from '../../services/ejemplo.service';
import { ComicDTO } from '../../dto/comic.dto';

/**
 * @description Componente Para la Presentacion del Nombre 
 * 
 * @author Luis David Mercado Ortega <luisdavidmercadoortega@gmail.com>
 */
@Component({
  /**
 * @description Declaramos el nombre del selector que se encargara de asociar al ts con el html 
 * y damos la ruta del componente html a visualizar
 * 
 */
  selector: 'taller',
  templateUrl: './taller-component.html',
})
export class TallerComponent implements OnInit {
  
/**
 * @description variable que me capatura el Nombre para luego mostrarlo en el html 
 * concatenandolo a un texto
 */
  public  Nombre : string ;

  /**
 * @description variable que me capatura la direccion para luego mostrarlo en el html 
 * concatenandolo a un texto
 */
  public  Direccion : string ;

  public  Saludo : string ;
  
  constructor(private router : Router, private activatedRoute: ActivatedRoute, private ejemploService: EjemploService) {
    console.log("entro al constructor del componente bienvenida");
    
  }

  ngOnInit(): void {
    
    this.Direccion="Monteria Vereda Aguas Negras";

    this.Nombre="Luis David Mercado Ortega";

    this.Saludo="Hola Diego Fernando Alvarez Silva";
   
  }


}