package com.dgha.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class FiltroConsultaVentaDto {

	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fechaInicial;
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fechaFinal;

	public LocalDateTime getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(LocalDateTime fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	@Override
	public String toString() {
		return "FiltroConsultaVentaDto [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + "]";
	}

}
