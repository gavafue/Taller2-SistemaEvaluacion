package consola;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Esta clase maneja una coleccion de procesos.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public class Procesos {

    /**
     * Coleccion.
     */
    private ArrayList<Proceso> listaProcesos;

    /**
     * Memoria disponible y compartido entre todos los procesos.
     */
    private int memoriaDisponible = 100;

    /**
     * Cpu disponible y compartido entre todos los procesos.
     */
    private int cpuDisponible = 100;

    /**
     * return memoriaDisponible
     */
    public int getMemoriaDisponible() {
        return memoriaDisponible;
    }

    /**
     * Establece la memoria disponible en el sistema por la dada en
     *
     * @param memoriaDisponible
     */
    public void setMemoriaDisponible(int memoriaDisponible) {
        this.memoriaDisponible = memoriaDisponible;
    }

    /**
     * return cpuDisponible
     */
    public int getCpuDisponible() {
        return cpuDisponible;
    }

    /**
     * Establece el cpu disponible en el sistema por la dado en
     *
     * @param cpuDisponible
     */
    public void setCpuDisponible(int cpuDisponible) {
        this.cpuDisponible = cpuDisponible;
    }

    /**
     * Constructor.
     */
    public Procesos() {
        this.listaProcesos = new ArrayList<>();
        cargarProcesos();
    }

    /**
     * @return listaProcesos
     */
    public ArrayList<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    /**
     * Establece el valor de listaProcesos recibido por
     *
     * @param listaProcesos
     */
    public void setListaProcesos(ArrayList<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    /**
     * Metodo que dado el comando a ejecutar, crea y agrega un proceso a la
     * lista de procesos.
     *
     * @param comando
     */
    public void agregarProceso(String comando) {
        Random random = new Random();
        int memoriaProceso = random.nextInt(getMemoriaDisponible() - 10);  // CONTEMPLAR POSIBILIDAD DE QUE DE 0. ME RESERVO 10% PARA LOS QUE VIENEN.
        setMemoriaDisponible(getMemoriaDisponible() - memoriaProceso);
        int cpuProceso = random.nextInt(getCpuDisponible() - 10);  // CONTEMPLAR POSIBILIDAD DE QUE DE 0. ME RESERVO 10% PARA LOS QUE VIENEN.
        setCpuDisponible(getCpuDisponible() - cpuProceso);
        Proceso proceso = new Proceso(comando, memoriaProceso, cpuProceso);
        listaProcesos.add(proceso);
    }

    /**
     * Metodo que dado el numero de id de proceso determina si existe.
     *
     * @param pid
     * @return si existe el proceso o no
     */
    public boolean existeProceso(int pid) {
        boolean existe = false;
        for (Proceso p : getListaProcesos()) {
            if (p.getPid() == pid) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    /**
     * Metodo que dado el numero de id del proceso permite obtenerlo de la
     * lista. Asume que existe el proceso.
     *
     * @param pid
     * @return
     */
    public Proceso obtenerProceso(int pid) {
        boolean encontrado = false;
        Proceso proceso = null;
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while ((iterator.hasNext()) && (!encontrado)) {
            proceso = iterator.next();
            if (proceso.getPid() == pid) {
                encontrado = true;
            }
        }
        return proceso;
    }

    /**
     * Este metodo permite cargar algunos procesos predefinidos al sistema.
     */
    public void cargarProcesos() {

        String[] posiblesProcesos = {"ls", "mv", "man", "ps", "kill", "grep", "tail", "cut", "head", "tail", "chmod", "sort", "mv", "mkdir", "rmdir", "clear", "cp", "|"};
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int azar = random.nextInt(posiblesProcesos.length);
            this.agregarProceso(posiblesProcesos[azar]);
        }
    }
}
