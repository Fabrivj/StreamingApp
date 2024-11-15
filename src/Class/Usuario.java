package Class;

/**
 * Clase que representa a un Usuario dentro del sistema de streaming multiservicio.
 */


public class Usuario {
    private String username;
    private String password;
    private String correo;
    private String role;

    public Usuario(String username, String password, String correo, String role) {
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRole() {
        return role;
    }
}
