
#include <iostream>
#include <regex>

using namespace std;
vector<string> Tokenize(const string& strInput);
vector<string> tokens;
regex id("[a-zA-Z][a-zA-Z0-9]+");
regex numero("[0-9]+");
regex op("=|<|>|#|~|=|!");

void getToken();
void secuencia();
void expresion();
void expresionSimple();
void termino();
void factor();
bool validarCadena(string simbolo, regex patron);
bool esIdentificador(string palabra);
bool tokenEs(string palabra);
bool esNumero(string palabra);
bool esOperadorLogico(string palabra);
std::string ReplaceString(std::string subject, const std::string& search, const std::string& replace);
int posicion;
string resultado, token;


int main() {

    string cadena;
    cout << "Ingresa la cadena...  ";
    getline(std::cin, cadena);
    cadena = cadena + " EOF";
    tokens = Tokenize(cadena);
    posicion = 0;
    secuencia();
    if (resultado == "") {
        resultado = "La sintaxis es correcta.";
    }
    cout << "\n" << resultado << "\n\n";

}

bool validarCadena(string simbolo, regex patron) {
    return (regex_match(simbolo, patron));
}

bool esIdentificador(string palabra) {
    return validarCadena(palabra, id);
}

bool tokenEs(string palabra) {
    return token == palabra;
}

bool esNumero(string palabra) {
    return validarCadena(palabra, numero);
}

bool esOperadorLogico(string palabra) {
    return validarCadena(palabra, op); /////////////////////////
}

std::string ReplaceString(std::string subject, const std::string& search,
        const std::string& replace) {
    size_t pos = 0;
    while ((pos = subject.find(search, pos)) != std::string::npos) {
        subject.replace(pos, search.length(), replace);
        pos += replace.length();
    }
    return subject;
}


void getToken() {
    if (posicion == tokens.size()) {
        resultado = "No se encontro ;";
        return;
    }

    token = tokens[posicion];

    //    cout << "\nposicion " << posicion << ": " << token;

    posicion++;
}

vector<string> Tokenize(const string& strInput) {
    vector<string> vS;

    string cadena = strInput;

    cadena = ReplaceString(cadena, "||", "|");
    cadena = ReplaceString(cadena, "&&", "&");
    cadena = ReplaceString(cadena, ">=", "#");
    cadena = ReplaceString(cadena, "<=", "~");
    cadena = ReplaceString(cadena, "!=", "!");

    string delimiters = " <>\n!\\(\\);+-*/%|&=#~";

    int startpos = 0;
    int pos = cadena.find_first_of(delimiters, startpos);

    while (string::npos != pos || string::npos != startpos) {


        if (cadena.substr(startpos, pos - startpos) != "") {
            vS.push_back(cadena.substr(startpos, pos - startpos));

        }
        if (cadena.substr(pos, 1) != " ") {
            vS.push_back(cadena.substr(pos, 1));
        }
        if (string::npos == cadena.find_first_not_of(delimiters, pos))
            startpos = cadena.find_first_not_of(delimiters, pos);
        else
            startpos = pos + 1;
        int ppos = pos;
        pos = cadena.find_first_of(delimiters, startpos);

        if (string::npos == pos || string::npos == startpos) {
            vS.push_back(cadena.substr(ppos + 1, ppos - startpos));
            break;
        }
    }

    return vS;
}

void secuencia() {
    getToken();
    do {
        expresion();
        while (!tokenEs(";")) {

            resultado = "ERROR... " + token;
            getToken();
            if (tokenEs("EOF")) {
                return;
            }
        }
        getToken();
    } while (!tokenEs("EOF"));
}

void expresion() {
    expresionSimple();
    if (esOperadorLogico(token)) {

        getToken();
        expresionSimple();
    }
}

void expresionSimple() {
    if (tokenEs("+") || tokenEs("-")) {
        getToken();
    }
    termino();
    while (tokenEs("+") || tokenEs("-") || tokenEs("|")) {
        getToken();
        termino();
    }

}

void termino() {
    factor();
    while (tokenEs("*") || tokenEs("/") || tokenEs("%") || tokenEs("&")) {
        getToken();
        factor();
    }
}

void factor() {
    if (esIdentificador(token) || esNumero(token)) {
        getToken();
    } else if (tokenEs("!")) {
        getToken();
        factor();
    } else if (tokenEs("(")) {
        getToken();
        expresion();
        if (!tokenEs(")")) {
            resultado = "ERROR... " + token;
        } else {
            getToken();
        }
    } else {
        resultado = "ERROR... " + token;
    }
}