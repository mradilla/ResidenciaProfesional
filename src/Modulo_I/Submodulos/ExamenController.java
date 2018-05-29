package Modulo_I.Submodulos;

import Inicio.InterfazInicioController;
import Modulo_I.InterfazDiagramasSintaxisController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class ExamenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox cajaExamen;

    int nPreguntas;
    ArrayList<Pregunta> basePreguntas;
    ArrayList<String> respuestasCorrectas, respAlumno;
    ArrayList<ToggleGroup> gruposRespuestas;
    ArrayList<Integer> nAleatorio;
    ArrayList<ArrayList<Integer>> orden;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cajaExamen.setPadding(new Insets(20, 70, 20, 70));

        basePreguntas = leerPreguntas("basePreguntas.xml");
        nPreguntas = 10;
        respuestasCorrectas = new ArrayList<>();
        gruposRespuestas = new ArrayList<>();
        orden = new ArrayList<>();
        nAleatorio = generarAleatorios(nPreguntas, 0, (basePreguntas.size() - 1));
        Label instrucciones = new Label("Instrucciones: Elige la opción que consideres correcta para cada pregunta.");
        instrucciones.setWrapText(true);
        instrucciones.setPadding(new Insets(25, 0, 40, 0));
        instrucciones.setStyle(" -fx-font-size: 15;");

        cajaExamen.getChildren().add(instrucciones);
        int contador = 0;
        System.out.println("contador: " + contador);
        for (int aleatorio : nAleatorio) {
            contador++;
            System.out.println("contador: " + contador);

            Pregunta p = basePreguntas.get(aleatorio);

            VBox cajaPregunta = new VBox();
            Label textoPregunta = new Label("\n" + contador + ". " + p.getTexto());
            textoPregunta.setWrapText(true);
            textoPregunta.setPadding(new Insets(0, 0, 20, 0));

            textoPregunta.setStyle(" -fx-font-size: 15; -fx-font-weight: bold;");

            ImageView imagen = null;

            if (p.getImagen() != null) {
                try {
                    System.out.println(p.getImagen());
                    imagen = new ImageView(new Image(getClass().getResourceAsStream(p.getImagen()))); //Logger.getLogger(ContenidoExamenController.class.getName()).log(Level.SEVERE, null, ex);
                    imagen.setFitHeight(55);
                    imagen.setPreserveRatio(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            HBox cajaRespuestas = new HBox();
            cajaRespuestas.setPadding(new Insets(15, 0, 0, 0));

            String[] respuestas = new String[3];
            respuestas[0] = p.getRespuesta1();
            respuestas[1] = p.getRespuesta2();
            respuestas[2] = p.getRespuestaCorrecta();

            ArrayList<Integer> aleat = generarAleatorios(3, 0, 2);

            ToggleGroup grupo = new ToggleGroup();
            orden.add(aleat);

            Region espacio = new Region();
            espacio.setPrefHeight(30);
            HBox.setHgrow(espacio, Priority.ALWAYS);
            cajaRespuestas.getChildren().add(espacio);
            for (int a : aleat) {
                RadioButton radio = new RadioButton(respuestas[a]);
                radio.setWrapText(true);
                radio.setPadding(new Insets(0, 40, 0, 0));
                radio.setCursor(Cursor.HAND);

                if (respuestas[a].contains("png")) {

                    ImageView img = new ImageView(new Image(getClass().getResourceAsStream(respuestas[a]))); //  Logger.getLogger(ContenidoExamenController.class.getName()).log(Level.SEVERE, null, ex);
                    img.setFitHeight(55);
                    img.setPreserveRatio(true);
                    radio.setGraphic(img);

                    radio.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    radio.setText(respuestas[a]);
                }

                radio.setToggleGroup(grupo);
                cajaRespuestas.getChildren().add(radio);
                espacio = new Region();
                espacio.setPrefHeight(30);
                HBox.setHgrow(espacio, Priority.ALWAYS);

                cajaRespuestas.getChildren().add(espacio);
            }

            gruposRespuestas.add(grupo);

            cajaPregunta.getChildren().add(textoPregunta);

            if (imagen != null) {

                HBox cajaImagen = new HBox();
                Region espacio1 = new Region();

                HBox.setHgrow(espacio1, Priority.ALWAYS);

                Region espacio3 = new Region();
                HBox.setHgrow(espacio3, Priority.ALWAYS);

                cajaImagen.getChildren().addAll(espacio1, imagen, espacio3);
                cajaPregunta.getChildren().add(cajaImagen);
                Region r = new Region();
                r.setPrefHeight(30);
                cajaPregunta.getChildren().add(r);
            }

            cajaPregunta.getChildren().add(cajaRespuestas);
            cajaPregunta.setPadding(new Insets(0, 30, 30, 30));
            cajaPregunta.getStyleClass().add("cajaPregunta");

            cajaExamen.getChildren().add(cajaPregunta);

            respuestasCorrectas.add(p.getRespuestaCorrecta());
        }

        Region r = new Region();
        r.setPrefHeight(30);
        cajaExamen.getChildren().add(r);

        HBox botones = new HBox();

        Region espacio1 = new Region();

        HBox.setHgrow(espacio1, Priority.ALWAYS);
        Region espacio2 = new Region();
        espacio2.setPrefWidth(50);
        Region espacio3 = new Region();
        HBox.setHgrow(espacio3, Priority.ALWAYS);

        JFXButton botonsalir = new JFXButton("Salir");
        botonsalir.getStyleClass().add("boton2");
        botonsalir.setOnAction(btnSalir);

        JFXButton botonEnviar = new JFXButton("Terminar examen");
        botonEnviar.getStyleClass().add("boton1");
        botonEnviar.setOnAction(btnEnviar);

        botones.getChildren().addAll(espacio1, botonsalir, espacio2, botonEnviar, espacio3);

        cajaExamen.getChildren().add(botones);

    }

    EventHandler<ActionEvent> btnSalir = (ActionEvent event) -> {

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("¿Salir del examen?");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        Label texto2 = new Label("Si sales del examen ahora perderás el progreso realizado.");
        texto2.getStyleClass().add("dTexto");
        content.setBody(texto2);
        StackPane s = InterfazInicioController.getControllerD().getStackpane();
        JFXDialog dialog = new JFXDialog(s, content, JFXDialog.DialogTransition.CENTER);
        JFXButton botonsalir = new JFXButton("Salir");
        botonsalir.setPadding(new Insets(10, 10, 10, 10));
        botonsalir.getStyleClass().add("dBoton2");
        botonsalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                InterfazDiagramasSintaxisController.getExamenController().reiniciarExamen(event);
            }
        });

        JFXButton botonregresar = new JFXButton("Regresar al examen");
        botonregresar.setPadding(new Insets(10, 10, 10, 10));

        botonregresar.getStyleClass().add("dBoton1");
        botonregresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InterfazInicioController.getControllerD().irA(0.0);
                dialog.close();
            }
        });
        content.setActions(botonregresar, botonsalir);

        dialog.show();

    };

    EventHandler<ActionEvent> btnEnviar = (ActionEvent event) -> {

        respAlumno = new ArrayList<>();
        for (ToggleGroup grupo : gruposRespuestas) {
            if (grupo.getSelectedToggle() != null) {
                RadioButton chk = (RadioButton) grupo.getSelectedToggle(); // Cast object to radio button
                String respuesta = chk.getText();
                respAlumno.add(respuesta);
            }
        }

        if (respAlumno.size() < nPreguntas) {

            JFXDialogLayout content = new JFXDialogLayout();
            Label texto = new Label("Aún no has terminado");
            texto.getStyleClass().add("dTitulo");
            content.setHeading(texto);
            Label texto2 = new Label("Asegúrate de haber respondido todas las preguntas");
            texto2.getStyleClass().add("dTexto");
            content.setBody(texto2);
            StackPane s = InterfazInicioController.getControllerD().getStackpane();
            JFXDialog dialog = new JFXDialog(s, content, JFXDialog.DialogTransition.CENTER);
            JFXButton botonsalir = new JFXButton("Aceptar");
            botonsalir.setPadding(new Insets(10, 10, 10, 10));
            botonsalir.getStyleClass().add("dBoton1");
            botonsalir.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    irinicio();
                    dialog.close();

                }
            });

            content.setActions(botonsalir);

            dialog.show();

            return;
        }

        JFXDialogLayout content = new JFXDialogLayout();
        Label texto = new Label("¿Terminar examen?");
        texto.getStyleClass().add("dTitulo");
        content.setHeading(texto);
        Label texto2 = new Label("Puedes revisar tus respuestas una vez más o finalizar el examen.");
        texto2.getStyleClass().add("dTexto");
        content.setBody(texto2);
        StackPane s = InterfazInicioController.getControllerD().getStackpane();
        JFXDialog dialog = new JFXDialog(s, content, JFXDialog.DialogTransition.CENTER);
       
        
        JFXButton botonsalir = new JFXButton("Terminar examen");
        botonsalir.setPadding(new Insets(10, 10, 10, 10));
        botonsalir.getStyleClass().add("dBoton1");
        botonsalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();

                InterfazInicioController.getControllerD().examenTerminado();

                cajaExamen.getChildren().clear();
                int preg = 0;
                double promedio = calcularPromedio(respuestasCorrectas, respAlumno);

                Label lbl = new Label("RESULTADOS DEL EXAMEN");
                lbl.getStyleClass().add("Indice1");
                lbl.setPadding(new Insets(0, 0, 30, 0));

                Label lblProm = new Label("Calificación: " + promedio);

                lblProm.getStyleClass().add("Indice2");
                lblProm.setPadding(new Insets(0, 0, 30, 0));

                cajaExamen.getChildren().addAll(lbl, lblProm);
                for (int aleatorio : nAleatorio) {
                    Pregunta p = basePreguntas.get(aleatorio);
                    HBox caja = new HBox();

                    VBox cajaPregunta = new VBox();
                    HBox.setHgrow(cajaPregunta, Priority.ALWAYS);

                    cajaPregunta.setPadding(new Insets(20, 25, 0, 0));

                    Label textoPregunta = new Label(p.getTexto());
                    textoPregunta.setWrapText(true);
                    textoPregunta.setPadding(new Insets(-20, 0, 0, 0));

                    textoPregunta.setStyle(" -fx-font-size: 15; -fx-font-weight: bold;");
                    ImageView imagen = null;
                    if (p.getImagen() != null) {
                        imagen = new ImageView(new Image(getClass().getResourceAsStream(p.getImagen()))); //Logger.getLogger(ContenidoExamenController.class.getName()).log(Level.SEVERE, null, ex);
                        imagen.setFitHeight(55);
                        imagen.setPreserveRatio(true);
                    }

                    Image im = null;
                    boolean c = true;
                    try {
                        if (respuestasCorrectas.get(preg).equals(respAlumno.get(preg))) {
                            im = new Image(getClass().getResourceAsStream("/Imagenes/ok.png"));
                            caja.getStyleClass().add("correcta");
                            c = true;
                        } else {
                            caja.getStyleClass().add("incorrecta");
                            im = new Image(getClass().getResourceAsStream("/Imagenes/no.png"));
                            c = false;
                        }
                    } catch (Exception e) {
                    }
                    ImageView icon = new ImageView(im);

                    HBox cajaRespuestas = new HBox();
                    cajaRespuestas.setPadding(new Insets(15, 0, 0, 0));

                    Region r = new Region();
                    r.setPrefHeight(20);
                    Region r2 = new Region();
                    r2.setPrefHeight(20);

                    cajaPregunta.getChildren().addAll(textoPregunta, r);

                    if (imagen != null) {

                        HBox cajaImagen = new HBox();
                        Region espacio1 = new Region();

                        HBox.setHgrow(espacio1, Priority.ALWAYS);

                        Region espacio3 = new Region();
                        HBox.setHgrow(espacio3, Priority.ALWAYS);

                        cajaImagen.getChildren().addAll(espacio1, imagen, espacio3);
                        cajaPregunta.getChildren().add(cajaImagen);
                        Region r3 = new Region();
                        r.setPrefHeight(30);
                        cajaPregunta.getChildren().add(r3);

                    }

                    cajaPregunta.getChildren().addAll(cajaRespuestas, r2);

                    String[] respuestas = new String[3];
                    respuestas[0] = p.getRespuesta1();
                    respuestas[1] = p.getRespuesta2();
                    respuestas[2] = p.getRespuestaCorrecta();
                    Region espacio = new Region();
                    espacio.setPrefHeight(30);
                    HBox.setHgrow(espacio, Priority.ALWAYS);
                    cajaRespuestas.getChildren().add(espacio);
                    for (int a : orden.get(preg)) {
                        RadioButton radio = new RadioButton();
                        radio.setWrapText(true);
                        radio.setMouseTransparent(true);
                        radio.setPadding(new Insets(5, 20, 5, 20));
                        radio.setCursor(Cursor.HAND);

                        if (respuestas[a].contains("png")) {
                            ImageView img = new ImageView(new Image(getClass().getResourceAsStream(respuestas[a]))); //  Logger.getLogger(ContenidoExamenController.class.getName()).log(Level.SEVERE, null, ex);
                            img.setFitHeight(55);
                            img.setPreserveRatio(true);
                            radio.setGraphic(img);
                            radio.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        } else {
                            radio.setText(respuestas[a]);
                        }

                        cajaRespuestas.getChildren().add(radio);

                        if (respAlumno.get(preg).equals(respuestas[a])) {
                            radio.setSelected(true);
                        }
                        if (respuestas[a].equals(respuestasCorrectas.get(preg))) {
                            if (!c) {
                                radio.setTextFill(Color.web("#29a329"));

                                radio.getStyleClass().add("respCorrecta");
                            }
                        }
                        Region espacio2 = new Region();
                        espacio2.setPrefHeight(30);
                        HBox.setHgrow(espacio2, Priority.ALWAYS);
                        cajaRespuestas.getChildren().add(espacio2);
                    }

                    caja.getChildren().addAll(cajaPregunta, icon);
                    caja.setPadding(new Insets(20, 20, 20, 20));

                    cajaExamen.getChildren().add(caja);

                    preg++;
                }

                HBox cajaboton = new HBox();
                cajaboton.setPadding(new Insets(20, 0, 30, 0));

                JFXButton botonIniciarNuevo = new JFXButton("Repetir examen");
                botonIniciarNuevo.getStyleClass().add("boton1");
                botonIniciarNuevo.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        InterfazDiagramasSintaxisController.getExamenController().reiniciarExamen(event);
                    }
                });

                Region e1 = new Region();
                e1.setPrefWidth(20);
                HBox.setHgrow(e1, Priority.ALWAYS);
                Region e2 = new Region();
                e2.setPrefWidth(20);
                HBox.setHgrow(e2, Priority.ALWAYS);
                cajaboton.getChildren().addAll(e1, botonIniciarNuevo, e2);
                cajaExamen.getChildren().add(cajaboton);

                irinicio();
            }
        });

        JFXButton botonregresar = new JFXButton("Revisar respuestas");
        botonregresar.setPadding(new Insets(10, 10, 10, 10));

        botonregresar.getStyleClass().add("dBoton1");
        botonregresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                irinicio();
                dialog.close();
            }
        });
        content.setActions(botonregresar, botonsalir);

        dialog.show();

    };

    public ArrayList<Pregunta> leerPreguntas(String file) {
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        try {
            ////////////////
            InputStream inputFile = getClass().getResourceAsStream("/Modulo_I/Submodulos/" + file);
            
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Pregunta");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Pregunta p = new Pregunta();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    p.setTexto(eElement.getAttribute("texto"));
                    NodeList carNameList = eElement.getElementsByTagName("respuesta");
                    for (int count = 0; count < carNameList.getLength(); count++) {
                        Node node1 = carNameList.item(count);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element car = (Element) node1;
                            if (car.getAttribute("tipo").equals("Correcta")) {
                                p.setRespuestaCorrecta(car.getTextContent());
                            } else {
                                if (p.getRespuesta1() == null) {
                                    p.setRespuesta1(car.getTextContent());
                                } else {
                                    p.setRespuesta2(car.getTextContent());
                                }
                            }
                        }
                    }

                    NodeList im = eElement.getElementsByTagName("imagen");

                    for (int count = 0; count < im.getLength(); count++) {
                        Node node1 = im.item(count);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element car = (Element) node1;
                            p.setImagen(car.getTextContent());
                        }
                    }

                }
                preguntas.add(p);
            }
            return preguntas;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    public ArrayList<Integer> generarAleatorios(int total, int min, int max) {

        ArrayList<Integer> numeros = new ArrayList<>();

        Random rn = new Random();
        int a;
        do {
            a = rn.nextInt((max) - min + 1) + min;
            if (!numeros.contains(a)) {
                numeros.add(a);
            }
        } while (numeros.size() < total);

        return numeros;
    }

    private double calcularPromedio(ArrayList<String> respuestasCorrectas, ArrayList<String> respAlumno) {
        int totalP = respuestasCorrectas.size();
        int correctas = 0;
        for (int i = 0; i < totalP; i++) {
            System.out.println(respuestasCorrectas.get(i));
            System.out.println(respAlumno.get(i));
            if (respuestasCorrectas.get(i).equals(respAlumno.get(i))) {
                correctas++;
            }
        }

        System.out.println("CORRECTAS: " + correctas);
        double prom = (correctas * 100) / totalP;
        return prom;

    }

    private void irinicio() {
        InterfazInicioController.getControllerD().irA(0.0);
    }
    
    
    
    @FXML
    void guardarPDF(ActionEvent event) {

    }

}
