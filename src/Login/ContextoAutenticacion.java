package Login;

import Class.Usuario;
import java.util.HashMap;
import java.util.Map;

public class ContextoAutenticacion {
    private EstadoAutenticacion estadoActual;
    private Usuario usuarioActual;
    private String token;

    // Simulación de una base de datos de usuarios
    private static final Map<String, Usuario> baseDeDatosUsuarios = new HashMap<>();

    static {
        // Crear un usuario de prueba (username: "fabrivj", password: "Cenfo24")
        baseDeDatosUsuarios.put("fabrivj", new Usuario("fabrivj", "Cenfo24", "fvargasj@ucenfotec.ac.cr", "admin"));
    }

    public ContextoAutenticacion() {
        this.estadoActual = new EstadoNoAutenticado(); // Estado inicial
    }

    public EstadoAutenticacion getEstadoActual() {
        return estadoActual;
    }

    public void cambiarEstado(EstadoAutenticacion nuevoEstado) {
        estadoActual = nuevoEstado;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public String getToken() {
        return token;
    }

    public void generarToken(String username) {
        this.token = "TOKEN-" + username + "-" + System.currentTimeMillis();
    }

    // Simula la validación de las credenciales
    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = baseDeDatosUsuarios.get(username);
        return usuario != null && usuario.getPassword().equals(password);
    }

    // Validar el token (aquí solo verificamos si existe el token, se puede mejorar para manejo real de expiración)
    public boolean validarToken() {
        return token != null && !token.isEmpty();  // Asumiendo que si el token no es nulo, es válido
    }
}