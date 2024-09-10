package consola;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase maneja una coleccion de procesos.
 * 
 * @author Gabriel, Anna, Santiago, Juan y Gonzalo
 */
public class Procesos {

    /**
     * Coleccion.
     */
    private LinkedList<Proceso> listaProcesos;

    /**
     * Constructor. 
     */
    public Procesos() {
        this.listaProcesos = new LinkedList<>();
        cargarProcesos();
    }

    /**
     * @return listaProcesos
     */
    public List<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    /**
     * Establece el valor de listaProcesos recibido por
     *
     * @param listaProcesos
     */
    public void setListaProcesos(LinkedList<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    /**
     * Metodo que dado el comando a ejecutar, crea y agrega un proceso a la
     * lista de procesos.
     *
     * @param comando
     */
    public void agregarProceso(String comando) {
        Proceso proceso = new Proceso(comando);
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
        this.agregarProceso("ls dir1");
        this.agregarProceso("mv arch1 arch2");
        this.agregarProceso("mkdir dir6");
        this.agregarProceso("ls dir1");
        this.agregarProceso("mv arch1 arch2");
    }
}
