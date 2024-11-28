package org.search;

import Class.Usuario;

/**
 * Servicio real de streaming de video.
 */
public class VideoStreamingService implements StreamingPlatformService {

    @Override
    public void playContent(String content) {
        System.out.println("Reproduciendo el contenido: " + content);
    }

    @Override
    public boolean hasAccess(Usuario usuario) {
        // Aquí verificamos el rol del usuario para determinar si tiene acceso.
        if (usuario.getRole().equals("premium")) {
            return true;
        }
        return false;
    }
}