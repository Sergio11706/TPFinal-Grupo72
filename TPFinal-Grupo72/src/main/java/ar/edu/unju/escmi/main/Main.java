package ar.edu.unju.escmi.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.entities.*;
import ar.edu.unju.escmi.dao.imp.*;

public class Main {

	private static ClienteDaoImp clienteDaoImp = new ClienteDaoImp();

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean band=true;
		
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
				realizarPago();
			break;
			
			case "5":
				realizarReserva();
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

	public static void realizarPago() {
		
	}
	
	public static void realizarReserva() {
		
	}
	
	public static void consultarReservas() {
		
	}
	
	public static void consultarUnaReserva() {
		
	}
	
	public static void consultarSalones() {
		
	}
	
	public static void consultarServicios() {
		
	}
}
