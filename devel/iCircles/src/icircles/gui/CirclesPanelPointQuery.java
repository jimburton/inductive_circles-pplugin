package icircles.gui;

import icircles.concreteDiagram.CircleContour;
import icircles.concreteDiagram.ConcreteSpider;
import icircles.concreteDiagram.ConcreteSpiderFoot;
import icircles.concreteDiagram.ConcreteZone;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CirclesPanelPointQuery {

    private ConcreteDiagramPointQuery cdpq;

    CirclesPanelPointQuery(CirclesPanel cp, Point2D.Double p, double tol) {
        this.cdpq = new ConcreteDiagramPointQuery(cp.getDiagram(), p, tol);
    }

    ArrayList<CircleContour> getContours() {
        return cdpq.getContours();
    }

    ConcreteZone getConcreteZone() {
        return cdpq.getConcreteZone();
    }

    class SpiderInfo {

        ConcreteSpiderFoot foot;
        ConcreteSpider spider;

        SpiderInfo(ConcreteSpider s, ConcreteSpiderFoot f) {
            foot = f;
            spider = s;
        }
    }

    SpiderInfo getSpider() {
        ConcreteSpider s = cdpq.getSpider();
        if (s != null) {
            ConcreteSpiderFoot foot = cdpq.getSpiderFoot();
            SpiderInfo result = new SpiderInfo(s, foot);
            return result;
        }
        return null;
    }
}
