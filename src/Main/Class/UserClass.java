/**
 * Clase que representa a un Usuario dentro del sistema de streaming multiservicio.
 */
public class Usuario {
    private String nombre;
    private String email;
    private String contraseña;

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombre El nombre del usuario.
     * @param email El correo electrónico del usuario.
     * @param contraseña La contraseña del usuario.
     */
    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Métodos Getter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

/**
 * Clase que representa una Suscripción del usuario a un servicio de streaming.
 */
public class Suscripción {
    private String nombrePlan;
    private double precio;

    /**
     * Constructor de la clase Suscripción.
     *
     * @param nombrePlan Nombre del plan de suscripción.
     * @param precio Precio del plan de suscripción.
     */
    public Suscripción(String nombrePlan, double precio) {
        this.nombrePlan = nombrePlan;
        this.precio = precio;
    }

    // Métodos Getter y Setter

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

