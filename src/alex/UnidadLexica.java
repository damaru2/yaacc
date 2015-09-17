package alex;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class UnidadLexica extends ComplexSymbol {

   public UnidadLexica(int fila, int columna, int clase, String lexema) {
     super("name", clase, new Location(fila, columna), new Location(fila, columna), lexema);
   }
   public int clase () {return sym;}
   public String lexema() {return (String)value;}

}
