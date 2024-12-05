package org.search;

import Class.Usuario.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class BusquedaPorRelevancia implements AlgoritmoBusqueda {
    @Override
    public Collection<SearchResult> buscar(String query, StreamingServiceManager manager) {
        System.out.println("Realizando búsqueda basada en relevancia...");
        // Usa Vector en lugar de List.of() para evitar conflictos
        Vector<String> parametros = new Vector<>();
        parametros.add("orden=relevancia");
        Usuario.actualizar("Búsqueda por popularidad realizada con éxito.");
        manager.notificarObservers();
        return manager.consultarServicio(query, parametros);
    }
}
