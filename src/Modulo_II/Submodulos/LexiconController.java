package Modulo_II.Submodulos;

import Inicio.InterfazInicioController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class LexiconController implements Initializable {

    int posImagen = 0;
    @FXML
    private JFXTextArea area;
    @FXML
    private ImageView imagen;
    @FXML
    private JFXListView<String> listatokens;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    ArrayList<String> imagenes, tokens;
    ArrayList<Integer> posTokens;
    @FXML
    private Label resultado;
    String res;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAnterior.setDisable(true);
        btnInicio.setDisable(true);
        btnSiguiente.setDisable(true);

        listatokens.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

    }

    @FXML
    public void analizar(ActionEvent a) {
        String cadena = area.getText();
        if (cadena.trim().equals("")) {
            avisar();
        } else {
            AnalizadorLexicon analizador = new AnalizadorLexicon(cadena);
            res = (analizador.analizar());
            imagenes = analizador.getEvaluacion();
            tokens = analizador.getTokens();
            posTokens = analizador.posTokens;

            listatokens.getSelectionModel().clearSelection();
            listatokens.getItems().clear();

            cargarTokens(tokens);
            posImagen = 0;
            cambiarImagen("secuencia-inicio");
            controlBotones();
            InterfazInicioController.getController().irA(0.6);
        }
    }

    public void adelante(ActionEvent ae) {
        posImagen++;
        cambiarImagen(imagenes.get(posImagen));
        controlBotones();
    }

    public void atras(ActionEvent ae) {
        posImagen--;
        cambiarImagen(imagenes.get(posImagen));
        controlBotones();
    }

    public void inicio(ActionEvent ae) {
        posImagen = 0;
        cambiarImagen(imagenes.get(posImagen));
        controlBotones();
    }

    public void controlBotones() {
        if (posImagen == 0) {
            btnInicio.setDisable(true);
            btnAnterior.setDisable(true);
        } else {
            btnInicio.setDisable(false);
            btnAnterior.setDisable(false);
        }
        if (posImagen == imagenes.size() - 1) {
            btnSiguiente.setDisable(true);
            resultado.setText("Resultado de la evaluación: " + res);
        } else {
            resultado.setText("");
            btnSiguiente.setDisable(false);
        }

    }

    public void cambiarImagen(String nombre) {
        String ruta = "/Modulo_II/Submodulos/imagenes/" + nombre + ".gif";
        System.out.println(ruta);
        Image image = new Image(ruta);
        imagen.setImage(image);
        System.out.println("POSIMAGEN: " + posImagen);
        seleccionarToken(posTokens.get(posImagen));
    }

    private void cargarTokens(ArrayList<String> tokens) {
        for (String t : tokens) {
            if(t.equals("°")){
                t = ">=";
            }
            if(t.equals("~")){
                t = "<=";
            }
            if(t.equals("!")){
                t = "!=";
            }
            listatokens.getItems().add(t);
        }

    }

    public void seleccionarToken(int n) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                listatokens.scrollTo(n);
                listatokens.getSelectionModel().select(n);
            }
        });
    }

    private void avisar() {

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("Expresión no válida");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        Label texto2 = new Label("Asegúrate de haber escrito la expresión que deseas evaluar.");
        texto2.getStyleClass().add("dTexto");
        content.setBody(texto2);

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
