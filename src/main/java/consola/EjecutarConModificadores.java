package consola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import javax.swing.JTextPane;

/**
 * Esta clase se encarga de ejecutar los comandos que utilizan modificadores "-"
 * Incluyendo las concatenaciones con grep utiizando pipe "|"
 *
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 *
 */
public class EjecutarConModificadores {

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
    public EjecutarConModificadores(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * @return tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Metodo principal de esta clase. Deriva la ejecucion al comando
     * correspondiente.
     *
     * @param comandos - coleccion de comandos cargados en el sistema
     * @param ficheros - ficheros cargados en el sistema
     * @param procesos - procesos activos en el sistema
     * @param salida - donde mostrar el resultado de la ejecucion.
     * @return String resultante de la ejecucion
     */
    public String ejecutarComando(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextPane salida) {
        String mensaje;

        switch (tokens[0]) {

            case "ls":
                mensaje = ejecutarLs(ficheros);
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

            default:
                mensaje = ">> Comando inexistente <<\n";
                break;
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
        String mensaje = "";

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
        }

        return mensaje;
    }

    /**
     * Obtiene la lista simple de nombres de archivos y directorios visibles.
     * Corresponde al ls sin opciones.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje con la lista de nombres de archivos y directorios
     * visibles. Excluyendo los ocultos.
     */
    private String obtenerListaSimple(Ficheros ficheros) {
        String mensaje = ficheros.obtenerNombres(false) + "\n"; // Muestra nombres sin ocultos
        return mensaje;
    }

    /**
     * Procesa el comando ls cuando tiene un unico parametro. Los parametros
     * admitidos: -a para incluir elementos ocultos en la respuesta y -l sin
     * incluir ficheros ocultos en la respuesta.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando con
     * un parámetro.
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
     * Procesa el comando ls cuando tiene dos parametros. parametros admitidos:
     * -a -l -l nombreDirectorio - se deriva cualquiera a obtenerComandosLsL()
     * -a nombreDirectorio - se deriva cualquiera a obtenerComandosLsA()
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
        } else {
            //Si el tokens[1] no es -l tiene que ser -a de otra manera el validador impide esta ejecucion
            mensaje = obtenerComandoLsA(ficheros, tokens[2]);
        }
        return mensaje;
    }

    /**
     * Maneja las opciones de ls con tres parámetros.
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @return mensaje detallando el resultado de la ejecución del comando con
     * tres parámetros.
     */
    private String manejarOpcionesTresParametros(Ficheros ficheros) {
        String mensaje = "";
        if ((tokens[1].equals("-l") && tokens[2].equals("-a")) || (tokens[1].equals("-a") && tokens[2].equals("-l"))) {
            mensaje = obtenerComandoLsLsA(ficheros, tokens[3]);
        }
        return mensaje;
    }

    /**
     * Obtiene el contenido de un directorio para el comando ls [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
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
            mensaje = ">> No existe un directorio con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l [directorio].
     *
     * De momento solo imprime un mensaje estandar ya que estamos trahajando con
     * un unico nivel en el sistema de archivos. Obtiene el mensaje para el
     * comando ls -l [directorio].
     *
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param segundoParametro el nombre del directorio a listar detalladamente.
     * @return mensaje indicando el resultado de ejecutar ls -l [directorio].
     */
    private String obtenerComandoLsL(Ficheros ficheros, String segundoParametro) {
        String mensaje;
        if (ficheros.existeFichero(segundoParametro) && ficheros.esDirectorio(segundoParametro)) {
            mensaje = "´Comando ls -l al directorio " + segundoParametro + "\n";
        } else {
            mensaje = ">> No existe un directorio con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -a [directorio].
     *
     * De momento solo imprime un mensaje estandar ya que estamos trahajando con
     * un unico nivel en el sistema de archivos.
     *
     * @param ficheros objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar con todos los
     * archivos.
     * @return mensaje indicando el resultado de ejecutar ls -a [directorio].
     */
    private String obtenerComandoLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "Comando ls -a al directorio " + nombreDirectorio + "\n";
        } else {
            mensaje = ">> No existe un directorio con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Obtiene el mensaje para el comando ls -l -a [directorio].
     *
     * @param ficheros el objeto que maneja la lista de archivos disponibles.
     * @param nombreDirectorio el nombre del directorio a listar detalladamente
     * con todos los archivos.
     * @return un mensaje indicando el resultado de ejecutar ls -l -a
     * [directorio].
     */
    private String obtenerComandoLsLsA(Ficheros ficheros, String nombreDirectorio) {
        String mensaje;
        if (ficheros.existeFichero(nombreDirectorio) && ficheros.esDirectorio(nombreDirectorio)) {
            mensaje = "Comando (ls -a -l) o (ls -l -a) al directorio " + nombreDirectorio
                    + "\n";
        } else {
            mensaje = ">> No existe un directorio con ese nombre <<\n";
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando grep para buscar un patrón en un archivo específico.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje indicando si se encontró el patrón en el archivo o un
     * mensaje de error si la expresión es incorrecta.
     */
    public String ejecutarGrep(Ficheros ficheros) {
        String mensaje = "";
        String expresion = tokens[1];
        String nombreArchivo = tokens[2];
        boolean existeExpresion = false;

        if (ficheros.existeFichero(nombreArchivo)) {
            String contenidoArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido();
            String[] porLineas = contenidoArchivo.split("\n");
            for (String linea : porLineas) {
                if (linea.contains(expresion)) {
                    existeExpresion = true;
                    mensaje += "-Coindicencia-\n" + linea + "\n";
                }
            }
            if (!existeExpresion) {
                mensaje = ">> La expresión '" + expresion + "' no se encontró en el archivo '" + nombreArchivo + "' <<\n";
            }

        } else {
            mensaje = ">> El archivo '" + nombreArchivo + "' indicado no existe <<\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando tail para mostrar las últimas líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las últimas líneas del archivo especificado o un
     * mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarTail(Ficheros ficheros) {

        String mensaje;

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las últimas 10 líneas
            mensaje = obtenerLineas(ficheros, tokens[1], 10, true);
        } else {// Caso con opción -n: mostrar las últimas n líneas           
            try {
                int n = Integer.parseInt(tokens[2]);
                mensaje = obtenerLineas(ficheros, tokens[3], n, true);
            } catch (NumberFormatException e) {
                mensaje = ">> El número de líneas debe ser un valor numérico <<\n";
            }
        }
        return mensaje;
    }

    /**
     * Ejecuta el comando head para mostrar las primeras líneas de un archivo.
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las primeras líneas del archivo especificado o un
     * mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarHead(Ficheros ficheros) {

        String mensaje;
        int numLineas;

        if (tokens.length == 2) {
            // Caso sin opción -n: mostrar las primeras 10 líneas
            mensaje = obtenerLineas(ficheros, tokens[1], 10, false);
        } else {
            // Caso con opción -n: mostrar las primeras n líneas
            try {
                numLineas = Integer.parseInt(tokens[2]);
                mensaje = obtenerLineas(ficheros, tokens[3], numLineas, false);
            } catch (NumberFormatException e) {
                mensaje = ">> El número de líneas debe ser un valor numérico <<\n";
            }
        }
        return mensaje;
    }

    /**
     * Metodo que devuelve las n lineas de un archivo. Comenzando a contar desde
     * el principio o el final del contenido
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @param nombreArchivo el nombre del archivo del cual se obtendrán las
     * líneas.
     * @param enReversa indica el sentido en el que se muestran las lineas
     * @param numLineas el número de líneas que se desean obtener.
     * @return cantidad de lineas solicitadas *
     */
    private String obtenerLineas(Ficheros ficheros, String nombreArchivo, int numLineas, boolean enReversa) {
        String mensaje = "";

        try {
            String[] lineasArchivo = ficheros.obtenerFichero(nombreArchivo).obtenerContenido().split("\\R");
            if (!enReversa) {//Si no es en reversa es un head            
                for (int i = 0; i < Math.min(numLineas, lineasArchivo.length); i++) {
                    mensaje += lineasArchivo[i] + "\n";
                }
            } else { //es un tail
                for (int i = Math.max(0, lineasArchivo.length - numLineas); i < lineasArchivo.length; i++) {
                    mensaje += lineasArchivo[i] + "\n";
                }
            }
        } catch (NullPointerException e) {//No se encontro el archivo

            mensaje += ">> No existe el arhivo <<\n";
        }

        return mensaje;
    }

    /**
     * Ejecuta el comando cut para mostrar las columnas seleccionadas de un
     * archivo usando un delimitador específico.
     *
     * Sintaxis esperada: cut -d ':' -f 1,4 archivo.txt
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las columnas seleccionadas del archivo especificado o
     * un mensaje de error si la sintaxis es incorrecta.
     */
    public String ejecutarCut(Ficheros ficheros) {

        String mensaje = "";
        String delimitador = tokens[2].replace("'", ""); // Delimitador especificado sin comillas simples
        String columnas = tokens[4]; // Campos a extraer
        String archivo = tokens[5]; // Nombre del archivo
        // Verificar si el archivo existe y no es un directorio
        if (!ficheros.existeFichero(archivo) || !ficheros.existeFichero(archivo) && ficheros.esDirectorio(archivo)) {
            mensaje = ">> Error: El archivo especificado no existe o es un directorio <<\n";
            // Cambiar color a rojo
        } else if (ficheros.obtenerFichero(archivo).obtenerContenido() == null || ficheros.obtenerFichero(archivo).obtenerContenido().isEmpty()) {// Verificar que el archivo tenga contenido
            mensaje = ">> Error: El archivo especificado está vacío <<\n";
            // Cambiar color a rojo    
        } else if (!delimitador.equals(":")) {
            mensaje = ">> Error: El delimitador especificado no es válido. Se admite solo ':' (dos puntos) <<\n";
        } else { ///////////// caso valido
            String[] columnasArray = columnas.split(",");
            ArrayList<Integer> indicesCampos = new ArrayList<>();
            // Convertir columnasArray a lista de índices (restamos 1 para hacerlos 0-based)
            for (String campo : columnasArray) {
                try {
                    indicesCampos.add(Integer.parseInt(campo.trim()) - 1);
                    System.out.println(tokens.length);
                } catch (NumberFormatException e) {
                    mensaje = ">> Error: Los campos especificados no son números válidos <<\n";
                    // Cambiar color a rojo
                }
            }
            if (!mensaje.contains("Error")) {
                // Dividir el contenido del archivo por líneas
                String[] lineas = ficheros.obtenerFichero(archivo).obtenerContenido().split("\n");
                // Procesar cada línea
                for (String linea : lineas) {
                    String[] partes = linea.split(delimitador); // Usar el delimitador especificado
                    StringBuilder lineaResultado = new StringBuilder();////////////PROBAR sin esta clase
                    // Obtener las columnas después de los columnas especificados
                    for (int indice : indicesCampos) {

                        if (indice >= 0 && indice < partes.length) {
                            if (lineaResultado.length() > 0) {
                                lineaResultado.append(delimitador); // Agregar delimitador entre columnas
                            }
                            lineaResultado.append(partes[indice]);
                        } else {
                            mensaje = ">> Error: El índice de columna especificado está fuera del rango de columnas en la línea <<\n";
                            // Cambiar color a rojo
                            return mensaje;
                        }
                    }

                    mensaje += lineaResultado.toString() + "\n";// Cambiar color a celeste
                }
            }

        }
        return mensaje;
    }

    /**
     * Ejecuta el comando sort para mostrar las líneas de un archivo ordenadas
     * alfabéticamente. Si se especifica la opción '-n', ordena por el valor
     * numerico de la linea.
     *
     *
     * @param ficheros el objeto que maneja la lista de archivos y directorios.
     * @return mensaje con las líneas del archivo ordenadas o un mensaje de
     * error si la sintaxis es incorrecta.
     */
    public String ejecutarSort(Ficheros ficheros) {
        String mensaje;
        String[] mensajeTokenizado;
        boolean tieneNumeros = true;

        try {
            if (tokens.length == 2) {
                mensajeTokenizado = ficheros.obtenerFichero(tokens[1]).obtenerContenido().split("\\R");
                mensaje = ordenarAlfabeticamente(mensajeTokenizado);
            } else {
                // Ordenar numéricamente las líneas del archivo especificado
                mensajeTokenizado = ficheros.obtenerFichero(tokens[2]).obtenerContenido().split("\\R");
                // De esta otra manera queda mas legible la logica de la operacion de comparacion y ordenamiento en funcion de esta.
                // Si el valor es estrictamente numerico, se guarda, de lo contrario pone un 0. Y luego se ordenan esos numeros.
                int[] numeritos = new int[mensajeTokenizado.length];
                for (int i = 0; i < mensajeTokenizado.length; i++) {
                    try {
                        numeritos[i] = Integer.parseInt(mensajeTokenizado[i]);  // REFINAR CON: inea.contains(".*\\d+.*")
                    } catch (NumberFormatException e) {
                        tieneNumeros = false;
                    }
                }
                if (!tieneNumeros) {
                    mensaje = ordenarAlfabeticamente(mensajeTokenizado) + "\n>> El contenido no es numérico <<\n";
                } else {
                    mensaje = ordenarNumeros(numeritos);
                }
            }
        } catch (NullPointerException ex) {
            mensaje = ">> No existe el archivo <<\n";
        }
        return mensaje;
    }

    /**
     * Metodo que recibe un arreglo de String y las ordena alfabeticamente
     * agregando un salto de linea al final de cada elemento
     *
     * @param lineas es un arreglo con los elemenos a ordenar
     * @return un String con las lineas ordenadas
     */
    private String ordenarAlfabeticamente(String[] lineas) {
        String ordenadas = "";
        TreeMap<Character, ArrayList<String>> lineasMapeadas = new TreeMap<>();

        for (String linea : lineas) { // CARGO mapa
            char letraInicial = linea.toLowerCase().charAt(0);
            
            ArrayList<String> actual = new ArrayList<>();
            
            if (!lineasMapeadas.containsKey(letraInicial)) {
                actual.add(linea);
                lineasMapeadas.put(letraInicial, actual);
            } else {
                actual = lineasMapeadas.get(letraInicial);
                actual.add(linea);
                lineasMapeadas.put(letraInicial, actual);
            }

        }

        for (ArrayList<String> elemento : lineasMapeadas.values()) {    //creo mensaje de retorno
            for (String l : elemento) {
                ordenadas += l + "\n";
            }

        }

        return ordenadas;
    }

    /**
     * Metodo que recibe un arreglo de numeros, los ordena de manera ascendente
     * y los devuelve en un String separados por saltos de linea
     *
     * @param numeros es el arreglo con los elemenos a ordenar
     * @return un String con las lineas ordenadas
     */
    private String ordenarNumeros(int[] numeros) {
        String ordenados = "";
        Arrays.sort(numeros);

        for (int n : numeros) {
            ordenados += Integer.toString(n) + "\n";
        }
        return ordenados;

    }

    /**
     * Este metodo es para ejecutar el comando | tambien llamado 'pipe'.
     *
     * @param indexPipe - indice del simbolo pipe en la linea ingresada.
     * @param ficheros - objeto que maneja la lista de archivos disponibles.
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
            mensaje = ">> Sintaxis incorrecta: el primer comando debe ser head o tail <<\n";
        }

        if (tokensB[0].equals("grep") && tokensB.length == 3) { // segundo bloque de IFs para procesar el segundo comando

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
            mensaje = ">> Sintaxis incorrecta: en segundo comando debe ser [grep 'expresión'] <<\n";
        }

        return mensaje;
    }

}
