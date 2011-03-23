package icircles.abstractDualGraph;

import icircles.abstractDescription.AbstractCurve;

public class AbstractDualEdge {

    public AbstractDualEdge(AbstractDualNode from, AbstractDualNode to,
            AbstractCurve label) {
        this.from = from;
        this.to = to;
        this.label = label;
    }
    public AbstractDualNode from;
    public AbstractDualNode to;
    public AbstractCurve label;
}
