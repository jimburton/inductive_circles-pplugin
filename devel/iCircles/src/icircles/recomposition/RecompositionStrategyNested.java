package icircles.recomposition;

import java.util.ArrayList;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;

public class RecompositionStrategyNested extends RecompositionStrategy {

    public ArrayList<Cluster> make_clusters(
            ArrayList<AbstractBasicRegion> zones_to_split) {
        if (DEB.level > 1) {
            System.out.println("recomposition stratgey is nested");
        }
        ArrayList<Cluster> result = new ArrayList<Cluster>();
        for (AbstractBasicRegion z : zones_to_split) {
            result.add(new Cluster(z));
        }
        return result;
    }
}
