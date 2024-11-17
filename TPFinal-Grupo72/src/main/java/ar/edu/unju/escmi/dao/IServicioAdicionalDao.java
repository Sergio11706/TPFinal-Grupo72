package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.ServicioAdicional;

public interface IServicioAdicionalDao {
	public void mostrarLosServiciosAdicionales();
	public ServicioAdicional consultarServicioAdicional(long idServicioAdicional);
}
