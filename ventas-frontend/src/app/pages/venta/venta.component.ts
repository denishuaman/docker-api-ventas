import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/_model/persona';
import { Producto } from 'src/app/_model/producto';
import { DetalleVenta } from 'src/app/_model/detalleVenta';
import { MatSnackBar } from '@angular/material';
import { PersonaService } from 'src/app/_service/persona.service';
import { ProductoService } from 'src/app/_service/producto.service';
import { Venta } from 'src/app/_model/venta';
import { VentaService } from 'src/app/_service/venta.service';

@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  venta: Venta;
  personas: Persona[] = [];
  productos: Producto[] = [];

  detalleVenta: DetalleVenta[] = [];
  cantidad: number;

  idPersonaSeleccionada: number;
  idProductoSeleccionado: number;

  constructor(private personaService: PersonaService,
    private productoService: ProductoService,
    private ventaService: VentaService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.cantidad = 1;
    this.listarPersonas();
    this.listarProductos();
  }

  listarPersonas() {
    this.personaService.listar().subscribe(data => {
      this.personas = data;
    });
  }

  listarProductos() {
    this.productoService.listar().subscribe(data => {
      this.productos = data;
    });
  }

  agregarDetalle() {
    console.log('Agregando detalle');
    if (this.cantidad > 0) {
      let det = new DetalleVenta();
      for (let i = 0; i < this.productos.length; i++) {
        if (this.productos[i].idProducto == this.idProductoSeleccionado) {
          det.producto = this.productos[i];
          break;
        }
      }
      det.cantidad = this.cantidad;
      this.detalleVenta.push(det);
      console.log('detalleVenta', this.detalleVenta);
      this.idProductoSeleccionado = 0;
      this.cantidad = 1;
    } else {
      this.snackBar.open('Debe indicar por lo menos un producto', 'Aviso', { duration: 3000 });
    }
  }

  registrarVenta() {
    console.log('Registrando venta');
    this.venta = new Venta();
    for (let i = 0; i < this.personas.length; i++) {
      if (this.idPersonaSeleccionada == this.personas[i].idPersona) {
        this.venta.persona = this.personas[i];
        break;
      }
    }
    this.venta.detalleVenta = this.detalleVenta;
    console.log('Venta a registrar', this.venta);
    this.ventaService.registrar(this.venta).subscribe(data => {
      console.log('Venta registrada', data);
      this.snackBar.open('Se registró la venta', 'Aviso', {duration: 3000});
      this.idPersonaSeleccionada = 0;
      this.idProductoSeleccionado = 0;
      this.venta = null;
      this.detalleVenta = [];
    }, error => {
      console.error('Error al registrar vebta', error);
      this.snackBar.open('Ocurrió un error al registrar la venta', 'Error', { duration: 5000 })
    });
  }
}
