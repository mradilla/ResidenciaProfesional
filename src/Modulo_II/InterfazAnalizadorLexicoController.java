package Modulo_II;

import Inicio.InterfazInicioController;
import Inicio.Modelo;
import Modulo_II.Submodulos.ExplicacionController;
import Modulo_II.Submodulos.InicioEvaluacionController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Martha
 */
public class InterfazAnalizadorLexicoController implements Initializable {

    @FXML
    private JFXButton btnAlgoritmo;

    @FXML
    private JFXButton btnExportar;

    @FXML
    private ScrollPane cajaContenido;

    @FXML
    private JFXButton btnEvaluacion;

    @FXML
    private JFXButton btnLexicon;

    @FXML
    private JFXButton btnExplicacion,btnInicio;

    public static boolean examenActivo = false;

    static ExplicacionController exp;
    @FXML
    private StackPane stackpane;
    static InicioEvaluacionController examen;

    
    
    public StackPane getStackpane() {
        return stackpane;
    }

    public void examenActivo() {
        examenActivo = true;
        botones();
    }

    public void examenTerminado() {
        examenActivo = false;
        botones();
    }

    public void botones() {
        btnExplicacion.setDisable(examenActivo);
        btnAlgoritmo.setDisable(examenActivo);
        btnEvaluacion.setDisable(examenActivo);
        btnLexicon.setDisable(examenActivo);
        btnExportar.setDisable(examenActivo);
       btnInicio.setDisable(examenActivo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exp = (ExplicacionController) cargarContenido("Submodulos/Explicacion");
        seleccionar(btnExplicacion);
    }

    public Object cargarContenido(String c) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(c + ".fxml"));
            VBox caja = fxmlLoader.load();
            cajaContenido.setContent(caja);
            cajaContenido.setVvalue(-50);
            return fxmlLoader.getController();
        } catch (IOException ex) {
            Logger.getLogger(InterfazAnalizadorLexicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static ExplicacionController getExplicacionController() {
        return exp;
    }

    public static InicioEvaluacionController getExamenController() {
        return examen;
    }

    public ScrollPane getScroll() {
        return cajaContenido;
    }

    @FXML
    public void cargarExplicacion(ActionEvent a) {
        exp = (ExplicacionController) cargarContenido("Submodulos/Explicacion");
        seleccionar(btnExplicacion);
        desseleccionar(btnAlgoritmo);
        desseleccionar(btnEvaluacion);
        desseleccionar(btnLexicon);
        desseleccionar(btnExportar);
    }

    @FXML
    public void cargarEjemplos(ActionEvent a) {
        cargarContenido("Submodulos/Ejemplos");
        desseleccionar(btnExplicacion);
        seleccionar(btnAlgoritmo);
        desseleccionar(btnEvaluacion);
        desseleccionar(btnLexicon);
        desseleccionar(btnExportar);
    }

    @FXML
    public void cargarExamen(ActionEvent a) {
        System.out.println("djhfksdjhsfj");
        examen = (InicioEvaluacionController) cargarContenido("Submodulos/InicioEvaluacion");
        desseleccionar(btnExplicacion);
        desseleccionar(btnAlgoritmo);
        seleccionar(btnEvaluacion);
        desseleccionar(btnLexicon);
        desseleccionar(btnExportar);

    }

    @FXML
    public void cargarLexicon(ActionEvent a) {
        cargarContenido("Submodulos/Lexicon");
        desseleccionar(btnExplicacion);
        desseleccionar(btnAlgoritmo);
        desseleccionar(btnEvaluacion);
        seleccionar(btnLexicon);
        desseleccionar(btnExportar);
    }

    @FXML
    public void cargarExportar(ActionEvent a) {
        cargarContenido("Submodulos/Exportar");
        desseleccionar(btnExplicacion);
        desseleccionar(btnAlgoritmo);
        desseleccionar(btnEvaluacion);
        desseleccionar(btnLexicon);
        seleccionar(btnExportar);
    }

    @FXML
    void regresarInicio(ActionEvent event) {
        cambiarEscena("/Inicio/InterfazInicio");
    }

    public void desseleccionar(JFXButton b) {
        b.getStyleClass().clear();
        b.getStyleClass().add("BotonSubmodulo");
    }

    public void seleccionar(JFXButton b) {
        b.getStyleClass().clear();
        b.getStyleClass().add("seleccionado");
    }

    public void cambiarEscena(String fxml) {
        try {
            Stage stage = Modelo.getVentana();
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(InterfazInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void irA(double c) {
        final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(cajaContenido.vvalueProperty(), c);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

}
