package icircles.concreteDiagram;

import java.awt.geom.Ellipse2D;

public // TODO make some data private
class ConcreteSpiderFoot{
	
	static int FOOT_SIZE = 5;
	
	public double footRad = 1;
public // TODO make some data private
	double x;
public // TODO make some data private
	double y;
	public Ellipse2D.Double getBlob() {
	    return new Ellipse2D.Double(x - footRad, y - footRad, FOOT_SIZE * footRad, FOOT_SIZE * footRad);
	}
	public double checksum() {
		return x + 1.02* y;
	}
	
	public static void set_foot_size(int size)
	{
		FOOT_SIZE = size;
	}
}
