package icircles.recomposition;

import java.util.ArrayList;
import java.util.Iterator;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;
import icircles.abstractDualGraph.AbstractDualEdge;
import icircles.abstractDualGraph.AbstractDualGraph;
import icircles.abstractDualGraph.AbstractDualNode;

public class RecompositionStrategySinglyPierced extends RecompositionStrategy {

    public ArrayList<Cluster> make_clusters(
            ArrayList<AbstractBasicRegion> zonesToSplit) {

        if (DEB.level > 1) {
            System.out.println("recomposition stratgey is singly peirced");
        }

        // Look for pairs of AbstractBasicRegions which differ by just a
        // single AbstractCurve - these pairs are potential double-clusters

        AbstractDualGraph adg = new AbstractDualGraph(zonesToSplit);
        ArrayList<Cluster> result = seekSinglePiercings(adg);
        return result;
    }

    public static ArrayList<Cluster> seekSinglePiercings(AbstractDualGraph adg) {
        ArrayList<Cluster> result = new ArrayList<Cluster>();
        for (AbstractDualEdge e = adg.getLowDegreeEdge();
                e != null;
                e = adg.getLowDegreeEdge()) {
            Cluster c = new Cluster(e.from.abr, e.to.abr);
            result.add(c);
            if (DEB.level > 2) {
                System.out.println("made single-peirced cluster " + (c.debug()) + "\n");
                System.out.println("graph before trimming for cluster " + (adg.debug()) + "\n");
            }
            adg.remove(e.from);
            adg.remove(e.to);
            if (DEB.level > 2) {
                System.out.println("graph after trimming for cluster " + adg.debug() + "\n");
            }
        }
        DEB.assertCondition(adg.getNumEdges() == 0, "non-empty adg edge set");
        result.addAll(seekNestedPiercings(adg));
        return result;
    }

    public static ArrayList<Cluster> seekNestedPiercings(AbstractDualGraph adg) {
        ArrayList<Cluster> result = new ArrayList<Cluster>();
        Iterator<AbstractDualNode> nIt = adg.getNodeIterator();
        while (nIt.hasNext()) {
            AbstractDualNode n = nIt.next();
            result.add(new Cluster(n.abr));
            if (DEB.level > 2) {
                System.out.println("adding nested cluster " + n.abr.debug());
            }
        }
        return result;
    }
}
