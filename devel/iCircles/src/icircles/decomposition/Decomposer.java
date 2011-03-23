package icircles.decomposition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.AbstractCurve;
import icircles.abstractDescription.AbstractBasicRegion;

public class Decomposer {

    DecompositionStrategy s;
    ArrayList<AbstractCurve> toRemove = new ArrayList<AbstractCurve>(); // some utility data

    public Decomposer(int decompStrategy) {
        s = DecompositionStrategy.getDecompositionStrategy(decompStrategy);
    }

    public Decomposer() {
        s = DecompositionStrategy.getDecompositionStrategy();
    }

    private DecompositionStep take_step(AbstractDescription ad, AbstractCurve c) {
        if (c == null) {
            return null;
        }

        // otherwise, make a new AbstractDescription
        TreeSet<AbstractCurve> contours = ad.getCopyOfContours();
        contours.remove(c);

        Iterator<AbstractBasicRegion> zoneIt = ad.getZoneIterator();
        TreeSet<AbstractBasicRegion> zones = new TreeSet<AbstractBasicRegion>();
        TreeMap<AbstractBasicRegion, AbstractBasicRegion> zones_moved = new TreeMap<AbstractBasicRegion, AbstractBasicRegion>();
        while (zoneIt.hasNext()) {
            AbstractBasicRegion z = zoneIt.next();
            AbstractBasicRegion znew = z.moveOutside(c);
            zones.add(znew);
            if (z != znew) {
                zones_moved.put(z, znew);
            }
        }
        AbstractDescription target_ad = new AbstractDescription(contours, zones);
        DecompositionStep result = new DecompositionStep(
                ad, target_ad, zones_moved, c);
        return result;
    }

    public ArrayList<DecompositionStep> decompose(AbstractDescription ad) {
        if (!ad.getZoneIterator().hasNext()) {
            throw new Error("decompose empty description?");
        }

        ArrayList<DecompositionStep> result = new ArrayList<DecompositionStep>();
        while_loop:
        while (true) {
            s.getContoursToRemove(ad, toRemove);

            if (toRemove.size() == 0) {
                break while_loop;
            }

            for (AbstractCurve c : toRemove) {
                DecompositionStep step = take_step(ad, c);
                if (step == null) {
                    break while_loop;
                }
                result.add(step);
                ad = step.target();
            }
        }
        if (DEB.level >= 1) {
            System.out.println("decomposition begin : ");
            for (DecompositionStep step : result) {
                System.out.println("step : " + step.debug());
            }
            System.out.println("decomposition end ");
        }
        return result;
    }
    /*
    public static void main(String args[])
    {
    Decomposer d = new Decomposer();
    DEB.level = 1;

    System.out.println("example 1: ____________ a b ab");
    ArrayList<DecompositionStep> steplist = d.decompose(
    AbstractDescription.makeForTesting("a b ab"));
    for(DecompositionStep step : steplist)
    System.out.println("step : "+step.debug());

    System.out.println("example 1: ____________ a b ab ac ad de");
    steplist = d.decompose(
    AbstractDescription.makeForTesting("a b ab ac ad de"));
    for(DecompositionStep step : steplist)
    System.out.println("step : "+step.debug());

    System.out.println("example 1: ____________ a(1) b a(2)b");
    // an example with multiple curves with the same label
    CurveLabel a = CurveLabel.get("a");
    CurveLabel b = CurveLabel.get("b");

    TreeSet<AbstractCurve> tsc = new TreeSet<AbstractCurve>();
    TreeSet<AbstractBasicRegion> tsz = new TreeSet<AbstractBasicRegion>();
    AbstractCurve ca1 = new AbstractCurve(a);
    AbstractCurve ca2 = new AbstractCurve(a);
    AbstractCurve cb = new AbstractCurve(b);
    tsz.add(AbstractBasicRegion.get(tsc)); // empty
    tsc.add(ca1);
    tsz.add(AbstractBasicRegion.get(tsc)); // in a(1)
    tsc.clear();
    tsc.add(cb);
    tsz.add(AbstractBasicRegion.get(tsc)); // in b
    tsc.add(ca2);
    tsz.add(AbstractBasicRegion.get(tsc)); // in a(2) and b
    tsc.add(ca1);
    AbstractDescription ad = new AbstractDescription(tsc, tsz);
    steplist = d.decompose(ad);
    for(DecompositionStep step : steplist)
    System.out.println("step : "+step.debug());
    }
     */
}
