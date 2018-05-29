package Modulo_I.Submodulos;

import Inicio.InterfazInicioController;
import Inicio.Modelo;
import Modulo_I.InterfazDiagramasSintaxisController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class ExplicacionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public VBox anchor;
    public VBox caja;

    @FXML
    private Accordion menu;
    @FXML
    private WebView elementos;

    String activo = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void cargarContenido(String c) {
        try {

            caja = FXMLLoader.load(getClass().getResource(c + ".fxml"));
            //ajustar(caja, anchor);
            anchor.getChildren().clear();
            anchor.getChildren().add(caja);
        } catch (IOException ex) {
            //  Logger.getLogger(InterfazAnalizadorLexicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void regresarIndice(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().indice();
    }

    @FXML
    void gramatica(ActionEvent event) {
        cargarContenido("Gramatica");
    }

    @FXML
    void Gramatica2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().gramatica(event);
    }

    @FXML
    void diagramas(ActionEvent event) {
        cargarContenido("Diagramas");
    }

    @FXML
    void Diagramas2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().diagramas(event);
    }

    @FXML
    void operaciones(ActionEvent event) {
        cargarContenido("Operaciones");
    }

    @FXML
    void Operaciones2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().operaciones(event);
    }

    @FXML
    void construccion(ActionEvent event) {
        System.out.println("sdsdsd");
        cargarContenido("Construccion");
    }

    @FXML
    void Construccion2(ActionEvent event) {
        InterfazDiagramasSintaxisController.getExplicacionController().construccion(event);
    }

    void indice() {
        cargarContenido("Explicacion_1");
    }

      @FXML
    void guardarPDF(ActionEvent event) {
        Modelo.guardarArchivo("gramatica.pdf");
    }

}
