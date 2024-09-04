package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import consola.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JFrame destinado a ser la interfaz gráfica de la consola de Linux.
 */
public class Consola extends javax.swing.JFrame {

    // Colecciones necesarias para crear la consola de Linux
    private Comandos hashComandos;
    private Ficheros listaFicheros;
    private Procesos listaProcesos;

    /**
     * Constructor vacío que permite crear una instancia de la consola.
     */
    public Consola() {
        initComponents();
        setLocationRelativeTo(null); //centrar
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // No detiene el programa al cerrar el JFrame

        hashComandos = new Comandos();
        listaFicheros = new Ficheros();
        listaProcesos = new Procesos();
        listaFicheros.cargarPrimerNivel(); // Primer nivel cargado desde memoria
    }

    /**
     * Método que permite obtener la colección de comandos del sistema.
     *
     * @return colección de comandos disponibles.
     */
    public Comandos getHashComandos() {
        return hashComandos;
    }

    /**
     * Método que permite obtener la colección de ficheros del sistema.
     *
     * @return colección de ficheros actuales.
     */
    public Ficheros getListaFicheros() {
        return listaFicheros;
    }

    /**
     * Método que permite obtener la colección de procesos actuales del sistema.
     *
     * @return colección de procesos actuales.
     */
    public Procesos getListaProcesos() {
        return listaProcesos;
    }

    /**
     * Método que permite modificar la colección de comandos, dada otra
     * colección de comandos.
     *
     * @param comandos
     */
    public void setHashComandos(Comandos comandos) {
        this.hashComandos = comandos;
    }

    /**
     * Método que permite modificar la colección de ficheros, dada otra
     * colección de ficheros.
     *
     * @param ficheros
     */
    public void setListaFicheros(Ficheros ficheros) {
        this.listaFicheros = ficheros;
    }

    /**
     * Método que permite modificar la colección de procesos, dada otra
     * colección de procesos.
     *
     * @param procesos
     */
    public void setListaProcesos(Procesos listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    /**
     * Método que desactiva el placeholder de ayuda.
     */
    private void helpTextOff(JTextField text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        text.setFont(fuente);
        text.setForeground(Color.white);
    }

    /**
     * Método que pone el texto a formato cursiva.
     */
    public void textoCursiva(JTextArea text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.ITALIC);
        text.setFont(fuente);
    }

    /**
     * Método que pone el texto a formato normal.
     */
    public void textoNormal(JTextArea text) {
        Font fuente = text.getFont();
        fuente = fuente.deriveFont(Font.PLAIN);
        text.setFont(fuente);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPrompt = new javax.swing.JLabel();
        txtComando = new javax.swing.JTextField();
        scrlOutput = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        lblPenguin = new javax.swing.JLabel();

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

        lblPrompt.setFont(new java.awt.Font("Lucida Console", 0, 18)); // NOI18N
        lblPrompt.setForeground(new java.awt.Color(51, 255, 51));
        lblPrompt.setText("[Consola@inet]:~$");

        txtComando.setBackground(new java.awt.Color(0, 0, 0));
        txtComando.setFont(new java.awt.Font("Lucida Console", 0, 16)); // NOI18N
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
        txtOutput.setFont(new java.awt.Font("Lucida Console", 0, 16)); // NOI18N
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
                .addComponent(lblPenguin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPrompt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComando, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrlOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(scrlOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrompt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComando))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblPenguin)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOutputMouseClicked(java.awt.event.MouseEvent evt) {
        txtComando.requestFocus();
    }

    /**
     * Método encargador de ejecutar el comando ingresado en la consola, que
     * deriva a las clases validar y ejecutar respectivamente.
     *
     * @param evt
     */
    private void txtComandoActionPerformed(java.awt.event.ActionEvent evt) {
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
        if (comando.equals("exit")) {
            try {
                // Comando salir
                Login login = new Login();
                login.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(Consola.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    }

    private void lblPenguinMouseClicked(java.awt.event.MouseEvent evt) {
        txtComando.requestFocus();
    }

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {
        this.requestFocusInWindow();
        txtComando.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPenguin;
    private javax.swing.JLabel lblPrompt;
    private javax.swing.JScrollPane scrlOutput;
    private javax.swing.JTextField txtComando;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables

}
