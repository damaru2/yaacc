package main;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import program.Program;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import visitor.Visitor;
import alex.AnalizadorLexicoTiny;
import asint.AnalizadorSintacticoTiny;

public class Main{
	   public static void main(String[] args) throws Exception {
		   Reader input = new InputStreamReader(new FileInputStream("input.c"));
			 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			 ComplexSymbolFactory csf = new ComplexSymbolFactory();
			 AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex,csf);
			 try{
				 Symbol a = asint.parse();
				 Visitor visitor = new Visitor("output.yap");
				 ((Program) a.value).accept(visitor);

			 }catch(Exception e){
				 e.printStackTrace();
			 }
	   }
}

