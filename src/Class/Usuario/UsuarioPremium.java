package Class.Usuario;

public class UsuarioPremium extends Usuario {
    public UsuarioPremium(String username, String password, String email) {
        super(username, password, email, "premium");
    }

    @Override
    public String toString() {
        return super.toString() + " (Acceso Premium)";
    }
}
