package errors;

import alex.UnidadLexica;

public class GestionErroresTiny {
   public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+ ", columna " + columna + ": Caracter inexperado: "+lexema);
     System.exit(1);
   }
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR fila "+unidadLexica.xleft.getLine() + ", columna " + unidadLexica.xleft.getColumn() + ": Elemento inexperado "+unidadLexica.value);
     System.exit(1);
   }
}
