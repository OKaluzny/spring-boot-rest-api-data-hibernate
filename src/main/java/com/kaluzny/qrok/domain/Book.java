package com.kaluzny.qrok.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "BOOKS")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity {

    private String title;
    private String isbn;
    private Genre genre;
    private List<Author> authors;

    @Column(name = "BOOK_TITLE")
    @Size(min = 2, max = 30)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "BOOK_ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "BOOK_GENRE")
    @Enumerated(EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (!getTitle().equals(book.getTitle())) return false;
        if (!getIsbn().equals(book.getIsbn())) return false;
        if (getGenre() != book.getGenre()) return false;
        return getAuthors().equals(book.getAuthors());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getIsbn().hashCode();
        result = 31 * result + getGenre().hashCode();
        result = 31 * result + getAuthors().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                ", authors=" + authors +
                '}';
    }

    public enum Genre {

        COMEDY("Comedy"),
        DRAMA("Drama"),
        ROMANCE("Romance"),
        SATIRE("Satire"),
        TRAGEDY("Tragedy"),
        TRAGICOMEDY("Tragicomedy"),
        FANTASY("Fantasy");

        private final String name;

        Genre(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
