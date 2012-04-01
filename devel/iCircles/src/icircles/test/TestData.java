package icircles.test;

import java.awt.Font;

public class TestData {

	// TODO : include shading and spiders into diagram checksums
	
    // possible actions
    public static final int RUN_ALL_TESTS = 0;
    public static final int RUN_TEST_LIST = 1;
    public static final int VIEW_TEST_LIST = 2;
    public static final int VIEW_ALL_TESTS = 3;
    public static final int TEST_PANEL_SIZE = 280;
    public static final boolean RANDOM_SHADING = false; // we don't have too many tests with shaded zones

    public static boolean GENERATE_ALL_TEST_DATA = false; // with "run all tests", generates text for test_data array

    public static boolean TEST_EULER_THREE = false;
    
    public static boolean DO_VIEW_FAILURES = false;
    public static int TEST_DEBUG_LEVEL = 0;
    
    // settings for view-list or view-all
    public static final int VIEW_PANEL_SIZE = 140; // small panel, good for viewing multiple
    //public static final int VIEW_PANEL_SIZE = 480; // large panel, good for single, complex diagrams
    public static final int FAIL_VIEW_PANEL_SIZE = 180;
    public static final int GRID_WIDTH = 7;
    public static int[] test_list = {
    	108
    };  // a set of tests of particular interest
    //public static int TASK = RUN_TEST_LIST;
    //public static int TASK = VIEW_TEST_LIST;
    public static int TASK = RUN_ALL_TESTS;
    //public static int TASK = VIEW_ALL_TESTS;
    
    public static Font font = new Font("Helvetica", Font.BOLD | Font.ITALIC,  16);
    public static double scale = 1.0; // to test scaled diagrams look= OK
    public static boolean test_journalling = false;// converts String->AbstractDescription->String
    
    public static TestDatum[] test_data = {
    	/*0*/new TestDatum( "a", 80.35747263647977),
    	/*1*/new TestDatum( "a b", 131.7353635695516),
    	/*2*/new TestDatum( "a b c", 197.80358797941003),
    	/*3*/new TestDatum( "ab", 162.93369525421534),
    	/*4*/new TestDatum( "a ab", 162.90331524089532),
    	/*5*/new TestDatum( "a b ab", 151.7818962901353),
    	/*6*/new TestDatum( "a b ac", 213.1198933122397),
    	/*7*/new TestDatum( "a b c ab", 221.12921720863423),
    	/*8*/new TestDatum( "ab ac", 249.18802791037467),
    	/*9*/new TestDatum( "a ab ac", 249.15795791037468),
    	/*10*/new TestDatum( "a b ab ac", 245.46650918145224),
    	/*11*/new TestDatum( "a b c ab ac", 234.07155278782034),
    	/*12*/new TestDatum( "a bc", 213.15058335752772),
    	/*13*/new TestDatum( "a ab bc", 236.37677387772402),
    	/*14*/new TestDatum( "a b ac bc", 234.10224283310836),
    	/*15*/new TestDatum( "ab ac bc", 345.8537728431746),
    	/*16*/new TestDatum( "a ab ac bc", 341.0629332286548),
    	/*17*/new TestDatum( "a b ab ac bc", 345.79270292522585),
    	/*18*/new TestDatum( "a b c ab ac bc", 320.738903832486),
    	/*19*/new TestDatum( "abc", 262.5961409068328),
    	/*20*/new TestDatum( "a abc", 262.56514086154476),
    	/*21*/new TestDatum( "a b abc", 251.3918065152784),
    	/*22*/new TestDatum( "a b c abc", 329.865511996727),
    	/*23*/new TestDatum( "ab abc", 262.5338698642088),
    	/*24*/new TestDatum( "a ab abc", 262.50348984822483),
    	/*25*/new TestDatum( "a b ab abc", 251.3304745152784),
    	/*26*/new TestDatum( "a b ac abc", 318.9277106237469),
    	/*27*/new TestDatum( "a b c ab abc", 329.80355107877824),
    	/*28*/new TestDatum( "ab ac abc", 262.1928707566217),
    	/*29*/new TestDatum( "a ab ac abc", 262.16280075662166),
    	/*30*/new TestDatum( "a b ab ac abc", 247.17005943313438),
    	/*31*/new TestDatum( "a b c ab ac abc", 346.9106429249104),
    	/*32*/new TestDatum( "a bc abc", 318.9584006114925),
    	/*33*/new TestDatum( "a ab bc abc", 248.03519043865083),
    	/*34*/new TestDatum( "a b ac bc abc", 346.9413329126561),
    	/*35*/new TestDatum( "ab ac bc abc", 344.31190058621024),
    	/*36*/new TestDatum( "a ab ac bc abc", 354.7520417131998),
    	/*37*/new TestDatum( "a b ab ac bc abc", 344.2508306682615),
    	/*38*/new TestDatum( "a b c ab ac bc abc", 239.42535182578533),
    	/*39*/new TestDatum( "ab b", 162.90331525421533),
    	/*40*/new TestDatum( "a ab b", 151.7818962901353),
    	/*41*/new TestDatum( "bc a b ", 213.11989334154367),
    	/*42*/new TestDatum( "a ab c", 218.8518190168085),
    	/*43*/new TestDatum( "a abc abcd", 383.19611159381213),
    	/*44*/new TestDatum( "abc b c ab ac bc", 344.25083058621027),
    	/*45*/new TestDatum( "a b c ab ac bc", 320.738903832486),
    	/*46*/new TestDatum( "a b c ab ac abc", 346.9106429249104),
    	/*47*/new TestDatum( "a b ab ac bc abc", 344.2508306682615),
    	/*48*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd", 480.2882471117551),
    	/*49*/new TestDatum( "a b ab c ac bc abc cd acd bcd abcd cde acde bcde abcde", 504.97993663529047),
    	/*50*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd cde acde bcde abcde", 647.653439758872),
    	/*51*/new TestDatum( "abcd abce", 519.3720985015174),
    	/*52*/new TestDatum( "a ab c cd", 309.1412020191669),
    	/*53*/new TestDatum( "a c ab bc", 225.14440110878547),
    	/*54*/new TestDatum( "a b ac bc bcd d", 461.41212647149644),
    	/*55*/new TestDatum( "abcd abce de", 1022.485575509847),
    	/*56*/new TestDatum( "a b ab c ac bc abc df adf bdf abdf cd acd bcd abcd cde acde bcde abcde", 842.2265833810333),
    	/*57*/new TestDatum( "abd abc dc", 620.3233211664545),
    	/*58*/new TestDatum( "a b ab c ac bc abc p q pq r pr qr pqr x bx px", 773.1897785388294),
    	/*59*/new TestDatum( "a b ab c ac d ad e ae f af", 623.4193360283326),
    	/*60*/new TestDatum( "a b c d cd ae be e ce de cde", 473.4313453028586),
    	/*61*/new TestDatum( "a b c d cd ae be e ce de cde ef", 624.755861723394),
    	/*62*/new TestDatum( "a b c ab ac bc abc ad", 354.40899348415445),
    	/*63*/new TestDatum( "a b c ab ac bc abc abd", 341.6622730654172),
    	/*64*/new TestDatum( "a b c ab ac bc abc abcd", 342.13103793381896),
    	/*65*/new TestDatum( "ad bd cd abd acd bcd abcd d", 389.46623298959486),
    	/*66*/new TestDatum( "a b c ab ac bc abc ad bd cd", 589.5571609784887),
    	/*67*/new TestDatum( "a b c ab ac bc abc abd bcd acd", 602.481485272905),
    	/*68*/new TestDatum( "a b c ab ac bc abc ad d", 329.37716678546525),
    	/*69*/new TestDatum( "a b c ab ac bc abc ad abd", 340.30289204968267),
    	/*70*/new TestDatum( "a b c ab ac bc abc abd abcd", 338.7584031904369),
    	/*71*/new TestDatum( "a b c ab ac bc abc ad d be e cf f", 554.2867469715592),
    	/*72*/new TestDatum( "a b c ab ac bc abc ad bd abd d", 355.41370661062354),
    	/*73*/new TestDatum( "a b c ab ac bc abc acd bcd abcd cd", 365.61327622737673),
    	/*74*/new TestDatum( "a ab b ac c ad d be e cf f dg g", 739.1650874845034),
    	/*75*/new TestDatum( "a ab b ac c ad d be e cf f dg g eh h fi i gj j ak k kl l lm m", 2755.650253481586),
    	/*76*/new TestDatum( "ab ac abc ad ae ade", 510.5684594777627),
    	/*77*/new TestDatum( "a b ab c ac abd ace", 473.88629518200145),
    	/*78*/new TestDatum( "a b ab c ac d ad be ce de", 906.6596427387265),
    	/*79*/new TestDatum( "a b ab c ac d ad ae be ce de", 963.0287791710319),
    	/*80*/new TestDatum( "a b ab c ac abd ace acef acefg", 803.1309682956693),
    	/*81*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg", 2912.8644212434906),
    	/*82*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc", 3097.959369087413),
    	/*83*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj", 3742.7817271682406),
    	/*84*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l", 4285.482133580946),
    	/*85*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc", 4238.267856637411),
    	/*86*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al", 6039.089394855562),
    	/*87*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s", 24424.900281902374),
    	/*88*/new TestDatum( ",", 0.0),
    	/*89*/new TestDatum( ",.", 0.0),
    	/*90*/new TestDatum( "a,", 80.35747263647977),
    	/*91*/new TestDatum( "a,.", 80.35747263647977),
    	/*92*/new TestDatum( "a,a", 80.38754263647976),
    	/*93*/new TestDatum( "a,. a", 80.38754263647976),
    	/*94*/new TestDatum( "a b ab,", 151.7818962901353),
    	/*95*/new TestDatum( "a b ab, a", 151.8119662901353),
    	/*96*/new TestDatum( "a b ab, b", 151.81227629013532),
    	/*97*/new TestDatum( "a b ab, ab", 151.8432282901353),
    	/*98*/new TestDatum( "a b ab, .", 151.7818962901353),
    	/*99*/new TestDatum( "a b ab, . a", 151.8119662901353),
    	/*100*/new TestDatum( "a b ab, . b", 151.81227629013532),
    	/*101*/new TestDatum( "a b ab, . a b", 151.8423462901353),
    	/*102*/new TestDatum( "a b ab, . a b ab", 151.9036782901353),
    	/*103*/new TestDatum( "a ab c abc, ", 248.06646143598684),
    	/*104*/new TestDatum( "a ab c abc,.", 248.06646143598684),
    	/*105*/new TestDatum( "a ab c abc,a", 248.09653143598683),
    	/*106*/new TestDatum( "a ab c abc,ab", 248.12779343598683),
    	/*107*/new TestDatum( "a ab c abc,a ab", 248.15786343598683),
    	/*108*/new TestDatum( "a b ab, ,a 'my_label", 310.9439177650555),
    	/*109*/new TestDatum( "a b ab, ,b 'label2", 234.9945557984952),
    	/*110*/new TestDatum( "a b ab, ,a 'sa, b 'sb", 425.9889815683995),
    	/*111*/new TestDatum( "a b ab, ,., a", 509.99511776505557),
    	/*112*/new TestDatum( "a b ab, ,ab", 272.96923678177535),
    	/*113*/new TestDatum( "a b ab, ,a, ab", 463.9636625516796),
    	/*114*/new TestDatum( "a b ab, ,b, ab", 372.8244281918072),
    	/*115*/new TestDatum( "a b ab, ,a, ab, .", 692.2760158039885),
    	/*116*/new TestDatum( "a b ab, ,a, ab, ., b", 883.5874992151189),
    	/*117*/new TestDatum( "a b ab, ,a b", 743.9813858633836),
    	/*118*/new TestDatum( "a b ab, ,a b, . ab", 1631.9744393021174),
    	/*119*/new TestDatum( "a b c ab ac bc abc ad, ,a b c abc ac", 2225.4621934841543),
    	/*120*/new TestDatum( "a b c ab ac bc abc ad, ,a b c abc", 1831.5765934841545),
    	/*121*/new TestDatum( "A B AB, ,A B, AB", 998.2966195810333),
    	/*122*/new TestDatum( "A B AB, ,A, AB", 463.9636578630396),
    	/*123*/new TestDatum( "A B AB, ,B, AB", 372.8244235031673),
    	/*124*/new TestDatum( "A B AB, ,A AB,B AB", 1548.50917068585),
    	/*125*/new TestDatum( "A B AB, ,A AB,B", 1086.8997518555555),
    	/*126*/new TestDatum( "A B AB, ,B AB,A", 971.4567216663836),
    	/*127*/new TestDatum( "A B C AB AC BC ABC, B,A AB ABC, B, B", 2411.6785080682166),
    	/*128*/new TestDatum( ",", 0.0),
    	/*129*/new TestDatum( ",,", 0.0),
    	/*130*/new TestDatum( "a,,a .", 845.5434726364797),
    	/*131*/new TestDatum( "a,.,a .,.", 1055.5206726364797),
    	/*132*/new TestDatum( "a,.,a .,.,.,.", 2033.7907206364794),
    	/*133*/new TestDatum( "A B AB, ,A B AB .,B AB", 2866.5386114881985),

    };
}
