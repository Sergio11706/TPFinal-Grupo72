package ar.edu.unju.escmi.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.entities.Cliente;
import ar.edu.unju.escmi.entities.Reserva;
import ar.edu.unju.escmi.entities.Salon;

public class ReservaPagoTest {
	private Reserva reserva;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente(12345678, "Ana", "Lopez", "Centro", "987654321");
        Salon salon = new Salon("Salon Esmeralda", 20, false, 10000);
        short hi = 10;
        short hf = 16;
        reserva = new Reserva(cliente, salon, LocalDate.now(), hi, hf, 0.0, 5000, false);
    }

    @Test
    void testCalculoPagoPendiente() {
        double montoTotal = reserva.calcularMontoTotal();
        assertEquals(10000, montoTotal, "El monto total de la reserva debería ser 10000");

        double pagoAdelantado = reserva.getPagoAdelantado();
        assertEquals(5000, pagoAdelantado, "El pago adelantado debería ser 5000");

        double montoPendiente = reserva.calcularPagoPendiente();
        assertEquals(5000, montoPendiente, "El monto pendiente debería ser 5000");

        reserva.setMontoPagado(montoPendiente);
        reserva.calcularPagoPendiente();

        assertTrue(reserva.isCancelado(), "La reserva debería estar marcada como cancelada después de pagar el monto total");
        assertEquals(10000, reserva.getMontoPagado(), "El monto pagado debería coincidir con el monto total después del pago completo");
    }
}
