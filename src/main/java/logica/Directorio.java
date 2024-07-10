package logica;

/**
 * Esta clase permite crear un fichero de tipo directorio.
 *
 * @author
 * @since version 2
 */
public class Directorio extends Fichero {

    //Atributos
    private Ficheros contenido; //Suponiendo que va a ser navegable

    //Constructor comun
    public Directorio(String nombre) {
        super(nombre,"drwxr--r--","user","Directorio");
        contenido = new Ficheros();
    }

    //Getter
    public Ficheros getContenido() {
        return contenido;
    }

    //Setter
    public void setContenido(Ficheros contenido) {
        this.contenido = contenido;
    }
    
    @Override
    public String obtenerContenido(){
        return "Esto es un directorio";
    }

    
}
