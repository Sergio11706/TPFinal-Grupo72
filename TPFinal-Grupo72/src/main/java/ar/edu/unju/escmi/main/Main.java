package ar.edu.unju.escmi.main;

import java.util.Scanner;

public class Main {

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
				altaCliente();
			break;
			
			case "2":
				consultarCliente();
			break;
			
			case "3":
				modificarCliente();
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
	
	public static void altaCliente() {
		
	}
	
	public static void consultarCliente() {
		
	}
	
	public static void modificarCliente() {
		
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
