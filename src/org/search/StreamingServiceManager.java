package org.search;

import java.util.Collection;
import java.util.Vector;

public class StreamingServiceManager {
    private static StreamingServiceManager instancia;
    private StreamingService servicioActual;

    // Constructor privado para el Singleton
    private StreamingServiceManager() {}

    // Método para obtener la única instancia del manager (Singleton)
    public static StreamingServiceManager getInstancia() {
        if (instancia == null) {
            instancia = new StreamingServiceManager();
        }
        return instancia;
    }

    // Configurar el servicio de streaming
    public void setServicio(StreamingService servicio) {
        this.servicioActual = servicio;
    }

    // Llamadas delegadas a la implementación del servicio actual

    /**
     * Configura el servicio de streaming con los parámetros especificados.
     *
     * @param configuracionServicio Los parámetros de configuración para el servicio.
     */
    public void configurarServicio(Vector<String> configuracionServicio) {
        if (servicioActual != null) {
            servicioActual.configurar(configuracionServicio);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
    }

    /**
     * Consulta contenido en el servicio configurado actualmente.
     *
     * @param query        La consulta de búsqueda.
     * @param configParams Parámetros adicionales para la búsqueda.
     * @return Una colección de resultados de búsqueda o null si no hay servicio configurado.
     */
    public Collection<SearchResult> consultarServicio(String query, Vector<String> configParams) {
        if (servicioActual != null) {
            return servicioActual.consultar(query, configParams);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
            return null;
        }
    }

    /**
     * Busca contenido en el servicio configurado actualmente.
     *
     * @param query        La consulta de búsqueda.
     * @param configParams Parámetros adicionales para la búsqueda.
     */
    public void buscarEnServicio(String query, Vector<String> configParams) {
        if (servicioActual != null) {
            servicioActual.buscar(query, configParams);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
    }
}
