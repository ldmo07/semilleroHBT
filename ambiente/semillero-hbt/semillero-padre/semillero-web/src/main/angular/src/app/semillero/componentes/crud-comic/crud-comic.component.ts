import { Component, OnInit } from '@angular/core';
import { ComicDTO } from '../../dto/comic.dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-crud-comic',
  templateUrl: './crud-comic.component.html',
  styleUrls: ['./crud-comic.component.css']
})
export class CrudComicComponent implements OnInit {

  public crudComicForm : FormGroup;

  //public mostrarMensaje:Boolean ;
  //public comic: ComicDTO;
  public listaComics : Array<ComicDTO>;
  public idComic : number = 0;
  public submitted : boolean;
  public datoEliminado: ComicDTO;

  constructor(private fb : FormBuilder,
    private router : Router,private comicRecibido:ActivatedRoute) {
    this.crudComicForm = this.fb.group({
        nombre : [null, Validators.required],
        editorial : [null],
        tematica : [null],
        coleccion : [null],
        numeroPaginas : [null],
        precio : [null],
        autores : [null],
        color : [null]
    });
}

  ngOnInit() {
    console.log("Ingreso al al evento oninit");
       /* this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();
        this.mostrarMensaje=false;*/
        let comic=this.comicRecibido.snapshot.params;
        this.consultarComic(comic);
        
  }
  
  /*public crearActualizarComic() : void {
    this.submitted = true;
    if(this.crudComicForm.invalid) {
        return;
    }
    this.idComic++;
    this.comic = new ComicDTO();
    this.comic.id = this.idComic + "";
    this.comic.nombre = this.crudComicForm.controls.nombre.value;
    this.comic.editorial = this.crudComicForm.controls.editorial.value;
    this.comic.tematica = this.crudComicForm.controls.tematica.value;
    this.comic.coleccion = this.crudComicForm.controls.coleccion.value;
    this.comic.numeroPaginas = this.crudComicForm.controls.numeroPaginas.value;
    this.comic.precio = this.crudComicForm.controls.precio.value;
    this.comic.autores = this.crudComicForm.controls.autores.value;
    this.comic.color = this.crudComicForm.controls.color.value;
    this.listaComics.push(this.comic);
    this.limpiarFormulario();
    
}*/

/**
 * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
 * @param posicion en la lista del comic seleccionado
 */
public consultarComic(comic : any) : void {
   // let comic = this.listaComics[posicion];
    this.crudComicForm.controls.nombre.setValue(comic.nombre);
    this.crudComicForm.controls.editorial.setValue(comic.editorial);
    this.crudComicForm.controls.tematica.setValue(comic.tematica);
    this.crudComicForm.controls.coleccion.setValue(comic.coleccion);
    this.crudComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
    this.crudComicForm.controls.precio.setValue(comic.precio);
    this.crudComicForm.controls.autores.setValue(comic.autores);
    this.crudComicForm.controls.color.setValue(comic.color);
    this.crudComicForm.controls.nombre.disable();
    this.crudComicForm.controls.editorial.disable();
    this.crudComicForm.controls.tematica.disable();
    this.crudComicForm.controls.coleccion.disable();
    this.crudComicForm.controls.numeroPaginas.disable();
    this.crudComicForm.controls.precio.disable();
    this.crudComicForm.controls.autores.disable();
    this.crudComicForm.controls.color.disable();
//        this.crudComicForm.controls.color.enable(); para habilitar el campo

}

/*public editarComic(posicion : number) : void {
  let comic = this.listaComics[posicion];
  this.crudComicForm.controls.nombre.setValue(comic.nombre);
  this.crudComicForm.controls.editorial.setValue(comic.editorial);
  this.crudComicForm.controls.tematica.setValue(comic.tematica);
  this.crudComicForm.controls.coleccion.setValue(comic.coleccion);
  this.crudComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
  this.crudComicForm.controls.precio.setValue(comic.precio);
  this.crudComicForm.controls.autores.setValue(comic.autores);
  this.crudComicForm.controls.color.setValue(comic.color);
  this.crudComicForm.controls.nombre;
  this.crudComicForm.controls.editorial;
  this.crudComicForm.controls.tematica;
  this.crudComicForm.controls.coleccion;
  this.crudComicForm.controls.numeroPaginas;
  this.crudComicForm.controls.precio;
  this.crudComicForm.controls.autores;
  this.crudComicForm.controls.color;
  //this.listaComics.splice(posicion, 1);
  //this.listaComics[posicion]=comic;
}*/

/*private limpiarFormulario() : void {
    this.submitted = false;
    this.crudComicForm.controls.nombre.setValue(null);
    this.crudComicForm.controls.editorial.setValue(null);
    this.crudComicForm.controls.tematica.setValue(null);
    this.crudComicForm.controls.coleccion.setValue(null);
    this.crudComicForm.controls.numeroPaginas.setValue(null);
    this.crudComicForm.controls.precio.setValue(null);
    this.crudComicForm.controls.autores.setValue(null);
    this.crudComicForm.controls.color.setValue(null);
}*/

/*private eliminarComic(idEliminar:number){
  this.datoEliminado = this.listaComics[idEliminar];
  this.listaComics.splice(idEliminar, 1);
 // this.listaString.splice(idEliminar, 1);
  console.log(this.listaComics, this.listaComics[1]);
}*/

/**
 * @description Metodo que obtiene los controles y sus propiedades
 * @author Diego Fernando Alvarez Silva
 */
get f() { 
    return this.crudComicForm.controls;
}

}
