package Login;

import Class.Usuario;

/**
 * La clase {@code EstadoNoAutenticado} representa el estado en el cual el usuario no ha iniciado sesión.
 * Implementa la interfaz {@code EstadoAutenticacion} para manejar las acciones permitidas en este estado.
 * <p>
 * En este estado:
 * <ul>
 *   <li>El usuario puede intentar iniciar sesión proporcionando credenciales válidas.</li>
 *   <li>No se permite el acceso a servicios restringidos.</li>
 *   <li>No es posible cerrar sesión porque el usuario no está autenticado.</li>
 * </ul>
 * </p>
 *
 * @see EstadoAutenticacion
 */
public class EstadoNoAutenticado implements EstadoAutenticacion {

    /**
     * Intenta iniciar sesión con el nombre de usuario y contraseña proporcionados.
     * Si las credenciales son válidas, el contexto de autenticación se actualiza con el usuario actual y se genera un token.
     * Luego, el estado cambia a {@code EstadoAutenticado}.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión y permite la transición de estados
     * @param username el nombre de usuario ingresado para autenticación
     * @param password la contraseña ingresada para autenticación
     */
    @Override
    public void iniciarSesion(ContextoAutenticacion contexto, String username, String password) {
        if (contexto.validarCredenciales(username, password)) {
            Usuario usuario = new Usuario(username, password, "fvargasj@ucenfotec.ac.cr", "admin");
            contexto.setUsuarioActual(usuario);  // Guardamos el usuario actual
            contexto.generarToken(username);  // Generamos el token
            System.out.println("Inicio de sesión exitoso.");
            System.out.println("Token generado: " + contexto.getToken());
            contexto.cambiarEstado(new EstadoAutenticado());  // Cambiamos al estado de autenticado
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }

    /**
     * Informa al usuario que no tiene acceso al servicio porque no ha iniciado sesión.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión
     */
    @Override
    public void accederServicio(ContextoAutenticacion contexto) {
        System.out.println("Acceso denegado. Debes iniciar sesión primero.");
    }

    /**
     * Informa al usuario que no puede cerrar sesión porque no está autenticado.
     *
     * @param contexto el contexto de autenticación que contiene el estado de la sesión
     */
    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("No estás autenticado. No puedes cerrar sesión.");
    }
}
