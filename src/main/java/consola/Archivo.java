package consola;

/**
 * Este clase permite crear un fichero de tipo archivo.
 *
 * @author
 * @since version 2
 */
public class Archivo extends Fichero {

    //Atributos
    private String contenido;

    //Constructor comun
    public Archivo(String nombre) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        contenido = "[vacio]";
    }

    /**
     * Este constructor admite como parametro el contenido del archivo.
     */
    public Archivo(String nombre, String contenido) {
        super(nombre, "-rwxr--r--", "user", "Archivo");
        this.contenido = contenido;
    }

    //Getter
    public String getContenido() {
        return contenido;
    }

    //Setter
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String obtenerContenido() {
        return contenido;
    }
}
