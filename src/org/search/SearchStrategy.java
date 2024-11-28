package org.search;
import Class.ContentItems;
import java.util.List;

public interface SearchStrategy {
  // Método para realizar la búsqueda de contenido (ej. canciones, películas)
    List<ContentItems>search (String query);

    // Método para generar recomendaciones de contenido (ej. basadas en preferencias de usuario)
    List<ContentItems> recommend (String userPreferences);
}
