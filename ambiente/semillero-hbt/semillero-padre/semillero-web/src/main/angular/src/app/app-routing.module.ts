import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import {CrudComicComponent} from './semillero/componentes/crud-comic/crud-comic.component';
import { from } from 'rxjs';
import { GestionarPersonaComponent } from './semillero/componentes/gestionar-persona/gestionar-persona.component';
import { VentaComponent } from './semillero/componentes/venta/venta.component';
const routes: Routes = [
  { path: 'gestionar-comic', component: GestionarComicComponent },
  { path: 'bienvenida', component: BienvenidaComponent, data : null },
  { path: 'crud', component: CrudComicComponent, data : null },
  { path: 'persona', component: GestionarPersonaComponent, data : null },
  { path: 'venta', component: VentaComponent, data : null }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
