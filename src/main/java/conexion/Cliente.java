package conexion;

import java.io.IOException;

/**
 * Esta clase representa al cliente e interactua de forma directa con conexión e
 * interfaz gráfica.
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class Cliente {

    /**
     * Número identificador del cliente, en general la CI.
     */
    private String id; 
    
    /**
     * Última instruccion por enviar del cliente al servidor.
     */
    private String instruccion; 
    
    /**
     * Última respuesta dada por el servidor al cliente.
     */
    private String respuesta; 
    
    /**
     * Conexión entre el cliente y el servidor.
     */
    private ConexionCliente conexion;

    /**
     * Este método establece una conexión con el servidor en la dirección IP y
     * puerto especificados.
     *
     * @throws IOException si ocurre un error al intentar establecer la
     * conexión.
     */
    public void establecerConexion() throws IOException {
        conexion = new ConexionCliente("127.0.0.1", 6464);
    }

    /**
     * Este método permite formatear un mensaje que será enviado por el cliente
     * al servidor. Combina el mensaje, la clase y la operación en un formato
     * específico.
     *
     * @param mensaje el contenido del mensaje a enviar.
     * @param clase la clase involucrada en la operación.
     * @param operacion la operación que se va a realizar.
     * @return el mensaje formateado para ser enviado al servidor.
     */
    public String formatearMensaje(String mensaje, String clase, String operacion) {
        return mensaje + ",;," + clase + ",;," + operacion;
    }

    /**
     * Este método permite obtener el código devuelto por el servidor. El código
     * se encuentra en la segunda posición del mensaje recibido, separado por
     * ",;,".
     *
     * @return el código enviado por el servidor.
     */
    public String obtenerCodigo() {
        String[] tokens = respuesta.split(",;,");
        return tokens[1];
    }

    /**
     * Este método permite obtener el mensaje enviado por el servidor. El
     * mensaje se encuentra en la primera posición del mensaje recibido,
     * separado por ",;,".
     *
     * @return el mensaje enviado por el servidor.
     */
    public String obtenerMensaje() {
        String[] tokens = respuesta.split(",;,");
        return tokens[0];
    }

    /**
     * Este método devuelve el ID actual.
     *
     * @return el ID del cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Este método devuelve la instrucción actual.
     *
     * @return la instrucción enviada por el cliente.
     */
    public String getInstruccion() {
        return instruccion;
    }

    /**
     * Este método devuelve la respuesta actual recibida del servidor.
     *
     * @return la respuesta recibida del servidor.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Este método devuelve la conexión actual con el servidor.
     *
     * @return la conexión establecida con el servidor.
     */
    public ConexionCliente getConexion() {
        return conexion;
    }

    /**
     * Este método establece el ID del cliente.
     *
     * @param id el nuevo ID del cliente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Este método establece la instrucción que será enviada por el cliente.
     *
     * @param instruccion la nueva instrucción.
     */
    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    /**
     * Este método establece la respuesta recibida del servidor.
     *
     * @param respuesta la respuesta recibida que se va a almacenar.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Este método establece la conexión con el servidor.
     *
     * @param conexion la nueva conexión que se va a establecer.
     */
    public void setConexion(ConexionCliente conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que dada la instruccion a enviar por el cliente realiza el
     * intercambio de mensajes.
     *
     * @param instruccionAEnviar la instrucción a enviar del cliente al servidor.
     * @throws java.io.IOException si ocurre un error al intentar establecer la
     * conexión.
     */
    public void intercambiarMensajes(String instruccionAEnviar) throws IOException {
        this.establecerConexion();
        this.setInstruccion(instruccionAEnviar);
        this.getConexion().enviarMensaje(this.instruccion);
        this.setRespuesta(this.getConexion().recibirMensaje());
    }

    /**
     * Método que dado un fragemento de la instruccion lo concatena a la
     * instrucción a enviar.
     *
     * @param fragmento a concatenar.
     */
    public void concatenarMensaje(String fragmento) {
        String nuevaInstruccion = this.getInstruccion().concat(fragmento);
        this.setInstruccion(nuevaInstruccion);
    }

    /**
     * Método que da formato a la pregunta de tipo multiple. El formato
     * corresponde a
     * "enunciado,,,Multiple,,,puntaje,,,op1,,,op2,,,op3,,,op4,,,respuesta".
     *
     * @param enunciado enunciado de la pregunta.
     * @param puntaje puntaje asociado a la pregunta.
     * @param op1 primera posible repuesta.
     * @param op2 segunda posible respuesta.
     * @param op3 tercera posible respuesta.
     * @param op4 cuarta posible respuesta.
     * @param respuesta respuesta correcta.
     *
     */
    public void armarMultiple(String enunciado, String puntaje, String op1, String op2, String op3, String op4, String respuesta) {
        String pregunta = ";;;" + enunciado + ",,,Multiple,,," + puntaje + ",,," + op1 + ",,," + op2 + ",,," + op3 + ",,," + op4 + ",,," + respuesta;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a la pregunta de tipo vf. El formato corresponde a
     * "enunciado,,,VF,,,puntaje,,,respuesta".
     *
     * @param enunciado asociado a la pregunta.
     * @param puntaje asociado a la pregunta.
     * @param respuesta respuesta verdadero o falso.
     */
    public void armarVF(String enunciado, String puntaje, String respuesta) {
        String pregunta = ";;;" + enunciado + ",,,VF,,," + puntaje + ",,," + respuesta;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a la pregunta de tipo espacios. EL formato
     * corresponde a "enunciado,,,Completar,,,puntaje,,,respuestas separadas por
     * coma".
     *
     * @param enunciado asociado a la pregunta.
     * @param puntaje asociado a la pregunta.
     * @param respuestas separadas por coma.
     */
    public void armarEspacios(String enunciado, String puntaje, String respuestas) {
        String pregunta = ";;;" + enunciado + ",,,Completar,,," + puntaje + ",,," + respuestas;
        this.concatenarMensaje(pregunta);
    }

    /**
     * Método que da formato a las respuestas del estudiante, ordenadas. El
     * formato corresponde a "respuesta0;;;respuesta1;;;...;;;respuestaN".
     *
     * @param respuestas dadas por el estudiante a lo largo de la evaluación.
     * @param respuesta de la pregunta actual.
     * @return respuestas actuales.
     */
    public String prepararRespuestas(String respuestas, String respuesta) {
        String[] tokens = respuesta.split(",;,");
        tokens = tokens[0].split(";;;");
        respuestas += ";;;" + tokens[0];
        return respuestas;
    }
}
