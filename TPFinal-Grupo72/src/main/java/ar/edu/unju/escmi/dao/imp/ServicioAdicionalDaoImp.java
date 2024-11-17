package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.entities.ServicioAdicional;

public class ServicioAdicionalDaoImp implements IServicioAdicionalDao {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void mostrarLosServiciosAdicionales() {
		Query query = manager.createQuery("SELECT e FROM Servicios s",ServicioAdicional.class);
		@SuppressWarnings("unchecked")
		List<ServicioAdicional> servicios = query.getResultList();
		for(ServicioAdicional servicio : servicios) {
			servicio.mostrarDatos();
		}	
	}

	@Override
	public ServicioAdicional consultarServicioAdicional(long idServicioAdicional) {
		return manager.find(ServicioAdicional.class, idServicioAdicional);
	}
}
