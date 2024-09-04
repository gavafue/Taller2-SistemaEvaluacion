package gui;

import conexion.Cliente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame destinado a mostrar los historiales de una evaluación en particular,
 * conformados por estudiante y puntaje obtenido.
 */
public class VerHistoriales extends javax.swing.JFrame {

    private Cliente cliente;
    private String titulo;
    private String rol;

    /**
     * Constructor común que permite crear una instancia de la clase, a partir
     * del título de la evaluación en cuestión, cliente actual y rol.
     *
     * @param titulo
     * @param cliente
     * @param rol
     * @throws IOException
     */
    public VerHistoriales(String titulo, Cliente cliente, String rol) throws IOException {
        this.titulo = titulo;
        this.cliente = cliente;
        this.rol = rol;
        initComponents();
        setLocationRelativeTo(null); //Centrar JFrame
        lblTitulo.setText("Evaluacion: " + titulo);
        this.soliictarHistoriales();
        this.visualizarBtnRespuestas();
    }

    /**
     * Método que permite obtener el cliente actual conectado.
     *
     * @return el cliente actual.
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
     * Método que permite obtener el rol del cliente actual.
     *
     * @return el rol del cliente actual.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Método que permite modificar el cliente actual conectado, dado otro
     * cliente.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que pemite modificar el título de la evaluación, dado otro título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método que permite modificar el rol del cliente, dado otro rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistorico = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnRespuestas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tableHistorico.setBackground(new java.awt.Color(204, 204, 204));
        tableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI Estudiante", "Puntaje Obtenido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableHistorico);

        btnAtras.setBackground(new java.awt.Color(51, 51, 51));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTitulo.setText("Evaluación: ");

        btnRespuestas.setBackground(new java.awt.Color(51, 51, 51));
        btnRespuestas.setForeground(new java.awt.Color(255, 255, 255));
        btnRespuestas.setText("Respuestas Correctas");
        btnRespuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuestasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRespuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al botón "Atrás", que permite cerrar la
     * ventana actual y abrir la ventana anterior.
     *
     * @param evt
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        try {
            GestionEvaluaciones evaluaciones = new GestionEvaluaciones(this.getCliente(), this.getRol());
            evaluaciones.setVisible(true);
            this.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerHistoriales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VerHistoriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    /**
     * Método que proveé funcionamiento al botón "Respuestas Correctas", que
     * permite abrir una ventana para visualizar las respuestas correctas de la
     * evaluación.
     *
     * @param evt
     */
    private void btnRespuestasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespuestasActionPerformed
        VerRespuestas abrirVentana = new VerRespuestas(this.getCliente(), this.getTitulo());
        abrirVentana.setVisible(true);
    }//GEN-LAST:event_btnRespuestasActionPerformed

    /**
     * Método que solicita al servidor los historiales de una evaluación en
     * particular.
     */
    public void soliictarHistoriales() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Historiales", "Ver");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                this.cargarTablaHistoriales();
            } else {
                JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(), "Error " + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que carga en la interfaz gráfica los historiales de una
     * evaluación, mediante una tabla.
     *
     * @throws IOException
     */
    public void cargarTablaHistoriales() throws IOException {
        String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
        String[] historial = null;

        String[] columnas = {"CI alumno", "Puntaje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (int i = 0; i < historiales.length; i++) {
            historial = historiales[i].split(",,,");
            if (this.getRol().equals("docente")) {
                Object[] fila = {historial[0]/*cialumno]*/, historial[1]/*puntaje*/};
                modelo.addRow(fila);
            }
            if (this.getCliente().getId().equals(historial[0]) && this.getRol().equals("estudiante")) {
                Object[] fila = {historial[0]/*cialumno]*/, historial[1]/*puntaje*/};
                modelo.addRow(fila);
            }
        }
        tableHistorico.setModel(modelo);
    }
    
    public void visualizarBtnRespuestas(){
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones", "ValorCheckboxRespuestas");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                if (this.getCliente().obtenerMensaje().equals("true")){
                    btnRespuestas.setVisible(true);
                } else {
                    btnRespuestas.setVisible(false);
                }
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRespuestas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableHistorico;
    // End of variables declaration//GEN-END:variables
}
