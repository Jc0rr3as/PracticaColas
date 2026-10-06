package Vista;
import java.util.Scanner;
import Modelo.cliente;
import java.util.Queue;
import java.util.LinkedList;
import Validaciones.validaciones;
public class metodos {
    public Queue<cliente> registrarCliente(Queue<cliente> colaClientes, metodos m, Scanner sc, validaciones v){
        boolean continuar = true;
        while(continuar){
        cliente c = new cliente();
        System.out.println("Ingrese la identificacion del cliente: ");
        c.setIdentificacion(String.valueOf(v.ValidarEntero(sc)));
        sc.nextLine(); // Limpiar el buffer del scanner
        System.out.println("Ingrese el nombre del cliente: ");
        c.setNombre(sc.nextLine());
        System.out.println("Ingrese el tipo de tramite del cliente: ");
        c.setTipoTramite(sc.nextLine());
        System.out.println("Ingrese la edad del cliente: ");
        c.setEdad(String.valueOf(v.ValidarEntero(sc)));
        sc.nextLine(); // Limpiar el buffer del scanner
        System.out.println("Ingrese si el cliente necesita atencion especial (si/no): ");
        c.setAtencionEspecial(v.ValidarSioNo(sc));
        c.setTurnoEspecial(m.ValidarTurnoEspecial(colaClientes, c));
        c.setTurno(m.ValidarTurno(colaClientes, c));
        c.setEstado("En espera");
        colaClientes.add(c);
        System.out.println("Desea registrar otro cliente? (si/no): ");
        String respuesta = v.ValidarSioNo(sc);
        if(respuesta.equalsIgnoreCase("no")){
            continuar = false;
        }
        }
        return colaClientes;
    }

    public int ValidarTurnoEspecial(Queue<cliente> colaClientes, cliente c){
        if(colaClientes.isEmpty() && c.getAtencionEspecial().equalsIgnoreCase("si")){
            return 1;
        } else{
            Queue<cliente> colaPrioritaria = new LinkedList<>();
            for (cliente cl : colaClientes) {
                if(cl.getAtencionEspecial().equalsIgnoreCase("si")){
                    colaPrioritaria.add(cl);
                }
            }
            int turnoEspecial = 0;
            for (cliente cl : colaPrioritaria) {
                if(cl.getTurnoEspecial() > turnoEspecial){
                    turnoEspecial = cl.getTurnoEspecial();
                }
            }
            return turnoEspecial + 1;
        }
    }

    public int ValidarTurno(Queue<cliente> colaClientes, cliente c){
        if(colaClientes.isEmpty() && c.getAtencionEspecial().equalsIgnoreCase("no")){
            return 1;
        } else{
            Queue<cliente> colaNormal = new LinkedList<>();
            for (cliente cl : colaClientes) {
                if(cl.getAtencionEspecial().equalsIgnoreCase("no")){
                    colaNormal.add(cl);
                }
            }
            int turnoNormal = 0;
            for (cliente cl : colaNormal) {
                if(cl.getTurno() > turnoNormal){
                    turnoNormal = cl.getTurno();
                }
            }
            return turnoNormal + 1;
        }
    }

    public void consultarClientesEsperando(Queue<cliente> colaClientes){
        System.out.println("Clientes prioritarios en espera:");
        System.out.println("-------------------------------------------");
        for (cliente c : colaClientes) {
            if(c.getAtencionEspecial().equalsIgnoreCase("si") && c.getEstado().equalsIgnoreCase("En espera")){
                System.out.println("Identificacion: " + c.getIdentificacion() + ", Nombre: " + c.getNombre() + ", Tipo de tramite: " + c.getTipoTramite() + ", Edad: " + c.getEdad() + ", Turno: " + c.getTurnoEspecial());
            }
        }
        System.out.println("Clientes en espera:");
        System.out.println("-------------------------------------------");
        for (cliente c : colaClientes) {
            if(c.getAtencionEspecial().equalsIgnoreCase("no") && c.getEstado().equalsIgnoreCase("En espera")){
                System.out.println("Identificacion: " + c.getIdentificacion() + ", Nombre: " + c.getNombre() + ", Tipo de tramite: " + c.getTipoTramite() + ", Edad: " + c.getEdad() + ", Turno: " + c.getTurno());
            }
        }
    }

    public Queue<cliente> atenderCliente(Queue<cliente> colaClientes){
        Queue<cliente> colaPrioritaria = new LinkedList<>();
        Queue<cliente> colaNormal = new LinkedList<>();
        if(colaClientes.isEmpty()){
            System.out.println("No hay clientes en espera");
            return colaClientes;
        } else {
            for (cliente c : colaClientes) {
                if(c.getAtencionEspecial().equalsIgnoreCase("si") && c.getEstado().equalsIgnoreCase("En espera")){
                    colaPrioritaria.add(c);
                } else if(c.getAtencionEspecial().equalsIgnoreCase("no") && c.getEstado().equalsIgnoreCase("En espera")){
                    colaNormal.add(c);
                }
            }

            if(!colaPrioritaria.isEmpty()){
                cliente c = colaPrioritaria.poll();
                System.out.println("Se atendió al cliente: " + c.getNombre() + ", con identificacion: " + c.getIdentificacion());
                c.setEstado("Atendido");
            } else if(!colaNormal.isEmpty()){
                cliente c = colaNormal.poll();
                System.out.println("Se atendió al cliente: " + c.getNombre() + ", con identificacion: " + c.getIdentificacion());
                c.setEstado("Atendido");
                }else {
                    System.out.println("No hay clientes en espera");
                }
        } 
                return colaClientes;
    }

    public Queue<cliente> cambiarPrioridad(Queue<cliente> colaClientes){
        Scanner sc = new Scanner(System.in);
        Validaciones.validaciones v = new Validaciones.validaciones();
        System.out.println("Ingrese la identificacion del cliente que desea cambiar de prioridad: ");
        String id = String.valueOf(v.ValidarEntero(sc));
        sc.nextLine(); // Limpiar el buffer del scanner
        boolean encontrado = false;
        for (cliente c : colaClientes) {
            if(c.getIdentificacion().equals(id)){
                encontrado = true;
                if(c.getAtencionEspecial().equalsIgnoreCase("si")){
                    System.out.println("El cliente ya tiene prioridad");
                } else {
                    c.setAtencionEspecial("si");
                    c.setTurnoEspecial(ValidarTurnoEspecial(colaClientes, c));
                    c.setTurno(0);
                    System.out.println("Se cambio la prioridad del cliente: " + c.getNombre() + ", con identificacion: " + c.getIdentificacion());
                }
            }
        }
        if(!encontrado){
            System.out.println("No se encontro un cliente con esa identificacion");
        }
        return colaClientes;
    }


    public Queue<cliente> eliminarCliente(Queue<cliente> colaClientes){
            Scanner sc = new Scanner(System.in);
            Validaciones.validaciones v = new Validaciones.validaciones();
            System.out.println("Ingrese la identificacion del cliente que desea cancelar el turno: ");
            String id = String.valueOf(v.ValidarEntero(sc));
            sc.nextLine(); // Limpiar el buffer del scanner
            boolean encontrado = false;
            for (cliente c : colaClientes) {
                if(c.getIdentificacion().equals(id)){
                    encontrado = true;
                    if(c.getEstado().equalsIgnoreCase("Atendido")){
                        System.out.println("El cliente ya fue atendido, no se puede cancelar el turno");
                        return colaClientes;
                        
                    }else{
                        colaClientes.remove(c);
                        System.out.println("Se canceló el turno de el cliente: " + c.getNombre() + ", con identificacion: " + c.getIdentificacion());
                }
            }
            if(!encontrado){
                System.out.println("No se encontro un cliente con esa identificacion");
            }
            
        }
        return colaClientes;
    }    
    public void consultarCliente(Queue<cliente> colaClientes){
        Scanner sc = new Scanner(System.in);
        Validaciones.validaciones v = new Validaciones.validaciones();
        System.out.println("Ingrese la identificacion del cliente que desea consultar: ");
        String id = String.valueOf(v.ValidarEntero(sc));
        sc.nextLine(); // Limpiar el buffer del scanner
        boolean encontrado = false;
        for (cliente c : colaClientes) {
            if(c.getIdentificacion().equals(id)){
                encontrado = true;
                System.out.println("Cliente encontrado: ");
                System.out.println("Identificacion: " + c.getIdentificacion() + ", Nombre: " + c.getNombre() + ", Tipo de tramite: " + c.getTipoTramite() + ", Edad: " + c.getEdad() + ", Atencion especial: " + c.getAtencionEspecial() + ", Turno: " + c.getTurno() + ", Turno especial: " + c.getTurnoEspecial() + ", Estado: " + c.getEstado());
            }
        }
        if(!encontrado){
            System.out.println("No se encontro un cliente con esa identificacion");
        }
    }

    public void consultarEsperando(Queue<cliente> colaClientes){
        int contadorPrioritarios = 0;
        int contadorNormales = 0;
        for (cliente c : colaClientes) {
            if(c.getAtencionEspecial().equalsIgnoreCase("si") && c.getEstado().equalsIgnoreCase("En espera")){
                contadorPrioritarios++;
            } else if(c.getAtencionEspecial().equalsIgnoreCase("no") && c.getEstado().equalsIgnoreCase("En espera")){
                contadorNormales++;
            }
        }
        System.out.println("Clientes en espera:");
        System.out.println("Prioritarios: " + contadorPrioritarios);
        System.out.println("Normales: " + contadorNormales);

    }
}
