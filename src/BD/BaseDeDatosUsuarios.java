package BD;

import Class.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatosUsuarios {

    private List<Usuario> usuarios;

    public BaseDeDatosUsuarios() {
        this.usuarios = new ArrayList<>();

        // Agregar usuarios predefinidos
        usuarios.add(new Usuario("fabrivj", "Cenfo24", "fvargasj@ucenfotec.ac.cr", "premium"));
        usuarios.add(new Usuario("Andrey", "1234", "afbonilla1234@gmail.com", "premium"));
        usuarios.add(new Usuario("Luis", "1234", "Lvegaa@ucenfotec.ac.cr", "basic"));
    }

    public Usuario obtenerUsuario(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;  // Si no se encuentra el usuario
    }

}
