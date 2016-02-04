package de.vanitasvitae.enigmandroid.enigma.inputPreparer;

/**
 * Created by welli on 12/12/2015.
 */
public class Util {

    public static String getConnectionsArray(Integer[] args){

        String retorno = "\"";
        for (int i = 0; i < args.length ; i++){
            if(i < args.length-1) {
                retorno = retorno + args[i].intValue() + ".";
            }
            else{
                retorno = retorno+args[i]+"\"";
            }
        }
        return retorno;
    }

    public static String getConnectionsArray(Integer[] args,int n){

        String retorno = "";
        for (int i = 0; i < args.length ; i++){
            if(i < args.length-1) {
                retorno = retorno + args[i].intValue() + ".";
            }
            else{
                retorno = retorno+args[i]+"\"";
            }
        }
        return retorno;
    }

    public static String getConnectionsArray(Integer[] args,boolean n){

        String retorno = "\"";
        for (int i = 0; i < args.length ; i++){
            if(i < args.length-1) {
                retorno = retorno + args[i].intValue() + ".";
            }
            else{
                retorno = retorno+args[i];
            }
        }
        return retorno;
    }


    public static String getConnectionsArray(int[] args){

        String retorno = "\"";
        for (int i = 0; i < args.length ; i++){
            if(i < args.length-1) {
                retorno = retorno + args[i] + ".";
            }
            else{
                retorno = retorno+args[i]+"\"";
            }
        }
        return retorno;
    }


    public static String GIANTSTRING = "lorem laoreet elit tincidunt dolor diam nibh consectetuer sed dolore aliquam dolore adipiscing dolore erat euismod erat \n" +
            "dolore elit nibh magna sit diam magna dolore sed nibh tincidunt laoreet erat lorem aliquam euismod nibh nibh nibh elit magna \n" +
            "ipsum amet erat amet sit magna magna laoreet laoreet adipiscing magna erat lorem diam nibh diam ipsum euismod sit sit lorem \n" +
            "ut ut amet tincidunt tincidunt aliquam nibh nibh nonummy sed nonummy consectetuer euismod sed dolore diam lorem consectetuer \n" +
            "erat consectetuer ut amet euismod euismod adipiscing sit consectetuer ut aliquam dolore adipiscing dolore dolore amet ipsum \n" +
            "sit euismod nonummy sit laoreet aliquam diam elit nibh. dolor euismod tincidunt lorem diam nonummy consectetuer euismod magna \n" +
            "adipiscing sit magna dolor dolor erat elit euismod consectetuer dolor sit diam ut euismod sit elit laoreet nonummy laoreet \n" +
            "nibh sit lorem tincidunt diam euismod erat euismod sed magna sed nonummy erat dolore lorem diam aliquam sed dolore adipiscing \n" +
            "dolor sit adipiscing ut laoreet sit erat euismod euismod sit tincidunt sit adipiscing ipsum diam tincidunt adipiscing dolor \n" +
            "tincidunt ut ipsum ut lorem lorem laoreet nonummy adipiscing laoreet lorem amet sit euismod laoreet dolor consectetuer nibh \n" +
            "diam lorem tincidunt erat lorem dolore lorem dolore diam laoreet sit laoreet ipsum sit diam amet magna nibh ipsum. magna \n" +
            "consectetuer euismod laoreet elit tincidunt dolor amet dolore euismod ut aliquam dolore dolor lorem nibh euismod dolor nibh \n" +
            "sit lorem ipsum dolor magna tincidunt magna erat consectetuer amet nonummy sit sed dolor nonummy consectetuer magna \n" +
            "consectetuer adipiscing elit euismod sed sed sit ut euismod sed sit dolore nibh nibh diam magna magna nibh ut sit diam ut \n" +
            "magna ut diam ipsum elit elit nibh aliquam elit sed dolor tincidunt dolor dolor magna euismod ipsum ipsum elit adipiscing \n" +
            "nonummy sed laoreet nonummy euismod erat adipiscing diam consectetuer diam nibh tincidunt nibh lorem nibh ut diam \n" +
            "consectetuer sed dolore consectetuer aliquam euismod elit sit. dolor aliquam sit laoreet sit tincidunt nibh dolore erat sed \n" +
            "euismod consectetuer diam erat euismod ut lorem ipsum aliquam sed dolor laoreet adipiscing amet laoreet sed sed dolor diam \n" +
            "ipsum ipsum nibh dolore tincidunt ut sit nibh nonummy magna tincidunt ipsum sed lorem amet elit euismod diam adipiscing \n" +
            "laoreet tincidunt euismod laoreet aliquam laoreet elit consectetuer laoreet sit sit euismod erat euismod ipsum tincidunt \n" +
            "tincidunt nibh dolore sit nonummy elit erat ut nibh diam lorem lorem tincidunt aliquam aliquam sit aliquam elit amet \n" +
            "adipiscing ut magna ut euismod dolore sit consectetuer consectetuer consectetuer ipsum magna aliquam dolore sit aliquam magna \n" +
            "amet laoreet consectetuer. sit dolore amet euismod erat nonummy sed consectetuer tincidunt adipiscing lorem diam sit laoreet \n" +
            "elit amet elit aliquam ut ipsum dolor adipiscing sed nibh consectetuer magna sit sed laoreet diam diam elit nibh consectetuer \n" +
            "amet diam dolore elit diam euismod dolor ut euismod dolore sit dolore sit nibh euismod dolor consectetuer lorem erat magna \n" +
            "diam magna erat magna sed consectetuer ut magna laoreet tincidunt dolor ipsum adipiscing sit ut sed laoreet consectetuer \n" +
            "consectetuer dolor elit amet laoreet erat dolore consectetuer sit adipiscing elit ipsum dolore ipsum diam ipsum elit nonummy \n" +
            "magna diam ut nibh consectetuer sit magna lorem dolor tincidunt sit magna tincidunt. laoreet sit aliquam lorem aliquam diam \n" +
            "dolor erat lorem dolor ipsum consectetuer magna tincidunt sit sit diam magna aliquam laoreet ipsum sed dolore diam ipsum sit \n" +
            "nonummy erat nibh ipsum magna nibh consectetuer adipiscing elit amet magna nonummy aliquam nonummy adipiscing lorem dolore \n" +
            "erat laoreet aliquam sit nonummy lorem ut tincidunt aliquam consectetuer tincidunt dolore euismod erat amet erat nonummy \n" +
            "nonummy adipiscing tincidunt aliquam ipsum ut amet laoreet dolor ipsum sit elit consectetuer aliquam magna lorem amet sit \n" +
            "tincidunt dolore diam tincidunt magna magna euismod lorem elit laoreet tincidunt diam sed erat adipiscing euismod ipsum amet \n" +
            "adipiscing tincidunt ipsum lorem lorem euismod magna. dolore sit aliquam nibh tincidunt nibh erat dolore amet dolor aliquam \n" +
            "sed tincidunt elit lorem amet magna sit consectetuer dolore ipsum adipiscing dolore ipsum erat elit ut elit lorem sed aliquam \n" +
            "nibh laoreet dolor lorem dolor ut diam amet ipsum erat aliquam dolor diam nonummy magna magna ipsum ut consectetuer magna sed \n" +
            "magna laoreet adipiscing diam dolor aliquam nonummy ipsum aliquam tincidunt sit nonummy elit lorem sit nonummy nonummy \n" +
            "adipiscing sed lorem elit ipsum sed magna nibh dolor ut euismod nonummy sit euismod adipiscing nibh dolore ut dolore diam \n" +
            "adipiscing laoreet elit erat sed nonummy ut euismod dolore sit lorem nonummy magna ipsum. tincidunt dolor dolor magna erat \n" +
            "magna euismod consectetuer aliquam diam dolor aliquam amet dolor magna amet sit amet laoreet diam consectetuer elit magna \n" +
            "lorem erat laoreet diam ipsum erat aliquam dolor magna sit ut laoreet amet nibh dolore euismod euismod adipiscing erat dolore \n" +
            "lorem adipiscing sit euismod sit dolor laoreet ipsum sit dolore nonummy nibh adipiscing euismod aliquam magna sed dolore \n" +
            "tincidunt dolor consectetuer sed diam elit ut tincidunt laoreet diam sed adipiscing diam dolor adipiscing nonummy dolor nibh \n" +
            "tincidunt ipsum sit elit diam nibh erat adipiscing aliquam laoreet sit nibh lorem magna lorem ipsum magna aliquam nonummy \n" +
            "adipiscing aliquam nonummy nibh dolore. diam dolore ut amet aliquam adipiscing dolor adipiscing lorem elit dolor elit amet \n" +
            "dolor elit laoreet magna nonummy lorem sit nonummy lorem aliquam adipiscing tincidunt ut adipiscing amet dolore magna dolore \n" +
            "sed dolore ipsum adipiscing laoreet dolor magna euismod nonummy adipiscing aliquam dolor sed laoreet dolor ipsum adipiscing \n" +
            "elit ipsum laoreet magna tincidunt diam tincidunt aliquam tincidunt sit nibh diam tincidunt nonummy magna nibh sit amet ipsum \n" +
            "magna dolor erat elit nonummy consectetuer sed ut ut aliquam tincidunt aliquam dolor dolore adipiscing euismod lorem nonummy \n" +
            "dolor elit lorem ipsum elit adipiscing consectetuer amet diam elit tincidunt aliquam nibh elit nibh diam diam ipsum. elit \n" +
            "aliquam diam dolor tincidunt aliquam dolor adipiscing dolore euismod elit diam ut amet ipsum lorem sed dolore lorem lorem \n" +
            "dolore dolor sit adipiscing laoreet laoreet amet sed aliquam nibh aliquam nibh euismod dolor erat erat diam nonummy ut sed \n" +
            "sit erat dolor sit adipiscing dolore magna ipsum amet dolore sit euismod aliquam ut laoreet magna diam tincidunt lorem diam \n" +
            "euismod tincidunt dolor amet adipiscing sit euismod erat dolor nibh laoreet ut lorem euismod ipsum erat sed ipsum adipiscing \n" +
            "ut ipsum euismod dolore dolor nibh euismod tincidunt magna dolor lorem laoreet euismod sit adipiscing dolor ut magna aliquam \n" +
            "adipiscing elit diam amet nibh. diam adipiscing ipsum lorem elit dolore laoreet ipsum sit ut erat nonummy ut sit diam \n" +
            "adipiscing ipsum dolor nibh diam elit magna erat dolore aliquam amet dolor laoreet sed lorem adipiscing diam amet aliquam \n" +
            "dolore diam tincidunt aliquam sit magna sit sed aliquam sit lorem dolor dolore magna erat dolor amet tincidunt magna lorem ut \n" +
            "dolor amet dolore ipsum elit magna sed dolore aliquam consectetuer erat elit dolor sed nonummy lorem dolor elit dolor ut \n" +
            "magna ipsum tincidunt adipiscing nonummy amet adipiscing ipsum amet aliquam dolor nonummy dolore elit amet dolor euismod \n" +
            "adipiscing ipsum dolor amet amet ut amet sed ipsum lorem erat. lorem aliquam adipiscing consectetuer tincidunt laoreet ut ut \n" +
            "tincidunt erat tincidunt nibh aliquam dolor tincidunt diam sed euismod amet magna dolore nibh laoreet magna nonummy nibh \n" +
            "laoreet dolore elit aliquam euismod nonummy sed magna sed lorem ut sed adipiscing euismod elit dolor lorem ut laoreet \n" +
            "adipiscing sed elit lorem adipiscing magna magna dolore magna erat dolore amet euismod dolore erat sit lorem nibh euismod \n" +
            "magna euismod tincidunt elit aliquam elit magna magna laoreet dolor ut ut sit lorem adipiscing amet erat ut laoreet laoreet \n" +
            "euismod ut nibh sit sit sit amet tincidunt elit sed erat nibh nibh nibh dolore ut erat euismod lorem. ipsum ipsum nibh nibh \n" +
            "erat magna aliquam consectetuer consectetuer dolor amet ipsum euismod sed amet lorem laoreet adipiscing ut consectetuer \n" +
            "nonummy lorem ipsum magna elit nibh magna aliquam amet aliquam laoreet consectetuer dolor dolor lorem ipsum consectetuer ut \n" +
            "erat sed nonummy sed erat amet aliquam sit aliquam nibh dolore magna sit sit aliquam dolor sit tincidunt euismod sed elit sit \n" +
            "laoreet elit consectetuer tincidunt nonummy lorem adipiscing consectetuer sed aliquam sit dolor consectetuer magna \n" +
            "consectetuer sit dolor aliquam sed nonummy euismod euismod ut erat tincidunt diam ut lorem sit dolor lorem nonummy dolor \n" +
            "aliquam amet magna magna dolor dolore nonummy sed diam aliquam. magna tincidunt magna erat laoreet sed ipsum ut nonummy \n" +
            "adipiscing sed diam tincidunt dolor diam ut lorem amet dolor consectetuer lorem sed ut adipiscing sit adipiscing diam laoreet \n" +
            "aliquam sed adipiscing magna dolore laoreet euismod lorem euismod sed sed amet dolore laoreet amet ut lorem laoreet \n" +
            "consectetuer dolore aliquam nonummy adipiscing adipiscing euismod nonummy amet nonummy diam tincidunt laoreet nibh magna \n" +
            "nonummy ut nonummy sit tincidunt erat ut lorem aliquam erat nonummy dolor ipsum tincidunt consectetuer tincidunt adipiscing \n" +
            "ut dolor erat diam euismod ut adipiscing tincidunt sed lorem magna laoreet sit elit lorem euismod consectetuer erat nonummy \n" +
            "nibh consectetuer adipiscing ut nonummy magna. magna nonummy dolore tincidunt dolore sit tincidunt sed ut consectetuer ipsum \n" +
            "lorem dolor dolore elit dolor sed magna nibh consectetuer sed dolor nibh elit erat tincidunt euismod sit erat amet elit erat \n" +
            "lorem ut ut ut lorem sit dolor elit sit consectetuer sit dolore nonummy nibh ipsum magna aliquam elit magna consectetuer \n" +
            "aliquam dolore euismod dolore adipiscing dolore dolore amet aliquam aliquam erat ipsum erat lorem dolor elit amet dolore \n" +
            "nonummy adipiscing magna euismod nibh adipiscing diam ut nibh sit sit diam adipiscing lorem consectetuer nonummy ipsum elit \n" +
            "sed laoreet laoreet lorem erat magna amet sit nonummy elit magna diam ut sit diam. sit lorem amet dolore magna euismod ut \n" +
            "magna nonummy ut diam consectetuer ipsum sed nibh sit diam diam ut dolor laoreet consectetuer diam euismod sed magna ut \n" +
            "tincidunt aliquam elit elit elit sed consectetuer erat ut consectetuer elit dolor elit sit ut magna elit lorem elit nibh \n" +
            "dolor dolor ut ipsum diam tincidunt consectetuer ipsum tincidunt consectetuer sed elit nonummy amet sit ipsum sed tincidunt \n" +
            "nonummy nibh dolor sit ipsum elit ipsum tincidunt diam lorem nibh adipiscing amet euismod consectetuer nonummy consectetuer \n" +
            "dolore elit erat laoreet aliquam dolore aliquam lorem ipsum nonummy erat consectetuer euismod euismod dolore magna dolor nibh \n" +
            "adipiscing nibh lorem. aliquam adipiscing aliquam adipiscing aliquam dolor ipsum sit dolor amet amet nibh sed lorem aliquam \n" +
            "nibh consectetuer laoreet euismod laoreet dolore consectetuer nonummy consectetuer nibh erat tincidunt laoreet diam aliquam \n" +
            "magna laoreet lorem euismod dolor aliquam erat dolore nonummy aliquam amet nonummy adipiscing aliquam diam consectetuer erat \n" +
            "amet euismod amet laoreet nibh magna euismod magna laoreet euismod euismod nonummy magna diam sed dolor lorem ipsum dolore \n" +
            "laoreet nonummy adipiscing ipsum consectetuer diam adipiscing aliquam erat diam magna amet adipiscing erat diam lorem nibh \n" +
            "dolore erat erat lorem nibh diam dolore amet ut ut elit nibh euismod erat nonummy elit tincidunt dolore sit lorem. laoreet \n" +
            "dolor amet erat dolore sed erat ut diam dolore amet amet aliquam ut adipiscing euismod magna amet nonummy erat magna ipsum \n" +
            "laoreet sed nibh euismod diam sed nibh euismod ipsum amet consectetuer adipiscing adipiscing erat sit elit aliquam euismod \n" +
            "ipsum tincidunt lorem euismod diam consectetuer nonummy amet lorem adipiscing tincidunt lorem magna ipsum dolore lorem dolore \n" +
            "magna ut lorem lorem nonummy euismod dolore ut laoreet nonummy magna nibh tincidunt nibh nibh sed erat lorem sed lorem amet \n" +
            "nonummy dolor amet euismod dolore consectetuer euismod elit elit dolore sit adipiscing magna ut sit laoreet tincidunt \n" +
            "tincidunt elit nonummy dolor elit nonummy erat euismod. consectetuer consectetuer amet nibh magna consectetuer lorem nonummy \n" +
            "sit laoreet dolore dolore euismod tincidunt magna laoreet sed tincidunt sit aliquam laoreet aliquam sit magna adipiscing nibh \n" +
            "consectetuer euismod dolore ipsum dolore euismod amet dolor nonummy elit dolor laoreet euismod elit nonummy aliquam sit \n" +
            "consectetuer dolore lorem ut euismod elit nonummy euismod tincidunt ipsum dolore tincidunt erat magna nibh ipsum sed sed \n" +
            "magna ipsum erat amet laoreet laoreet aliquam elit nonummy amet amet sed sit magna nonummy tincidunt tincidunt elit laoreet \n" +
            "dolor diam consectetuer tincidunt tincidunt diam consectetuer lorem dolore laoreet nonummy dolore elit sed tincidunt \n" +
            "adipiscing aliquam consectetuer ipsum diam lorem adipiscing consectetuer. magna tincidunt adipiscing dolor magna sit dolore \n" +
            "laoreet dolor dolore nibh tincidunt euismod dolore magna sit nibh sit ut erat aliquam tincidunt sed euismod sed nonummy erat \n" +
            "magna nibh consectetuer erat nonummy aliquam tincidunt adipiscing ipsum amet laoreet sed sit laoreet laoreet sit dolor dolore \n" +
            "diam lorem euismod euismod nibh euismod aliquam nibh erat elit erat ipsum dolore aliquam erat adipiscing ut diam amet amet \n" +
            "laoreet sed euismod euismod elit laoreet consectetuer dolor tincidunt adipiscing nibh consectetuer amet erat dolore laoreet \n" +
            "dolore nonummy dolor nibh erat nonummy diam dolor elit dolor laoreet euismod amet consectetuer ut ut erat nibh ut magna ipsum \n" +
            "euismod. dolore diam nonummy elit sit diam magna sed ut magna ut lorem nonummy dolor elit nonummy ut tincidunt amet aliquam \n" +
            "tincidunt dolor aliquam ipsum diam euismod erat dolore nonummy aliquam sed elit diam dolor sit erat laoreet dolor euismod \n" +
            "dolore adipiscing amet amet tincidunt amet sit adipiscing ipsum elit euismod adipiscing nonummy magna sit amet dolor euismod \n" +
            "ut ipsum adipiscing tincidunt diam sed lorem nibh euismod sed magna amet ipsum nonummy laoreet sed laoreet sit ipsum aliquam \n" +
            "laoreet euismod tincidunt dolore elit laoreet sit dolor tincidunt nonummy sed nibh ipsum nonummy ut dolore euismod tincidunt \n" +
            "sed elit sed tincidunt aliquam lorem aliquam magna. magna dolor erat laoreet ipsum ipsum dolor dolor diam elit dolore lorem \n" +
            "magna dolor adipiscing amet erat elit lorem consectetuer lorem tincidunt sed sed diam dolore consectetuer consectetuer magna \n" +
            "lorem magna erat laoreet magna dolor sit tincidunt consectetuer amet dolor dolore laoreet magna euismod nibh nibh erat \n" +
            "aliquam lorem laoreet euismod nibh nonummy diam euismod nibh sed adipiscing erat magna elit sed aliquam nibh sit laoreet sit \n" +
            "lorem euismod nonummy consectetuer adipiscing sed consectetuer diam consectetuer adipiscing elit euismod consectetuer \n" +
            "tincidunt elit nibh lorem nibh lorem adipiscing lorem tincidunt dolor aliquam dolore dolore elit sed ipsum dolor laoreet \n" +
            "dolor ipsum erat sed sed. sed dolore sit tincidunt euismod diam euismod diam nibh nibh sed elit elit consectetuer dolor sed \n" +
            "aliquam lorem elit sit erat sit sit sed ipsum lorem ut elit laoreet consectetuer laoreet aliquam adipiscing laoreet amet \n" +
            "euismod amet magna nibh consectetuer aliquam ut nonummy laoreet ipsum sed laoreet elit ipsum consectetuer ut dolore \n" +
            "adipiscing lorem laoreet aliquam dolore dolor elit elit nonummy elit nibh erat elit nibh diam adipiscing nonummy diam lorem \n" +
            "aliquam diam erat laoreet lorem adipiscing dolor laoreet laoreet tincidunt magna ut dolore dolore sed nibh magna dolore \n" +
            "nonummy aliquam ipsum dolor amet dolore laoreet amet sit ipsum elit aliquam dolor euismod. diam dolore lorem aliquam lorem \n" +
            "magna lorem consectetuer sit adipiscing nonummy erat sed adipiscing laoreet dolor erat sit nonummy sed nonummy nonummy diam \n" +
            "tincidunt diam nibh nibh diam sit euismod elit amet magna elit nibh amet ipsum elit dolore sit ipsum diam tincidunt aliquam \n" +
            "aliquam sed sed diam dolor sed euismod lorem euismod elit adipiscing amet dolor consectetuer sit amet nibh laoreet \n" +
            "consectetuer sit tincidunt sit ut sit nibh nibh ut laoreet nibh euismod erat adipiscing tincidunt ut amet ipsum elit sit \n" +
            "nonummy sit tincidunt euismod ipsum erat nonummy elit adipiscing nonummy ut sit lorem lorem euismod dolor erat laoreet \n" +
            "euismod erat consectetuer. dolor dolor tincidunt ut lorem ipsum nibh ut lorem magna amet adipiscing euismod consectetuer \n" +
            "adipiscing magna erat consectetuer dolore aliquam euismod diam adipiscing laoreet nibh elit nonummy sed dolor adipiscing \n" +
            "ipsum nonummy tincidunt ipsum erat amet erat sit sit sit elit aliquam elit nonummy euismod aliquam consectetuer euismod magna \n" +
            "erat euismod nonummy nonummy erat adipiscing aliquam sit dolor adipiscing laoreet elit euismod diam euismod ut dolor nonummy \n" +
            "nibh elit ipsum amet dolor adipiscing amet diam sed nonummy nonummy amet adipiscing elit erat sit nonummy dolor sed dolore \n" +
            "aliquam adipiscing ipsum ut ut sit elit lorem amet dolor erat adipiscing diam magna dolor sed. amet ipsum dolore ipsum \n" +
            "nonummy adipiscing sed sed euismod adipiscing adipiscing tincidunt adipiscing dolor lorem sit nibh ut sit ut erat euismod \n" +
            "adipiscing nonummy magna tincidunt ipsum sit lorem euismod laoreet erat lorem dolore dolor ut nibh ipsum dolore tincidunt ut \n" +
            "aliquam euismod laoreet ut aliquam nibh lorem sed lorem adipiscing sit diam aliquam tincidunt elit euismod elit dolore \n" +
            "nonummy ut consectetuer aliquam erat nibh dolor nonummy aliquam dolor aliquam nibh aliquam erat nibh diam erat dolor euismod \n" +
            "aliquam sit consectetuer consectetuer amet elit erat nibh amet nonummy euismod dolore laoreet ut magna nibh nonummy aliquam \n" +
            "lorem ipsum sit amet ipsum diam euismod. lorem lorem euismod lorem elit ut sit lorem elit amet laoreet magna amet laoreet ut \n" +
            "sit sed ut erat sed laoreet sit ut nibh euismod dolore sed dolor ipsum elit laoreet elit elit amet sit aliquam aliquam \n" +
            "laoreet lorem nonummy tincidunt nonummy elit elit magna sed sed erat lorem nonummy ut magna magna adipiscing laoreet \n" +
            "consectetuer nibh nonummy ut nibh erat sed dolore amet dolor dolore nibh sed sed nibh diam ut dolore consectetuer diam sed \n" +
            "nonummy adipiscing erat erat dolore nonummy elit elit dolore lorem ut magna tincidunt erat lorem ut dolore laoreet dolor sed \n" +
            "nonummy nibh laoreet laoreet diam laoreet dolor. dolor nonummy consectetuer euismod ipsum sit ipsum elit lorem magna ipsum \n" +
            "amet dolor dolore amet ipsum adipiscing dolor nibh nibh sit aliquam nonummy erat dolor elit adipiscing euismod dolor ut \n" +
            "laoreet dolor ut magna diam aliquam adipiscing dolore dolore elit lorem dolor tincidunt erat ipsum adipiscing dolor aliquam \n" +
            "tincidunt sed consectetuer tincidunt tincidunt elit amet laoreet nibh lorem euismod sit laoreet diam nibh magna euismod diam \n" +
            "dolor adipiscing elit ut diam nibh diam ipsum erat ipsum ipsum euismod consectetuer consectetuer euismod lorem adipiscing sit \n" +
            "consectetuer aliquam magna diam tincidunt dolor aliquam adipiscing erat euismod dolore sed dolor amet erat erat lorem erat \n" +
            "laoreet. sed nibh elit dolor adipiscing dolor sed laoreet diam magna diam tincidunt magna elit lorem tincidunt diam nonummy \n" +
            "tincidunt erat ut nonummy aliquam sed adipiscing sit sit diam erat dolore tincidunt dolor tincidunt dolore sit nonummy nibh \n" +
            "dolore diam sit dolor sed diam nonummy nibh sit elit lorem amet diam sed sed laoreet lorem nibh lorem laoreet laoreet magna \n" +
            "euismod lorem sed sit aliquam ut adipiscing dolor amet tincidunt elit sit tincidunt magna sit consectetuer ut diam sed amet \n" +
            "dolor tincidunt elit dolore lorem ipsum diam sit diam dolore elit lorem laoreet nibh amet ipsum elit euismod dolore amet sed \n" +
            "nibh ut dolor. ipsum ipsum euismod magna ipsum dolor erat sed nibh consectetuer ut magna amet euismod lorem nibh tincidunt \n" +
            "tincidunt adipiscing ipsum nonummy sed elit erat diam dolor ut diam magna lorem ut sit nibh aliquam nonummy dolore aliquam \n" +
            "dolore sed ut nibh erat euismod dolor dolore laoreet aliquam ipsum dolor euismod amet sed nonummy elit erat ut dolore diam \n" +
            "consectetuer lorem amet lorem sit sed laoreet ipsum tincidunt sit dolor ipsum nibh nonummy ut laoreet ipsum lorem sit euismod \n" +
            "nibh erat nonummy lorem nonummy euismod erat euismod aliquam euismod euismod lorem dolore ipsum sed adipiscing consectetuer \n" +
            "nibh ipsum dolor adipiscing magna dolore nibh amet. dolore sed ut sit sit ipsum consectetuer laoreet elit nibh adipiscing \n" +
            "aliquam nonummy dolore nibh ipsum nibh dolor euismod elit ut nonummy lorem euismod nibh magna sit euismod dolore tincidunt \n" +
            "euismod adipiscing euismod consectetuer sit amet adipiscing erat sed magna elit sit sed dolor diam consectetuer euismod \n" +
            "dolore dolor lorem sed nibh consectetuer ipsum dolore laoreet sed nibh erat nibh tincidunt euismod consectetuer dolor erat \n" +
            "magna laoreet nibh ut consectetuer erat nonummy erat lorem diam adipiscing aliquam sit sit adipiscing consectetuer elit amet \n" +
            "ut erat aliquam sit laoreet dolor erat elit nibh laoreet ut elit sed erat diam diam magna dolor aliquam elit. adipiscing \n" +
            "consectetuer sed dolor euismod tincidunt nonummy laoreet euismod euismod elit ipsum nonummy sit tincidunt adipiscing amet \n" +
            "consectetuer ipsum tincidunt tincidunt lorem erat dolore lorem nonummy laoreet consectetuer amet sit elit diam tincidunt \n" +
            "dolor diam nonummy adipiscing adipiscing ipsum nonummy ut euismod lorem dolor laoreet adipiscing magna sit ut aliquam ipsum \n" +
            "amet lorem ipsum aliquam tincidunt ipsum laoreet dolore sed dolor diam elit sit aliquam elit aliquam adipiscing elit euismod \n" +
            "consectetuer dolor euismod erat laoreet erat erat lorem adipiscing erat lorem sed laoreet magna amet magna laoreet diam sed \n" +
            "nonummy ipsum aliquam erat lorem erat nonummy laoreet nibh ut magna dolore amet consectetuer. magna adipiscing erat nonummy \n" +
            "tincidunt lorem dolore diam dolor laoreet adipiscing elit laoreet dolor magna erat dolor tincidunt laoreet sed consectetuer \n" +
            "consectetuer nibh adipiscing diam dolor tincidunt sit nibh sed consectetuer aliquam diam nibh laoreet nonummy amet dolor sit \n" +
            "nonummy nibh laoreet nonummy ut sit tincidunt magna ut elit amet erat adipiscing nibh nonummy nibh diam adipiscing amet lorem \n" +
            "dolore ipsum sit euismod sit elit consectetuer elit dolore dolor elit consectetuer euismod dolore sed sit adipiscing dolore \n" +
            "diam consectetuer nibh elit diam dolore amet dolore tincidunt laoreet elit amet lorem consectetuer magna nonummy consectetuer \n" +
            "ipsum tincidunt nibh dolor laoreet aliquam adipiscing magna consectetuer. nibh euismod nonummy adipiscing lorem ut elit \n" +
            "dolore aliquam euismod elit lorem lorem erat nonummy dolore consectetuer adipiscing dolore diam tincidunt elit erat erat erat \n" +
            "consectetuer dolor sit adipiscing diam laoreet dolore euismod adipiscing sed consectetuer ipsum aliquam magna laoreet lorem \n" +
            "euismod sit adipiscing sed dolore dolor ut dolor adipiscing ut tincidunt aliquam nibh sit laoreet aliquam magna erat erat \n" +
            "aliquam consectetuer consectetuer diam elit lorem erat ut laoreet laoreet consectetuer magna tincidunt tincidunt magna \n" +
            "aliquam adipiscing lorem euismod tincidunt elit dolor elit laoreet erat adipiscing lorem tincidunt ut nonummy sit euismod sed \n" +
            "ut dolore aliquam nibh dolore erat euismod erat aliquam nibh. elit amet ipsum diam dolore sed dolor euismod amet sit euismod \n" +
            "elit tincidunt amet ipsum sit consectetuer tincidunt ut elit ipsum aliquam ut laoreet ipsum magna erat amet nibh nonummy \n" +
            "dolor diam magna ut magna sit dolore amet dolor laoreet elit laoreet dolor tincidunt nonummy ipsum sit dolor ut ut euismod \n" +
            "sit adipiscing adipiscing euismod dolor dolor dolore nonummy magna erat ut laoreet tincidunt adipiscing aliquam tincidunt ut \n" +
            "elit euismod ipsum dolore euismod laoreet tincidunt elit nonummy aliquam ipsum amet amet sit erat nibh laoreet adipiscing \n" +
            "consectetuer ipsum erat ipsum elit erat dolore diam adipiscing adipiscing laoreet tincidunt ipsum elit dolore nonummy dolor. \n" +
            "erat magna adipiscing consectetuer laoreet ut nibh aliquam magna diam ipsum dolore adipiscing sed dolore nibh magna ipsum \n" +
            "erat sit nonummy adipiscing ipsum sit sed sed ut ipsum ut dolor lorem ut sed laoreet consectetuer adipiscing nonummy dolore \n" +
            "sed adipiscing magna elit erat sed nonummy tincidunt sit aliquam tincidunt sit erat consectetuer adipiscing aliquam amet \n" +
            "lorem amet euismod adipiscing sed dolor laoreet magna euismod dolore ipsum laoreet lorem amet ut magna dolor tincidunt ut \n" +
            "magna tincidunt ipsum nibh adipiscing diam magna nibh euismod erat laoreet ut euismod erat consectetuer lorem dolore amet \n" +
            "diam aliquam euismod consectetuer ut lorem dolore sit euismod dolor aliquam. nonummy sed sed sed dolore laoreet sed laoreet \n" +
            "dolore adipiscing nonummy nibh dolore dolore dolore erat sed sit sed diam sed tincidunt dolore erat dolore dolor euismod \n" +
            "consectetuer aliquam elit adipiscing sit euismod sit nibh dolore ipsum aliquam aliquam consectetuer laoreet adipiscing dolore \n" +
            "amet amet nonummy erat amet nonummy elit laoreet magna sit laoreet erat magna ipsum amet dolore ut laoreet consectetuer ipsum \n" +
            "lorem adipiscing dolore laoreet sed lorem euismod euismod lorem elit amet euismod adipiscing dolor consectetuer erat aliquam \n" +
            "consectetuer sed dolor nibh aliquam nibh consectetuer aliquam ipsum ipsum diam nonummy lorem dolor sed aliquam euismod \n" +
            "consectetuer ut consectetuer diam laoreet ut. sed euismod magna sit tincidunt erat tincidunt diam sit adipiscing dolor \n" +
            "tincidunt sit adipiscing dolore lorem nonummy erat dolore laoreet dolor erat ut tincidunt erat tincidunt adipiscing nonummy \n" +
            "adipiscing nibh euismod diam amet ipsum nibh dolore aliquam amet diam sit adipiscing dolore diam nibh aliquam magna dolore ut \n" +
            "sed amet diam tincidunt ipsum nonummy lorem aliquam amet magna tincidunt erat euismod sed adipiscing erat ut nonummy \n" +
            "tincidunt sed ipsum erat sit erat erat laoreet ut ipsum sed sed diam adipiscing ut elit consectetuer euismod euismod ut \n" +
            "consectetuer diam tincidunt magna lorem aliquam nonummy sit dolore aliquam diam lorem sit erat tincidunt diam consectetuer. \n" +
            "dolore tincidunt amet euismod aliquam amet nibh dolore sed laoreet sed elit lorem sit sit ipsum dolore sit amet adipiscing \n" +
            "erat diam nibh magna tincidunt diam nonummy magna dolore ut adipiscing dolore nonummy dolor ut sit sed elit adipiscing lorem \n" +
            "magna erat amet laoreet euismod amet consectetuer dolore nonummy lorem dolor erat aliquam diam ipsum adipiscing dolore \n" +
            "nonummy lorem amet tincidunt diam dolor laoreet consectetuer tincidunt nonummy amet diam euismod magna erat laoreet nonummy \n" +
            "aliquam diam diam ut amet ut erat diam lorem amet nonummy lorem adipiscing sit elit dolore elit sit laoreet adipiscing \n" +
            "consectetuer sed magna dolor erat tincidunt diam sit sit. elit sed lorem ut dolor aliquam laoreet sed nonummy lorem laoreet \n" +
            "consectetuer dolore amet ipsum sed laoreet dolor nibh nonummy laoreet tincidunt euismod laoreet magna ut tincidunt adipiscing \n" +
            "dolore consectetuer dolor adipiscing diam laoreet magna ipsum magna tincidunt nibh dolore ipsum laoreet euismod ut erat lorem \n" +
            "ut lorem dolore magna laoreet nonummy ut tincidunt euismod erat laoreet tincidunt nonummy nonummy ipsum ipsum ut dolore magna \n" +
            "dolore diam elit adipiscing sed nibh aliquam sit adipiscing ut dolor adipiscing tincidunt nibh nonummy amet ipsum lorem ut \n" +
            "nonummy sed magna erat adipiscing ipsum magna sit aliquam tincidunt dolor sit aliquam erat magna nibh dolor nibh elit. diam \n" +
            "sit dolore elit aliquam nibh euismod ipsum adipiscing dolore consectetuer laoreet erat ut magna sed consectetuer magna dolor \n" +
            "dolor adipiscing tincidunt sed nibh consectetuer adipiscing amet laoreet tincidunt sed laoreet aliquam sed laoreet diam \n" +
            "consectetuer consectetuer sed dolore euismod sit dolore lorem adipiscing euismod elit magna sed nibh magna sed tincidunt \n" +
            "aliquam ipsum nibh dolore nibh nibh dolore euismod dolor euismod euismod lorem sed consectetuer tincidunt aliquam nibh diam \n" +
            "aliquam erat erat erat tincidunt dolor diam lorem amet adipiscing sit nonummy nibh ut sit elit ipsum nibh diam adipiscing \n" +
            "ipsum lorem tincidunt nonummy aliquam sit ut dolor euismod amet ipsum dolore aliquam. sit magna elit ipsum laoreet laoreet \n" +
            "consectetuer diam dolor ut sed sit magna ut dolor erat dolore euismod dolor tincidunt erat dolor erat sed lorem sed aliquam \n" +
            "adipiscing adipiscing aliquam laoreet amet sit lorem ut diam magna diam ut diam elit amet elit ipsum ut laoreet nonummy \n" +
            "consectetuer sed ipsum consectetuer nibh adipiscing dolor aliquam nibh magna nibh dolore sed sit erat magna elit amet nonummy \n" +
            "lorem adipiscing tincidunt elit lorem sed consectetuer tincidunt aliquam sit diam euismod elit erat ut laoreet tincidunt elit \n" +
            "nibh lorem erat dolor dolor elit adipiscing consectetuer diam laoreet laoreet amet tincidunt tincidunt erat dolor diam dolor \n" +
            "ut. tincidunt magna ipsum erat sit laoreet lorem dolor sed erat magna nibh tincidunt erat magna elit sit adipiscing euismod \n" +
            "dolor tincidunt sit dolor amet adipiscing sit dolor nonummy erat adipiscing nonummy sed consectetuer sit amet dolore euismod \n" +
            "nonummy sed consectetuer diam nibh elit elit nonummy nibh dolore tincidunt elit adipiscing ipsum diam amet lorem consectetuer \n" +
            "dolore adipiscing ipsum ipsum euismod amet ipsum ut ut lorem ut nonummy ipsum adipiscing erat dolor sit consectetuer magna \n" +
            "dolore ipsum lorem magna consectetuer consectetuer tincidunt diam euismod aliquam ut ut laoreet laoreet dolore dolore aliquam \n" +
            "adipiscing ipsum dolore adipiscing consectetuer magna ut nonummy euismod nonummy adipiscing dolor. magna consectetuer \n" +
            "adipiscing ut lorem consectetuer erat magna erat sit lorem sed laoreet laoreet lorem consectetuer nonummy sit elit magna sed \n" +
            "dolor consectetuer dolor dolore diam sed nonummy sit dolor euismod aliquam ipsum magna consectetuer elit magna euismod \n" +
            "laoreet erat euismod dolor nibh dolore adipiscing dolore nonummy dolor laoreet amet laoreet dolor ipsum amet adipiscing nibh \n" +
            "laoreet amet sed lorem sit dolore elit magna dolor euismod nonummy amet euismod consectetuer elit dolore erat diam magna \n" +
            "nonummy sed euismod sed nonummy consectetuer nibh erat tincidunt ut magna tincidunt consectetuer laoreet magna ipsum aliquam \n" +
            "lorem adipiscing tincidunt lorem sed erat erat ut diam dolore consectetuer. laoreet dolore consectetuer nonummy sed \n" +
            "consectetuer nonummy nonummy dolor ipsum elit euismod aliquam amet nibh euismod adipiscing elit diam euismod ut nonummy \n" +
            "aliquam amet diam lorem aliquam ipsum sit nibh diam elit consectetuer euismod dolor nibh elit sed sed lorem ipsum magna \n" +
            "nonummy ut lorem lorem ipsum tincidunt consectetuer sit sit amet tincidunt amet erat magna dolore sed sed dolor dolore dolore \n" +
            "tincidunt erat tincidunt tincidunt elit amet nibh sit aliquam nonummy sed dolore diam amet ipsum diam erat adipiscing erat \n" +
            "laoreet consectetuer adipiscing laoreet euismod nonummy tincidunt amet elit adipiscing ut magna aliquam aliquam erat elit \n" +
            "ipsum tincidunt amet sed euismod nonummy. dolore aliquam aliquam sit dolor elit aliquam tincidunt consectetuer adipiscing \n" +
            "lorem tincidunt consectetuer euismod lorem diam nonummy ipsum ut lorem magna adipiscing consectetuer aliquam diam lorem amet \n" +
            "sed laoreet aliquam lorem ipsum dolor sit sit aliquam magna erat ut lorem magna aliquam consectetuer nibh nonummy sed ipsum \n" +
            "tincidunt nonummy elit elit magna consectetuer dolor elit euismod laoreet sit lorem lorem adipiscing magna adipiscing amet \n" +
            "lorem sed euismod nonummy consectetuer diam ut dolor tincidunt adipiscing dolor dolore dolore laoreet dolor sit euismod amet \n" +
            "ipsum adipiscing consectetuer nibh nonummy amet amet euismod elit dolore tincidunt ipsum amet dolor ut lorem lorem \n" +
            "consectetuer diam consectetuer nonummy. tincidunt erat dolor amet amet dolore magna amet dolor laoreet ipsum amet laoreet \n" +
            "erat erat euismod elit sed amet diam amet laoreet consectetuer lorem elit sed euismod lorem tincidunt aliquam nibh tincidunt \n" +
            "lorem nonummy nonummy aliquam laoreet ut ipsum dolore dolor tincidunt euismod euismod sit laoreet lorem nibh elit adipiscing \n" +
            "erat euismod nonummy magna sit erat elit adipiscing sit adipiscing adipiscing erat elit consectetuer sit ut elit euismod \n" +
            "lorem tincidunt ipsum amet elit magna amet dolor erat ut euismod sit ut sed laoreet dolor sed adipiscing ipsum magna ut amet \n" +
            "aliquam euismod diam diam magna lorem dolor elit diam dolor sed consectetuer laoreet. euismod magna nonummy dolor diam elit \n" +
            "dolore diam adipiscing adipiscing tincidunt sit ut ut laoreet diam aliquam dolore sit erat adipiscing adipiscing elit \n" +
            "consectetuer elit magna euismod elit consectetuer nonummy sit amet nonummy ipsum ut diam adipiscing nonummy amet diam aliquam \n" +
            "magna tincidunt euismod euismod nonummy nibh ipsum diam adipiscing diam consectetuer nonummy ipsum nonummy sit tincidunt \n" +
            "tincidunt laoreet consectetuer dolore sit erat ipsum lorem aliquam lorem diam nibh diam ut ipsum amet aliquam amet amet diam \n" +
            "adipiscing nibh adipiscing erat nibh elit ipsum adipiscing consectetuer magna ut euismod euismod sit dolore elit elit amet \n" +
            "lorem laoreet erat lorem sit nonummy dolore elit. consectetuer lorem diam adipiscing aliquam sed adipiscing adipiscing amet \n" +
            "sed dolor consectetuer nonummy consectetuer erat diam sit ut sed dolore nibh laoreet adipiscing nonummy dolor elit ut magna \n" +
            "elit nibh nonummy erat tincidunt nonummy erat nibh elit magna dolore tincidunt nibh lorem ipsum sed diam laoreet sit ipsum \n" +
            "erat dolore dolore nonummy dolor aliquam elit nibh laoreet lorem dolor magna erat sed euismod magna euismod magna nonummy \n" +
            "laoreet aliquam euismod consectetuer nibh nibh adipiscing dolore dolor amet consectetuer euismod aliquam euismod dolor lorem \n" +
            "ipsum nonummy laoreet dolor amet erat consectetuer dolore erat ipsum laoreet tincidunt adipiscing euismod erat sed adipiscing \n" +
            "euismod nonummy lorem. diam euismod euismod tincidunt consectetuer ut nonummy sit euismod elit dolore dolore diam adipiscing \n" +
            "nibh consectetuer nibh magna nibh adipiscing laoreet consectetuer erat sit ipsum amet aliquam sed sed euismod nonummy nibh \n" +
            "nibh ut lorem ipsum sit lorem dolor sit dolor lorem ipsum diam dolor erat sed nonummy sed elit dolor ut tincidunt aliquam \n" +
            "euismod sed laoreet nibh ut aliquam diam nonummy amet erat amet lorem nibh tincidunt aliquam tincidunt aliquam nonummy sed \n" +
            "sit adipiscing magna amet sed sed ut nonummy dolore aliquam adipiscing ipsum sed erat sed elit elit tincidunt nonummy \n" +
            "consectetuer erat consectetuer sed lorem amet sed lorem sed dolor dolore. ipsum erat sed elit ut elit aliquam ut amet nonummy \n" +
            "sit adipiscing sed erat euismod sit sed ut nonummy tincidunt sed euismod ut euismod diam euismod lorem diam ut nibh nonummy \n" +
            "ut erat laoreet laoreet dolore nibh amet sed elit tincidunt euismod ipsum amet sit elit diam euismod amet erat magna sit \n" +
            "consectetuer adipiscing dolore tincidunt elit dolore ut sed nibh ipsum tincidunt dolore dolor erat euismod dolore euismod \n" +
            "tincidunt dolor ipsum magna aliquam elit dolor sed tincidunt dolore dolor amet laoreet lorem laoreet sit euismod sed dolore \n" +
            "ipsum nibh sed dolor nibh dolore euismod sed sit dolore adipiscing diam consectetuer laoreet ipsum. erat elit sit erat magna \n" +
            "diam tincidunt amet adipiscing elit ipsum laoreet ipsum lorem sed elit dolor diam aliquam sit nibh nibh dolor ut amet \n" +
            "consectetuer magna nibh ipsum sit aliquam euismod diam euismod tincidunt dolor dolore laoreet laoreet tincidunt laoreet dolor \n" +
            "dolor adipiscing amet sit sit sit adipiscing euismod lorem nibh aliquam tincidunt magna sit elit aliquam ut consectetuer \n" +
            "tincidunt lorem laoreet laoreet lorem lorem dolore lorem magna nibh amet amet ut sed adipiscing ipsum ut sed magna nibh lorem \n" +
            "diam adipiscing lorem magna sit lorem sed adipiscing sit diam consectetuer amet nonummy sed erat nibh aliquam magna \n" +
            "consectetuer dolor amet dolor. lorem diam ut amet euismod magna elit sit amet consectetuer dolor laoreet adipiscing nonummy \n" +
            "amet aliquam diam sed sit laoreet dolore amet elit adipiscing amet dolor erat lorem consectetuer dolore adipiscing aliquam ut \n" +
            "nonummy amet euismod laoreet aliquam magna sit dolore sit amet dolore consectetuer nonummy sed tincidunt ipsum diam euismod \n" +
            "elit sed erat consectetuer ut consectetuer ut laoreet sed lorem nibh magna amet ipsum sed diam sit consectetuer euismod \n" +
            "dolore nibh elit consectetuer adipiscing amet amet euismod laoreet sed euismod ipsum sit sit dolor dolor dolore dolor ut \n" +
            "ipsum diam ipsum ipsum ut consectetuer ipsum diam dolore dolor ipsum lorem consectetuer aliquam. tincidunt sed dolore aliquam \n" +
            "lorem diam adipiscing adipiscing erat nonummy consectetuer nibh elit aliquam magna dolor euismod sit magna ut lorem ut \n" +
            "laoreet erat nibh laoreet dolore laoreet magna dolor erat aliquam nonummy elit nonummy ipsum erat adipiscing ut nibh ut \n" +
            "dolore sit diam dolore lorem sit lorem adipiscing consectetuer aliquam adipiscing ipsum magna dolore ipsum sit adipiscing \n" +
            "dolor amet dolore euismod erat dolore nibh consectetuer dolor nonummy dolor consectetuer magna euismod magna dolor nonummy \n" +
            "diam magna laoreet ut nibh aliquam sed nonummy dolore elit elit aliquam ipsum aliquam sit aliquam lorem consectetuer sit \n" +
            "dolore lorem aliquam ipsum sed erat tincidunt amet elit. amet erat laoreet laoreet nonummy nibh consectetuer amet nonummy ut \n" +
            "ut nibh magna erat ipsum euismod aliquam adipiscing ipsum nonummy elit diam magna amet ut diam laoreet sed ipsum nibh euismod \n" +
            "erat nibh dolor elit amet magna aliquam euismod nonummy diam sed nonummy dolore lorem amet tincidunt dolore sed tincidunt \n" +
            "amet dolore sit laoreet laoreet tincidunt sit nibh dolore erat nibh euismod magna erat ipsum diam sit amet adipiscing \n" +
            "consectetuer ut elit lorem aliquam ipsum ut nibh sit euismod sed elit ipsum ipsum erat dolor euismod magna euismod tincidunt \n" +
            "laoreet sed dolore ut consectetuer nibh ipsum ut dolore nonummy elit amet euismod ipsum. consectetuer nibh adipiscing \n" +
            "consectetuer elit lorem nibh ut diam amet amet elit lorem sed laoreet nonummy consectetuer nonummy elit tincidunt lorem \n" +
            "nonummy aliquam adipiscing dolor nibh elit lorem diam euismod dolore nibh ut adipiscing erat adipiscing diam nibh magna \n" +
            "laoreet elit magna amet elit tincidunt erat tincidunt euismod euismod diam nonummy diam ipsum ut adipiscing dolor diam \n" +
            "aliquam adipiscing ut nibh dolor ipsum dolor laoreet aliquam dolore sit dolore diam euismod sit aliquam lorem laoreet lorem \n" +
            "sed ipsum consectetuer aliquam euismod nonummy sit lorem magna nibh tincidunt nibh sed consectetuer ut sit dolore tincidunt \n" +
            "ut sit amet diam erat dolore aliquam dolor consectetuer. diam magna sed erat dolor dolore adipiscing amet sit adipiscing \n" +
            "nonummy amet ipsum nibh ipsum diam sed magna laoreet tincidunt dolore consectetuer nonummy lorem nonummy amet ipsum erat \n" +
            "consectetuer ipsum dolor sed nibh magna ipsum dolor adipiscing dolor sed magna consectetuer ut erat lorem amet lorem sit nibh \n" +
            "erat tincidunt euismod euismod lorem dolore tincidunt dolor nibh diam tincidunt lorem lorem sed lorem amet erat nonummy sit \n" +
            "sit erat tincidunt magna ipsum aliquam amet sit laoreet ut erat dolor magna ut dolore euismod sit sit erat consectetuer diam \n" +
            "aliquam magna tincidunt magna adipiscing laoreet diam diam sit sed ut diam ut ipsum magna. dolore nibh amet dolore dolore \n" +
            "dolor sed aliquam erat nibh nonummy dolore aliquam ut aliquam nibh dolore dolor diam dolor dolore consectetuer laoreet \n" +
            "euismod dolore magna adipiscing laoreet adipiscing dolor aliquam nonummy magna diam diam amet diam diam sit aliquam ipsum \n" +
            "amet dolor nonummy dolor diam nibh elit lorem consectetuer dolore dolor sed dolore laoreet nonummy diam aliquam tincidunt \n" +
            "dolore ipsum amet diam aliquam sed ut sed dolor diam aliquam lorem nibh ipsum ut ipsum euismod tincidunt nonummy sed euismod \n" +
            "amet magna dolore sit magna euismod erat sit sit dolor ut sit nonummy elit magna consectetuer elit laoreet magna ut amet diam \n" +
            "lorem. diam adipiscing ipsum elit ipsum lorem erat lorem laoreet ipsum dolore sit euismod elit euismod sed sit adipiscing \n" +
            "aliquam dolor sit magna elit dolor lorem elit amet amet diam nonummy erat sit nibh ut magna amet ipsum elit sed elit dolore \n" +
            "ut sed dolor consectetuer adipiscing dolore tincidunt magna sed sit dolore amet magna tincidunt consectetuer laoreet nonummy \n" +
            "sed dolore tincidunt sit sit nibh nonummy ipsum aliquam erat euismod elit euismod adipiscing ut dolore ut tincidunt ut \n" +
            "aliquam consectetuer nonummy nonummy nonummy euismod dolor ut amet euismod euismod ut sed consectetuer adipiscing dolore elit \n" +
            "ut ut ut lorem euismod adipiscing aliquam aliquam nibh. sed aliquam laoreet nibh amet sit nibh erat erat diam nonummy lorem \n" +
            "adipiscing ut magna amet tincidunt lorem lorem aliquam ut diam ut laoreet nibh aliquam nonummy sit erat dolore dolor aliquam \n" +
            "tincidunt elit dolor magna adipiscing dolore ipsum dolore magna laoreet euismod erat aliquam ut dolore aliquam sed aliquam \n" +
            "erat ut euismod amet sed aliquam ut sit lorem nonummy erat lorem consectetuer amet nonummy dolor consectetuer consectetuer \n" +
            "lorem aliquam amet aliquam erat dolore elit euismod dolor dolore tincidunt ut elit laoreet dolore sit sed ipsum tincidunt \n" +
            "amet euismod lorem dolor nibh diam lorem diam tincidunt magna erat ipsum laoreet ut sed euismod. diam tincidunt elit amet \n" +
            "magna euismod sit dolore ut amet tincidunt euismod elit ipsum amet aliquam lorem sit laoreet nibh euismod erat erat amet \n" +
            "dolor lorem nonummy aliquam dolor euismod diam adipiscing lorem nibh ipsum nibh amet aliquam elit magna amet aliquam \n" +
            "consectetuer aliquam erat sed ut tincidunt sit adipiscing magna lorem sit erat tincidunt sed erat erat dolor nibh sed aliquam \n" +
            "nibh lorem nibh adipiscing adipiscing nonummy aliquam magna euismod lorem lorem dolor adipiscing elit laoreet ipsum diam erat \n" +
            "sed dolor consectetuer euismod nonummy ut elit erat erat amet dolore euismod lorem laoreet dolor nibh sit diam diam dolore \n" +
            "dolore ipsum nibh. ipsum magna elit adipiscing erat elit amet laoreet lorem laoreet tincidunt diam sit ut euismod nibh sit \n" +
            "adipiscing consectetuer laoreet sit magna dolore dolore elit erat consectetuer nibh nibh amet ut nonummy ipsum sit diam \n" +
            "nonummy magna elit amet elit tincidunt ipsum adipiscing nonummy erat elit lorem erat laoreet euismod magna sed tincidunt \n" +
            "lorem magna laoreet tincidunt amet sed amet sit sit nibh laoreet aliquam laoreet amet diam consectetuer erat ut ipsum euismod \n" +
            "magna diam dolore magna ipsum laoreet ipsum dolor nonummy tincidunt laoreet euismod ut consectetuer diam laoreet euismod \n" +
            "lorem sit tincidunt magna diam ipsum consectetuer lorem tincidunt ipsum dolore erat dolor. ipsum ut sed ut ut sed euismod \n" +
            "nonummy nibh nonummy amet lorem euismod euismod magna aliquam sed adipiscing aliquam amet consectetuer tincidunt diam dolore \n" +
            "sed nonummy nibh dolor laoreet ut nibh sed lorem euismod diam ut tincidunt ipsum amet nonummy lorem dolor sed dolor ut magna \n" +
            "adipiscing dolor laoreet sit euismod ipsum aliquam nonummy consectetuer lorem euismod aliquam sit aliquam dolor dolor ut sed \n" +
            "sed sed euismod euismod dolore nibh lorem aliquam euismod aliquam ipsum erat adipiscing magna consectetuer adipiscing aliquam \n" +
            "amet nonummy adipiscing diam lorem sed magna magna erat lorem aliquam dolore dolore ut ipsum sit elit nonummy diam lorem erat \n" +
            "adipiscing. tincidunt lorem diam dolore amet elit elit dolor dolor diam nibh sed laoreet erat lorem ipsum dolor laoreet \n" +
            "aliquam erat amet adipiscing tincidunt ipsum euismod sed dolor nibh laoreet consectetuer euismod lorem nonummy nonummy magna \n" +
            "laoreet sit aliquam ipsum magna consectetuer ipsum ut nibh euismod dolore nonummy adipiscing sed nibh lorem elit erat laoreet \n" +
            "ipsum dolore magna nibh nibh tincidunt amet adipiscing lorem dolore nonummy dolore diam aliquam sit nibh sit dolor tincidunt \n" +
            "sed elit elit ipsum laoreet diam consectetuer sit amet sit tincidunt laoreet euismod laoreet lorem amet amet amet adipiscing \n" +
            "sed lorem nibh amet diam consectetuer tincidunt erat tincidunt tincidunt dolor. euismod dolore sed amet laoreet erat diam \n" +
            "nonummy laoreet nibh adipiscing euismod amet diam ipsum sed elit magna laoreet erat adipiscing ipsum nibh ut diam aliquam \n" +
            "consectetuer diam tincidunt sit diam magna diam erat amet nibh laoreet tincidunt ut dolore tincidunt erat diam lorem sed erat \n" +
            "ipsum elit ut elit magna adipiscing euismod ipsum laoreet consectetuer sit ut sed tincidunt ipsum euismod sit magna elit diam \n" +
            "euismod dolor sit amet ipsum dolore magna lorem tincidunt magna dolor euismod diam magna consectetuer laoreet amet euismod \n" +
            "sed elit sit laoreet nonummy dolor adipiscing ipsum consectetuer euismod adipiscing nonummy dolore amet dolore nibh lorem \n" +
            "elit elit. tincidunt euismod adipiscing ut magna ipsum diam diam diam laoreet tincidunt nonummy amet sit laoreet laoreet sed \n" +
            "adipiscing amet dolore consectetuer ipsum nibh laoreet elit dolor nonummy ipsum consectetuer ut nonummy erat dolore diam diam \n" +
            "ipsum aliquam adipiscing amet euismod sed aliquam sed aliquam sed dolore aliquam consectetuer tincidunt sit lorem amet magna \n" +
            "sed aliquam ut tincidunt adipiscing lorem tincidunt elit lorem consectetuer adipiscing lorem aliquam tincidunt magna nonummy \n" +
            "amet sed elit magna ipsum adipiscing dolore laoreet erat elit nibh adipiscing diam sed dolore consectetuer adipiscing amet ut \n" +
            "erat euismod dolor nonummy elit nonummy euismod lorem dolor ut magna consectetuer amet amet consectetuer. aliquam ipsum erat \n" +
            "tincidunt diam consectetuer magna adipiscing adipiscing nonummy amet ut erat tincidunt amet aliquam sed ipsum dolore elit sit \n" +
            "dolore amet dolore consectetuer aliquam diam nonummy sit erat euismod erat amet dolore dolor nonummy nibh ipsum nonummy \n" +
            "dolore ipsum ut nibh laoreet ipsum aliquam diam adipiscing dolore ipsum dolor magna dolore aliquam dolore amet dolore elit \n" +
            "nonummy euismod erat tincidunt laoreet erat aliquam ipsum euismod tincidunt sed magna dolore sit magna tincidunt sed elit \n" +
            "aliquam aliquam ipsum adipiscing consectetuer sed adipiscing aliquam tincidunt sed nibh magna nonummy consectetuer nonummy \n" +
            "elit erat tincidunt ut euismod lorem euismod nonummy tincidunt aliquam euismod dolor. consectetuer nonummy dolore tincidunt \n" +
            "diam sit nonummy magna aliquam euismod consectetuer dolore nibh nonummy lorem elit magna nonummy tincidunt aliquam sit sed ut \n" +
            "nibh sit ipsum adipiscing ipsum dolore erat ut diam aliquam elit dolor erat erat aliquam ut dolore amet ipsum diam erat sed \n" +
            "lorem magna elit ut diam laoreet euismod elit nibh aliquam ut aliquam nonummy elit erat sed dolor laoreet sit dolore diam \n" +
            "dolore ut sit ipsum erat nibh nonummy laoreet elit magna sed tincidunt erat euismod dolore sit ut diam amet erat sed aliquam \n" +
            "nibh ut adipiscing ipsum dolor erat erat dolore ipsum nibh magna elit aliquam erat sit. magna elit sit diam lorem amet elit \n" +
            "adipiscing magna ipsum dolore erat tincidunt aliquam sed sed erat adipiscing elit erat dolor ipsum nibh aliquam adipiscing \n" +
            "sed laoreet ut lorem amet lorem nibh sit elit sed laoreet dolor dolore ipsum nonummy ut elit elit consectetuer amet euismod \n" +
            "dolor ut aliquam magna erat lorem dolore sit euismod amet sed adipiscing sit nibh diam dolore euismod amet sed sit euismod \n" +
            "aliquam tincidunt amet sit elit sed tincidunt tincidunt euismod adipiscing lorem diam aliquam ut aliquam adipiscing nonummy \n" +
            "nonummy adipiscing dolore ipsum laoreet tincidunt laoreet adipiscing euismod sed elit amet dolore adipiscing magna tincidunt \n" +
            "ut dolore consectetuer. magna erat elit diam adipiscing sit sed ut elit sed lorem consectetuer euismod dolore erat nonummy \n" +
            "diam ipsum elit tincidunt aliquam dolore ut dolor dolore dolore amet elit diam tincidunt elit sed amet magna adipiscing \n" +
            "dolore dolore nonummy tincidunt nibh elit ut nibh dolore elit diam adipiscing ut dolore adipiscing amet aliquam consectetuer \n" +
            "ipsum aliquam dolore consectetuer sit adipiscing nibh adipiscing nonummy aliquam adipiscing amet dolore ipsum ut ipsum magna \n" +
            "elit ut consectetuer elit euismod nibh aliquam erat ut amet ut sed dolor magna nibh aliquam ut amet diam ipsum adipiscing \n" +
            "tincidunt sit tincidunt ipsum magna diam sed lorem ut aliquam nonummy tincidunt. dolor diam consectetuer aliquam magna dolor \n" +
            "lorem magna dolore elit ut sit lorem aliquam magna amet euismod dolor nibh adipiscing consectetuer euismod adipiscing euismod \n" +
            "dolore dolore dolore dolore tincidunt ut erat dolore sit laoreet amet consectetuer sit ut ipsum diam erat sit ipsum \n" +
            "adipiscing dolore sit sed nonummy erat sit diam aliquam adipiscing erat euismod elit erat nibh lorem nibh laoreet laoreet \n" +
            "dolor consectetuer magna amet euismod magna amet euismod adipiscing laoreet laoreet nonummy sit euismod consectetuer lorem \n" +
            "euismod aliquam ut elit tincidunt lorem tincidunt diam ipsum magna ut laoreet diam sed erat ipsum sed diam lorem adipiscing \n" +
            "ut sit ipsum sed laoreet. aliquam adipiscing consectetuer euismod nibh adipiscing diam tincidunt euismod elit sed \n" +
            "consectetuer nonummy diam sed erat elit magna euismod nonummy dolore adipiscing magna tincidunt nibh magna ut dolor aliquam \n" +
            "aliquam sit lorem dolore nibh nonummy adipiscing ipsum dolore diam erat aliquam sed sed diam nibh sit sed dolore ipsum ipsum \n" +
            "elit sed amet lorem nonummy ipsum adipiscing adipiscing sed ut nibh erat euismod tincidunt laoreet aliquam ut euismod dolor \n" +
            "nonummy diam ut aliquam elit tincidunt amet euismod laoreet euismod adipiscing nibh diam aliquam ut tincidunt sed diam dolore \n" +
            "magna tincidunt dolore tincidunt euismod nibh dolore adipiscing lorem sit nonummy laoreet amet ipsum lorem. adipiscing magna \n" +
            "adipiscing aliquam aliquam dolore consectetuer ipsum ut amet amet dolor sed elit sit dolor aliquam magna tincidunt aliquam \n" +
            "sed nibh erat elit ut laoreet nibh dolore magna diam consectetuer nibh nibh erat lorem laoreet dolore laoreet dolore dolor \n" +
            "diam magna euismod amet nibh euismod consectetuer amet laoreet dolor adipiscing elit diam elit adipiscing sed erat nibh \n" +
            "dolore consectetuer aliquam amet ut consectetuer ipsum dolore aliquam erat diam magna sed sed consectetuer dolor magna \n" +
            "aliquam dolor nonummy magna erat adipiscing erat diam diam amet consectetuer ipsum dolore amet aliquam amet aliquam ipsum \n" +
            "consectetuer laoreet erat laoreet nibh dolor euismod dolor erat laoreet. erat erat elit ipsum diam magna dolore lorem \n" +
            "consectetuer diam diam amet diam sit laoreet amet nonummy amet aliquam nonummy diam ut magna ipsum amet nibh adipiscing \n" +
            "nonummy ipsum aliquam elit sit tincidunt adipiscing sit tincidunt adipiscing magna ut adipiscing nibh ut consectetuer diam \n" +
            "amet magna laoreet dolor nonummy lorem amet ut euismod adipiscing nibh erat ipsum erat erat euismod nonummy consectetuer \n" +
            "lorem tincidunt tincidunt dolore dolore ut sit aliquam magna aliquam laoreet nibh euismod adipiscing amet elit consectetuer \n" +
            "dolore ipsum euismod amet sed tincidunt dolore nibh ut aliquam sed dolor nonummy sit amet erat dolore tincidunt lorem dolore \n" +
            "erat dolor nibh sed. magna consectetuer adipiscing diam tincidunt laoreet elit laoreet adipiscing elit tincidunt ipsum sed \n" +
            "adipiscing dolore ut nonummy adipiscing sit consectetuer laoreet euismod adipiscing aliquam diam consectetuer tincidunt amet \n" +
            "lorem nonummy lorem elit elit amet dolore laoreet ipsum erat tincidunt erat nibh sit tincidunt euismod elit dolor erat nibh \n" +
            "aliquam tincidunt adipiscing aliquam erat ipsum sed sed euismod dolor ipsum nibh diam aliquam ipsum ut sit diam magna diam \n" +
            "elit diam laoreet sed amet adipiscing amet aliquam laoreet lorem tincidunt nonummy elit dolore dolor nibh ut aliquam diam \n" +
            "diam sed dolore adipiscing sit adipiscing dolore elit nonummy dolor sit diam dolore magna ut lorem. laoreet magna euismod \n" +
            "amet euismod adipiscing nonummy dolore nibh diam nibh sit consectetuer tincidunt elit ut consectetuer dolor adipiscing \n" +
            "tincidunt adipiscing nibh erat ut nonummy erat nibh dolore lorem diam diam consectetuer dolor ut nibh lorem tincidunt erat \n" +
            "aliquam adipiscing sit elit tincidunt amet elit erat lorem tincidunt consectetuer nonummy dolore nibh magna consectetuer \n" +
            "adipiscing tincidunt dolor lorem consectetuer aliquam sed consectetuer ipsum euismod laoreet consectetuer nonummy lorem erat \n" +
            "ut dolor elit consectetuer nonummy erat laoreet lorem sit ipsum magna sed erat lorem adipiscing lorem adipiscing elit amet \n" +
            "laoreet dolor laoreet nibh ut laoreet euismod tincidunt diam tincidunt elit sit dolore amet diam. aliquam ipsum adipiscing \n" +
            "amet erat elit nibh adipiscing erat erat dolor nibh nibh erat lorem magna amet nibh lorem lorem ut sed dolor lorem laoreet \n" +
            "diam lorem aliquam laoreet dolor laoreet sit elit laoreet aliquam sed erat tincidunt adipiscing laoreet consectetuer lorem \n" +
            "elit elit adipiscing magna dolor amet amet tincidunt dolore adipiscing diam erat nonummy elit ut adipiscing sit sed ut \n" +
            "tincidunt ipsum sed laoreet dolore sit magna ipsum dolor ipsum lorem erat consectetuer magna sed aliquam erat euismod laoreet \n" +
            "dolore aliquam elit ipsum nonummy dolor sed euismod nibh lorem nonummy tincidunt tincidunt laoreet dolore nibh magna elit \n" +
            "magna lorem laoreet diam dolor. aliquam amet adipiscing tincidunt ipsum tincidunt tincidunt erat dolore ipsum lorem dolore \n" +
            "nonummy nonummy sit aliquam sit consectetuer nibh dolore amet lorem dolor adipiscing dolor aliquam dolore nibh sed adipiscing \n"






                 ;
    
}
