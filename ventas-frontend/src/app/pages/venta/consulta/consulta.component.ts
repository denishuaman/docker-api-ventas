import { Component, OnInit, ViewChild } from '@angular/core';
import { Venta } from 'src/app/_model/venta';
import { VentaService } from 'src/app/_service/venta.service';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog } from '@angular/material';
import { VentaDialogoComponent } from '../venta-dialogo/venta-dialogo.component';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  nombresColumnas = ['idVenta', 'importe', 'fecha', 'acciones'];
  dataSource: MatTableDataSource<Venta>;
  venta: Venta[] = [];

  @ViewChild(MatPaginator, { static: true })
  paginator: MatPaginator;

  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  constructor(private ventaService: VentaService, private dialogo: MatDialog) { }

  ngOnInit() {
    this.ventaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  consultarDetalle(venta: Venta) {
    console.log('Venta seleccionada', venta);
    this.dialogo.open(VentaDialogoComponent, {
      data: venta,
      maxHeight: '500px'
    });
  }
}
