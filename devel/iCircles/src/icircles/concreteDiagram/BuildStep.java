package icircles.concreteDiagram;

import java.util.ArrayList;

import icircles.recomposition.RecompData;

public class BuildStep {

    public ArrayList<RecompData> recomp_data;
    public BuildStep next = null;

    BuildStep(RecompData rd) {
        recomp_data = new ArrayList<RecompData>();
        recomp_data.add(rd);
    }
}
