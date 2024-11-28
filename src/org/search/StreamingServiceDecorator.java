package org.search;

import java.util.Collection;

public abstract class StreamingServiceDecorator implements StreamingService {
    protected StreamingService service;

    public StreamingServiceDecorator(StreamingService service) {
        this.service = service;
    }

    @Override
    public void configurar(Collection<String> configParams) {
        service.configurar(configParams);
    }

    @Override
    public Collection<SearchResult> consultar(String query, Collection<String> configParams) {
        return service.consultar(query, configParams);
    }

    @Override
    public Collection<SearchResult> buscar(String query, Collection<String> configParams) {
        return service.buscar(query, configParams);
    }
}

