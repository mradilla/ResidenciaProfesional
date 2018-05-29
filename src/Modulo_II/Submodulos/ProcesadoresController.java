package Modulo_II.Submodulos;

import Inicio.Modelo;
import Modulo_II.InterfazAnalizadorLexicoController;
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
public class ProcesadoresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView compilador, interprete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cargarAnimacion(compilador, "compilador");
        cargarAnimacion(interprete, "interprete");

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
        InterfazAnalizadorLexicoController.getExplicacionController().indice();
    }


    @FXML
    void siguiente(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().estructura(event);
    }
    
     @FXML
    void guardarPDF(ActionEvent event) {
        Modelo.guardarArchivo("Introducción.pdf");
    }

}
