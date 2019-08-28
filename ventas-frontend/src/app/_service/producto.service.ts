import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Producto } from '../_model/producto';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  cambioProducto = new Subject<Producto[]>();
  mensaje = new Subject<string>();

  urlWS: string = environment.HOST_API_PRODUCTOS;

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Producto[]>(`${this.urlWS}/listar`);
  }

  buscarPorId(idProducto: number) {
    return this.http.get<Producto>(`${this.urlWS}/buscar/${idProducto}`);
  }

  registrar(producto: Producto) {
    console.log('producto a registrar', producto);
    return this.http.post(`${this.urlWS}/registrar`, producto);
  }

  modificar(producto: Producto) {
    return this.http.put(`${this.urlWS}/modificar`, producto);
  }

  eliminar(idProducto: number) {
    return this.http.delete(`${this.urlWS}/eliminar/${idProducto}`);
  }
}
