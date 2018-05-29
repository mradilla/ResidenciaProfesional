package Modulo_II.Submodulos;

import Modulo_II.Submodulos.AnalizadorLexicon.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexicon {

    private int posToken = -1;
    private String cadena;
    private StringTokenizer tokenizer;
    private String token;
    private String resultadoAnalisis;
    ArrayList<String> evaluacion, tokens;
    ArrayList<Integer> posTokens;

    public AnalizadorLexicon(String cadena) {
        this.cadena = cadena + " EOF";
        evaluacion = new ArrayList<>();
        tokens = new ArrayList<>();
        posTokens = new ArrayList<>();
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

        cadena = cadena.replace("||", "|");
        cadena = cadena.replace("&&", "&");
        cadena = cadena.replace(">=", "°"); ///////////////////////7
        cadena = cadena.replace("<=", "~");/////////////////////////        
        cadena = cadena.replace("!=", "!");/////////////////////////
  
        return new StringTokenizer(cadena, " <>\n!\\(\\);+-*/%|&=°~", true);//////////////

    }

    public void getToken() {
        try {
            token = tokenizer.nextToken();
            if (!tokenEs(" ") && !tokenEs("\n")) {
                tokens.add(token);
                posToken++;
            }
            if (tokenEs(" ") || tokenEs("\n")) {
                getToken();
            }
        } catch (Exception e) {
            resultadoAnalisis = "No se encontro ;";
        }
    }

    public void secuencia() {
        agregarPaso("secuencia-inicio");
        getToken();
        agregarPaso("secuencia-get-1");
        do {
            agregarPaso("secuencia-do");
            agregarPaso("secuencia-exp");
            expresion();
            agregarPaso("secuencia-while-1");
            while (!tokenEs(";")) {
                agregarPaso("secuencia-error");
                resultadoAnalisis = "ERROR... " + token;
                getToken();
                agregarPaso("secuencia-get-2");
                agregarPaso("secuencia-llave-1");
                if (tokenEs("EOF")) {
                    return;
                }
                agregarPaso("secuencia-while-1");
            }
            getToken();
            agregarPaso("secuencia-get-3");
            agregarPaso("secuencia-while-2");
        } while (!tokenEs("EOF"));
        agregarPaso("secuencia-llave-2");
    }

    private void expresion() {
        agregarPaso("expresion-inicio");
        agregarPaso("expresion-es-1");
        expresionSimple();
        agregarPaso("expresion-if");
        if (esOperadorLogico(token)) {
            getToken();
            agregarPaso("expresion-get");
            agregarPaso("expresion-es-2");
            expresionSimple();
            agregarPaso("expresion-llave-1");
        }
        agregarPaso("expresion-llave-2");
    }

    private void expresionSimple() {
        agregarPaso("exp-simple-inicio");
        agregarPaso("exp-simple-if");
        if (tokenEs("+") || tokenEs("-")) {
            getToken();
            agregarPaso("exp-simple-get-1");

            agregarPaso("exp-simple-llave-1");
        }
        agregarPaso("exp-simple-termino-1");
        termino();
        agregarPaso("exp-simple-while");
        while (tokenEs("+") || tokenEs("-") || tokenEs("|")) {
            getToken();
            agregarPaso("exp-simple-get-2");
            agregarPaso("exp-simple-termino-2");
            termino();
            agregarPaso("exp-simple-llave-2");
            agregarPaso("exp-simple-while");
        }
        agregarPaso("exp-simple-llave-3");
    }

    private void termino() {
        agregarPaso("termino-inicio");
        agregarPaso("termino-factor-1");
        factor();
        agregarPaso("termino-while");
        while (tokenEs("*") || tokenEs("/") || tokenEs("%") || tokenEs("&")) {
            getToken();
            agregarPaso("termino-get");
            agregarPaso("termino-factor-2");
            factor();
            agregarPaso("termino-llave-1");
            agregarPaso("termino-while");
        }
        agregarPaso("termino-llave-2");

    }

    private void factor() {
        agregarPaso("factor-inicio");
        agregarPaso("factor-switch");
        agregarPaso("factor-case-id");
        if (esIdentificador(token)) {
            getToken();
            agregarPaso("factor-get-1");
            agregarPaso("factor-break-1");
        } else {
            agregarPaso("factor-case-num");
            if (esNumero(token)) {
                getToken();
                agregarPaso("factor-get-2");

                agregarPaso("factor-break-2");
            } else {
                agregarPaso("factor-case-not");
                if (tokenEs("NOT") || tokenEs("not")) {
                    getToken();
                    agregarPaso("factor-get-3");
                    agregarPaso("factor-factor");
                    factor();
                    agregarPaso("factor-break-3");
                } else {
                    agregarPaso("factor-case-abre");
                    if (tokenEs("(")) {
                        getToken();
                        agregarPaso("factor-get-4");
                        agregarPaso("factor-expresion");
                        expresion();
                        agregarPaso("factor-if");
                        if (!tokenEs(")")) {
                            agregarPaso("factor-error-1");
                            resultadoAnalisis = "ERROR... " + token;

                        } else {
                            agregarPaso("factor-else");
                            getToken();
                            agregarPaso("factor-get-5");

                        }
                        agregarPaso("factor-break-4");
                    } else {
                        agregarPaso("factor-default");
                        agregarPaso("factor-error-2");
                        resultadoAnalisis = "ERROR... " + token;
                    }
                }
            }
        }
        agregarPaso("factor-llave-2");

    }

    public boolean esIdentificador(String palabra) {
        if (tokenEs("NOT") || tokenEs("not")) {
            return false;
        }
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

    public ArrayList<String> getEvaluacion() {
        /* for (String a : evaluacion) {
            System.out.println(a);
        }
         */
        return evaluacion;
    }

    public ArrayList<String> getTokens() {

        return tokens;
    }

    public void agregarPaso(String e) {
        evaluacion.add(e);
        posTokens.add(posToken);
    }

}
