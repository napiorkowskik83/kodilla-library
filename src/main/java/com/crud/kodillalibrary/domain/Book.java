package com.crud.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "release_year")
    private Integer releaseYear;

    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy = "book",
            //cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<BookCopy> bookCopies  = new ArrayList<>();

    public Book(String title, String author, Integer releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}
