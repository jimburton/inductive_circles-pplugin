package icircles.abstractDescription;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import icircles.util.DEB;

/**
 * An AbstractDescription encapsulates the elements of a diagram, with no drawn information.
 * A diagram comprises a set of AbstractCurves (the contours).
 * A set of AbstractBasicRegions is given (zones which must be present.
 * <p>
 * An AbstractDiagram is consistent if 
 * <p>1. the contours in each of the AbstractBasicRegions match those
 * in m_contours. 
 * <p>2. every valid diagram includes the "outside" zone. 
 * TODO add a coherence check on these internal checks.
 */
public class AbstractDescription {

    TreeSet<AbstractCurve> m_contours;
    TreeSet<AbstractBasicRegion> m_zones;
    
    //TreeSet<AbstractBasicRegion> m_shaded_zones;
    //TreeSet<AbstractSpider> m_spiders;
    
    //class AbstractSpider{
    //	TreeSet<AbstractBasicRegion> m_feet;
    //}

    public AbstractDescription(Set<AbstractCurve> contours, Set<AbstractBasicRegion> zones) {
        m_contours = new TreeSet<AbstractCurve>(contours);
        m_zones = new TreeSet<AbstractBasicRegion>(zones);
    }

    public AbstractCurve getFirstContour() {
        if (m_contours.size() == 0) {
            return null;
        }
        return m_contours.first();
    }

    public AbstractCurve getLastContour() {
        if (m_contours.size() == 0) {
            return null;
        }
        return m_contours.last();
    }

    public Iterator<AbstractCurve> getContourIterator() {
        return m_contours.iterator();
    }

    public int getNumContours() {
        return m_contours.size();
    }

    public Iterator<AbstractBasicRegion> getZoneIterator() {
        return m_zones.iterator();
    }
    // expensive - do not use just for querying

    public TreeSet<AbstractCurve> getCopyOfContours() {
        return new TreeSet<AbstractCurve>(m_contours);
    }
    // expensive - do not use just for querying

    public TreeSet<AbstractBasicRegion> getCopyOfZones() {
        return new TreeSet<AbstractBasicRegion>(m_zones);
    }

    public static AbstractDescription makeForTesting(String s) {
        TreeSet<AbstractBasicRegion> ad_zones = new TreeSet<AbstractBasicRegion>();
        ad_zones.add(AbstractBasicRegion.get(new TreeSet<AbstractCurve>()));
        StringTokenizer st = new StringTokenizer(s);
        HashMap<CurveLabel, AbstractCurve> contours = new HashMap<CurveLabel, AbstractCurve>();
        while (st.hasMoreTokens()) {
            String word = st.nextToken();
            TreeSet<AbstractCurve> zoneContours = new TreeSet<AbstractCurve>();
            for (int i = 0; i < word.length(); i++) {
                String character = "" + word.charAt(i);
                CurveLabel cl = CurveLabel.get(character);
                if (!contours.containsKey(cl)) {
                    contours.put(cl, new AbstractCurve(cl));
                }
                zoneContours.add(contours.get(cl));
            }
            ad_zones.add(AbstractBasicRegion.get(zoneContours));
        }
        TreeSet<AbstractCurve> ad_contours = new TreeSet<AbstractCurve>(contours.values());
        return new AbstractDescription(ad_contours, ad_zones);
    }
    /*
    public static void main(String args[])
    {

    CurveLabel a = CurveLabel.get("a");
    CurveLabel a2 = CurveLabel.get("a");

    Debug.level = 2;
    System.out.println("contour labels equal? "+a.debug()+","+a2.debug());
    System.out.println("contour labels equal? "+(a==a2));

    AbstractCurve ca1 = new AbstractCurve(a);
    AbstractCurve ca2 = new AbstractCurve(a);

    System.out.println("contours equal? "+a.debug()+","+a2.debug());
    System.out.println("contours equal? "+(a==a2));

    TreeSet<AbstractCurve> ts = new TreeSet<AbstractCurve>();
    AbstractBasicRegion z0 = AbstractBasicRegion.get(ts);
    System.out.println("outside zone "+z0.debug());

    ts.add(ca1);
    AbstractBasicRegion za = AbstractBasicRegion.get(ts);

    AbstractBasicRegion za2;
    {
    TreeSet<AbstractCurve> ts2 = new TreeSet<AbstractCurve>();
    ts2.add(ca2);
    za2 = AbstractBasicRegion.get(ts2);
    System.out.println("za==za2 ?" + (za == za2));
    }

    System.out.println("zone in a "+za.debug());
    System.out.println("zone in a "+za2.debug());


    CurveLabel b = CurveLabel.get("b");
    AbstractCurve cb = new AbstractCurve(b);
    ts.add(cb);
    AbstractBasicRegion zab = AbstractBasicRegion.get(ts);
    System.out.println("zone in ab "+zab.debug());

    ts.remove(ca1);
    AbstractBasicRegion zb = AbstractBasicRegion.get(ts);
    System.out.println("zone in b "+zb.debug());

    ts.add(ca1);
    AbstractBasicRegion zab2 = AbstractBasicRegion.get(ts);
    System.out.println("zone2 in ab "+zab2.debug());

    System.out.println("zab==zab2 ?" + (zab == zab2));

    ts.clear();
    TreeSet<AbstractBasicRegion> tsz = new TreeSet<AbstractBasicRegion>();

    debug_abstract_description(ts, tsz);

    ts.add(ca1);
    debug_abstract_description(ts, tsz);

    ts.add(ca1);
    debug_abstract_description(ts, tsz);

    ts.add(ca2);
    debug_abstract_description(ts, tsz);

    ts.add(cb);
    debug_abstract_description(ts, tsz);

    tsz.add(z0);
    debug_abstract_description(ts, tsz);

    tsz.add(za);
    debug_abstract_description(ts, tsz);

    tsz.add(zab);
    debug_abstract_description(ts, tsz);

    tsz.add(zb);
    debug_abstract_description(ts, tsz);

    //ContourLabel c = ContourLabel.get("c");
    //ContourLabel d = ContourLabel.get("d");
    //ContourLabel e = ContourLabel.get("e");

    System.out.println("\"\" is " + makeForTesting("").debug());
    System.out.println("\"a\" is " + makeForTesting("a").debug());
    System.out.println("\"a a\" is " + makeForTesting("a a").debug());
    System.out.println("\"a ab\" is " + makeForTesting("a ab").debug());

    }
    private static void debug_abstract_description(
    TreeSet<AbstractCurve> ts, TreeSet<AbstractBasicRegion> tsz)
    {
    AbstractDescription ad = new AbstractDescription(ts, tsz);
    System.out.println("ad is " + ad.debug());
    }
     */

    public String debug() {
        if (DEB.level == 0) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        b.append("labels:");
        boolean first = true;
        if (DEB.level > 1) {
            b.append("{");
        }
        for (AbstractCurve c : m_contours) {
            if (!first) {
                b.append(",");
            }
            b.append(c.debug());
            first = false;
        }
        if (DEB.level > 1) {
            b.append("}");
        }
        b.append("\n");
        b.append("zones:");
        if (DEB.level > 1) {
            b.append("{");
        }
        first = true;
        for (AbstractBasicRegion z : m_zones) {
            if (!first) {
                b.append(",");
            }
            if (DEB.level > 1) {
                b.append("\n");
            }
            b.append(z.debug());
            first = false;
        }
        if (DEB.level > 1) {
            b.append("}");
        }
        b.append("\n");

        return b.toString();
    }

    public String debugAsSentence() {
        HashMap<AbstractCurve, String> printable = new HashMap<AbstractCurve, String>();
        for (AbstractCurve c : m_contours) {
            printable.put(c, print_contour(c));
        }
        StringBuilder b = new StringBuilder();
        boolean first = true;
        for (AbstractBasicRegion z : m_zones) {
            if (!first) {
                b.append(",");
            }
            Iterator<AbstractCurve> c_it = z.getContourIterator();
            boolean printed_something = false;
            while (c_it.hasNext()) {
                AbstractCurve c = c_it.next();
                b.append(printable.get(c));
                printed_something = true;
            }
            if (!printed_something) {
                b.append("0");
            }
            first = false;
        }
        return b.toString();
    }

    public String print_contour(AbstractCurve c) {
        if (one_of_multiple_instances(c)) {
            return c.debugWithId();
        } else {
            return c.debug();
        }
    }

    boolean one_of_multiple_instances(AbstractCurve c) {
        for (AbstractCurve cc : m_contours) {
            if (cc != c && cc.matches_label(c)) {
                return true;
            }
        }
        return false;
    }

    public int getNumZones() {
        return m_zones.size();
    }

    public double checksum() {
        double scaling = 2.1;
        double result = 0.0;
        for (AbstractCurve c : m_contours) {
            result += c.checksum() * scaling;
            scaling += 0.07;
            scaling += 0.05;
            for (AbstractBasicRegion z : m_zones) {
                if (z.is_in(c)) {
                    result += z.checksum() * scaling;
                    scaling += 0.09;
                }
            }
        }
        return result;
    }

    public boolean includesLabel(CurveLabel l) {
        for (AbstractCurve c : m_contours) {
            if (c.getLabel() == l) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLabelEquivalentZone(AbstractBasicRegion z) {
        for (AbstractBasicRegion zone : m_zones) {
            if (zone.isLabelEquivalent(z)) {
                return true;
            }
        }
        return false;
    }
}

//junk change
