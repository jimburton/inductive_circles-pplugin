package icircles.decomposition;

import java.util.ArrayList;
import java.util.Iterator;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.AbstractCurve;
import icircles.abstractDescription.AbstractBasicRegion;

public class DecompositionStrategyInnermost extends DecompositionStrategy {

    void getContoursToRemove(AbstractDescription ad, ArrayList<AbstractCurve> toRemove) {
        toRemove.clear();
        if (DEB.level > 1) {
            System.out.println("recomposition stratgey is innermost");
        }

        // an innermost abstract contour has the fewest abstract basic regions inside
        int best_num_zones = ad.getNumZones() + 1;
        AbstractCurve best_contour = null;
        Iterator<AbstractCurve> c_it = ad.getContourIterator();
        while (c_it.hasNext()) {
            AbstractCurve c = c_it.next();
            int num_zones = 0;
            Iterator<AbstractBasicRegion> z_it = ad.getZoneIterator();
            while (z_it.hasNext()) {
                AbstractBasicRegion z = z_it.next();
                if (z.is_in(c)) {
                    num_zones++;
                }
            }
            if (num_zones < best_num_zones) {
                best_num_zones = num_zones;
                best_contour = c;
            }
        }
        toRemove.add(best_contour);
    }
}
