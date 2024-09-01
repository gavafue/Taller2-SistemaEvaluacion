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
    }

    public String recibirMensaje() throws IOException {
        String mensaje = in.readUTF();//Es necesario manejar las excepciones
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
        boolean online;
        try {
            enviarMensaje(obtenerDireccionIP() + ",;," + "Prueba" + ",;," + "Conexion");
            System.out.println(recibirMensaje());//Se podria establecer una respuesta concreta del server
            //para validar la conexion
            online = true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            online = false;
        }
        cerrarConexion();
        return online;
    }
}
