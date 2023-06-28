import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.SocketSecurityException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import MNUtility.Utility;

public class App {
    private String horarioRutaDirectorio;
    public static final String mnCedula = "1754491494";
    public static final String mnCorreo = "maicol.nasimba@epn.edu.ec";
    public static final String mnNombre = "MAICOL ESTUARDO NASIMBA QUINGA";
    static char flecha = (char) 16;
    public static Scanner mnSc = new Scanner(System.in); 
    public static void main(String[] args) throws Exception {
       
        mnmostrarDatos();
        System.out.println("\nPresione enter para continuar...");
        mnSc.nextLine();
        Utility.clearScreen();
        int mni=3;
        // validar credenciales
        System.out.println("\\u001B[35mIngrese sus credenciales para iniciar el programa ");
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
                mnMostrarCredenciales(mnUsuario, mnClave);
                System.out.println("\u001B[33m\t BIENVENIDO: "+mnUsuario.toUpperCase());
                System.out.println("\nPresione enter para continuar...");
                mnSc.nextLine();
                Utility.clearScreen();
                boolean mnBandV = false;
                while(mnBandV==false){
                    mnMenuPrincipal(mnUsuario);
                    int mnOpcion = Utility.NasimbagetNumeroPositivo("Ingrese una opcion: ");
                    Utility.clearScreen();
                    if (mnOpcion < 0  || mnOpcion > 4) {
                        System.out.println("[x] Entrada no aceptada. Intente de nuevo");
                        continue;
                    }
                
                    switch(mnOpcion){
                        case 0:
                            mnBandV = true;
                            System.out.println("Regrsa pronto "+ mnUsuario.toUpperCase());
                            break;
                        case 1:
                            System.out.println("\u001B[33m[+] Listado de Profesores:");
                            String mnNombreArchivo = "202110105-CHUNCHO JIMENEZ ANGEL DAVID.csv";
                            String mnRutaArchivo = mnObtenerRuta(mnNombreArchivo);
                            String nombreArchivoCompleto = mnObtenerNombreArchivo(mnRutaArchivo);
                            System.out.println("\t- " + nombreArchivoCompleto);

                            String mnNombreArchivo2 = "202111083-HIDALGO CRUZ PABLO ESTEBAN.csv";
                            String mnRutaArchivo2 = mnObtenerRuta(mnNombreArchivo2);
                            String mnNombreArchivoCompleto2 = mnObtenerNombreArchivo(mnRutaArchivo2);
                            System.out.println("\t- " + mnNombreArchivoCompleto2);

                            String mnNombreArchivo3 = "202120757-ALEMAN OSORIO CARLOS ALEJANDRO.csv";
                            String mnRutaArchivo3 = mnObtenerRuta(mnNombreArchivo3);
                            String mnNombreArchivoCompleto3 = mnObtenerNombreArchivo(mnRutaArchivo3);
                            System.out.println("\t- " + mnNombreArchivoCompleto3);
                            System.out.println("\nPresione enter para continuar...");
                            mnSc.nextLine();
                            Utility.clearScreen();

                            break;
                        case 2: 
                                String rutaArchivo = "\\\\Nasimba.Maicol\\MNarchivos\\202110105-CHUNCHO JIMENEZ ANGEL DAVID.csv";
                                Map<String, Set<String>> datosSeparados = leerArchivoCSV(rutaArchivo);

                                // Imprimir los datos separados por categorías
                                for (Map.Entry<String, Set<String>> entry : datosSeparados.entrySet()) {
                                    String categoria = entry.getKey();
                                    Set<String> datos = entry.getValue();
                                    System.out.println(categoria + ": " + datos);
                                }
                        

                    
                    }

                }


                break;
            }


        }while(mni>0);

    }


    private static void mnMenuPrincipal(String mnUsuario) {
        System.out.println("\u001B[34m------------------");
        System.out.println("Carga horaria de profesores");
        System.out.println("------------------");
        System.out.println("Usuario: "+mnUsuario.toUpperCase()+"\n");
        System.out.println(flecha+" 1 Visualizar titulo");
        System.out.println(flecha+" 2 Visualizar profesores");
        System.out.println(flecha+" 3 Visualizar Horario");
        System.out.println(flecha+" 4 Visualizar horaio de un titulo");
        System.out.println(flecha+" 0 Salir");
    }



    


    private static void mnmostrarDatos(){
        System.out.println("\\u001B[32m----------------------");
        System.out.println("\t DATOS");
        System.out.println("----------------------");
        System.out.println("- CEDULA: " + mnCedula);
        System.out.println("- CORREO: " + mnCorreo);
        System.out.println("- NOMBRE: " + mnNombre);
    }


    public static boolean mnLogin(String mnUsuario, String mnClave) {
            return ((mnUsuario.equals("profe") && mnClave.equals("1234"))||(mnUsuario.equals("maicol.nasimba@epn.edu.ec") && mnClave.equals("1754491494"))); // Credenciales válidas
    }
    public static void mnMostrarCredenciales(String mnUsuario, String mnClave) {
        System.out.println(flecha+ " Usuario: " + mnUsuario);
        System.out.print(flecha+" Clave: ");
        for (int i = 0; i < mnClave.length(); i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static String mnObtenerRuta(String nombreArchivo) {
        String rutaRelativa = "\\\\Nasimba.Maicol\\MNarchivos\\";
        File archivo = new File(rutaRelativa, nombreArchivo);
        return archivo.getPath();
    }

    public static String mnObtenerNombreArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        String nombreCompleto = archivo.getName();
        int indicePunto = nombreCompleto.lastIndexOf(".");
        if (indicePunto != -1) {
            return nombreCompleto.substring(0, indicePunto);
        }
        return nombreCompleto;
    }

    public static Map<String, Set<String>> leerArchivoCSV(String rutaArchivo) {
        Map<String, Set<String>> datosSeparados = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String primeraLinea = br.readLine();
            if (primeraLinea != null) {
                String[] categorias = primeraLinea.split(";");
                for (String categoria : categorias) {
                    datosSeparados.put(categoria, new HashSet<>());
                }
            }

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                int index = 0;
                for (String categoria : datosSeparados.keySet()) {
                    datosSeparados.get(categoria).add(datos[index]);
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosSeparados;
    }

}
