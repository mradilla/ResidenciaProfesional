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
public class OperacionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView yuxtaposicion;
    @FXML
    private WebView opcion1;
    @FXML
    private WebView opcion2;
    @FXML
    private WebView repetir1;
    @FXML
    private WebView repetir1mas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarAnimacion(yuxtaposicion, "yuxtaposicion_Canvas");
        cargarAnimacion(opcion1, "opcion 1_Canvas");
        cargarAnimacion(opcion2, "opcion 2_Canvas");
        cargarAnimacion(repetir1, "Repetir 2_Canvas");
        cargarAnimacion(repetir1mas, "Repetir 1_Canvas");
    }

    public void cargarAnimacion(WebView wb, String file) {

        WebEngine engine = wb.getEngine();
        URL webpage = this.getClass().getResource("/Animate/" + file + ".html");
        engine.load(webpage.toString());
        wb.setCache(true);
        wb.setZoom(0.8);
    }

    @FXML
    void regresarIndice(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().indice();
    }

    @FXML
    void Diagramas2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().diagramas(event);
    }

    @FXML
    void Construccion2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().construccion(event);
    }

    @FXML
    void guardarPDF(ActionEvent event) {
        Modelo.guardarArchivo("operaciones.pdf");
    }
}
