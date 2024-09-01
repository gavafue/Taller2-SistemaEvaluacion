package gui;

import conexion.Cliente;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class GestionEvaluaciones extends javax.swing.JFrame {

    private Cliente cliente;    
    private String rol;
    

    //Se pasa por constructor el objeto cliente y conexioncliente instanciados en el login
    public GestionEvaluaciones(Cliente cli, String rol) throws FileNotFoundException, IOException {
        this.rol = rol;
        this.cliente = cli;
        initComponents();
        setLocationRelativeTo(null); //Centrar JFrame
        determinarRol();//Se muestran determinados botones dependiendo del rol
        cargarTabla();
        System.out.println(cliente.getId());
        lblUsuario.setText("Usuario: "+cliente.getId());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEvaluaciones = new javax.swing.JTable();
        btnHistorico = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnRealizar = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        btnActualizarPassword = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tableEvaluaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableEvaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEvaluacionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableEvaluaciones);

        btnHistorico.setText("Historico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        lblTitulo.setText("Evaluaciones");

        btnRealizar.setBackground(new java.awt.Color(0, 0, 153));
        btnRealizar.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizar.setText("Realizar");
        btnRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarActionPerformed(evt);
            }
        });

        lblUsuario.setText("Usuario: ");

        btnActualizarPassword.setText("Actualizar Contraseña");
        btnActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarPassword))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRealizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(btnRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(btnActualizarPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            AltaEvaluacion generador = new AltaEvaluacion(cliente);
            generador.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            try {
                String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
                VerHistoriales historico = new VerHistoriales(titulo, cliente, rol);
                historico.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluacion");
        }
    }//GEN-LAST:event_btnHistoricoActionPerformed

    private void tableEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEvaluacionesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableEvaluacionesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
            try {
                cliente.intercambiarMensajes(titulo + ",;,Evaluaciones,;,Eliminar");
                if (cliente.obtenerCodigo().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Evaluacion eliminada");
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluacion");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow == -1) {
            // No hay ninguna evaluación seleccionada
            JOptionPane.showMessageDialog(null, "Seleccione la evaluación a realizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
                String aEnviar = cliente.formatearMensaje(titulo + ";;;0", "Evaluaciones", "ObtenerPregunta");//Solicita la Pregunta 0 de una evaluacion
                //El servidor deberia responder con:
                //TipoPregunta;;;Enunciado;;;Opc1(opcional);;Opc2(opcional);;;Opc3(opcional);;;Opc4(opcional);;;puntaje,;,200
                cliente.intercambiarMensajes(aEnviar);
                System.out.println(cliente.getRespuesta() + "\n");//Temporal para ver la respuesta del servidor por consola¿

                String[] pregunta = cliente.obtenerMensaje().split(";;;"); // el primercampo lo tokenizo por ;;;               

                if (cliente.obtenerCodigo().equals("200")) {
                    //compruebo si la respuesta del servidor fue exitosa y si la pregunta tiene la cantidad de tokens correcta
                    //MulitpleOpcion;;;Enunciado;;;Opc1;;Opc2;;;Opc3;;;Opc4;;;puntaje
                    //VerdaderoFalso;;;Enunciado;;;puntaje
                    //Completar;;;Enunciado;;;puntaje
                    AltaPregunta framePregunta = new AltaPregunta(null, cliente);
                    framePregunta.setRespuestas(cliente.getId()+";;;"+titulo);//Ya cargo en el string respuestas el idUsuario y la evaluacion.
                    framePregunta.setEvaluacion(titulo);
                    cliente.cargarEnGui(pregunta,framePregunta);//se carga la pregunta en la ventana correspondiente                  
                } else {
                    JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRealizarActionPerformed

    private void btnActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPasswordActionPerformed
        String contrasenia = JOptionPane.showInputDialog(this, "Ingrese nueva password para su usuario: ",
                "CAMBIE SU PASSWORD", JOptionPane.QUESTION_MESSAGE);
        if (contrasenia != null && !contrasenia.isBlank()) {
            try {
                cliente.intercambiarMensajes(cliente.getId()+";;;"+contrasenia+",;,Usuarios,;,CambioPassword");
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
          if(cliente.obtenerCodigo().equals("200")){
              JOptionPane.showMessageDialog(this, "Modificada con éxito");
          }else{
              JOptionPane.showMessageDialog(this, cliente.obtenerMensaje(), "Error" + cliente.obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
          }
        } else {
            JOptionPane.showMessageDialog(this, "NO SE MODIFICO. La password no puede ser vacia.");
        }
    }//GEN-LAST:event_btnActualizarPasswordActionPerformed

    //Metodo que carga la tabla con todas las evaluaciones existentes
    public void cargarTabla() throws IOException {
        cliente.intercambiarMensajes("titulos,;,Evaluaciones,;,Listar");//Instruccion para solicitar los titulos de las evaluaciones
        String[] titulos = cliente.obtenerMensaje().split(";;;"); // el primercampo lo tokenizo por ;;;        
        if (cliente.obtenerCodigo().equals("200")) {//La respuesta es exitosa
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Títulos");
            for (String titulo : titulos) {
                modelo.addRow(new Object[]{titulo});
            }
            tableEvaluaciones.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(this, "El servidor no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Determina con que rol se visualiza la interfaz de gestion de evaluaciones
    private void determinarRol() {
        if (rol.equals("docente")) {
            btnAgregar.setVisible(true);
            btnRealizar.setVisible(false);
            btnEliminar.setVisible(true);
        } else {
            
            btnAgregar.setVisible(false);//Sino es docente entonces es alumno
            btnRealizar.setVisible(true);
            btnEliminar.setVisible(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarPassword;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnRealizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tableEvaluaciones;
    // End of variables declaration//GEN-END:variables
}
