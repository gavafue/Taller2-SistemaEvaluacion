package logica;

/**
 * Esta clase permite crear un usuario con su respectivo nombre de usuario y
 * contraseña.
 *
 * @author
 * @since version 2
 */
public class Usuario {

    //Atributos
    private String nombreUsuario;
    private String contrasenia;
    //private String tipo; @todo para identificar si es docente, adminstrativo o estudiante.

    //Constructor comun
    public Usuario(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    //Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    //Setters
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
