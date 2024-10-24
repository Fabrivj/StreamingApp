package Class;

/**
 * Clase que representa a un Usuario dentro del sistema de streaming multiservicio.
 */
public class Users {
    private String name;
    private String email;
    private String password;

    /**
     * Constructor for the Users class.
     *
     * @param name The name of the Users.
     * @param email The email address of the Users.
     * @param password The password of the Users.
     */
    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
