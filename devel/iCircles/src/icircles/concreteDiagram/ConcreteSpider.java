package icircles.concreteDiagram;

import icircles.abstractDescription.AbstractSpider;

import java.util.ArrayList;

public class ConcreteSpider {
public int footRad = 1;
public // TODO make some data private
	ArrayList<ConcreteSpiderFoot> feet;
public // TODO make some data private
	ArrayList<ConcreteSpiderLeg> legs;

	public AbstractSpider as;

public ConcreteSpider(AbstractSpider as)
	{
		this.as = as;
		feet = new ArrayList<ConcreteSpiderFoot>();
		legs = new ArrayList<ConcreteSpiderLeg>();
	}

}
