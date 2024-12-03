package org.search;

import Class.Usuario;

import java.util.Collection;
import java.util.Vector;

/**
 * Proxy que controla el acceso al servicio de streaming.
 */
public class StreamingServiceProxy implements StreamingPlatformService {
    private final Usuario usuario;
    private final StreamingServiceManager serviceManager;

    /**
     * Constructor del Proxy.
     *
     * @param usuario El usuario autenticado que solicita el servicio.
     * @param manager
     */
    public StreamingServiceProxy(Usuario usuario, StreamingServiceManager manager) {
        this.usuario = usuario;
        this.serviceManager = StreamingServiceManager.getInstancia(); // Singleton del manager
    }

    @Override
    public void playContent(String content) {
        if (hasAccess(usuario)) {
            System.out.println("Reproduciendo contenido: " + content);
        } else {
            System.out.println("Acceso denegado. El usuario no tiene suscripción adecuada.");
        }
    }

    /**
     * Realiza una consulta de contenido en el servicio configurado actualmente.
     *
     * @param query        La consulta de búsqueda.
     * @param configParams Parámetros adicionales para la búsqueda.
     * @return Una colección de resultados de búsqueda o null si el usuario no tiene acceso.
     */
    public Collection<SearchResult> consultarServicio(String query, Vector<String> configParams) {
        if (hasAccess(usuario)) {
            return serviceManager.consultarServicio(query, configParams);
        } else {
            System.out.println("Acceso denegado. Solo los usuarios premium pueden realizar búsquedas.");
            return null;
        }
    }

    @Override
    public boolean hasAccess(Usuario usuario) {
        return usuario.getTipoUsuario().trim().equalsIgnoreCase("premium");
    }
}
