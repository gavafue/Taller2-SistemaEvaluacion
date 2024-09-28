package gui;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import java.util.ArrayList;
/**
 * Clase destinada a modificar el renderizado estándar de la tabla
 * colocando colores diferentes segun el contenido
 * 
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */

public class RendererColores extends DefaultTableCellRenderer {
    
    /**
     * Almacena las respuestasCorrectas para compararlas
     * con el contenido de la celda
     *
     */
    private ArrayList<String> respuestasCorrectas;
    
    
    public RendererColores(ArrayList<String> respuestasCorrectas) {        
        this.respuestasCorrectas = respuestasCorrectas;
    }
   
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean estaSeleccionado,
            boolean tieneFoco, int fila, int columna) {

        Component celda = super.getTableCellRendererComponent(tabla, valor, estaSeleccionado, tieneFoco, fila, columna);

        String respuestaEstudiante = (String) valor;
        String respuestaCorrecta = respuestasCorrectas.get(fila);
        celda.setFont(celda.getFont().deriveFont(Font.BOLD));        
        
        if (respuestaEstudiante.equals(respuestaCorrecta)) {            
            celda.setForeground(Color.GREEN);
        } else {           
            celda.setForeground(Color.RED);
        }

        return celda;
    }
}

