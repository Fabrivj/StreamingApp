package org.search;

import java.util.Collection;

public class LoggingStreamingServiceDecorator extends StreamingServiceDecorator {

    public LoggingStreamingServiceDecorator(StreamingService service) {
        super(service);
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {
        System.out.println("Consultando el servicio de streaming para: " + query);
        Collection<SearchResult> results = super.consultar(query, configParams);
        System.out.println("Resultados obtenidos: " + results.size());
        return results;
    }
}

