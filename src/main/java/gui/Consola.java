package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import logica.*;

public class Consola extends javax.swing.JFrame {

    // Atributos
    private Comandos hashComandos;
    private Ficheros listaFicheros;
    private Procesos listaProcesos;
    private Usuario usuarioActual;

    // Constructor
    public Consola(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(null);// centrar
        hashComandos = new Comandos();
        listaFicheros = new Ficheros();
        listaFicheros.cargarPrimerNivel();
        listaProcesos = new Procesos();
        usuarioActual = usuario;
    }

    // Getters
    public Comandos getHashComandos() {
        return hashComandos;
    }

    public Ficheros getListaFicheros() {
        return listaFicheros;
    }

    public Procesos getListaProcesos() {
        return listaProcesos;
    }

    // Setters
    public void setListaFicheros(Ficheros ficheros) {
        this.listaFicheros = ficheros;
    }

    public void setHashComandos(Comandos comandos) {
        this.hashComandos = comandos;
    }

    public void setListaProcesos(Procesos listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    // Desactiva placeholder de ayuda
    private void helpTextOff(JTextField text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        text.setFont(fuente);
        text.setForeground(Color.white);
    }

    // Pone el texto a formato cursiva
    public void textoCursiva(JTextArea text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.ITALIC);
        text.setFont(fuente);
    }

    // Pone el texto a formato normal
    public void textoNormal(JTextArea text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        text.setFont(fuente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPrompt = new javax.swing.JLabel();
        txtComando = new javax.swing.JTextField();
        scrlOutput = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        lblPenguin = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }

            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        lblPrompt.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        lblPrompt.setForeground(new java.awt.Color(204, 204, 204));
        lblPrompt.setText("[Consola@inet]:~$");

        txtComando.setBackground(new java.awt.Color(0, 0, 0));
        txtComando.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        txtComando.setForeground(new java.awt.Color(204, 204, 204));
        txtComando.setBorder(null);
        txtComando.setCaretColor(new java.awt.Color(0, 255, 51));
        txtComando.setOpaque(true);
        txtComando.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComandoActionPerformed(evt);
            }
        });

        scrlOutput.setBackground(new java.awt.Color(51, 51, 51));
        scrlOutput.setBorder(null);
        scrlOutput.setForeground(new java.awt.Color(51, 51, 51));
        scrlOutput.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txtOutput.setEditable(false);
        txtOutput.setBackground(new java.awt.Color(0, 0, 0));
        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Lucida Console", 0, 14)); // NOI18N
        txtOutput.setForeground(new java.awt.Color(204, 204, 204));
        txtOutput.setLineWrap(true);
        txtOutput.setRows(5);
        txtOutput.setBorder(null);
        txtOutput.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtOutput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOutputMouseClicked(evt);
            }
        });
        scrlOutput.setViewportView(txtOutput);

        lblPenguin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pingusmall.png"))); // NOI18N
        lblPenguin.setBorder(new javax.swing.border.MatteBorder(null));
        lblPenguin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPenguinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPenguin, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblPrompt)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtComando, javax.swing.GroupLayout.PREFERRED_SIZE, 609,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrlOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 757,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrlOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 418,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtComando, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPenguin)
                                .addGap(0, 0, Short.MAX_VALUE)));

        jMenu1.setText("Usuario");

        jMenuItem1.setText("Cambiar password");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOutputMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_txtOutputMouseClicked
        // TODO add your handling code here:
        txtComando.requestFocus();
    }// GEN-LAST:event_txtOutputMouseClicked
    // Al escribir el comando y presionar ENTER

    private void txtComandoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtComandoActionPerformed
        // Valores por defecto
        textoNormal(txtOutput);
        helpTextOff(txtComando);
        String comando = txtComando.getText(); // Obtiene el texto ingresado
        txtComando.setText(""); // Elimina el texto ingresado
        if (!lblPenguin.isEnabled()) { // Imagen del pinguino
            lblPenguin.setEnabled(true);
        }

        Validar validador = new Validar(comando);
        Boolean comandoEsCorrecto = validador.validarComando(hashComandos);
        String[] tokens = validador.getTokens();
        Ejecutar ejecutar = new Ejecutar(tokens);
        if (comando.equals("exit")) { // Comando salir
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        } else {
            if (comandoEsCorrecto) {
                String resultado = ejecutar.ejecutarComando(hashComandos, listaFicheros, listaProcesos, txtOutput);
                txtOutput.append("[Consola@inet]:~$" + "[" + ejecutar.getHora() + "] " + comando + "\n" + resultado + "\n"); // CAMBIO ESTO PARA QUE en el prompt muestre el comando ingresado.
            } else {
                lblPenguin.setEnabled(false);
                txtOutput.append("[Consola@inet]:~$" + "[" + ejecutar.getHora() + "] " + "Comando ingresado " + comando
                        + " incorrecto\n");
            }
        }
    }// GEN-LAST:event_txtComandoActionPerformed

    private void lblPenguinMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPenguinMouseClicked
        txtComando.requestFocus();
    }// GEN-LAST:event_lblPenguinMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }// GEN-LAST:event_formWindowGainedFocus

     /**
     * Metodo que responde al seleccionar el item "cambiar password" del menu
     * Usuario.
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
        Usuarios hashUsuarios = new Usuarios();
        String nuevaPass = JOptionPane.showInputDialog(this, "Ingrese nueva password para su usuario: ",
                "CAMBIE SU PASSWORD", JOptionPane.QUESTION_MESSAGE);
        if (nuevaPass != null && !nuevaPass.isBlank()) {
            hashUsuarios.actualizarContrasenia(usuarioActual.getNombreUsuario(), nuevaPass);
            JOptionPane.showMessageDialog(this, "Su password fue cambiada con éxito en la persistencia.");
        } else {
            JOptionPane.showMessageDialog(this, "NO SE MODIFICO. La password no puede ser vacia.");
        }
    }// GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPenguin;
    private javax.swing.JLabel lblPrompt;
    private javax.swing.JScrollPane scrlOutput;
    private javax.swing.JTextField txtComando;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables

}
