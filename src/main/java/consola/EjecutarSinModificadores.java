package consola;

import javax.swing.JTextPane;

/**
 * Esta clase se encarga de ejecutar los comandos que NO utilizan modificadores
 * "-"
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 *
 */
public class EjecutarSinModificadores {

    /**
     * La linea ingresada en la terminal por el usuario fue tokenizada y
     * almacenada aqui.
     */
    private String[] tokens;

    /**
     * Constructor.
     *
     * @param tokens - La linea ingresada en la terminal por el usuario fue
     * tokenizada
     *
     */
    public EjecutarSinModificadores(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Método que permite obtener el comando ingresado dividido en tokens.
     *
     * @return tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Método que permite establecer los tokens actuales a partir de otros
     * tokens.
     *
     * @param tokens los tokens para establecer.
     */
    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Metodo principal de esta clase. Deriva la ejecucion al comando
     * correspondiente.
     *
     * @param comandos - coleccion de comandos cargados en el sistema
     * @param ficheros - ficheros cargados en el sistema
     * @param procesos - procesos activos en el sistema
     * @param salida - donde mostrar el resultado de la ejecucion.
     */
    public String ejecutarComandoSinMod(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextPane salida) {
        String mensaje;
        switch (tokens[0]) {
            case "man":
                mensaje = ejecutarMan(comandos);
                break;
            case "mkdir":
                mensaje = ejecutarMkdir(ficheros);
                break;
            case "rmdir":
                mensaje = ejecutarRmdir(ficheros);
                break;
            case "clear":
                mensaje = ejecutarClear(salida);
                break;
            case "cat":
                mensaje = ejecutarCat(ficheros);
                break;
            case "mv":
                mensaje = ejecutarMv(ficheros);
                break;
            case "ps":
                mensaje = ejecutarPs(procesos);
                break;
            case "kill":
                mensaje = ejecutarKill(procesos);
                break;
            case "chmod":
                mensaje = ejecutarChmod(ficheros);
                break;
            default:
                mensaje = ">> Comando inexistente <<\n";
                break;
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando man.
     *
     * @param comandos Objeto que contiene los comandos disponibles.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarMan(Comandos comandos) {
        String mensaje;
        if (tokens != null && tokens.length == 1) {
            mensaje = comandos.obtenerDescripcion("man") + "\n" + comandos.obtenerEjemplo("man");
        } else {//Si tiene mas de 1 token es la ayuda de un comando
            mensaje = obtenerAyudaComando(comandos);
        }
        return mensaje;
    }

    /**
     * Este metodo formatea un mensaje de ayuda obteniendo informacion desde los
     * atributos del comando.
     *
     * @param comandos - comandos cargados en el sistema
     * @return mensaje
     */
    public String obtenerAyudaComando(Comandos comandos) {
        String mensaje;
        try {
            mensaje = "[AYUDA]\n Descripcion: " + comandos.obtenerDescripcion(tokens[1]) + "\n Ejemplos: "
                    + comandos.obtenerEjemplo(tokens[1]);
        } catch (Exception e) {
            mensaje = ">> Error al consultar informacion sobre el comando <<" + tokens[1];
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando mkdir para crear directorios.
     *
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @return mensaje resultante de la operacion.
     */
    public String ejecutarMkdir(Ficheros ficheros) {
        String mensaje;

        mensaje = crearDirectorio(tokens[1], ficheros);

        return mensaje;
    }

    /**
     * Crea un directorio si no existe previamente.
     *
     * @param nombreDirectorio Nombre del directorio a crear.
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @return Mensaje resultante de la operación.
     */
    private String crearDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje;
        if (!ficheros.existeFichero(nombreDirectorio)) {
            Directorio nuevo = new Directorio(nombreDirectorio);
            ficheros.agregarFichero(nuevo);
            mensaje = "-Se ha creado el directorio " + nombreDirectorio + "-\n";
        } else {
            mensaje = ">> Ya existe un fichero con el nombre " + nombreDirectorio + "-\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando rmdir para eliminar directorios.
     *
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarRmdir(Ficheros ficheros) {
        String mensaje = eliminarDirectorio(tokens[1], ficheros);
        return mensaje;
    }

    /**
     * Elimina un directorio si existe.
     *
     * @param nombreDirectorio String con el nombre del directorio a eliminar.
     * @param ficheros Objeto que gestiona los ficheros y directorios.
     * @return mensaje resultante de la operación.
     */
    private String eliminarDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            ficheros.eliminarFichero(nombreDirectorio);
            mensaje = "-Directorio eliminado-\n";
        } else {
            mensaje = ">> No existe un directorio con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando clear para limpiar el área de salida. Es decir, limpia
     * la consola estableciendo su texto vacio.
     *
     * @param salida JTextÁrea que se limpia.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarClear(JTextPane salida) {
        String mensaje = "";
        salida.setText("");
        return mensaje;
    }

    /**
     * Ejecuta el comando cat para mostrar el contenido de un archivo si existe
     * y no es un directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     *
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarCat(Ficheros ficheros) {
        String mensaje;
        String nombreArchivo = tokens[1];
        if (ficheros.existeFichero(nombreArchivo) && !ficheros.esDirectorio(nombreArchivo)) {
            Fichero archivo = ficheros.obtenerFichero(nombreArchivo);
            mensaje = archivo.obtenerResumenDelContenido() + "\n";
        } else {
            mensaje = ">> No existe un archivo con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando mv para renombrar un archivo si existe y no es un
     * directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     *
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarMv(Ficheros ficheros) {
        String mensaje;
        String nombreActual = tokens[1];
        String nombreNuevo = tokens[2];
        if (nombreActual.equals(nombreNuevo)) {
            mensaje = ">> Nombre actual y nombre propuesto son el mismo. <<\n";
        } else {
            // Verifica si el archivo existe y no es un directorio.
            if (ficheros.existeFichero(nombreActual)) {
                // Encuentra el índice del archivo en la lista.
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(nombreActual)) {
                    i++;
                }
                // Cambia el nombre del archivo.
                ficheros.obtenerFichero(i).setNombre(nombreNuevo);
                // Construye el mensaje de éxito.
                mensaje = "-El fichero " + nombreActual + " ha sido renombrado a "
                        + nombreNuevo
                        + "-\n";
            } else {
                // Si no existe el archivo, muestra un mensaje de error.
                mensaje = ">> No existe un archivo con ese nombre <<\n";
            }
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando kill para terminar uno de los procesos en ejecucion
     *
     * @param procesos en ejecucion
     * @return
     */
    public String ejecutarKill(Procesos procesos) {
        String mensaje;
        String procesoIdStr = tokens[1];
        try {
            int procesoID = Integer.parseInt(procesoIdStr);
            if (procesos.existeProceso(procesoID)) {
                procesos.getListaProcesos().remove(procesos.obtenerProceso(procesoID));
                mensaje = "-Proceso eliminado-\n";
            } else {
                mensaje = ">> No existe proceso con pid " + procesoID + " <<\n";
            }
        } catch (NumberFormatException e) {
            mensaje = ">> No existe proceso con pid " + procesoIdStr + " | DEBE SER NUMERO. <<\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando ps para listar los procesos en ejecución del sistema.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @return un mensaje detallando los procesos en ejecución o un mensaje de
     * error si la sintaxis es incorrecta.
     */
    public String ejecutarPs(Procesos procesos) {
        String mensaje;

        if (procesos.getListaProcesos().isEmpty()) {
            mensaje = ">> No existen procesos en ejecución <<\n";
        } else {
            mensaje = "PID | USER | % MEMORY | % CPU | TIME | COMMAND\n";
            for (Proceso proceso : procesos.getListaProcesos()) {
                mensaje += proceso.toString() + "\n";
            }
        }
        return mensaje;
    }

    /**
     * Método que permite modificar los permisos de un archivo y/o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @return Mensaje a imprimir en consola.
     */
    public String ejecutarChmod(Ficheros ficheros) {
        String permisos = tokens[1];
        String fich = tokens[2];
        String mensaje;
        if (!ficheros.existeFichero(fich)) {
            // Verificar si el fichero existe
            mensaje = ">> Sintaxis incorrecta: el fichero o directorio] '" + fich + "' no existe <<\n";
        } else if (!validarLongitudPermisos(permisos)) {
            // Validar longitud de permisos
            mensaje = ">> Sintaxis incorrecta: longitud incorrecta de permisos <<\n";
        } else {
            switch (permisos.length()) {
                case 3:
                    if (!aplicarPermisosNumericos(ficheros, fich, permisos)) {
                        mensaje = ">> Sintaxis incorrecta: valor de permisos inválido <<\n";
                    } else {
                        mensaje = "-Comando ejecutado correctamente-\n";
                    }
                    break;
                case 9:
                    if (!aplicarPermisosSimbolicos(ficheros, fich, permisos)) {
                        mensaje = ">> Sintaxis incorrecta: permisos inválidos <<\n";
                    } else {
                        mensaje = "-Comando ejecutado correctamente-\n";
                    }
                    break;
                default:
                    mensaje = ">> Sintaxis incorrecta: longitud incorrecta de permisos <<\n";
            }
        }
        return mensaje;
    }

    /**
     * Valida la longitud de los permisos.
     *
     * @param permisos String con los permisos a validar.
     * @return false si la longitud de los persmisos no es correcta. Asume true
     * por defecto.
     */
    private boolean validarLongitudPermisos(String permisos) {
        boolean retorno = true;
        if (permisos.length() != 3 && permisos.length() != 9) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Aplica los permisos numéricos al fichero (archivo o directorio).
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param nombre del fichero o directorio al que se aplicarán los permisos.
     * @param permisos String con los permisos numéricos.
     * @return true si se aplicaron los permisos correctamente, false de lo
     * contrario.
     */
    private boolean aplicarPermisosNumericos(Ficheros ficheros, String nombre, String permisos) {
        String permisosEnLetras = String.valueOf(ficheros.obtenerFichero(nombre).getPermisos().charAt(0));
        boolean permisosValidos = true;
        for (int i = 0; i < 3; i++) {
            int permisoNum = Character.getNumericValue(permisos.charAt(i));
            if (permisoNum < 0 || permisoNum > 7) {
                permisosValidos = false;
            }
            switch (permisoNum) {
                case 0:
                    permisosEnLetras += "---";
                    break;
                case 1:
                    permisosEnLetras += "--x";
                    break;
                case 2:
                    permisosEnLetras += "-w-";
                    break;
                case 3:
                    permisosEnLetras += "-wx";
                    break;
                case 4:
                    permisosEnLetras += "r--";
                    break;
                case 5:
                    permisosEnLetras += "r-x";
                    break;
                case 6:
                    permisosEnLetras += "rw-";
                    break;
                case 7:
                    permisosEnLetras += "rwx";
                    break;
            }
        }
        if (permisosValidos) {
            ficheros.obtenerFichero(nombre).setPermisos(permisosEnLetras);
        }
        return permisosValidos;
    }

    /**
     * Aplica los permisos simbólicos al fichero o directorio.
     *
     * @param ficheros Objeto que maneja los ficheros y directorios.
     * @param fich Nombre del fichero o directorio al que se aplicarán los
     * permisos.
     * @param permisos String con los permisos simbólicos.
     * @return true si se aplicaron los permisos correctamente, false de lo
     * contrario.
     */
    private boolean aplicarPermisosSimbolicos(Ficheros ficheros, String fich, String permisos) {
        boolean permisosValidos = true;
        for (int i = 0; i < permisos.length(); i++) {
            char permiso = permisos.charAt(i);
            switch (i % 3) {
                case 0: // r
                    if (permiso != 'r' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
                case 1: // w
                    if (permiso != 'w' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
                case 2: // x
                    if (permiso != 'x' && permiso != '-') {
                        permisosValidos = false;
                    }
                    break;
            }
            if (!permisosValidos) {
                break;
            }
        }
        if (permisosValidos) {
            char primerCaracter = ficheros.obtenerFichero(fich).getPermisos().charAt(0);
            permisos = primerCaracter + permisos;
            ficheros.obtenerFichero(fich).setPermisos(permisos);
        } else {
        }
        return permisosValidos;
    }
}
