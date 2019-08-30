import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Venta } from '../_model/venta';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  mensaje = new Subject<string>();

  urlWs: string = environment.HOST_API_VENTAS;

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
}
