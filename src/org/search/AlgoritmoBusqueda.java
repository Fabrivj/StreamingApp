package org.search;

import java.util.Collection;

public interface AlgoritmoBusqueda {
    Collection<SearchResult> buscar(String query, StreamingServiceManager manager);
}
