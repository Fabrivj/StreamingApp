package Login;
import Class.Usuario;

public class EstadoNoAutenticado implements EstadoAutenticacion {

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

    @Override
    public void accederServicio(ContextoAutenticacion contexto) {
        System.out.println("Acceso denegado. Debes iniciar sesión primero.");
    }

    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("No estás autenticado. No puedes cerrar sesión.");
    }
}