package icircles.concreteDiagram;

import java.awt.geom.Ellipse2D;

public // TODO make some data private
class ConcreteSpiderFoot{
	
	public static double FOOT_SIZE = 4;
	
public // TODO make some data private
	double x;
public // TODO make some data private
	double y;
	public Ellipse2D.Double getBlob() {
		double rad = FOOT_SIZE;
	    return new Ellipse2D.Double(x - rad, y - rad, 2*rad, 2*rad);
	}
	public double checksum() {
		return x + 1.02* y;
	}
	
	public static void set_foot_size(int size)
	{
		FOOT_SIZE = size;
	}
}
