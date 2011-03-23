package icircles.abstractDualGraph;

import java.util.ArrayList;
import java.util.Iterator;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;
import icircles.abstractDescription.AbstractCurve;

public class AbstractDualGraph {

    ArrayList<AbstractDualNode> nodes;
    ArrayList<AbstractDualEdge> edges;

    public AbstractDualGraph(ArrayList<AbstractBasicRegion> abrs) {
        nodes = new ArrayList<AbstractDualNode>();
        edges = new ArrayList<AbstractDualEdge>();
        // Each abr becomes a node.
        // Neighbouring abrs get edges added between them.
        for (AbstractBasicRegion abr : abrs) {
            nodes.add(new AbstractDualNode(abr));
        }
        for (AbstractDualNode n : nodes) {
            boolean found_node_again = false;
            for (AbstractDualNode n2 : nodes) {
                if (!found_node_again) {
                    if (n2 == n) {
                        found_node_again = true;
                    }
                } else {
                    AbstractCurve straddlingCurve =
                            n.abr.getStraddledContour(n2.abr);
                    if (straddlingCurve != null) {
                        add_edge(n, n2, straddlingCurve);
                    }
                }
            }
        }
    }

    private void add_edge(AbstractDualNode n, AbstractDualNode n2,
            AbstractCurve straddlingCurve) {
        AbstractDualEdge e = new AbstractDualEdge(n, n2, straddlingCurve);
        n.incidentEdges.add(e);
        n2.incidentEdges.add(e);
        edges.add(e);
    }

    public void remove(AbstractDualEdge e) {
        e.from.removeEdge(e);
        e.to.removeEdge(e);
        edges.remove(e);
    }

    public void remove(AbstractDualNode n) {
        while (n.incidentEdges.size() != 0) {
            remove(n.incidentEdges.get(0));
        }
        nodes.remove(n);
    }

    public AbstractDualEdge getLowDegreeEdge() {
        // find a lowest-degree vertex, and from that,
        // choose the edge to its lowest-degree neighbour
        if (DEB.level > 3) {
            System.out.println("graph is " + this.debug());
        }
        int lowestDegree = Integer.MAX_VALUE;
        AbstractDualNode lowestDegreeNode = null;
        for (AbstractDualNode n : nodes) {
            int thisDegree = n.degree();

            if (thisDegree == 0) {
                continue; // ignore isolated nodes when picking a low-degree edge
            }
            if (thisDegree < lowestDegree) {
                lowestDegreeNode = n;
                lowestDegree = thisDegree;
            }
        }
        if (lowestDegreeNode == null) {
            return null;
        }

        lowestDegree = Integer.MAX_VALUE;
        AbstractDualEdge result = null;
        for (AbstractDualEdge e : lowestDegreeNode.incidentEdges) {
            AbstractDualNode otherNode;
            if (e.from == lowestDegreeNode) {
                otherNode = e.to;
            } else {
                DEB.assertCondition(e.to == lowestDegreeNode, "inconcistent graph nodes");
                otherNode = e.from;
            }
            int otherDegree = otherNode.degree();
            if (otherDegree < lowestDegree) {
                lowestDegree = otherDegree;
                result = e;
            }
        }
        return result;
    }

    public int getNumEdges() {
        return edges.size();
    }

    public Iterator<AbstractDualNode> getNodeIterator() {
        return nodes.iterator();
    }

    public String debug() {
        String result = "nodes : ";
        boolean isFirst = true;
        for (AbstractDualNode n : nodes) {
            if (!isFirst) {
                result += ",";
            } else {
                isFirst = false;
            }
            result += n.abr.debug();
        }
        result += " edges : ";
        isFirst = true;
        for (AbstractDualEdge e : edges) {
            if (!isFirst) {
                result += ",";
            } else {
                isFirst = false;
            }
            result += e.from.abr.debug();
            result += "->";
            result += e.to.abr.debug();
        }
        return result;
    }

    public ArrayList<AbstractDualNode> getFourTuple() {

        for (AbstractDualNode n : nodes) {
            for (AbstractDualEdge e : n.incidentEdges) {
                if (e.from != n) {
                    continue;
                }
                AbstractDualNode n2 = e.to;
                for (AbstractDualEdge e2 : n2.incidentEdges) {
                    if (e2.from != n2) {
                        continue;
                    }

                    if (DEB.level > 2) {
                        // we have edges e and e2 - are these part of a square?
                        System.out.println("edges are " + e.from.abr.debug() + "->" + e.to.abr.debug() + "\n and "
                                + e2.from.abr.debug() + "->" + e2.to.abr.debug());
                    }

                    // look for an edge from n with the same label as e2
                    for (AbstractDualEdge e3 : n.incidentEdges) {
                        if (e3.label == e2.label) {
                            // found a square
                            ArrayList<AbstractDualNode> result = new ArrayList<AbstractDualNode>();
                            result.add(n);
                            result.add(n2);
                            result.add(e3.to);
                            result.add(e2.to);
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }
}
