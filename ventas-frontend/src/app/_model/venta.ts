import { Persona } from './persona';
import { DetalleVenta } from './detalleVenta';

export class Venta {
    idVenta: number;
    importe: number;
    persona: Persona;
    detalleVenta: DetalleVenta[];
}