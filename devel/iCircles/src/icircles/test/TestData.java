package icircles.test;

//import icircles.recomposition.RecompositionStrategy;
//import icircles.decomposition.DecompositionStrategy;

public class TestData {

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
        108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118//,224
    };  // a set of tests of particular interest
    //public static int TASK = RUN_TEST_LIST;
    public static int TASK = VIEW_TEST_LIST;
    //public static int TASK = RUN_ALL_TESTS;
    //public static int TASK = VIEW_ALL_TESTS;
    
    public static TestDatum[] test_data = {
    	/*0*/new TestDatum( "a", 80.36472146975977),
    	/*1*/new TestDatum( "a b", 131.80238317020599),
    	/*2*/new TestDatum( "a b c", 198.0504849890292),
    	/*3*/new TestDatum( "ab", 163.01391290713627),
    	/*4*/new TestDatum( "a ab", 162.98095149492545),
    	/*5*/new TestDatum( "a b ab", 151.9122309318273),
    	/*6*/new TestDatum( "a b ac", 213.41472482428395),
    	/*7*/new TestDatum( "a b c ab", 221.5671462932229),
    	/*8*/new TestDatum( "ab ac", 249.5006443263974),
    	/*9*/new TestDatum( "a ab ac", 249.50077903402826),
    	/*10*/new TestDatum( "a b ab ac", 245.9944471724825),
    	/*11*/new TestDatum( "a b c ab ac", 234.5355413899492),
    	/*12*/new TestDatum( "a bc", 213.5605259628382),
    	/*13*/new TestDatum( "a ab bc", 236.8514605614278),
    	/*14*/new TestDatum( "a b ac bc", 234.7093158673113),
    	/*15*/new TestDatum( "ab ac bc", 346.55988801316926),
    	/*16*/new TestDatum( "a ab ac bc", 341.6340407671089),
    	/*17*/new TestDatum( "a b ab ac bc", 346.4648764035928),
    	/*18*/new TestDatum( "a b c ab ac bc", 321.54880628356665),
    	/*19*/new TestDatum( "abc", 263.1574283955421),
    	/*20*/new TestDatum( "a abc", 262.97802217273033),
    	/*21*/new TestDatum( "a b abc", 251.97181237544484),
    	/*22*/new TestDatum( "a b c abc", 330.6385978152413),
    	/*23*/new TestDatum( "ab abc", 263.06030257152827),
    	/*24*/new TestDatum( "a ab abc", 262.9775108773818),
    	/*25*/new TestDatum( "a b ab abc", 251.97249855160848),
    	/*26*/new TestDatum( "a b ac abc", 319.48600785950435),
    	/*27*/new TestDatum( "a b c ab abc", 330.52851131017997),
    	/*28*/new TestDatum( "ab ac abc", 262.73300055158813),
    	/*29*/new TestDatum( "a ab ac abc", 262.73319789981565),
    	/*30*/new TestDatum( "a b ab ac abc", 247.95317293651647),
    	/*31*/new TestDatum( "a b c ab ac abc", 347.65926725464857),
    	/*32*/new TestDatum( "a bc abc", 319.672763675523),
    	/*33*/new TestDatum( "a ab bc abc", 248.7631632728904),
    	/*34*/new TestDatum( "a b ac bc abc", 347.8798400081099),
    	/*35*/new TestDatum( "ab ac bc abc", 345.3100997530271),
    	/*36*/new TestDatum( "a ab ac bc abc", 355.5731276324365),
    	/*37*/new TestDatum( "a b ab ac bc abc", 345.19233268331016),
    	/*38*/new TestDatum( "a b c ab ac bc abc", 240.48736618824523),
    	/*39*/new TestDatum( "ab b", 163.01410335903373),
    	/*40*/new TestDatum( "a ab b", 151.9122309318273),
    	/*41*/new TestDatum( "bc a b ", 213.41292101743107),
    	/*42*/new TestDatum( "a ab c", 219.07537611817557),
    	/*43*/new TestDatum( "a abc abcd", 385.07434552406465),
    	/*44*/new TestDatum( "abc b c ab ac bc", 345.3107955291445),
    	/*45*/new TestDatum( "a b c ab ac bc", 321.54880628356665),
    	/*46*/new TestDatum( "a b c ab ac abc", 347.65926725464857),
    	/*47*/new TestDatum( "a b ab ac bc abc", 345.19233268331016),
    	/*48*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd", 487.32611513844887),
    	/*49*/new TestDatum( "a b ab c ac bc abc cd acd bcd abcd cde acde bcde abcde", 523.5727860360485),
    	/*50*/new TestDatum( "a b ab c ac bc abc d ad bd abd cd acd bcd abcd cde acde bcde abcde", 673.813627985722),
    	/*51*/new TestDatum( "abcd abce", 525.2880511162579),
    	/*52*/new TestDatum( "a ab c cd", 310.0881210592595),
    	/*53*/new TestDatum( "a c ab bc", 225.67901299330262),
    	/*54*/new TestDatum( "a b ac bc bcd d", 463.7832887737619),
    	/*55*/new TestDatum( "abcd abce de", 1032.657444673156),
    	/*56*/new TestDatum( "a b ab c ac bc abc df adf bdf abdf cd acd bcd abcd cde acde bcde abcde", 899.3500955512374),
    	/*57*/new TestDatum( "abd abc dc", 623.0710037690604),
    	/*58*/new TestDatum( "a b ab c ac bc abc p q pq r pr qr pqr x bx px", 798.6583605368851),
    	/*59*/new TestDatum( "a b ab c ac d ad e ae f af", 629.1677827566442),
    	/*60*/new TestDatum( "a b c d cd ae be e ce de cde", 481.2444661606544),
    	/*61*/new TestDatum( "a b c d cd ae be e ce de cde ef", 638.1848156544418),
    	/*62*/new TestDatum( "a b c ab ac bc abc ad", 357.5780458153101),
    	/*63*/new TestDatum( "a b c ab ac bc abc abd", 345.0823308783294),
    	/*64*/new TestDatum( "a b c ab ac bc abc abcd", 345.852737714166),
    	/*65*/new TestDatum( "ad bd cd abd acd bcd abcd d", 393.00302006162656),
    	/*66*/new TestDatum( "a b c ab ac bc abc ad bd cd", 593.3126547607693),
    	/*67*/new TestDatum( "a b c ab ac bc abc abd bcd acd", 607.0629788729966),
    	/*68*/new TestDatum( "a b c ab ac bc abc ad d", 332.61835499550835),
    	/*69*/new TestDatum( "a b c ab ac bc abc ad abd", 343.98096068233),
    	/*70*/new TestDatum( "a b c ab ac bc abc abd abcd", 343.0238595522451),
    	/*71*/new TestDatum( "a b c ab ac bc abc ad d be e cf f", 566.2309876684462),
    	/*72*/new TestDatum( "a b c ab ac bc abc ad bd abd d", 359.07120005194946),
    	/*73*/new TestDatum( "a b c ab ac bc abc acd bcd abcd cd", 370.6209389711012),
    	/*74*/new TestDatum( "a ab b ac c ad d be e cf f dg g", 750.6120821927219),
    	/*75*/new TestDatum( "a ab b ac c ad d be e cf f dg g eh h fi i gj j ak k kl l lm m", 2878.1526309125193),
    	/*76*/new TestDatum( "ab ac abc ad ae ade", 514.8615577681419),
    	/*77*/new TestDatum( "a b ab c ac abd ace", 477.54566400961136),
    	/*78*/new TestDatum( "a b ab c ac d ad be ce de", 911.7919119972838),
    	/*79*/new TestDatum( "a b ab c ac d ad ae be ce de", 970.1589540326663),
    	/*80*/new TestDatum( "a b ab c ac abd ace acef acefg", 825.2035769231204),
    	/*81*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg", 3003.922903989048),
    	/*82*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc", 3225.689791860058),
    	/*83*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj", 3911.2890090142337),
    	/*84*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l", 4499.361353150288),
    	/*85*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc", 4457.055567832824),
    	/*86*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al", 6279.477475167126),
    	/*87*/new TestDatum( "qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s", 25482.19335371067),
    	/*88*/new TestDatum( ",", 151.9122309318273),
    	/*89*/new TestDatum( ",.", 151.9122309318273),
    	/*90*/new TestDatum( "a,", 151.9122309318273),
    	/*91*/new TestDatum( "a,.", 151.9122309318273),
    	/*92*/new TestDatum( "a,a", 151.9122309318273),
    	/*93*/new TestDatum( "a,. a", 151.9122309318273),
    	/*94*/new TestDatum( "a b ab,", 151.9122309318273),
    	/*95*/new TestDatum( "a b ab, a", 151.9122309318273),
    	/*96*/new TestDatum( "a b ab, b", 151.9122309318273),
    	/*97*/new TestDatum( "a b ab, ab", 151.9122309318273),
    	/*98*/new TestDatum( "a b ab, .", 151.9122309318273),
    	/*99*/new TestDatum( "a b ab, . a", 151.9122309318273),
    	/*100*/new TestDatum( "a b ab, . b", 151.9122309318273),
    	/*101*/new TestDatum( "a b ab, . a b", 151.9122309318273),
    	/*102*/new TestDatum( "a b ab, . a b ab", 151.9122309318273),
    	/*103*/new TestDatum( "a ab c abc, ", 151.9122309318273),
    	/*104*/new TestDatum( "a ab c abc,.", 151.9122309318273),
    	/*105*/new TestDatum( "a ab c abc,a", 151.9122309318273),
    	/*106*/new TestDatum( "a ab c abc,ab", 151.9122309318273),
    	/*107*/new TestDatum( "a ab c abc,a ab", 151.9122309318273),
    	/*108*/new TestDatum( "a b ab, ,a", 151.9122309318273),
    	/*109*/new TestDatum( "a b ab, ,b", 151.9122309318273),
    	/*110*/new TestDatum( "a b ab, ,a, b", 151.9122309318273),
    	/*111*/new TestDatum( "a b ab, ,., a", 151.9122309318273),
    	/*112*/new TestDatum( "a b ab, ,ab", 151.9122309318273),
    	/*113*/new TestDatum( "a b ab, ,a, ab", 151.9122309318273),
    	/*114*/new TestDatum( "a b ab, ,b, ab", 151.9122309318273),
    	/*115*/new TestDatum( "a b ab, ,a, ab, .", 151.9122309318273),
    	/*116*/new TestDatum( "a b ab, ,a, ab, ., b", 151.9122309318273),
    	/*117*/new TestDatum( "a b ab, ,a b", 151.9122309318273),
    	/*118*/new TestDatum( "a b ab, ,a b, . ab", 151.9122309318273),
    };
}
