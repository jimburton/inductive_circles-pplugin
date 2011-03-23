package icircles.recomposition;

import java.util.ArrayList;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;

public class Cluster {

    ArrayList<AbstractBasicRegion> m_zones;

    public Cluster(AbstractBasicRegion z) {
        m_zones = new ArrayList<AbstractBasicRegion>();
        m_zones.add(z);
    }

    public Cluster(AbstractBasicRegion z1,
            AbstractBasicRegion z2) {
        DEB.assertCondition(z1.getStraddledContour(z2) != null, "non-adjacent cluster pair");
        m_zones = new ArrayList<AbstractBasicRegion>();
        m_zones.add(z1);
        m_zones.add(z2);
    }

    public Cluster(AbstractBasicRegion z1,
            AbstractBasicRegion z2,
            AbstractBasicRegion z3,
            AbstractBasicRegion z4) {
        DEB.assertCondition(z1.getStraddledContour(z2) != null, "non-adjacent cluster pair");
        DEB.assertCondition(z1.getStraddledContour(z3) != null, "non-adjacent cluster pair");
        DEB.assertCondition(z2.getStraddledContour(z4) == z1.getStraddledContour(z3), "non-adjacent cluster pair");
        DEB.assertCondition(z3.getStraddledContour(z4) == z1.getStraddledContour(z2), "non-adjacent cluster pair");
        m_zones = new ArrayList<AbstractBasicRegion>();
        m_zones.add(z1);
        m_zones.add(z2);
        m_zones.add(z3);
        m_zones.add(z4);
    }

    public ArrayList<AbstractBasicRegion> zones() {
        return m_zones;
    }

    public String debug() {
        String result = "{";
        boolean firstOne = true;
        for (AbstractBasicRegion abr : m_zones) {
            if (!firstOne) {
                result += ",";
            }
            result = result + abr.debug();
            firstOne = false;
        }
        result += "}";
        return result;
    }
}
