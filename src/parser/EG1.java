/* Generated By:JavaCC: Do not edit this line. EG1.java */
package parser;
import composant.$Composant.niveau;
import composant.generateur.$Generateur;
import composant.transformateur.$Transformateur;
import composant.recepteur.$Recepteur;
import composant.generateur.*;
import composant.transformateur.*;
import composant.recepteur.*;
import composant.*;
import circuit.composite.*;
import circuit.*;
import circuit.Ferme;
import circuit.composite.Composite;

public class EG1 implements EG1Constants {
private static $Composant which_composant(String name, int num){
  switch (name){
        case "Itr" : return ($Composant) new Itr(name,num,niveau.Bas);
        case "Vcc" : return ($Composant) new Vcc(name,num);
        case "Gnd" : return ($Composant) new Ground(name,num);
        case "Led" : return ($Composant) new Led(name,num);
        case "Et" : return ($Composant) new Et(name,num);
        case "Non" : return ($Composant) new Non(name,num);
        case "Ou" : return ($Composant) new Ou(name,num);
        case "Oux" : return ($Composant) new Oux(name,num);
        default:
                return null; //Ca serait un composite definit avant
        }
}
  public static void main(String args []) throws ParseException
  {
    EG1 parser = new EG1(System.in);
    System.out.println("Entrer un circuit :" );
    Ferme circuit = DEF_CIRCUIT();
    System.out.println(circuit.toString2());
  }

  static final public Ferme CIRCUIT(Ferme res) throws ParseException {
        Token numt, numSt,numBt,numSBt,namt;
        int nbS = 0;
        int num, numS,numB,numSB;
        String name;
        $Composant comp;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 9:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(9);
      numt = jj_consume_token(NUM);
      jj_consume_token(10);
      namt = jj_consume_token(ID);
                        num  = Integer.parseInt(numt.image); //numero du composant
                        name = namt.image; //nom du composant
                        comp = which_composant(name,num);//type du composant

      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 11:
        jj_consume_token(11);
                         res.addComposant(comp);
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 12:
        jj_consume_token(12);
        numSt = jj_consume_token(NUM);
                         numS = Integer.parseInt(numSt.image);
        jj_consume_token(13);
        label_2:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case NUM:
            ;
            break;
          default:
            jj_la1[2] = jj_gen;
            break label_2;
          }
          //* pour chaque composant
                                          numBt = jj_consume_token(NUM);
          jj_consume_token(14);
          numSBt = jj_consume_token(NUM);
                                  numB = Integer.parseInt(numBt.image); //numero du composant dest
                                  numSB = Integer.parseInt(numSBt.image); //numero du port du composant dest
                                  comp.addSortie(numS-1,numB-1,numSB-1); //ajout de la sortie au composant		

          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 15:
            jj_consume_token(15);
            break;
          default:
            jj_la1[3] = jj_gen;
            ;
          }
        }
        jj_consume_token(16);
                          res.addComposant(comp);//ajout du composant

        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
    }
         {if (true) return res;}
    throw new Error("Missing return statement in function");
  }

/*
Composite COMPOSITE(List<Composite> lCF) : //lCF = liste composite final
{
	Token numt, numSt,numBt,numSBt,namt;
	int nbS = 0;
	int num, numS,numB,numSB; //num : numero du composant, numS : numero de sortie du composant, meme prefixe avec B : composant cible
	String name;
	Composite res;
	$Composant comp;
}
{
(
		"<" numt=<NUM> //numero composant token
		"|" namt=<ID> //nom du composant token
		{//ajout du composant a la liste des composant
		num  = Integer.parseInt(numt.image);
		name = namt.image;
		}
		//test si pas de sortie
		(
		    ">"
			{comp = new Led(name,num);//maj dans la liste
			res.addComposant(comp,num);
			}
			//Pas de donne a metre dans lCD
			//Ici il n'existe qu'un seul type de recepteur, sinon nous aurons qu'a faire un switch nam 
		)?
		
		//au moins une sortie
		(//*
			{
			comp = which_composant(name,num,LCF);
			res.addComposant(comp,num);//ajoute le composant a l'emplacement d'index num, composant encore inconnue
			}
			"->#"
			numSt=<NUM> //numero de sortie
			{numS = Integer.parseInt(numSt.image);}//maj du numero de sortie
			"("
			(//* pour chaque composant
				numBt=<NUM> "#" numSBt=<NUM> // numero du composant ; numero de sortie
				
				{
				  numB = Integer.parseInt(numBt.image);
				  numSB = Integer.parseInt(numSBt.image);
				  comp.addSortie(numS,numB,numSB);
				}
				(",")?
			)*
			")>"
		
		)*
)*

{lCF.add(res);}
}	
*/
  static final public Ferme DEF_CIRCUIT() throws ParseException {
  Token namet;
  String name;
  Ferme circuit = new Ferme();
  Composite compo;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 17:
      jj_consume_token(17);
      namet = jj_consume_token(ID);
                  circuit = CIRCUIT(circuit); //Je pense qu'on le lui passera en parametre, sinon on redescent les ligne cu-dessus, en dessous
                  name = namet.image;
                  circuit.setNom(name);
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(18);
         {if (true) return circuit;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public EG1TokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[6];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200,0x800,0x20,0x8000,0x1000,0x20000,};
   }

  /** Constructor with InputStream. */
  public EG1(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public EG1(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new EG1TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public EG1(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new EG1TokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public EG1(EG1TokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(EG1TokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 6; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 6; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
