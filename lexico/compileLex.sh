#!/bin/bash
cd "$(dirname "$0")"
java -cp ../lib/jlex.jar JLex.Main AnalizadorLexicoTiny.l > /dev/null
mv AnalizadorLexicoTiny.l.java ./src/AnalizadorLexicoTiny.java
javac -cp ../lib/cup.jar -sourcepath src -d bin src/*.java
java -cp ../lib/cup.jar:bin alex.Main input.c
# In order to change something of the lexic analizer you have to modify the following files:
# 1 	lexico.l
# 2 	ClaseLexica.java (Add or modify the key word)
# 3 	AlexOperations.java (Add or modify the method asociated to the key word)
