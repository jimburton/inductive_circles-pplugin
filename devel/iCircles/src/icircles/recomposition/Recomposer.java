package icircles.recomposition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.AbstractCurve;
import icircles.abstractDescription.AbstractBasicRegion;

import icircles.decomposition.DecompositionStep;

public class Recomposer {

    RecompositionStrategy strategy;

    public Recomposer(int recompStrategy) {
        strategy = RecompositionStrategy.getStrategy(recompStrategy);
    }

    Recomposer() {
        strategy = RecompositionStrategy.getStrategy();
    }

    public ArrayList<RecompositionStep> recompose(
            ArrayList<DecompositionStep> decomp_steps) {
        TreeMap<AbstractBasicRegion, AbstractBasicRegion> matched_zones =
                new TreeMap<AbstractBasicRegion, AbstractBasicRegion>(new ABRComparator());
        int n = decomp_steps.size();
        ArrayList<RecompositionStep> result =
                new ArrayList<RecompositionStep>(n);
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                result.add(recompose_step(
                        decomp_steps.get(i), result.get(n - 2 - i), matched_zones));
            } else {
                result.add(recompose_step(
                        decomp_steps.get(i), null, matched_zones));
            }
        }
        if (DEB.level >= 1) {
            System.out.println("recomposition begin : ");
            for (RecompositionStep step : result) {
                System.out.println("step : " + step.debug());
            }
            System.out.println("recomposition end ");
        }
        return result;
    }

    RecompositionStep recompose_step(
            DecompositionStep decomp_step,
            RecompositionStep previous,
            TreeMap<AbstractBasicRegion, AbstractBasicRegion> matched_zones) {
        AbstractDescription from = null;
        AbstractDescription to = null;

        AbstractCurve was_removed = decomp_step.removed();
        ArrayList<RecompData> added_contour_data = new ArrayList<RecompData>();
        if (previous != null) {
            from = previous.to();

            // find the resulting zones in the previous step got to
            ArrayList<AbstractBasicRegion> zones_to_split =
                    new ArrayList<AbstractBasicRegion>();

            TreeMap<AbstractBasicRegion, AbstractBasicRegion> zones_moved_during_decomp = decomp_step.zones_moved();
            Collection<AbstractBasicRegion> zones_after_moved =
                    zones_moved_during_decomp.values();

            HashMap<AbstractBasicRegion, AbstractBasicRegion> matched_inverse =
                    new HashMap<AbstractBasicRegion, AbstractBasicRegion>();
            Iterator<AbstractBasicRegion> moved_it = zones_after_moved.iterator();
            while (moved_it.hasNext()) {
                AbstractBasicRegion moved = moved_it.next();
                AbstractBasicRegion to_split = matched_zones.get(moved);
                //System.out.println("split this zone : "+to_split.debug());
                matched_inverse.put(to_split, moved);
                if (to_split != null) {
                    zones_to_split.add(to_split);
                } else {
                    throw new Error("match not found");
                }
            }
            // Partition zones_to_split
            ArrayList<Cluster> clusters = strategy.make_clusters(zones_to_split);

            if (DEB.level > 1) {
                for (Cluster c : clusters) {
                    System.out.println("cluster for recomposition is " + c.debug());
                }
            }

            TreeSet<AbstractBasicRegion> new_zone_set = from.getCopyOfZones();
            TreeSet<AbstractCurve> new_cont_set = from.getCopyOfContours();
            // for each cluster, make a Contour with label
            for (Cluster cluster : clusters) {
                AbstractCurve new_cont = was_removed.clone();
                ArrayList<AbstractBasicRegion> split_zones = new ArrayList<AbstractBasicRegion>();
                ArrayList<AbstractBasicRegion> added_zones = new ArrayList<AbstractBasicRegion>();
                new_cont_set.add(new_cont);
                ArrayList<AbstractBasicRegion> cluster_zones = cluster.zones();
                for (AbstractBasicRegion z : cluster_zones) {
                    split_zones.add(z);
                    AbstractBasicRegion new_zone = z.moved_in(new_cont);
                    new_zone_set.add(new_zone);
                    added_zones.add(new_zone);
                    AbstractBasicRegion decomp_z = matched_inverse.get(z);
                    //System.out.println("zone "+z.debug()+" has matched inverse "+decomp_z.debug());
                    matched_zones.put(decomp_z.moved_in(was_removed), new_zone);
                }
                added_contour_data.add(new RecompData(new_cont, split_zones, added_zones));
            }
            to = new AbstractDescription(new_cont_set, new_zone_set);

        } else {
            from = decomp_step.to()/*.copy()*/;

            // make a new Abstract Description
            TreeSet<AbstractCurve> cs = new TreeSet<AbstractCurve>();
            AbstractBasicRegion outside_zone = AbstractBasicRegion.get(cs);

            ArrayList<AbstractBasicRegion> split_zone = new ArrayList<AbstractBasicRegion>();
            ArrayList<AbstractBasicRegion> added_zone = new ArrayList<AbstractBasicRegion>();
            split_zone.add(outside_zone);
            added_contour_data.add(new RecompData(was_removed, split_zone, added_zone));

            cs.add(was_removed);
            AbstractBasicRegion new_zone = AbstractBasicRegion.get(cs);
            TreeSet<AbstractBasicRegion> new_zones = new TreeSet<AbstractBasicRegion>();
            new_zones.add(new_zone);
            new_zones.add(outside_zone);
            added_zone.add(new_zone);
            to = new AbstractDescription(cs, new_zones);
            matched_zones.put(outside_zone, outside_zone);
            matched_zones.put(new_zone, new_zone);
        }
        RecompositionStep result = new RecompositionStep(from, to, added_contour_data);
        return result;
    }
}
