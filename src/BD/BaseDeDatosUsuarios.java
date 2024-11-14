package BD;

import Class.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosUsuarios {

    private List<Usuario> usuarios;

    public BaseDeDatosUsuarios() {
        this.usuarios = new ArrayList<>();

        // Agregar usuarios predefinidos
        usuarios.add(new Usuario("fabrivj", "Cenfo24", "fvargasj@ucenfotec.ac.cr", "admin"));
        usuarios.add(new Usuario("Andrey", "Andrey1234", "afbonilla1234@gmail.com", "admin"));
        // Puedes agregar más usuarios de prueba aquí
        usuarios.add(new Usuario("juan", "Contra123", "juan@correo.com", "user"));
    }

    // Método para obtener un usuario por nombre de usuario
    public Usuario obtenerUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;  // Si no se encuentra el usuario
    }

    // Método para validar las credenciales
    public boolean validarCredenciales(String username, String password) {
        Usuario usuario = obtenerUsuario(username);
        return usuario != null && usuario.getPassword().equals(password);
    }
}
