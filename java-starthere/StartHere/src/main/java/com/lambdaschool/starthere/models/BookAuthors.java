package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bookauthors")
public class BookAuthors extends Auditable implements Serializable
{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties({"bookAuthors", "hibernateLazyInitializer"})
    private Book book;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties({"bookAuthors", "hibernateLazyInitializer"})
    private Author author;

    public BookAuthors()
    {
    }

    public BookAuthors(Book book, Author author)
    {
        this.book = book;
        this.author = author;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof BookAuthors))
        {
            return false;
        }
        BookAuthors that = (BookAuthors) o;
        return Objects.equals(getBook(), that.getBook()) && Objects.equals(getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getBook(), getAuthor());
    }
}
