package ar.edu.unju.escmi.dao.imp;

import javax.persistence.EntityManager;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.entities.ServicioAdicional;
import javax.persistence.Query;
import ar.edu.unju.escmi.dao.ISalonDao;
import ar.edu.unju.escmi.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.entities.Salon;

public class ServicioAdicionalDaoImp implements IServicioAdicionalDao {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
    
    @Override
    public void guardarServicio(ServicioAdicional servicio) {
        try {
            manager.getTransaction().begin();
            manager.merge(servicio);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction() != null) {
                manager.getTransaction().rollback();
            }
            System.out.println("Error al guardar el servicio adicional: " + e.getMessage());
        }
    }

    @Override
    public ServicioAdicional consultarServicio(long idServicio) {
        return manager.find(ServicioAdicional.class, idServicio);
    }
}

