package icircles.abstractDescription;

import icircles.util.DEB;

public class AbstractCurve implements Comparable<AbstractCurve> {

    static int id = 0;
    CurveLabel m_label;
    int m_id;

    public AbstractCurve(CurveLabel label) {
        id++;
        m_id = id;
        m_label = label;
    }

    public CurveLabel getLabel() {
        return m_label;
    }

    public AbstractCurve clone() {
        return new AbstractCurve(m_label);
    }

    public int compareTo(AbstractCurve o) {
        int tmp = m_label.compareTo(o.m_label);
        if (tmp != 0) {
            return tmp;
        }
        int this_id = m_id;
        int other_id = o.m_id;
        return (this_id < other_id) ? -1 : (this_id == other_id) ? 0 : 1;
    }

    public String debug() {
        if (DEB.level == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean deb_level_was_high = false;
        if (DEB.level > 4) {
            sb.append("contour(");
            deb_level_was_high = true;
            DEB.level--;
        }
        sb.append(m_label.debug());
        if (deb_level_was_high) {
            DEB.level++;
            sb.append("_" + m_id + ")@");
            sb.append(hashCode());
        }
        return sb.toString();
    }

    public boolean matches_label(AbstractCurve c) {
        return m_label == c.m_label;
    }

    public String debugWithId() {
        return debug() + "_" + m_id;
    }

    public double checksum() {
        if (DEB.level == 2) {
            System.out.println("build checksum from " + m_label + " and " + m_id + "\n");
        }
        return m_label.checksum() * m_id;
    }

    public static void reset_id_counter() {
        id = 0;
    }
}
