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
    public void configurarServicio(Vector<String> configuracionServicio) {
        if (servicioActual != null) {
            servicioActual.configurar(configuracionServicio);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
    }

    public Collection<SearchResult> consultarServicio(String query, Vector<String> configParams) {
        if (servicioActual != null) {
            servicioActual.consultar(query, configParams);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
        return null;
    }

    public void buscarEnServicio(String query, Vector<String> configParams) {
        if (servicioActual != null) {
            servicioActual.buscar(query, configParams);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
    }
}
