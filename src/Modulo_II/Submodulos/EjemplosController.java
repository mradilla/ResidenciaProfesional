package Modulo_II.Submodulos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Martha
 */
public class EjemplosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private WebView wb;

    @Override
    public void initialize(URL url, ResourceBundle rb){        
        //uno por cada aimacion
        cargarAnimacion(wb, "algoritmo_analizador_Canvas_1");
    }

  public void cargarAnimacion(WebView wb, String file) {
        
  
                WebEngine engine = wb.getEngine();
                URL webpage = this.getClass().getResource("/Animate/" + file + ".html");
                engine.load(webpage.toString());
                wb.setCache(true);
                wb.setZoom(0.8);
    
    }
}
