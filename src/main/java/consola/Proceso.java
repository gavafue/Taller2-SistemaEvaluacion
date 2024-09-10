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
     *
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

    /**
     * @return pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return memoria
     */
    public double getMemoria() {
        return memoria;
    }

    /**
     * @return cpu
     */
    public double getCpu() {
        return cpu;
    }

    /**
     * @return comando
     */
    public String getComando() {
        return comando;
    }

    /**
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece el valor de pid dado por el
     *
     * @param pid - valor entero
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * Establece el valor de usuario dado por el
     *
     * @param usuario - valor String
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Establece el valor de memoria dado por el
     *
     * @param memoria - valor double
     */
    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    /**
     * Establece el valor de cpu dado por el
     *
     * @param cpu - valor double
     */
    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    /**
     * Establece el valor de comando dado por el
     *
     * @param comando - valor String
     */
    public void setComando(String comando) {
        this.comando = comando;
    }

    /**
     * Establece el valor de hora dado por el
     *
     * @param hora - valor String
     */
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
