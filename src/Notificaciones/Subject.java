package Notificaciones;

import java.util.List;

public interface Subject {
    void agregarObserver(Observer observer);
    void eliminarObserver(Observer observer);
    void notificarObservers();
}