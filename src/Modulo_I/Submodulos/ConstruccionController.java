package Modulo_I.Submodulos;

import Inicio.Modelo;
import Modulo_I.InterfazDiagramasSintaxisController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class ConstruccionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView elementos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void regresarIndice(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().indice();
    }

    @FXML
    void Operaciones2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().operaciones(event);
    }

    @FXML
    void guardarPDF(ActionEvent event) {
        Modelo.guardarArchivo("construccion.pdf");
    }
}
