package alex;

import asint.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;
  }
  	public UnidadLexica unidadIdentificador(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDEN ,alex.lexema() );
	}
	public UnidadLexica unidadNumeroEntero(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENT ,alex.lexema() );
	}
	public UnidadLexica unidadOperadorSuma(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
	}
	public UnidadLexica unidadOperadorResta(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
	}
	public UnidadLexica unidadOperadorMultiplicacion(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*");
	}
	public UnidadLexica unidadOperadorDivision(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/");
	}
	public UnidadLexica unidadOperadorLE(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LE, "<=");
	}
	public UnidadLexica unidadOperadorLT(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LT, "<");
	}
	public UnidadLexica unidadOperadorGE(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.GE, ">=");
	}
	public UnidadLexica unidadOperadorGT(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.GT, ">");
	}
	public UnidadLexica unidadParentesisApertura(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.P_AP, "(");
	}
	public UnidadLexica unidadParentesisCierre(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.P_CIERRE, ")");
	}
	public UnidadLexica unidadCorcheteApertura(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.C_AP, "[");
	}
	public UnidadLexica unidadCorcheteCierre(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.C_CIERRE, "]");
	}
	public UnidadLexica unidadLlaveApertura(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LL_AP, "{");
	}
	public UnidadLexica unidadLlaveCierre(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LL_CIERRE, "}");
	}
	public UnidadLexica unidadPorcentaje(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PORCEN, "%");
	}
	public UnidadLexica unidadIgual(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "=");
	}
	public UnidadLexica unidadComa(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}
	public UnidadLexica unidadPuntoYComa(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA, ";");
	}
	public UnidadLexica unidadOr(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "||");
	}
	public UnidadLexica unidadAnd(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "&&");
	}
	public UnidadLexica unidadIgualBool(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL_BOOL, "==");
	}
	public UnidadLexica unidadDesigualBool(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIST_BOOL, "!=");
	}
	public UnidadLexica unidadNotBool(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT_BOOL, "!");
	}
	public UnidadLexica unidadMasMas(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MASMAS, "++");
	}
	public UnidadLexica unidadMenosMenos(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOSMENOS, "--");
	}
	public UnidadLexica unidadWhile(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "while");
	}
	public UnidadLexica unidadIf(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "if");
	}
	public UnidadLexica unidadElse(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "else");
	}
	public UnidadLexica unidadFor(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FOR, "for");
	}
	public UnidadLexica unidadReturn(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RETURN, "return");
	}
	public UnidadLexica unidadContinue(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CONTINUE, "continue");
	}
	public UnidadLexica unidadBreak(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BREAK, "break");
	}
	public UnidadLexica unidadInt(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "int");
	}
	public UnidadLexica unidadStruct(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "struct");
	}
	public UnidadLexica unidadBool(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "bool");
	}
	public UnidadLexica unidadVoid(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VOID, "void");
	}
	public UnidadLexica unidadTrue(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "true");
	}
	public UnidadLexica unidadFalse(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "false");
	}
	public UnidadLexica unidadPunto(){
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}
	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(),alex.columna(), ClaseLexica.EOF, "<EOF>");
   }
  /*
  public UnidadLexica unidadReal() {
     return new UnidadLexica(alex.fila(),ClaseLexica.REAL,alex.lexema());
  }
  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>");
  }*/
}
