package de.tum.in.ase.eist;

import java.util.List;

public class LinearSearch implements SearchStrategy {
    @Override
    public int performSearch(List<Chapter> list, String q) {
        for (var chapter : list) {
            if (q.equals(chapter.getName()))
                return chapter.getPageNumber();
        }
        return -1;
    }
}
