package Class;

/**
 * Clase que representa un Servicio de Streaming (Netflix, Disney+, etc.)
 */
public class ServicioStreaming {
    private String nombre;
    private String urlAutenticacion;
    private String protocolo;
    

    /**
     * Constructor de la clase ServicioStreaming.
     *
     * @param nombre El nombre del servicio de streaming.
     * @param urlAutenticacion La URL de autenticación del servicio.
     * @param protocolo El protocolo de autenticación utilizado (OAuth, etc.).
     */
    public ServicioStreaming(String nombre, String urlAutenticacion, String protocolo) {
        this.nombre = nombre;
        this.urlAutenticacion = urlAutenticacion;
        this.protocolo = protocolo;
    }

    // Métodos Getter y Setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlAutenticacion() {
        return urlAutenticacion;
    }

    public void setUrlAutenticacion(String urlAutenticacion) {
        this.urlAutenticacion = urlAutenticacion;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
