package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Esta clase representa un porceso del sistema operativo. Tiene los atributos y
 * metodos necesarios para ello.
 *
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 */
public class Proceso {

    /**
     * PID de la instancia actual del proceso. Permite identificarlo. Se espera
     * que tenga un valor entre 1 y 100.
     */
    private int pid;

    private static int siguientePid = 1; // Siguiente PID a asignar, propio de la clase
    /**
     * Usuario que creo el proceso.
     */
    private String usuario;

    /**
     * Porcentaje de memoria de la maquina que ocupa el proceso.
     */
    private double memoria;

    /**
     * POrcentaje de CPU de la maquina que ocupa el proceso.
     */
    private double cpu;

    /**
     * Comando que dio origen al proceso.
     */
    private String comando;

    /**
     * Indica el formato de fecha.
     */
    private DateTimeFormatter formatoFecha;
    /**
     * Hora en la que se creo proceso.
     */
    private String hora;

    /**
     * Constructor.
     * @param comando
     */
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
