import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Venta } from '../_model/venta';
import { FiltroConsultaVentaDto } from '../_model/filtroConsultaVentaDto';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  mensaje = new Subject<string>();

  urlWs: string = `${environment.HOST}/api/ventas`;

  constructor(private http: HttpClient) { }

  registrar(venta: Venta) {
    return this.http.post(`${this.urlWs}/registrar`, venta);
  }

  listar() {
    return this.http.get<Venta[]>(`${this.urlWs}/listar`);
  }

  buscarPorId(idVenta: number) {
    return this.http.get<Venta>(`${this.urlWs}/buscar/${idVenta}`);
  }

  consultarVentasPorFecha(filtro: FiltroConsultaVentaDto) {
    return this.http.post<any>(`${this.urlWs}/consultar-por-fecha`, filtro);
  }
}
