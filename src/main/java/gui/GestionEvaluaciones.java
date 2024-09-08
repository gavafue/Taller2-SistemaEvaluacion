package gui;

import conexion.Cliente;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

/**
 * JFrame destinado para la gestión de evaluaciones, esto incluye alta y baja de
 * evaluaciones, además de consultas sobre evaluaciones al servidor.
 */
public class GestionEvaluaciones extends javax.swing.JFrame {

    private Cliente cliente;
    private String rol;

    /**
     * Constructor común que crea una instancia de la clase a partir del cliente
     * y su rol, en función de esto los elementos que se muestran en la interfaz
     * gráfica.
     *
     * @param cliente
     * @param rol
     * @throws FileNotFoundException
     * @throws IOException
     */
    public GestionEvaluaciones(Cliente cliente, String rol) throws FileNotFoundException, IOException {
        this.rol = rol;
        this.cliente = cliente;
        initComponents();
        setLocationRelativeTo(null); // Centrar JFrame
        this.determinarInterfaz(); // Muestra determinados elementos gráficos dependiendo del rol
        this.solicitarTitulosEvaluaciones();
        lblUsuario.setText("Usuario: " + cliente.getId());
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
     * Método que permite modificar el rol del cliente, dado otro rol.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
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
        btnRealizarAlAzar = new javax.swing.JButton();

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
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        btnAgregar.setBackground(new java.awt.Color(0, 0, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(51, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tableEvaluaciones.setBackground(new java.awt.Color(204, 204, 204));
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

        btnHistorico.setBackground(new java.awt.Color(0, 51, 51));
        btnHistorico.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorico.setText("Historico");
        btnHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
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

        btnActualizarPassword.setBackground(new java.awt.Color(102, 102, 102));
        btnActualizarPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarPassword.setText("Actualizar Contraseña");
        btnActualizarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPasswordActionPerformed(evt);
            }
        });

        btnRealizarAlAzar.setBackground(new java.awt.Color(0, 0, 153));
        btnRealizarAlAzar.setForeground(new java.awt.Color(255, 255, 255));
        btnRealizarAlAzar.setText("Al Azar");
        btnRealizarAlAzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarAlAzarActionPerformed(evt);
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
                            .addComponent(btnRealizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRealizarAlAzar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnRealizarAlAzar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    /**
     * Método que proveé funcionamiento al botón "Al Azar" que le permite al estudiante realizar una evaluación aleatoria.
     * @param evt 
     */
    private void btnRealizarAlAzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarAlAzarActionPerformed
        try {
            this.solicitarEvaluacionAlAzar();
        } catch (IOException ex) {
            Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRealizarAlAzarActionPerformed

     /**
     * Método destinado a proveer funcionalidad al botón "Agregar" que le
     * permite al rol docente crear una evaluación.
     *
     * @param evt
     */
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarActionPerformed
        try {
            AltaEvaluacion generador = new AltaEvaluacion(cliente);
            generador.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }// GEN-LAST:event_btnAgregarActionPerformed

    /**
     * Método destinado a proveer funcionalidad al botón "Historial" que le
     * permite al rol docente acceder a una nueva ventana para ver los
     * historiales de las evaluaciones y al rol estudiante ver el puntaje
     * obtenido.
     *
     * @param evt
     */
    private void btnHistoricoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnHistoricoActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            try {
                String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
                VerHistoriales historico = new VerHistoriales(titulo, this.getCliente(), this.getRol());
                if (this.getRol().equals("docente")) {
                    if (historico.hayHistorialesDisponibles()) {
                        historico.setVisible(true);
                        this.dispose();
                    }
                } else if (this.getRol().equals("estudiante")) {
                    if (historico.hayHistorialesDisponibles() && historico.estudianteRealizoEvaluacion()) {
                        historico.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No has realizado esta evaluación aún");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al procesar la solicitud: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluación.");
        }
    }// GEN-LAST:event_btnHistoricoActionPerformed

    /**
     * Método destinado a proveer funcionalidad al botón "Eliminar" que le
     * permite al rol docente eliminar una evaluación ya existente.
     *
     * @param evt
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow != -1) {
            String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);

            // Ventana de confirmación
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro? En caso de eliminar la evaluación, también se eliminarán todos los historiales asociados a esta.",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) { // Si el usuario confirma
                try {
                    this.getCliente().intercambiarMensajes(titulo + ",;,Evaluaciones,;,Eliminar");
                    if (this.getCliente().obtenerCodigo().equals("200")) {
                        JOptionPane.showMessageDialog(null, "Evaluación eliminada");
                        this.solicitarTitulosEvaluaciones();
                        cargarTablaEvaluaciones();
                    } else {
                        JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                                "Error " + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // Si el usuario elige "No", no se hace nada
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una evaluación");
        }
    }// GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Método destinado a proveer funcionalidad al botón "Realizar" que le
     * permite al rol estudiante realizar una evaluación seleccionada.
     *
     * @param evt
     */
    private void btnRealizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRealizarActionPerformed
        int selectedRow = tableEvaluaciones.getSelectedRow();
        if (selectedRow == -1) { // No hay ninguna evaluación seleccionada
            JOptionPane.showMessageDialog(null, "Seleccione la evaluación a realizar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String titulo = (String) tableEvaluaciones.getValueAt(selectedRow, 0);
                this.solicitarEvaluacion(titulo);
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }// GEN-LAST:event_btnRealizarActionPerformed

    /**
     * Método que permite solicitar una evaluación al Azar.
     * @throws java.io.IOException
     */
    public void solicitarEvaluacionAlAzar() throws IOException{
        String aEnviar = this.getCliente().formatearMensaje("Solicitud", "Evaluaciones", "ObtenerTituloAlAzar");
        // Solicita un título aleatorio
        this.getCliente().intercambiarMensajes(aEnviar);
        if (this.getCliente().obtenerCodigo().equals("200")) {
            String titulo = this.getCliente().obtenerMensaje();
            this.solicitarEvaluacion(titulo);
        } else {
            JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(), this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método que permite solicitar una evaluación.
     * @param titulo de la evaluación seleccionada.
     * @throws IOException 
     */
    public void solicitarEvaluacion(String titulo) throws IOException {
        String aEnviar = this.getCliente().formatearMensaje(titulo + ";;;0", "Evaluaciones", "ObtenerPregunta");
        // Solicita la pregunta 0
        // El servidor deberia responder con:
        // TipoPregunta;;;Enunciado;;;Opc1(opcional);;Opc2(opcional);;;Opc3(opcional);;;Opc4(opcional);;;puntaje,;,200
        this.getCliente().intercambiarMensajes(aEnviar);
        String[] pregunta = this.getCliente().obtenerMensaje().split(";;;");
        if (this.getCliente().obtenerCodigo().equals("200")) {
            // MulitpleOpcion;;;Enunciado;;;Opc1;;Opc2;;;Opc3;;;Opc4;;;puntaje
            // VerdaderoFalso;;;Enunciado;;;puntaje
            // Completar;;;Enunciado;;;puntaje
            AltaPregunta framePregunta = new AltaPregunta(null, this.getCliente());
            framePregunta.setRespuestas(this.getCliente().getId() + ";;;" + titulo);
            framePregunta.setEvaluacion(titulo);
            framePregunta.cargarEnGui(pregunta, framePregunta);
        } else {
            JOptionPane.showMessageDialog(this, "Error en la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método destinado a proveer funcionalidad al botón "Actualizar Contraseña"
     * que le permite al usuario modificar su contraseña.
     *
     * @param evt
     */
    private void btnActualizarPasswordActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnActualizarPasswordActionPerformed
        String contrasenia = JOptionPane.showInputDialog(this, "Ingrese nueva password para su usuario: ",
                "CAMBIE SU PASSWORD", JOptionPane.QUESTION_MESSAGE);
        if (contrasenia != null && !contrasenia.isBlank()) {
            try {
                this.getCliente().intercambiarMensajes(
                        this.getCliente().getId() + ";;;" + contrasenia + ",;,Usuarios,;,CambioPassword");
            } catch (IOException ex) {
                Logger.getLogger(GestionEvaluaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.getCliente().obtenerCodigo().equals("200")) {
                JOptionPane.showMessageDialog(this, "Modificada con éxito");
            } else {
                JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                        "Error" + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "NO SE MODIFICO. La password no puede ser vacia.");
        }
    }// GEN-LAST:event_btnActualizarPasswordActionPerformed

    /**
     * Método que le solicita al servidor los títulos de las evaluaciones ya
     * existentes.
     */
    private void solicitarTitulosEvaluaciones() throws IOException {
        cliente.intercambiarMensajes("titulos,;,Evaluaciones,;,Listar");
        if (this.getCliente().obtenerCodigo().equals("200")) {
            this.cargarTablaEvaluaciones();
        } else {
            JOptionPane.showMessageDialog(this, "El servidor no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que carga en interfaz gráfica los títulos de las evaluaciones
     * existentes.
     *
     * @throws IOException
     */
    public void cargarTablaEvaluaciones() throws IOException {
        String[] titulos = this.getCliente().obtenerMensaje().split(";;;");
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no serán editables
            }
        };
        modelo.addColumn("Títulos");
        for (String titulo : titulos) {
            modelo.addRow(new Object[] { titulo });
        }
        tableEvaluaciones.setModel(modelo);
    }

    /**
     * Método que modifica la interfáz gráfica según el rol del cliente actual.
     */
    private void determinarInterfaz() { // Administrativo no puede acceder
        if (rol.equals("docente")) { // Docente
            btnAgregar.setVisible(true);
            btnRealizar.setVisible(false);
            btnRealizarAlAzar.setVisible(false);
            btnEliminar.setVisible(true);
        } else { // Estudiante
            btnAgregar.setVisible(false);
            btnRealizar.setVisible(true);
            btnRealizarAlAzar.setVisible(true);
            btnEliminar.setVisible(false);
        }
    }
    
    private void tableEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {   
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarPassword;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnRealizar;
    private javax.swing.JButton btnRealizarAlAzar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tableEvaluaciones;
    // End of variables declaration//GEN-END:variables
}
