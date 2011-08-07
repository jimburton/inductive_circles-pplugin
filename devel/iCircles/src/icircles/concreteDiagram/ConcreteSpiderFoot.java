package icircles.concreteDiagram;

import java.awt.geom.Ellipse2D;

public // TODO make some data private
class ConcreteSpiderFoot{
	double footRad = 1;
public // TODO make some data private
	double x;
public // TODO make some data private
	double y;
	public Ellipse2D.Double getBlob() {
	    return new Ellipse2D.Double(x - footRad, y - footRad, 2 * footRad, 2 * footRad);
	}
}
