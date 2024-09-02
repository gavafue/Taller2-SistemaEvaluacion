package gui;

import conexion.Cliente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerHistoriales extends javax.swing.JFrame {

    private String titulo; //Titulo de la evaluacion
    //Datos que se mandan por constructor para no perderlos
    private Cliente cliente;
    private String rol;

    public VerHistoriales(String titulo, Cliente cli, String rol) throws IOException {
        this.titulo = titulo;
        this.cliente = cli;
        this.rol = rol;
        initComponents();
        setLocationRelativeTo(null); //Centrar JFrame
        lblTitulo.setText("Evaluacion: " + titulo);
        this.cargarTablaEvaluaciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistorico = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        tableHistorico.setBackground(new java.awt.Color(204, 204, 204));
        tableHistorico.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
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
        btnAtras.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTitulo.setText("Evaluación: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        try {
            GestionEvaluaciones evaluaciones = new GestionEvaluaciones(cliente, rol);
            evaluaciones.setVisible(true);
            this.dispose();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerHistoriales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VerHistoriales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAtrasActionPerformed

    //Metodo que carga en la tabla el historico de una evaluacion en particular
    //Clienta manda "tituloEvaluacion,;,Historiales,;,Ver"
    //Server manda idalumno,,,puntaje;;;idalumnoN,,,puntajeN,;,200
    public void cargarTablaEvaluaciones() throws IOException {
        String instruccion = cliente.formatearMensaje(titulo, "Historiales", "Ver");
        try {
            cliente.intercambiarMensajes(instruccion);
            System.out.println(cliente.getRespuesta() + "\n"); //Temporal para ver la respuesta del servidor por consola 
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] historiales = cliente.obtenerMensaje().split(";;;");
        String[] historial = null;

        String[] columnas = {"CI alumno", "Puntaje"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no serán editables
            }
        };
        if (cliente.obtenerCodigo().equals("200")) {
            for (int i = 0; i < historiales.length; i++) {
                historial = historiales[i].split(",,,");
                Object[] fila = {historial[0]/*cialumno]*/, historial[1]/*puntaje*/};
                modelo.addRow(fila);
            }
            tableHistorico.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error " + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableHistorico;
    // End of variables declaration//GEN-END:variables
}
