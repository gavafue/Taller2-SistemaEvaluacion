package consola;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextPane;

/**
 * Esta clase permite validar un comando y sus opciones. A partir de esto
 * determinar a que clase ejecutora derivar.
 *
 */
public class Validar {

    private String comandoIngresado; // El comando ingresado por el usuario
    private String[] tokens; // Los tokens resultantes de dividir la orden.
    private boolean esValido, usaModificadores;

    /**
     * Constructor que inicializa el comando a validar.
     *
     * @param comandoIngresado El comando ingresado por el usuario.
     */
    public Validar(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado.stripLeading();
        this.tokens = this.comandoIngresado.split(" ");
    }

    /**
     * Obtiene el comando ingresado por el usuario.
     *
     * @return el comando ingresado por el usuario
     */
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    /**
     * Establece el comando ingresado por el usuario.
     *
     * @param comandoIngresado el comando que fue ingresado por el usuario
     */
    public void setComandoIngresado(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado;
    }

    /**
     * Obtiene los tokens resultantes de dividir la orden ingresada.
     *
     * @return un arreglo de strings que representa los tokens del comando
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Establece los tokens resultantes de dividir la orden ingresada.
     *
     * @param tokens el arreglo de strings que representa los tokens del comando
     */
    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Verifica si el comando ingresado es válido.
     *
     * @return true si el comando es válido, false en caso contrario
     */
    public boolean getEsValido() {
        return esValido;
    }

    /**
     * Establece si el comando ingresado es válido.
     *
     * @param esValido true si el comando es válido, false en caso contrario
     */
    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    /**
     * Verifica si el comando ingresado utiliza modificadores.
     *
     * @return true si el comando utiliza modificadores, false en caso contrario
     */
    public boolean getUsaModificadores() {
        return usaModificadores;
    }

    /**
     * Establece si el comando ingresado utiliza modificadores.
     *
     * @param usaModificadores true si el comando utiliza modificadores, false
     * en caso contrario
     */
    public void setUsaModificadores(boolean usaModificadores) {
        this.usaModificadores = usaModificadores;
    }

    /**
     * Método que determina si el comando ingresado es válido o no. Este método
     * verifica si el comando ingresado cumple con los criterios básicos de
     * validez, tales como no comenzar con un espacio y ser un comando
     * reconocido. También verifica si las opciones proporcionadas con el
     * comando son válidas.
     *
     * @param comandos La lista de comandos disponibles.
     * @return String "200" si el comando es válido o el error concreto en caso
     * contrario.
     */
    public String validarComando(Comandos comandos) {
        String comando = tokens[0];
        String resultado = ">> Comando ingresado '"
                + comando + "' inexistente <<\n\nIntente [man] para ver los comandos disponibles\n";

        boolean existe = comandos.existeComando(comando);
        if (existe) {
            String[] opcionesValidas = comandos.obtenerOpciones(comando);
            if (opcionesValidas != null) {
                usaModificadores = true;
                resultado = validarOpciones();
            } else {
                usaModificadores = false;
                resultado = validarSintaxis();
            }
        }
        return resultado;
    }

    /**
     * Método que comienza la ejecuciòn de un comando. Dependiendo si utiliza o
     * no paràmetros se instancia un objeto u otro.
     *
     * El comando grep se ejecuta desde la clase EjecutarConModificadores para
     * facilitar la concatenaciòn con pipe.
     *
     * @param comandos posibles.
     * @param ficheros es la lista con el sistema de ficheros.
     * @param procesos en ejecucion.
     * @param salida es el JtextPane donde se muestra el resultado de la
     * ejecucion.
     * @return un String con el resultado de la ejecucion
     */
    public String comenzarEjecucion(Comandos comandos, Ficheros ficheros, Procesos procesos, JTextPane salida) {
        String resultado;
        if (usaModificadores || tokens[0].equals("grep")) {
            EjecutarConModificadores ejecutor = new EjecutarConModificadores(tokens);
            resultado = ejecutor.ejecutarComando(comandos, ficheros, procesos, salida);
        } else {
            EjecutarSinModificadores ejecutor = new EjecutarSinModificadores(tokens);
            resultado = ejecutor.ejecutarComandoSinMod(comandos, ficheros, procesos, salida);
        }
        return resultado;
    }

    /**
     * Método que determina si las opciones del comando ingresado son válidas.
     * Este método recorre las opciones del comando (tokens) y verifica si cada
     * una de ellas es una opción válida utilizando el método auxiliar
     * esOpcionValida.
     *
     * @return true si todas las opciones son válidas, false en caso contrario.
     */
    private String validarOpciones() {
        String resultado = "200";
        switch (tokens[0]) {
            case "ls":
                if (tokens.length > 1) {
                    resultado = validarOpLs();
                }
                break;
            case "head":
            case "tail":
                resultado = validarHeadTail();
                break;
            case "sort":
                resultado = validarSort();
                break;
            case "cut":
                resultado = validarCut();
                break;
        }
        return resultado;
    }

    /**
     * Metodo auxiliar que valida las opciones ingresadas con "-" para un
     * comando determinado
     *
     * @param comando del cual desea validar las opciones
     * @param aComprobar arreglo con las opciones ingresadas por el usuario
     * @return un String "200" sin todas las opciones con correctas o un mensaje
     * descriptivo en caso contrario
     */
    private String compararConOpcPosibles(String comando, String[] aComprobar) {
        Comandos comandos = new Comandos();
        // Arreglo con las opciones válidas del comando pasado por parámetros
        String[] opcionesValidas = comandos.obtenerOpciones(comando);
        String resultado = "200"; //Valor valido por defecto
        boolean esValida;

        for (String opcion : aComprobar) {
            esValida = Arrays.asList(opcionesValidas).contains(opcion);
            if (!esValida) {
                resultado = ">> El modificador " + opcion + " no es válido <<\n";
                //Se podría utilizar while o un break para salir del bucle, ya que alcanza con encontrar una sola inválida
                //Tambien podrían mostrarse todas las opciones incorrectas
            }
        }
        return resultado;
    }

    /**
     * Metodo que controla la posibilidad de colocar 2 veces la misma opcion por
     * parte del usuario Ejemplo: ls -l -l
     *
     * @return "200" si no existen opciones duplicadas o el mensaje de error
     * concreto en caso contrario
     */
    private String encontrarDuplicados() {
        boolean[] incluido = new boolean[tokens.length];
        String resultado = "200";

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].startsWith("-") && !incluido[i]) {
                for (int j = i + 1; j < tokens.length; j++) {
                    if (tokens[i].equals(tokens[j])) {
                        resultado = ">> Modificador " + tokens[i] + " duplicado <<\n";
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo auxiliar que cuenta la cantidad de opciones ingresadas
     *
     * @return cantidad de elementos que comienzan con '-'
     */
    private int contarOpciones() {
        int contador = 0;
        // Recorrer el arreglo y contar los elementos que comienzan con '-'
        for (String token : tokens) {
            if (token.startsWith("-")) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Metodo que utiliza compararConOpcPosibles y encontrarDuplicado para
     * validar las opciones de un ls en todas sus variantes ls ls dir ls -l ||
     * ls -l dir ls -a || ls -a dir ls -l -a || ls -l -a dir ls -a -l || ls -a
     * -l dir
     *
     * @return un mensaje con el error concreto o "200" si es valido
     */
    private String validarOpLs() {
        String duplicados = encontrarDuplicados();
        // Mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man ls] <<\n";

        if (!duplicados.equals("200")) {
            // Si hay un error con los duplicados, establecer mensajeError
            mensajeError = duplicados;
        } else {
            // Crear un nuevo arreglo sin duplicados
            int tamanio = contarOpciones();
            String[] opcionesUnicas = new String[tamanio];
            int index = 0;
            // Agregar los elementos únicos al nuevo arreglo
            for (String token : tokens) {
                if (token.startsWith("-")) {
                    opcionesUnicas[index++] = token;
                }
            }
            // Validar las opciones y actualizar el mensaje de error
            mensajeError = compararConOpcPosibles("ls", opcionesUnicas);
        }
        return mensajeError;
    }

    /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis de tail y head
     *
     * @return un mensaje con el error concreto o "200" si la sintaxis es valida
     */
    private String validarHeadTail() {
        String resultado;
        // Caso especial para una sola opción
        if (tokens.length == 1) {
            resultado = ">> Faltan opciones/parámetros <<\n";
        } else {
            // Encontrar duplicados en las opciones
            String duplicados = encontrarDuplicados();

            // Verificar el caso de duplicados
            if (!duplicados.equals("200")) {
                resultado = duplicados;
            } else {
                int tamanio = contarOpciones();
                // Verificar el caso especial para tamaño de opciones
                if (tamanio == 0 && tokens.length == 2) {
                    resultado = "200"; // Valor válido
                } else {
                    // Crear un nuevo arreglo sin duplicados
                    String[] resultadoArray = new String[tamanio];
                    int index = 0;

                    // Agregar los elementos únicos al nuevo arreglo
                    for (String token : tokens) {
                        if (token.startsWith("-")) {
                            resultadoArray[index++] = token;
                        }
                    }
                    // Comparar con opciones posibles
                    String opcValidas = compararConOpcPosibles("head", resultadoArray);
                    // Determinar el resultado final basado en el tamaño de tokens y las opciones válidas
                    if (tokens.length == 4 && opcValidas.equals("200")) {
                        resultado = "200"; // Valor válido
                    } else if (!opcValidas.equals("200")) {
                        resultado = opcValidas; // Mensaje de error de opciones válidas
                    } else {
                        resultado = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
                    }
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis de sort.
     *
     * @return un String "200" si la sintaxis de sort es correcta y el mensaje
     * de error en caso contrario.
     */
    private String validarSort() {
        String duplicados = encontrarDuplicados();
        // Mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man sort] <<\n";

        if (!duplicados.equals("200")) {
            // Si hay opciones duplicadas, las devuelve
            mensajeError = duplicados;
        } else {
            int tamanio = contarOpciones();
            if (tokens.length == 1) {
                // Si solo hay un token
                mensajeError = ">> Faltas opciones/parámetros <<\n";
            } else if (tamanio == 0 && tokens.length == 2) {
                // Si no hay opciones y tokens.length es 2 el comando puede ejecutarse
                mensajeError = "200";
            } else if (tamanio > 0 && tokens.length == 3) {
                // Si hay opciones y tokens.length es 3, validar las opciones
                String[] resultadoOpciones = new String[tamanio];
                int index = 0;
                for (String token : tokens) {
                    if (token.startsWith("-")) {
                        resultadoOpciones[index++] = token;
                    }
                }
                String opcValidas = compararConOpcPosibles("sort", resultadoOpciones);
                // Determinar el resultado final basado en el tamaño de tokens y las opciones válidas
                if (tokens.length == 3 && opcValidas.equals("200")) {
                    mensajeError = "200"; // Valor válido
                } else if (!opcValidas.equals("200")) {
                    mensajeError = opcValidas; // Mensaje de error de opciones válidas
                } else {
                    mensajeError = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
                }
            }
        }
        return mensajeError;
    }

    /**
     * Metodo que utiliza contarOpciones y encontrarDuplicados para validar la
     * sintaxis cut.
     *
     * @return si la sintaxis de cut es correcta devuelve el String "200", en
     * caso contrario el mensaje de error
     */
    private String validarCut() {
        String duplicados = encontrarDuplicados();
        // Inicializar el mensaje de error por defecto
        String mensajeError = ">> Sintaxis incorrecta. Intente [man cut] <<\n";
        // Si hay opciones duplicadas, las devuelve
        if (!duplicados.equals("200")) {
            mensajeError = duplicados;
        } else {
            int tamanio = contarOpciones();
            String[] resultadoOpciones = new String[tamanio];
            int index = 0;
            for (String token : tokens) {
                if (token.startsWith("-")) {
                    resultadoOpciones[index++] = token;
                }
            }
            String opValidas = compararConOpcPosibles("cut", resultadoOpciones);
            if (tokens.length == 6 && opValidas.equals("200") && tokens[1].equals("-d")) {
                mensajeError = "200"; // Valor válido
            } else if (!opValidas.equals("200")) {
                mensajeError = opValidas; // Mensaje de error de opciones válidas
            } else if (tokens.length != 6) {
                mensajeError = ">> Cantidad de opciones incorrecta para este comando <<\n";
            } else {
                mensajeError = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n"; // Mensaje de error de sintaxis
            }
        }
        return mensajeError;
    }

    /**
     * Método que busca la existencia del caracter pipe y devuelve su posición
     *
     * @return la poscion en la que se encuentra el pipe.
     */
    public int posicionPipe() {
        int indicePipe = 0;
        // Creo un arraylist temporal para usar su metodo
        // contains(). Esto es mas corto que un for y el codigo mas legible          
        ArrayList<String> tokensParaElPipe = new ArrayList<>(Arrays.asList(tokens));
        if (tokensParaElPipe.contains("|")) {
            indicePipe = tokensParaElPipe.indexOf("|");
        }
        return indicePipe;
    }

    /**
     * Método que determina si el comando ingresado tiene pipe.
     *
     * @return true si tiene pipe y false en caso contrario.
     */
    public boolean tienePipe() {
        return this.posicionPipe() != 0;
    }

    /**
     * Método que determina si el comando con pipe es válido o no, llamando al
     * ejecutor corresopondiente en caso de que lo sea.
     *
     * @param hashComandos comandos del sistema.
     * @param listaFicheros ficheros actuales.
     * @return resultado de validar comando con pipe.
     */
    public String validarPipe(Comandos hashComandos, Ficheros listaFicheros) {
        String mensaje = "";
        String msjComando1 = "";
        String msjComando2 = "";
        int indexPipe = this.posicionPipe();
        String[] tokensA = Arrays.copyOfRange(tokens, 0, indexPipe); // antes del pipe. indexPipe queda afuera
        String[] tokensB = Arrays.copyOfRange(tokens, indexPipe + 1, tokens.length + 1); // despues del pipe. y uno mas,
        // que queda con null

        EjecutarConModificadores ejecutar = new EjecutarConModificadores(tokensA);
        if (tokens[0].equals("tail")) {// primero bloque de IFs para procesar el primer comando
            tokens = tokensA;
            msjComando1 = ejecutar.ejecutarTail(listaFicheros);
        } else if (tokens[0].equals("head")) {
            tokens = tokensA;
            msjComando1 = ejecutar.ejecutarHead(listaFicheros);
        } else {
            mensaje = ">> Sintaxis incorrecta: el primer comando debe ser head o tail <<\n";
        }
        if (tokens[0].equals("head") || tokens[0].equals("tail")) {
            EjecutarConModificadores ejecutar2 = new EjecutarConModificadores(tokensB);
            if (tokensB[0].equals("grep") && tokensB.length == 3) { // segundo bloque de IFs para procesar el segundo comando
                listaFicheros.agregarFichero(new Archivo("especificado", msjComando1)); // creo un archivo con el contenido del
                // mensaje1. Ya que grep esta programado para
                // levantar contenido de ficheros.
                tokensB[2] = "especificado"; // modifico tokens para poner como 3 parametro el nombre del archivo temporal.
                tokens = tokensB;
                msjComando2 = ejecutar2.ejecutarGrep(listaFicheros);// Va a tomar el tokens global, el cual no esta como lo necesita.
                // PROUESTA: que todos los ejecutarComando() reciban el tokens como
                // parametro.
                // borro ese archivo que no debe existir mas.
                listaFicheros.eliminarFichero("especificado");
                mensaje = msjComando2;
            } else {
                mensaje = ">> Sintaxis incorrecta: en segundo comando debe ser [grep 'expresión'] <<\n";
            }
        }
        return mensaje;
    }

    /**
     * Metodo para validar la sintaxis de los comandos que no utilizan
     * modificadores "-"
     *
     *
     * @return la validez de la sintaxis en base a la cantidad de tokens
     * esperados
     *
     * "200" se interpreta como sintaxis
     */
    private String validarSintaxis() {
        boolean sintaxisCorrecta = false;
        String mensaje;

        switch (tokens[0]) {

            case "chmod":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "cp":
                sintaxisCorrecta = false;//Futura implementacion
                break;
            case "mv":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "mkdir":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "grep":
                sintaxisCorrecta = (tokens.length == 3);
                break;
            case "kill":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "cat":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "man":
                sintaxisCorrecta = (tokens.length == 1) || (tokens.length == 2);
                break;
            case "rmdir":
                sintaxisCorrecta = (tokens.length == 2);
                break;
            case "clear":
                sintaxisCorrecta = (tokens.length == 1);
                break;
            case "ps":
                sintaxisCorrecta = (tokens.length == 1);
                break;

        }
        if (sintaxisCorrecta) {
            mensaje = "200";
        } else {
            mensaje = ">> Sintaxis incorrecta intente man " + tokens[0] + "<<\n";
        }
        esValido = sintaxisCorrecta;
        return mensaje;
    }
}
