package alex;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	public UnidadLexica unidadIdentificador(){
		return new UnidadLexicaMultivaluada(alex.fila(), ClaseLexica.IDEN ,alex.lexema() );
	}
	public UnidadLexica unidadNumeroEntero(){
		return new UnidadLexicaMultivaluada(alex.fila(), ClaseLexica.ENT , alex.lexema());
	}
	public UnidadLexica unidadOperadorSuma(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.MAS);
	}
	public UnidadLexica unidadOperadorResta(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.MENOS);
	}
	public UnidadLexica unidadOperadorMultiplicacion(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.POR);
	}
	public UnidadLexica unidadOperadorDivision(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.DIV);
	}
	public UnidadLexica unidadOperadorLE(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.LE);
	}
	public UnidadLexica unidadOperadorLT(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.LT);
	}
	public UnidadLexica unidadOperadorGE(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.GE);
	}
	public UnidadLexica unidadOperadorGT(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.GT);
	}	
	public UnidadLexica unidadParentesisApertura(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.P_AP);
	}
	public UnidadLexica unidadParentesisCierre(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.P_CIERRE);
	}
	public UnidadLexica unidadCorcheteApertura(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.C_AP);
	}
	public UnidadLexica unidadCorcheteCierre(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.C_CIERRE);
	}
	public UnidadLexica unidadLlaveApertura(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.LL_AP);
	}
	public UnidadLexica unidadLlaveCierre(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.LL_CIERRE);
	}
	public UnidadLexica unidadPorcentaje(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.PORCEN);
	}
	public UnidadLexica unidadIgual(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.IGUAL);
	}
	public UnidadLexica unidadComa(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.COMA);
	}
	public UnidadLexica unidadPuntoYComa(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.PUNTO_Y_COMA);
	}
	public UnidadLexica unidadOr(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.OR);
	}
	public UnidadLexica unidadAnd(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.AND);
	}
	public UnidadLexica unidadIgualBool(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.IGUAL_BOOL);
	}
	public UnidadLexica unidadDesigualBool(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.DIST_BOOL);
	}
	public UnidadLexica unidadNotBool(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.NOT_BOOL);
	}
	public UnidadLexica unidadMasMas(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.MASMAS);
	}
	public UnidadLexica unidadMenosMenos(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.MENOSMENOS);
	}
	public UnidadLexica unidadWhile(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.WHILE);
	}
	public UnidadLexica unidadIf(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.IF);
	}
	public UnidadLexica unidadElse(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.ELSE);
	}
	public UnidadLexica unidadFor(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.FOR);
	}
	public UnidadLexica unidadReturn(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.RETURN);
	}
	public UnidadLexica unidadConst(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.CONST);
	}
	public UnidadLexica unidadContinue(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.CONTINUE);
	}
	public UnidadLexica unidadBreak(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.BREAK);
	}
	public UnidadLexica unidadInt(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.INT);
	}
	public UnidadLexica unidadStruct(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.STRUCT);
	}
	public UnidadLexica unidadBool(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.BOOL);
	}
	public UnidadLexica unidadVoid(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.VOID);
	}
	public UnidadLexica unidadTrue(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.TRUE);
	}
	public UnidadLexica unidadFalse(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.FALSE);
	}
	public UnidadLexica unidadPunto(){
		return new UnidadLexicaUnivaluada(alex.fila(), ClaseLexica.PUNTO);
	}
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF); 
   }
	public void error() {
		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
    }
}
