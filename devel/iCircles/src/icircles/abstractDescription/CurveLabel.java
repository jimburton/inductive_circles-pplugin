package icircles.abstractDescription;

import java.util.TreeSet;

import icircles.util.DEB;

public class CurveLabel implements Comparable<CurveLabel> {

    String m_label;
    static TreeSet<CurveLabel> m_library = new TreeSet<CurveLabel>();
    // or use a WeakReference - then the WeakHashMap will be emptied when
    // there will be no references for the members any more
    // but beware to put the item into the WeakHashMap just after you've 
    // extracted it!

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
        return (double)m_label.hashCode() * 1E-7;
    }

    public boolean isLabelled(String string) {
        return string.equals(m_label);

    }

    public String getLabel() {
        return m_label;
    }
}
