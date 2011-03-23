package icircles.decomposition;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import icircles.util.DEB;
import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.AbstractCurve;
import icircles.abstractDescription.AbstractBasicRegion;

public class DecompositionStep {

    AbstractDescription m_from;
    AbstractDescription m_to;
    TreeMap<AbstractBasicRegion, AbstractBasicRegion> m_zones_moved;
    AbstractCurve m_removed;                  // in m_from but not m_to

    public DecompositionStep(
            AbstractDescription from,
            AbstractDescription to,
            TreeMap<AbstractBasicRegion, AbstractBasicRegion> zones_moved, // could be derived from from + to
            AbstractCurve removed) // could be derived from from + to
    {
        m_from = from;
        m_to = to;
        m_zones_moved = zones_moved;
        m_removed = removed;
    }

    public AbstractDescription target() {
        return m_to;
    }

    public String debug() {
        if (DEB.level == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("remove ");
        sb.append(m_from.print_contour(m_removed));
        if (DEB.level > 1) {
            sb.append("\n");
        }
        sb.append(" from ");
        sb.append(m_from.debugAsSentence());
        if (DEB.level > 1) {
            sb.append("\n");
        }
        sb.append(" to ");
        sb.append(m_to.debugAsSentence());
        if (DEB.level > 1) {
            sb.append("\n");
        }
        sb.append(" zonesMoved: ");
        Set<Map.Entry<AbstractBasicRegion, AbstractBasicRegion>> entries = m_zones_moved.entrySet();
        for (Map.Entry<AbstractBasicRegion, AbstractBasicRegion> z_map : entries) {
            sb.append("<");
            sb.append(z_map.getKey().debug());
            sb.append("->");
            sb.append(z_map.getValue().debug());
            sb.append(">");
        }
        return sb.toString();
    }

    public AbstractDescription to() {
        return m_to;
    }

    public AbstractDescription from() {
        return m_from;
    }

    public TreeMap<AbstractBasicRegion, AbstractBasicRegion> zones_moved() {
        return m_zones_moved;
    }

    public AbstractCurve removed() {
        return m_removed;
    }

    private double checksum() {
        return 1.1 * m_from.checksum() + 1.3 * m_to.checksum();
    }

    public static double checksum(ArrayList<DecompositionStep> d_steps) {
        double scaling = 1.11;
        double result = 0.0;
        for (DecompositionStep step : d_steps) {
            result += step.checksum() * scaling;
            scaling += 0.1;
        }
        return result;
    }
}
