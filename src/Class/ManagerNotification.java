package Class;

import java.util.Date;

public class ManagerNotification {

    private int idManagerNotification;
    private String notificationType;
    private Date sendDate;

    /**
     * Constructor para crear un objeto ManagerNotification.
     *
     * @param idManagerNotification ID único del gestor de notificaciones.
     * @param notificationType      Tipo de notificación.
     * @param sendDate              Fecha de envío de la notificación.
     */
    public ManagerNotification(int idManagerNotification, String notificationType, Date sendDate) {
        this.idManagerNotification = idManagerNotification;
        this.notificationType = notificationType;
        this.sendDate = sendDate;
    }

    /**
     * Obtiene el ID del gestor de notificaciones.
     *
     * @return ID del gestor de notificaciones.
     */
    public int getIdManagerNotification() {
        return idManagerNotification;
    }

    /**
     * Establece el ID del gestor de notificaciones.
     *
     * @param idManagerNotification ID único del gestor de notificaciones.
     */
    public void setIdManagerNotification(int idManagerNotification) {
        this.idManagerNotification = idManagerNotification;
    }


    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }


    public Date getSendDate() {
        return sendDate;
    }


    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
