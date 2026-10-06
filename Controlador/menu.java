package Controlador;
import Vista.metodos;
import java.util.Scanner;
import Modelo.cliente;
import java.util.Queue;
import java.util.LinkedList;
import Validaciones.validaciones;

public class menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        metodos m = new metodos();
        validaciones v = new validaciones();
        Queue<cliente> colaClientes = new LinkedList<>();
        boolean continuar = true;
        while(continuar){
            System.out.println("Bienvenido al sistema de turnos del banco");
            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar los clientes en espera");
            System.out.println("3. Atender cliente");
            System.out.println("4. Cambiar la prioridad de un cliente");
            System.out.println("5. Cancelar turno de un cliente");
            System.out.println("6. Buscar un cliente por identificacion");
            System.out.println("7.Ver cantidad de clientes espera: ");
            System.out.println("8. Salir");
            int opcion = v.ValidarEntero(sc);
            sc.nextLine(); // Limpiar el buffer del scanner
            switch(opcion){
                case 1:
                    colaClientes = m.registrarCliente(colaClientes, m, sc, v);
                    break;
                case 2:
                    m.consultarClientesEsperando(colaClientes);
                    break;
                case 3:
                    colaClientes = m.atenderCliente(colaClientes);
                    break;
                case 4:
                    colaClientes = m.cambiarPrioridad(colaClientes);
                    break;
                case 5:
                    colaClientes = m.eliminarCliente(colaClientes);
                    break;
                case 6:
                    m.consultarCliente(colaClientes);
                    break;
                case 7:
                    m.consultarEsperando(colaClientes);
                    break;
                case 8:
                    System.out.println("Gracias por usar el sistema de turnos del banco");
                    continuar = false;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
