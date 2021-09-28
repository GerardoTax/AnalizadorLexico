/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author dell
 */
public class VentaPrincipal extends javax.swing.JFrame {
    
 ArrayList <String> listErrores = new ArrayList <String>();
 ArrayList <String> listLexema = new ArrayList <String>();
 ArrayList <String> listToken = new ArrayList <String>();
  private CargaArchivo cargarArchivo; 
 
  int inidice=0;
  int estado=0;
  String lexema="";
  
  private void analizar(){
   String todoTexto= this.jTextArea1.getText()+" ";
   String textolimpio="";
   
   for(int i=0;i<todoTexto.length(); i++){
   
       char letra=todoTexto.charAt(i);
       switch (letra) {
           case '\r' :
           case '\t' :
          // case '\n' :
           case '\b' :
           case '\f' :
               
               break;
           default:
                textolimpio=textolimpio+letra;
       }
       
   }
   
   for(int indice=0; indice<textolimpio.length();indice++){
       char letra=textolimpio.charAt(indice);
       char Error;
       switch (estado) {
           case 0:
               if(Character.isLetter(letra)){
                estado=1;
                lexema=""+letra;
               }
               else if(Character.isDigit(letra)){
                   estado=2;
                   lexema=""+letra;
               }
               else if(letra==' ' | letra=='\n'){
                   estado=0;
               }
               else if (letra=='.' | letra==',' | letra=='.' | letra==';' | letra==':' ){
                   estado=4;
                   lexema=""+letra;
               }
               else if(letra=='+' | letra=='-' |letra=='*' | letra=='/' |letra=='%' ){
                   estado=5;
                   lexema=""+letra;
               }
               else if (letra=='(' | letra==')' | letra=='[' |  letra==']' | letra=='{' | letra=='}' ){
                   estado=6;
                   lexema=""+letra;
               }
               else {
                 // buscarpalabra(this.jTextArea1, String.valueOf(letra));
               System.out.println("Error Lexico   "+letra);
               listErrores.add(""+letra);
               }
               
               break;
               
           case 1:
               if(Character.isLetter(letra) | Character.isDigit(letra) ){
                   estado=1;
                   lexema=lexema+letra;
                  
               }
               else if(letra==' ' | letra=='\n'){
                    listLexema.add("Token:  Entero    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
               }
               else {
                  // buscarpalabra(this.jTextArea1, String.valueOf(letra));
                  listLexema.add("Token: identificador    Lexema:  "+lexema);
                  listErrores.add(""+letra);
                  System.out.println("Error  ---"+letra);
                  lexema="";
                  estado=0;
                  indice--;
               }
               break;
               
           case 2:
               if(Character.isDigit(letra)){
                        estado=2;
                        lexema=lexema+letra;
                        
                  }
               else if(letra=='.'){
                    estado=3;
                    lexema=lexema+letra;
               }
               else if(letra==' ' | letra=='\n'){
                    listLexema.add("Token:  Entero    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
               }
               else {
                  // buscarpalabra(this.jTextArea1, String.valueOf(letra));
                  listLexema.add(lexema);
                  System.out.println("Error Lexico   "+letra);
                  listErrores.add(""+letra);
                  lexema="";
                  estado=0;
                  indice--;
               }
                
               break;
               
           case 3:
               if(Character.isDigit(letra)){
                        estado=3;
                        lexema=lexema+letra;
                        
                  }
               else if(letra==' '| letra=='\n' ){
                    listLexema.add("Token:  Decimal    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
               }
              else {
                  buscarpalabra(this.jTextArea1, String.valueOf(letra));
                  listLexema.add(lexema);
                  System.out.println("Error Lexico   "+letra);
                  listErrores.add(""+letra);
                  lexema="";
                  estado=0;
                  indice--;
                  
               }
               
               
               break;
               
           case 4:
               if(letra==' ' | letra=='\n' ){
                    listLexema.add("Token: Puntuacion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
                  }
               else {
                   buscarpalabra(this.jTextArea1, String.valueOf(letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add(""+letra);
                    lexema="";
                    estado=0;
                    indice--;
               }
               break;
           case 5:
             if(letra==' ' | letra=='\n'){
                    listLexema.add("Token: Operecion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                  }
               else {
                   // buscarpalabra(this.jTextArea1, String.valueOf(letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add(""+letra);
                    lexema="";
                    estado=0;
                    indice--;
               }
               break;
           case 6:
                if(letra==' ' | letra=='\n'){
                    listLexema.add("Token: Agrupacion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                  }
               else {
                   // buscarpalabra(this.jTextArea1, String.valueOf(letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add(""+letra);
                    lexema="";
                    estado=0;
                    indice--;
               }
                
               break;
               
           default:
               throw new AssertionError();
       }
               
   }
   
   
   
   
   
   
  }
    public VentaPrincipal() {
        initComponents();
        this.cargarArchivo= new CargaArchivo();
         DefaultHighlighter.DefaultHighlightPainter sub = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Analizador");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Errores");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Lexemas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cargar Archivo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Exportar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(274, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      //   Analizador tmp = new Analizador();
        // tmp.analizar(jTextArea1);
         //tmp.imprimirErrores();
       
      
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        imprimirErrores();
        listErrores.clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        imprimirLexema();
        listLexema.clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         JFileChooser fileChosser = new JFileChooser();
        int seleccion = fileChosser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //aqui selecciono y guardo el FILE que va a utilizar el FileReader
            File fichero = fileChosser.getSelectedFile();
            try {
                ArrayList<String> lista = this.cargarArchivo.leerFichero(fichero,this.jTextArea1);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        exportar();
    }//GEN-LAST:event_jButton5ActionPerformed

   
    private void imprimirLexema(){
       for(int i=0; i<listLexema.size();i++){
           System.out.println(listLexema.get(i));
       
       }
   }
    
    private void imprimirErrores(){
        if(listErrores.size()==0){
        
            System.out.println("no hay errores");
        }
        else {
       for(int i=0; i<listErrores.size();i++){
           System.out.println("EL ERROR ES......"+listErrores.get(i));
             }
        }
        
        
   }
    public void exportar(){
        try {
              JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
              archivo.showSaveDialog(this);
            if (archivo.getSelectedFile() != null) {
                try (FileWriter guardado = new FileWriter(archivo.getSelectedFile())) {
                    guardado.write(this.jTextArea1.getText());
                    JOptionPane.showMessageDialog(rootPane, "El archivo fue guardado con éxito en la ruta establecida");
                 }
            }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
    }
    
    public void buscarpalabra(JTextArea area1, String texto) {
        if (texto.length() >= 1) {
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
            Highlighter h = area1.getHighlighter();
            h.removeAllHighlights();
            String text = area1.getText();
            String caracteres = texto;
            Pattern p = Pattern.compile("(?i)" + caracteres);
            Matcher m = p.matcher(text);
            while (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter);
                } catch (BadLocationException ex) {
                  //  Logger.getLogger(color.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(area1, "la palabra a buscar no puede ser vacia");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
