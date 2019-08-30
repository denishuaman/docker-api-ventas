import { Component, OnInit, Inject } from '@angular/core';
import { Venta } from 'src/app/_model/venta';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-venta-dialogo',
  templateUrl: './venta-dialogo.component.html',
  styleUrls: ['./venta-dialogo.component.css']
})
export class VentaDialogoComponent implements OnInit {

  venta: Venta;

  constructor(private refDialog: MatDialogRef<VentaDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Venta) { }

  ngOnInit() {
    console.log('data recibida', this.data);
    this.venta = new Venta();
    this.venta.persona = this.data.persona;
    this.venta.detalleVenta = this.data.detalleVenta;
  }

  cerrar() {
    this.refDialog.close();
  }
}
