
import { ComicDTO } from '../../dto/comic.dto';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EjemploService } from '../../services/ejemplo.service';
import { error } from 'protractor';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})
export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm: FormGroup;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array<ComicDTO>;

    public idComic: number = 0;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
   * Atributo bandera que indica si se debe editar 
   */
    public debeEditar: boolean;

    /*
        Variable de tipo comic que captura la informacion de el comic a elimia
    */
    public datoEliminado: ComicDTO;

    /* Variable que ocultara el mensaje de que se elimino un comic*/
    public mostrarMensaje: Boolean;

    /* Variable captura mensaje devuelto de java al registrar un comic*/
    public MensajeExito: String;

    /* Variable que ocultara el mensaje de que se creo comic*/
    public MostrarAlerta: boolean;

    /**
     * @description Este es el constructor del componente GestionarComicComponent
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     * @modificado por LUIS DAVID MERCADO ORTEGA
     */

    //Se inyecta en el constructor el  FormBuilder,el ROUTER Y los Servicos
    constructor(private fb: FormBuilder,
        private router: Router, private ejemploservicio: EjemploService) {
        this.gestionarComicForm = this.fb.group({
            nombre: [null, Validators.required],
            editorial: [null],
            tematica: [null],
            coleccion: [null],
            numeroPaginas: [null],
            precio: [null],
            autores: [null],
            color: [null]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    ngOnInit(): void {
        console.log("Ingreso al al evento oninit");
        this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();
        this.consultarComicSer();
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actulizar un comic
     */
    public crearActualizarComic(): void {
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
        if (this.debeEditar) {
            console.log("debe Editar siii");
           /* this.comic = new ComicDTO();
            
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.ejemploservicio.editarComic(this.comic).subscribe(resultado => {
                this.MostrarAlerta = true
                this.MensajeExito = resultado.mensajeEjecucion;
                if (resultado.exitoso) {
                    this.limpiarFormulario();
                    this.consultarComicSer();
                }
            },
                error => {
                    console.log("Error en la suscripcion al servicio al Momento de Registrar un comic " + error)
                });*/

        } else {
            // this.idComic++;
            this.comic = new ComicDTO();
            //this.comic.id = this.idComic + "";
            this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
            this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
            this.comic.tematica = this.gestionarComicForm.controls.tematica.value;
            this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
            this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
            this.comic.precio = this.gestionarComicForm.controls.precio.value;
            this.comic.autores = this.gestionarComicForm.controls.autores.value;
            this.comic.color = this.gestionarComicForm.controls.color.value;
            this.comic.cantidad = 4;
            this.comic.estado = "ACTIVO";
            //INICIO LA SUSCRIPCION AL SERVICIO CREAR COMIC
            this.ejemploservicio.crearComic(this.comic).subscribe(resultado => {
                this.MostrarAlerta = true;
                //mensajeEjecucion es una variable declarada en java
                this.MensajeExito = resultado.mensajeEjecucion;
                //exitoso  es una variable booleana declarada en java
                if (resultado.exitoso) {
                    this.limpiarFormulario();
                    this.consultarComicSer();
                }
            },
                error => {
                    console.log("Error en la suscripcion al servicio al Momento de Registrar un comic " + error)
                });
           
        }

    }

    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComic(posicion: number): void {
        let comic = this.listaComics[posicion];
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre.disable();
        this.gestionarComicForm.controls.editorial.disable();
        this.gestionarComicForm.controls.tematica.disable();
        this.gestionarComicForm.controls.coleccion.disable();
        this.gestionarComicForm.controls.numeroPaginas.disable();
        this.gestionarComicForm.controls.precio.disable();
        this.gestionarComicForm.controls.autores.disable();
        this.gestionarComicForm.controls.color.disable();
        //        this.gestionarComicForm.controls.color.enable(); para habilitar el campo

    }

    public editarComic(posicion: number): void {
        let comic = this.listaComics[posicion];
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre;
        this.gestionarComicForm.controls.editorial;
        this.gestionarComicForm.controls.tematica;
        this.gestionarComicForm.controls.coleccion;
        this.gestionarComicForm.controls.numeroPaginas;
        this.gestionarComicForm.controls.precio;
        this.gestionarComicForm.controls.autores;
        this.gestionarComicForm.controls.color;
        //this.listaComics.splice(posicion, 1);
        //this.listaComics[posicion]=comic;
        this.debeEditar = true;
        this.idComic=Number(this.listaComics[posicion].id);
    }



    private limpiarFormulario(): void {
        this.submitted = false;
        this.gestionarComicForm.controls.nombre.setValue(null);
        this.gestionarComicForm.controls.editorial.setValue(null);
        this.gestionarComicForm.controls.tematica.setValue(null);
        this.gestionarComicForm.controls.coleccion.setValue(null);
        this.gestionarComicForm.controls.numeroPaginas.setValue(null);
        this.gestionarComicForm.controls.precio.setValue(null);
        this.gestionarComicForm.controls.autores.setValue(null);
        this.gestionarComicForm.controls.color.setValue(null);
    }

    //Metodo donde Invocaamos el Servicio de eliminar comic y nos Suscrbimos a este servicio
    public eliminarComicSer(idComic: number) {
        let id = Number(this.listaComics[idComic].id);
        console.log("Quiere borrar el id " + id);
        this.ejemploservicio.eliminarComic(id).subscribe(res => {
            //listaComics es el nombre del nfor del html para recorrer la tabla
            this.consultarComicSer();
            console.log("exito al ejecutar la suscripcion al metodo eliminar comic");
        }, error => {
            console.log("error al suscrbir el metodo eliminar comic " + error);
        });
    }

    //Metodo donde Invocaamos el Servicio de consultar comic y nos Suscrbimos a este servicio
    private consultarComicSer() {
        this.ejemploservicio.consultarComics().subscribe(listaCo => {
            //listaComics es el nombre del nfor del html para recorrer la tabla
            this.listaComics = listaCo;
            console.log("exito al ejecutar la suscripcion al metodo consultar comic");
        }, error => {
            console.log("error al suscrbir el metodo consultar comic " + error);
        });
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() {
        return this.gestionarComicForm.controls;
    }
}