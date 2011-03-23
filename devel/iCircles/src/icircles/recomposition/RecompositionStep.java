package icircles.recomposition;

import java.util.ArrayList;
import java.util.Iterator;

import icircles.util.DEB;
import icircles.abstractDescription.CurveLabel;
import icircles.abstractDescription.AbstractDescription;

public class RecompositionStep {

    AbstractDescription m_from;
    AbstractDescription m_to;
    ArrayList<RecompData> m_added_contour_data;

    public RecompositionStep(AbstractDescription from,
            AbstractDescription to,
            ArrayList<RecompData> added_contour_data) {
        m_from = from;
        m_to = to;
        m_added_contour_data = added_contour_data;
        DEB.assertCondition(added_contour_data.size() > 0, "no added curve in recomp");
        CurveLabel cl = added_contour_data.get(0).added_curve.getLabel();
        for (RecompData rp : added_contour_data) {
            DEB.assertCondition(rp.added_curve.getLabel() == cl, "mixed curves added in recomp");
        }

        DEB.assertCondition(!from.includesLabel(cl), "added curve already present");
        DEB.assertCondition(to.includesLabel(cl), "added curve wasn't added");
    }

    public String debug() {
        if (DEB.level == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
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

        return sb.toString();
    }

    public AbstractDescription to() {
        return m_to;
    }

    public static double checksum(ArrayList<RecompositionStep> rSteps) {
        double scaling = 11.23;
        double result = 0.0;
        for (RecompositionStep step : rSteps) {
            result += step.checksum() * scaling;
            scaling += 0.1;
        }
        return result;
    }

    private double checksum() {
        return 7.1 * m_from.checksum() + 7.3 * m_to.checksum();
    }

    public Iterator<RecompData> getRecompIterator() {
        return m_added_contour_data.iterator();
    }
}
