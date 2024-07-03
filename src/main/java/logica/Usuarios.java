package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Esta clase permite crear un hash de usuarios.
 *
 * @author
 * @since version 2
 */
public class Usuarios {

    //Atributos
    private HashMap<String, Usuario> hashUsuarios;

    //Constructor vacio
    public Usuarios() {
        this.hashUsuarios = new HashMap<String, Usuario>();
        cargarUsuarios();
    }

    //Getter
    public HashMap<String, Usuario> getListaUsuarios() {
        return hashUsuarios;
    }

    //Setter
    public void setListaUsuarios(HashMap<String, Usuario> hashUsuarios) {
        this.hashUsuarios = hashUsuarios;
    }

    /**
     * Este metodo permite dado un usuario agregarlo a la lista.
     *
     * @param usuario
     */
    public void agregarUsuario(Usuario usuario) {
        this.getListaUsuarios().put(usuario.getNombreUsuario(), usuario);
    }

    /**
     * Este metodo permite eliminar un usuario dado su nombre.
     *
     * @param nombreUsuario
     */
    public void eliminarUsuario(String nombreUsuario) {
        this.getListaUsuarios().remove(nombreUsuario);
    }

    /**
     * Este metodo permite obtener el objeto Usuario dado su nombre.
     *
     * @param nombreUsuario
     * @return
     */
    public Usuario obtenerUsuario(String nombreUsuario) {
        return this.getListaUsuarios().get(nombreUsuario);
    }

    /**
     * Este metodo permite confirmar que existe un usuario ingresado con ese
     * nombre.
     */
    public boolean existeUsuario(String nombreUsuario) {
        return getListaUsuarios().containsKey(nombreUsuario);
    }

    /**
     * Este metodo permite confirmar que existe un usuario ingresado con ese
     * nombre y password. Se utiliza para el login.
     */
    public boolean existeUsuarioLogin(String nombreUsuario, String password) {
        return getListaUsuarios().containsKey(nombreUsuario) && getListaUsuarios().get(nombreUsuario).getContrasenia().equals(password);
    }

    /**
     * Metodo que permite cargar al sistema los usuarios extraidos de un archivo
     * de texto.
     */
    public void cargarUsuarios() {
        try {
            Scanner s = new Scanner(new File("passwords.txt"));
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                String[] usuario = linea.split(";"); // de momento dos posiciones. Podria haber un tercer
                // atributo que identifique tipo de usuario.
                this.agregarUsuario(new Usuario(usuario[0], usuario[1]));
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace(); // @todo Despues tenemos que redirigir estos errores a un log.
        }
    }

    /**
     * Metodo que actualiza la contraseña de un usuario, identifacdo por nombre.
     *
     * @param nombre
     * @param nuevaContrasenia
     */
    public void actualizarContrasenia(String nombre, String nuevaContrasenia) {
        obtenerUsuario(nombre).setContrasenia(nuevaContrasenia);
        perisistirUsuarios();
    }

    /**
     * Metodo que persiste los usuarios del sistema en su totalidad.
     *
     * @todo, en un futuro solo persistir solo el modificado.
     *
     */
    private void perisistirUsuarios() {
        try {
            FileWriter fw = new FileWriter("passwords.txt");
            for (Map.Entry<String, Usuario> entry : hashUsuarios.entrySet()) { //CODIFICACION SUGERIDA EN LA DOCUMENTACION OFICIAL DE JAVA.
                fw.write(entry.getKey() + ";" + entry.getValue().getContrasenia() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace(); //@todo, despues tenemos que redirigir estos errores a un log.
        }
    }
}
