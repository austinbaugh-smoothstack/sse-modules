package lms.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private Author author;
    private Publisher publisher;
    private List<Genre> genres = new ArrayList<Genre>();
    
    public Integer getId() {
        return id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(final List<Genre> genres) {
        this.genres = genres;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(final Author author) {
        this.author = author;
    }
}
