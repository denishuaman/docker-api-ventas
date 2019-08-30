import { Component, OnInit, ViewChild } from '@angular/core';
import { Venta } from 'src/app/_model/venta';
import { VentaService } from 'src/app/_service/venta.service';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  nombresColumnas = ['idVenta', 'importe', 'fecha'];
  dataSource: MatTableDataSource<Venta>;
  venta: Venta[] = [];

  @ViewChild(MatPaginator, { static: true })
  paginator: MatPaginator;

  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  constructor(private ventaService: VentaService) { }

  ngOnInit() {
    this.ventaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  consultarDetalle() {
    
  }
}
