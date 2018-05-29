package Modulo_I.Submodulos;

/**
 *
 * @author Martha
 */
public class Pregunta {
    
    String texto, respuestaCorrecta, respuesta1, respuesta2,imagen;

   
    public Pregunta() {
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getTexto() {
        return texto;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    
    @Override
    public String toString() {
        return "Pregunta{" + "texto=" + texto + ", respuestaCorrecta=" + respuestaCorrecta + ", respuesta1=" + respuesta1 + ", respuesta2=" + respuesta2 + '}';
    }
      
}
