package Login;
import Class.Usuario;

public class EstadoAutenticado implements EstadoAutenticacion {

    @Override
    public void iniciarSesion(ContextoAutenticacion contexto, String username, String password) {
        System.out.println("Ya estás autenticado. No necesitas iniciar sesión nuevamente.");
    }

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

    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("Cerrando sesión...");
        contexto.cambiarEstado(new EstadoNoAutenticado());
    }
}