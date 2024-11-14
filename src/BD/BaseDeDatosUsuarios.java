package BD;

import Class.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code BaseDeDatosUsuarios} simula una base de datos de usuarios en memoria.
 * Contiene una lista de objetos {@code Usuario} y proporciona métodos para obtener un usuario y validar credenciales.
 * Esta clase incluye algunos usuarios predefinidos de prueba.
 *
 * @see Usuario
 */
public class BaseDeDatosUsuarios {

    private List<Usuario> usuarios;

    /**
     * Constructor de la clase {@code BaseDeDatosUsuarios}.
     * Inicializa la lista de usuarios con algunos usuarios predefinidos para pruebas.
     */
    public BaseDeDatosUsuarios() {
        this.usuarios = new ArrayList<>();

        // Agregar usuarios predefinidos
        usuarios.add(new Usuario("fabrivj", "Cenfo24", "fvargasj@ucenfotec.ac.cr", "admin"));
        usuarios.add(new Usuario("Andrey", "Andrey1234", "afbonilla1234@gmail.com", "admin"));
        // Puedes agregar más usuarios de prueba aquí
        usuarios.add(new Usuario("juan", "Contra123", "juan@correo.com", "user"));
    }

    /**
     * Obtiene un usuario de la lista según el nombre de usuario.
     *
     * @param username el nombre de usuario que se desea buscar
     * @return el objeto {@code Usuario} si se encuentra un usuario con el nombre especificado,
     *         o {@code null} si no se encuentra
     */
    public Usuario obtenerUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;  // Si no se encuentra el usuario
    }

    /**
     * Valida las credenciales del usuario verificando el nombre de usuario y la contraseña.
     *
     * @param username el nombre de usuario ingresado para la autenticación
     * @param password la contraseña ingresada para la autenticación
     * @return {@code true} si el nombre de usuario y la contraseña son correctos;
     *         {@code false} en caso contrario
     */
    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = obtenerUsuario(username);
        return usuario != null && usuario.getPassword().equals(password);
    }
}
