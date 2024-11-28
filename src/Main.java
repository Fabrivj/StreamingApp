
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import org.search.*;
import Login.*;
import Class.ContentItems;

public class Main {
    public static void main(String[] args) {


        StreamingServiceManager manager = StreamingServiceManager.getInstancia();
        String accessToken = "5ba21cbaa50cab330f1b4fc6a3d1b61e"; // Asegúrate de usar el token correcto
        manager.setServicio(new VimeoService(accessToken)); // Pasar el token correctamente

        String query = "LIBRE SOY - FROZEN"; // Consulta específica para la película "Frozen La película"
        Collection<SearchResult> resultados = manager.consultarServicio(query, new Vector<>());


        if (resultados != null && !resultados.isEmpty()) {
            for (SearchResult resultado : resultados) {
                System.out.println(resultado);
            }
        } else {
            System.out.println("No se encontró el resultado para la búsqueda: " + query);
        }


        // Crear el contexto de autenticación
        ContextoAutenticacion contexto = new ContextoAutenticacion();

        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Crear el contexto de búsqueda
        SearchContext context = new SearchContext();

        context.setSearchStrategy(new VimeoSearchStrategy(accessToken));

        while (true) {
            // Mostrar el menú con las opciones
            System.out.println("\n--- Menú de Autenticación ---");
            System.out.println("1. Ver estado actual de la sesión");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir de sesión");
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
                    } else {
                        System.out.println("Ya estás autenticado. No necesitas iniciar sesión nuevamente.");
                    }
                    break;

                case 3:
                    // Salir de sesión
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
            // Menú de búsqueda y recomendación
            System.out.println("\n--- Menú de Búsqueda y Recomendación ---");
            System.out.println("5. Buscar contenido");
            System.out.println("6. Ver recomendaciones");
            System.out.println("7. Cambiar API de búsqueda");
            System.out.println("8. Volver al menú de autenticación");
            System.out.print("Elige una opción: ");

            int searchOption = scanner.nextInt();
            scanner.nextLine();

            switch (searchOption){
                case 5:
                    //Realizar búsqueda
                    System.out.print("Introduce el término de búsqueda: ");
                    query = scanner.nextLine();
                    List<ContentItems> searchResults = context.search(query);
                    for (ContentItems item : searchResults) {
                        System.out.println(item);
                    }
                    break;
                case 6:
                    //Recomendaciones
                    System.out.println("Introduce tus preferencias(Género, Artista, etc...)");
                    String preferences = scanner.nextLine();
                    List<ContentItems> recommendations = context.recommend(preferences);
                    for(ContentItems item: recommendations){
                        System.out.println(item);
                    }
                    break;

                case 7:
                    // Cambiar la estrategia de búsqueda
                    System.out.println("Selecciona la API de búsqueda:");
                    System.out.println("1. Vimeo");
                    System.out.println("2. API2");
                    int apiChoice = scanner.nextInt();
                    if (apiChoice == 1) {
                        context.setSearchStrategy(new VimeoSearchStrategy(accessToken));
                    } else if (apiChoice == 2) {
                        //context.setSearchStrategy(new API2SearchStrategy()); // Si tienes esta estrategia implementada
                    }
                    break;

                case 8:
                    // Volver al menú de autenticación
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                    break;
            }

        }
    }
}
