package Login;

import Class.Usuario.Usuario;

import java.util.UUID;
import BD.BaseDeDatosUsuarios;

/**
 * Clase que representa el contexto de autenticación, que gestiona el estado actual
 * de autenticación y la asignación de tokens de acceso seguros.
 */
public class ContextoAutenticacion {
    private EstadoAutenticacion estadoActual;
    private Usuario usuarioActual;
    private String token;
    private long tokenExpirationTime;  // Tiempo de expiración del token en milisegundos
    private BaseDeDatosUsuarios baseDeDatosUsuarios;

    // Duración del token en milisegundos (por ejemplo, 30 minutos)
    private static final long TOKEN_DURATION_MS = 30 * 60 * 1000;

    /**
     * Constructor que inicializa el contexto de autenticación en el estado no autenticado.
     */
    public ContextoAutenticacion() {
        this.estadoActual = new EstadoNoAutenticado(); // Estado inicial
        this.baseDeDatosUsuarios = new BaseDeDatosUsuarios(); // Inicializa la base de datos de usuarios
    }

    /**
     * Obtiene el estado actual de autenticación.
     *
     * @return El estado actual de autenticación.
     */
    public EstadoAutenticacion getEstadoActual() {
        return estadoActual;
    }

    /**
     * Cambia el estado de autenticación al nuevo estado especificado.
     *
     * @param nuevoEstado El nuevo estado de autenticación.
     */
    public void cambiarEstado(EstadoAutenticacion nuevoEstado) {
        estadoActual = nuevoEstado;
    }

    /**
     * Obtiene el usuario actualmente autenticado.
     *
     * @return El usuario actualmente autenticado.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Establece el usuario actual en el contexto de autenticación.
     *
     * @param usuario El usuario a establecer como actual.
     */
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    /**
     * Obtiene el token de autenticación actual.
     *
     * @return El token de autenticación actual.
     */
    public String getToken() {
        return token;
    }

    /**
     * Genera un nuevo token seguro para el usuario autenticado y establece su tiempo de expiración.
     *
     * @param username El nombre de usuario para el cual se genera el token.
     */
    public void generarToken(String username) {
        this.token = "TOKEN-" + UUID.randomUUID();  // Genera un token único y seguro
        this.tokenExpirationTime = System.currentTimeMillis() + TOKEN_DURATION_MS;  // Establece la expiración del token
    }

    /**
     * Simula la validación de credenciales para la autenticación del usuario.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son válidas; false en caso contrario.
     */
    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = baseDeDatosUsuarios.obtenerUsuario(username); // Obtiene el usuario de la base de datos
        setUsuarioActual(usuario);
        return usuario != null && usuario.getPassword().equals(password);
    }

    /**
     * Verifica si el token de autenticación es válido y no ha expirado.
     *
     * @return true si el token es válido y no ha expirado; false en caso contrario.
     */
    public boolean validarToken() {
        if (token == null || token.isEmpty()) {
            System.out.println("Token inválido o inexistente.");
            return false;
        }
        if (System.currentTimeMillis() > tokenExpirationTime) {
            System.out.println("El token ha expirado.");
            token = null; // Expira el token y lo elimina
            return false;
        }
        return true;
    }

    /**
     * Expira manualmente el token de autenticación.
     */
    public void expirarToken() {
        this.token = null;
        this.tokenExpirationTime = 0;
        System.out.println("Token expirado manualmente.");
    }
}
