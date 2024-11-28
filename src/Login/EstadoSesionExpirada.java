package Login;

/**
 * La clase {@code EstadoSesionExpirada} representa el estado de autenticación en el cual la sesión del usuario ha expirado.
 * Implementa la interfaz {@code EstadoAutenticacion} para manejar las acciones permitidas en este estado.
 * <p>
 * En este estado:
 * <ul>
 *   <li>No es posible acceder a servicios protegidos hasta que el usuario vuelva a iniciar sesión.</li>
 *   <li>No se puede cerrar la sesión, ya que ésta ya ha expirado.</li>
 * </ul>
 * </p>
 *
 * @see EstadoAutenticacion
 */
public class EstadoSesionExpirada implements EstadoAutenticacion {

    /**
     * Notifica al usuario que su sesión ha expirado y que debe iniciar sesión nuevamente.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     * @param username el nombre de usuario ingresado (sin efecto en este estado)
     * @param password la contraseña ingresada (sin efecto en este estado)
     */
    @Override
    public void iniciarSesion(ContextoAutenticacion contexto, String username, String password) {
        System.out.println("Tu sesión ha expirado. Debes iniciar sesión nuevamente.");
    }

    /**
     * Informa al usuario que no puede acceder al servicio porque su sesión ha expirado.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     */
    @Override
    public void accederServicio(ContextoAutenticacion contexto) {
        System.out.println("Tu sesión ha expirado. Debes iniciar sesión para acceder al servicio.");
    }

    /**
     * Informa al usuario que no puede cerrar sesión porque su sesión ya ha expirado.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     */
    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("Tu sesión ha expirado. No puedes cerrar sesión en este estado.");
    }
}
