package Validaciones;
    import java.util.Scanner;

public class validaciones {
    public int ValidarEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Por favor Ingrese un digito numerico");
            sc.next();
        }
        return sc.nextInt();
    }

    public Double ValidarDecimal(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor Ingrese un digito numerico");
            sc.next();
        }
        return sc.nextDouble();
    }

    public String ValidarSioNo(Scanner sc) {
        String respuesta = sc.nextLine();
        while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
            System.out.println("Por favor Ingrese si o no");
            respuesta = sc.nextLine();
        }
        return respuesta;
    }
}

