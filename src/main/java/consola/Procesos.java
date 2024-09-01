package consola;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase permite crear una lista de procesos.
 *
 * @author
 */
public class Procesos {

    //Atributos
    private LinkedList<Proceso> listaProcesos;

    //Constructor Vacio
    public Procesos() {
        this.listaProcesos = new LinkedList<>();
        cargarProcesos();
    }

    //Getter
    public List<Proceso> getListaProcesos() {
        return listaProcesos;
    }

    //Setter
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
     * Metodo que dado el numero de id de proceso determina si existe o no.
     *
     * @param pid
     * @return si existe el proceso o no
     */
    public boolean existeProceso(int pid) {
        boolean existe = false;
        Iterator<Proceso> iterator = listaProcesos.iterator();
        while ((iterator.hasNext()) && (!existe)) {
            Proceso proceso = iterator.next();
            if (proceso.getPid() == pid) {
                existe = true;
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
