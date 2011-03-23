package icircles.abstractDualGraph;

import java.util.ArrayList;

import icircles.abstractDescription.AbstractBasicRegion;

public class AbstractDualNode {

    public AbstractBasicRegion abr;
    ArrayList<AbstractDualEdge> incidentEdges;

    AbstractDualNode(AbstractBasicRegion abr) {
        incidentEdges = new ArrayList<AbstractDualEdge>();
        this.abr = abr;
    }

    int degree() {
        return incidentEdges.size();
    }

    void removeEdge(AbstractDualEdge e) {
        incidentEdges.remove(e);
    }
}
