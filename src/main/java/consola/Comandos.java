package consola;

import java.util.HashMap;

/**
 * Esta clase permite crear un HashMap de comandos para la consola Linux.
 *
 * @author Gabriel, Ana, Santiago, Juan y Gonzalo
 */
public class Comandos {

        /**
         * Colección de elementos tipo Comando. En esta colección la clave es el
         * nombre del comando. El nombre del comando es asignado al agregarlo a la
         * colección.
         *
         */
        private HashMap<String, Comando> hashComandos;

        /**
         * Constructor común que inicializa la colección y carga los comandos desde
         * memoria.
         */
        public Comandos() {
                hashComandos = new HashMap<String, Comando>();
                cargarComandos();
        }

        /**
         * Este método agrega un comando a la colección dado el nombre del comando y
         * sus características.
         *
         * @param nombre  del comando.
         * @param comando objeto que contiene las caracteristicas del comando.
         */
        public void agregarComando(String nombre, Comando comando) {
                hashComandos.put(nombre, comando);
        }

        /**
         * Este método determina si un comando existe.
         *
         * @param nombre del comando
         * @return si el comando existe o no.
         */
        public boolean existeComando(String nombre) {
                return hashComandos.containsKey(nombre);
        }

        /**
         * Este método devuelve la descripcion de un comando dado su nombre.
         *
         * @param nombre del comando
         * @return descripcion del comando.
         */
        public String obtenerDescripcion(String nombre) {
                String descripcion = "";
                if (this.existeComando(nombre)) {
                        descripcion = hashComandos.get(nombre).getDescripcion();
                } else {
                        descripcion = "No hay informacion disponible sobre el comando " + nombre;
                }
                return descripcion;
        }

        /**
         * Este método devuelve un ejemplo en formato String dado el nombre de un
         * comando.
         *
         * @param nombre del comando
         * @return ejemplo de uso del comando.
         */
        public String obtenerEjemplo(String nombre) {
                String ejemplo = "";
                if (this.existeComando(nombre)) {
                        ejemplo = hashComandos.get(nombre).getEjemplo();
                } else {
                        ejemplo = "No hay ejemplos disponibles sobre el comando " + nombre + "\n\n" + " Intente man"
                                        + "\n";
                }
                return ejemplo;
        }

        /**
         * Este método dado el nombre de un comando retorna sus extensiones o
         * opciones validas.
         *
         * @param nombre del comando
         * @return extensiones validas del comando
         */
        public String[] obtenerOpciones(String nombre) {
                return hashComandos.get(nombre).getOpciones();
        }

        /**
         * Este metodo carga los comandos en el sistema.
         */
        public void cargarComandos() {
                String opcLs[] = { "-l", "-a" };
                String opcSort[] = { "-n" };
                String opcHeadTail[] = { "-n" };
                String opcCut[] = { "-d", "-f" };

                Comando com0 = new Comando(
                                "Esta consola permite validar la sintaxis de varios comandos de Bash:\n" +
                                                " - ls: listar archivos\n" +
                                                " - mkdir: crear directorio\n" +
                                                " - rmdir: eliminar directorio\n" +
                                                " - mv: mover o renombrar archivos\n" +
                                                " - cat: mostrar contenido de archivos\n" +
                                                " - clear: limpiar la terminal\n" +
                                                " - ps: mostrar procesos en ejecución\n" +
                                                " - kill: terminar procesos\n" +
                                                " - grep: buscar patrones en texto\n" +
                                                " - tail: mostrar las últimas líneas de un archivo\n" +
                                                " - head: mostrar las primeras líneas de un archivo\n" +
                                                " - cut: recortar secciones de líneas\n" +
                                                " - sort: ordenar líneas de texto\n" +
                                                " - chmod: cambiar permisos de archivos.\n",
                                "Ejemplo: man ls  // Muestra la página del manual para el comando 'ls'.\n",
                                null);

                Comando com1 = new Comando(
                                "Lista el contenido de un directorio",
                                "\n     Este comando permite listar el contenido de un directorio." +
                                                "\n     Opciones disponibles:" +
                                                "\n        -l: Muestra una lista detallada de los archivos (incluye permisos, propietario, tamaño, etc.)."
                                                +
                                                "\n        -a: Muestra todos los archivos, incluyendo archivos ocultos."
                                                +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        ls /etc -l -a" +
                                                "\n        Lista el contenido del directorio /etc con detalles y archivos ocultos.\n\n",
                                opcLs);

                Comando com3 = new Comando(
                                "Crea un directorio",
                                "\n     Este comando permite crear un nuevo directorio en el sistema de archivos." +
                                                "\n     Sintaxis básica:" +
                                                "\n        mkdir <nombre_del_directorio>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        mkdir dir1" +
                                                "\n        Crea un directorio llamado 'dir1' en el directorio actual." +
                                                "\n",
                                null);

                Comando com4 = new Comando(
                                "Borra un directorio sin contenido",
                                "\n     Este comando permite eliminar un directorio vacío del sistema de archivos." +
                                                "\n     Sintaxis básica:" +
                                                "\n        rmdir <nombre_del_directorio>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        rmdir dir1\n",
                                null);

                Comando com5 = new Comando(
                                "Renombra un archivo o directorio",
                                "\n     Este comando permite renombrar un archivo o directorio existente." +
                                                "\n     Sintaxis básica:" +
                                                "\n        mv <nombre_actual> <nuevo_nombre>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        mv arch1 arch2" +
                                                "\n        Renombra el archivo o directorio 'arch1' como 'arch2'." +
                                                "\n" +
                                                "\n     Nota: El comando solo cambia el nombre del archivo o directorio, manteniendo su ubicación actual.\n\n",
                                null);

                Comando com6 = new Comando(
                                "Muestra el contenido de un archivo",
                                "\n     Este comando permite visualizar el contenido de un archivo de texto en la terminal."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        cat <nombre_del_archivo>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        cat respaldo.txt" +
                                                "\n        Muestra el contenido del archivo 'respaldo.txt' en la pantalla."
                                                +
                                                "\n" +
                                                "\n     Nota: Este comando solo muestra el contenido del archivo, no lo modifica.\n\n",
                                null);

                Comando com7 = new Comando(
                                "Borra el texto en pantalla",
                                "\n     Este comando permite limpiar la terminal, eliminando todo el texto visible en pantalla."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        clear" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        clear" +
                                                "\n        Borra todo el texto en la terminal, dejando la pantalla vacía."
                                                +
                                                "\n" +
                                                "\n     Nota: Este comando no acepta parámetros ni afecta el contenido del historial de comandos.\n\n",
                                null);

                Comando com8 = new Comando(
                                "Muestra los procesos activos",
                                "\n     Este comando permite listar los procesos que están siendo ejecutados actualmente en el sistema."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        ps" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        ps" +
                                                "\n        Muestra una lista de los procesos activos en la terminal." +
                                                "\n" +
                                                "\n     Nota: Este comando no acepta parámetros.\n\n",
                                null);

                Comando com9 = new Comando(
                                "Elimina un proceso según su ID",
                                "\n     Este comando permite finalizar un proceso activo en el sistema utilizando su ID de proceso (PID)."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        kill <pid>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        kill 1234" +
                                                "\n        Elimina el proceso con ID '1234'." +
                                                "\n" +
                                                "\n     Nota: Este comando no admite parámetros. Es necesario conocer el PID del proceso a finalizar.\n\n",
                                null);

                Comando com10 = new Comando(
                                "Busca patrones en archivos de texto",
                                "\n     Este comando permite buscar una palabra o patrón dentro de un archivo de texto."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        grep <patrón> <nombre_del_archivo>" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        grep palabraAbuscar nombreArchivo" +
                                                "\n        Busca 'palabraAbuscar' dentro del archivo 'nombreArchivo' y muestra las líneas que coincidan."
                                                +
                                                "\n",
                                null);

                // tail RF30.
                Comando com11 = new Comando(
                                "Mostrar las últimas líneas de un archivo",
                                "\n     Este comando permite visualizar las últimas líneas de un archivo de texto." +
                                                "\n     Sintaxis básica:" +
                                                "\n        tail [opción] [nombre_del_archivo]" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        tail -n 10 nombreArchivo" +
                                                "\n        Muestra las últimas 10 líneas del archivo 'nombreArchivo'." +
                                                "\n" +
                                                "\n     Opciones disponibles:" +
                                                "\n        -n <número>: Especifica el número de líneas que se desean mostrar desde el final del archivo."
                                                +
                                                "\n" +
                                                "\n     Nota: Si no se especifica ninguna opción, el comando mostrará las últimas 10 líneas por defecto.\n\n",
                                opcHeadTail);
                // head RF31.
                Comando com12 = new Comando(
                                "Mostrar las primeras líneas de un archivo",
                                "\n     Este comando permite visualizar las primeras líneas de un archivo de texto." +
                                                "\n     Sintaxis básica:" +
                                                "\n        head [opción] [nombre_del_archivo]" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        head -n 10 nombreArchivo" +
                                                "\n        Muestra las primeras 10 líneas del archivo 'nombreArchivo'."
                                                +
                                                "\n" +
                                                "\n     Opciones disponibles:" +
                                                "\n        -n <número>: Especifica el número de líneas que se desean mostrar desde el principio del archivo."
                                                +
                                                "\n" +
                                                "\n     Nota: Si no se especifica ninguna opción, el comando mostrará las primeras 10 líneas por defecto.\n\n",
                                opcHeadTail); // admite como opciones “-n
                                              // [número]” si no se
                // especifica, las primeras 10.
                // cut RF34
                // admite como opciones “-d [delimitador]"
                Comando com13 = new Comando(
                                "Extraer secciones de cada línea de un archivo",
                                "\n     Este comando permite extraer partes específicas de cada línea de un archivo de texto."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        cut [opción] [argumento]" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        cut -d ':' -f 1,4 archivo.txt" +
                                                "\n        Extrae el primer y cuarto campo de cada línea del archivo 'archivo.txt', utilizando ':' como delimitador."
                                                +
                                                "\n" +
                                                "\n     Opciones disponibles:" +
                                                "\n        -d ':' como el único carácter delimitador que se utiliza para dividir las líneas en campos."
                                                +
                                                "\n        -f <campos>: Indica qué campos se desean extraer, separados por comas."
                                                +
                                                "\n",
                                opcCut);

                // sort RF38
                // La consola permitirá el comando “sort” para mostrar en pantalla las líneas de
                // un
                // archivo de manera ordenada. No hay modificacion en el archivo.
                Comando com14 = new Comando(
                                "Ordenar las líneas de un archivo",
                                "\n     Este comando permite ordenar las líneas de un archivo de texto."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        sort [opciones] [archivo]" +
                                                "\n" +
                                                "\n     Ejemplo de uso:" +
                                                "\n        sort archivo.txt" +
                                                "\n        Ordena las líneas del archivo 'archivo.txt' alfabéticamente."
                                                +
                                                "\n" +
                                                "\n        sort -n archivo.txt" +
                                                "\n        Ordena las líneas del archivo 'archivo.txt' de manera numérica."
                                                +
                                                "\n" +
                                                "\n     Opciones disponibles:" +
                                                "\n        -n: Ordena las líneas numéricamente en lugar de alfabéticamente."
                                                +
                                                "\n" +
                                                "\n     Nota: Si no se especifican opciones, el comando ordenará las líneas alfabéticamente por defecto.\n\n",
                                opcSort);
                // opciones disponibles “-n” para ordenar numéricamente.
                Comando com15 = new Comando(
                                "Modificar los permisos asignados en un archivo o directorio",
                                "\n     Este comando permite cambiar los permisos de acceso de un archivo o directorio."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        chmod [opciones] [permisos] [archivo/directorio]" +
                                                "\n" +
                                                "\n     Ejemplos de uso:" +
                                                "\n        chmod rwxrwxrwx arch1" +
                                                "\n        Establece los permisos de lectura, escritura y ejecución para el propietario, grupo y otros en 'arch1'."
                                                +
                                                "\n" +
                                                "\n        chmod 555 arch1" +
                                                "\n        Establece permisos de lectura y ejecución para el propietario, grupo y otros en 'arch1', sin permisos de escritura."
                                                +
                                                "\n" +
                                                "\n     Notas sobre los permisos:" +
                                                "\n        - Los permisos pueden especificarse en formato simbólico (como 'rwx') o en formato octal (como '555')."
                                                +
                                                "\n        - Las letras tienen los siguientes significados: 'r' (lectura), 'w' (escritura), 'x' (ejecución)."
                                                +
                                                "\n        - En formato octal, cada dígito representa un conjunto de permisos: el primer dígito es para el propietario, el segundo para el grupo y el tercero para otros.\n\n",
                                null);

                // RF39. La consola permitirá el uso de pipe ( | ) entre los comandos tail, grep
                // y head. La sintaxis para el uso del pipe sería la siguiente “comando1 |
                // comando2”
                Comando com16 = new Comando(
                                "Redirigir la salida del primer comando hacia la entrada del segundo",
                                "\n     Este comando permite tomar la salida de un comando (como 'head' o 'tail') y usarla como entrada para otro comando (como 'grep')."
                                                +
                                                "\n     Sintaxis básica:" +
                                                "\n        comando1 | comando2" +
                                                "\n" +
                                                "\n     Ejemplos de uso:" +
                                                "\n        head arch1 | grep INET" +
                                                "\n        Toma las primeras líneas de 'arch1' y busca las que contienen 'INET'."
                                                +
                                                "\n" +
                                                "\n        tail archivo.txt | grep ERROR" +
                                                "\n        Toma las últimas líneas de 'archivo.txt' y busca las que contienen 'ERROR'."
                                                +
                                                "\n" +
                                                "\n     Notas:" +
                                                "\n        - 'head' muestra las primeras líneas de un archivo, mientras que 'tail' muestra las últimas."
                                                +
                                                "\n        - 'grep' busca líneas que coincidan con un patrón específico en la entrada proporcionada."
                                                +
                                                "\n",
                                null);

                agregarComando("man", com0);
                agregarComando("ls", com1);
                agregarComando("mkdir", com3);
                agregarComando("rmdir", com4);
                agregarComando("mv", com5);
                agregarComando("cat", com6);
                agregarComando("clear", com7);
                agregarComando("ps", com8);
                agregarComando("kill", com9);
                agregarComando("grep", com10);
                agregarComando("tail", com11);
                agregarComando("head", com12);
                agregarComando("cut", com13);
                agregarComando("sort", com14);
                agregarComando("chmod", com15);
                agregarComando("|", com16);
        }
}
