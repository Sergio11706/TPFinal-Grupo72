package ar.edu.unju.escmi.dao;

import ar.edu.unju.escmi.entities.ServicioAdicional;

public interface IServicioAdicionalDao {
	public void guardarServicio(ServicioAdicional servicio);
	public ServicioAdicional consultarServicio(long idServicio);
}
