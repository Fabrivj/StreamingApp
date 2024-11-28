package org.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Class.ContentItems;

// Estrategia de búsqueda para Vimeo
public class VimeoSearchStrategy implements SearchStrategy {
    private String accessToken;

    public VimeoSearchStrategy(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public List<ContentItems> search(String query) {
        // Lógica de búsqueda para Vimeo (esto es simulado, normalmente harías una llamada HTTP aquí)
        List<ContentItems> results = new ArrayList<>();
        results.add(new ContentItems("1", "Movie 1 from Vimeo", "Movies", "Kids", new Date(), "Frozen", 4.5, "Vimeo", "HD", "US"));
        results.add(new ContentItems("2", "Movie 2 from Vimeo", "Movies", "Kids", new Date(), "Toy Story", 4.0, "Vimeo", "HD", "US"));
        return results; // Devolvemos los resultados
    }

    @Override
    public List<ContentItems> recommend(String userPreferences) {
        // Lógica de recomendación para Vimeo (simulada)
        List<ContentItems> recommendations = new ArrayList<>();
        recommendations.add(new ContentItems("3", "Recommended Song 1 from Vimeo", "Movies", "kids", new Date(), "Kids movies", 5.0, "Vimeo", "HD", "US"));
        return recommendations;
    }
}