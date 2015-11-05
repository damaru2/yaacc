#!/bin/bash

echo "Preconditions:"
echo "The files AnalizadorLexicoTiny.l, Tiny.cup must be in ./"
echo "The files jlex.jar, cup.jar must be placed must be in ./lib/"
echo ""

cd "$(dirname "$0")"
java -cp lib/jlex.jar JLex.Main AnalizadorLexicoTiny.l > /dev/null
rm ./src/alex/AnalizadorLexicoTiny.java
mv AnalizadorLexicoTiny.l.java ./src/alex/AnalizadorLexicoTiny.java

echo "[+] Lexic analizer compiled."
echo "Change the for in the function yy_mark_start() in the file src/alex/AnalizadorLexicoTiny.java for this one:

		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
				yycolumn = 0;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yycolumn = 0;
				yy_last_was_cr=true;
			} else {
				yy_last_was_cr=false;
				++yycolumn;
			}
		}
	"

# This part is for debugging purposes,
# feel free to comment this if you don't want to see the result of the lexical analizer
#========================================================
echo "[+] Lexic analizer result:"
echo ""
cd ./lexico
rm input.c
cp ../input.c ./input.c
./compileLex.sh

cd ..
echo ""
echo ""
#========================================================

echo "[*] Compiling sintactic analizer."
java -jar lib/cup.jar -dump_grammar -symbols ClaseLexica -parser AnalizadorSintacticoTiny -compact_red Tiny.cup &> .a.temp
a=`cat .a.temp | grep "\-\-\-\-\-\-\-" -A 1 | grep error | cut -d " " -f 3`
if [ $a != "0" ]; then
	echo "[!] There has been errors in the compilation of Tiny.cup"
	cat .a.temp
	rm .a.temp
	exit 1
fi

rm .a.temp
rm ./src/asint/AnalizadorSintacticoTiny.java
mv ./AnalizadorSintacticoTiny.java ./src/asint/AnalizadorSintacticoTiny.java
rm ./src/asint/ClaseLexica.java
mv ./ClaseLexica.java ./src/asint/ClaseLexica.java
echo "[+] Successfully built."
