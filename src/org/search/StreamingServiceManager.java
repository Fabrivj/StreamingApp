package org.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import Notificaciones.*;

public class StreamingServiceManager implements Subject {
    private static StreamingServiceManager instancia;
    private StreamingService servicioActual;
    private List<Observer> observers = new ArrayList<>();
    private String estadoBusqueda;

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
            return servicioActual.consultar(query, configParams);
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
            return null;
        }
    }

    public void buscarEnServicio(String query, Vector<String> configParams) {
        if (servicioActual != null) {
            servicioActual.buscar(query, configParams);
            // Actualizamos el estado de la búsqueda
            this.estadoBusqueda = "Búsqueda realizada con éxito.";
            notificarObservers(); // Notificamos a los observadores después de la búsqueda
        } else {
            System.out.println("No se ha seleccionado ningún servicio.");
        }
    }

    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (Observer observer : observers) {
            Observer.actualizar(estadoBusqueda); // Notificar a cada observador con el estado actual
        }
    }

}