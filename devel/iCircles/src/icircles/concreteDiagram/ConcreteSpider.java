package icircles.concreteDiagram;

import java.util.ArrayList;

public class ConcreteSpider {
public int footRad = 1;
public // TODO make some data private
	ArrayList<ConcreteSpiderFoot> feet;
public // TODO make some data private
	ArrayList<ConcreteSpiderLeg> legs;

public // TODO make some data private
	ConcreteSpider()
	{
		feet = new ArrayList<ConcreteSpiderFoot>();
		legs = new ArrayList<ConcreteSpiderLeg>();
	}

}
