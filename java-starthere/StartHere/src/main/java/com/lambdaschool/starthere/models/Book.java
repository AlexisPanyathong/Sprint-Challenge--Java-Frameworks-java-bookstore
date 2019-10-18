package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    private String title;

    private String isbn;

    @Column(nullable = true)
    private int copy;

    @ApiModelProperty(name = "bookAuthors", value = "List of Book Authors")
    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<BookAuthors> bookAuthors = new ArrayList<>();

    public Book()
    {
    }

    public Book(String title, String isbn, int copy, List<BookAuthors> bookAuthors)
    {
        this.title = title;
        this.isbn = isbn;
        this.copy = copy;

        for (BookAuthors ba : bookAuthors)
        {
            ba.setBook(this);
        }
        this.bookAuthors = bookAuthors;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopy()
    {
        return copy;
    }

    public void setCopy(int copy)
    {
        this.copy = copy;
    }

    public List<BookAuthors> getBookAuthors()
    {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthors> bookAuthors)
    {
        this.bookAuthors = bookAuthors;
    }
}
