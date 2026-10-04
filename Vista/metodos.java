package Vista;
import java.util.Scanner;
import Modelo.cliente;
import java.util.Queue;
import java.util.LinkedList;
import Validaciones.validaciones;
public class metodos {
    public Queue<cliente> registrarCliente(Queue<cliente> colaClientes, metodos m, Scanner sc, validaciones v){
        cliente c = new cliente();
        System.out.println("Ingrese la identificacion del cliente: ");
        c.setIdentificacion(sc.nextLine());
        System.out.println("Ingrese el nombre del cliente: ");
        c.setNombre(sc.nextLine());
        System.out.println("Ingrese el tipo de tramite del cliente: ");
        c.setTipoTramite(sc.nextLine());
        System.out.println("Ingrese la edad del cliente: ");
        c.setEdad(String.valueOf(v.ValidarEntero(sc)));
        System.out.println("Ingrese si el cliente necesita atencion especial (si/no): ");
        c.setAtencionEspecial(v.ValidarSioNo(sc));
        System.out.println("Ingrese el turno del cliente: ");
        c.setTurno(m.ValidarTurno(colaClientes));
        colaClientes.add(c);
        return colaClientes;
    }

    public int ValidarTurno(Queue<cliente> colaClientes) {
        int turno = 0;
        if (colaClientes.isEmpty()) {
            turno = 1;
        } else {
            turno = colaClientes.size() + 1;
        }
        return turno;
    }

    public String ValidarEstado(Queue<cliente> colaClientes){
        String estado = "";
    }
}
