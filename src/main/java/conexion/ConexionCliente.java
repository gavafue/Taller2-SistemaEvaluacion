package conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConexionCliente {

    private final String server;//IP del servidor
    private final int puerto;
    private final DataInputStream in;//Flujo de datos entrada
    private final DataOutputStream out;//Flujo de datos salida
    private final Socket soc;

    public ConexionCliente(String server, int puerto) throws IOException {
        this.server = server;
        this.puerto = puerto;
        soc = new Socket(server, puerto);//Es necesario manejar las excepciones
        in = new DataInputStream(soc.getInputStream());
        out = new DataOutputStream(soc.getOutputStream());
    }

    public void cerrarConexion() throws IOException {
        soc.close();
    }

    public void enviarMensaje(String mensaje) throws IOException {
        out.writeUTF(mensaje);//Es necesario manejar las excepciones
        System.out.println(" < Comunicacion enviada: " + mensaje);
    }

    public String recibirMensaje() throws IOException {
        String mensaje = "";
        try {
            mensaje = in.readUTF();
            System.out.println(" > Comunicacion recibida: " + mensaje);
        } catch (IOException e) {
            System.err.println("! Error al leer el mensaje: " + e.getMessage());
        }
        return mensaje;
    }

    public String obtenerDireccionIP() {
        String ipCliente;
        try {
            //Obtiene la dirección IP local del dispositivo
            InetAddress direccionIP = InetAddress.getLocalHost();
            ipCliente = direccionIP.getHostAddress();
        } catch (UnknownHostException e) {
            ipCliente = "";
        }
        return ipCliente;
    }

    public boolean probarConexion() throws IOException {
        boolean online = false; //Se supone falso hasta que responda el server.
        try {
            enviarMensaje(obtenerDireccionIP() + ",;," + "Prueba" + ",;," + "Conexion");
            //System.out.println(recibirMensaje());//Se podria establecer una respuesta concreta del server
            //para validar la conexion
            if (this.recibirMensaje().contains("200")) {
            online = true;
            }
        } catch (IOException ex) {
            System.out.println("! Error" + ex.getMessage());
            online = false;
        }
        cerrarConexion();
        return online;
    }
}
