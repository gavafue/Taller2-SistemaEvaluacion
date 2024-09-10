package consola;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase permite validar un comando y sus opciones.
 *
 */
public class Validar {

    private String comandoIngresado; // El comando ingresado por el usuario
    private String[] tokens; // Los tokens resultantes de dividir la orden.

    /**
     * Constructor que inicializa el comando a validar.
     *
     * @param comandoIngresado El comando ingresado por el usuario.
     */
    public Validar(String comandoIngresado) {
        this.comandoIngresado = comandoIngresado.stripLeading(); //Agregando este strip, carece de sentido el verificar si comienza con espacio. Se boora el metodo para ello.
        this.tokens = this.comandoIngresado.split(" ");
    }

    /**
     * @return comandoIngresado
     */
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    /**
     * @return tokens
     */
    public String[] getTokens() {
        return tokens;
    }

    /**
     * Establece el valor de comandoIngresado y tokens a partir del
     *
     * @param input de la linea de la terminal.
     */
    public void setComandoIngresado(String input) {
        this.comandoIngresado = input.stripLeading();
        this.tokens = input.stripLeading().split(" ");
    }

    /**
     * Método que determina si el comando ingresado es válido o no. Este método
     * verifica si el comando ingresado cumple con los criterios básicos de
     * validez, tales como no comenzar con un espacio y ser un comando
     * reconocido. También verifica si las opciones proporcionadas con el
     * comando son válidas.
     *
     * @param comandos La lista de comandos disponibles.
     * @return true si el comando es válido, false en caso contrario.
     */
    public boolean validarComando(Comandos comandos) {
        boolean esValido = true;

        String comando = tokens[0];
        if (esValido && !comandos.existeComando(comando)) {
            esValido = false;
        }

        if (esValido) {
            String[] opcionesValidas = comandos.obtenerOpciones(comando);
            if (opcionesValidas != null) {
                esValido = validarOpciones(opcionesValidas);
            }
        }

        return esValido;
    }

    /**
     * Método que determina si las opciones del comando ingresado son válidas.
     * Este método recorre las opciones del comando (tokens) y verifica si cada
     * una de ellas es una opción válida utilizando el método auxiliar
     * esOpcionValida.
     *
     * @param opcionesValidas Las opciones válidas para el comando.
     * @return true si todas las opciones son válidas, false en caso contrario.
     */
    private boolean validarOpciones(String[] opcionesValidas) {
        boolean todasValidas = true;

        for (int i = 1; i < tokens.length && todasValidas; i++) {
            todasValidas = esOpcionValida(tokens[i], opcionesValidas);
        }

        return todasValidas;
    }

    /**
     * Método auxiliar que verifica si una opción es válida. Este método compara
     * una opción específica (token) con la lista de opciones válidas para
     * determinar si es aceptable.
     *
     * @param opcion La opción a validar.
     * @param opcionesValidas Las opciones válidas para el comando.
     * @return true si la opción es válida, false en caso contrario.
     */
    private boolean esOpcionValida(String opcion, String[] opcionesValidas) {
        boolean esValida = false;

        for (String valida : opcionesValidas) {
            if (opcion.equals(valida)) {
                esValida = true;
                break;
            }
        }

        return esValida;
    }

    /**
     * Método que busca la existencia del caracter pipe y devuelve su posición
     *
     * @return la poscion en la que se encuentra el pipe
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
}
