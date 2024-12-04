package Class.Usuario;

public class UsuarioBasico extends Usuario {
    public UsuarioBasico(String username, String password, String email) {
        super(username, password, email, "básico");
    }

    @Override
    public String toString() {
        return super.toString() + " (Acceso Básico)";
    }
}
