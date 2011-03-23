package icircles.recomposition;

import java.util.ArrayList;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractBasicRegion;

public abstract class RecompositionStrategy {

    public static final int RECOMPOSE_NESTED = 0;
    public static final int RECOMPOSE_SINGLY_PIERCED = 1;
    public static final int RECOMPOSE_DOUBLY_PIERCED = 2;

    public static RecompositionStrategy getStrategy() {
        return new RecompositionStrategySinglyPierced();
    }

    public static RecompositionStrategy getStrategy(int strategy) {
        if (strategy == RECOMPOSE_NESTED) {
            return new RecompositionStrategyNested();
        } else if (strategy == RECOMPOSE_SINGLY_PIERCED) {
            return new RecompositionStrategySinglyPierced();
        } else {
            return new RecompositionStrategyDoublyPierced();
        }
    }
    private static String[] names = {
        "RECOMPOSE_NESTED",
        "RECOMPOSE_SINGLY_PIERCED",
        "RECOMPOSE_DOUBLY_PIERCED"
    };
    private static String[] nice_names = {
        "recompose using zero-piercing (nesting)",
        "recompose using single piercings",
        "recompose using double piercings"
    };

    public static String text_for(int recompStrategy) {
        DEB.assertCondition(recompStrategy >= 0
                && recompStrategy < names.length, "out of bounds");
        return names[recompStrategy];
    }

    public static String[] getRecompStrings() {
        return nice_names;
    }

    public abstract ArrayList<Cluster> make_clusters(
            ArrayList<AbstractBasicRegion> zones_to_split);
}
