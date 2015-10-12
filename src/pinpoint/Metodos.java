/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinpoint;

//import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Metodos {

    public static File guardar(ArrayList<Punto> coord, File fichero) {

        try {
// Creamos el objeto que encapsula el fichero

//            String[] listadeArchivos = fichero.list();
            String res = "";
            res = JOptionPane.showInputDialog("Indique nombre del archivo y su extension");
            fichero = new File(res);
            fichero.createNewFile();
            if (fichero.exists()) {
                FileOutputStream fileout = new FileOutputStream(fichero); //salida
                //conecta el flujo de bytes al flujo de datos
                ObjectOutputStream ObjSt = new ObjectOutputStream(fileout);
                ObjSt.writeObject(coord);
                ObjSt.close();
                JOptionPane.showMessageDialog(null, "El fichero se ha creado correctamente");
//                System.out.println("El fichero se ha creado correctamente");
            } else {
//                System.out.println("No ha podido ser creado el fichero");
                JOptionPane.showMessageDialog(null, "El fichero no se ha creado correctamente");
            }

        } catch (Exception ioe) {
            ioe.getMessage();
            ioe.printStackTrace();
        }
        return fichero;
    }

    public static File borrarFichero(File fichero) {

        String res = "";
        res = JOptionPane.showInputDialog("Indique nombre del archivo y su extension a borrar");
        fichero = new File(res);

        try {
//            File fichero = new File("misCoordenadas.txt");
            if (res.matches(fichero.getName())) {
                fichero.delete();
                JOptionPane.showMessageDialog(null, "Fichero borrado ");
            } else {
                JOptionPane.showMessageDialog(null, "Fichero no borrado  " + fichero.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fichero;

    }

    public static Stage elegirArchivo(Stage pstage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("Epub Files", "*.epub"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(pstage);

        if (selectedFile != null) {
            pstage.setTitle("notepad");

            pstage.show();
           
//    pstage.showAndWait();
//    selectedFile.getParent();
        }

        return pstage;
    }

    public  static Double ordenarArray( Double[][] array){
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                //le pasamos al printf primer parametro con el espacio a ocupar por el valor %4, 
                //la cantidad de decimales .3 a mostrar y el tipo de datos float f 
                System.out.printf("%4.3f   ", array[i][j]);
//                System.out.println("\n");
            }
           
        }
        
   
    return 0.0;
    }

//    @Override
//    public String toString() {
//         
//        
//        return null ;
//    }
    
    
    
}
