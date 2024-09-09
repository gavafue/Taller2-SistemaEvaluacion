package consola;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase permite validar un comando y sus opciones.
 *
 * @since version 1
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

    // Getters
    public String getComandoIngresado() {
        return comandoIngresado;
    }

    public String[] getTokens() {
        return tokens;
    }

    // Setters
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
        if (!comandos.existeComando(comando)) {
            esValido = false;
        }
        if (esValido) {
            String[] opcionesValidas = comandos.obtenerOpciones(comando);
            if (opcionesValidas != null) {
                esValido = validarOpciones();
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
    private boolean validarOpciones() {
        
        boolean todasValidas = false;
        
        
        switch (tokens[0]){                    
            
            case "ls":
                        todasValidas=validarOpLs();               
                                       
                break;
            case "head":
                break;
            case "tail":
                 break;
            case "sort":
                  break;
            case "cut":
                  break;
                
        }
            return todasValidas;
    }
    
     
    private boolean compararConOpcPosibles (String comando,String[]aComprobar) {
        Comandos comandos = new Comandos();
        //arreglo con las opciones validas del comando pasado por parametros
        String [] opcionesValidas = comandos.obtenerOpciones(comando);

         for (String opcion : aComprobar) {
                if (!Arrays.asList(opcionesValidas).contains(opcion)) {
                    return false; // Si algún elemento no está, devuelve false
                }
            }
            return true; // Si todos los elementos están, devuelven true
        }    
    
    private boolean validarOpLs() {        
        boolean todasValidas=false;
        
        if (tokens.length==1){
            todasValidas=true;
        } else {                        
            // Variable para verificar si hay duplicados
            boolean duplicadoEncontrado = false;
            int contador = 0;
            // Creo un arreglo booleano para marcar duplicados
            boolean[] yaIncluido = new boolean[tokens.length];
            // Recorrer el arreglo para contar cuántos elementos únicos empiezan con '-'
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].startsWith("-") && !yaIncluido[i]) {
                    // Marcar este elemento y buscar duplicados
                    for (int j = i + 1; j < tokens.length; j++) {
                        if (tokens[i].equals(tokens[j])) {
                            duplicadoEncontrado = true;
                            yaIncluido[j] = true; // Marcar duplicados
                            System.out.println("Duplicado encontrado: " + tokens[i]);
                        }
                    }
                    contador++;
                }
            }
            if (!duplicadoEncontrado&&(tokens.length>=2&&tokens.length<=4)){
                // Crear un nuevo arreglo sin duplicados
                String[] resultado = new String[contador];
                int index = 0;
                // Agregar los elementos únicos al nuevo arreglo
                for (String token : tokens) {
                    if (token.startsWith("-")) {
                        resultado[index++] = token;
                    }
                }
                todasValidas=compararConOpcPosibles("ls",resultado);                 
            } else {
                todasValidas=false;                 
            }
        }
        return todasValidas;
    }  
   
    
    /**
     * Método que busca la existencia del caracter pipe y devuelve su posición
     * 
     * @return la poscion en la que se encuentra el pipe 
     */    
    public int posicionPipe() {
        int indicePipe=0;
        // Creo un arraylist temporal para usar su metodo
        // contains(). Esto es mas corto que un for y el codigo mas legible          
        ArrayList<String>tokensParaElPipe = new ArrayList<>(Arrays.asList(tokens));
        if (tokensParaElPipe.contains("|")){         
             indicePipe=tokensParaElPipe.indexOf("|");         
        }
        return indicePipe;    
    }
}
