package Modulo_II.Submodulos;

import Inicio.InterfazInicioController;
import Modulo_I.Submodulos.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class InicioEvaluacionController implements Initializable {


    @FXML
    private VBox cajaExamen, caja;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
    }

    
    public void CargarExamen(ActionEvent ae){
        cargarContenido("Examen");
         InterfazInicioController.getController().examenActivo();
    }
    
     public void cargarContenido(String c) {
        try {
            caja = FXMLLoader.load(getClass().getResource(c + ".fxml"));
            //ajustar(caja, anchor);
            cajaExamen.getChildren().clear();
            cajaExamen.getChildren().add(caja);
        } catch (IOException ex) {
            //  Logger.getLogger(InterfazAnalizadorLexicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void reiniciarExamen(ActionEvent ae){
         
         cargarContenido("InicioEvaluacion");
                 InterfazInicioController.getController().examenTerminado();

     }

}
