package org.search;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CachingStreamingServiceDecorator extends StreamingServiceDecorator {
    private Map<String, Collection<SearchResult>> cache = new HashMap<>();

    public CachingStreamingServiceDecorator(StreamingService service) {
        super(service);
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {
        if (cache.containsKey(query)) {
            System.out.println("Recuperando de la caché la consulta para: " + query);
            return cache.get(query);
        } else {
            System.out.println("Realizando la consulta al servicio de streaming para: " + query);
            Collection<SearchResult> results = super.consultar(query, configParams);
            cache.put(query, results);
            return results;
        }
    }
}

