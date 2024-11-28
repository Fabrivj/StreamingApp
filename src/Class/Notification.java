package Class;

public class Notification {

    private int idNotification;
    private String message;
    private int idUser;
    /**
     * Constructor para crear un objeto Notification.
     *
     * @param idNotification ID único de la notificación.
     * @param message        Mensaje de la notificación.
     * @param idUser         ID del usuario que recibe la notificación.
     */
    public Notification(int idNotification, String message, int idUser) {
        this.idNotification = idNotification;
        this.message = message;
        this.idUser = idUser;
    }

    /**
     * Obtiene el ID único de la notificación.
     *
     * @return ID único de la notificación.
     */
    public int getIdNotification() {
        return idNotification;
    }

    /**
     * Establece el ID único de la notificación.
     *
     * @param idNotification ID único de la notificación.
     */
    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdUser() {
        return idUser;
    }


    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
