package Login;

/**
 * La clase {@code EstadoAutenticado} representa el estado en el cual el usuario está autenticado.
 * Implementa la interfaz {@code EstadoAutenticacion} para manejar las acciones permitidas en este estado.
 * <p>
 * En este estado:
 * <ul>
 *   <li>El usuario ya ha iniciado sesión y no necesita autenticarse nuevamente.</li>
 *   <li>El usuario puede acceder a servicios restringidos, siempre que el token de autenticación sea válido.</li>
 *   <li>El usuario puede cerrar sesión, cambiando al estado de no autenticado.</li>
 * </ul>
 * </p>
 *
 * @see EstadoAutenticacion
 */
public class EstadoAutenticado implements EstadoAutenticacion {

    /**
     * Informa al usuario que ya está autenticado y no es necesario iniciar sesión nuevamente.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     * @param username el nombre de usuario ingresado (sin efecto en este estado)
     * @param password la contraseña ingresada (sin efecto en este estado)
     */
    @Override
    public void iniciarSesion(ContextoAutenticacion contexto, String username, String password) {
        System.out.println("Ya estás autenticado. No necesitas iniciar sesión nuevamente.");
    }

    /**
     * Permite al usuario acceder al servicio si el token de autenticación es válido.
     * Si el token no es válido, cambia el estado a {@code EstadoSesionExpirada}.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     */
    @Override
    public void accederServicio(ContextoAutenticacion contexto) {
        if (contexto.validarToken()) {
            System.out.println("Acceso permitido al servicio.");
        } else {
            // Si el token no es válido, se cambia al estado de sesión expirada
            System.out.println("Tu sesión ha expirado o el token es incorrecto. Inicia sesión nuevamente.");
            contexto.cambiarEstado(new EstadoSesionExpirada());  // Cambiar al estado de sesión expirada
        }
    }

    /**
     * Cierra la sesión del usuario y cambia el estado a {@code EstadoNoAutenticado}.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     */
    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("Cerrando sesión...");
        contexto.cambiarEstado(new EstadoNoAutenticado());
    }
}
