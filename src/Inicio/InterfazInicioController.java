/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import Modulo_I.InterfazDiagramasSintaxisController;
import Modulo_II.InterfazAnalizadorLexicoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Martha
 */
public class InterfazInicioController implements Initializable {

    static InterfazAnalizadorLexicoController controller;
    static InterfazDiagramasSintaxisController controllerD;

    public static InterfazAnalizadorLexicoController getController() {
        return controller;
    }

    public static InterfazDiagramasSintaxisController getControllerD() {
        return controllerD;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void btnDiagramasSintaxis(ActionEvent event) {
        controllerD = (InterfazDiagramasSintaxisController) cambiarEscena("/Modulo_I/InterfazDiagramasSintaxis");
    }

    @FXML
    public void btnAnalizadorLexico(ActionEvent event) {
        controller = (InterfazAnalizadorLexicoController) cambiarEscena("/Modulo_II/InterfazAnalizadorLexico");
    }

    public Object cambiarEscena(String fxml) {
        try {
            Stage stage = Modelo.getVentana();
            FXMLLoader f = new FXMLLoader(getClass().getResource(fxml + ".fxml"));
            Parent root = f.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return f.getController();
        } catch (IOException ex) {
            Logger.getLogger(InterfazInicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
