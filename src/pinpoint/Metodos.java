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
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Metodos {

    public static File crearFichero(File fichero) {

        try {
// Creamos el objeto que encapsula el fichero

//            String[] listadeArchivos = fichero.list();
            String res = "";
            res = JOptionPane.showInputDialog("Indique nombre del archivo y su extension");
            fichero = new File(res);
            if (!fichero.exists()) {
                fichero.createNewFile();
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
// SEXOOOOOO

    public static File borrarFichero(File fichero) {

        String res = "";
        res = JOptionPane.showInputDialog("Indique nombre del archivo y su extension a borrar");
        fichero = new File(res);

        try {
//            File fichero = new File("misCoordenadas.txt");
            if (res.matches(fichero.getName())){
            fichero.delete();
            JOptionPane.showMessageDialog(null, "Fichero borrado ");
            }
              else {
                JOptionPane.showMessageDialog(null, "Fichero no borrado  " + fichero.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fichero;

    }

    public static void PointsCount(Punto coord) {
        try {
            ArrayList arrayPuntos = new ArrayList();
            arrayPuntos.add(coord);

            Iterator<Punto> itr = arrayPuntos.iterator();

            while (itr.hasNext()) {

                System.out.println(itr.next());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //SEXOOOOOOOOOOOOOO!!!!!!! XDDDDDDDDD
    public static void escritura(Punto coord) throws IOException {
        File fichero = new File("misCoordenadas.txt");
        try {
            if (fichero.exists()) {

                FileOutputStream fileout = new FileOutputStream(fichero); //salida
                //conecta el flujo de bytes al flujo de datos
                ObjectOutputStream ObjSt = new ObjectOutputStream(fileout);
                ObjSt.writeObject(coord);
                ObjSt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
