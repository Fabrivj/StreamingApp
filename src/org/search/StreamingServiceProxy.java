package org.search;

import Class.Usuario;

/**
 * Proxy que controla el acceso al servicio de streaming.
 */
public class StreamingServiceProxy implements StreamingPlatformService {

    private VideoStreamingService videoStreamingService;
    private Usuario usuario;

    public StreamingServiceProxy(Usuario usuario) {
        this.usuario = usuario;
        this.videoStreamingService = new VideoStreamingService(); // El servicio real
    }

    @Override
    public void playContent(String content) {
        if (hasAccess(usuario)) {
            videoStreamingService.playContent(content); // Permitir el acceso si tiene los permisos
        } else {
            System.out.println("Acceso denegado. El usuario no tiene suscripción adecuada.");
        }
    }

    @Override
    public boolean hasAccess(Usuario usuario) {
        // El proxy controla si el usuario tiene acceso basado en su rol
        return usuario.getRole().equals("premium");
    }
}
