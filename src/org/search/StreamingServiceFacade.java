package org.search;

import java.util.Collection;
import java.util.Vector;

/**
 * Clase Fachada para interactuar con servicios de streaming.
 */
public class StreamingServiceFacade {

    private StreamingServiceManager serviceManager;

    public StreamingServiceFacade() {
        // Inicializar StreamingServiceManager
        serviceManager = StreamingServiceManager.getInstancia();
    }

    /**
     * Configurar el servicio de streaming con los parámetros necesarios.
     * @param service StreamingService Servicio de streaming
     * @param configParams Configuración para el servicio
     */
    public void configurarServicio(StreamingService service, Collection<String> configParams) {
        // Configurar servicio utilizando el manager
        serviceManager.setServicio(service);
        serviceManager.configurarServicio(new Vector<>(configParams));
    }

    /**
     * Buscar contenido en el servicio de streaming.
     * @param query Nombre o término de búsqueda
     * @param configParams Parámetros de configuración
     * @return Collection de resultados de búsqueda
     */
    public Collection<SearchResult> buscarContenido(String query, Collection<String> configParams) {
        // Delegar la búsqueda al servicio configurado
        return serviceManager.consultarServicio(query, new Vector<>(configParams));
    }

    /**
     * Consultar contenido en el servicio de streaming.
     * @param query Término de búsqueda
     * @param configParams Parámetros de configuración
     * @return Collection de resultados de búsqueda
     */
    public Collection<SearchResult> consultarContenido(String query, Collection<String> configParams) {
        // Delegar la consulta al servicio configurado
        return serviceManager.consultarServicio(query, new Vector<>(configParams));
    }
}

