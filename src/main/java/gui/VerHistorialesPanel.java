package gui;

import conexion.Cliente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * JFrame que representa los historiales de una evaluación, donde se visualizan
 * todos los estudiantes que la realizaron y su puntaje desde el rol docente.
 * Minetras que desde el rol estudiante solo se visualiza su propio puntaje.
 * @author Ana, Gabriel, Gonzalo, Juan y Santiago.
 */
public class VerHistorialesPanel extends javax.swing.JPanel {

    /**
     * Cliente actual en el sistema.
     */
    private Cliente cliente;

    /**
     * Título de la evaluación de la que se verán los historiales.
     */
    private String titulo;

    /**
     * Rol del cliente actual.
     */
    private String rol;

    /**
     * Panel de contenido.
     */
    private JPanel panelContent;

    /**
     * Constructor que permite instanciar el JFrame VerHistoriales y carga los
     * atributos necesarios para ello.
     * @param titulo titulo de la evaluación.
     * @param cliente cliente actual.
     * @param panelContent panel de contenido para manejo de interfaz.
     * @param rol rol del cliente actual.
     */
    public VerHistorialesPanel(String titulo, Cliente cliente, String rol, JPanel panelContent) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.rol = rol;
        this.panelContent = panelContent;
        initComponents();
        lblTitulo.setText("Evaluación: " + titulo);
        this.solicitarHistoriales();
        this.visualizarBtnRespuestas();
        this.solicitarPuntajeTotalEvaluacion();
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
     * @param cliente actual.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que pemite modificar el título de la evaluación, dado otro título.
     * @param titulo titulo de la evaluación.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Método que permite modificar el rol del cliente, dado otro rol.
     * @param rol rol del cliente actual.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Método que solicita al servidor los historiales de una evaluación en
     * particular.
     */
    public void solicitarHistoriales() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Historiales", "Ver");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                this.cargarTablaHistoriales();
            } else {
                JOptionPane.showMessageDialog(this, this.getCliente().obtenerMensaje(),
                        "Error " + this.getCliente().obtenerCodigo(), JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AltaEvaluacionPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        String[] columnas = {"CI alumno", "Puntaje Obtenido"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no serán editables
            }
        };
        for (int i = 0; i < historiales.length; i++) {
            historial = historiales[i].split(",,,");
            if (this.getRol().equals("docente")) {
                Object[] fila = {historial[0]/* cialumno] */, historial[1]/* puntaje */};
                modelo.addRow(fila);
            }
            if (this.getCliente().getId().equals(historial[0]) && this.getRol().equals("estudiante")) {
                Object[] fila = {historial[0]/* cialumno] */, historial[1]/* puntaje */};
                modelo.addRow(fila);
            }
        }

        this.darEstiloTabla();
        tableHistorico.setModel(modelo);
    }

    /**
     * Método destinado a dar estilo a la tabla del JFrame.
     */
    public void darEstiloTabla() {
        // Estilo de la tabla
        tableHistorico.setGridColor(new Color(0, 0, 153));
        tableHistorico.setShowGrid(true);
        tableHistorico.setRowHeight(30);
        tableHistorico.setIntercellSpacing(new Dimension(0, 0)); // Espacio entre celdas verticalmente

        // Suponiendo que tableEvaluaciones ya está en un JScrollPane
        JScrollPane scrollPane = (JScrollPane) tableHistorico.getParent().getParent();
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153), 2)); // Borde azul alrededor del
        // JScrollPane
        // Configurar el encabezado
        JTableHeader header = tableHistorico.getTableHeader();
        header.setBackground(new Color(0, 0, 153));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
    }

    /**
     * Método que determina si un estudiante realizó una evaluación o no, a
     * partir de una petición al servidor.
     *
     * @return
     */
    public Boolean estudianteRealizoEvaluacion() {
        boolean estudianteRealizoEvaluacion = false;
        String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
        String ciEstudianteConectado = this.getCliente().getId();
        for (int i = 0; i < historiales.length; i++) {
            String[] historial = historiales[i].split(",,,");
            if (historial[0].equals(ciEstudianteConectado)) {
                estudianteRealizoEvaluacion = true;
            }
        }
        return estudianteRealizoEvaluacion;
    }

    /**
     * Método que determina si mostrar las respuestas correctas o no al
     * finalizar una evaluación como rol estudiante, a partir de una solicitud
     * al servidor.
     */
    public void visualizarBtnRespuestas() {
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones",
                    "ValorCheckboxRespuestas");
            this.getCliente().intercambiarMensajes(instruccion);
            if (this.getCliente().obtenerCodigo().equals("200")) {
                if (this.getCliente().obtenerMensaje().equals("true")) {
                    btnRespuestas.setVisible(true);
                } else {
                    btnRespuestas.setVisible(false);
                }
            }
        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red
            System.err.println("Error de comunicación con el servidor: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el servidor.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            // Manejo de errores de puntero nulo, por ejemplo, si cliente o respuesta son
            // null
            System.err.println("Referencia nula detectada: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: datos incompletos o nulos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Manejo general de excepciones para cualquier otro error no específico
            System.err.println("Ha ocurrido un error inesperado: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método que verifica si hay evaluaciones disposibles, a partir de una
     * petición al servdior.
     *
     * @return true si hay historiales o false en caso contrario.
     */
    public boolean hayHistorialesDisponibles() {
        boolean retorno = false;
        try {
            String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Historiales", "Ver");
            this.getCliente().intercambiarMensajes(instruccion);
            // Verifica si el código de respuesta es "200" para historiales disponibles
            if (this.getCliente().obtenerCodigo().equals("200")) {
                String[] historiales = this.getCliente().obtenerMensaje().split(";;;");
                retorno = historiales.length > 0 && !historiales[0].isEmpty();
            }
        } catch (IOException ex) {
            // Maneja el error pero no muestra alerta aquí
            Logger.getLogger(VerHistorialesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Devuelve false si ocurre algún error o no hay historiales
        return retorno;
    }

    /**
     * Solicita el puntaje total de la evaluación al cliente y actualiza el
     * campo de texto correspondiente. Muestra mensajes de error detallados en
     * caso de problemas durante la solicitud o al procesar la respuesta.
     */
    public void solicitarPuntajeTotalEvaluacion() {
        String instruccion = this.getCliente().formatearMensaje(this.getTitulo(), "Evaluaciones",
                "ObtenerPuntajeTotal");
        try {
            // Intercambia mensajes con el cliente para solicitar el puntaje total
            this.getCliente().intercambiarMensajes(instruccion);
            // Verifica el código de respuesta del cliente
            String codigoRespuesta = this.getCliente().obtenerCodigo();
            String mensajeRespuesta = this.getCliente().obtenerMensaje();
            if ("200".equals(codigoRespuesta)) {
                // Actualiza el campo de texto con el puntaje total
                txtPuntajeTotal.setText("Puntaje total: " + mensajeRespuesta);
            } else {
                // Maneja códigos de respuesta inesperados
                throw new RuntimeException(
                        "Código de respuesta inesperado: " + codigoRespuesta + " con mensaje: " + mensajeRespuesta);
            }
        } catch (IOException e) {
            // Manejo de errores de entrada/salida, como problemas de red o comunicación
            System.err.println("Error de comunicación con el cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error de comunicación con el cliente. Por favor, intente nuevamente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException e) {
            // Manejo de errores inesperados relacionados con la respuesta del cliente
            System.err.println("Error al solicitar el puntaje total de la evaluación: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al procesar la respuesta del cliente: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Captura cualquier otra excepción inesperada
            System.err.println("Error inesperado: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Por favor, intente nuevamente.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tableHistorico = new javax.swing.JTable();
        btnAtras = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnRespuestas = new javax.swing.JButton();
        txtPuntajeTotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(730, 520));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableHistorico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableHistorico.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "CI Estudiante", "Puntaje Obtenido"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableHistorico);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 650, 340));

        btnAtras.setBackground(new java.awt.Color(0, 0, 153));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setText("Atrás");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 430, 119, 46));

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitulo.setText("Evaluación:");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1401, -1));

        btnRespuestas.setBackground(new java.awt.Color(25, 118, 210));
        btnRespuestas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRespuestas.setForeground(new java.awt.Color(255, 255, 255));
        btnRespuestas.setText("Ver Respuestas");
        btnRespuestas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespuestasActionPerformed(evt);
            }
        });
        add(btnRespuestas, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 167, 46));

        txtPuntajeTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPuntajeTotal.setForeground(new java.awt.Color(0, 0, 153));
        txtPuntajeTotal.setText("Puntaje total:");
        add(txtPuntajeTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 744, -1));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que proveé funcionamiento al botón "atrás", que redirecciona a la
     * pestaña de gestión.
     *
     * @param evt
     */
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // GEN-FIRST:event_btnAtrasActionPerformed
            GestionEvaluacionesPanel evaluacionesPanel = new GestionEvaluacionesPanel(cliente, rol, panelContent);
            evaluacionesPanel.setSize(730, 520);
            evaluacionesPanel.setLocation(0, 0);
            panelContent.removeAll();
            panelContent.add(evaluacionesPanel);
            panelContent.revalidate();
            panelContent.repaint();
        } // GEN-LAST:event_btnAtrasActionPerformed
        catch (IOException ex) {
            Logger.getLogger(VerHistorialesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que proveé funcionamiento al botón "ver respuestas", que
     * redirecciona al JFrame correspondiente.
     *
     * @param evt
     */
    private void btnRespuestasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRespuestasActionPerformed
        VerRespuestasPanel evaluacionesPanel = new VerRespuestasPanel(cliente, titulo, rol, panelContent);
        evaluacionesPanel.setSize(730, 520);
        evaluacionesPanel.setLocation(0, 0);
        panelContent.removeAll();
        panelContent.add(evaluacionesPanel);
        panelContent.revalidate();
        panelContent.repaint();
    }// GEN-LAST:event_btnRespuestasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnRespuestas;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tableHistorico;
    private javax.swing.JLabel txtPuntajeTotal;
    // End of variables declaration//GEN-END:variables
}
