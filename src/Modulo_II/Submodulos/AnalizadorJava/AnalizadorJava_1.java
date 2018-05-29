package Modulo_II.Submodulos.AnalizadorJava;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class AnalizadorJava_1 {

    private String cadena;
    private StringTokenizer tokenizer;
    private String token;
    private String resultadoAnalisis;

    public AnalizadorJava_1(String cadena) {
        this.cadena = cadena + " EOF";
    }

    public String analizar() {
        tokenizer = separarPalabras(cadena);
        secuencia();
        if (resultadoAnalisis == null) {
            resultadoAnalisis = "La sintaxis es correcta.";
        }
        return resultadoAnalisis;
    }

    public StringTokenizer separarPalabras(String cadena) {

        System.out.println("sdjshdjshd");
        cadena = cadena.replace("||", "|");
        cadena = cadena.replace("&&", "&");
        cadena = cadena.replace(">=", "°"); ///////////////////////7
        cadena = cadena.replace("<=", "~");/////////////////////////        
        cadena = cadena.replace("!=", "!");/////////////////////////
        System.out.println("cadenaaa: "+cadena);
        return new StringTokenizer(cadena, " <>\n!\\(\\);+-*/%|&=°~", true);//////////////
    }

    public void getToken() {
        try {
            token = tokenizer.nextToken();
            if (tokenEs(" ") || tokenEs("\n")) {
                getToken();
            }
            System.out.println("token: " + token);
        } catch (Exception e) {
            resultadoAnalisis = "No se encontro ;";
        }
    }

    public void secuencia() {
        getToken();
        do {
            expresion();
            while (!tokenEs(";")) {
                resultadoAnalisis = "ERROR... " + token;
                getToken();
                if (tokenEs("EOF")) {
                    return;
                }
            }
            getToken();
        } while (!tokenEs("EOF"));
    }

    private void expresion() {
        expresionSimple();
        if (esOperadorLogico(token)) {
            System.out.println("ENTRO");
            getToken();
            expresionSimple();
        }
    }

    private void expresionSimple() {
        if (tokenEs("+") || tokenEs("-")) {
            getToken();
        }
        termino();
        while (tokenEs("+") || tokenEs("-") || tokenEs("|")) {
            getToken();
            termino();
        }

    }

    private void termino() {
        factor();
        while (tokenEs("*") || tokenEs("/") || tokenEs("%") || tokenEs("&")) {
            getToken();
            factor();
        }
    }

    private void factor() {
        if (esIdentificador(token) || esNumero(token)) {
            getToken();
        } else if (tokenEs("!")) {
            getToken();
            factor();
        } else if (tokenEs("(")) {
            getToken();
            expresion();
            if (!tokenEs(")")) {
                resultadoAnalisis = "ERROR... " + token;
            } else {
                getToken();
            }
        } else {
            resultadoAnalisis = "ERROR... " + token;

        }

    }

    public boolean esIdentificador(String palabra) {
        return new Identificador(palabra).analizar();
    }

    public boolean tokenEs(String palabra) {
        return token.equals(palabra);
    }

    public boolean esNumero(String palabra) {
        return validarCadena(palabra, "[0-9]+");
    }

    public boolean esOperadorLogico(String palabra) {
        return validarCadena(palabra, "=|<|>|°|~|=|!");/////////////////////////
    }

    private boolean validarCadena(String cadena, String patron) {
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(cadena);
        return m.matches();
    }

    public class Identificador {

        String cadena;
        char simbolo;
        int sig = 0;
        int estado;
        public static final int ERROR = -1;

        Identificador(String cadena) {
            this.cadena = cadena + "~";
        }

        public boolean analizar() {

            estado = 1;
            simbolo = siguiente(cadena);
            w:
            while (!fin(simbolo)) {
                switch (estado) {
                    case 1: {
                        if (esletra(simbolo)) {
                            estado = 2;
                        } else {
                            estado = ERROR;
                        }
                        break;
                    }
                    case 2: {
                        if (esletra(simbolo)) {
                            estado = 2;
                        } else if (esNumero(simbolo + "")) {
                            estado = 2;
                        } else {

                            estado = ERROR;
                        }
                        break;
                    }
                }
                simbolo = siguiente(cadena);
            }
            return (estado == 2);
        }

        char siguiente(String cadena) {
            char caracter = cadena.charAt(sig);
            sig++;
            return caracter;
        }

        boolean fin(char caracter) {
            return validarCadena(caracter + "", "~");
        }

        boolean esletra(char caracter) {
            return validarCadena(caracter + "", "[a-zA-Z]");
        }

    }
    public static void main(String[] args) {
        String cadena = JOptionPane.showInputDialog("Ingresa la cadena");
        AnalizadorJava_1 analizador = new AnalizadorJava_1(cadena);
        JOptionPane.showMessageDialog(null, analizador.analizar());
    }
}
