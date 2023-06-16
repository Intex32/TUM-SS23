package de.tum.in.ase.eist;

import java.util.List;

public class Context {

    private List<Chapter> book;
    private SearchStrategy searchAlgorithm;

    public Context() {

    }

    public boolean isChaptersSortedByName() {
        for (int i = 1; i < book.size(); i++)
            if (book.get(i - 1).getName().compareTo(book.get(i).getName()) > 0)
                return false;
        return true;
    }

    public int search(String q) {
        return searchAlgorithm.performSearch(book, q);
    }

    public void setBook(List<Chapter> book) {
        this.book = book;
    }

    public List<Chapter> getBook() {
        return book;
    }

    public SearchStrategy getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(SearchStrategy searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }
}
