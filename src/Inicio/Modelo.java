/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Martha
 */
public class Modelo extends Application {

    private static Stage ventana;
    static InterfazInicioController con;

    @Override
    public void start(Stage stage) throws Exception {

        ventana = stage;
        ventana.setTitle("LENGUAJES Y AUTOMATAS II");
        ventana.setMinWidth(1100);
        ventana.setMinHeight(610);
        stage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/Imagenes/icono.png")));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfazInicio.fxml"));
        Parent root = loader.load();
        con = loader.getController();
        Scene scene = new Scene(root);
        ventana.setScene(scene);
        ventana.show();
    }

    public static InterfazInicioController getCon() {
        return con;
    }

    public static Stage getVentana() {
        return ventana;
    }

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void guardarArchivo(String nombre) {

        FileChooser fileChooser = new FileChooser();
        String extension1 = "", extension2 = "", ruta = "";

        extension1 = "Archivos PDF (*.pdf)";
        extension2 = "*.pdf";
        ruta = "/pdf/";

        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter(extension1, extension2);

        fileChooser.setInitialFileName(nombre);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(getVentana());

        if (file != null) {

            new GuardarPDF((ruta + nombre), file.getAbsolutePath());

           
            avisar(file.getParentFile().toString());
        }

    }
    
    
    private static void avisar(String absolutePath) {

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("El archivo se guardó correctamente");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        Label texto2 = new Label("El tema se guardó en el archivo PDF.");
        texto2.getStyleClass().add("dTexto");
        content.setBody(texto2);

        StackPane s = InterfazInicioController.getControllerD().getStackpane();

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

    private static void abrirCarpeta(String absolutePath) {
        try {
            File file = new File(absolutePath);
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException ex) {
            
        }
    }


}
