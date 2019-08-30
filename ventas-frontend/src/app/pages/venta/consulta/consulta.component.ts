import { Component, OnInit, ViewChild } from '@angular/core';
import { Venta } from 'src/app/_model/venta';
import { VentaService } from 'src/app/_service/venta.service';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog, MatSnackBar } from '@angular/material';
import { VentaDialogoComponent } from '../venta-dialogo/venta-dialogo.component';
import { FormGroup, FormControl } from '@angular/forms';
import { FiltroConsultaVentaDto } from 'src/app/_model/filtroConsultaVentaDto';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  form: FormGroup;

  nombresColumnas = ['idVenta', 'importe', 'fecha', 'acciones'];
  dataSource: MatTableDataSource<Venta>;
  venta: Venta[] = [];

  @ViewChild(MatPaginator, { static: true })
  paginator: MatPaginator;

  @ViewChild(MatSort, { static: true })
  sort: MatSort;

  constructor(private ventaService: VentaService, private dialogo: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = new FormGroup({
      'fechaInicial': new FormControl(),
      'fechaFinal': new FormControl()
    })
    this.ventaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  buscar() {
    let filtro = new FiltroConsultaVentaDto();
    filtro.fechaInicial = this.form.value['fechaInicial'];
    filtro.fechaFinal = this.form.value['fechaFinal'];
    if (!filtro.fechaFinal || !filtro.fechaFinal) {
      this.snackBar.open('Debe seleccionar ambas fechas', 'Aviso', { duration: 3000 });
      return;
    }
    console.log('FiltroConsultaVentaDto', filtro);
    this.ventaService.consultarVentasPorFecha(filtro).subscribe(data => {
      this.dataSource = new MatTableDataSource(data.ventas);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }, error => {
      console.error(error);
      this.snackBar.open(error.error.mensaje, 'Aviso', { duration: 3000 });
    })
  }

  buscarTodo() {
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
