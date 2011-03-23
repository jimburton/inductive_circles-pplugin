package icircles.abstractDescription;

import java.util.TreeSet;

import icircles.util.DEB;

public class CurveLabel implements Comparable<CurveLabel> {

    String m_label;
    static TreeSet<CurveLabel> m_library = new TreeSet<CurveLabel>();

    public static void clearLibrary() {
        m_library.clear();
    }

    private CurveLabel(String label) {
        m_label = label;
    }

    public static CurveLabel get(String label) {
        for (CurveLabel alreadyThere : m_library) {
            if (alreadyThere.m_label.equals(label)) {
                return alreadyThere;
            }
        }

        CurveLabel result = new CurveLabel(label);
        m_library.add(result);
        return result;
    }

    public String debug() {
        if (DEB.level == 0) {
            return "";
        } else //if(Debug.level == 1)
        {
            return m_label;
        }
//		else
//		{
//			return m_label + "@"+ hashCode();
//		}

    }

    public int compareTo(CurveLabel other) {
        return m_label.compareTo(other.m_label);
    }

    public double checksum() {
        double result = 0.0;
        double scaling = 1.1;
        for (int i = 0; i < m_label.length(); i++) {
            result += (int) (m_label.charAt(i)) * scaling;
            scaling += 0.01;
        }
        return result;
    }

    public boolean isLabelled(String string) {
        return string.equals(m_label);

    }

    public String getLabel() {
        return m_label;
    }
}
