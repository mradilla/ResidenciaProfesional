package Modulo_II.Submodulos;

import Inicio.Modelo;
import Modulo_II.InterfazAnalizadorLexicoController;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;
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

    public VBox anchor;
    public VBox caja;

    @FXML
    private Accordion menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void indice() {
        cargarContenido("Explicacion");
    }

    public void cargarAnimacion(WebView wb, String file) {

        WebEngine engine = wb.getEngine();
        URL webpage = this.getClass().getResource("/Animate/" + file + ".html");
        engine.load(webpage.toString());
        wb.setCache(true);
        wb.setZoom(0.65);
    }

    public void cargarContenido(String c) {
        try {
            caja = FXMLLoader.load(getClass().getResource(c + ".fxml"));
            // ajustar(caja, anchor);
            anchor.getChildren().clear();
            anchor.getChildren().add(caja);
        } catch (IOException ex) {
            Logger.getLogger(InterfazAnalizadorLexicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void procesadores(ActionEvent event) {
        cargarContenido("Procesadores");
    }

    @FXML
    void compilador(ActionEvent event) {
        cargarContenido("Procesadores");

    }

    @FXML
    void interprete(ActionEvent event) {
        cargarContenido("Procesadores");
    }

    @FXML
    void estructura(ActionEvent event) {
        cargarContenido("Estructura");
    }

    @FXML
    void estructura2(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().estructura(event);
    }

    @FXML
    void analisis(ActionEvent event) {
        cargarContenido("Analisis");
    }

    @FXML
    void analisis2(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().analisis(event);
    }

    @FXML
    void recursivas(ActionEvent event) {
        cargarContenido("Recursivas");
    }

    @FXML
    void recursivas2(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().recursivas(event);
    }

    @FXML
    void regresarIndice(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().indice();
    }

    @FXML
    void procesadores2(ActionEvent event) {
        InterfazAnalizadorLexicoController.getExplicacionController().procesadores(event);
    }

  

}
