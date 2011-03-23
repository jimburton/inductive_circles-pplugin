package icircles.recomposition;

import java.util.ArrayList;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;
import icircles.abstractDualGraph.AbstractDualGraph;
import icircles.abstractDualGraph.AbstractDualNode;

public class RecompositionStrategyDoublyPierced extends RecompositionStrategy {

    public ArrayList<Cluster> make_clusters(
            ArrayList<AbstractBasicRegion> zonesToSplit) {

        if (DEB.level > 1) {
            System.out.println("recomposition stratgey is doubly peirced");
        }

        // Look for four-tuples of AbstractBasicRegions which differ by
        // two AbstractCurves - these four-tuples are potential double-clusters

        ArrayList<Cluster> result = new ArrayList<Cluster>();

        AbstractDualGraph adg = new AbstractDualGraph(zonesToSplit);
        if (DEB.level > 2) {
            System.out.println("zonesToSplit is ");
            for (AbstractBasicRegion abr : zonesToSplit) {
                System.out.println("abr:" + abr.debug());
            }
        }
        for (ArrayList<AbstractDualNode> nodes = adg.getFourTuple();
                nodes != null;
                nodes = adg.getFourTuple()) {
            if (nodes.size() == 0) {
                break;
            }
            Cluster c = new Cluster(nodes.get(0).abr,
                    nodes.get(1).abr,
                    nodes.get(2).abr,
                    nodes.get(3).abr);
            result.add(c);
            if (DEB.level > 2) {
                System.out.println("made cluster " + (c.debug()) + "\n");
                System.out.println("graph before trimming for cluster " + (adg.debug()) + "\n");
            }
            adg.remove(nodes.get(0));
            adg.remove(nodes.get(1));
            adg.remove(nodes.get(2));
            adg.remove(nodes.get(3));
            if (DEB.level > 2) {
                System.out.println("graph after trimming for cluster " + adg.debug() + "\n");
            }
        }

        result.addAll(RecompositionStrategySinglyPierced.seekSinglePiercings(adg));

        return result;
    }
}
