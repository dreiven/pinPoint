/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinpoint;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbl_X;
    @FXML
    private Label lbl_Y;
    @FXML
    private Pane Zona;
    @FXML
    private TextField txt_X;
    @FXML
    private TextField txt_Y;
    @FXML
    private TextField txt_Yy;
    @FXML
    private TextField txt_Xx;
    @FXML
    private Punto coorX;
    @FXML
    private AnchorPane anchorP;
    @FXML
    private Scene scene1;
    @FXML
    private Stage pantalla;
    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;
    @FXML
    private Image pnt;
    @FXML
    private Button btn_slr;
    @FXML
    private Label lbl_tg;
    @FXML
    private ToggleButton tb_cambio;
    @FXML
    private Punto punto1 = new Punto();
    @FXML
    private Punto punto2 = new Punto();
    @FXML
    private Stage stage1 = new Stage();
    @FXML
    private ArrayList arrayPuntos = new ArrayList();
    @FXML
    private Button btn_cr;
    @FXML
    private File fichero;
    @FXML
    private int cont = 0;
    @FXML
    private TextArea tx_area;

    //metodo de action event para guardar el fichero con datos asociado con el menubar

    public void botonAccionGuardar(ActionEvent event) throws IOException {

        Metodos.guardar(arrayPuntos, fichero);

    }
//metodo de action event para abrir un fichero nuevo 

    public void botonAccionAbrir(ActionEvent event) throws IOException {

        Metodos.elegirArchivo(stage1);

    }
//metodo de action event para borrar un fichero indicado especificamente asociado con el menubar y el boton delete

    public void botonAccionBorrar(ActionEvent event) {

        Metodos.borrarFichero(fichero);
    }

    @FXML
    //metodo para almacenar los objetos punto en un array de puntos
    public void PointsCount(Punto coord) {
        String xy;
        try {
            //añadimos al array de puntos el parametro punto enviado en el metodo
            arrayPuntos.add(coord);
            //se declara objeto iterator para recorrer el array de puntos  
            Iterator<Punto> itr = arrayPuntos.iterator();
            //mientras el objeto iterator itr tenga datos (true) sigue avanzando
            while (itr.hasNext()) {
                xy = itr.next().toString();
                //muestra los objeto de arraypuntos a traves del objeto iterator y el metodo next()
//                System.out.println(itr.next().toString());
                tx_area.setWrapText(true);
                tx_area.setText(xy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    //se declara metodo para cambiar el toggle button de on a off
    private void cambio() {
        //si el toggle button esta Selected 
        if (tb_cambio.isSelected()) {
            //seteo de la propiedad text en el toggle button a ON
            tb_cambio.setText("ON");
            //seteo de la propiedad text de la label 
            lbl_tg.setText("Modo Discontinuo");
            //seteo de la propiedad color del text dl objeto  label
            lbl_tg.setTextFill(RED);
        } else {
            //seteo de la propiedad text en el toggle button a OFF
            tb_cambio.setText("OFF");
            //seteo de la propiedad text de la label 
            lbl_tg.setText("Modo Continuo");
            //seteo de la propiedad color del text dl objeto  label
            lbl_tg.setTextFill(GREEN);
        }

    }

    @FXML
    //se declara metodo reset en el action event del boton reset
    private void Reset(ActionEvent event) {
        Double resValor = 0.0;
        String resValorString;
        punto1.setX(resValor);
        punto1.setY(resValor);
        punto2.setX(resValor);
        punto2.setY(resValor);
        resValorString = Double.toString(resValor);
        //seteamos propiedad texto del objeto txt_X a 0.0
        txt_X.setText(resValorString);
        //seteamos propiedad texto del objeto txt_Y a 0.0
        txt_Y.setText(resValorString);
        //seteamos propiedad texto del objeto txt_Xx a 0.0
        txt_Xx.setText(resValorString);
        //seteamos propiedad texto del objeto txt_Yy a 0.0
        txt_Yy.setText(resValorString);
        //se crea e inicializa un objeto GraphicsContent para dibujar encima del objeto canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //se da a la propiedad color del objeto gc el valor gris
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.GRAY);
        //se crea un rectangulo gris del tamaño del canvas
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }

    @FXML
    //se declara metodo para activarse cuando el usuario realice click encima del objeto canvas
    public void Mcliked() {
        //se crea e inicializa un objeto GraphicsContent para dibujar encima del objeto canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //se agrega al objeto canvas un nuevo handler para el evento del raton setOnMouseClicked
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            //se override el metodo handle para darle instrucciones en caso de se produzca el evento del mouse anterior
            @Override
            public void handle(MouseEvent e) {

                punto2.setX(e.getX());
                punto2.setY(e.getY());
                //se castea a string el valor de x1 que esta en double
                String corX = Double.toString(punto2.getX());
                //se castea a string el valor de y1 que esta en double
                String corY = Double.toString(punto2.getY());
//                System.out.println(corX + "" + corY);
                //se da a la propiedad color del objeto gc el valor gris
                if (tb_cambio.isSelected()) {
                    gc.setFill(Color.GRAY);
                    gc.setStroke(Color.RED);
                    gc.setLineWidth(5);
                    //se crea un rectangulo gris del tamaño del canvas
                    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                }

//         
//           gc.drawImage(punto, x1, y1);
//           iv1.setImage(punto);
//           pictureRegion.getChildren().add(iv1);
            }
        });

    }

    @FXML
    //se declara metodo para activarse cuando el usuario pulse y suelte el boton del raton  encima del objeto canvas
    public void Mpressed() {
        //se crea e inicializa un objeto GraphicsContent para dibujar encima del objeto canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //se agrega al objeto canvas un nuevo handler para el evento del raton setOnMousePressed
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            //se override el metodo handle para darle instrucciones en caso de se produzca el evento del mouse anterior  
            @Override
            public void handle(MouseEvent t) {
                //se pasa el valor del metodo getX del evento de mouse a la variable x2
                punto1.setX(t.getX());
                punto1.setY(t.getY());

                //si la variable x1 contiene un valor mayor que 0
                if (punto2.getX() != null) {

                    //se castea a string el valor de x1 que esta en double
                    String corX = Double.toString(punto1.getX());
                    //se castea a string el valor de y1 que esta en double
                    String corY = Double.toString(punto1.getY());
                    //se castea a string el valor de x2 que esta en double
                    String corX2 = Double.toString(punto2.getX());
                    //se castea a string el valor de y2 que esta en double
                    String corY2 = Double.toString(punto2.getY());
                    //se setea la propiedad setText del objeto txt_X con el string resultante del casteo de la variable x1
                    txt_X.setText(corX);
                    //se setea la propiedad setText del objeto txt_Y con el string resultante del casteo de la variable y1
                    txt_Y.setText(corY);
                    //se setea la propiedad setText del objeto txt_Xx con el string resultante del casteo de la variable x2
                    txt_Xx.setText(corX2);
                    //se setea la propiedad setText del objeto txt_Yy con el string resultante del casteo de la variable y2
                    txt_Yy.setText(corY2);
                    //se seta a verde el color de las lineas creadas a traves de este metodo
                    gc.setStroke(Color.FORESTGREEN);
                    //se setea a 2 el ancho de las lineas
                    gc.setLineWidth(2);
                    //se le pasa al metodo strokeline los 4 parametro necesarios para dibujar una linea todos en double
                    gc.strokeLine(punto1.getX(), punto1.getY(), punto2.getX(), punto2.getY());
                    //se le pasa al metodo Pointscount los objeto punto 1 y punto 2 con las coordenadas de click del raton
                    PointsCount(punto1);
                    PointsCount(punto2);
                    cont = 0;
//                  
                }

            }
        });

    }

    @FXML
    public void Salir() {
        //se accede al stage actual y se linkea con el btn_salir la scene
        Stage stage1 = (Stage) btn_slr.getScene().getWindow();
        //accedemos al objeto stage1 y le damos el metodo para cerrar 
        stage1.close();

    }

    @FXML
    //metodo para dibujar lineas a partir de datos enviados a traves de los textfield
    public void dibujarLinea() {
        //se declara objeto graphicc para dibujar  en el canvas 
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //se castea la coordenada introducida de String a Double 
            Double corX = Double.parseDouble(txt_X.getText());
            //se castea la coordenada introducida de String a Double
            Double corY = Double.parseDouble(txt_Y.getText());
            //se castea la coordenada introducida de String a Double
            Double corX2 = Double.parseDouble(txt_Xx.getText());
            //se castea la coordenada introducida de String a Double
            Double corY2 = Double.parseDouble(txt_Yy.getText());
            //si el texto del primer textfield no esta vacio y si corX es menor q el ancho del canvas
        if (!txt_X.getText().isEmpty() && !txt_Y.getText().isEmpty() && !txt_Xx.getText().isEmpty() && !txt_Yy.getText().isEmpty() 
                && corX < canvas.getWidth() && corY < canvas.getHeight() &&  corX2 < canvas.getWidth() && corY2 < canvas.getHeight() ) {
            
            //seteamos a azul las nuevas lineas creadas a traves de este metodo
            gc.setStroke(Color.BLUE);
            //seteamos a 5 el ancho de las lineas creadas
            gc.setLineWidth(5);           
            //se le pasa al metodo strokeline los 4 parametro necesarios para dibujar una linea todos en double
            gc.strokeLine(corX, corY, corX2, corY2);
        } else {
            //si el txt de las coordenadas esta vacio o excede la capacidad maxima del canvas se informa al usuario con un mensaje en pantalla
            JOptionPane.showMessageDialog(null, "Debe introducir coordenadas correctas primero");
            JOptionPane.showMessageDialog(null, " Width X  Canvas Max : 293 ,  Height Y  Canvas Max : 458 ");
        }
    }

    @FXML
    //se declara metodo para borrar lineas
    public void borrarLinea() {
        //se declara objeto graphicc para dibujar  en el canvas 
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //se setea el color para rellenar el canvas
        gc.setFill(Color.GRAY);
        //se dibuja un cuadrado en gris con las medidas de todo el canvas para tapar cualquier linea dibujada
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
///////////////////////
        Double[][] prueba = {
         {1.012,2.123,3.345,4.456,5.543,6.543,7.456,8.673,9.456,0.356},
         {7.133,0.135,8.456,2.654,1.234,10.067,54.456,9.432,23.235,11.959}
        };
        Metodos.ordenarArray(prueba);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

}
