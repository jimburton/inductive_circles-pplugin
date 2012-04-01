package icircles.concreteDiagram;

public // TODO make some data private
class ConcreteSpiderLeg{
public // TODO make some data private
	ConcreteSpiderFoot from;
public // TODO make some data private
	ConcreteSpiderFoot to;
public double checksum() {
	return 1.1 * from.checksum() + 2.1 * to.checksum();
}
}
