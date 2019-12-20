import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EjemploService } from '../../services/ejemplo.service';
import { PersonaDTO } from '../../dto/persona.dto';
import { error } from 'protractor';

@Component({
  selector: 'app-gestionar-persona',
  templateUrl: './gestionar-persona.component.html',
  styleUrls: ['./gestionar-persona.component.css']
})
export class GestionarPersonaComponent implements OnInit {

  /**
    * Atributo que contendra la lista de comics creados
    */
  public listaPersonas: Array<PersonaDTO>;

  //Variable Form Group que asocia al Formulario reactivo
  public gestionarPersonaForm: FormGroup;

  // Atributo que indica si se envio a validar el formulario
  public submitted: boolean;

  public debeEditar: boolean

  //Atributo tipo Persona para hacer Mapero
  public persona: PersonaDTO;

  public MostrarAlerta: boolean;
  //se le Inyecta al constructor la variables de tipo FormBuilder y Router 

  /* Variable captura mensaje devuelto de java al registrar un comic*/
  public MensajeExito: String;
  constructor(private fb: FormBuilder,
    private router: Router, private ejemploservicio: EjemploService) {
    this.gestionarPersonaForm = this.fb.group({
      ndocumento: [null, Validators.required],
      nombre: [null],
      tipoDoc: [null],
      FechaNaci: [null],
    });

  }

  ngOnInit() {
    this.consultarPersonaSer();
  }

  public crearActualizarPersona() {
    this.submitted = true;
    if (this.gestionarPersonaForm.invalid) {
      return;
    }
    if (this.debeEditar) {

    } else {
      // this.idComic++;
      this.persona = new PersonaDTO();

      this.persona.nombre = this.gestionarPersonaForm.controls.nombre.value;
      this.persona.ndocumento = this.gestionarPersonaForm.controls.ndocumento.value;
      this.persona.tipoDoc = this.gestionarPersonaForm.controls.tipoDoc.value;
      this.persona.FechaNaci = this.gestionarPersonaForm.controls.FechaNaci.value;

      console.log("Fecha es " + this.persona.FechaNaci);
      this.ejemploservicio.crearPersona(this.persona).subscribe(resu => {
        this.MostrarAlerta = true;
        //mensajeEjecucion es una variable declarada en java
        this.MensajeExito = resu.mensajeEjecucion;
        
        if (resu.exitoso) {
          this.limpiarFormulario();
          this.consultarPersonaSer();
          console.log("exito registrando");
      }
      }, error => {
        console.log("fallo registrando" + error);
      });

    }
  }

  public limpiarFormulario() {
    this.submitted = false;
    this.gestionarPersonaForm.controls.nombre.setValue(null);
    this.gestionarPersonaForm.controls.ndocumento.setValue(null);
    this.gestionarPersonaForm.controls.tipoDoc.setValue(null);
    this.gestionarPersonaForm.controls.FechaNaci.setValue(null);
  }

  public consultarPersonaSer() {
    this.ejemploservicio.consultarPersona().subscribe(listaPe => {
      //lista es el nombre del nfor del html para recorrer la tabla
      this.listaPersonas = listaPe;
      console.log("exito al ejecutar la suscripcion al metodo consultar Personas");
    }, error => {
      console.log("error al suscrbir el metodo consultar Personas " + error);
    });
  }

  public editarpersona(ndocumento: String) {

  }

  get f() {
    return this.gestionarPersonaForm.controls;
  }

}
