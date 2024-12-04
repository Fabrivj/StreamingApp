package Class.Usuario;

public class UsuarioAdmin extends Usuario {
    public UsuarioAdmin(String username, String password, String email) {
        super(username, password, email, "admin");
    }

    @Override
    public String toString() {
        return super.toString() + " (Acceso Administrador)";
    }
}
