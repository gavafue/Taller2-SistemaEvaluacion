package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;

/**
 * Esta clase se encarga de ejecutar los comandos.
 *
 */
public class Ejecutar {

    /**
     * La linea ingresada en la terminal por el usuario fue tokenizada y
     * almacenada aqui.
     */
    private String[] tokens;

    /**
     * Hora de ejecucion.
     */
    private String hora;

    /**
     * Formato para mostrar hora en cada ejecucion.
     */
    private DateTimeFormatter formatoFecha;

    /**
     * Constructor.
     *
     * @param tokens - La linea ingresada en la terminal por el usuario fue tokenizada
     *
     */
    public Ejecutar(String[] tokens) {
        this.tokens = tokens;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora = LocalDateTime.now().format(formatoFecha);
    }

    /**
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @return tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Establece la hora pasada por
     *
     * @param hora - hora a asignar.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Establece la hora tomandola del sistema.
     */
    private void actualizarHora() {
        hora = LocalDateTime.now().format(formatoFecha);
    }

    /**
     * Este metodo formatea un mensaje de ayuda obteniendo informacion desde los
     * atributos del comando.
     *
     * @param comandos - comandos cargados en el sistema
     * @return mensaje
     */
    public String obtenerAyudaComando(Comandos comandos) {
        String mensaje = "";
        try {
            mensaje = "[AYUDA]\n Descripcion: " + comandos.obtenerDescripcion(tokens[1]) + "\n Ejemplos: "
                    + comandos.obtenerEjemplo(tokens[1]);
        } catch (Exception e) {
            mensaje = "! Error al consultar informacion sobre el comando" + tokens[1];
        }
        return mensaje;
    }

    /**
     * Metodo principal de esta clase. Deriva la ejecucion al comando
     * correspondiente.
     *
     * @param comandos - coleccion de comandos cargados en el sistema
     * @param ficheros - ficheros cargados en el sistema
     * @param procesos - procesos activos en el sistema
     * @param salida   - donde mostrar el resultado de la ejecucion.
     */
    public String ejecutarComando(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextArea salida) {
        String mensaje = "";
        actualizarHora();

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
            case "ls":
                mensaje = ejecutarLs(ficheros);
                break;
            case "ps":
                mensaje = ejecutarPs(procesos);
                break;
            case "kill":
                mensaje = ejecutarKill(procesos);
                break;
            case "grep":
                mensaje = ejecutarGrep(ficheros);
                break;
            case "tail":
                mensaje = ejecutarTail(ficheros);
                break;
            case "head":
                mensaje = ejecutarHead(ficheros);
                break;
            case "cut":
                mensaje = ejecutarCut(ficheros);
                break;
            case "sort":
                mensaje = ejecutarSort(ficheros);
                break;
            case "chmod":
                mensaje = ejecutarChmod(ficheros);
                break;

            default:
                mensaje = "Sintaxis incorrecta\n";
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
        String mensaje = "";
        if (tokens != null && tokens.length == 1) {
            mensaje = comandos.obtenerDescripcion("man") + "\n" + comandos.obtenerEjemplo("man");
        } else if (tokens.length == 2) {
            mensaje = obtenerAyudaComando(comandos);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man [nombre_del_comando] o man";
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
        String mensaje = "";
        if (tokens != null && tokens.length == 2) {
            mensaje = crearDirectorio(tokens[1], ficheros);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente mkdir [nombre] o man mkdir\n";
        }
        return mensaje;
    }

    /**
     * Crea un directorio si no existe previamente.
     *
     * @param nombreDirectorio Nombre del directorio a crear.
     * @param ficheros         Objeto que gestiona los ficheros y directorios.
     * @return Mensaje resultante de la operación.
     */
    private String crearDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje = "";
        if (!ficheros.existeFichero(nombreDirectorio)) {
            Directorio nuevo = new Directorio(nombreDirectorio);
            ficheros.agregarFichero(nuevo);
            mensaje = "Se ha creado el directorio " + nombreDirectorio;
        } else {
            mensaje = "Ya existe un fichero con el nombre" + nombreDirectorio;
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
        String mensaje;
        if (tokens != null && tokens.length == 2) {
            mensaje = eliminarDirectorio(tokens[1], ficheros);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente rmdir [nombre] o man rmdir\n";
        }
        return mensaje;
    }

    /**
     * Elimina un directorio si existe.
     *
     * @param nombreDirectorio String con el nombre del directorio a eliminar.
     * @param ficheros         Objeto que gestiona los ficheros y directorios.
     * @return mensaje resultante de la operación.
     */
    private String eliminarDirectorio(String nombreDirectorio, Ficheros ficheros) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            ficheros.eliminarFichero(nombreDirectorio);
            mensaje = "Directorio eliminado\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando clear para limpiar el área de salida. Es decir,
     * limpia la consola estableciendo su texto vacio.
     *
     * @param salida JTextÁrea que se limpia.
     * @return Mensaje resultante de la ejecución.
     */
    public String ejecutarClear(JTextArea salida) {
        String mensaje;
        if (tokens != null && tokens.length == 1) {
            salida.setText("");
            mensaje = "";
        } else {
            mensaje = "Sintaxis incorrecta\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando cat para mostrar el contenido de un archivo si
     * existe y no es un directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     *
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarCat(Ficheros ficheros) {
        String mensaje;

        if (tokens.length != 2) {
            mensaje = "Sintaxis incorrecta.\nIntente cat [nombre] o man cat\n";
        } else {
            String nombreArchivo = tokens[1];
            if (ficheros.existeFichero(nombreArchivo) && !ficheros.esDirectorio(nombreArchivo)) {
                Fichero archivo = ficheros.obtenerFichero(nombreArchivo);
                mensaje = archivo.obtenerContenido() + "\n";
            } else {
                mensaje = "No existe un archivo con ese nombre\n";
            }
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando mv para renombrar un archivo si existe y no es
     * un directorio.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     *
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarMv(Ficheros ficheros) {
        String mensaje;

        // Verifica la cantidad de tokens para determinar la sintaxis correcta.
        if (tokens.length != 3) {
            mensaje = "[" + getHora() + "]\n"
                    + "Sintaxis incorrecta.\nIntente mv [nombreActual] [nombreNuevo] o man mv\n";
        } else {
            String nombreActual = tokens[1];
            String nombreNuevo = tokens[2];

            // Verifica si el archivo existe y no es un directorio.
            if (ficheros.existeFichero(nombreActual) && !ficheros.esDirectorio(nombreActual)) {

                // Encuentra el índice del archivo en la lista.
                int i = 0;
                while (!ficheros.obtenerFichero(i).getNombre().equals(nombreActual)) {
                    i++;
                }

                // Cambia el nombre del archivo.
                ficheros.obtenerFichero(i).setNombre(nombreNuevo);

                // Construye el mensaje de éxito.
                mensaje = "El fichero " + nombreActual + " ha sido renombrado a "
                        + nombreNuevo
                        + "\n";
            } else {
                // Si no existe el archivo, muestra un mensaje de error.
                mensaje = "No existe un archivo con ese nombre\n";
            }
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando ls para listar archivos y directorios según los
     * parámetros proporcionados.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarLs(Ficheros ficheros) {
        String mensaje;

        switch (tokens.length) {
            case 1:
                mensaje = obtenerListaSimple(ficheros);
                break;
            case 2:
                mensaje = manejarOpcionesUnParametro(ficheros);
                break;
            case 3:
                mensaje = manejarOpcionesDosParametros(ficheros);
                break;
            case 4:
                mensaje = manejarOpcionesTresParametros(ficheros);
                break;
            default:
                mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
                break;
        }

        return mensaje;
    }

    /**
     * Obtiene la lista simple de nombres de archivos y directorios visibles.
     * Corresponde al ls sin opciones.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje con la lista de nombres de archivos y directorios
     *         visibles. Excluyendo los ocultos.
     */
    private String obtenerListaSimple(Ficheros ficheros) {
        String mensaje = ficheros.obtenerNombres(false) + "\n"; // Muestra nombres sin ocultos
        return mensaje;
    }

    /**
     * Procesa el comando ls cuando tiene un unico parametro. Los
     * parametros admitidos:
     * -a para incluir elementos ocultos en la respuesta y -l
     * sin incluir ficheros ocultos en la respuesta.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando con
     *         un parámetro.
     */
    private String manejarOpcionesUnParametro(Ficheros ficheros) {
        String mensaje;
        switch (tokens[1]) {
            case "-l":
                mensaje = ficheros.obtenerInformacionDetallada(false) + "\n";
                break;
            case "-a":
                mensaje = ficheros.obtenerNombres(true) + "\n";
                break;
            default:
                mensaje = obtenerContenidoDirectorio(ficheros, tokens[1]);
                break;
        }
        return mensaje;
    }

    /**
     * Procesa el comando ls cuando tiene dos parametros. parametros
     * admitidos:
     * -a -l
     * -l nombreDirectorio - se deriva cualquiera a
     * obtenerComandosLsL()
     * -a nombreDirectorio - se deriva cualquiera a
     * obtenerComandosLsA()
     *  
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    private String manejarOpcionesDosParametros(Ficheros ficheros) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = ficheros.obtenerInformacionDetallada(true) + "\n";
        } else if (tokens[1].equals("-l")) {
            mensaje = obtenerComandoLsL(ficheros, tokens[2]);
        } else if (tokens[1].equals("-a")) {
            mensaje = obtenerComandoLsA(ficheros, tokens[2]);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con tres parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando con
     *         tres parámetros.
     */
    private String manejarOpcionesTresParametros(Ficheros ficheros) {
        String mensaje;
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = obtenerComandoLsLsA(ficheros, tokens[3]);
        } else {
            mensaje = "Sintaxis incorrecta.\nIntente man ls\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el contenido de un directorio para el comando ls 
     * [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar.
     * @return mensaje con el contenido del directorio especificado.
     */
    private String obtenerContenidoDirectorio(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            int i = 0;
            while (!ficheros.obtenerFichero(i).getNombre().equals(nombreDirectorio)) {
                i++;
            }
            mensaje = ficheros.obtenerFichero(i).obtenerContenido() + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l [directorio].
     *
     * De momento solo imprime un mensaje estandar ya que estamos trahajando con
     * un unico nivel en el sistema de archivos.
     * Obtiene el mensaje para el comando ls -l [directorio].
     * 
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param segundoParametro el nombre del directorio a listar detalladamente.
     * @return mensaje indicando el resultado de ejecutar ls -l [directorio].
     */
    private String obtenerComandoLsL(Ficheros ficheros, String segundoParametro) {
        String mensaje;
        if (ficheros.existeFichero(segundoParametro) && ficheros.esDirectorio(segundoParametro)) {
            mensaje = "[" + getHora() + "]\nComando ls -l al directorio " + segundoParametro + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre.\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -a [directorio].
     *
     * De momento solo imprime un mensaje estandar ya que estamos trahajando con
     * un unico nivel en el sistema de archivos.
     *
     * @param ficheros         objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar con todos los
     *                         archivos.
     * @return mensaje indicando el resultado de ejecutar ls -a [directorio].
     */
    private String obtenerComandoLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\nComando ls -a al directorio " + nombreDirectorio + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l -a [directorio].
     *
     * @param ficheros         el objeto que maneja la lista de archivos
     *                         disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente
     *                         con todos los archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -l -a
     *         [directorio].
     */
    private String obtenerComandoLsLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "[" + getHora() + "]\nComando (ls -a -l) o (ls -l -a) al directorio " + nombreDirectorio
                    + "\n";
        } else {
            mensaje = "No existe un directorio con ese nombre\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando kill para eliminar un proceso por su ID.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarKill(Procesos procesos) {
        String mensaje;

        if (tokens.length == 2) {
            String procesoIdStr = tokens[1];

            int procesoID = Integer.parseInt(procesoIdStr);
            if (procesos.existeProceso(procesoID)) {
                procesos.getListaProcesos().remove(procesos.obtenerProceso(procesoID));
                mensaje = "Proceso eliminado\n";
            } else {
                mensaje = "No existe proceso con pid" + procesoID + "\n";
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man kill\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando ps  para listar los procesos en ejecución del
     * sistema.
     *
     * @param procesos el objeto que maneja la lista de procesos activos.
     * @return un mensaje detallando los procesos en ejecución o un mensaje de
     *         error si la sintaxis es incorrecta.
     */
    public String ejecutarPs(Procesos procesos) {
        String mensaje = "";

        if (tokens.length == 1) {
            if (procesos.getListaProcesos().isEmpty()) {
                mensaje = "No existen procesos en ejecución\n";
            } else {
                mensaje = "PID | USER | % MEMORY | % CPU | TIME | COMMAND\n";
                for (Proceso proceso : procesos.getListaProcesos()) {
                    mensaje += proceso.toString() + "\n";
                }
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man ps\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando grep para buscar un patrón en un archivo
     * específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje indicando si se encontró el patrón en el archivo o un
     *         mensaje de error si la expresión es incorrecta.
     */
    private String ejecutarGrep(Ficheros ficheros) {
        String mensaje = "";

        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: debe ingresar la expresión a buscar y el nombre del archivo.";
        } else {
            String expresion = tokens[1];
            String nombreArchivo = tokens[2];

            if (ficheros.existeFichero(nombreArchivo)) {
                String contenidoArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido();
                if (contenidoArchivo.contains(expresion)) {
                    mensaje = "Se encontró la expresión '" + expresion + "' en el archivo '" + nombreArchivo + "'.";
                    // Por hacer: mostrar el texto resaltado o contar cantidad de ocurrencias.
                } else {
                    mensaje = "La expresión '" + expresion + "' no se encontró en el archivo '" + nombreArchivo + "'.";
                }
            } else {
                mensaje = "El archivo '" + nombreArchivo + "' indicado no existe.";
            }
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando tail para mostrar las últimas líneas de un
     * archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las últimas líneas del archivo especificado o un
     *         mensaje de error si la sintaxis es incorrecta.
     */
    private String ejecutarTail(Ficheros ficheros) {
        
        String mensaje;

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las últimas 10 líneas
            mensaje = obtenerLineas(ficheros, tokens[1],10,true);
        } else if (tokens.length == 4 && tokens[1].equals("-n")) {
            // Caso con opción -n: mostrar las últimas n líneas
            try {
                int n = Integer.parseInt(tokens[2]);
                mensaje = obtenerLineas(ficheros, tokens[3], n,true);
            } catch (NumberFormatException e) {
                mensaje = "El número de líneas debe ser un valor numérico.\n";
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man tail\n";
        }

        return mensaje;
    }
    /**
     * Ejecuta el comando head para mostrar las primeras líneas de un
     * archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las primeras líneas del archivo especificado o un
     *         mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarHead(Ficheros ficheros) {
        
        String mensaje;
        int numLineas;

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las primeras 10 líneas
            mensaje = obtenerLineas(ficheros, tokens[1],10,false);
        } else if (tokens.length == 4 && tokens[1].equals("-n")) {
            // Caso con opción -n: mostrar las primeras n líneas
            try {
                numLineas = Integer.parseInt(tokens[2]);
                mensaje = obtenerLineas(ficheros, tokens[3], numLineas,false);
            } catch (NumberFormatException e) {
                mensaje = "El número de líneas debe ser un valor numérico.\n";
            }
        } else {
            mensaje = "Sintaxis incorrecta.\nPruebe man head\n";
        }

        return mensaje;
    }

    /**
     * Metodo que devuelve las n lineas de un archivo.
     * Comenzando a contar desde el principio o el final del contenido
     *
     * @param ficheros      el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @param enReversa indica el sentido en el que se muestran las lineas
     * @param numLineas el número de líneas que se desean obtener.
     * @return cantidad de lineas solicitadas
     * **/

    private String obtenerLineas (Ficheros ficheros, String nombreArchivo, int numLineas, boolean enReversa) {
        String mensaje="";
        
        try{
            String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");        
            if (!enReversa){//Si no es en reversa es un head            
                for (int i = 0; i < Math.min(numLineas, lineasArchivo.length); i++) {
                mensaje += lineasArchivo[i] + "\n";
                }
            } else { //es un tail
                for (int i = Math.max(0, lineasArchivo.length - numLineas); i < lineasArchivo.length; i++) {
                mensaje += lineasArchivo[i] + "\n";
                }      
            }
        } catch (NullPointerException e) {//No se encontro el archivo
        
            mensaje += "No existe el arhivo";
        }
        
        return mensaje;
    }

    /**
     * Ejecuta el comando cut para mostrar las columnas seleccionadas de
     * un archivo usando un delimitador específico.
     *
     * Sintaxis esperada: cut -d ':' -f 1,4 archivo.txt
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las columnas seleccionadas del archivo especificado o
     *         un mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarCut(Ficheros ficheros) {

        String mensaje = "";

        // Verificar si el comando tiene la cantidad correcta de tokens y las opciones
        // correctas
        if (tokens.length == 6 && tokens[0].equals("cut") && tokens[1].equals("-d") && tokens[3].equals("-f")) {
            String delimitador = tokens[2].replace("'", ""); // Delimitador especificado sin comillas simples
            String columnas = tokens[4]; // Campos a extraer
            String archivo = tokens[5]; // Nombre del archivo

            // Verificar si el archivo existe y no es un directorio
            if (ficheros.esDirectorio(archivo)) {
                mensaje = "Error: El archivo especificado no existe o es un directorio.";
                // Cambiar color a rojo
            } else if (ficheros.obtenerFichero(archivo).obtenerContenido() == null || ficheros.obtenerFichero(archivo).obtenerContenido().isEmpty()) {// Verificar que el archivo tenga contenido
                mensaje = "Error: El archivo especificado está vacío.";
                // Cambiar color a rojo    
            } else if (!delimitador.equals(":")) {
                mensaje = "Error: El delimitador especificado no es válido. Se admite solo ':' (dos puntos).";
            } else { ///////////// caso valido
                String[] columnasArray = columnas.split(",");
                ArrayList<Integer> indicesCampos = new ArrayList<>();

                // Convertir columnasArray a lista de índices (restamos 1 para hacerlos 0-based)
                for (String campo : columnasArray) {
                    try {
                        indicesCampos.add(Integer.parseInt(campo.trim()) - 1);
                    } catch (NumberFormatException e) {
                        mensaje = "Error: Los campos especificados no son números válidos.";
                        // Cambiar color a rojo
                    }
                }

                if (!mensaje.contains("Error")) {
                    // Dividir el contenido del archivo por líneas
                    String[] lineas = ficheros.obtenerFichero(archivo).obtenerContenido().split("\n");

                    // Procesar cada línea
                    for (String linea : lineas) {
                        String[] partes = linea.split(delimitador); // Usar el delimitador especificado
                        StringBuilder lineaResultado = new StringBuilder();////////////////////////////////////////////////////////PROBAR sin esta clase

                        // Obtener las columnas después de los columnas especificados
                        for (int indice : indicesCampos) {

                            if (indice >= 0 && indice < partes.length) {
                                if (lineaResultado.length() > 0) {
                                    lineaResultado.append(delimitador); // Agregar delimitador entre columnas
                                }
                                lineaResultado.append(partes[indice]);
                            } else {
                                mensaje = "Error: El índice de columna especificado está fuera del rango de columnas en la línea.";
                                // Cambiar color a rojo
                                return mensaje;
                            }
                        }

                        mensaje += lineaResultado.toString() + "\n";
                    }
                }

            } ////////////////////// fin caso valido

            // Cambiar color a celeste
        } else {
            mensaje += "Error: Sintaxis incorrecta. La sintaxis correcta es: cut -d ':' -f 1,4 archivo.txt";
            // Cambiar color a rojo
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando sort para mostrar las líneas de un archivo
     * ordenadas alfabéticamente. Si se especifica la opción '-n', ordena por el
     * valor numerico de la linea.
     *
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las líneas del archivo ordenadas o un mensaje de
     *         error si la sintaxis es incorrecta.
     */
    public String ejecutarSort(Ficheros ficheros) {
        String mensaje = "";
        String[] mensajeTokenizado;
        boolean tieneNumeros=true;

        if (getTokens().length == 2) {
            mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
            Arrays.sort(mensajeTokenizado);
            for (String linea : mensajeTokenizado) {
                mensaje += linea + "\n";
            }
        } else if (getTokens().length == 3 && tokens[1].equals("-n")) {
            // Ordenar numéricamente las líneas del archivo especificado
            mensajeTokenizado = ficheros.obtenerFichero(tokens[2]).obtenerContenido().split("\\R");
            //Arrays.sort(mensajeTokenizado, Comparator.comparingInt(Integer::parseInt));
            // De esta otra manera queda mas legible la logica de la operacion de comparacion y ordenamiento en funcion de esta.
            // Si el valor es estrictamente numerico, se guarda, de lo contrario pone un 0. Y luego se ordenan esos numeros.
            int[] numeritos = new int[mensajeTokenizado.length];
            for (int i = 0; i < mensajeTokenizado.length; i++) {
                try {
                    numeritos[i] = Integer.parseInt(mensajeTokenizado[i]);  // REFINAR CON: inea.contains(".*\\d+.*")

                } catch (NumberFormatException e) {                  
                    
                    tieneNumeros=false;
                }

            }
            if(!tieneNumeros){
            
                mensaje += "El archivo no contiene numeros";
            } else {
            Arrays.sort(numeritos);

            for (int n : numeritos) {
                mensaje += Integer.toString(n) + "\n";
            }
            }
        } else {
            // Sintaxis incorrecta
            mensaje = "Sintaxis incorrecta\n";
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
        String mensaje = "";

        // Verificar la cantidad de argumentos
        if (tokens.length != 3) {
            mensaje = "Sintaxis incorrecta: número incorrecto de argumentos";
        } else if (!ficheros.existeFichero(fich)) {
            // Verificar si el fichero existe
            mensaje = "Sintaxis incorrecta: el fichero o directorio '" + fich + "' no existe";
        } else if (!validarLongitudPermisos(permisos)) {
            // Validar longitud de permisos
            mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
        } else {
            switch (permisos.length()) {
                case 3:
                    if (!aplicarPermisosNumericos(ficheros, fich, permisos)) {
                        mensaje = "Sintaxis incorrecta: valor de permisos inválido";
                    } else {
                        mensaje = "Comando ejecutado correctamente";
                    }
                    break;
                case 9:
                    if (!aplicarPermisosSimbolicos(ficheros, fich, permisos)) {
                        mensaje = "Sintaxis incorrecta: permisos inválidos";
                    } else {
                        mensaje = "Comando ejecutado correctamente";
                    }
                    break;
                default:
                    mensaje = "Sintaxis incorrecta: longitud incorrecta de permisos";
            }
        }

        return mensaje;
    }

    /**
     * Este metodo es para ejecutar el comando | tambien llamado 'pipe'.
     *
     * @param indexPipe - indice del simbolo pipe en la linea ingresada.
     * @param ficheros  - objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando.
     */
    public String ejecutarPipe(int indexPipe, Ficheros ficheros) {
        String mensaje = "";
        String msjComando1 = "";
        String msjComando2 = "";
        String[] tokensA = Arrays.copyOfRange(tokens, 0, indexPipe); // antes del pipe. indexPipe queda afuera
        String[] tokensB = Arrays.copyOfRange(tokens, indexPipe + 1, tokens.length + 1); // despues del pipe. y uno mas,
        // que queda con null

        if (tokens[0].equals("tail")) {// primero bloque de IFs para procesar el primer comando
            tokens = tokensA;
            msjComando1 = ejecutarTail(ficheros);
        } else if (tokens[0].equals("head")) {
            tokens = tokensA;
            msjComando1 = ejecutarHead(ficheros);
        } else {
            mensaje = "Sintaxis incorrecta en primer comando. Debe ser head o tail de un archivo.";
        }

        if (tokensB[0].equals("grep")) { // segundo bloque de IFs para procesar el segundo comando

            ficheros.agregarFichero(new Archivo("especificado", msjComando1)); // creo un archivo con el contenido del
            // mensaje1. Ya que grep esta programado para
            // levantar contenido de ficheros.
            tokensB[2] = "especificado"; // modifico tokens para poner como 3 parametro el nombre del archivo temporal.
            tokens = tokensB;
            msjComando2 = ejecutarGrep(ficheros);// Va a tomar el tokens global, el cual no esta como lo necesita.
            // PROUESTA: que todos los ejecutarComando() reciban el tokens como
            // parametro.
            // borro ese archivo que no debe existir mas.
            ficheros.eliminarFichero("especificado");
            mensaje = msjComando2;
        } else {
            mensaje = "Sintaxis incorrecta en segundo comando. Debe ser grep.";
        }

        return mensaje;
    }

    /**
     * Valida la longitud de los permisos.
     *
     * @param permisos String con los permisos a validar.
     * @return false si la longitud de los persmisos no es correcta. Asume true
     *         por defecto.
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
     * @param nombre   del fichero o directorio al que se aplicarán los permisos.
     * @param permisos String con los permisos numéricos.
     * @return true si se aplicaron los permisos correctamente, false de lo
     *         contrario.
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
     * @param fich     Nombre del fichero o directorio al que se aplicarán los
     *                 permisos.
     * @param permisos String con los permisos simbólicos.
     * @return true si se aplicaron los permisos correctamente, false de lo
     *         contrario.
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
