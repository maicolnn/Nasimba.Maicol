package MNUtility;
import java.util.Scanner;

public class Utility {
    static  Scanner input = new Scanner(System.in);
    static Scanner entrada = new Scanner(System.in);

    /**
     * Pide una entrada hasta que sea un numero entero positivo
     * @param mensage Mensaje para pedir numero
     * @return Retona el numero positivo
     */
    public  static int NasimbagetNumeroPositivo(String mensage){ 
        boolean validar;
        int num;
        do {
            System.out.print(mensage);
        
            if(input.hasNextInt()){
                num = input.nextInt(); 
                if(num<0){
                    validar = false;
                    System.out.println("[x] Entrada no aceptada, vuelva a intentarlo");

                }
                 else
                validar = true;
            }
            else{
                System.out.println("[x] Entrada no aceptada, vuelva a intentarlo");

                num=-1;
                input.next();
                input.nextLine();
                validar=false;
            }
            
        } while (!validar || num<0);
        return num;
    }

    /**
     * Pide al usuario una entrada hasta que sea tipo String
     * @param cadena Mensaje
     * @return Retorna la entrada tipo String
     */
    public static String NasimbapedirString(String mensaje) {
        String cadena;
        do {
            System.out.print(mensaje);
            cadena = entrada.nextLine();
            if(!NasimbaValidarString(cadena)){
                System.out.println("[x] Entrada no aceptada, vuelva a intentarlo");
            }
        } while (!NasimbaValidarString(cadena));
        return cadena;
    }

    /**
     * Verifica si se ha ingresado un string y no otro tipo de entrada
     * @param cadena Entrada que da el usuario
     * @return {@code true} si es una String que contenga los caracteres establecidos.
     */
    public static boolean NasimbaValidarString(String cadena) {
        return cadena.matches("[a-zA-Z ]+");
    }

    /**
     * Pide una entrada hasta que sea una vocal
     * @return Retorna la vocal
     */
    public static char NasimbapedirVocal() {
        System.out.print("Vocal a eliminar: ");
        String Vocal = entrada.nextLine().toLowerCase();
        
        while (Vocal.length() != 1 || !NasimbaValidarVocal(Vocal.charAt(0))) {
            System.out.println("[x] Entrada no aceptada, vuelva a intentarlo");
            System.out.print("Vocal a eliminar: ");
            Vocal = entrada.nextLine().toLowerCase();
        }
        
        return Vocal.charAt(0);
    }
    
    /**
     * Verifica que el caracter ingresado sea vocal
     * @param vocal caracter que da el usuario
     * @return {@code true} si el caracter es un vocal
     */
    public static boolean NasimbaValidarVocal(char vocal) {
        return vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u';
    }

    /**
     * Pide una entrada hasta que sea una letra
     * @return Retorna la letra
     */
    public static char NasimbapedirLetra() {
        System.out.print("Letra a eliminar: ");
        String Letra = entrada.nextLine().toLowerCase();
        
        while (Letra.length() != 1 || !NasimbaValidarLetra(Letra.charAt(0))) {
            System.out.println("[x] Entrada no aceptada, vuelva a intentarlo");
            System.out.print("Letra a eliminar: ");
            Letra = entrada.nextLine().toLowerCase();
        }
        
        return Letra.charAt(0);
    }
    
    /**
     * Verifica que el caracter ingresado sea letra
     * @param letra caracter que da el usuario
     * @return {@code true} si el caracter es un letra
     */
    public static boolean NasimbaValidarLetra(char letra) {
        return letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u' && letra != ' ';
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
