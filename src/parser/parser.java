options{STATIC=false;}
PARSER_BEGIN(Reader)
package jus.aoo.boole.reader;

import jus.aoo.boole.circuit.*;

public class Reader extends ReaderUtilities {
	public _Circuit read() throws Exception {return DEF_CIRCUIT();}


PARSER_END(Reader)
SKIP :{" " | "\r" | "\t" | "\n" }
TOKEN :{
	<NUM: ( <DIGIT> )+ >
|	<ID: <LETTER> (<DIGIT> | <LETTER>)+ >
|	<#LETTER: ["a"-"z","A"-"Z"] >
|	<#DIGIT: ["0" - "9"] >
}

/*
_Circuit DEF_COMPOSANT() : {variables locales} {
	<COMPOSANT: "composant" <TETE> (<CORP>)+ <QUEU>
|	#TETE: <ID> "(" <NUM> "," <NUM> ")"
|	#CORP: "<" <NUM> "|" <ID>
*/

_Circuit CIRCUIT() : {
int nbS = 0;
int num, numS,numB,numSB;
string nam;
_Circuit res = _Circuit();;
}
{
	(//*
		"<" num=<NUM> "|" nam=<ID>

		(">"//? //pas de sortie
			{res.addComposant(Recepteur(nbS,num,nam));}
		)?
		//au moins une sortie
		(//*
		_Composant comp =Composant();
			"->"
				{nbS+=1;} 
			"#" numS=<NUM> "("
			(//* pour chaque composant
				numB=<NUM> "#" numSB=<NUM>
				(",")?
				comp.addSortie(numS,numB,numSB);
			)*
			")"
		res.addComposant(comp);
		)*
		">"
	)*
	//fini de lire la liste des composant du circuit
	{return res;}

}

_Circuit DEF_CIRCUIT() : { string name; _Circuit init} {
	(
		"circuit" name=<ID> init=CIRCUIT()
			{
			init.setName(name);
			return init;
			}
	)?
	//mal lu...
	{return null;}
}






