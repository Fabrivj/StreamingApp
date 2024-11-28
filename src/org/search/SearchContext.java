package org.search;
import Class.ContentItems;

import java.util.List;

public class SearchContext {
    private SearchStrategy searchStrategy;

    // Cambiar la estrategia de búsqueda y recomendación en tiempo de ejecución
    public void setSearchStrategy(SearchStrategy strategy) {
        this.searchStrategy = strategy;
    }

    // Realizar la búsqueda usando la estrategia actual
    public List<ContentItems> search(String query) {
        return searchStrategy.search(query); // Delegamos la búsqueda a la estrategia
    }

    // Obtener recomendaciones usando la estrategia actual
    public List<ContentItems> recommend(String userPreferences) {
        return searchStrategy.recommend(userPreferences); // Delegamos la recomendación a la estrategia
    }
}
