package consola;

import java.util.ArrayList;

/**
 * Esta clase permite gestionar una coleccion de ficheros.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public class Ficheros {

    /**
     * Coleccion.
     */
    private ArrayList<Fichero> listaFicheros;

    /**
     * Constructor.
     */
    public Ficheros() {
        listaFicheros = new ArrayList<Fichero>();
    }

    /**
     * @return listaficheros
     */
    public ArrayList<Fichero> getListaFicheros() {
        return listaFicheros;
    }

    /**
     * Establece el valor de listaFicheros recibido por
     *
     * @param listaFicheros
     */
    public void setListaFicheros(ArrayList<Fichero> listaFicheros) {
        this.listaFicheros = listaFicheros;
    }

    /**
     * Metodo que determina si existe un fichero a partir de un ficheroBuscado.
     *
     * @param ficheroBuscado del fichero
     * @return true si existe el fichero.
     */
    public boolean existeFichero(String ficheroBuscado) {
        boolean existe = false;
        for (Fichero existente : listaFicheros) {
            if (existente.getNombre().equals(ficheroBuscado)) {
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
     * @param ficheroBuscado fichero
     * @return si es un directorio
     */
    public boolean esDirectorio(String ficheroBuscado) {
        int i = 0;
        while (!this.obtenerFichero(i).getNombre().equals(ficheroBuscado)) {
            i++;
        }
        return this.obtenerFichero(i).getTipo().equals("Directorio");
    }

    /**
     * Metodo que permite obtener los nombres de los ficheros (archivos y
     * directorios) existentes.
     *
     * @param ocultos (si se devuelven los arhivos ocultos o no)
     * @return nombres de los arhivos existentes
     */
    public String obtenerNombres(boolean ocultos) {
        String nombres = "";
        for (int i = 0; i < listaFicheros.size(); i++) {
            if (!ocultos) { //Si no quiero listar los ocultos
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
                    detalles += (listaFicheros.get(i).toString() + "\n");
                }
            } else {
                detalles += (listaFicheros.get(i).toString() + "\n");
            }
        }
        return detalles;
    }

    /**
     * Metodo que carga los ficheros iniciales en el sistema.
     *
     *
     * ##### Esto deberia estar afuera de Ficheros. Porque se arrastra (esta
     * disponible) en todos los directorios que se creen, ya que su contenido es
     * de tipo Ficheros. ####
     */
    public void cargarPrimerNivel() {
        // ############################## ARCHIVOS #############################
        Archivo arch1 = new Archivo("arch1.csv");
        arch1.setContenido("campo1:campo2:campo3:campo4\n"
                + "dato1:dato2:dato3:dato4\n"
                + "informacion1:informacion2:informacion3:informacion4");
        Archivo arch2 = new Archivo("arch2.txt",
                "La Celeste se impuso por 3-1 en Miami, \n"
                + "donde Maximiliano Araujo abrió el marcador a los 15 del primer tiempo \n"
                + "y debió esperar hasta los 39 del complemento para asegurar el triunfo.\n"
                + "Darwin Núñez y Federico Viña hicieron los goles del equipo de Bielsa\n"
                + " sobre el final, y en la última descontó Panamá por un lindo gol de \n"
                + "Murillo."
                + "FIN.");
        Archivo arch22 = new Archivo("arch22.txt");
        arch22.setContenido("La elaboración de software de computadora es un \n"
                + "proceso reiterativo de aprendizaje social,\n"
                + "y el resultado es la reunión de conocimiento recabado, \n"
                + "depurado y organizado a medida que se realiza el proceso.\n"
                + "Pressman(2010)\n");
        Archivo arch33 = new Archivo("arch33.odt");
        arch33.setContenido("Linea 1 \n"
                + "linea 2 \n"
                + "linea 3 \n"
                + "linea 4 \n"
                + "linea 5 \n"
                + "linea 6 \n"
                + "linea 7 \n"
                + "linea 8 \n"
                + "linea 9 \n"
                + "linea 10 \n"
                + "linea 11 \n"
                + "linea 12."
                + "FIN. \n");
        Archivo m = new Archivo("m.html",
                "Hola \n"
                + "10.Chau \n"
                + "99.Hola 232342\n"
                + "Chau\n"
                + "9\n"
                + "1\n"
                + "11\n");
        Archivo s = new Archivo(".secreto.txt", "Este archivo esta oculto");
        Archivo s1 = new Archivo(".Secreto1.txt", "Este archivo esta oculto");
        Archivo s2 = new Archivo(".granSecreto2.txt", "Este archivo esta oculto");
        Archivo s3 = new Archivo(".secretoPequeño.txt", "Este archivo esta oculto");
        Archivo s4 = new Archivo(".secretito.txt", "Este archivo esta oculto");
        Archivo algunosNumeros = new Archivo("algunosNumeros.txt",
                "10\n3\n7\n300\n40\n1\n230\n540\n23\n76\n12\n7");
        Archivo estatuto = new Archivo("estatuto.pdf",
                "Artículo 4. Son derechos específicos del funcionario docente:\n"
                + "a) Ejercer sus funciones en el marco de la libertad de cátedra, respetando\n"
                + "la orientación general fijada en los planes de estudio, cumpliendo el\n"
                + "programa respectivo y asegurando la consideración crítica de las diversas\n"
                + "tendencias cuando corresponda.\n"
                + "b) La libertad de conciencia y la libertad de opinión, sean éstas de orden\n"
                + "religioso, filosófico, político o de cualquier otra índole, dentro del más\n"
                + "estricto marco de laicidad, preservando la libertad de los educandos ante\n"
                + "cualquier forma de coacción.\n"
                + "Los Consejos respectivos adoptarán las providencias necesarias para hacer\n"
                + "efectiva esta disposición.\n"
                + "c) Perfeccionar sus aptitudes técnico-pedagógicas, para lo cual contará con\n"
                + "el apoyo del Ente, de conformidad con las reglamentaciones que se dicten\n"
                + "al respecto.\n"
                + "d) Reunirse en los locales donde se presten servicios para la consideración\n"
                + "de temas culturales y técnico docentes. El ejercicio de este derecho no\n"
                + "podrá perjudicar el normal funcionamiento del servicio y deberá ajustarse a\n"
                + "las normas reglamentarias.\n"
                + "e) Ser calificado anualmente en su labor del modo que determine la\n"
                + "reglamentación respectiva. La omisión de calificación por causas no\n"
                + "imputables al interesado deberá ser denunciada a la jerarquía inspectiva\n"
                + "superior, quien adoptará las medidas pertinentes para subsanar la falta.\n"
                + "f) Acceder a su legajo para verificar su contenido y solicitar las\n"
                + "aclaraciones, adiciones y enmiendas que correspondieran.\n"
                + "g) Tener estabilidad en su cargo y grado, de acuerdo con las disposiciones\n"
                + "de este Estatuto y las reglamentaciones complementarias dictadas en\n"
                + "interés del Servicio.\n"
                + "h) Acceder al traslado o reubicación conforme a los reglamentos dictados por\n"
                + "el Consejo Directivo Central o Consejo respectivo. El ejercicio de este derecho\n"
                + "no puede afectar la estabilidad en el cargo de otros funcionarios docentes\n"
                + "efectivos.\n");

        // ############################## DIRECTORIOS ##########################
        Directorio dir1 = new Directorio("dir1");
        dir1.agregarContenido(m);
        dir1.agregarContenido(estatuto);
        dir1.agregarContenido(arch2);
        dir1.agregarContenido(arch1);
        dir1.agregarContenido(arch33);
        dir1.agregarContenido(arch22);
        dir1.agregarContenido(estatuto);

        Directorio dir2 = new Directorio("dir2");
        dir2.agregarContenido(arch1);
        dir2.agregarContenido(dir1);
        dir2.agregarContenido(dir2);
        Directorio dir3 = new Directorio("dir3");
        dir3.agregarContenido(arch33);
        dir3.agregarContenido(dir2);
        dir3.agregarContenido(arch22);
        dir3.agregarContenido(dir2);

        Directorio dir4 = new Directorio("dir4"); // vacio
        Directorio dir5 = new Directorio("dir5"); // vacio

        /* Cargo el directorio raiz con ficheros iniciales */
        agregarFichero(arch1);
        agregarFichero(dir1);
        agregarFichero(dir2);
        agregarFichero(arch2);
        agregarFichero(dir3);
        agregarFichero(arch22);
        agregarFichero(arch33);
        agregarFichero(m);
        agregarFichero(s);
        agregarFichero(algunosNumeros);
        agregarFichero(estatuto);
        agregarFichero(dir4);
        agregarFichero(dir5);
    }

    /**
     *
     * @return resumen de la coleccion en formato String. Devuleve cada elemento
     * por linea, agregando un '\n' al final.
     */
    @Override
    public String toString() {
        String resumen = "";
        for (Fichero f : getListaFicheros()) {
            resumen += f.toString() + "\n";
        }
        return resumen;
    }
}
