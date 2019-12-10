import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { EjemploService } from '../../services/ejemplo.service';
import { ComicDTO } from '../../dto/comic.dto';
import { LibroDTO } from '../../dto/libro.dto';
import { JsonPipe } from '@angular/common';

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
  public Nombre: string;

  /**
 * @description variable que me capatura la direccion para luego mostrarlo en el html 
 * concatenandolo a un texto
 */
  public Direccion: string;

  public Saludo: string;

  //variable que me alamacenara la conversion del array LibroDto a un String 
  public listaString: Array<string>;

  //variable que capturara un el Objeto LibroDTO a eliminar del array para luego ser mostrado en la vista html
  public datoEliminado: LibroDTO;

  //array de tipo LibroDto que almacenara un objeto de tipo LibroDto
  public libros: Array<LibroDTO>;

  //variable que me validara si muestro un mensaje o no con el ngiF al momento de presionar un boton
  public mostrarMensaje:Boolean ;

  constructor(private router : Router, private activatedRoute: ActivatedRoute, private ejemploService: EjemploService) {
    console.log("entro al constructor del componente bienvenida");
    
  }
  /*constructor() {

  }*/

  ngOnInit(): void {


    /**
* @description Asignamos los valores a las variables para que se llenen cuando se ejecute
* el metodo abstracto de la interfaz OnInit y se muestren el componente html
*/

    this.Direccion = "Monteria Vereda Aguas Negras";

    this.Nombre = "Luis David Mercado Ortega";

    this.Saludo = "Hola Diego Fernando Alvarez Silva";

    //Asignamos un false para que no se muestre inicialmente con el ngIf al momento de presionar el boton eliminar
    this.mostrarMensaje=false;

    //se Crea un Array de tipo LibroDTO para ser llenado con un Objeto del mismo tipo
    this.libros = new Array<LibroDTO>();

    //LLenado del Array tipo LibroDTO creando Objetos de tipo LibroDTO
    this.libros = [this.llenarObjeto(0, "Batman", "Marvel", "Guerra", 200, 500, "juan perez", new Date('2018-02-05'), "activo", false),
    this.llenarObjeto(1, "Super Man", "DC", "Ficcion", 200, 900, "pirlo", new Date('2019-02-05'), "Activo", true),
    this.llenarObjeto(2, "Guerra Galaxia", "fox", "espacial", 250, 1000, "oto", new Date('2019-08-05'), "viejo", "negro"),
    this.llenarObjeto(3, "libro4", "Marvel", "Drama", 19, 80, "juan", new Date('2019-08-05'), "viejo", "negro"),
    this.llenarObjeto(4, "libro5", "Desconocido", "cine", 80, 380, "megan", new Date('2019-08-05'), "viejo", "negro")
    ];


    console.log(this.libros, JSON.stringify(this.libros))

    //se le Asigna a la variable listaString un array de tipo string retornado por la funcion libroJsontoString()
    this.listaString = this.libroJsontoString();

    //this.eliminarObjeto();


  }

  /*@
    Metodo que se encargara de recibir los valores de la clase libro.dto y retornara un objeto de ese tipo
    para ser asignado en un array
  */
  private llenarObjeto(id: number, nombre: string, editorial: string, tematica: string, numeroPaginas: number,
    precio: number, autores: string, fechaVenta: Date, estado: string, color: any): LibroDTO {
    
    //Variable Local para hacer el mapeo o llenado al objeto LibroDTO
    let obj = new LibroDTO();
    obj.nombre = nombre;
    obj.id = id;
    obj.editorial = editorial;
    obj.tematica = tematica;
    obj.numeroPaginas = numeroPaginas;
    obj.precio = precio;
    obj.autores = autores;
    obj.fechaVenta = fechaVenta;
    obj.estado = estado;
    obj.color = color;
    return obj;

  }
  /*@descripcion metodo que se encarga de eliminar un elemento del array segun 
  una posicion recibida por parametro
  */
  private eliminarObjeto(idEliminar:number){
    this.datoEliminado = this.libros[idEliminar];
    this.libros.splice(idEliminar, 1);
    this.listaString.splice(idEliminar, 1);
    console.log(this.libros, this.libros[1]);
  }

    /*@descripcion
   Metodo encargado de convertir un array a un String con la funcion JSON.stringify
   */
  private libroJsontoString(): Array<string> {
    let lista:string[]=[];
    this.libros.forEach(valor =>{lista.push(JSON.stringify(valor))});
    return lista;
  }

}