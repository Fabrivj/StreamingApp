
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
                    // Buscar contenido (sin cambios)
                    break;

                case 2:
                    return; // Volver al menú principal

                case 3:
                    // Opción para agregar un nuevo usuario, solo disponible para admin
                    if (usuario.getTipoUsuario().equals("admin")) {
                        System.out.println("Como administrador, puedes agregar un nuevo usuario.");
                        System.out.print("Introduce el nombre de usuario: ");
                        String username = scanner.nextLine();
                        System.out.print("Introduce la contraseña: ");
                        String password = scanner.nextLine();
                        System.out.print("Introduce el email: ");
                        String email = scanner.nextLine();

                        // Solicitar el tipo de usuario
                        System.out.println("Selecciona el tipo de usuario:");
                        System.out.println("1 - Admin");
                        System.out.println("2 - Básico");
                        System.out.println("3 - Premium");
                        int tipoUsuario = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        // Agregar el usuario
                        String tipo = "";
                        switch (tipoUsuario) {
                            case 1:
                                tipo = "admin";
                                break;
                            case 2:
                                tipo = "basic";
                                break;
                            case 3:
                                tipo = "premium";
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                continue;
                        }

                        // Crear el nuevo usuario
                        try {
                            Usuario nuevoUsuario = UsuarioFactory.crearUsuario(tipo, username, password, email);
                            // Agregar a la lista de usuarios (supongamos que la lista está almacenada en un lugar accesible)
                            // Por ejemplo:
                            // usuarios.add(nuevoUsuario);

                            System.out.println("Nuevo usuario " + tipo + " agregado exitosamente.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error al crear el usuario: " + e.getMessage());
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