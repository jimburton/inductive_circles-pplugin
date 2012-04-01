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
        //108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120
    	 3
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
    	/*1*/new TestDatum( "a b", 131.73538432211157),
    	/*2*/new TestDatum( "a b c", 197.803656305682),
    	/*3*/new TestDatum( "ab", 162.96409600677532),
    	/*4*/new TestDatum( "a ab", 162.9033282944953),
    	/*5*/new TestDatum( "a b ab", 151.7819170426953),
    	/*6*/new TestDatum( "a b ac", 213.1199431556797),
    	/*7*/new TestDatum( "a b c ab", 221.1292762375462),
    	/*8*/new TestDatum( "ab ac", 249.1880777538147),
    	/*9*/new TestDatum( "a ab ac", 249.1580077538147),
    	/*10*/new TestDatum( "a b ab ac", 245.46656821036424),
    	/*11*/new TestDatum( "a b c ab ac", 234.07160263126036),
    	/*12*/new TestDatum( "a bc", 213.21203168379972),
    	/*13*/new TestDatum( "a ab bc", 236.40721290663595),
    	/*14*/new TestDatum( "a b ac bc", 234.16369115938036),
    	/*15*/new TestDatum( "ab ac bc", 346.006407135701),
    	/*16*/new TestDatum( "a ab ac bc", 341.1550956747829),
    	/*17*/new TestDatum( "a b ab ac bc", 345.7928067269202),
    	/*18*/new TestDatum( "a b c ab ac bc", 320.7390181250124),
    	/*19*/new TestDatum( "abc", 262.81189123310486),
    	/*20*/new TestDatum( "a abc", 262.6599337049848),
    	/*21*/new TestDatum( "a b abc", 251.51333754419036),
    	/*22*/new TestDatum( "a b c abc", 330.01992828925336),
    	/*23*/new TestDatum( "ab abc", 262.5643088931208),
    	/*24*/new TestDatum( "a ab abc", 262.5035397183048),
    	/*25*/new TestDatum( "a b ab abc", 251.33053354419036),
    	/*26*/new TestDatum( "a b ac abc", 318.927803069875),
    	/*27*/new TestDatum( "a b c ab abc", 329.8036548804726),
    	/*28*/new TestDatum( "ab ac abc", 262.1929206000617),
    	/*29*/new TestDatum( "a ab ac abc", 262.1628506000617),
    	/*30*/new TestDatum( "a b ab ac abc", 247.17011846204636),
    	/*31*/new TestDatum( "a b c ab ac abc", 346.91073537103836),
    	/*32*/new TestDatum( "a bc abc", 319.0198949040189),
    	/*33*/new TestDatum( "a ab bc abc", 248.06562946756281),
    	/*34*/new TestDatum( "a b ac bc abc", 347.0028272051824),
    	/*35*/new TestDatum( "ab ac bc abc", 344.4645348787367),
    	/*36*/new TestDatum( "a ab ac bc abc", 354.8442041593278),
    	/*37*/new TestDatum( "a b ab ac bc abc", 344.2509344699559),
    	/*38*/new TestDatum( "a b c ab ac bc abc", 239.4254201520573),
    	/*39*/new TestDatum( "ab b", 162.90333600677533),
    	/*40*/new TestDatum( "a ab b", 151.7819170426953),
    	/*41*/new TestDatum( "bc a b ", 213.1199429185837),
    	/*42*/new TestDatum( "a ab c", 218.85186886024846),
    	/*43*/new TestDatum( "a abc abcd", 383.32255269259616),
    	/*44*/new TestDatum( "abc b c ab ac bc", 344.2509448787367),
    	/*45*/new TestDatum( "a b c ab ac bc", 320.7390181250124),
    	/*46*/new TestDatum( "a b c ab ac abc", 346.91073537103836),
    	/*47*/new TestDatum( "a b ab ac bc abc", 344.2509344699559),
    	/*48*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd", 480.28847284366384),
    	/*49*/new TestDatum( "a b ab c ac bc abc cd acd bcd abcd cde acde bcde abcde", 504.98018594391885),
    	/*50*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd cde acde bcde abcde", 647.6538131818752),
    	/*51*/new TestDatum( "abcd abce", 520.0504638367859),
    	/*52*/new TestDatum( "a ab c cd", 309.1413411375092),
    	/*53*/new TestDatum( "a c ab bc", 225.23559994056143),
    	/*54*/new TestDatum( "a b ac bc bcd d", 461.4737200542865),
    	/*55*/new TestDatum( "abcd abce de", 1024.6773998954752),
    	/*56*/new TestDatum( "a b ab c ac bc abc df adf bdf abdf cd acd bcd abcd cde acde bcde abcde", 843.5827187587321),
    	/*57*/new TestDatum( "abd abc dc", 621.0326917295579),
    	/*58*/new TestDatum( "a b ab c ac bc abc p q pq r pr qr pqr x bx px", 773.1905509244544),
    	/*59*/new TestDatum( "a b ab c ac d ad e ae f af", 623.4197151114835),
    	/*60*/new TestDatum( "a b c d cd ae be e ce de cde", 473.4316221120656),
    	/*61*/new TestDatum( "a b c d cd ae be e ce de cde ef", 624.7563041808456),
    	/*62*/new TestDatum( "a b c ab ac bc abc ad", 354.40913258651284),
    	/*63*/new TestDatum( "a b c ab ac bc abc abd", 341.6624121677756),
    	/*64*/new TestDatum( "a b c ab ac bc abc abcd", 342.13117703617735),
    	/*65*/new TestDatum( "ad bd cd abd acd bcd abcd d", 389.46636039805895),
    	/*66*/new TestDatum( "a b c ab ac bc abc ad bd cd", 589.5574705098849),
    	/*67*/new TestDatum( "a b c ab ac bc abc abd bcd acd", 602.4817948043013),
    	/*68*/new TestDatum( "a b c ab ac bc abc ad d", 329.37730588782364),
    	/*69*/new TestDatum( "a b c ab ac bc abc ad abd", 340.3030311520411),
    	/*70*/new TestDatum( "a b c ab ac bc abc abd abcd", 338.7585422927953),
    	/*71*/new TestDatum( "a b c ab ac bc abc ad d be e cf f", 554.2871566358954),
    	/*72*/new TestDatum( "a b c ab ac bc abc ad bd abd d", 355.41383462008594),
    	/*73*/new TestDatum( "a b c ab ac bc abc acd bcd abcd cd", 365.6134153297351),
    	/*74*/new TestDatum( "a ab b ac c ad d be e cf f dg g", 739.1656835419293),
    	/*75*/new TestDatum( "a ab b ac c ad d be e cf f dg g eh h fi i gj j ak k kl l lm m", 2755.6547317526115),
    	/*76*/new TestDatum( "ab ac abc ad ae ade", 510.5686842234586),
    	/*77*/new TestDatum( "a b ab c ac abd ace", 473.8865183239693),
    	/*78*/new TestDatum( "a b ab c ac d ad be ce de", 906.8168248956114),
    	/*79*/new TestDatum( "a b ab c ac d ad ae be ce de", 963.1547437610822),
    	/*80*/new TestDatum( "a b ab c ac abd ace acef acefg", 803.1315643163961),
    	/*81*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg", 2914.8922295833545),
    	/*82*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc", 3100.9052396609977),
    	/*83*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj", 3745.9198040226643),
    	/*84*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l", 4288.812843454157),
    	/*85*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc", 4241.777308395484),
    	/*86*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al", 6042.700502575398),
    	/*87*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s", 24431.312294660525),
    	/*88*/new TestDatum( ",", 0.0),
    	/*89*/new TestDatum( ",.", 0.0),
    	/*90*/new TestDatum( "a,", 80.35747263647977),
    	/*91*/new TestDatum( "a,.", 80.35747263647977),
    	/*92*/new TestDatum( "a,a", 80.38754263647976),
    	/*93*/new TestDatum( "a,. a", 80.38754263647976),
    	/*94*/new TestDatum( "a b ab,", 151.7819170426953),
    	/*95*/new TestDatum( "a b ab, a", 151.87212704269533),
    	/*96*/new TestDatum( "a b ab, b", 151.8426770426953),
    	/*97*/new TestDatum( "a b ab, ab", 151.93465104269532),
    	/*98*/new TestDatum( "a b ab, .", 151.7819170426953),
    	/*99*/new TestDatum( "a b ab, . a", 151.87212704269533),
    	/*100*/new TestDatum( "a b ab, . b", 151.8426770426953),
    	/*101*/new TestDatum( "a b ab, . a b", 151.9328870426953),
    	/*102*/new TestDatum( "a b ab, . a b ab", 152.08562104269532),
    	/*103*/new TestDatum( "a ab c abc, ", 248.16125427942686),
    	/*104*/new TestDatum( "a ab c abc,.", 248.16125427942686),
    	/*105*/new TestDatum( "a ab c abc,a", 248.19132427942685),
    	/*106*/new TestDatum( "a ab c abc,ab", 248.34763427942684),
    	/*107*/new TestDatum( "a ab c abc,a ab", 248.37770427942684),
    	/*108*/new TestDatum( "a b ab, ,a 'my_label", 310.94393851761555),
    	/*109*/new TestDatum( "a b ab, ,b 'label2", 234.99457655105522),
    	/*110*/new TestDatum( "a b ab, ,a 'sa, b 'sb", 425.98900232095946),
    	/*111*/new TestDatum( "a b ab, ,., a", 509.9951385176156),
    	/*112*/new TestDatum( "a b ab, ,ab", 272.9692575343354),
    	/*113*/new TestDatum( "a b ab, ,a, ab", 463.96368330423957),
    	/*114*/new TestDatum( "a b ab, ,b, ab", 372.82444894436725),
    	/*115*/new TestDatum( "a b ab, ,a, ab, .", 692.2760365565484),
    	/*116*/new TestDatum( "a b ab, ,a, ab, ., b", 883.587519967679),
    	/*117*/new TestDatum( "a b ab, ,a b", 743.9814066159436),
    	/*118*/new TestDatum( "a b ab, ,a b, . ab", 1631.9745601011973),
    	/*119*/new TestDatum( "a b c ab ac bc abc ad, ,a b c abc ac", 2225.4623325865127),
    	/*120*/new TestDatum( "a b c ab ac bc abc ad, ,a b c abc", 1831.576732586513),
    	/*121*/new TestDatum( "A B AB, ,A B, AB", 998.2966911360733),
    	/*122*/new TestDatum( "A B AB, ,A, AB", 463.96367179575964),
    	/*123*/new TestDatum( "A B AB, ,B, AB", 372.82443743588726),
    	/*124*/new TestDatum( "A B AB, ,A AB,B AB", 1548.50918461857),
    	/*125*/new TestDatum( "A B AB, ,A AB,B", 1086.8997657882755),
    	/*126*/new TestDatum( "A B AB, ,B AB,A", 971.4567355991036),
    	/*127*/new TestDatum( "A B C AB AC BC ABC, B,A AB ABC, B, B", 2411.7399340595125),
    	/*128*/new TestDatum( ",", 0.0),
    	/*129*/new TestDatum( ",,", 0.0),
    	/*130*/new TestDatum( "a,,a .", 845.5434726364797),
    	/*131*/new TestDatum( "a,.,a .,.", 1055.5206726364797),
    	/*132*/new TestDatum( "a,.,a .,.,.,.", 2033.7907206364794),

    };
}
