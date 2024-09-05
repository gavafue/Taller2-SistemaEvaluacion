package gui;

import conexion.Cliente;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame destinado a mostrar las respuestas correctas de una evaluación en
 * particular dado su título.
 *
 */
public class VerRespuestas extends javax.swing.JFrame {

    Cliente cliente;
    String titulo;

    /**
     * Constructor común que permite crear una instancia de la clase, dado el
     * cliente y el titulo de la evaluación.
     */
    public VerRespuestas(Cliente cliente, String titulo) {
        this.cliente = cliente;
        this.titulo = titulo;
        initComponents();
        this.solicitarPreguntasYRespuestas();
        setLocationRelativeTo(null);
    }

    /**
     * Método que permite obtener el cliente actual conectado.
     *
     * @return el cliente actualmente conectado.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que permite obtener el título de la evaluación.
     *
     * @return el título de la evaluación.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Método que permite modificar el cliente actual dado otro cliente.
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que permite modificar el título de la evluación dado otro título.
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método que le solicita al cliente las preguntas y respuestas correctas de
     * la evaluación seleccionada.
     */
    public void solicitarPreguntasYRespuestas() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones", "ObtenerCorrectas");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                cargarPreguntasYRespuestas();
            }
        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // Manejo de errores de puntero nulo, por ejemplo, si cliente o respuesta son null
            System.err.println("Referencia nula detectada: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: datos incompletos o nulos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Manejo general de excepciones para cualquier otro error no específico
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que carga las preguntas y respuestas correctas a la interfaz
     * gráfica, mediante una tabla.
     */
    public void cargarPreguntasYRespuestas() {
        String[] preguntasYRespuestas = this.getCliente().obtenerMensaje().split(";;;");
        String[] columnas = {"Enunciado", "Respuesta"};

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < preguntasYRespuestas.length; i++) {          
            String[] separarPreguntaYRespuesta = preguntasYRespuestas[i].split(",,,");
            String[] separarRespuestas = separarPreguntaYRespuesta[1].split("\\*");
            if (separarRespuestas[1].equals("null")) {
                separarRespuestas[1] = "";
                separarPreguntaYRespuesta[1] = separarRespuestas[0];
            }
            Object[] fila = {separarPreguntaYRespuesta[0]/*pregunta*/, separarPreguntaYRespuesta[1]/*respuesta*/};
            modelo.addRow(fila);
        }
        jTable1.setModel(modelo);
        labelTitulo.setText("Respuestas correctas: " + this.getTitulo());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(468, 440));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Enunciado", "Respuesta"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        labelTitulo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelTitulo.setText("Respuestas correctas:");

        btnAtras.setBackground(new java.awt.Color(51, 51, 51));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtras)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                        .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al botón "Atrás", que permite cerrar la
     * ventana actual.
     *
     * @param evt
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
