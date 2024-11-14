package Login;


public class EstadoSesionExpirada implements EstadoAutenticacion {

    @Override
    public void iniciarSesion(ContextoAutenticacion contexto, String username, String password) {
        System.out.println("Tu sesión ha expirado. Debes iniciar sesión nuevamente.");
    }

    @Override
    public void accederServicio(ContextoAutenticacion contexto) {
        // Si la sesión ha expirado, no se puede acceder al servicio
        System.out.println("Tu sesión ha expirado. Debes iniciar sesión para acceder al servicio.");
    }

    @Override
    public void cerrarSesion(ContextoAutenticacion contexto) {
        System.out.println("Tu sesión ha expirado. No puedes cerrar sesión en este estado.");
    }
}
