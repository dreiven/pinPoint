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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        Runtime aplicacion = Runtime.getRuntime(); 
        try {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new ExtensionFilter("Epub Files", "*.epub"),
                new ExtensionFilter("All Files", "*.*"));
        
            File selectedFile = fileChooser.showOpenDialog(pstage);
            if (selectedFile.canExecute()){;
            aplicacion.load(selectedFile.toString());
            aplicacion.exec("C:\\Windows\\system32\\notepad.exe",null,selectedFile);
            }
        } catch (Exception e) {
        }
        

   

        return pstage;
    }

    public static Double[][] ordenarArray(Double[][] val) {

        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < val[0].length; j++) {
                //le pasamos al printf primer parametro con el espacio a ocupar por el valor %4, 
                //la cantidad de decimales .3 a mostrar y el tipo de datos float f 
                System.out.printf("%4.3f   ", val[i][j]);
//                System.out.println("\n");
            }

        }

        return val;
    }
    //metodo que devuelve un Boolean para la comprobacion de los datos introducidos en los  textfield (Que haya valores y que sean numeros) 
    public static Boolean validarTexto(TextField campo, TextField campo2, TextField campo3, TextField campo4) {
        //se crea String con el patron que deseamos
        String pattern = "[0-9]";
        // Crea el objeto Pattern, que indicara el patron a condicionar y se compila en la variable r
        Pattern r = Pattern.compile(pattern);
        // Crea el objeto matcher m,m1,m2,m3 y se le asigna el valor del resultado de la comparacion entre el patron y el texto del textfield
        Matcher m = r.matcher(campo.getText());
        Matcher m1 = r.matcher(campo2.getText());
        Matcher m2 = r.matcher(campo3.getText());
        Matcher m3 = r.matcher(campo4.getText());
       //si el textfield pasado por parametros tienen todos algun valor diferente  nos da true  
        if (!campo.getText().isEmpty() && !campo2.getText().isEmpty() && !campo3.getText().isEmpty() && !campo4.getText().isEmpty()) {
            //si encuentra el patron indicado (numeros de 0-9) devuelve true
            if (m.find() && m1.find() && m2.find() && m3.find()) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }
   //metodo que devuelve un Boolean para comprobar que los valores no excedan el limite del canvas 
    public static Boolean validarCoordenadas(Double corX, Double corY, Double corX2, Double corY2, Canvas canvas) {
        //se pasan por parametro los resultados de los textField convertidos a Double ,
        //si exceden el alto o el ancho del canvas nos da false
        if (corX < canvas.getWidth() && corY < canvas.getHeight() && corX2 < canvas.getWidth() && corY2 < canvas.getHeight()) {

            return true;

        }

        return false;
    }

}
