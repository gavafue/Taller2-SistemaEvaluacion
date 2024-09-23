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
     * Coleccion de procesos del sistema.
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
     * Constructor que crea una colección de procesos vacía y la carga desde
     * memoria.
     */
    public Procesos() {
        this.listaProcesos = new ArrayList<>();
        cargarProcesos();
    }

    /**
     * Método que permite obtener la memoria disponible. return
     * memoriaDisponible
     */
    public int getMemoriaDisponible() {
        return memoriaDisponible;
    }

    /**
     * Establece la memoria disponible en el sistema.
     *
     * @param memoriaDisponible en el sistema.
     */
    public void setMemoriaDisponible(int memoriaDisponible) {
        this.memoriaDisponible = memoriaDisponible;
    }

    /**
     * Método que permite obtener el cpu disponible en el sistema. return
     * cpuDisponible en el sistema.
     */
    public int getCpuDisponible() {
        return cpuDisponible;
    }

    /**
     * Establece el cpu disponible en el sistema.
     *
     * @param cpuDisponible en el sistema.
     */
    public void setCpuDisponible(int cpuDisponible) {
        this.cpuDisponible = cpuDisponible;
    }

    /**
     * Método que permite obtener la lista de procesos del sistema.
     *
     * @return lista de procesos del sistema.
     */
    public ArrayList<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    /**
     * Establece el valor de listaProcesos.
     *
     * @param listaProcesos a establecer.
     */
    public void setListaProcesos(ArrayList<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    /**
     * Método que permite crear un proceso dado el nombre de instrucción.
     *
     * @param instruccion que desemboca al proceso.
     */
    public void agregarProceso(String instruccion) {
        Random random = new Random();
        int memoriaProceso = random.nextInt(getMemoriaDisponible() - 10);  // CONTEMPLAR POSIBILIDAD DE QUE DE 0. ME RESERVO 10% PARA LOS QUE VIENEN.
        setMemoriaDisponible(getMemoriaDisponible() - memoriaProceso);
        random = new Random();
        int cpuProceso = random.nextInt(getCpuDisponible() - 10);  // CONTEMPLAR POSIBILIDAD DE QUE DE 0. ME RESERVO 10% PARA LOS QUE VIENEN.
        setCpuDisponible(getCpuDisponible() - cpuProceso);
        Proceso proceso = new Proceso(instruccion, memoriaProceso, cpuProceso);
        listaProcesos.add(proceso);
    }

    /**
     * Método que dado el número de id de proceso determina si existe.
     *
     * @param pid id del proceso.
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
     * Método que dado el número de id del proceso permite obtenerlo de la
     * lista. Asume que existe el proceso.
     *
     * @param pid id del proceso.
     * @return el proceso con dicha id.
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
        String[] posiblesProcesos = {"chrome", "bash", "bluetoothd", "NetworkManager", "java", "libreoffice", "spotify", "firefox", "sshd", "apache2", "vsftpd", "vlc", "eclipse", "netbeans", "rmdir", "Gnome", "vscode", "mysqld"};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int azar = random.nextInt(posiblesProcesos.length);
            this.agregarProceso(posiblesProcesos[azar]);
        }
    }
}
