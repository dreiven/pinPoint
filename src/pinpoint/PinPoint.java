/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinpoint;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class PinPoint extends Application {

    @Override
    public void start(Stage primarystage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        primarystage.setScene(scene);
        primarystage.show();
      
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
        
    }

}
