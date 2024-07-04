package logica;


/**
 * Esta clase permite validar un comando y sus extensiones.
 *
 * @author
 * @since version 1
 */
public class Validar {

    //Atributos
    /**
     * String a validar. Es el input del usuario en la consola.
     */
    private String cadena;
    private String[] tokens;
    

    /**
     * Constructor comun.
     */
    public Validar(String cadena) {
        this.cadena = cadena;
        tokens = cadena.split(" "); //Tokenización de la cadena en funcion de espacios
    }

    //Getters
    public String getCadena() {
        return cadena;//Constructor comun
    }

    public String[] getTokens() {
        return tokens;
    }

 
    //Setters
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }



      /**
     * Metodo que determina si el comando ingresado es valido o no.
     *
     * @param comandos
     * @return
     */
    public boolean validarComando(Comandos comandos) {
        String conEspacio = "^\\s.*"; //Expresion regular que busca un espacio al principio del string
        boolean valido = false;
        if ((!cadena.matches(conEspacio)) && (comandos.existeComando(tokens[0]))) { //Si no tiene espacio al comienzo y existe el comando encontrado en el token 0.
            if ((comandos.obtenerOpciones(tokens[0])) == null) { //Si no cuenta con extensiones
                valido = true;
            } else {
                valido = comprobarOpciones(comandos);
            }
        }
        return valido;
    }

    /**
     * Metodo que determina si las extensiones del comando ingresado son validas
     * o no.
     *
     * @param comandos
     * @return
     */
    private boolean comprobarOpciones(Comandos comandos) {
        String[] validas = comandos.obtenerOpciones(tokens[0]); //Arreglo que contiene las extensiones validas del comando
        int i = 1; //Comienzo en la segunda posicion, porque el token 0 es el comando
        boolean todasValidas = true;
        while ((i < tokens.length) && (todasValidas)) {
            boolean encontrado = false;
            int j = 0;
            while ((j < validas.length) && (!encontrado)) {
                if (tokens[i].equals(validas[j])) { //Si es valido
                    encontrado = true;
                }
                j++;
            }
            //Si el token actual no coincide con ninguna opcion valida
            if (encontrado == false) {
                todasValidas = false;
            }
            i++;
        }
        return todasValidas;
    }
}
