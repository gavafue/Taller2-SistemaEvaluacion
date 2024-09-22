package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase que establece una conexión cliente-servidor.
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class ConexionCliente {
    
    /**
     * Flujos de datos de entrada.
     */
    private final DataInputStream in;
    
    /**
     * Flujo de datos de salida.
     */
    private final DataOutputStream out;
    
    /**
     * Socket a utilizar.
     */
    private final Socket soc;

    /**
     * Este constructor establece una conexión con el servidor dado y un puerto específico.
     * Inicializa los flujos de entrada y salida de datos a través del socket.
     * 
     * @param server la dirección del servidor al que conectarse.
     * @param puerto el número del puerto en el que el servidor está escuchando.
     * @throws IOException si ocurre un error al intentar crear el socket o los flujos de datos.
     */
    public ConexionCliente(String server, int puerto) throws IOException {
        soc = new Socket(server, puerto); // Es necesario manejar las excepciones
        in = new DataInputStream(soc.getInputStream());
        out = new DataOutputStream(soc.getOutputStream());
    }
    
    /**
     * Este método cierra la conexión con el servidor.
     * 
     * @throws IOException si ocurre un error al intentar cerrar el socket.
     */
    public void cerrarConexion() throws IOException {
        soc.close();
    }

    /**
     * Este método permite enviar un mensaje al servidor.
     * 
     * @param mensaje el mensaje que será enviado al servidor.
     * @throws IOException si ocurre un error al intentar enviar el mensaje.
     */
    public void enviarMensaje(String mensaje) throws IOException {
        out.writeUTF(mensaje); // Es necesario manejar las excepciones
        System.out.println(" < Comunicación enviada: " + mensaje); // Temporal, para monitorear la comunicación
    }

    /**
     * Este método permite recibir un mensaje del servidor.
     * 
     * @return el mensaje recibido del servidor.
     * @throws IOException si ocurre un error al intentar recibir el mensaje.
     */
    public String recibirMensaje() throws IOException {
        String mensaje = in.readUTF(); // Es necesario manejar las excepciones
        System.out.println(" > Comunicación recibida: " + mensaje); // Temporal, para monitorear la comunicación
        return mensaje;
    }

    /**
     * Este método obtiene la dirección IP local del cliente.
     * 
     * @return la dirección IP del cliente como una cadena de texto, o una cadena vacía si ocurre un error.
     */
    public String obtenerDireccionIP() {
        String ipCliente;
        try {
            // Obtiene la dirección IP local del dispositivo
            InetAddress direccionIP = InetAddress.getLocalHost();
            ipCliente = direccionIP.getHostAddress();
        } catch (UnknownHostException e) {
            ipCliente = "";
        }
        return ipCliente;
    }

    /**
     * Este método prueba la conexión con el servidor enviando la IP del cliente junto con un mensaje de prueba.
     * Cierra la conexión después de realizar la prueba.
     * 
     * @return true si la conexión es exitosa, false si ocurre un error.
     * @throws IOException si ocurre un error durante la prueba de conexión.
     */
    public boolean probarConexion() throws IOException {
        boolean online;
        try {
            enviarMensaje(obtenerDireccionIP() + ",;," + "Prueba" + ",;," + "Conexion");
            online = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            online = false;
        }
        cerrarConexion();
        return online;
    }
}
