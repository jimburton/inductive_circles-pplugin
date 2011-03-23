package icircles.abstractDescription;

import java.util.Iterator;
import java.util.TreeSet;

import icircles.util.DEB;

public class AbstractBasicRegion implements Comparable<AbstractBasicRegion> {

    TreeSet<AbstractCurve> m_in_set;
    static TreeSet<AbstractBasicRegion> m_library = new TreeSet<AbstractBasicRegion>();

    private AbstractBasicRegion(TreeSet<AbstractCurve> in_set) {
        m_in_set = in_set;
    }

    public static AbstractBasicRegion get(TreeSet<AbstractCurve> in_set) {
        for (AbstractBasicRegion alreadyThere : m_library) {
            if (alreadyThere.m_in_set.equals(in_set)) {
                return alreadyThere;
            }
        }

        TreeSet<AbstractCurve> tmp = new TreeSet<AbstractCurve>(in_set);
        AbstractBasicRegion result = new AbstractBasicRegion(tmp);
        m_library.add(result);
        return result;
    }

    public AbstractBasicRegion moveOutside(AbstractCurve c) {
        if (m_in_set.contains(c)) {
            TreeSet<AbstractCurve> contours = new TreeSet<AbstractCurve>(m_in_set);
            contours.remove(c);
            return get(contours);
        } else {
            return this;
        }
    }

    public int compareTo(AbstractBasicRegion other) {
        if (other.m_in_set.size() < m_in_set.size()) {
            return 1;
        } else if (other.m_in_set.size() > m_in_set.size()) {
            return -1;
        }

        // same sized in_set
        Iterator<AbstractCurve> this_it = m_in_set.iterator();
        Iterator<AbstractCurve> other_it = other.m_in_set.iterator();

        while (this_it.hasNext()) {
            AbstractCurve this_c = this_it.next();
            AbstractCurve other_c = other_it.next();
            int comp = this_c.compareTo(other_c);
            if (comp != 0) {
                return comp;
            }
        }
        return 0;
    }

    public String debug() {
        if (DEB.level == 0) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        if (DEB.level > 1) {
            b.append("(");
        }
        boolean first = true;
        for (AbstractCurve c : m_in_set) {
            if (!first && DEB.level > 1) {
                b.append(",");
            }
            b.append(c.debug());
            first = false;
        }
        if (DEB.level > 1) {
            b.append(")");
        }
        if (DEB.level > 3) {
            b.append(hashCode());
        }
        return b.toString();
    }

    public Iterator<AbstractCurve> getContourIterator() {
        return m_in_set.iterator();
    }

    public int getNumContours() {
        return m_in_set.size();
    }

    public AbstractCurve getStraddledContour(AbstractBasicRegion other) {
        int nc = getNumContours();
        int othernc = other.getNumContours();
        if (Math.abs(nc - othernc) != 1) {
            return null;
        } else if (nc < othernc) {
            return other.getStraddledContour(this);
        } else {
            // we have one more contour than other - are we neighbours?
            AbstractCurve result = null;
            Iterator<AbstractCurve> it = getContourIterator();
            while (it.hasNext()) {
                AbstractCurve ac = it.next();
                if (!other.is_in(ac)) {
                    if (result != null) {
                        return null; // found two contours here absent from other
                    } else {
                        result = ac;
                    }
                }
            }
            if (DEB.level > 2) {
                System.out.println("straddle : " + debug() + "->" + other.debug() + "=" + result.debug());
            }
            return result;
        }
    }

    public AbstractBasicRegion moved_in(AbstractCurve newCont) {
        TreeSet<AbstractCurve> conts = new TreeSet<AbstractCurve>(m_in_set);
        conts.add(newCont);
        return AbstractBasicRegion.get(conts);
    }

    public boolean is_in(AbstractCurve c) {
        return m_in_set.contains(c);
    }

    public double checksum() {
        double result = 0.0;
        double scaling = 3.1;
        for (AbstractCurve c : m_in_set) {
            result += c.checksum() * scaling;
            scaling += 0.09;
        }
        return result;
    }

    public static void clearLibrary() {
        m_library.clear();
    }

    public boolean isLabelEquivalent(AbstractBasicRegion z) {
        if (getNumContours() == z.getNumContours()) {
            if (z.getNumContours() == 0) {
                return true;
            } else {
                //System.out.println(" compare zones "+debug()+" and "+z.debug());
                Iterator<AbstractCurve> acIt = getContourIterator();
                AcItLoop:
                while (acIt.hasNext()) {
                    AbstractCurve thisAC = acIt.next();
                    // look for an AbstractCurve in z with the same label
                    Iterator<AbstractCurve> acIt2 = z.getContourIterator();
                    while (acIt2.hasNext()) {
                        AbstractCurve thatAC = acIt2.next();
                        //System.out.println(" compare abstract contours "+thisAC.debug()+" and "+thatAC.debug());
                        if (thisAC.matches_label(thatAC)) {
                            //System.out.println(" got match ");
                            continue AcItLoop;
                        }
                    }
                    //System.out.println(" no match for "+thisAC.debug());
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
