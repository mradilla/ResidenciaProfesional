package Modulo_I;

import Inicio.InterfazInicioController;
import Inicio.Modelo;
import Modulo_I.Submodulos.ExplicacionController;
import Modulo_I.Submodulos.InicioEvaluacionController;
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
public class InterfazDiagramasSintaxisController implements Initializable {

    @FXML
    private JFXButton btnEjemplos;

    @FXML
    private ScrollPane cajaContenido;

    @FXML
    private JFXButton btnEvaluacion;

    @FXML
    private JFXButton btnExplicacion, btnInicio;

    static ExplicacionController exp;
    static InicioEvaluacionController examen;

    public StackPane stackpane;
        public StackPane getStackpane() {
        return stackpane;
    }

    
    public static boolean examenActivo = false;

    
    public void examenActivo() {
        examenActivo = true;
        botones();
    }

    public void examenTerminado() {
        examenActivo = false;
        botones();
    }
    
    public void botones(){
        btnExplicacion.setDisable(examenActivo);
        btnEjemplos.setDisable(examenActivo);
        btnEvaluacion.setDisable(examenActivo);
        btnInicio.setDisable(examenActivo);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exp = (ExplicacionController) cargarContenido("Submodulos/Explicacion_1");
        seleccionar(btnExplicacion);

    }

    public Object cargarContenido(String c) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            VBox caja = fxmlLoader.load(getClass().getResource(c + ".fxml").openStream());
            cajaContenido.setContent(caja);
            cajaContenido.setVvalue(-50);
            return fxmlLoader.getController();
        } catch (IOException ex) {
            System.out.println("sdfghfdsdfg");
            //Logger.getLogger(InterfazAnalizadorLexicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @FXML
    public void cargarExplicacion(ActionEvent a) {
        exp = (ExplicacionController) cargarContenido("Submodulos/Explicacion_1");
        seleccionar(btnExplicacion);
        desseleccionar(btnEjemplos);
        desseleccionar(btnEvaluacion);
    }

    @FXML
    public void cargarEjemplos(ActionEvent a) {
        cargarContenido("Submodulos/Ejemplos");
        seleccionar(btnEjemplos);
        desseleccionar(btnEvaluacion);
        desseleccionar(btnExplicacion);
    }

    @FXML
    public void cargarExamen(ActionEvent a) {
        examen = (InicioEvaluacionController) cargarContenido("Submodulos/InicioEvaluacion");
        seleccionar(btnEvaluacion);
        desseleccionar(btnEjemplos);
        desseleccionar(btnExplicacion);
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

    public static ExplicacionController getExplicacionController() {
        return exp;
    }

    public static InicioEvaluacionController getExamenController() {
        return examen;
    }

    public void irA(double c) {
        final Timeline timeline = new Timeline();
        final KeyValue kv = new KeyValue(cajaContenido.vvalueProperty(), c);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
