
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;
import org.search.*;
import Login.*;


public class Main {
    public static void main(String[] args) {
        StreamingServiceManager manager =  StreamingServiceManager.getInstancia();
        // Crear el contexto de autenticación
        ContextoAutenticacion contexto = new ContextoAutenticacion();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Mostrar el menú con las opciones
            System.out.println("\n--- Menú de Autenticación ---");
            System.out.println("1. Ver estado actual de la sesión");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Cerrar de sesión");
            System.out.println("4. Salir del programa");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Ver estado actual de la sesión
                    if (contexto.getEstadoActual() instanceof EstadoAutenticado) {
                        System.out.println("Estado actual: AUTENTICADO");
                    } else if (contexto.getEstadoActual() instanceof EstadoNoAutenticado) {
                        System.out.println("Estado actual: NO AUTENTICADO");
                    } else if (contexto.getEstadoActual() instanceof EstadoSesionExpirada) {
                        System.out.println("Estado actual: SESIÓN EXPIRADA");
                    }
                    break;

                case 2:
                    // Iniciar sesión
                    if (contexto.getEstadoActual() instanceof EstadoNoAutenticado) {
                        System.out.print("Ingresa tu usuario: ");
                        String username = scanner.nextLine();
                        System.out.print("Ingresa tu contraseña: ");
                        String password = scanner.nextLine();
                        contexto.getEstadoActual().iniciarSesion(contexto, username, password);
                        manager.setServicio(new VimeoService()); // Pasar el token correctamente

                        String query = "LIBRE SOY - FROZEN"; // Consulta específica para la película "Frozen La película"
                        Collection<SearchResult> resultados = manager.consultarServicio(query, new Vector<>());


                        if (resultados != null && !resultados.isEmpty()) {
                            for (SearchResult resultado : resultados) {
                                System.out.println(resultado);
                            }
                        } else {
                            System.out.println("No se encontró el resultado para la búsqueda: " + query);
                        }
                    } else {
                        System.out.println("Ya estás autenticado. No necesitas iniciar sesión nuevamente.");
                    }
                    break;

                case 3:
                    // Cerrar de sesión
                    if (contexto.getEstadoActual() instanceof EstadoAutenticado) {
                        contexto.getEstadoActual().cerrarSesion(contexto);
                    } else {
                        System.out.println("No estás autenticado. No puedes cerrar sesión.");
                    }
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }


    }

}







    //        System.out.println("\n------------------------------------------");
//        System.out.println("                Búsquedas                ");
//        System.out.println("------------------------------------------");
//
//        // Obtenemos la instancia del manager (Singleton)
//        StreamingServiceManager manager = StreamingServiceManager.getInstancia();
//
//        // Usar el servicio WatchMode
//        manager.setServicio(new WatchModeService());
//
//        // Configurar el servicio WatchMode
//        Vector<String> configParams = new Vector<>();
//        configParams.add("Región: US");
//        manager.configurarServicio(configParams);
//
//        // *****  BUSCAR *****
//        // Realizar una búsqueda en WatchMode
//        System.out.println("\n------------------------------------------");
//        System.out.println("   Búsqueda con parámetros (método buscar)   ");
//        System.out.println("------------------------------------------");
//        Vector<String> searchParams = new Vector<>();
//        String query = "FROZEN"; // Consulta específica para la película "Frozen La película"
//        Collection<SearchResult> resultados = manager.consultarServicio(query, new Vector<>());
//
//
//        // Mostrar los resultados de Buscar
//        for (SearchResult result : resultados) {
//            System.out.println(result.toString() + "\n");
//        }


    //
    //        // Crear el proxy para cada usuario
    //        StreamingPlatformService proxyPremium = new StreamingServiceProxy(usuarioPremium);
    //        StreamingPlatformService proxyBasico = new StreamingServiceProxy(usuarioBasico);
    //
    //        // El usuario premium tiene acceso
    //        proxyPremium.playContent("Película de acción");
    //
    //        // El usuario básico no tiene acceso
    //        proxyBasico.playContent("Comedia romántica");
