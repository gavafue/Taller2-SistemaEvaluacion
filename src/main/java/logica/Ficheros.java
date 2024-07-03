package logica;

import java.util.LinkedList;

/**
 * Esta clase permite crear una lista de ficheros.
 *
 * @author
 * @since version 2
 */
public class Ficheros {

    //Atributos
    private LinkedList<Fichero> listaFicheros;

    //Constructor vacio
    public Ficheros() {
        listaFicheros = new LinkedList<Fichero>();
    }

    //Getter de la lista
    public LinkedList<Fichero> getListaFicheros() {
        return listaFicheros;
    }

    //Setter de la lista
    public void setListaFicheros(LinkedList<Fichero> listaFicheros) {
        this.listaFicheros = listaFicheros;
    }

    /**
     * Metodo que determina si existe un fichero a partir de un nombre.
     *
     * @param nombre del fichero
     * @return si existe el fichero
     */
    public boolean existeFichero(String nombre) {
        boolean existe = false;
        for (Fichero existente : listaFicheros) {
            if (existente.getNombre().equals(nombre)) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     * Metodo que permite agregar un fichero a la lista de ficheros.
     *
     * @param nuevo fichero
     */
    public void agregarFichero(Fichero nuevo) {
        if (!existeFichero(nuevo.getNombre())) {
            listaFicheros.add(nuevo);
        }
    }

    /**
     * Metodo que permite eliminar un fichero a partir del nombre. Asume que
     * existe el fichero a eliminar.
     *
     * @param nombre fichero
     */
    public void eliminarFichero(String nombre) {
        int i = 0;
        while (!this.obtenerFichero(i).getNombre().equals(nombre)) { //Sin control de rango porque existe
            i++; //Obtengo indice en el que se encuentra el fichero a eliminar
        }
        listaFicheros.remove(this.obtenerFichero(i));
    }

    /**
     * Metodo que retorna el fichero dado su posicion en la lista. Asume que el
     * numero de indice es valido.
     *
     * @param indice
     * @return fichero
     */
    public Fichero obtenerFichero(int indice) {
        return this.getListaFicheros().get(indice);
    }

    /**
     * Metodo que retorna el fichero dado su nombre.
     *
     * @param nombre del fichero a buscar.
     * @return encontrado Fichero encontrado en la coleccion.
     */
    public Fichero obtenerFichero(String nombre) {
        Fichero encontrado = null;
        for (Fichero f : listaFicheros) {
            if (f.getNombre().equals(nombre)) {
                encontrado = f;
            }
        }
        return encontrado;
    }

    /**
     * Metodo que retorna si un fichero es un directorio o no. Asume que existe
     * el fichero a evaluar.
     *
     * @param nombre fichero
     * @return si es un directorio
     */
    public boolean esDirectorio(String nombre) {
        int i = 0;
        while (!this.obtenerFichero(i).getNombre().equals(nombre)) { //Sin control de rango porque existe
            i++; //Obtengo indice en el que se encuentra el fichero
        }
        return this.obtenerFichero(i).getTipo().equals("Directorio");
    }

    /**
     * Metodo que permite obtener los nombres de los ficheros existentes.
     *
     * @param oculto (si se devuelven los arhivos ocultos o no)
     * @return nombres de los arhivos existentes
     */
    public String obtenerNombres(boolean oculto) {
        String nombres = "";
        for (int i = 0; i < listaFicheros.size(); i++) {
            if (!oculto) { //Si no quiero listar los ocultos
                if (listaFicheros.get(i).getNombre().matches("^[^.].*")) { //Omito los nombres que comienzan por punto
                    nombres += listaFicheros.get(i).getNombre();
                }
            } else {
                nombres += listaFicheros.get(i).getNombre();
            }
            //Un espacio si no es el ultimo
            if (i < listaFicheros.size() - 1) {
                nombres += " ";
            }
            //Cada 6 nombres, agregar un salto de linea
            if ((i + 1) % 6 == 0) {
                nombres += "\n";
            }
        }
        return nombres;
    }

    /**
     * Metodo que permite obetener los nombres de los ficheros existentes y su
     * informacion detallada.
     *
     * @param oculto (si se devuelven los arhivos ocultos o no)
     * @return nombres de los arhivos existentes e informacion detallada
     */
    public String obtenerInformacionDetallada(boolean oculto) { // para el ls -l
        String detalles = "";
        for (int i = 0; i < listaFicheros.size(); i++) {
            if (!oculto) { //si no quiero listar los ocultos
                if (listaFicheros.get(i).getNombre().matches("^[^.].*")) { //omito los nombres que comienzan por punto
                    detalles += ("\n" + listaFicheros.get(i).toString());
                }
            } else {
                detalles += ("\n" + listaFicheros.get(i).toString());
            }
        }
        return detalles;
    }

    /**
     * Metodo que carga los ficheros en el sistema.
     */
    public void cargarPrimerNivel() {
        Directorio dir1 = new Directorio("dir1");
        Archivo arch1 = new Archivo("arch1");
        arch1.setContenido("campo1:campo2:campo3:campo4\n" +
                "dato1:dato2:dato3:dato4\n" +
                "informacion1:informacion2:informacion3:informacion4"); //Este set contenido podria estar en el constructor.
        Directorio dir4 = new Directorio("dir4");
        Archivo dir5 = new Archivo("dir5");
        Directorio dir3 = new Directorio("dir3");
        Archivo arch2 = new Archivo("arch2", "La Celeste se impuso por 3-1 en Miami,\n donde Maximiliano Araujo abrió el marcador a los 15 del primer tiempo\n y debió esperar hasta los 39 del complemento para asegurar el triunfo.\n Darwin Núñez y Federico Viña hicieron los goles del equipo de Bielsa sobre el final,\n y en la última descontó Panamá por un lindo gol de Murillo. FIN.");
        Archivo arch22 = new Archivo("arch22");
        arch22.setContenido("\n Los funcionarios estadounidenses han impuesto sanciones a gran parte de los altos directivos de Kaspersky Lab\n después de una reciente medida para prohibir su software antivirus,\n presentando la nueva medida como una respuesta a los continuos riesgos de ciberseguridad.\n El viernes, funcionarios del Departamento del Tesoro impusieron sanciones a 12 altos directivos de Kaspersky,\n pero optaron por no sancionar a la propia empresa ni a su director ejecutivo, Eugene Kaspersky. Los funcionarios estadounidenses han impuesto sanciones a gran parte de los altos directivos de Kaspersky Lab\n después de una reciente medida para prohibir su software antivirus,\n presentando la nueva medida como una respuesta a los continuos riesgos de ciberseguridad.\n El viernes, funcionarios del Departamento del Tesoro impusieron sanciones a 12 altos directivos de Kaspersky,\n pero optaron por no sancionar a la propia empresa ni a su director ejecutivo, Eugene Kaspersky."); //arch1 tiene contenido inicialmente
        Archivo arch33 = new Archivo("arch33");
        arch33.setContenido("Linea 1 \nlinea 2 \nlinea 3 \n linea 4 \n linea 5 \n linea 6 \n linea 7 \n linea 8 \n linea 9 \n linea 10 \n linea 11 \n linea 12. FIN. \n");

//Cargo el directorio raiz con ficheros iniciales
        agregarFichero(arch1);
        agregarFichero(dir1);
        agregarFichero(dir4);
        agregarFichero(dir5);
        agregarFichero(arch2);
        agregarFichero(dir3);
        agregarFichero(arch22);
        agregarFichero(arch33);

    }
}
