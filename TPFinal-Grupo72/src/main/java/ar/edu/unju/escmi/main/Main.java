package ar.edu.unju.escmi.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.entities.*;
import ar.edu.unju.escmi.dao.imp.*;

public class Main {

		public static List<Salon> precargarSalones() {
		
	    List<Salon> salones = new ArrayList<>();
	    
	    salones.add(new Salon("Salón Cosmos", 60, false, 50000, null));
	    salones.add(new Salon("Salón Esmeralda", 20, false, 20000, null));
	    salones.add(new Salon("Salón Galaxy", 100, true, 80000, null));
	    
	    return salones;
	}
	
	 public static List<ServicioAdicional> precargarServicios() {
	        List<ServicioAdicional> servicios = new ArrayList<>();

	    servicios.add(new ServicioAdicional("Cámara 360", 15000, null));
	    servicios.add(new ServicioAdicional("Cabina de fotos", 10000, null));
	    servicios.add(new ServicioAdicional("Filmación", 20000, null));
	    servicios.add(new ServicioAdicional("Pintacaritas", 7500, null));

	        return servicios;
	    }
	 
	private static ClienteDaoImp clienteDaoImp = new ClienteDaoImp();
	private static ReservaDaoImp reservaDaoImp = new ReservaDaoImp();
	private static SalonDaoImp salonDaoImp = new SalonDaoImp();
	private static ServicioAdicionalDaoImp servicioAdicionalDaoImp = new ServicioAdicionalDaoImp();


	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean band=true;
		precargarSalones();
		precargarServicios();
		do {
			System.out.println("*****MENU*****");
			System.out.println("1-Alta de cliente");
			System.out.println("2-Consultar cliente");
			System.out.println("3-Modificar cliente");
			System.out.println("4-Realizar pago");
			System.out.println("5-Realizar reserva");
			System.out.println("6-Consultar todas las reservas");
			System.out.println("7-Consultar una reserva");
			System.out.println("8-Consultar Salones");
			System.out.println("9-Consultar servicios adicionales");
			System.out.println("10-Salir");
			System.out.println("***************");
			
			System.out.print("Ingrese una opcion: ");
			String op = sc.nextLine();
			
			switch(op) {
			
			case "1":
				altaCliente(sc);
			break;
			
			case "2":
				System.out.println("\nIngrese el DNI del cliente: ");
				long id = sc.nextLong();
				consultarCliente(id);
			break;
			
			case "3":
				modificarCliente(sc);
			break;
			
			case "4":
				realizarPago(sc);
			break;
			
			case "5":
				realizarReserva(sc);
			break;
			
			case "6":
				consultarReservas();
			break;
			
			case "7":
				consultarUnaReserva();
			break;
			
			case "8":
				consultarSalones();
			break;
			
			case "9":
				consultarServicios();
			break;
			
			case "10":
				System.out.println("\n*****FIN DEL PROGRAMA*****\n");
				band=false;
			break;
			
			default: System.out.println("\nOpcion no disponible");
			}
		}while(band);
		
		sc.close(); 
	}
	
	public static void altaCliente(Scanner sc) {

		System.out.print("\nIngrese nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Ingrese apellido: ");
		String apellido = sc.nextLine();
		System.out.print("Ingrese domicilio: ");
		String domicilio = sc.nextLine();
		System.out.println("Ingrese el numero telefonico: ");
		long tel = sc.nextLong();
		
		boolean datoInvalido=false;
		int dni=0;
		do {
			try {
				datoInvalido=false;
				System.out.print("Ingrese DNI: ");
				dni = sc.nextInt();
			}
			catch(Exception e) {
				System.out.println("\nDato no valido, vuelva a ingresar el DNI");
				datoInvalido=true;
				sc.nextLine();
			}
		}while(datoInvalido);
		
		
		List<Reserva> reservas = new ArrayList<>();
		Cliente cliente = new Cliente(dni, nombre, apellido, domicilio, tel, reservas);
		
		clienteDaoImp.guardarCliente(cliente);
	 
		System.out.println("\nCliente registrado exitosamente.\n");
		sc.nextLine();
	}
	
	public static void consultarCliente(long id) {
		Cliente cli = clienteDaoImp.consultarCliente(id);
		cli.mostrarCliente();
	}
	
	public static void modificarCliente(Scanner sc) {
	    
		clienteDaoImp.mostrarTodosLosClientes();
		
		Cliente cliente = new Cliente();
	    long idCliente=0;
	    boolean datoInvalido=true;
	    do {
	    	try {
	    		System.out.print("\nIngrese el ID del cliente a modificar: ");
		        idCliente = sc.nextLong();
		        
		        cliente = clienteDaoImp.consultarCliente(idCliente);

		        if (cliente == null) {
		            System.out.println("Cliente no encontrado. Intente nuevamente");
		        }
		        else datoInvalido=false;
	    	}
	    	catch(Exception e) {
	    		System.out.println("\nDato no valido, ingrese nuevamente el ID");
	    		sc.nextLine();
	    	}
	    } while (datoInvalido);

	    sc.nextLine();

	    System.out.print("Ingrese el nuevo nombre: ");
	    cliente.setNombre(sc.nextLine());

	    System.out.print("Ingrese el nuevo apellido: ");
	    cliente.setApellido(sc.nextLine());

	    System.out.print("Ingrese el nuevo domicilio: ");
	    cliente.setDomicilio(sc.nextLine());
	    
	    System.out.println("Ingrese el nuevo telefono: ");
	    cliente.setTelefono(sc.nextLong());

		do {
			try {
				datoInvalido=false;
				System.out.print("Ingrese el nuevo DNI: ");
				cliente.setDni(sc.nextInt());
			}
			catch(Exception e) {
				System.out.println("\nDato no valido, vuelva a ingresar el DNI");
				datoInvalido=true;
				sc.nextLine();
			}
		}while(datoInvalido);
		
	    clienteDaoImp.modificarCliente(cliente);
	    System.out.println("\nDatos del cliente actualizados.\n");
	}

	public static void realizarPago(Scanner sc) {
		System.out.println("Ingrese el ID de la reserva:");
        long idReserva = sc.nextLong();
        
        Reserva reserva = reservaDaoImp.consultarReserva(idReserva);
        if (reserva == null) {
            System.out.println("No se encontró una reserva con el ID proporcionado.");
            return;
        }

        reserva.mostrarDatos();

        if (reserva.isCancelado()) {
            System.out.println("La reserva ya está cancelada. No se requiere más pagos.");
            return;
        }

        System.out.println("Ingrese el monto a pagar:");
        double monto = sc.nextDouble();

        reserva.setMontoPagado(reserva.getMontoPagado() + monto);

        if (reserva.getMontoPagado() >= reserva.calcularMontoTotal()) {
            reserva.setCancelado(true);
            System.out.println("Pago completo, la reserva ha sido cancelada.");
        } else {
            System.out.println("Pago realizado, monto pendiente: $" + reserva.calcularPagoPendiente());
        }

        reservaDaoImp.guardarReserva(reserva);
	}
	
	public static void realizarReserva(Scanner sc) {
		
		Salon saloninicio = new Salon();
        saloninicio.inicializarSalones();
        ServicioAdicional servicioInicio = new ServicioAdicional();
        servicioInicio.inicializarServicios();
        
        Cliente cliente = new Cliente();
        
        boolean clienteValido=false;
        
        do {
            try {
                System.out.println("Ingrese el ID del cliente:");
                long idCliente = sc.nextLong();
                cliente = clienteDaoImp.consultarCliente(idCliente);

                if (cliente == null) {
                    System.out.println("El ID del cliente no existe. Por favor, registre al cliente.");
                } else {
                    clienteValido = true;
                }
            } catch (Exception e) {
                System.out.println("\nID no válido. Por favor ingrese nuevamente.");
                sc.nextLine(); 
            }
        } while (!clienteValido);

        
        Salon salon = null;
        do {
            System.out.println("Ingrese el ID del salón:");
            long idSalon = sc.nextLong();
            salon = salonDaoImp.consultarSalon(idSalon);

            if (salon == null) {
                System.out.println("El salón no existe o no está disponible. Intente nuevamente.");
            }
        } while (salon == null);

        
        System.out.println("Ingrese la hora de inicio (formato 24 horas):");
        short horaInicio = sc.nextShort();

        System.out.println("Ingrese la hora de fin (formato 24 horas):");
        short horaFin = sc.nextShort();

        if (horaInicio >= horaFin) {
            System.out.println("La hora de inicio debe ser menor que la hora de fin. Intente nuevamente.");
            return;
        }

       
        List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
        boolean agregarServicios = true;
        do {
            System.out.println("¿Desea agregar un servicio adicional? (S/N):");
            String respuesta = sc.next();

            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Ingrese el ID del servicio adicional:");
                long idServicio = sc.nextLong();
                ServicioAdicional servicio = servicioAdicionalDaoImp.consultarServicio(idServicio);
                if (servicio != null) {
                    serviciosAdicionales.add(servicio);
                    System.out.println("Servicio añadido: " + servicio.getDescripcion());
                } else {
                    System.out.println("El ID del servicio no es válido.");
                }
            } else {
                agregarServicios = false;
            }
        } while (agregarServicios);

      
        System.out.println("Ingrese un pago adelantado (opcional, 0 si no desea):");
        double pagoAdelantado = sc.nextDouble();

      
        Reserva nuevaReserva = new Reserva(cliente, salon, LocalDate.now(), horaInicio, horaFin, 0, serviciosAdicionales, pagoAdelantado, false);
        reservaDaoImp.guardarReserva(nuevaReserva);

        System.out.println("Reserva creada con éxito:");
        nuevaReserva.mostrarDatos();
	}
	
	public static void consultarReservas() {
		 try {
		        reservaDaoImp.mostrarTodosLasReservas();
		    } catch (Exception e) {
		        System.out.println("Error al consultar las reservas ");
		    }
	}
	
	public static void consultarUnaReserva() {
		
	}
	
	public static void consultarSalones() {
		
	}
	
	public static void consultarServicios() {
		
	}
}
