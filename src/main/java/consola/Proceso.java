package consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Esta clase representa un porceso del sistema operativo. Tiene los atributos y
 * métodos necesarios para ello.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public class Proceso {

    /**
     * PID de la instancia actual del proceso. Permite identificarlo. Se espera
     * que tenga un valor entre 1 y 100.
     */
    private int pid;

    /**
     * Siguiente PID a asignar, propio de la clase y no de la instancia.
     */
    private static int siguientePid = 1;

    /**
     * Usuario que creo el proceso.
     */
    private String usuario;

    /**
     * Porcentaje de memoria de la máquina que ocupa el proceso.
     */
    private double memoria;

    /**
     * Porcentaje de CPU de la maquina que ocupa el proceso.
     */
    private double cpu;

    /**
     * Instrucción que dio origen al proceso.
     */
    private String instruccion;

    /**
     * Indica el formato de fecha.
     */
    private DateTimeFormatter formatoFecha;

    /**
     * Hora en la que se creo proceso.
     */
    private String hora;

    /**
     * Constructor que crea un proceso a partir de una instrucción.
     *
     * @param instruccion del sistema.
     */
    public Proceso(String instruccion) {
        Random random = new Random();
        this.pid = siguientePid++;
        this.usuario = "USER";
        this.memoria = random.nextInt(100) + 1; //Valor entre 1 y 100
        this.cpu = random.nextInt(100) + 1;
        this.instruccion = instruccion;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora = LocalDateTime.now().format(formatoFecha);
    }

    /**
     * Constructor que crea un proceso a partir de una instrucción, uso en cpu y
     * memoria.
     *
     * @param instruccion del sistema.
     * @param memoria en uso.
     * @param cpu en uso.
     *
     */
    public Proceso(String instruccion, double memoria, double cpu) {
        this.pid = siguientePid++;
        this.usuario = "USER";
        this.memoria = memoria;
        this.cpu = cpu;
        this.instruccion = instruccion;
        this.formatoFecha = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora = LocalDateTime.now().format(formatoFecha);
    }

    /**
     * Método que permite obtener el id del proceso.
     *
     * @return id del proceso.
     */
    public int getPid() {
        return pid;
    }

    /**
     * Método que permite obtener el usuario que ejecutó el proceso.
     *
     * @return usuario que ejecutó el proceso.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Método que permite obtener la memoria que usa el proceso.
     *
     * @return memoria en uso.
     */
    public double getMemoria() {
        return memoria;
    }

    /**
     * Método que permite obtener el porcentaje de cpu en uso por el proceso.
     *
     * @return porcentaje de uso de cpu.
     */
    public double getCpu() {
        return cpu;
    }

    /**
     * Método que permite obtener la instrucción.
     *
     * @return instrucción del proceso.
     */
    public String getInstruccion() {
        return instruccion;
    }

    /**
     * Método que permite obtener la hora en la que se ejecutó el proceso.
     *
     * @return hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Establece el valor de pid.
     *
     * @param pid id del proceso.
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * Establece el usuario.
     *
     * @param usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Establece el valor de memoria.
     *
     * @param memoria en uso.
     */
    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    /**
     * Establece el valor de cpu.
     *
     * @param cpu en uso.
     */
    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    /**
     * Establece el valor de la instrucción.
     *
     * @param instruccion instrucción.
     */
    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    /**
     * Establece el valor de hora.
     *
     * @param hora de ejecución.
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Método que permite transformar un objeto de tipo Proceso a String.
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
                + instruccion;
    }
}
