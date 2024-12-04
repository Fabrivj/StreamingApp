package Class.Usuario;


public class UsuarioFactory {
    public static Usuario crearUsuario(String tipoUsuario, String username, String password, String email) {
        switch (tipoUsuario.toLowerCase()) {
            case "premium":
                return new UsuarioPremium(username, password, email);
            case "basic":
                return new UsuarioBasico(username, password, email);
            case "admin":
                return new UsuarioAdmin(username, password, email);
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }
    }
}
