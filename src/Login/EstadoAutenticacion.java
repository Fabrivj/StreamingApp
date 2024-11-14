package Login;

public interface EstadoAutenticacion {

    /**
     * Inicia sesión usando credenciales.
     *
     * @param contexto El contexto de autenticación.
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     */
    void iniciarSesion(ContextoAutenticacion contexto, String username, String password);

    /**
     * Intenta acceder a un servicio si el usuario está autenticado.
     *
     * @param contexto El contexto de autenticación.
     */
    void accederServicio(ContextoAutenticacion contexto);

    /**
     * Cierra la sesión del usuario, invalidando el token de autenticación.
     *
     * @param contexto El contexto de autenticación.
     */
    void cerrarSesion(ContextoAutenticacion contexto);
}
