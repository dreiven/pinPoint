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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javafx.scene.paint.Paint;

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
    private Stage stage1;
    @FXML
    private ArrayList arrayPuntos = new ArrayList();
    @FXML
    private Button btn_cr;
    @FXML
    private File fichero;
    @FXML
    private int cont;

    public void botonAccionBorrar(ActionEvent event) {

        Metodos.borrarFichero(fichero);
        
    }

    public void botonAccionCrear(ActionEvent event) {

        Metodos.crearFichero(fichero);

    }

    @FXML
    private void cambio() {

        if (tb_cambio.isSelected()) {
            tb_cambio.setText("ON");
            lbl_tg.setText("Modo Discontinuo");
            lbl_tg.setTextFill(RED);
        } else {
//            tb_cambio.setSelected(false);
            tb_cambio.setText("OFF");
            lbl_tg.setText("Modo Continuo");
            tb_cambio.arm();
            lbl_tg.setTextFill(GREEN);
        }

    }

    @FXML
    //se declara metodo reset en el action event del boton reset
    private void Reset(ActionEvent event
    ) {
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
        Paint[] colores = {Color.AQUA,Color.BLUE,Color.RED};
       for  (cont = 0; cont < 2; cont++) {
           gc.setFill(colores[cont]);    
           }
//        gc.setFill(Color.GRAY);
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

//           punto= new Image(Main.class.getResourceAsStream("punto.png"));
//           final HBox pictureRegion = new HBox();
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
//                punto2.setLocation(t.getX(), t.getY());

                punto1.setX(t.getX());
                punto1.setY(t.getY());

//                int posX = MouseInfo.getPointerInfo().getLocation().x;
//                int posY = MouseInfo.getPointerInfo().getLocation().y;
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
                    //se crea una linea a traves del metodo strokeLine del objeto gc GraphicsContent

                    gc.strokeLine(punto1.getX(), punto1.getY(), punto2.getX(), punto2.getY());
                    Metodos.PointsCount(punto1);
                    Metodos.PointsCount(punto2);
                    try {
                        Metodos.escritura(punto1);
                        Metodos.escritura(punto2);

//                    Metodos.listarPuntos(arrayPuntos);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

        // TODO
    }

}
