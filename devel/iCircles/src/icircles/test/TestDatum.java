package icircles.test;

public class TestDatum {

    public String description;
    //public int decomp_strategy;
    //public int recomp_strategy;
    public double expected_checksum;

    public TestDatum(String string, 
    		//int decomp_strategy,
            //int recomp_strategy, 
            double checksum) {
        description = string;
        //this.decomp_strategy = decomp_strategy;
        //this.recomp_strategy = recomp_strategy;
        expected_checksum = checksum;
    }
}
