package alex;

%%
%line
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra 				= [A-Za-z]
digito				= [0-9]
parteEntera			= {digito}*
carIdentif			= ({letra}|_)

separador			= [ \t\r\b\n]
comentario			= (/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)
while				= while
if				= if
else				= else
for				= for
return				= return
continue			= continue
break				= break
int				= int
bool				= bool
void				= void
struct				= struct
true				= true
false				= false
identificador			= {carIdentif}({carIdentif}|{digito})*
numeroEntero			= {parteEntera}
masmas					= \+\+
menosmenos				= --
operadorSuma			= \+
operadorResta			= -
operadorMultiplicacion 		= \*
operadorDivision 		= /
operadorLE				= <=
operadorLT				= <
operadorGE				= >=
operadorGT				= >

parentesisApertura		= \(
parentesisCierre		= \)
corcheteApertura		= \[
corcheteCierre			= \]
llaveApertura			= \{
llaveCierre			= \}
porcentaje			= %
igualBool			= ==
igual				= =
coma 				= ,
punto				= \.
puntoYComa			= ;
or				= \|\|
and				= &&
desigualBool			= !=
notBool			= !



%%
{separador}					{}
{comentario}				{}
{while}						{return ops.unidadWhile();}
{if}						{return ops.unidadIf();}
{else}						{return ops.unidadElse();}
{for}						{return ops.unidadFor();}
{return}					{return ops.unidadReturn();}
{continue}					{return ops.unidadContinue();}
{break}						{return ops.unidadBreak();}
{int}						{return ops.unidadInt();}
{struct}					{return ops.unidadStruct();}
{bool}						{return ops.unidadBool();}
{void}						{return ops.unidadVoid();}
{true}						{return ops.unidadTrue();}
{false}						{return ops.unidadFalse();}
{identificador}				{return ops.unidadIdentificador();}
{numeroEntero}				{return ops.unidadNumeroEntero();}
{masmas}					{return ops.unidadMasMas();}
{menosmenos}				{return ops.unidadMenosMenos();}
{operadorSuma}				{return ops.unidadOperadorSuma();}
{operadorResta}				{return ops.unidadOperadorResta();}
{operadorMultiplicacion}	{return ops.unidadOperadorMultiplicacion();}
{operadorDivision}			{return ops.unidadOperadorDivision();}

{operadorLE}				{return ops.unidadOperadorLE();}
{operadorLT}				{return ops.unidadOperadorLT();}	
{operadorGE}			    {return ops.unidadOperadorGE();}
{operadorGT}			    {return ops.unidadOperadorGT();}

{parentesisApertura}		{return ops.unidadParentesisApertura();}
{parentesisCierre}			{return ops.unidadParentesisCierre();}
{corcheteApertura}			{return ops.unidadCorcheteApertura();}
{corcheteCierre}			{return ops.unidadCorcheteCierre();}
{llaveApertura}				{return ops.unidadLlaveApertura();}
{llaveCierre}				{return ops.unidadLlaveCierre();}
{porcentaje}				{return ops.unidadPorcentaje();}
{igualBool}					{return ops.unidadIgualBool();}
{igual}						{return ops.unidadIgual();}
{coma}						{return ops.unidadComa();}
{punto}						{return ops.unidadPunto();}
{puntoYComa}				{return ops.unidadPuntoYComa();}
{or}						{return ops.unidadOr();}
{and}						{return ops.unidadAnd();}
{desigualBool}				{return ops.unidadDesigualBool();}
{notBool}					{return ops.unidadNotBool();}

[^]                       {ops.error();}
