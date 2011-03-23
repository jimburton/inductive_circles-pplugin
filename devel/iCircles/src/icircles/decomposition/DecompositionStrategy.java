package icircles.decomposition;

import java.util.ArrayList;

import icircles.util.DEB;
import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.AbstractCurve;

public abstract class DecompositionStrategy {

    public static final int SORT_ORDER = 0;
    public static final int SORT_ORDER_REV = 1;
    public static final int INNERMOST = 2;
    public static final int PIERCEDFIRST = 3;
    private static String[] names = {"SORT_ORDER", "SORT_ORDER_REV", "INNERMOST", "PIERCEDFIRST"};
    private static String[] nice_names = {
        "decompose in alphabetic order",
        "decompose in reverse alphabetic order",
        "decompose using fewest-zone contours first",
        "decompose using piercing curves first"};
    public static int strategy = INNERMOST;

    abstract void getContoursToRemove(AbstractDescription ad, ArrayList<AbstractCurve> toRemove);

    public static DecompositionStrategy getDecompositionStrategy() {
        return getDecompositionStrategy(strategy);
    }

    public static DecompositionStrategy getDecompositionStrategy(
            int decompStrategy) {
        if (decompStrategy == SORT_ORDER) {
            return new DecompositionStrategyUseSortOrder(true);
        } else if (decompStrategy == SORT_ORDER_REV) {
            return new DecompositionStrategyUseSortOrder(false);
        } else if (decompStrategy == INNERMOST) {
            return new DecompositionStrategyInnermost();
        } else if (decompStrategy == PIERCEDFIRST) {
            return new DecompositionStrategyPiercing();
        } else {
            throw new Error("unrecognised decomposition strategy");
        }
    }

    public static String text_for(int decompStrategy) {
        DEB.assertCondition(decompStrategy >= 0
                && decompStrategy < names.length,
                "out of bounds");
        return names[decompStrategy];
    }

    public static String[] getDecompStrings() {
        return nice_names;
    }
}
