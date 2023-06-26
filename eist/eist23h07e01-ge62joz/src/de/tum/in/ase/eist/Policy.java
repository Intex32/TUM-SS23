package de.tum.in.ase.eist;

public class Policy {
    private final Context context;

    public Policy(Context c) {
        this.context = c;
    }

    public void configure() {
        if (context.isChaptersSortedByName()) {
            context.setSearchAlgorithm(new BinarySearch());
        } else {
            context.setSearchAlgorithm(new LinearSearch());
        }
    }

    public Context getContext() {
        return context;
    }
}
