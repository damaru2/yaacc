package alex;
import java_cup.runtime.Symbol;

public abstract class UnidadLexica extends Symbol {
   private ClaseLexica clase;
   private int fila;
   public UnidadLexica(int fila, ClaseLexica clase) {
	 super(clase.ordinal(), null);
     this.fila = fila;
     this.clase = clase;
   }
   public UnidadLexica(int fila, ClaseLexica clase, String lexema) {
	 super(clase.ordinal(), lexema);
     this.fila = fila;
     this.clase = clase;
   }
   public ClaseLexica clase () {return clase;}
   public abstract String lexema();
   public int fila() {return fila;}
}
