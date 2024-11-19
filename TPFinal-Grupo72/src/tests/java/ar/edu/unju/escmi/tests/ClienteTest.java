package ar.edu.unju.escmi.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.entities.Cliente;

class ClienteTest {
	private IClienteDao clienteDao;

    @BeforeEach
    void setUp() {
        clienteDao = new ClienteDaoImp();
    }

    @Test
    void testDarDeAltaYModificarCliente() {
        Cliente nuevoCliente = new Cliente(44055521, "Lionel", "Avila", "Monterrico", "388111111");
        clienteDao.guardarCliente(nuevoCliente);

        Cliente clienteConsultado = clienteDao.consultarCliente(44055521);
        assertNotNull(clienteConsultado, "El cliente debería haberse dado de alta correctamente");
        assertEquals("Lionel", clienteConsultado.getNombre(), "El nombre del cliente debería coincidir");

        clienteConsultado.setNombre("Gabriel");
        clienteConsultado.setApellido("Santos");
        clienteDao.modificarCliente(clienteConsultado);

        Cliente clienteEsperado = new Cliente(440552121, "Gabriel", "Santos", "Palpala", "388222222");

        Cliente clienteModificado = clienteDao.consultarCliente(440552121);

        assertEquals(clienteEsperado.getNombre(), clienteModificado.getNombre(), "El nombre debería haberse actualizado correctamente");
        assertEquals(clienteEsperado.getApellido(), clienteModificado.getApellido(), "El apellido debería haberse actualizado correctamente");
    }
}
