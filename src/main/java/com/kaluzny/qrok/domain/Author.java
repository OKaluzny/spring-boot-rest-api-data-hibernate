package com.kaluzny.qrok.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AUTHORS")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private Sex sex;
    private List<Book> books;
    private Date birthDate;
    private List<Reward> rewards;

    @Column(name = "AUTHOR_FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "AUTHOR_LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "AUTHOR_SEX", nullable = true)
    @Enumerated(EnumType.STRING)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_fk"),
            inverseJoinColumns = @JoinColumn(name = "book_fk"))
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUTHOR_BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY /*orphanRemoval = true*/)
    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;

        Author author = (Author) o;

        if (!getFirstName().equals(author.getFirstName())) return false;
        if (!getLastName().equals(author.getLastName())) return false;
        if (getSex() != author.getSex()) return false;
        if (!getBooks().equals(author.getBooks())) return false;
        if (!getBirthDate().equals(author.getBirthDate())) return false;
        return getRewards().equals(author.getRewards());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + getBooks().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        result = 31 * result + getRewards().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", books=" + books +
                ", birthDate=" + birthDate +
                ", rewards=" + rewards +
                '}';
    }

    public enum Sex {

        MALE("Male"),
        FEMALE("Female");

        private final String name;

        Sex(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
