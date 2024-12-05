
import java.util.Collection;
import java.util.Scanner;
import java.util.Vector;

import Class.Usuario.*;
import org.search.*;
import Login.*;


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

        // Suscribir al usuario para recibir notificaciones de cambios
        manager.agregarObserver(usuario);  // El usuario se suscribe al manager para recibir notificaciones
        System.out.println("Usuario " + usuario.getUsername() + " agregado como observador.");  // Depuración

        while (true) {
            System.out.println("\n--- Menú de Usuario Autenticado ---");
            System.out.println("1. Buscar contenido");
            System.out.println("2. Cerrar sesión");

            // Solo mostrar la opción de agregar usuarios si el usuario es admin
            if (usuario.getTipoUsuario().equals("admin")) {
                System.out.println("3. Agregar usuario");
            }

            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    // Verificar si el usuario es premium
                    if (proxy.hasAccess(usuario)) {
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
                        manager.setServicio(new VimeoService());
                    }

                    System.out.println("Selecciona el tipo de búsqueda:");
                    System.out.println("1. Por popularidad");
                    System.out.println("2. Por relevancia");
                    int tipoBusqueda = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    AlgoritmoBusqueda estrategiaBusqueda;
                    switch (tipoBusqueda) {
                        case 1:
                            estrategiaBusqueda = new BusquedaPorPopularidad();
                            break;
                        case 2:
                            estrategiaBusqueda = new BusquedaPorRelevancia();
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            continue;
                    }

                    System.out.print("Ingresa el término de búsqueda: ");
                    String query = scanner.nextLine();
                    Collection<SearchResult> resultados = estrategiaBusqueda.buscar(query, manager);

                    // Mostrar los resultados de la búsqueda
                    if (resultados != null && !resultados.isEmpty()) {
                        for (SearchResult resultado : resultados) {
                            System.out.println(resultado);
                        }
                    } else {
                        System.out.println("No se encontraron resultados.");
                    }
                    break;

                case 2:
                    return;

                case 3:
                    if (usuario.getTipoUsuario().equals("admin")) {
                        System.out.println("Como administrador, puedes agregar un nuevo usuario.");
                        System.out.print("Introduce el nombre de usuario: ");
                        String username = scanner.nextLine();
                        System.out.print("Introduce la contraseña: ");
                        String password = scanner.nextLine();
                        System.out.print("Introduce el email: ");
                        String email = scanner.nextLine();

                        System.out.println("Selecciona el tipo de usuario:");
                        System.out.println("1 - Admin");
                        System.out.println("2 - Básico");
                        System.out.println("3 - Premium");
                        int tipoUsuario = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        String tipo = switch (tipoUsuario) {
                            case 1 -> "admin";
                            case 2 -> "basic";
                            case 3 -> "premium";
                            default -> {
                                System.out.println("Opción no válida.");
                                yield "";
                            }
                        };

                        if (!tipo.isEmpty()) {
                            try {
                                Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipo, username, password, email);
                                System.out.println("Nuevo usuario " + tipo + " agregado exitosamente.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error al crear el usuario: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Solo los administradores pueden agregar usuarios.");
                    }
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}