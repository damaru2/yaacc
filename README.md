# YAACC
Yet Another Almost C Compiler

Compiler of a C like language implemented in Java using JLex and JCup for the lexical and syntactic analysis resp. 

It generates P-machine code in a the file called output.yap

## What you will find in this repository

- An example of output.yap.
- A Haskell implementation of a P-machine, so you can test the generated code. It is in the folder P-machine.
- A file named `AnalizadorLexicoTiny.l` with the lexical code that JLex accepts as input.
- Code for JCup with the syntactic rules of the language `Tiny.cup`.
- The required JCup and JLex libraries.
- The source code of the Java compiler implementation 
- Several examples of the C like language separated in two folders. One contains correct code, so the compiler successfully generate code for those, and the other contains lexical and syntactic errors so the compiler detects them and output descriptive error messages.
- A folder `lexico` to debug the lexical implementation. It's a slightly modified version of the final lexical that displays the recognized lexical tokens.

## About this project

This was a project for the subject Language Processors for the fourth course in the Double Degree in Mathematics and Computer Science at Complutense University of Madrid.

This project was done along with https://github.com/lezcano
It was marked with a 10/10
