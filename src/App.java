import java.rmi.server.SocketSecurityException;
import java.util.Scanner;
import MNUtility.Utility;

public class App {
    public static final String mnCedula = "1754491494";
    public static final String mnCorreo = "maicol.nasimba@epn.edu.ec";
    public static final String mnNombre = "MAICOL ESTUARDO NASIMBA QUINGA";
    static char flecha = (char) 16;

    public static void main(String[] args) throws Exception {
        Scanner mnSc = new Scanner(System.in);        
        mnmostrarDatos();
        System.out.println("\nPresione enter para continuar...");
        mnSc.nextLine();
        Utility.clearScreen();

        mnValidarCredenciales(mnSc);
        if(mnLogin(mnCorreo, mnCedula)){
        System.out.println("XD");
        
        }



    }


    private static void mnValidarCredenciales(Scanner mnSc) {
        int mni=3;
        // validar credenciales
        System.out.println("Ingrese sus credenciales para iniciar el programa ");
        do{
        System.out.println("Nuero de intentos: "+mni + "/3");        
        System.out.print("Ingrese su usuario: ");
        String mnUsuario = mnSc.nextLine();
        System.out.print("Ingrese su clave: ");
        String mnClave = mnSc.nextLine(); 
        Utility.clearScreen();   
            if(!mnLogin(mnUsuario,mnClave) && mni !=0){
                System.out.println("[X] SU CLAVE Y USUARIO NO COINCIDE. VUELVA A INTENTARLO");
                mni--;
                if(mni==0){
                    System.out.println("Los entimos tu usuario y clave son incorrectos...");
                    System.out.println("Gracias");
                    break;
                }
            }
            if(mnLogin(mnUsuario,mnClave)){
                mostrarCredenciales(mnUsuario, mnClave);
                System.out.println("\t BIENVENIDO: \u001B[33m"+mnUsuario.toUpperCase());

                break;
            }


        }while(mni>0);
    }


    private static void mnmostrarDatos(){
        System.out.println("----------------------");
        System.out.println("\t DATOS");
        System.out.println("----------------------");
        System.out.println("- CEDULA: " + mnCedula);
        System.out.println("- CORREO: " + mnCorreo);
        System.out.println("- NOMBRE: " + mnNombre);
    }


    public static boolean mnLogin(String mnUsuario, String mnClave) {
            return ((mnUsuario.equals("profe") && mnClave.equals("1234"))||(mnUsuario.equals("maicol.nasimba@epn.edu.ec") && mnClave.equals("1754491494"))); // Credenciales válidas
    }
    public static void mostrarCredenciales(String mnUsuario, String mnClave) {
        System.out.println(flecha+ " Usuario: " + mnUsuario);
        System.out.print(flecha+" Clave: ");
        for (int i = 0; i < mnClave.length(); i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}
