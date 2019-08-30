import { PersonaComponent } from './pages/persona/persona.component';
import { ProductoComponent } from './pages/producto/producto.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductoEdicionComponent } from './pages/producto/producto-edicion/producto-edicion.component';
import { VentaComponent } from './pages/venta/venta.component';
import { ConsultaComponent } from './pages/venta/consulta/consulta.component';


const routes: Routes = [
  {path: 'producto', component: ProductoComponent, children: [
    {path: 'nuevo', component: ProductoEdicionComponent},
    {path: 'edicion/:idProducto', component: ProductoEdicionComponent}
  ]},
  {path: 'persona', component: PersonaComponent},
  {path: 'venta', component: VentaComponent},
  {path: 'consulta', component: ConsultaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
