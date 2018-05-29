package Modulo_II.Submodulos;

import Inicio.InterfazInicioController;
import Inicio.Modelo;
import Modulo_I.InterfazDiagramasSintaxisController;
import Modulo_I.Submodulos.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class ExportarController implements Initializable {

    String lenguaje;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void exportarJava(ActionEvent event) {
        lenguaje = "Java";
        guardarArchivo("AnalizadorJava");
    }

    public void exportarC(ActionEvent event) {
        lenguaje = "C++";
        guardarArchivo("AnalizadorC++.cc");

    }

    public void guardarArchivo(String nombre) {

        FileChooser fileChooser = new FileChooser();
        String extension1 = "", extension2 = "", ruta = "";

        if (nombre.contains("Java")) {
            extension1 = "Archivos Java (*.java)";
            extension2 = "*.java";
            ruta = "AnalizadorJava/";
        } else {
            extension1 = "Archivos C++ (*.cc)";
            extension2 = "*.cc";
            ruta = "AnalizadorC++/";
        }

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter(extension1, extension2);
        fileChooser.getExtensionFilters().add(extFilter);
        if (nombre.contains("Java")) {
            fileChooser.setInitialFileName(nombre + ".java");

        } else {
            fileChooser.setInitialFileName(nombre);
        }

        //Show save file dialog
        File file = fileChooser.showSaveDialog(Modelo.getVentana());

        if (file != null) {

            new CopiarArchivo((ruta + nombre), file.getAbsolutePath());

            avisar(file.getParentFile().toString());

        }

    }

    private void avisar(String absolutePath) {

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("El archivo se guardó correctamente");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        Label texto2 = new Label("Para ejecutar el analizador coloca el archivo exportado\nen un proyecto " + lenguaje + " creado en el IDE de tu preferencia.");
        texto2.getStyleClass().add("dTexto");
        content.setBody(texto2);

        StackPane s = InterfazInicioController.getController().getStackpane();

        JFXDialog dialog = new JFXDialog(s, content, JFXDialog.DialogTransition.CENTER);

        JFXButton botonsalir = new JFXButton("Abrir carpeta");
        botonsalir.setPadding(new Insets(10, 10, 10, 10));
        botonsalir.getStyleClass().add("dBoton1");

        botonsalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                abrirCarpeta(absolutePath);
            }
        });

        JFXButton botonregresar = new JFXButton("Aceptar");
        botonregresar.setPadding(new Insets(10, 10, 10, 10));

        botonregresar.getStyleClass().add("dBoton1");
        botonregresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(botonsalir, botonregresar);

        dialog.show();
    }

    private void abrirCarpeta(String absolutePath) {
        try {
            File file = new File(absolutePath);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ExportarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void avisar2() {

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("Prueba");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        WebView wb = new WebView();
        wb.setMaxWidth(100);
        wb.setMaxHeight(100);

        WebEngine engine = wb.getEngine();
        URL webpage = this.getClass().getResource("/Animate/compilador.html");
        engine.load(webpage.toString());
        wb.setZoom(0.8);

        content.getStyleClass().add("botonSubmodulo");

        try {
            content.setBody((Node) FXMLLoader.load(getClass().getResource("Exportar.fxml")));
        } catch (IOException ex) {

        }

        StackPane s = InterfazInicioController.getController().getStackpane();

        JFXDialog dialog = new JFXDialog(s, content, JFXDialog.DialogTransition.CENTER);

        JFXButton botonregresar = new JFXButton("Aceptar");
        botonregresar.setPadding(new Insets(10, 10, 10, 10));

        botonregresar.getStyleClass().add("dBoton1");
        botonregresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        content.setActions(botonregresar);

        dialog.show();
    }

}
