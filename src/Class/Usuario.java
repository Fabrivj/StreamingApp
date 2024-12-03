package Class;

/**
 * Clase que representa a un usuario en el sistema.
 */
public class Usuario {
    private String username;
    private String password;
    private String email;
    private String tipoUsuario; // Puede ser "premium" o "básico"

    /**
     * Constructor para inicializar un usuario.
     *
     * @param username    El nombre de usuario.
     * @param password    La contraseña del usuario.
     * @param email       El correo electrónico del usuario.
     * @param tipoUsuario El tipo de usuario ("premium" o "básico").
     */
    public Usuario(String username, String password, String email, String tipoUsuario) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Sobrescribe el método toString para representar al usuario como una cadena.
     *
     * @return Una representación en texto del usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
