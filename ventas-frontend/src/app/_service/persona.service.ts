import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Persona } from '../_model/persona';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  cambioPersona = new Subject<Persona[]>();
  mensaje = new Subject<string>();

  urlWS: string = `${environment.HOST}/api/personas`;

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Persona[]>(`${this.urlWS}/listar`);
  }

  buscarPorDni(dni: string) {
    return this.http.get<Persona>(`${this.urlWS}/buscar/${dni}`);
  }

  registrar(persona: Persona) {
    return this.http.post(`${this.urlWS}/registrar`, persona);
  }

  modificar(persona: Persona) {
    return this.http.put(`${this.urlWS}/modificar`, persona);
  }

  eliminar(dni: string) {
    return this.http.delete(`${this.urlWS}/eliminar/${dni}`);
  }
}
