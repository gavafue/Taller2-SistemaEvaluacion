package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Esta clase permite crear un proceso.
 *
 * @author
 */
public class Proceso {

    //Atributos
    private int pid; // PID de la instancia actual
    private static int siguientePid = 1; // Siguiente PID a asignar, propio de la clase
    private String usuario;
    private double memoria; //Porcentaje de memoria en uso
    private double cpu; //Porcentaje de CPU en uso
    private String comando;
    private DateTimeFormatter formatoFecha;
    private String hora; //Hora en la que se creo el proceso

    // Constructor
    public Proceso(String comando) {
        Random random = new Random();
        this.pid = siguientePid++;
        this.usuario = "USER";
        this.memoria = random.nextInt(100) + 1; //Valor entre 1 y 100
        this.cpu = random.nextInt(100) + 1;
        this.comando = comando;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora = LocalDateTime.now().format(formatoFecha);
    }

    // Getters
    public int getPid() {
        return pid;
    }

    public String getUsuario() {
        return usuario;
    }

    public double getMemoria() {
        return memoria;
    }

    public double getCpu() {
        return cpu;
    }

    public String getComando() {
        return comando;
    }

    public String getHora() {
        return hora;
    }

    // Setters
    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Metodo que permite transformar un objeto de tipo Proceso a String.
     *
     * @return proceso en formato String
     */
    @Override
    public String toString() {
        return ""
                + pid + " | "
                + usuario + " |  "
                + memoria + "  |  "
                + cpu + "  | "
                + hora + " | "
                + comando;
    }
}
