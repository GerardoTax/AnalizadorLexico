/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author dell
 */
public class CargaArchivo {
    public ArrayList<String > leerFichero(File archivo,JTextArea text) throws FileNotFoundException, IOException {
        ArrayList<String > lineas = new ArrayList<>();
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            //System.out.println(linea);
            
            text.append("\n"+linea);
        }
        fr.close(); 
        return lineas;
    }
     public void Exportar(JTextArea tex) {
        try {
        JFileChooser archivo = new JFileChooser(System.getProperty("user.dir"));
       // archivo.showSaveDialog(this);
        if (archivo.getSelectedFile() != null) {
        try (FileWriter guardado = new FileWriter(archivo.getSelectedFile())) {
        guardado.write(tex.getText());
        JOptionPane.showMessageDialog(null, "El archivo fue guardado con éxito en la ruta establecida");
        }
        }
    } catch (IOException ex) {
    JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }
}
