package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "Section", description = "The Section Entity")

@Entity
@Table(name = "section")
public class Section extends Auditable {

    // sectionid
    @ApiModelProperty(name = "sectionid",
            value = "Primary key for the section",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sectionid;

    // sectionname
    @ApiModelProperty(name = "sectionname",
            value = "Section Name",
            required = true,
            example = "Fiction")
    @Column(nullable = false)
    private String name;



    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("sections")
    private List<Book> book = new ArrayList<>();

    // Default Constructor
    public Section() {

    }

    // Constructor
    public Section(String name) {
        this.name = name;
    }

    public Section(String name, List<Book> book) {
        this.name = name;
        this.book = book;
    }

    // Getters and Setters
    public long getSectionid() {
        return sectionid;
    }

    public void setSectionid(long sectionid) {
        this.sectionid = sectionid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    // toString
    @Override
    public String toString() {
        return "Section{" +
                "sectionid=" + sectionid +
                ", name='" + name + '\'' +
                ", book=" + book +
                '}';
    }
}