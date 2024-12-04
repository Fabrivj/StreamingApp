package org.search;
import Class.Usuario.Usuario;

/**
 * Interfaz común para todos los servicios de streaming.
 */
public interface StreamingPlatformService {
    void playContent(String content);
    boolean hasAccess(Usuario usuario);
}
