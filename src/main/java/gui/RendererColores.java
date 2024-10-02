package gui;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;

/**
 * Clase destinada a modificar el renderizado estándar de la tabla colocando
 * colores diferentes según si el contenido es "Correcto" o "Incorrecto".
 *
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class RendererColores extends DefaultTableCellRenderer {

    /**
     * Almacena las respuestas para determinar si es "Correcto" o "Incorrecto"
     */
    private String[] resultados;

    public RendererColores(String[] resultados) {
        this.resultados = resultados;
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean estaSeleccionado,
            boolean tieneFoco, int fila, int columna) {

        Component celda = super.getTableCellRendererComponent(tabla, valor, estaSeleccionado, tieneFoco, fila, columna);
        String resultado = resultados[fila];
        // Configuración general de la fuente
        celda.setFont(celda.getFont().deriveFont(Font.BOLD));
        celda.setForeground(Color.BLACK);
        // Colores suaves
        Color verdeSuave = new Color(144, 238, 144);
        Color rojoSuave = new Color(255, 182, 193); 
        // Asignar colores según "Correcto" o "Incorrecto"
        if ("Incorrecto".equalsIgnoreCase(resultado.trim())) {            
            celda.setBackground(rojoSuave);    // Fondo rojo suave
        } else if ("Correcto".equalsIgnoreCase(resultado.trim())) {            
            celda.setBackground(verdeSuave);   // Fondo verde suave
        } else {             
            celda.setBackground(Color.WHITE);  
        }
        // Mantener el color de fondo de selección si la celda está seleccionada
        if (estaSeleccionado) {
            celda.setBackground(tabla.getSelectionBackground());
            celda.setForeground(tabla.getSelectionForeground());
        }

        return celda;
    }
}
