
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;
import org.search.*;
import Login.*;
import  Class.*;



public class Main {
    public static void main(String[] args) {
        StreamingServiceManager manager = StreamingServiceManager.getInstancia();
        ContextoAutenticacion contexto = new ContextoAutenticacion();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            // Mostrar el menú principal
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Ver estado actual de la sesión");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Cerrar sesión");
            System.out.println("4. Salir del programa");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Ver estado actual
                    if (contexto.getEstadoActual() instanceof EstadoAutenticado) {
                        System.out.println("Estado actual: AUTENTICADO");
                    } else {
                        System.out.println("Estado actual: NO AUTENTICADO");
                    }
                    break;

                case 2:
                    // Iniciar sesión
                    if (contexto.getEstadoActual() instanceof EstadoNoAutenticado) {
                        System.out.print("Usuario: ");
                        String username = scanner.nextLine();
                        System.out.print("Contraseña: ");
                        String password = scanner.nextLine();
                        contexto.getEstadoActual().iniciarSesion(contexto, username, password);

                        if (contexto.getUsuarioActual() != null) {
                            System.out.println("Bienvenido, " + contexto.getUsuarioActual().getUsername());

                            mostrarMenuUsuarioAutenticado(contexto, manager, scanner,contexto.getUsuarioActual());
                        }
                    } else {
                        System.out.println("Ya estás autenticado.");
                    }
                    break;

                case 3:
                    // Cerrar sesión
                    if (contexto.getEstadoActual() instanceof EstadoAutenticado) {
                        contexto.getEstadoActual().cerrarSesion(contexto);
                        System.out.println("Sesión cerrada.");
                    } else {
                        System.out.println("No estás autenticado.");
                    }
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenuUsuarioAutenticado(ContextoAutenticacion contexto, StreamingServiceManager manager, Scanner scanner, Usuario usuario) {

        StreamingServiceProxy proxy = new StreamingServiceProxy(usuario, manager);

        while (true) {
            System.out.println("\n--- Menú de Usuario Autenticado ---");
            System.out.println("1. Buscar contenido");
            System.out.println("2. Cerrar sesión");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Verificar si el usuario es premium
                    if (proxy.hasAccess(usuario)) {
                        // Si el usuario es premium, preguntamos en qué servicio desea buscar
                        System.out.println("¿En qué servicio deseas buscar?");
                        System.out.println("1. Vimeo");
                        System.out.println("2. WatchMode");
                        int servicio = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        switch (servicio) {
                            case 1:
                                manager.setServicio(new VimeoService());
                                break;
                            case 2:
                                manager.setServicio(new WatchModeService());
                                Vector<String> configParams = new Vector<>();
                                configParams.add("Región: US");
                                manager.configurarServicio(configParams);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                continue;
                        }
                    } else {
                        // Si no es premium, solo le permitimos buscar en Vimeo
                        manager.setServicio(new VimeoService());
                        //System.out.println("Buscando solo en Vimeo.");
                    }

                    System.out.print("Ingresa el término de búsqueda: ");
                    String query = scanner.nextLine();
                    Collection<SearchResult> resultados = manager.consultarServicio(query, new Vector<>());

                    if (resultados != null && !resultados.isEmpty()) {
                        for (SearchResult resultado : resultados) {
                            System.out.println(resultado);
                        }
                    } else {
                        System.out.println("No se encontraron resultados.");
                    }
                    break;

                case 2:
                    return; // Volver al menú principal

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}